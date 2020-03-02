import path from 'path';
import url from 'url';
import swPrecache from 'sw-precache';
import UglifyJS from 'uglify-es';
import {format} from 'util';

const FILEPATH_WARNING = 'sw-prechache-webpack-plugin [filepath]: You are using a custom path for your service worker, this may prevent the service worker from working correctly if it is not available in the same path as your application.';
const FORCEDELETE_WARNING = 'sw-prechache-webpack-plugin [forceDelete]: You are specifying the option forceDelete. This was removed in v0.10. It should not affect your build but should no longer be required.';

const
  DEFAULT_CACHE_ID = 'sw-precache-webpack-plugin',
  DEFAULT_WORKER_FILENAME = 'service-worker.js',
  DEFAULT_PUBLIC_PATH = '',
  DEFAULT_IMPORT_SCRIPTS = [],
  DEFAULT_IGNORE_PATTERNS = [],
  CHUNK_NAME_NOT_FOUND_ERROR = 'Could not locate files for chunkName: "%s"',
  // eslint-disable-next-line max-len
  CHUNK_NAME_OVERRIDES_FILENAME_WARNING = 'Don\'t use chunkName & filename together; importScripts[<index>].filename overriden by specified chunkName: %j';

const DEFAULT_OPTIONS = {
  cacheId: DEFAULT_CACHE_ID,
  filename: DEFAULT_WORKER_FILENAME,
  importScripts: DEFAULT_IMPORT_SCRIPTS,
  staticFileGlobsIgnorePatterns: DEFAULT_IGNORE_PATTERNS,
  mergeStaticsConfig: false,
  minify: false,
};

class SWPrecacheWebpackPlugin {

  /**
   * SWPrecacheWebpackPlugin - A wrapper for sw-precache to use with webpack
   * @constructor
   * @param {object} options - All parameters should be passed as a single options object. All sw-precache options can be passed here in addition to plugin options.
   *
   * // plugin options:
   * @param {string} [options.filename] - Service worker filename, default is 'service-worker.js'
   * @param {string} [options.filepath] - Service worker path and name, default is to use webpack.output.path + options.filename
   * @param {RegExp} [options.staticFileGlobsIgnorePatterns[]] - Define an optional array of regex patterns to filter out of staticFileGlobs
   * @param {boolean} [options.mergeStaticsConfig=false] - Merge provided staticFileGlobs and stripPrefix(Multi) with webpack's config, rather than having those take precedence
   * @param {boolean} [options.minify=false] - Minify the generated Service worker file using UglifyJS
   * @param {boolean} [options.debug=false] - Output error and warning messages
   */
  constructor(options) {
    // generated configuration options
    this.config = {};
    // configuration options passed by user
    this.options = {
      ...DEFAULT_OPTIONS,
      ...options,
    };
    // generated configuration that will override user options
    this.overrides = {};
    // push warning messages here
    this.warnings = [];
  }

  /**
   * @returns {object} - plugin configuration
   */
  get workerOptions() {
    return {
      ...this.config,
      ...this.options,
      ...this.overrides,
    };
  }

  apply(compiler) {
    // sw-precache needs physical files to reference so we MUST wait until after assets are emitted before generating the service-worker.
    const afterEmit = (compilation, callback) => {
      this.configure(compiler, compilation); // configure the serviceworker options
      this.checkWarnings(compilation);

      // generate service worker then write to file system
      this.createServiceWorker()
        .then(serviceWorker => this.writeServiceWorker(serviceWorker, compiler))
        .then(() => callback())
        .catch(err => callback(err));
    };
    if (compiler.hooks) {
      compiler.hooks.afterEmit.tapAsync('swPrecache', afterEmit);
    } else {
      compiler.plugin('after-emit', afterEmit);
    }
  }

