'use strict';

const constants = require('./constants');

// patterns can be a single regexp or an array of regexps
module.exports = (customProperty, declaration, patterns) => {
  const previousNode = declaration.prev();
  if (
    previousNode
    && previousNode.type === 'comment'
    && previousNode.text === constants.IGNORE_COMMENT
  ) return true;

  if (!patterns) return false;

  return [].concat(patterns).some(p => p.test(customProperty));
};
