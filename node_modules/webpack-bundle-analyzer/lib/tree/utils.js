'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.getModulePathParts = getModulePathParts;

var _lodash = require('lodash');

var _lodash2 = _interopRequireDefault(_lodash);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var MULTI_MODULE_REGEXP = /^multi /;

function getModulePathParts(moduleData) {
  if (MULTI_MODULE_REGEXP.test(moduleData.identifier)) {
    return [moduleData.identifier];
  }

  var parsedPath = _lodash2.default
  // Removing loaders from module path: they're joined by `!` and the last part is a raw module path
  .last(moduleData.name.split('!'))
  // Splitting module path into parts
  .split('/')
  // Removing first `.`
  .slice(1)
  // Replacing `~` with `node_modules`
  .map(function (part) {
    return part === '~' ? 'node_modules' : part;
  });

  return parsedPath.length ? parsedPath : null;
}