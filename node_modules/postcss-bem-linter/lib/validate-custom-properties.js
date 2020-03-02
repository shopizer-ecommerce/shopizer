'use strict';

const shouldIgnoreCustomProperty = require('./should-ignore-custom-property');

/**
 * @param {Object} config
 * @param {Rule} config.rule
 * @param {String} config.componentName
 * @param {Result} config.result - PostCSS Result, for registering warnings
 * @param {RegExp} config.ignorePattern
 */
module.exports = (config) => {
  config.rule.walkDecls((declaration) => {
    const property = declaration.prop;

    if (property.indexOf('--') !== 0) return;

    if (shouldIgnoreCustomProperty(property, declaration, config.ignorePattern)) return;

    if (property.indexOf(`${config.componentName}-`) === 2) return;

    config.result.warn(
      `Invalid custom property name "${property}": a component's custom properties must start with the component name`,
      {node: declaration}
    );
  });
};
