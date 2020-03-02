'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.recursivePatternCapture = recursivePatternCapture;

var _fs = require('fs');

var _fs2 = _interopRequireDefault(_fs);

var _doctrine = require('doctrine');

var _doctrine2 = _interopRequireDefault(_doctrine);

var _debug = require('debug');

var _debug2 = _interopRequireDefault(_debug);

var _parse = require('eslint-module-utils/parse');

var _parse2 = _interopRequireDefault(_parse);

var _resolve = require('eslint-module-utils/resolve');

var _resolve2 = _interopRequireDefault(_resolve);

var _ignore = require('eslint-module-utils/ignore');

var _ignore2 = _interopRequireDefault(_ignore);

var _hash = require('eslint-module-utils/hash');

var _unambiguous = require('eslint-module-utils/unambiguous');

var unambiguous = _interopRequireWildcard(_unambiguous);

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } else { var newObj = {}; if (obj != null) { for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key]; } } newObj.default = obj; return newObj; } }

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const log = (0, _debug2.default)('eslint-plugin-import:ExportMap');

const exportCache = new Map();

class ExportMap {
  constructor(path) {
    this.path = path;
    this.namespace = new Map();
    // todo: restructure to key on path, value is resolver + map of names
    this.reexports = new Map();
    /**
     * star-exports
     * @type {Set} of () => ExportMap
     */
    this.dependencies = new Set();
    /**
     * dependencies of this module that are not explicitly re-exported
     * @type {Map} from path = () => ExportMap
     */
    this.imports = new Map();
    this.errors = [];
  }

  get hasDefault() {
    return this.get('default') != null;
  } // stronger than this.has

  get size() {
    let size = this.namespace.size + this.reexports.size;
    this.dependencies.forEach(dep => size += dep().size);
    return size;
  }

  /**
   * Note that this does not check explicitly re-exported names for existence
   * in the base namespace, but it will expand all `export * from '...'` exports
   * if not found in the explicit namespace.
   * @param  {string}  name
   * @return {Boolean} true if `name` is exported by this module.
   */
  has(name) {
    if (this.namespace.has(name)) return true;
    if (this.reexports.has(name)) return true;

    // default exports must be explicitly re-exported (#328)
    if (name !== 'default') {
      for (let dep of this.dependencies) {
        let innerMap = dep();

        // todo: report as unresolved?
        if (!innerMap) continue;

        if (innerMap.has(name)) return true;
      }
    }

    return false;
  }

  /**
   * ensure that imported name fully resolves.
   * @param  {[type]}  name [description]
   * @return {Boolean}      [description]
   */
  hasDeep(name) {
    if (this.namespace.has(name)) return { found: true, path: [this] };

    if (this.reexports.has(name)) {
      const reexports = this.reexports.get(name),
            imported = reexports.getImport();

      // if import is ignored, return explicit 'null'
      if (imported == null) return { found: true, path: [this] };

      // safeguard against cycles, only if name matches
      if (imported.path === this.path && reexports.local === name) {
        return { found: false, path: [this] };
      }

      const deep = imported.hasDeep(reexports.local);
      deep.path.unshift(this);

      return deep;
    }

    // default exports must be explicitly re-exported (#328)
    if (name !== 'default') {
      for (let dep of this.dependencies) {
        let innerMap = dep();
        // todo: report as unresolved?
        if (!innerMap) continue;

        // safeguard against cycles
        if (innerMap.path === this.path) continue;

        let innerValue = innerMap.hasDeep(name);
        if (innerValue.found) {
          innerValue.path.unshift(this);
          return innerValue;
        }
      }
    }

    return { found: false, path: [this] };
  }

  get(name) {
    if (this.namespace.has(name)) return this.namespace.get(name);

    if (this.reexports.has(name)) {
      const reexports = this.reexports.get(name),
            imported = reexports.getImport();

      // if import is ignored, return explicit 'null'
      if (imported == null) return null;

      // safeguard against cycles, only if name matches
      if (imported.path === this.path && reexports.local === name) return undefined;

      return imported.get(reexports.local);
    }

    // default exports must be explicitly re-exported (#328)
    if (name !== 'default') {
      for (let dep of this.dependencies) {
        let innerMap = dep();
        // todo: report as unresolved?
        if (!innerMap) continue;

        // safeguard against cycles
        if (innerMap.path === this.path) continue;

        let innerValue = innerMap.get(name);
        if (innerValue !== undefined) return innerValue;
      }
    }

    return undefined;
  }

  forEach(callback, thisArg) {
    this.namespace.forEach((v, n) => callback.call(thisArg, v, n, this));

    this.reexports.forEach((reexports, name) => {
      const reexported = reexports.getImport();
      // can't look up meta for ignored re-exports (#348)
      callback.call(thisArg, reexported && reexported.get(reexports.local), name, this);
    });

    this.dependencies.forEach(dep => {
      const d = dep();
      // CJS / ignored dependencies won't exist (#717)
      if (d == null) return;

      d.forEach((v, n) => n !== 'default' && callback.call(thisArg, v, n, this));
    });
  }

  // todo: keys, values, entries?

  reportErrors(context, declaration) {
    context.report({
      node: declaration.source,
      message: `Parse errors in imported module '${declaration.source.value}': ` + `${this.errors.map(e => `${e.message} (${e.lineNumber}:${e.column})`).join(', ')}`
    });
  }
}

exports.default = ExportMap; /**
                              * parse docs from the first node that has leading comments
                              * @param  {...[type]} nodes [description]
                              * @return {{doc: object}}
                              */

function captureDoc(docStyleParsers) {
  const metadata = {},
        nodes = Array.prototype.slice.call(arguments, 1);

  // 'some' short-circuits on first 'true'
  nodes.some(n => {
    if (!n.leadingComments) return false;

    for (let name in docStyleParsers) {
      const doc = docStyleParsers[name](n.leadingComments);
      if (doc) {
        metadata.doc = doc;
      }
    }

    return true;
  });

  return metadata;
}

const availableDocStyleParsers = {
  jsdoc: captureJsDoc,
  tomdoc: captureTomDoc
};

/**
 * parse JSDoc from leading comments
 * @param  {...[type]} comments [description]
 * @return {{doc: object}}
 */
function captureJsDoc(comments) {
  let doc;

  // capture XSDoc
  comments.forEach(comment => {
    // skip non-block comments
    if (comment.value.slice(0, 4) !== '*\n *') return;
    try {
      doc = _doctrine2.default.parse(comment.value, { unwrap: true });
    } catch (err) {
      /* don't care, for now? maybe add to `errors?` */
    }
  });

  return doc;
}

/**
  * parse TomDoc section from comments
  */
function captureTomDoc(comments) {
  // collect lines up to first paragraph break
  const lines = [];
  for (let i = 0; i < comments.length; i++) {
    const comment = comments[i];
    if (comment.value.match(/^\s*$/)) break;
    lines.push(comment.value.trim());
  }

  // return doctrine-like object
  const statusMatch = lines.join(' ').match(/^(Public|Internal|Deprecated):\s*(.+)/);
  if (statusMatch) {
    return {
      description: statusMatch[2],
      tags: [{
        title: statusMatch[1].toLowerCase(),
        description: statusMatch[2]
      }]
    };
  }
}

ExportMap.get = function (source, context) {
  const path = (0, _resolve2.default)(source, context);
  if (path == null) return null;

  return ExportMap.for(childContext(path, context));
};

ExportMap.for = function (context) {
  const path = context.path;


  const cacheKey = (0, _hash.hashObject)(context).digest('hex');
  let exportMap = exportCache.get(cacheKey);

  // return cached ignore
  if (exportMap === null) return null;

  const stats = _fs2.default.statSync(path);
  if (exportMap != null) {
    // date equality check
    if (exportMap.mtime - stats.mtime === 0) {
      return exportMap;
    }
    // future: check content equality?
  }

  // check valid extensions first
  if (!(0, _ignore.hasValidExtension)(path, context)) {
    exportCache.set(cacheKey, null);
    return null;
  }

  const content = _fs2.default.readFileSync(path, { encoding: 'utf8' });

  // check for and cache ignore
  if ((0, _ignore2.default)(path, context) || !unambiguous.test(content)) {
    log('ignored path due to unambiguous regex or ignore settings:', path);
    exportCache.set(cacheKey, null);
    return null;
  }

  log('cache miss', cacheKey, 'for path', path);
  exportMap = ExportMap.parse(path, content, context);

  // ambiguous modules return null
  if (exportMap == null) return null;

  exportMap.mtime = stats.mtime;

  exportCache.set(cacheKey, exportMap);
  return exportMap;
};

