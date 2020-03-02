'use strict';

const listSequences = require('./list-sequences');
const shouldIgnoreRule = require('./should-ignore-rule');
const shouldIgnoreSelector = require('./should-ignore-selector');
const toInterpoloatedRegexp = require('./to-interpolated-regexp');
const getSelectors = require('./get-selectors');

/**
 * @param {Object} config
 * @param {Rule} config.rule - PostCSS Rule
 * @param {String} config.componentName
 * @param {Boolean} config.weakMode
 * @param {Object} config.selectorPattern
 * @param {Object} config.selectorPatternOptions
 * @param {RegExp} config.ignorePattern
 * @param {Result} config.result - PostCSS Result, for registering warnings
 */
module.exports = (config) => {
  if (shouldIgnoreRule(config.rule)) return;
  const rule = config.rule;

  const initialPattern = (config.selectorPattern.initial)
    ? toInterpoloatedRegexp(config.selectorPattern.initial)(config.componentName, config.selectorPatternOptions)
    : toInterpoloatedRegexp(config.selectorPattern)(config.componentName, config.selectorPatternOptions);
  const combinedPattern = (config.selectorPattern.combined)
    ? toInterpoloatedRegexp(config.selectorPattern.combined)(config.componentName, config.selectorPatternOptions)
    : toInterpoloatedRegexp(initialPattern);
  const selectors = getSelectors(rule);

  selectors.forEach((selector) => {
    // Don't bother with :root
    if (selector === ':root') return;

    const allSequences = listSequences(selector);
    let sequence;
    for (let i = 0, l = allSequences.length; i < l; i++) {
      if (config.weakMode && i !== 0) return;
      sequence = allSequences[i];
      if (config.ignorePattern && shouldIgnoreSelector(sequence, config.ignorePattern)) continue;
      if (i === 0 && initialPattern.test(sequence)) continue;
      if (i !== 0 && combinedPattern.test(sequence)) continue;

      config.result.warn(
        `Invalid component selector "${selector}"`,
        {
          node: rule,
          word: selector,
        }
      );
      return;
    }
  });
};
