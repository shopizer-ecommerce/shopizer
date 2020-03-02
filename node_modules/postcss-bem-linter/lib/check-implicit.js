'use strict';

const path = require('path');
const minimatch = require('minimatch');

function minimatchList(path, patternList, options) {
  return patternList.some(pattern => minimatch(path, pattern, options));
}

/**
 * Check the file matches on of the globs.
 *
 * @param {string} filename - The filename to test
 * @param {string[]} globs - An array of glob strings to test the filename against
 * @return {boolean}
 */
function checkGlob(filename, globs) {
  // PostCSS turns relative paths into absolute paths
  filename = path.relative(process.cwd(), filename);
  return minimatchList(filename, globs);
}

/**
 * @param {string[]|boolean} implicitComponentsConfig - The configuration value implicitComponents
 * @param {string} filename - The filename of the CSS file being checked
 * @returns {boolean}
 */
function isImplicitComponent(implicitComponentsConfig, filename) {
  if (Array.isArray(implicitComponentsConfig)) {
    return checkGlob(filename, implicitComponentsConfig);
  }

  return Boolean(implicitComponentsConfig);
}

/**
 * @param {string[]|boolean} implicitUtilitiesConfig - The configuration value implicitUtilities
 * @param {string} filename - The filename of the CSS file being checked
 * @return {boolean}
 */
function isImplicitUtilities(implicitUtilitiesConfig, filename) {
  if (Array.isArray(implicitUtilitiesConfig)) {
    return checkGlob(filename, implicitUtilitiesConfig);
  }

  return false;
}

module.exports = {
  isImplicitComponent,
  isImplicitUtilities,
};
