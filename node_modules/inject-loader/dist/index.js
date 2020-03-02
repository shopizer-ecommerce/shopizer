module.exports =
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 1);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports) {

module.exports = require("babel-core");

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(2);


/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = injectifyLoader;

var _injectify2 = __webpack_require__(3);

var _injectify3 = _interopRequireDefault(_injectify2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function injectifyLoader(source, inputSourceMap) {
  if (this.cacheable) {
    this.cacheable();
  }

  var _injectify = (0, _injectify3.default)(this, source, inputSourceMap),
      code = _injectify.code,
      map = _injectify.map;

  this.callback(null, code, map);
}
module.exports = exports['default'];

/***/ }),
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = injectify;

var _babelCore = __webpack_require__(0);

var _wrapper_template = __webpack_require__(4);

var _wrapper_template2 = _interopRequireDefault(_wrapper_template);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function processRequireCall(path) {
  var dependencyString = path.node.arguments[0].value;
  path.replaceWith(_babelCore.types.logicalExpression('||', _babelCore.types.CallExpression(_babelCore.types.identifier('__getInjection'), [_babelCore.types.stringLiteral(dependencyString)]), path.node));

  return dependencyString;
}

function injectify(context, source, inputSourceMap) {
  var _transform = (0, _babelCore.transform)(source, {
    babelrc: false,
    code: false,
    compact: false,
    filename: context.resourcePath
  }),
      ast = _transform.ast;

  var dependencies = [];
  (0, _babelCore.traverse)(ast, {
    CallExpression: function CallExpression(path) {
      if (_babelCore.types.isIdentifier(path.node.callee, { name: 'require' })) {
        dependencies.push(processRequireCall(path));
        path.skip();
      }
    }
  });

  if (dependencies.length === 0) {
    context.emitWarning('The module you are trying to inject into doesn\'t have any dependencies. ' + 'Are you sure you want to do this?');
  }

  var dependenciesArrayAst = _babelCore.types.arrayExpression(dependencies.map(function (dependency) {
    return _babelCore.types.stringLiteral(dependency);
  }));
  var wrapperModuleAst = _babelCore.types.file(_babelCore.types.program([(0, _wrapper_template2.default)({ SOURCE: ast, DEPENDENCIES: dependenciesArrayAst })]));

  return (0, _babelCore.transformFromAst)(wrapperModuleAst, source, {
    sourceMaps: context.sourceMap,
    sourceFileName: context.resourcePath,
    inputSourceMap: inputSourceMap,
    babelrc: false,
    compact: false,
    filename: context.resourcePath
  });
}
module.exports = exports['default'];

/***/ }),
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _babelCore = __webpack_require__(0);

exports.default = (0, _babelCore.template)('\n  module.exports = function __injector(__injections) {\n    __injections = __injections || {};\n\n    (function __validateInjection() {\n      var validDependencies = DEPENDENCIES;\n      var injectedDependencies = Object.keys(__injections);\n      var invalidInjectedDependencies = injectedDependencies.filter(function (dependency) {\n        return validDependencies.indexOf(dependency) === -1;\n      });\n\n      if (invalidInjectedDependencies.length > 0) {\n        var validDependenciesString = \'- \' + validDependencies.join(\'\\n- \');\n        var injectedDependenciesString = \'- \' + injectedDependencies.join(\'\\n- \');\n        var invalidDependenciesString = \'- \' + invalidInjectedDependencies.join(\'\\n- \');\n\n        throw new Error(\'Some of the injections you passed in are invalid.\\n\' +\n          \'Valid injection targets for this module are:\\n\' + validDependenciesString + \'\\n\' +\n          \'The following injections were passed in:\\n\' + injectedDependenciesString + \'\\n\' +\n          \'The following injections are invalid:\\n\' + invalidDependenciesString + \'\\n\'\n        );\n      }\n    })();\n\n    var module = { exports: {} };\n    var exports = module.exports;\n\n    function __getInjection(dependency) {\n      return __injections.hasOwnProperty(dependency) ? __injections[dependency] : null;\n    }\n\n    (function () {\n      SOURCE\n    })();\n\n    return module.exports;\n  }\n');
module.exports = exports['default'];

/***/ })
/******/ ]);
//# sourceMappingURL=index.js.map