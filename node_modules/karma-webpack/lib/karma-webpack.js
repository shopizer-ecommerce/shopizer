'use strict';

var _toConsumableArray2 = require('babel-runtime/helpers/toConsumableArray');

var _toConsumableArray3 = _interopRequireDefault(_toConsumableArray2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/* eslint-disable
  max-len,
  no-console,
  func-style,
*/

var os = require('os');
var _ = require('lodash');
var path = require('path');
var async = require('async');
var webpackDevMiddleware = require('webpack-dev-middleware');
var webpack = require('webpack');
var SingleEntryDependency = require('webpack/lib/dependencies/SingleEntryDependency');

var blocked = [];
var isBlocked = false;

var escapeRegExp = function escapeRegExp(str) {
  // See details here https://stackoverflow.com/questions/3446170/escape-string-for-use-in-javascript-regex
  return str.replace(/[-[\]/{}()*+?.\\^$|]/g, '\\$&');
};

function Plugin(
/* config.webpack */webpackOptions,
/* config.webpackServer */webpackServerOptions,
/* config.webpackMiddleware */webpackMiddlewareOptions,
/* config.basePath */basePath,
/* config.files */files,
/* config.frameworks */frameworks,
/* config.singleRun */singleRun, customFileHandlers, emitter) {
  webpackOptions = _.clone(webpackOptions) || {};
  webpackMiddlewareOptions = _.clone(webpackMiddlewareOptions || webpackServerOptions) || {};

  var applyOptions = Array.isArray(webpackOptions) ? webpackOptions : [webpackOptions];
  var includeIndex = applyOptions.length > 1;

  applyOptions.forEach(function (webpackOptions, index) {
    // The webpack tier owns the watch behavior so we want to force it in the config
    webpackOptions.watch = !singleRun;

    // Webpack 2.1.0-beta.7+ will throw in error if both entry and plugins are not specified in options
    // https://github.com/webpack/webpack/commit/b3bc5427969e15fd3663d9a1c57dbd1eb2c94805
    if (!webpackOptions.entry) {
      webpackOptions.entry = function () {
        return {};
      };
    };

    if (!webpackOptions.output) {
      webpackOptions.output = {};
    };

    // When using an array, even of length 1, we want to include the index value for the build.
    // This is due to the way that the dev server exposes commonPath for build output.
    var indexPath = includeIndex ? `${index}/` : '';
    var publicPath = indexPath !== '' ? `${indexPath}/` : '';

    // Must have the common _karma_webpack_ prefix on path here to avoid
    // https://github.com/webpack/webpack/issues/645
    webpackOptions.output.path = path.join(os.tmpdir(), '_karma_webpack_', indexPath, '/');
    webpackOptions.output.publicPath = path.join(os.tmpdir(), '_karma_webpack_', publicPath, '/');
    webpackOptions.output.filename = '[name]';
    if (includeIndex) {
      webpackOptions.output.jsonpFunction = `webpackJsonp${index}`;
    }
    webpackOptions.output.chunkFilename = '[id].bundle.js';
  });

  this.emitter = emitter;
  this.wrapMocha = frameworks.indexOf('mocha') >= 0 && includeIndex;
  this.optionsCount = applyOptions.length;
  this.files = [];
  this.basePath = basePath;
  this.waiting = [];

  var compiler;

  try {
    compiler = webpack(webpackOptions);
  } catch (e) {
    console.error(e.stack || e);
    if (e.details) {
      console.error(e.details);
    }
    throw e;
  }

  var applyPlugins = compiler.compilers || [compiler];

  applyPlugins.forEach(function (compiler) {
    compiler.plugin('this-compilation', function (compilation, params) {
      compilation.dependencyFactories.set(SingleEntryDependency, params.normalModuleFactory);
    });
    compiler.plugin('make', this.make.bind(this));
  }, this);

  ['invalid', 'watch-run', 'run'].forEach(function (name) {
    compiler.plugin(name, function (_, callback) {
      isBlocked = true;

      if (typeof callback === 'function') {
        callback();
      }
    });
  });

  compiler.plugin('done', function (stats) {
    var applyStats = Array.isArray(stats.stats) ? stats.stats : [stats];
    var assets = [];
    var noAssets = false;

    applyStats.forEach(function (stats) {
      stats = stats.toJson();

      assets.push.apply(assets, (0, _toConsumableArray3.default)(stats.assets));
      if (stats.assets.length === 0) {
        noAssets = true;
      }
    });

    if (!this.waiting || this.waiting.length === 0) {
      this.notifyKarmaAboutChanges();
    }

    if (this.waiting && !noAssets) {
      var w = this.waiting;

      this.waiting = null;
      w.forEach(function (cb) {
        cb();
      });
    }

    isBlocked = false;
    for (var i = 0; i < blocked.length; i++) {
      blocked[i]();
    }
    blocked = [];
  }.bind(this));
  compiler.plugin('invalid', function () {
    if (!this.waiting) {
      this.waiting = [];
    }
  }.bind(this));

  webpackMiddlewareOptions.publicPath = path.join(os.tmpdir(), '_karma_webpack_', '/');
  var middleware = this.middleware = new webpackDevMiddleware(compiler, webpackMiddlewareOptions);

  customFileHandlers.push({
    urlRegex: new RegExp(`^${escapeRegExp(webpackMiddlewareOptions.publicPath)}.*`),
    handler(req, res) {
      middleware(req, res, function () {
        res.statusCode = 404;
        res.end('Not found');
      });
    }
  });

  emitter.on('exit', function (done) {
    middleware.close();
    done();
  });
}

Plugin.prototype.notifyKarmaAboutChanges = function () {
  // Force a rebuild
  this.emitter.refreshFiles();
};

Plugin.prototype.addFile = function (entry) {
  if (this.files.indexOf(entry) >= 0) {
    return;
  }
  this.files.push(entry);

  return true;
};

Plugin.prototype.make = function (compilation, callback) {
  async.forEach(this.files.slice(), function (file, callback) {
    var entry = file;

    if (this.wrapMocha) {
      entry = `${require.resolve('./mocha-env-loader')}!${entry}`;
    }

    var dep = new SingleEntryDependency(entry);

    compilation.addEntry('', dep, path.relative(this.basePath, file).replace(/\\/g, '/'), function (err) {
      // If the module fails because of an File not found error, remove the test file
      if (dep.module && dep.module.error && dep.module.error.error && dep.module.error.error.code === 'ENOENT') {
        this.files = this.files.filter(function (f) {
          return file !== f;
        });
        this.middleware.invalidate();
      }
      callback(err);
    }.bind(this));
  }.bind(this), callback);
};

Plugin.prototype.readFile = function (file, callback) {
  var middleware = this.middleware;
  var optionsCount = this.optionsCount;

  var doRead = function () {
    if (optionsCount > 1) {
      async.times(optionsCount, function (idx, callback) {
        middleware.fileSystem.readFile(path.join(os.tmpdir(), '_karma_webpack_', String(idx), file.replace(/\\/g, '/')), callback);
      }, function (err, contents) {
        if (err) {
          return callback(err);
        };
        contents = contents.reduce(function (arr, x) {
          if (!arr) {
            return [x];
          };
          arr.push(new Buffer('\n'), x);

          return arr;
        }, null);
        callback(null, Buffer.concat(contents));
      });
    } else {
      try {
        var fileContents = middleware.fileSystem.readFileSync(path.join(os.tmpdir(), '_karma_webpack_', file.replace(/\\/g, '/')));

        callback(undefined, fileContents);
      } catch (e) {
        // If this is an error from `readFileSync` method, wait for the next tick.
        // Credit #69 @mewdriller
        if (e.code === 'ENOENT') {
          // eslint-disable-line quotes
          this.waiting = [process.nextTick.bind(process, this.readFile.bind(this, file, callback))];

          // throw otherwise
        } else {
          callback(e);
        }
      }
    }
  }.bind(this);

  if (!this.waiting) {
    doRead();
  } else {
    // Retry to read once a build is finished
    // do it on process.nextTick to catch changes while building
    this.waiting.push(process.nextTick.bind(process, this.readFile.bind(this, file, callback)));
  }
};

function createPreprocesor( /* config.basePath */basePath, webpackPlugin) {
  return function (content, file, done) {
    if (webpackPlugin.addFile(file.originalPath)) {
      // recompile as we have an asset that we have not seen before
      webpackPlugin.middleware.invalidate();
    }

    // read blocks until bundle is done
    webpackPlugin.readFile(path.relative(basePath, file.originalPath), function (err, content) {
      if (err) {
        throw err;
      }

      done(err, content && content.toString());
    });
  };
}

function createWebpackBlocker() {
  return function (request, response, next) {
    if (isBlocked) {
      blocked.push(next);
    } else {
      next();
    }
  };
}

module.exports = {
  webpackPlugin: ['type', Plugin],
  'preprocessor:webpack': ['factory', createPreprocesor],
  'middleware:webpackBlocker': ['factory', createWebpackBlocker]
};