ExportMap.parse = function (path, content, context) {
  var m = new ExportMap(path);

  try {
    var ast = (0, _parse2.default)(path, content, context);
  } catch (err) {
    log('parse error:', path, err);
    m.errors.push(err);
    return m; // can't continue
  }

  if (!unambiguous.isModule(ast)) return null;

  const docstyle = context.settings && context.settings['import/docstyle'] || ['jsdoc'];
  const docStyleParsers = {};
  docstyle.forEach(style => {
    docStyleParsers[style] = availableDocStyleParsers[style];
  });

  // attempt to collect module doc
  if (ast.comments) {
    ast.comments.some(c => {
      if (c.type !== 'Block') return false;
      try {
        const doc = _doctrine2.default.parse(c.value, { unwrap: true });
        if (doc.tags.some(t => t.title === 'module')) {
          m.doc = doc;
          return true;
        }
      } catch (err) {/* ignore */}
      return false;
    });
  }

  const namespaces = new Map();

  function remotePath(value) {
    return _resolve2.default.relative(value, path, context.settings);
  }

  function resolveImport(value) {
    const rp = remotePath(value);
    if (rp == null) return null;
    return ExportMap.for(childContext(rp, context));
  }

  function getNamespace(identifier) {
    if (!namespaces.has(identifier.name)) return;

    return function () {
      return resolveImport(namespaces.get(identifier.name));
    };
  }

  function addNamespace(object, identifier) {
    const nsfn = getNamespace(identifier);
    if (nsfn) {
      Object.defineProperty(object, 'namespace', { get: nsfn });
    }

    return object;
  }

  function captureDependency(declaration) {
    if (declaration.source == null) return null;

    const p = remotePath(declaration.source.value);
    if (p == null) return null;
    const existing = m.imports.get(p);
    if (existing != null) return existing.getter;

    const getter = () => ExportMap.for(childContext(p, context));
    m.imports.set(p, {
      getter,
      source: { // capturing actual node reference holds full AST in memory!
        value: declaration.source.value,
        loc: declaration.source.loc
      }
    });
    return getter;
  }

  ast.body.forEach(function (n) {

    if (n.type === 'ExportDefaultDeclaration') {
      const exportMeta = captureDoc(docStyleParsers, n);
      if (n.declaration.type === 'Identifier') {
        addNamespace(exportMeta, n.declaration);
      }
      m.namespace.set('default', exportMeta);
      return;
    }

    if (n.type === 'ExportAllDeclaration') {
      const getter = captureDependency(n);
      if (getter) m.dependencies.add(getter);
      return;
    }

    // capture namespaces in case of later export
    if (n.type === 'ImportDeclaration') {
      captureDependency(n);
      let ns;
      if (n.specifiers.some(s => s.type === 'ImportNamespaceSpecifier' && (ns = s))) {
        namespaces.set(ns.local.name, n.source.value);
      }
      return;
    }

    if (n.type === 'ExportNamedDeclaration') {
      // capture declaration
      if (n.declaration != null) {
        switch (n.declaration.type) {
          case 'FunctionDeclaration':
          case 'ClassDeclaration':
          case 'TypeAlias': // flowtype with babel-eslint parser
          case 'InterfaceDeclaration':
          case 'TSEnumDeclaration':
          case 'TSInterfaceDeclaration':
          case 'TSAbstractClassDeclaration':
          case 'TSModuleDeclaration':
            m.namespace.set(n.declaration.id.name, captureDoc(docStyleParsers, n));
            break;
          case 'VariableDeclaration':
            n.declaration.declarations.forEach(d => recursivePatternCapture(d.id, id => m.namespace.set(id.name, captureDoc(docStyleParsers, d, n))));
            break;
        }
      }

      const nsource = n.source && n.source.value;
      n.specifiers.forEach(s => {
        const exportMeta = {};
        let local;

        switch (s.type) {
          case 'ExportDefaultSpecifier':
            if (!n.source) return;
            local = 'default';
            break;
          case 'ExportNamespaceSpecifier':
            m.namespace.set(s.exported.name, Object.defineProperty(exportMeta, 'namespace', {
              get() {
                return resolveImport(nsource);
              }
            }));
            return;
          case 'ExportSpecifier':
            if (!n.source) {
              m.namespace.set(s.exported.name, addNamespace(exportMeta, s.local));
              return;
            }
          // else falls through
          default:
            local = s.local.name;
            break;
        }

        // todo: JSDoc
        m.reexports.set(s.exported.name, { local, getImport: () => resolveImport(nsource) });
      });
    }
  });

  return m;
};

/**
 * Traverse a pattern/identifier node, calling 'callback'
 * for each leaf identifier.
 * @param  {node}   pattern
 * @param  {Function} callback
 * @return {void}
 */
function recursivePatternCapture(pattern, callback) {
  switch (pattern.type) {
    case 'Identifier':
      // base case
      callback(pattern);
      break;

    case 'ObjectPattern':
      pattern.properties.forEach(p => {
        recursivePatternCapture(p.value, callback);
      });
      break;

    case 'ArrayPattern':
      pattern.elements.forEach(element => {
        if (element == null) return;
        recursivePatternCapture(element, callback);
      });
      break;
  }
}

/**
 * don't hold full context object in memory, just grab what we need.
 */
