'use strict';

const listSequences = require('./list-sequences');
const shouldIgnoreRule = require('./should-ignore-rule');
const shouldIgnoreSelector = require('./should-ignore-selector');
const getSelectors = require('./get-selectors');

/**
 * @param {Object} config
 * @param {Rule} config.rule - PostCSS Rule
 * @param {RegExp} config.utilityPattern
 * @param {RegExp} config.ignorePattern
 * @param {Result} config.result - PostCSS Result, for registering warnings
 */
module.exports = (config) => {
  if (shouldIgnoreRule(config.rule)) return;
  const selectors = getSelectors(config.rule);

  selectors.forEach((selector) => {
    const allSequences = listSequences(selector);
    for (const sequence of allSequences) {
      if (config.ignorePattern && shouldIgnoreSelector(sequence, config.ignorePattern)) continue;
      if (config.utilityPattern.test(sequence)) continue;
      config.result.warn(
        `Invalid utility selector "${selector}"`,
        {
          node: config.rule,
          word: selector,
        }
      );
      return;
    }
  });
};
