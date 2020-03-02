'use strict';

const postcss = require('postcss');
const validateCustomProperties = require('./lib/validate-custom-properties');
const validateUtilities = require('./lib/validate-utilities');
const validateSelectors = require('./lib/validate-selectors');
const generateConfig = require('./lib/generate-config');
const toRegexp = require('./lib/to-regexp');
const path = require('path');
const checkImplicit = require('./lib/check-implicit');

const DEFINE_VALUE = '([-_a-zA-Z0-9]+)\\s*(?:;\\s*(weak))?';
const DEFINE_DIRECTIVE = new RegExp(
  `(?:\\*?\\s*@define ${DEFINE_VALUE})|(?:\\s*postcss-bem-linter: define ${DEFINE_VALUE})\\s*`
);
const END_DIRECTIVE = new RegExp(
  '(?:\\*\\s*@end\\s*)|' +
  '(?:\\s*postcss-bem-linter: end)\\s*'
);
const UTILITIES_IDENT = 'utilities';
const WEAK_IDENT = 'weak';

function stripUnderscore(str) {
  return str.replace(/^_/, '');
}

/**
 * Set things up and call the validators.
 *
 * If the input CSS does not have any
 * directive defining a component name according to the specified pattern,
 * do nothing -- or warn, if the directive is there but the name does not match.
 *
 * @param {Object|String} primaryOptions
 * @param {Object} [secondaryOptions]
 */
module.exports = postcss.plugin('postcss-bem-linter', (primaryOptions, secondaryOptions) => {
  const config = generateConfig(primaryOptions, secondaryOptions);
  const patterns = config.patterns;

  return (root, result) => {
    const ranges = findRanges(root);

    root.walkRules((rule) => {
      if (rule.parent && rule.parent.name === 'keyframes') return;
      if (!rule.source) return;

      const ruleStartLine = rule.source.start.line;
      ranges.forEach((range) => {
        if (ruleStartLine < range.start) return;
        if (range.end && ruleStartLine > range.end) return;
        checkRule(rule, range);
      });
    });

    function checkRule(rule, range) {
      if (range.defined === UTILITIES_IDENT) {
        if (!patterns.utilitySelectors) {
          throw new Error(
            'You tried to `@define utilities` but have not provided ' +
            'a `utilitySelectors` pattern'
          );
        }
        validateUtilities({
          rule,
          utilityPattern: toRegexp(patterns.utilitySelectors),
          ignorePattern: toRegexp(patterns.ignoreSelectors),
          result,
        });
        return;
      }

      if (!patterns.componentSelectors) {
        throw new Error(
          'You tried to `@define` a component but have not provided ' +
          'a `componentSelectors` pattern'
        );
      }
      validateCustomProperties({
        rule,
        componentName: range.defined,
        result,
        ignorePattern: toRegexp(patterns.ignoreCustomProperties),
      });
      validateSelectors({
        rule,
        componentName: range.defined,
        weakMode: range.weakMode,
        selectorPattern: patterns.componentSelectors,
        selectorPatternOptions: config.presetOptions,
        ignorePattern: toRegexp(patterns.ignoreSelectors),
        result,
      });
    }

    function findRanges(root) {
      const ranges = [];

      if (root.source && root.source.input && root.source.input.file) {
        const filename = root.source.input.file;
        if (checkImplicit.isImplicitUtilities(config.implicitUtilities, filename)) {
          ranges.push({
            defined: 'utilities',
            start: 0,
            weakMode: false,
          });
        } else if (checkImplicit.isImplicitComponent(config.implicitComponents, filename)) {
          let defined = stripUnderscore(path.basename(filename).split('.')[0]);
          if (defined === 'index') {
            defined = path.basename(path.join(filename, '..'));
          }

          if (defined !== UTILITIES_IDENT && !toRegexp(config.componentNamePattern).test(defined)) {
            result.warn(
              `Invalid component name from implicit conversion from filename ${filename}`
            );
          }
          ranges.push({
            defined,
            start: 0,
            weakMode: false,
          });
        }
      }

      root.walkComments((comment) => {
        const commentStartLine = (comment.source) ? comment.source.start.line : null;
        if (!commentStartLine) return;

        if (END_DIRECTIVE.test(comment.text)) {
          endCurrentRange(commentStartLine);
          return;
        }

        const directiveMatch = comment.text.match(DEFINE_DIRECTIVE);
        if (!directiveMatch) return;
        const defined = (directiveMatch[1] || directiveMatch[3]).trim();
        if (defined !== UTILITIES_IDENT && !toRegexp(config.componentNamePattern).test(defined)) {
          result.warn(
            `Invalid component name in definition /*${comment}*/`,
            {node: comment}
          );
        }
        endCurrentRange(commentStartLine);
        ranges.push({
          defined,
          start: commentStartLine,
          weakMode: directiveMatch[2] === WEAK_IDENT,
        });
      });
      return ranges;

      function endCurrentRange(line) {
        if (!ranges.length) return;
        const lastRange = ranges[ranges.length - 1];
        if (lastRange.end) return;
        lastRange.end = line;
      }
    }
  };
});