function childContext(path, context) {
  const settings = context.settings,
        parserOptions = context.parserOptions,
        parserPath = context.parserPath;

  return {
    settings,
    parserOptions,
    parserPath,
    path
  };
}
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIkV4cG9ydE1hcC5qcyJdLCJuYW1lcyI6WyJyZWN1cnNpdmVQYXR0ZXJuQ2FwdHVyZSIsInVuYW1iaWd1b3VzIiwibG9nIiwiZXhwb3J0Q2FjaGUiLCJNYXAiLCJFeHBvcnRNYXAiLCJjb25zdHJ1Y3RvciIsInBhdGgiLCJuYW1lc3BhY2UiLCJyZWV4cG9ydHMiLCJkZXBlbmRlbmNpZXMiLCJTZXQiLCJpbXBvcnRzIiwiZXJyb3JzIiwiaGFzRGVmYXVsdCIsImdldCIsInNpemUiLCJmb3JFYWNoIiwiZGVwIiwiaGFzIiwibmFtZSIsImlubmVyTWFwIiwiaGFzRGVlcCIsImZvdW5kIiwiaW1wb3J0ZWQiLCJnZXRJbXBvcnQiLCJsb2NhbCIsImRlZXAiLCJ1bnNoaWZ0IiwiaW5uZXJWYWx1ZSIsInVuZGVmaW5lZCIsImNhbGxiYWNrIiwidGhpc0FyZyIsInYiLCJuIiwiY2FsbCIsInJlZXhwb3J0ZWQiLCJkIiwicmVwb3J0RXJyb3JzIiwiY29udGV4dCIsImRlY2xhcmF0aW9uIiwicmVwb3J0Iiwibm9kZSIsInNvdXJjZSIsIm1lc3NhZ2UiLCJ2YWx1ZSIsIm1hcCIsImUiLCJsaW5lTnVtYmVyIiwiY29sdW1uIiwiam9pbiIsImNhcHR1cmVEb2MiLCJkb2NTdHlsZVBhcnNlcnMiLCJtZXRhZGF0YSIsIm5vZGVzIiwiQXJyYXkiLCJwcm90b3R5cGUiLCJzbGljZSIsImFyZ3VtZW50cyIsInNvbWUiLCJsZWFkaW5nQ29tbWVudHMiLCJkb2MiLCJhdmFpbGFibGVEb2NTdHlsZVBhcnNlcnMiLCJqc2RvYyIsImNhcHR1cmVKc0RvYyIsInRvbWRvYyIsImNhcHR1cmVUb21Eb2MiLCJjb21tZW50cyIsImNvbW1lbnQiLCJwYXJzZSIsInVud3JhcCIsImVyciIsImxpbmVzIiwiaSIsImxlbmd0aCIsIm1hdGNoIiwicHVzaCIsInRyaW0iLCJzdGF0dXNNYXRjaCIsImRlc2NyaXB0aW9uIiwidGFncyIsInRpdGxlIiwidG9Mb3dlckNhc2UiLCJmb3IiLCJjaGlsZENvbnRleHQiLCJjYWNoZUtleSIsImRpZ2VzdCIsImV4cG9ydE1hcCIsInN0YXRzIiwic3RhdFN5bmMiLCJtdGltZSIsInNldCIsImNvbnRlbnQiLCJyZWFkRmlsZVN5bmMiLCJlbmNvZGluZyIsInRlc3QiLCJtIiwiYXN0IiwiaXNNb2R1bGUiLCJkb2NzdHlsZSIsInNldHRpbmdzIiwic3R5bGUiLCJjIiwidHlwZSIsInQiLCJuYW1lc3BhY2VzIiwicmVtb3RlUGF0aCIsInJlbGF0aXZlIiwicmVzb2x2ZUltcG9ydCIsInJwIiwiZ2V0TmFtZXNwYWNlIiwiaWRlbnRpZmllciIsImFkZE5hbWVzcGFjZSIsIm9iamVjdCIsIm5zZm4iLCJPYmplY3QiLCJkZWZpbmVQcm9wZXJ0eSIsImNhcHR1cmVEZXBlbmRlbmN5IiwicCIsImV4aXN0aW5nIiwiZ2V0dGVyIiwibG9jIiwiYm9keSIsImV4cG9ydE1ldGEiLCJhZGQiLCJucyIsInNwZWNpZmllcnMiLCJzIiwiaWQiLCJkZWNsYXJhdGlvbnMiLCJuc291cmNlIiwiZXhwb3J0ZWQiLCJwYXR0ZXJuIiwicHJvcGVydGllcyIsImVsZW1lbnRzIiwiZWxlbWVudCIsInBhcnNlck9wdGlvbnMiLCJwYXJzZXJQYXRoIl0sIm1hcHBpbmdzIjoiOzs7OztRQTJlZ0JBLHVCLEdBQUFBLHVCOztBQTNlaEI7Ozs7QUFFQTs7OztBQUVBOzs7O0FBRUE7Ozs7QUFDQTs7OztBQUNBOzs7O0FBRUE7O0FBQ0E7O0lBQVlDLFc7Ozs7OztBQUVaLE1BQU1DLE1BQU0scUJBQU0sZ0NBQU4sQ0FBWjs7QUFFQSxNQUFNQyxjQUFjLElBQUlDLEdBQUosRUFBcEI7O0FBRWUsTUFBTUMsU0FBTixDQUFnQjtBQUM3QkMsY0FBWUMsSUFBWixFQUFrQjtBQUNoQixTQUFLQSxJQUFMLEdBQVlBLElBQVo7QUFDQSxTQUFLQyxTQUFMLEdBQWlCLElBQUlKLEdBQUosRUFBakI7QUFDQTtBQUNBLFNBQUtLLFNBQUwsR0FBaUIsSUFBSUwsR0FBSixFQUFqQjtBQUNBOzs7O0FBSUEsU0FBS00sWUFBTCxHQUFv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QUFPTyxTQUFTaEcsdUJBQVQsQ0FBaUNvSSxPQUFqQyxFQUEwQ3JHLFFBQTFDLEVBQW9EO0FBQ3pELFVBQVFxRyxRQUFRN0IsSUFBaEI7QUFDRSxTQUFLLFlBQUw7QUFBbUI7QUFDakJ4RSxlQUFTcUcsT0FBVDtBQUNBOztBQUVGLFNBQUssZUFBTDtBQUNFQSxjQUFRQyxVQUFSLENBQW1CcEgsT0FBbkIsQ0FBMkJxRyxLQUFLO0FBQzlCdEgsZ0NBQXdCc0gsRUFBRXpFLEtBQTFCLEVBQWlDZCxRQUFqQztBQUNELE9BRkQ7QUFHQTs7QUFFRixTQUFLLGNBQUw7QUFDRXFHLGNBQVFFLFFBQVIsQ0FBaUJySCxPQUFqQixDQUEwQnNILE9BQUQsSUFBYTtBQUNwQyxZQUFJQSxXQUFXLElBQWYsRUFBcUI7QUFDckJ2SSxnQ0FBd0J1SSxPQUF4QixFQUFpQ3hHLFFBQWpDO0FBQ0QsT0FIRDtBQUlBO0FBaEJKO0FBa0JEOztBQUVEOzs7QUFHQSxTQUFTcUQsWUFBVCxDQUFzQjdFLElBQXRCLEVBQTRCZ0MsT0FBNUIsRUFBcUM7QUFBQSxRQUMzQjZELFFBRDJCLEdBQ2E3RCxPQURiLENBQzNCNkQsUUFEMkI7QUFBQSxRQUNqQm9DLGFBRGlCLEdBQ2FqRyxPQURiLENBQ2pCaUcsYUFEaUI7QUFBQSxRQUNGQyxVQURFLEdBQ2FsRyxPQURiLENBQ0ZrRyxVQURFOztBQUVuQyxTQUFPO0FBQ0xyQyxZQURLO0FBRUxvQyxpQkFGSztBQUdMQyxjQUhLO0FBSUxsSTtBQUpLLEdBQVA7QUFNRCIsImZpbGUiOiJFeHBvcnRNYXAuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgZnMgZnJvbSAnZnMnXG5cbmltcG9ydCBkb2N0cmluZSBmcm9tICdkb2N0cmluZSdcblxuaW1wb3J0IGRlYnVnIGZyb20gJ2RlYnVnJ1xuXG5pbXBvcnQgcGFyc2UgZnJvbSAnZXNsaW50LW1vZHVsZS11dGlscy9wYXJzZSdcbmltcG9ydCByZXNvbHZlIGZyb20gJ2VzbGludC1tb2R1bGUtdXRpbHMvcmVzb2x2ZSdcbmltcG9ydCBpc0lnbm9yZWQsIHsgaGFzVmFsaWRFeHRlbnNpb24gfSBmcm9tICdlc2xpbnQtbW9kdWxlLXV0aWxzL2lnbm9yZSdcblxuaW1wb3J0IHsgaGFzaE9iamVjdCB9IGZyb20gJ2VzbGludC1tb2R1bGUtdXRpbHMvaGFzaCdcbmltcG9ydCAqIGFzIHVuYW1iaWd1b3VzIGZyb20gJ2VzbGludC1tb2R1bGUtdXRpbHMvdW5hbWJpZ3VvdXMnXG5cbmNvbnN0IGxvZyA9IGRlYnVnKCdlc2xpbnQtcGx1Z2luLWltcG9ydDpFeHBvcnRNYXAnKVxuXG5jb25zdCBleHBvcnRDYWNoZSA9IG5ldyBNYXAoKVxuXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBFeHBvcnRNYXAge1xuICBjb25zdHJ1Y3RvcihwYXRoKSB7XG4gICAgdGhpcy5wYXRoID0gcGF0aFxuICAgIHRoaXMubmFtZXNwYWNlID0gbmV3IE1hcCgpXG4gICAgLy8gdG9kbzogcmVzdHJ1Y3R1cmUgdG8ga2V5IG9uIHBhdGgsIHZhbHVlIGlzIHJlc29sdmVyICsgbWFwIG9mIG5hbWVzXG4gICAgdGhpcy5yZWV4cG9ydHMgPSBuZXcgTWFwKClcbiAgICAvKipcbiAgICAgKiBzdGFyLWV4cG9ydHNcbiAgICAgKiBAdHlwZSB7U2V0fSBvZiAoKSA9PiBFeHBvcnRNYXBcbiAgICAgKi9cbiAgICB0aGlzLmRlcGVuZGVuY2llcyA9IG5ldyBTZXQoKVxuICAgIC8qKlxuICAgICAqIGRlcGVuZGVuY2llcyBvZiB0aGlzIG1vZHVsZSB0aGF0IGFyZSBub3QgZXhwbGljaXRseSByZS1leHBvcnRlZFxuICAgICAqIEB0eXBlIHtNYXB9IGZyb20gcGF0aCA9ICgpID0+IEV4cG9ydE1hcFxuICAgICAqL1xuICAgIHRoaXMuaW1wb3J0cyA9IG5ldyBNYXAoKVxuICAgIHRoaXMuZXJyb3JzID0gW11cbiAgfVxuXG4gIGdldCBoYXNEZWZhdWx0KCkgeyByZXR1cm4gdGhpcy5nZXQoJ2RlZmF1bHQnKSAhPSBudWxsIH0gLy8gc3Ryb25nZXIgdGhhbiB0aGlzLmhhc1xuXG4gIGdldCBzaXplKCkge1xuICAgIGxldCBzaXplID0gdGhpcy5uYW1lc3BhY2Uuc2l6ZSArIHRoaXMucmVleHBvcnRzLnNpemVcbiAgICB0aGlzLmRlcGVuZGVuY2llcy5mb3JFYWNoKGRlcCA9PiBzaXplICs9IGRlcCgpLnNpemUpXG4gICAgcmV0dXJuIHNpemVcbiAgfVxuXG4gIC8qKlxuICAgKiBOb3RlIHRoYXQgdGhpcyBkb2VzIG5vdCBjaGVjayBleHBsaWNpdGx5IHJlLWV4cG9ydGVkIG5hbWVzIGZvciBleGlzdGVuY2VcbiAgICogaW4gdGhlIGJhc2UgbmFtZXNwYWNlLCBidXQgaXQgd2lsbCBleHBhbmQgYWxsIGBleHBvcnQgKiBmcm9tICcuLi4nYCBleHBvcnRzXG4gICAqIGlmIG5vdCBmb3VuZCBpbiB0aGUgZXhwbGljaXQgbmFtZXNwYWNlLlxuICAgKiBAcGFyYW0gIHtzdHJpbmd9ICBuYW1lXG4gICAqIEByZXR1cm4ge0Jvb2xlYW59IHRydWUgaWYgYG5hbWVgIGlzIGV4cG9ydGVkIGJ5IHRoaXMgbW9kdWxlLlxuICAgKi9cbiAgaGFzKG5hbWUpIHtcbiAgICBpZiAodGhpcy5uYW1lc3BhY2UuaGFzKG5hbWUpKSByZXR1cm4gdHJ1ZVxuICAgIGlmICh0aGlzLnJlZXhwb3J0cy5oYXMobmFtZSkpIHJldHVybiB0cnVlXG5cbiAgICAvLyBkZWZhdWx0IGV4cG9ydHMgbXVzdCBiZSBleHBsaWNpdGx5IHJlLWV4cG9ydGVkICgjMzI4KVxuICAgIGlmIChuYW1lICE9PSAnZGVmYXVsdCcpIHtcbiAgICAgIGZvciAobGV0IGRlcCBvZiB0aGlzLmRlcGVuZGVuY2llcykge1xuICAgICAgICBsZXQgaW5uZXJNYXAgPSBkZXAoKVxuXG4gICAgICAgIC8vIHRvZG86IHJlcG9ydCBhcyB1bnJlc29sdmVkP1xuICAgICAgICBpZiAoIWlubmVyTWFwKSBjb250aW51ZVxuXG4gICAgICAgIGlmIChpbm5lck1hcC5oYXMobmFtZSkpIHJldHVybiB0cnVlXG4gICAgICB9XG4gICAgfVxuXG4gICAgcmV0dXJuIGZhbHNlXG4gIH1cblxuICAvKipcbiAgICogZW5zdXJlIHRoYXQgaW1wb3J0ZWQgbmFtZSBmdWxseSByZXNvbHZlcy5cbiAgICogQHBhcmFtICB7W3R5cGVdfSAgbmFtZSBbZGVzY3JpcHRpb25dXG4gICAqIEByZXR1cm4ge0Jvb2xlYW59ICAgICAgW2Rlc2NyaXB0aW9uXVxuICAgKi9cbiAgaGFzRGVlcChuYW1lKSB7XG4gICAgaWYgKHRoaXMubmFtZXNwYWNlLmhhcyhuYW1lKSkgcmV0dXJuIHsgZm91bmQ6IHRydWUsIHBhdGg6IFt0aGlzXSB9XG5cbiAgICBpZiAodGhpcy5yZWV4cG9ydHMuaGFzKG5hbWUpKSB7XG4gICAgICBjb25zdCByZWV4cG9ydHMgPSB0aGlzLnJlZXhwb3J0cy5nZXQobmFtZSlcbiAgICAgICAgICAsIGltcG9ydGVkID0gcmVleHBvcnRzLmdldEltcG9ydCgpXG5cbiAgICAgIC8vIGlmIGltcG9ydCBpcyBpZ25vcmVkLCByZXR1cm4gZXhwbGljaXQgJ251bGwnXG4gICAgICBpZiAoaW1wb3J0ZWQgPT0gbnVsbCkgcmV0dXJuIHsgZm91bmQ6IHRydWUsIHBhdGg6IFt0aGlzXSB9XG5cbiAgICAgIC8vIHNhZmVndWFyZCBhZ2FpbnN0IGN5Y2xlcywgb25seSBpZiBuYW1lIG1hdGNoZXNcbiAgICAgIGlmIChpbXBvcnRlZC5wYXRoID09PSB0aGlzLnBhdGggJiYgcmVleHBvcnRzLmxvY2FsID09PSBuYW1lKSB7XG4gICAgICAgIHJldHVybiB7IGZvdW5kOiBmYWxzZSwgcGF0aDogW3RoaXNdIH1cbiAgICAgIH1cblxuICAgICAgY29uc3QgZGVlcCA9IGltcG9ydGVkLmhhc0RlZXAocmVleHBvcnRzLmxvY2FsKVxuICAgICAgZGVlcC5wYXRoLnVuc2hpZnQodGhpcylcblxuICAgICAgcmV0dXJuIGRlZXBcbiAgICB9XG5cblxuICAgIC8vIGRlZmF1bHQgZXhwb3J0cyBtdXN0IGJlIGV4cGxpY2l0bHkgcmUtZXhwb3J0ZWQgKCMzMjgpXG4gICAgaWYgKG5hbWUgIT09ICdkZWZhdWx0Jykge1xuICAgICAgZm9yIChsZXQgZGVwIG9mIHRoaXMuZGVwZW5kZW5jaWVzKSB7XG4gICAgICAgIGxldCBpbm5lck1hcCA9IGRlcCgpXG4gICAgICAgIC8vIHRvZG86IHJlcG9ydCBhcyB1bnJlc29sdmVkP1xuICAgICAgICBpZiAoIWlubmVyTWFwKSBjb250aW51ZVxuXG4gICAgICAgIC8vIHNhZmVndWFyZCBhZ2FpbnN0IGN5Y2xlc1xuICAgICAgICBpZiAoaW5uZXJNYXAucGF0aCA9PT0gdGhpcy5wYXRoKSBjb250aW51ZVxuXG4gICAgICAgIGxldCBpbm5lclZhbHVlID0gaW5uZXJNYXAuaGFzRGVlcChuYW1lKVxuICAgICAgICBpZiAoaW5uZXJWYWx1ZS5mb3VuZCkge1xuICAgICAgICAgIGlubmVyVmFsdWUucGF0aC51bnNoaWZ0KHRoaXMpXG4gICAgICAgICAgcmV0dXJuIGlubmVyVmFsdWVcbiAgICAgICAgfVxuICAgICAgfVxuICAgIH1cblxuICAgIHJldHVybiB7IGZvdW5kOiBmYWxzZSwgcGF0aDogW3RoaXNdIH1cbiAgfVxuXG4gIGdldChuYW1lKSB7XG4gICAgaWYgKHRoaXMubmFtZXNwYWNlLmhhcyhuYW1lKSkgcmV0dXJuIHRoaXMubmFtZXNwYWNlLmdldChuYW1lKVxuXG4gICAgaWYgKHRoaXMucmVleHBvcnRzLmhhcyhuYW1lKSkge1xuICAgICAgY29uc3QgcmVleHBvcnRzID0gdGhpcy5yZWV4cG9ydHMuZ2V0KG5hbWUpXG4gICAgICAgICAgLCBpbXBvcnRlZCA9IHJlZXhwb3J0cy5nZXRJbXBvcnQoKVxuXG4gICAgICAvLyBpZiBpbXBvcnQgaXMgaWdub3JlZCwgcmV0dXJuIGV4cGxpY2l0ICdudWxsJ1xuICAgICAgaWYgKGltcG9ydGVkID09IG51bGwpIHJldHVybiBudWxsXG5cbiAgICAgIC8vIHNhZmVndWFyZCBhZ2FpbnN0IGN5Y2xlcywgb25seSBpZiBuYW1lIG1hdGNoZXNcbiAgICAgIGlmIChpbXBvcnRlZC5wYXRoID09PSB0aGlzLnBhdGggJiYgcmVleHBvcnRzLmxvY2FsID09PSBuYW1lKSByZXR1cm4gdW5kZWZpbmVkXG5cbiAgICAgIHJldHVybiBpbXBvcnRlZC5nZXQocmVleHBvcnRzLmxvY2FsKVxuICAgIH1cblxuICAgIC8vIGRlZmF1bHQgZXhwb3J0cyBtdXN0IGJlIGV4cGxpY2l0bHkgcmUtZXhwb3J0ZWQgKCMzMjgpXG4gICAgaWYgKG5hbWUgIT09ICdkZWZhdWx0Jykge1xuICAgICAgZm9yIChsZXQgZGVwIG9mIHRoaXMuZGVwZW5kZW5jaWVzKSB7XG4gICAgICAgIGxldCBpbm5lck1hcCA9IGRlcCgpXG4gICAgICAgIC8vIHRvZG86IHJlcG9ydCBhcyB1bnJlc29sdmVkP1xuICAgICAgICBpZiAoIWlubmVyTWFwKSBjb250aW51ZVxuXG4gICAgICAgIC8vIHNhZmVndWFyZCBhZ2FpbnN0IGN5Y2xlc1xuICAgICAgICBpZiAoaW5uZXJNYXAucGF0aCA9PT0gdGhpcy5wYXRoKSBjb250aW51ZVxuXG4gICAgICAgIGxldCBpbm5lclZhbHVlID0gaW5uZXJNYXAuZ2V0KG5hbWUpXG4gICAgICAgIGlmIChpbm5lclZhbHVlICE9PSB1bmRlZmluZWQpIHJldHVybiBpbm5lclZhbHVlXG4gICAgICB9XG4gICAgfVxuXG4gICAgcmV0dXJuIHVuZGVmaW5lZFxuICB9XG5cbiAgZm9yRWFjaChjYWxsYmFjaywgdGhpc0FyZykge1xuICAgIHRoaXMubmFtZXNwYWNlLmZvckVhY2goKHYsIG4pID0+XG4gICAgICBjYWxsYmFjay5jYWxsKHRoaXNBcmcsIHYsIG4sIHRoaXMpKVxuXG4gICAgdGhpcy5yZWV4cG9ydHMuZm9yRWFjaCgocmVleHBvcnRzLCBuYW1lKSA9PiB7XG4gICAgICBjb25zdCByZWV4cG9ydGVkID0gcmVleHBvcnRzLmdldEltcG9ydCgpXG4gICAgICAvLyBjYW4ndCBsb29rIHVwIG1ldGEgZm9yIGlnbm9yZWQgcmUtZXhwb3J0cyAoIzM0OClcbiAgICAgIGNhbGxiYWNrLmNhbGwodGhpc0FyZywgcmVleHBvcnRlZCAmJiByZWV4cG9ydGVkLmdldChyZWV4cG9ydHMubG9jYWwpLCBuYW1lLCB0aGlzKVxuICAgIH0pXG5cbiAgICB0aGlzLmRlcGVuZGVuY2llcy5mb3JFYWNoKGRlcCA9PiB7XG4gICAgICBjb25zdCBkID0gZGVwKClcbiAgICAgIC8vIENKUyAvIGlnbm9yZWQgZGVwZW5kZW5jaWVzIHdvbid0IGV4aXN0ICgjNzE3KVxuICAgICAgaWYgKGQgPT0gbnVsbCkgcmV0dXJuXG5cbiAgICAgIGQuZm9yRWFjaCgodiwgbikgPT5cbiAgICAgICAgbiAhPT0gJ2RlZmF1bHQnICYmIGNhbGxiYWNrLmNhbGwodGhpc0FyZywgdiwgbiwgdGhpcykpXG4gICAgfSlcbiAgfVxuXG4gIC8vIHRvZG86IGtleXMsIHZhbHVlcywgZW50cmllcz9cblxuICByZXBvcnRFcnJvcnMoY29udGV4dCwgZGVjbGFyYXRpb24pIHtcbiAgICBjb250ZXh0LnJlcG9ydCh7XG4gICAgICBub2RlOiBkZWNsYXJhdGlvbi5zb3VyY2UsXG4gICAgICBtZXNzYWdlOiBgUGFyc2UgZXJyb3JzIGluIGltcG9ydGVkIG1vZHVsZSAnJHtkZWNsYXJhdGlvbi5zb3VyY2UudmFsdWV9JzogYCArXG4gICAgICAgICAgICAgICAgICBgJHt0aGlzLmVycm9yc1xuICAgICAgICAgICAgICAgICAgICAgICAgLm1hcChlID0+IGAke2UubWVzc2FnZX0gKCR7ZS5saW5lTnVtYmVyfToke2UuY29sdW1ufSlgKVxuICAgICAgICAgICAgICAgICAgICAgICAgLmpvaW4oJywgJyl9YCxcbiAgICB9KVxuICB9XG59XG5cbi8qKlxuICogcGFyc2UgZG9jcyBmcm9tIHRoZSBmaXJzdCBub2RlIHRoYXQgaGFzIGxlYWRpbmcgY29tbWVudHNcbiAqIEBwYXJhbSAgey4uLlt0eXBlXX0gbm9kZXMgW2Rlc2NyaXB0aW9uXVxuICogQHJldHVybiB7e2RvYzogb2JqZWN0fX1cbiAqL1xuZnVuY3Rpb24gY2FwdHVyZURvYyhkb2NTdHlsZVBhcnNlcnMpIHtcbiAgY29uc3QgbWV0YWRhdGEgPSB7fVxuICAgICAgICwgbm9kZXMgPSBBcnJheS5wcm90b3R5cGUuc2xpY2UuY2FsbChhcmd1bWVudHMsIDEpXG5cbiAgLy8gJ3NvbWUnIHNob3J0LWNpcmN1aXRzIG9uIGZpcnN0ICd0cnVlJ1xuICBub2Rlcy5zb21lKG4gPT4ge1xuICAgIGlmICghbi5sZWFkaW5nQ29tbWVudHMpIHJldHVybiBmYWxzZVxuXG4gICAgZm9yIChsZXQgbmFtZSBpbiBkb2NTdHlsZVBhcnNlcnMpIHtcbiAgICAgIGNvbnN0IGRvYyA9IGRvY1N0eWxlUGFyc2Vyc1tuYW1lXShuLmxlYWRpbmdDb21tZW50cylcbiAgICAgIGlmIChkb2MpIHtcbiAgICAgICAgbWV0YWRhdGEuZG9jID0gZG9jXG4gICAgICB9XG4gICAgfVxuXG4gICAgcmV0dXJuIHRydWVcbiAgfSlcblxuICByZXR1cm4gbWV0YWRhdGFcbn1cblxuY29uc3QgYXZhaWxhYmxlRG9jU3R5bGVQYXJzZXJzID0ge1xuICBqc2RvYzogY2FwdHVyZUpzRG9jLFxuICB0b21kb2M6IGNhcHR1cmVUb21Eb2MsXG59XG5cbi8qKlxuICogcGFyc2UgSlNEb2MgZnJvbSBsZWFkaW5nIGNvbW1lbnRzXG4gKiBAcGFyYW0gIHsuLi5bdHlwZV19IGNvbW1lbnRzIFtkZXNjcmlwdGlvbl1cbiAqIEByZXR1cm4ge3tkb2M6IG9iamVjdH19XG4gKi9cbmZ1bmN0aW9uIGNhcHR1cmVKc0RvYyhjb21tZW50cykge1xuICBsZXQgZG9jXG5cbiAgLy8gY2FwdHVyZSBYU0RvY1xuICBjb21tZW50cy5mb3JFYWNoKGNvbW1lbnQgPT4ge1xuICAgIC8vIHNraXAgbm9uLWJsb2NrIGNvbW1lbnRzXG4gICAgaWYgKGNvbW1lbnQudmFsdWUuc2xpY2UoMCwgNCkgIT09ICcqXFxuIConKSByZXR1cm5cbiAgICB0cnkge1xuICAgICAgZG9jID0gZG9jdHJpbmUucGFyc2UoY29tbWVudC52YWx1ZSwgeyB1bndyYXA6IHRydWUgfSlcbiAgICB9IGNhdGNoIChlcnIpIHtcbiAgICAgIC8qIGRvbid0IGNhcmUsIGZvciBub3c/IG1heWJlIGFkZCB0byBgZXJyb3JzP2AgKi9cbiAgICB9XG4gIH0pXG5cbiAgcmV0dXJuIGRvY1xufVxuXG4vKipcbiAgKiBwYXJzZSBUb21Eb2Mgc2VjdGlvbiBmcm9tIGNvbW1lbnRzXG4gICovXG5mdW5jdGlvbiBjYXB0dXJlVG9tRG9jKGNvbW1lbnRzKSB7XG4gIC8vIGNvbGxlY3QgbGluZXMgdXAgdG8gZmlyc3QgcGFyYWdyYXBoIGJyZWFrXG4gIGNvbnN0IGxpbmVzID0gW11cbiAgZm9yIChsZXQgaSA9IDA7IGkgPCBjb21tZW50cy5sZW5ndGg7IGkrKykge1xuICAgIGNvbnN0IGNvbW1lbnQgPSBjb21tZW50c1tpXVxuICAgIGlmIChjb21tZW50LnZhbHVlLm1hdGNoKC9eXFxzKiQvKSkgYnJlYWtcbiAgICBsaW5lcy5wdXNoKGNvbW1lbnQudmFsdWUudHJpbSgpKVxuICB9XG5cbiAgLy8gcmV0dXJuIGRvY3RyaW5lLWxpa2Ugb2JqZWN0XG4gIGNvbnN0IHN0YXR1c01hdGNoID0gbGluZXMuam9pbignICcpLm1hdGNoKC9eKFB1YmxpY3xJbnRlcm5hbHxEZXByZWNhdGVkKTpcXHMqKC4rKS8pXG4gIGlmIChzdGF0dXNNYXRjaCkge1xuICAgIHJldHVybiB7XG4gICAgICBkZXNjcmlwdGlvbjogc3RhdHVzTWF0Y2hbMl0sXG4gICAgICB0YWdzOiBbe1xuICAgICAgICB0aXRsZTogc3RhdHVzTWF0Y2hbMV0udG9Mb3dlckNhc2UoKSxcbiAgICAgICAgZGVzY3JpcHRpb246IHN0YXR1c01hdGNoWzJdLFxuICAgICAgfV0sXG4gICAgfVxuICB9XG59XG5cbkV4cG9ydE1hcC5nZXQgPSBmdW5jdGlvbiAoc291cmNlLCBjb250ZXh0KSB7XG4gIGNvbnN0IHBhdGggPSByZXNvbHZlKHNvdXJjZSwgY29udGV4dClcbiAgaWYgKHBhdGggPT0gbnVsbCkgcmV0dXJuIG51bGxcblxuICByZXR1cm4gRXhwb3J0TWFwLmZvcihjaGlsZENvbnRleHQocGF0aCwgY29udGV4dCkpXG59XG5cbkV4cG9ydE1hcC5mb3IgPSBmdW5jdGlvbiAoY29udGV4dCkge1xuICBjb25zdCB7IHBhdGggfSA9IGNvbnRleHRcblxuICBjb25zdCBjYWNoZUtleSA9IGhhc2hPYmplY3QoY29udGV4dCkuZGlnZXN0KCdoZXgnKVxuICBsZXQgZXhwb3J0TWFwID0gZXhwb3J0Q2FjaGUuZ2V0KGNhY2hlS2V5KVxuXG4gIC8vIHJldHVybiBjYWNoZWQgaWdub3JlXG4gIGlmIChleHBvcnRNYXAgPT09IG51bGwpIHJldHVybiBudWxsXG5cbiAgY29uc3Qgc3RhdHMgPSBmcy5zdGF0U3luYyhwYXRoKVxuICBpZiAoZXhwb3J0TWFwICE9IG51bGwpIHtcbiAgICAvLyBkYXRlIGVxdWFsaXR5IGNoZWNrXG4gICAgaWYgKGV4cG9ydE1hcC5tdGltZSAtIHN0YXRzLm10aW1lID09PSAwKSB7XG4gICAgICByZXR1cm4gZXhwb3J0TWFwXG4gICAgfVxuICAgIC8vIGZ1dHVyZTogY2hlY2sgY29udGVudCBlcXVhbGl0eT9cbiAgfVxuXG4gIC8vIGNoZWNrIHZhbGlkIGV4dGVuc2lvbnMgZmlyc3RcbiAgaWYgKCFoYXNWYWxpZEV4dGVuc2lvbihwYXRoLCBjb250ZXh0KSkge1xuICAgIGV4cG9ydENhY2hlLnNldChjYWNoZUtleSwgbnVsbClcbiAgICByZXR1cm4gbnVsbFxuICB9XG5cbiAgY29uc3QgY29udGVudCA9IGZzLnJlYWRGaWxlU3luYyhwYXRoLCB7IGVuY29kaW5nOiAndXRmOCcgfSlcblxuICAvLyBjaGVjayBmb3IgYW5kIGNhY2hlIGlnbm9yZVxuICBpZiAoaXNJZ25vcmVkKHBhdGgsIGNvbnRleHQpIHx8ICF1bmFtYmlndW91cy50ZXN0KGNvbnRlbnQpKSB7XG4gICAgbG9nKCdpZ25vcmVkIHBhdGggZHVlIHRvIHVuYW1iaWd1b3VzIHJlZ2V4IG9yIGlnbm9yZSBzZXR0aW5nczonLCBwYXRoKVxuICAgIGV4cG9ydENhY2hlLnNldChjYWNoZUtleSwgbnVsbClcbiAgICByZXR1cm4gbnVsbFxuICB9XG5cbiAgbG9nKCdjYWNoZSBtaXNzJywgY2FjaGVLZXksICdmb3IgcGF0aCcsIHBhdGgpXG4gIGV4cG9ydE1hcCA9IEV4cG9ydE1hcC5wYXJzZShwYXRoLCBjb250ZW50LCBjb250ZXh0KVxuXG4gIC8vIGFtYmlndW91cyBtb2R1bGVzIHJldHVybiBudWxsXG4gIGlmIChleHBvcnRNYXAgPT0gbnVsbCkgcmV0dXJuIG51bGxcblxuICBleHBvcnRNYXAubXRpbWUgPSBzdGF0cy5tdGltZVxuXG4gIGV4cG9ydENhY2hlLnNldChjYWNoZUtleSwgZXhwb3J0TWFwKVxuICByZXR1cm4gZXhwb3J0TWFwXG59XG5cblxuRXhwb3J0TWFwLnBhcnNlID0gZnVuY3Rpb24gKHBhdGgsIGNvbnRlbnQsIGNvbnRleHQpIHtcbiAgdmFyIG0gPSBuZXcgRXhwb3J0TWFwKHBhdGgpXG5cbiAgdHJ5IHtcbiAgICB2YXIgYXN0ID0gcGFyc2UocGF0aCwgY29udGVudCwgY29udGV4dClcbiAgfSBjYXRjaCAoZXJyKSB7XG4gICAgbG9nKCdwYXJzZSBlcnJvcjonLCBwYXRoLCBlcnIpXG4gICAgbS5lcnJvcnMucHVzaChlcnIpXG4gICAgcmV0dXJuIG0gLy8gY2FuJ3QgY29udGludWVcbiAgfVxuXG4gIGlmICghdW5hbWJpZ3VvdXMuaXNNb2R1bGUoYXN0KSkgcmV0dXJuIG51bGxcblxuICBjb25zdCBkb2NzdHlsZSA9IChjb250ZXh0LnNldHRpbmdzICYmIGNvbnRleHQuc2V0dGluZ3NbJ2ltcG9ydC9kb2NzdHlsZSddKSB8fCBbJ2pzZG9jJ11cbiAgY29uc3QgZG9jU3R5bGVQYXJzZXJzID0ge31cbiAgZG9jc3R5bGUuZm9yRWFjaChzdHlsZSA9PiB7XG4gICAgZG9jU3R5bGVQYXJzZXJzW3N0eWxlXSA9IGF2YWlsYWJsZURvY1N0eWxlUGFyc2Vyc1tzdHlsZV1cbiAgfSlcblxuICAvLyBhdHRlbXB0IHRvIGNvbGxlY3QgbW9kdWxlIGRvY1xuICBpZiAoYXN0LmNvbW1lbnRzKSB7XG4gICAgYXN0LmNvbW1lbnRzLnNvbWUoYyA9PiB7XG4gICAgICBpZiAoYy50eXBlICE9PSAnQmxvY2snKSByZXR1cm4gZmFsc2VcbiAgICAgIHRyeSB7XG4gICAgICAgIGNvbnN0IGRvYyA9IGRvY3RyaW5lLnBhcnNlKGMudmFsdWUsIHsgdW53cmFwOiB0cnVlIH0pXG4gICAgICAgIGlmIChkb2MudGFncy5zb21lKHQgPT4gdC50aXRsZSA9PT0gJ21vZHVsZScpKSB7XG4gICAgICAgICAgbS5kb2MgPSBkb2NcbiAgICAgICAgICByZXR1cm4gdHJ1ZVxuICAgICAgICB9XG4gICAgICB9IGNhdGNoIChlcnIpIHsgLyogaWdub3JlICovIH1cbiAgICAgIHJldHVybiBmYWxzZVxuICAgIH0pXG4gIH1cblxuICBjb25zdCBuYW1lc3BhY2VzID0gbmV3IE1hcCgpXG5cbiAgZnVuY3Rpb24gcmVtb3RlUGF0aCh2YWx1ZSkge1xuICAgIHJldHVybiByZXNvbHZlLnJlbGF0aXZlKHZhbHVlLCBwYXRoLCBjb250ZXh0LnNldHRpbmdzKVxuICB9XG5cbiAgZnVuY3Rpb24gcmVzb2x2ZUltcG9ydCh2YWx1ZSkge1xuICAgIGNvbnN0IHJwID0gcmVtb3RlUGF0aCh2YWx1ZSlcbiAgICBpZiAocnAgPT0gbnVsbCkgcmV0dXJuIG51bGxcbiAgICByZXR1cm4gRXhwb3J0TWFwLmZvcihjaGlsZENvbnRleHQocnAsIGNvbnRleHQpKVxuICB9XG5cbiAgZnVuY3Rpb24gZ2V0TmFtZXNwYWNlKGlkZW50aWZpZXIpIHtcbiAgICBpZiAoIW5hbWVzcGFjZXMuaGFzKGlkZW50aWZpZXIubmFtZSkpIHJldHVyblxuXG4gICAgcmV0dXJuIGZ1bmN0aW9uICgpIHtcbiAgICAgIHJldHVybiByZXNvbHZlSW1wb3J0KG5hbWVzcGFjZXMuZ2V0KGlkZW50aWZpZXIubmFtZSkpXG4gICAgfVxuICB9XG5cbiAgZnVuY3Rpb24gYWRkTmFtZXNwYWNlKG9iamVjdCwgaWRlbnRpZmllcikge1xuICAgIGNvbnN0IG5zZm4gPSBnZXROYW1lc3BhY2UoaWRlbnRpZmllcilcbiAgICBpZiAobnNmbikge1xuICAgICAgT2JqZWN0LmRlZmluZVByb3BlcnR5KG9iamVjdCwgJ25hbWVzcGFjZScsIHsgZ2V0OiBuc2ZuIH0pXG4gICAgfVxuXG4gICAgcmV0dXJuIG9iamVjdFxuICB9XG5cbiAgZnVuY3Rpb24gY2FwdHVyZURlcGVuZGVuY3koZGVjbGFyYXRpb24pIHtcbiAgICBpZiAoZGVjbGFyYXRpb24uc291cmNlID09IG51bGwpIHJldHVybiBudWxsXG5cbiAgICBjb25zdCBwID0gcmVtb3RlUGF0aChkZWNsYXJhdGlvbi5zb3VyY2UudmFsdWUpXG4gICAgaWYgKHAgPT0gbnVsbCkgcmV0dXJuIG51bGxcbiAgICBjb25zdCBleGlzdGluZyA9IG0uaW1wb3J0cy5nZXQocClcbiAgICBpZiAoZXhpc3RpbmcgIT0gbnVsbCkgcmV0dXJuIGV4aXN0aW5nLmdldHRlclxuXG4gICAgY29uc3QgZ2V0dGVyID0gKCkgPT4gRXhwb3J0TWFwLmZvcihjaGlsZENvbnRleHQocCwgY29udGV4dCkpXG4gICAgbS5pbXBvcnRzLnNldChwLCB7XG4gICAgICBnZXR0ZXIsXG4gICAgICBzb3VyY2U6IHsgIC8vIGNhcHR1cmluZyBhY3R1YWwgbm9kZSByZWZlcmVuY2UgaG9sZHMgZnVsbCBBU1QgaW4gbWVtb3J5IVxuICAgICAgICB2YWx1ZTogZGVjbGFyYXRpb24uc291cmNlLnZhbHVlLFxuICAgICAgICBsb2M6IGRlY2xhcmF0aW9uLnNvdXJjZS5sb2MsXG4gICAgICB9LFxuICAgIH0pXG4gICAgcmV0dXJuIGdldHRlclxuICB9XG5cblxuICBhc3QuYm9keS5mb3JFYWNoKGZ1bmN0aW9uIChuKSB7XG5cbiAgICBpZiAobi50eXBlID09PSAnRXhwb3J0RGVmYXVsdERlY2xhcmF0aW9uJykge1xuICAgICAgY29uc3QgZXhwb3J0TWV0YSA9IGNhcHR1cmVEb2MoZG9jU3R5bGVQYXJzZXJzLCBuKVxuICAgICAgaWYgKG4uZGVjbGFyYXRpb24udHlwZSA9PT0gJ0lkZW50aWZpZXInKSB7XG4gICAgICAgIGFkZE5hbWVzcGFjZShleHBvcnRNZXRhLCBuLmRlY2xhcmF0aW9uKVxuICAgICAgfVxuICAgICAgbS5uYW1lc3BhY2Uuc2V0KCdkZWZhdWx0JywgZXhwb3J0TWV0YSlcbiAgICAgIHJldHVyblxuICAgIH1cblxuICAgIGlmIChuLnR5cGUgPT09ICdFeHBvcnRBbGxEZWNsYXJhdGlvbicpIHtcbiAgICAgIGNvbnN0IGdldHRlciA9IGNhcHR1cmVEZXBlbmRlbmN5KG4pXG4gICAgICBpZiAoZ2V0dGVyKSBtLmRlcGVuZGVuY2llcy5hZGQoZ2V0dGVyKVxuICAgICAgcmV0dXJuXG4gICAgfVxuXG4gICAgLy8gY2FwdHVyZSBuYW1lc3BhY2VzIGluIGNhc2Ugb2YgbGF0ZXIgZXhwb3J0XG4gICAgaWYgKG4udHlwZSA9PT0gJ0ltcG9ydERlY2xhcmF0aW9uJykge1xuICAgICAgY2FwdHVyZURlcGVuZGVuY3kobilcbiAgICAgIGxldCBuc1xuICAgICAgaWYgKG4uc3BlY2lmaWVycy5zb21lKHMgPT4gcy50eXBlID09PSAnSW1wb3J0TmFtZXNwYWNlU3BlY2lmaWVyJyAmJiAobnMgPSBzKSkpIHtcbiAgICAgICAgbmFtZXNwYWNlcy5zZXQobnMubG9jYWwubmFtZSwgbi5zb3VyY2UudmFsdWUpXG4gICAgICB9XG4gICAgICByZXR1cm5cbiAgICB9XG5cbiAgICBpZiAobi50eXBlID09PSAnRXhwb3J0TmFtZWREZWNsYXJhdGlvbicpIHtcbiAgICAgIC8vIGNhcHR1cmUgZGVjbGFyYXRpb25cbiAgICAgIGlmIChuLmRlY2xhcmF0aW9uICE9IG51bGwpIHtcbiAgICAgICAgc3dpdGNoIChuLmRlY2xhcmF0aW9uLnR5cGUpIHtcbiAgICAgICAgICBjYXNlICdGdW5jdGlvbkRlY2xhcmF0aW9uJzpcbiAgICAgICAgICBjYXNlICdDbGFzc0RlY2xhcmF0aW9uJzpcbiAgICAgICAgICBjYXNlICdUeXBlQWxpYXMnOiAvLyBmbG93dHlwZSB3aXRoIGJhYmVsLWVzbGludCBwYXJzZXJcbiAgICAgICAgICBjYXNlICdJbnRlcmZhY2VEZWNsYXJhdGlvbic6XG4gICAgICAgICAgY2FzZSAnVFNFbnVtRGVjbGFyYXRpb24nOlxuICAgICAgICAgIGNhc2UgJ1RTSW50ZXJmYWNlRGVjbGFyYXRpb24nOlxuICAgICAgICAgIGNhc2UgJ1RTQWJzdHJhY3RDbGFzc0RlY2xhcmF0aW9uJzpcbiAgICAgICAgICBjYXNlICdUU01vZHVsZURlY2xhcmF0aW9uJzpcbiAgICAgICAgICAgIG0ubmFtZXNwYWNlLnNldChuLmRlY2xhcmF0aW9uLmlkLm5hbWUsIGNhcHR1cmVEb2MoZG9jU3R5bGVQYXJzZXJzLCBuKSlcbiAgICAgICAgICAgIGJyZWFrXG4gICAgICAgICAgY2FzZSAnVmFyaWFibGVEZWNsYXJhdGlvbic6XG4gICAgICAgICAgICBuLmRlY2xhcmF0aW9uLmRlY2xhcmF0aW9ucy5mb3JFYWNoKChkKSA9PlxuICAgICAgICAgICAgICByZWN1cnNpdmVQYXR0ZXJuQ2FwdHVyZShkLmlkLFxuICAgICAgICAgICAgICAgIGlkID0+IG0ubmFtZXNwYWNlLnNldChpZC5uYW1lLCBjYXB0dXJlRG9jKGRvY1N0eWxlUGFyc2VycywgZCwgbikpKSlcbiAgICAgICAgICAgIGJyZWFrXG4gICAgICAgIH1cbiAgICAgIH1cblxuICAgICAgY29uc3QgbnNvdXJjZSA9IG4uc291cmNlICYmIG4uc291cmNlLnZhbHVlXG4gICAgICBuLnNwZWNpZmllcnMuZm9yRWFjaCgocykgPT4ge1xuICAgICAgICBjb25zdCBleHBvcnRNZXRhID0ge31cbiAgICAgICAgbGV0IGxvY2FsXG5cbiAgICAgICAgc3dpdGNoIChzLnR5cGUpIHtcbiAgICAgICAgICBjYXNlICdFeHBvcnREZWZhdWx0U3BlY2lmaWVyJzpcbiAgICAgICAgICAgIGlmICghbi5zb3VyY2UpIHJldHVyblxuICAgICAgICAgICAgbG9jYWwgPSAnZGVmYXVsdCdcbiAgICAgICAgICAgIGJyZWFrXG4gICAgICAgICAgY2FzZSAnRXhwb3J0TmFtZXNwYWNlU3BlY2lmaWVyJzpcbiAgICAgICAgICAgIG0ubmFtZXNwYWNlLnNldChzLmV4cG9ydGVkLm5hbWUsIE9iamVjdC5kZWZpbmVQcm9wZXJ0eShleHBvcnRNZXRhLCAnbmFtZXNwYWNlJywge1xuICAgICAgICAgICAgICBnZXQoKSB7IHJldHVybiByZXNvbHZlSW1wb3J0KG5zb3VyY2UpIH0sXG4gICAgICAgICAgICB9KSlcbiAgICAgICAgICAgIHJldHVyblxuICAgICAgICAgIGNhc2UgJ0V4cG9ydFNwZWNpZmllcic6XG4gICAgICAgICAgICBpZiAoIW4uc291cmNlKSB7XG4gICAgICAgICAgICAgIG0ubmFtZXNwYWNlLnNldChzLmV4cG9ydGVkLm5hbWUsIGFkZE5hbWVzcGFjZShleHBvcnRNZXRhLCBzLmxvY2FsKSlcbiAgICAgICAgICAgICAgcmV0dXJuXG4gICAgICAgICAgICB9XG4gICAgICAgICAgICAvLyBlbHNlIGZhbGxzIHRocm91Z2hcbiAgICAgICAgICBkZWZhdWx0OlxuICAgICAgICAgICAgbG9jYWwgPSBzLmxvY2FsLm5hbWVcbiAgICAgICAgICAgIGJyZWFrXG4gICAgICAgIH1cblxuICAgICAgICAvLyB0b2RvOiBKU0RvY1xuICAgICAgICBtLnJlZXhwb3J0cy5zZXQocy5leHBvcnRlZC5uYW1lLCB7IGxvY2FsLCBnZXRJbXBvcnQ6ICgpID0+IHJlc29sdmVJbXBvcnQobnNvdXJjZSkgfSlcbiAgICAgIH0pXG4gICAgfVxuICB9KVxuXG4gIHJldHVybiBtXG59XG5cblxuLyoqXG4gKiBUcmF2ZXJzZSBhIHBhdHRlcm4vaWRlbnRpZmllciBub2RlLCBjYWxsaW5nICdjYWxsYmFjaydcbiAqIGZvciBlYWNoIGxlYWYgaWRlbnRpZmllci5cbiAqIEBwYXJhbSAge25vZGV9ICAgcGF0dGVyblxuICogQHBhcmFtICB7RnVuY3Rpb259IGNhbGxiYWNrXG4gKiBAcmV0dXJuIHt2b2lkfVxuICovXG5leHBvcnQgZnVuY3Rpb24gcmVjdXJzaXZlUGF0dGVybkNhcHR1cmUocGF0dGVybiwgY2FsbGJhY2spIHtcbiAgc3dpdGNoIChwYXR0ZXJuLnR5cGUpIHtcbiAgICBjYXNlICdJZGVudGlmaWVyJzogLy8gYmFzZSBjYXNlXG4gICAgICBjYWxsYmFjayhwYXR0ZXJuKVxuICAgICAgYnJlYWtcblxuICAgIGNhc2UgJ09iamVjdFBhdHRlcm4nOlxuICAgICAgcGF0dGVybi5wcm9wZXJ0aWVzLmZvckVhY2gocCA9PiB7XG4gICAgICAgIHJlY3Vyc2l2ZVBhdHRlcm5DYXB0dXJlKHAudmFsdWUsIGNhbGxiYWNrKVxuICAgICAgfSlcbiAgICAgIGJyZWFrXG5cbiAgICBjYXNlICdBcnJheVBhdHRlcm4nOlxuICAgICAgcGF0dGVybi5lbGVtZW50cy5mb3JFYWNoKChlbGVtZW50KSA9PiB7XG4gICAgICAgIGlmIChlbGVtZW50ID09IG51bGwpIHJldHVyblxuICAgICAgICByZWN1cnNpdmVQYXR0ZXJuQ2FwdHVyZShlbGVtZW50LCBjYWxsYmFjaylcbiAgICAgIH0pXG4gICAgICBicmVha1xuICB9XG59XG5cbi8qKlxuICogZG9uJ3QgaG9sZCBmdWxsIGNvbnRleHQgb2JqZWN0IGluIG1lbW9yeSwganVzdCBncmFiIHdoYXQgd2UgbmVlZC5cbiAqL1xuZnVuY3Rpb24gY2hpbGRDb250ZXh0KHBhdGgsIGNvbnRleHQpIHtcbiAgY29uc3QgeyBzZXR0aW5ncywgcGFyc2VyT3B0aW9ucywgcGFyc2VyUGF0aCB9ID0gY29udGV4dFxuICByZXR1cm4ge1xuICAgIHNldHRpbmdzLFxuICAgIHBhcnNlck9wdGlvbnMsXG4gICAgcGFyc2VyUGF0aCxcbiAgICBwYXRoLFxuICB9XG59XG4iXX0=