  configure(compiler, compilation) {

    // get the defaults from options
    const {
      importScripts,
      staticFileGlobsIgnorePatterns,
      mergeStaticsConfig,
      stripPrefixMulti = {},
    } = this.options;

    // get the output path used by webpack
    const {outputPath} = compiler;

    // outputPath + filename or the user option
    const {filepath = path.resolve(outputPath, this.options.filename)} = this.options;

    // get the public path specified in webpack config
    const {publicPath = DEFAULT_PUBLIC_PATH} = compiler.options.output;

    // get all assets outputted by webpack
    const assetGlobs = Object
      .keys(compilation.assets)
      .map(f => path.join(outputPath, f));

    // merge assetGlobs with provided staticFileGlobs and filter using staticFileGlobsIgnorePatterns
    const staticFileGlobs = assetGlobs.concat(this.options.staticFileGlobs || []).filter(text =>
      (!staticFileGlobsIgnorePatterns.some((regex) => regex.test(text)))
    );

    if (outputPath) {
      // strip the webpack config's output.path (replace for windows users)
      stripPrefixMulti[`${outputPath}${path.sep}`.replace(/\\/g, '/')] = publicPath;
    }

    this.config = {
      ...this.config,
      staticFileGlobs,
      stripPrefixMulti,
    };

    // set the actual filepath
    this.overrides.filepath = filepath;

    // resolve [hash] used in importScripts
    this.configureImportScripts(importScripts, publicPath, compiler, compilation);

    if (mergeStaticsConfig) {
      // merge generated and user provided options
      this.overrides = {
        ...this.overrides,
        staticFileGlobs,
        stripPrefixMulti,
      };
    }
  }

  configureImportScripts(importScripts, publicPath, compiler, compilation) {
    if (!importScripts) {
      return;
    }

    const {hash, chunks} = compilation.getStats()
      .toJson({hash: true, chunks: true});

    this.overrides.importScripts = importScripts
      .reduce((fileList, criteria) => {
        // legacy support for importScripts items defined as string
        if (typeof criteria === 'string') {
          criteria = {filename: criteria};
        }

        const hasFileName = !!criteria.filename;
        const hasChunkName = !!criteria.chunkName;

        if (hasFileName && hasChunkName) {
          this.warnings.push(new Error(
            format(CHUNK_NAME_OVERRIDES_FILENAME_WARNING, criteria)
          ));
        }

        if (hasChunkName) {
          const chunk = chunks.find(c => c.names.includes(criteria.chunkName));

          if (!chunk) {
            compilation.errors.push(new Error(
              format(CHUNK_NAME_NOT_FOUND_ERROR, criteria.chunkName)
            ));
            return fileList;
          }

          const chunkFileName = chunk.files[chunk.names.indexOf(criteria.chunkName)];
          fileList.push(url.resolve(publicPath, chunkFileName));
        } else if (hasFileName) {
          const hashedFilename = criteria.filename.replace(/\[hash\]/g, hash);
          fileList.push(url.resolve(publicPath, hashedFilename));
        }

        return fileList;
      }, []);
  }

  createServiceWorker() {
    return swPrecache.generate(this.workerOptions)
      .then((serviceWorkerFileContents) => {
        if (this.options.minify) {
          const uglifyFiles = {};
          uglifyFiles[this.options.filename] = serviceWorkerFileContents;
          return UglifyJS.minify(uglifyFiles).code;
        }
        return serviceWorkerFileContents;
      });
  }

  writeServiceWorker(serviceWorker, compiler) {
    const {filepath} = this.workerOptions;
    const {outputFileSystem} = compiler;

    // use the outputFileSystem api to manually write service workers rather than adding to the compilation assets
    return new Promise((resolve, reject) => {
      outputFileSystem.mkdirp(path.resolve(filepath, '..'), (mkdirErr) => {
        if (mkdirErr) {
          reject(mkdirErr);
          return;
        }
        outputFileSystem.writeFile(filepath, serviceWorker, writeError => {
          if (writeError) {
            reject(writeError);
          } else {
            resolve();
          }
        });
      });
    });
  }

  /**
   * Push plugin warnings to webpack log
   * @param {object} compilation - webpack compilation
   * @returns {void}
   */
  checkWarnings(compilation) {
    if (this.options.filepath) {
      // warn about changing filepath
      this.warnings.push(new Error(FILEPATH_WARNING));
    }

    if (this.options.forceDelete) {
      // deprecate forceDelete
      this.warnings.push(new Error(FORCEDELETE_WARNING));
    }

    if (this.workerOptions.debug) {
      this.warnings.forEach(warning => compilation.warnings.push(warning));
    }
  }
}


module.exports = SWPrecacheWebpackPlugin;
