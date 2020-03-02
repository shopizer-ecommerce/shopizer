'use strict';

var _stringify = require('babel-runtime/core-js/json/stringify');

var _stringify2 = _interopRequireDefault(_stringify);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var sourceMap = require('source-map');
var loaderUtils = require('loader-utils');

var SourceNode = sourceMap.SourceNode;
var SourceMapConsumer = sourceMap.SourceMapConsumer;

module.exports = function (content, map) {
  this.cacheable();

  var sourceNode;
  var id = this.options.name;

  if (!id) {
    return this.callback(null, content, map);
  }

  if (map) {
    sourceNode = SourceNode.fromStringWithSourceMap(content, new SourceMapConsumer(map));
  } else {
    var fileName = loaderUtils.getRemainingRequest(this);

    sourceNode = new SourceNode(null, null, null);
    content.split('\n').forEach(function (line, idx) {
      sourceNode.add(new SourceNode(idx + 1, 0, fileName, `${line}\n`));
    });
    sourceNode.setSourceContent(fileName, content);
  }

  var concatSrc = new SourceNode();

  concatSrc.add([`describe(${(0, _stringify2.default)(id)}, function() {\n`, sourceNode, '\n});']);

  var result = concatSrc.toStringWithSourceMap();

  this.callback(undefined, result.code, result.map.toString());
};