'use strict';

// patterns can be a single regexp or an array of regexps
module.exports = (selector, patterns) => {
  if (!patterns) return false;
  return [].concat(patterns).some(p => p.test(selector));
};
