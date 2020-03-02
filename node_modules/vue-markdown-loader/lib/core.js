var path = require('path');
var loaderUtils = require('loader-utils');

var markdownCompilerPath = path.resolve(__dirname, 'markdown-compiler.js');

module.exports = function(source) {
  this.cacheable();

  var options = loaderUtils.getOptions(this) || {};
  Object.defineProperty(this._compilation, '__vueMarkdownOptions__', {
    value: options,
    enumerable: false,
    configurable: true
  })

  var filePath = this.resourcePath;

  var result =
    'module.exports = require(' +
    loaderUtils.stringifyRequest(
      this,
      '!!vue-loader!' +
        markdownCompilerPath +
        '?raw!' +
        filePath +
        (this.resourceQuery || '')
    ) +
    ');';

  return result;
};
