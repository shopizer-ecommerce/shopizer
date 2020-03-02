var _ = require('lodash');
var Result = require('postcss/lib/result');
var stylelint = require('stylelint');
var bemLinter = require('postcss-bem-linter');

var ruleName = 'plugin/selector-bem-pattern';

var optionsSchema = {
  preset: ['suit', 'bem'],
  presetOptions: function() { return true; }, // Can't currently validate `presetOptions`
  componentName: [isStringOrRegExp],
  componentSelectors: [function(pattern) {
    if (isStringOrFunction(pattern)) return true;
    if (!pattern.initial) return false;
    if (!isStringOrFunction(pattern.initial)) return false;
    if (pattern.combined && !isStringOrFunction(pattern.combined)) return false;
    return true;
  }],
  implicitComponents: [_.isBoolean, _.isString, function(pattern) {
    return _.isArray(pattern) && _.every(pattern, _.isString);
  }],
  utilitySelectors: [isStringOrRegExp],
  ignoreSelectors: [
    isStringOrRegExp,
    function(pattern) {
      if (!_.isArray(pattern)) {
        return isStringOrRegExp(pattern);
      }
      return _.every(pattern, isStringOrRegExp);
    },
  ],
  ignoreCustomProperties: [
    isStringOrRegExp,
    function(pattern) {
      if (!_.isArray(pattern)) {
        return isStringOrRegExp(pattern);
      }
      return _.every(pattern, isStringOrRegExp);
    },
  ],
};

module.exports = stylelint.createPlugin(ruleName, function(options) {
  return function(root, result) {
    if (!options) return;
    var validOptions = stylelint.utils.validateOptions(result, ruleName, {
      actual: options,
      possible: optionsSchema,
    });
    if (!validOptions) return;

    var bemLinterResult = new Result();
    bemLinter(options)(root, bemLinterResult)
    var bemLinterWarnings = bemLinterResult.warnings();

    bemLinterWarnings.forEach(function(warning) {
      stylelint.utils.report({
        ruleName: ruleName,
        result: result,
        node: warning.node || root,
        line: warning.line,
        column: warning.column,
        message: warning.text + ' (' + ruleName + ')',
      });
    });
  };
});

function isStringOrRegExp(x) {
  return _.isString(x) || _.isRegExp(x);
}

function isStringOrFunction(x) {
  return _.isString(x) || _.isFunction(x);
}
