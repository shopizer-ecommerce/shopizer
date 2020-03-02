"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

exports.default = function (selector) {
  var standardSyntaxSelector = (0, _isStandardSyntaxSelector2.default)(selector);

  // SCSS placeholder selectors
  if (!standardSyntaxSelector) {
    if (selector.indexOf("%") === 0 && !(0, _hasInterpolation2.default)(selector)) {
      return true;
    }
  }

  return standardSyntaxSelector;
};

var _isStandardSyntaxSelector = require("stylelint/lib/utils/isStandardSyntaxSelector");

var _isStandardSyntaxSelector2 = _interopRequireDefault(_isStandardSyntaxSelector);

var _hasInterpolation = require("stylelint/lib/utils/hasInterpolation");

var _hasInterpolation2 = _interopRequireDefault(_hasInterpolation);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }