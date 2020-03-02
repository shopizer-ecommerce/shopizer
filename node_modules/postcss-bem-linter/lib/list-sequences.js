'use strict';

/**
 * Extract an array of selector sequences from a selector string ---
 * all the sequences that were combined via combinators.
 *
 * CSS combinators are " ", >, +, ~
 * (cf. http://www.w3.org/TR/css3-selectors/#selector-syntax)
 *
 * Ignore pseudo-selectors ... by presuming they come at the end of the
 * sequence and cutting them off from the string that gets checked.
 *
 * @param {String} selector
 * @returns {String[]}
 */
module.exports = (selector) => {
  const withoutPseudos = selector.split(':')[0];
  return withoutPseudos
    .split(/[\s>+~]/)
    .filter(s => s !== '');
};
