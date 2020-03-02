"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.messages = exports.ruleName = undefined;

exports.default = function (primaryOption, secondaryOptions) {
  return function (root, result) {
    var validOptions = _stylelint.utils.validateOptions(result, ruleName, {
      actual: primaryOption
    }, {
      actual: secondaryOptions,
      possible: {
        ignore: ["local"]
      },
      optional: true
    });

    if (!validOptions) {
      return;
    }

    root.walkDecls(function (decl) {
      // not variable
      if (decl.prop[0] !== "$") {
        return;
      }

      // "ignore" options
      if ((0, _utils.optionsHaveIgnored)(secondaryOptions, "local") && decl.parent.type !== "root") {
        return;
      }

      if (decl.value.toLowerCase().indexOf("!default") !== -1) {
        return;
      }

      _stylelint.utils.report({
        message: messages.expected(decl.prop),
        node: decl,
        result: result,
        ruleName: ruleName
      });
    });
  };
};

var _stylelint = require("stylelint");

var _utils = require("../../utils");

var ruleName = exports.ruleName = (0, _utils.namespace)("dollar-variable-default");

var messages = exports.messages = _stylelint.utils.ruleMessages(ruleName, {
  expected: function expected(variable) {
    return "Expected !default flag for \"" + variable + "\"";
  }
});