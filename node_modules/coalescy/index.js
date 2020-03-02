/**
 * Return the first non null of the passed elements
 * it works the same as
 *
 * a || b
 *
 * but it works on falsie values too
 *
 * @method coalescy
 * @static
 * @return {Object} the first non null of the arguments passed. Null if all the values are null
 * @example
 * ```javascript
 * var clsc = require('coalescy');
 * var obj = clsc(null, []); // obj = [];
 * obj = clsc(null, {}); // obj = {};
 * obj = clsc(null, [], {}); // obj = []; // the first non null
 * obj = clsc(null, undefined, 0, []) // 0
 * ```
 */
module.exports = function clsc() {
  var args = arguments;

  args = [].slice.call( args );
  for (var i = 0, len = args.length; i < len; i++) {
    var current = args[ i ];
    if ( typeof current !== 'undefined' && current !== null  ) {
      return current;
    }
  }
  return null;
};
