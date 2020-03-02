'use strict';

const constants = require('./constants');

module.exports = (rule) => {
  const previousNode = rule.prev();
  return (
    previousNode
    && previousNode.type === 'comment'
    && previousNode.text === constants.IGNORE_COMMENT
  );
};
