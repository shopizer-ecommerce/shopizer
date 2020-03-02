'use strict';

// Dependencies
var stylelint = require('stylelint');

function lint (options) {
  return stylelint.lint(options);
}

module.exports = lint;
