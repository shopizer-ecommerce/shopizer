'use strict';

const presetPatterns = require('./preset-patterns');

/**
 * Given some user options, put together a config object that
 * the validators can use.
 *
 * @param {Object|String} [primaryOptions = 'suit']
 * @param {RegExp} [primaryOptions.componentName]
 * @param {RegExp} [primaryOptions.utilitySelectors]
 * @param {Object|Function} [primaryOptions.componentSelectors]
 * @param {RegExp} [primaryOptions.ignoreSelectors]
 * @param {RegExp} [primaryOptions.ignoreCustomProperties]
 * @param {String} [primaryOptions.preset] - The same as passing a string for `primaryOptions`
 * @param {Object} [primaryOptions.presetOptions] - Options that are can be used by
 *   a pattern (e.g. `namespace`)
 * @param {Object} [secondaryOptions] - The same as `primaryOptions.presetOptions`
 * @return {Object} The configuration object
 */
module.exports = (primaryOptions, secondaryOptions) => {
  let patterns = primaryOptions || 'suit';
  if (typeof patterns === 'string') {
    patterns = presetPatterns[patterns];
  } else if (patterns.preset) {
    patterns = presetPatterns[patterns.preset];

    // Enable overriding of preset patterns
    if (primaryOptions.utilitySelectors) {
      patterns.utilitySelectors = primaryOptions.utilitySelectors;
    }
    if (primaryOptions.componentName) {
      patterns.componentName = primaryOptions.componentName;
    }
    if (primaryOptions.componentSelectors) {
      patterns.componentSelectors = primaryOptions.componentSelectors;
    }
    if (primaryOptions.ignoreSelectors) {
      patterns.ignoreSelectors = primaryOptions.ignoreSelectors;
    }
    if (primaryOptions.ignoreCustomProperties) {
      patterns.ignoreCustomProperties = primaryOptions.ignoreCustomProperties;
    }
  }

  let presetOptions = secondaryOptions || {};
  if (primaryOptions && primaryOptions.presetOptions) {
    presetOptions = primaryOptions.presetOptions;
  }

  const implicitComponents =
    getImplicitDefineValue('implicitComponents', primaryOptions, secondaryOptions);
  const implicitUtilities =
    getImplicitDefineValue('implicitUtilities', primaryOptions, secondaryOptions);

  return {
    patterns,
    presetOptions,
    componentNamePattern: patterns.componentName || /^[-_a-zA-Z0-9]+$/,
    implicitComponents,
    implicitUtilities,
  };
};

function getImplicitDefineValue(key, primaryOptions, secondaryOptions) {
  let implicitValue = false;

  if (secondaryOptions && secondaryOptions[key] !== undefined) {
    implicitValue = secondaryOptions[key];
  } else if (primaryOptions && primaryOptions[key]) {
    implicitValue = primaryOptions[key];
  }

  if (typeof implicitValue === 'string') {
    implicitValue = [implicitValue];
  }

  return implicitValue;
}
