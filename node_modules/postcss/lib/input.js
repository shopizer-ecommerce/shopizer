'use strict';

exports.__esModule = true;

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _cssSyntaxError = require('./css-syntax-error');

var _cssSyntaxError2 = _interopRequireDefault(_cssSyntaxError);

var _previousMap = require('./previous-map');

var _previousMap2 = _interopRequireDefault(_previousMap);

var _path = require('path');

var _path2 = _interopRequireDefault(_path);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var sequence = 0;

/**
 * Represents the source CSS.
 *
 * @example
 * const root  = postcss.parse(css, { from: file });
 * const input = root.source.input;
 */

var Input = function () {

    /**
     * @param {string} css    - input CSS source
     * @param {object} [opts] - {@link Processor#process} options
     */
    function Input(css) {
        var opts = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

        _classCallCheck(this, Input);

        if (css === null || (typeof css === 'undefined' ? 'undefined' : _typeof(css)) === 'object' && !css.toString) {
            throw new Error('PostCSS received ' + css + ' instead of CSS string');
        }

        /**
         * @member {string} - input CSS source
         *
         * @example
         * const input = postcss.parse('a{}', { from: file }).input;
         * input.css //=> "a{}";
         */
        this.css = css.toString();

        if (this.css[0] === '\uFEFF' || this.css[0] === '\uFFFE') {
            this.css = this.css.slice(1);
        }

        if (opts.from) {
            if (/^\w+:\/\//.test(opts.from)) {
                /**
                 * @member {string} - The absolute path to the CSS source file
                 *                    defined with the `from` option.
                 *
                 * @example
                 * const root = postcss.parse(css, { from: 'a.css' });
                 * root.source.input.file //=> '/home/ai/a.css'
                 */
                this.file = opts.from;
            } else {
                this.file = _path2.default.resolve(opts.from);
            }
        }

        var map = new _previousMap2.default(this.css, opts);
        if (map.text) {
            /**
             * @member {PreviousMap} - The input source map passed from
             *                         a compilation step before PostCSS
             *                         (for example, from Sass compiler).
             *
             * @example
             * root.source.input.map.consumer().sources //=> ['a.sass']
             */
            this.map = map;
            var file = map.consumer().file;
            if (!this.file && file) this.file = this.mapResolve(file);
        }

        if (!this.file) {
            sequence += 1;
            /**
             * @member {string} - The unique ID of the CSS source. It will be
             *                    created if `from` option is not provided
             *                    (because PostCSS does not know the file path).
             *
             * @example
             * const root = postcss.parse(css);
             * root.source.input.file //=> undefined
             * root.source.input.id   //=> "<input css 1>"
             */
            this.id = '<input css ' + sequence + '>';
        }
        if (this.map) this.map.file = this.from;
    }

    Input.prototype.error = function error(message, line, column) {
        var opts = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : {};

        var result = void 0;
        var origin = this.origin(line, column);
        if (origin) {
            result = new _cssSyntaxError2.default(message, origin.line, origin.column, origin.source, origin.file, opts.plugin);
        } else {
            result = new _cssSyntaxError2.default(message, line, column, this.css, this.file, opts.plugin);
        }

        result.input = { line: line, column: column, source: this.css };
        if (this.file) result.input.file = this.file;

        return result;
    };

    /**
     * Reads the input source map and returns a symbol position
     * in the input source (e.g., in a Sass file that was compiled
     * to CSS before being passed to PostCSS).
     *
     * @param {number} line   - line in input CSS
     * @param {number} column - column in input CSS
     *
     * @return {filePosition} position in input source
     *
     * @example
     * root.source.input.origin(1, 1) //=> { file: 'a.css', line: 3, column: 1 }
     */


    Input.prototype.origin = function origin(line, column) {
        if (!this.map) return false;
        var consumer = this.map.consumer();

        var from = consumer.originalPositionFor({ line: line, column: column });
        if (!from.source) return false;

        var result = {
            file: this.mapResolve(from.source),
            line: from.line,
            column: from.column
        };

        var source = consumer.sourceContentFor(from.source);
        if (source) result.source = source;

        return result;
    };

    Input.prototype.mapResolve = function mapResolve(file) {
        if (/^\w+:\/\//.test(file)) {
            return file;
        } else {
            return _path2.default.resolve(this.map.consumer().sourceRoot || '.', file);
        }
    };

    /**
     * The CSS source identifier. Contains {@link Input#file} if the user
     * set the `from` option, or {@link Input#id} if they did not.
     * @type {string}
     *
     * @example
     * const root = postcss.parse(css, { from: 'a.css' });
     * root.source.input.from //=> "/home/ai/a.css"
     *
     * const root = postcss.parse(css);
     * root.source.input.from //=> "<input css 1>"
     */


    _createClass(Input, [{
        key: 'from',
        get: function get() {
            return this.file || this.id;
        }
    }]);

    return Input;
}();

exports.default = Input;

/**
 * @typedef  {object} filePosition
 * @property {string} file   - path to file
 * @property {number} line   - source line in file
 * @property {number} column - source column in file
 */

module.exports = exports['default'];
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImlucHV0LmVzNiJdLCJuYW1lcyI6WyJzZXF1ZW5jZSIsIklucHV0IiwiY3NzIiwib3B0cyIsInRvU3RyaW5nIiwiRXJyb3IiLCJzbGljZSIsImZyb20iLCJ0ZXN0IiwiZmlsZSIsInJlc29sdmUiLCJtYXAiLCJ0ZXh0IiwiY29uc3VtZXIiLCJtYXBSZXNvbHZlIiwiaWQiLCJlcnJvciIsIm1lc3NhZ2UiLCJsaW5lIiwiY29sdW1uIiwicmVzdWx0Iiwib3JpZ2luIiwic291cmNlIiwicGx1Z2luIiwiaW5wdXQiLCJvcmlnaW5hbFBvc2l0aW9uRm9yIiwic291cmNlQ29udGVudEZvciIsInNvdXJjZVJvb3QiXSwibWFwcGluZ3MiOiI7Ozs7Ozs7O0FBQUE7Ozs7QUFDQTs7OztBQUVBOzs7Ozs7OztBQUVBLElBQUlBLFdBQVcsQ0FBZjs7QUFFQTs7Ozs7Ozs7SUFPTUMsSzs7QUFFRjs7OztBQUlBLG1CQUFZQyxHQUFaLEVBQTZCO0FBQUEsWUFBWkMsSUFBWSx1RUFBTCxFQUFLOztBQUFBOztBQUN6QixZQUFLRCxRQUFRLElBQVIsSUFBZ0IsUUFBT0EsR0FBUCx5Q0FBT0EsR0FBUCxPQUFlLFFBQWYsSUFBMkIsQ0FBQ0EsSUFBSUUsUUFBckQsRUFBZ0U7QUFDNUQsa0JBQU0sSUFBSUMsS0FBSix1QkFBK0JILEdBQS9CLDRCQUFOO0FBQ0g7O0FBRUQ7Ozs7Ozs7QUFPQSxhQUFLQSxHQUFMLEdBQVdBLElBQUlFLFFBQUosRUFBWDs7QUFFQSxZQUFLLEtBQUtGLEdBQUwsQ0FBUyxDQUFULE1BQWdCLFFBQWhCLElBQTRCLEtBQUtBLEdBQUwsQ0FBUyxDQUFULE1BQWdCLFFBQWpELEVBQTREO0FBQ3hELGlCQUFLQSxHQUFMLEdBQVcsS0FBS0EsR0FBTCxDQUFTSSxLQUFULENBQWUsQ0FBZixDQUFYO0FBQ0g7O0FBRUQsWUFBS0gsS0FBS0ksSUFBVixFQUFpQjtBQUNiLGdCQUFLLFlBQVlDLElBQVosQ0FBaUJMLEtBQUtJLElBQXRCLENBQUwsRUFBbUM7QUFDL0I7Ozs7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R0FBb0JjLFVBQXBCLElBQWtDLEdBQS9DLEVBQW9EbEIsSUFBcEQsQ0FBUDtBQUNIO0FBQ0osSzs7QUFFRDs7Ozs7Ozs7Ozs7Ozs7Ozs0QkFZVztBQUNQLG1CQUFPLEtBQUtBLElBQUwsSUFBYSxLQUFLTSxFQUF6QjtBQUNIOzs7Ozs7a0JBSVVkLEs7O0FBRWYiLCJmaWxlIjoiaW5wdXQuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgQ3NzU3ludGF4RXJyb3IgZnJvbSAnLi9jc3Mtc3ludGF4LWVycm9yJztcbmltcG9ydCBQcmV2aW91c01hcCAgICBmcm9tICcuL3ByZXZpb3VzLW1hcCc7XG5cbmltcG9ydCBwYXRoIGZyb20gJ3BhdGgnO1xuXG5sZXQgc2VxdWVuY2UgPSAwO1xuXG4vKipcbiAqIFJlcHJlc2VudHMgdGhlIHNvdXJjZSBDU1MuXG4gKlxuICogQGV4YW1wbGVcbiAqIGNvbnN0IHJvb3QgID0gcG9zdGNzcy5wYXJzZShjc3MsIHsgZnJvbTogZmlsZSB9KTtcbiAqIGNvbnN0IGlucHV0ID0gcm9vdC5zb3VyY2UuaW5wdXQ7XG4gKi9cbmNsYXNzIElucHV0IHtcblxuICAgIC8qKlxuICAgICAqIEBwYXJhbSB7c3RyaW5nfSBjc3MgICAgLSBpbnB1dCBDU1Mgc291cmNlXG4gICAgICogQHBhcmFtIHtvYmplY3R9IFtvcHRzXSAtIHtAbGluayBQcm9jZXNzb3IjcHJvY2Vzc30gb3B0aW9uc1xuICAgICAqL1xuICAgIGNvbnN0cnVjdG9yKGNzcywgb3B0cyA9IHsgfSkge1xuICAgICAgICBpZiAoIGNzcyA9PT0gbnVsbCB8fCB0eXBlb2YgY3NzID09PSAnb2JqZWN0JyAmJiAhY3NzLnRvU3RyaW5nICkge1xuICAgICAgICAgICAgdGhyb3cgbmV3IEVycm9yKGBQb3N0Q1NTIHJlY2VpdmVkICR7IGNzcyB9IGluc3RlYWQgb2YgQ1NTIHN0cmluZ2ApO1xuICAgICAgICB9XG5cbiAgICAgICAgLyoqXG4gICAgICAgICAqIEBtZW1iZXIge3N0cmluZ30gLSBpbnB1dCBDU1Mgc291cmNlXG4gICAgICAgICAqXG4gICAgICAgICAqIEBleGFtcGxlXG4gICAgICAgICAqIGNvbnN0IGlucHV0ID0gcG9zdGNzcy5wYXJzZSgnYXt9JywgeyBmcm9tOiBmaWxlIH0pLmlucHV0O1xuICAgICAgICAgKiBpbnB1dC5jc3MgLy89PiBcImF7fVwiO1xuICAgICAgICAgKi9cbiAgICAgICAgdGhpcy5jc3MgPSBjc3MudG9TdHJpbmcoKTtcblxuICAgICAgICBpZiAoIHRoaXMuY3NzWzBdID09PSAnXFx1RkVGRicgfHwgdGhpcy5jc3NbMF0gPT09ICdcXHVGRkZFJyApIHtcbiAgICAgICAgICAgIHRoaXMuY3NzID0gdGhpcy5jc3Muc2xpY2UoMSk7XG4gICAgICAgIH1cblxuICAgICAgICBpZiAoIG9wdHMuZnJvbSApIHtcbiAgICAgICAgICAgIGlmICggL15cXHcrOlxcL1xcLy8udGVzdChvcHRzLmZyb20pICkge1xuICAgICAgICAgICAgICAgIC8qKlxuICAgICAgICAgICAgICAgICAqIEBtZW1iZXIge3N0cmluZ30gLSBUaGUgYWJzb2x1dGUgcGF0aCB0byB0aGUgQ1NTIHNvdXJjZSBmaWxlXG4gICAgICAgICAgICAgICAgICogICAgICAgICAgICAgICAgICAgIGRlZmluZWQgd2l0aCB0aGUgYGZyb21gIG9wdGlvbi5cbiAgICAgICAgICAgICAgICAgKlxuICAgICAgICAgICAgICAgICAqIEBleGFtcGxlXG4gICAgICAgICAgICAgICAgICogY29uc3Qgcm9vdCA9IHBvc3Rjc3MucGFyc2UoY3NzLCB7IGZyb206ICdhLmNzcycgfSk7XG4gICAgICAgICAgICAgICAgICogcm9vdC5zb3VyY2UuaW5wdXQuZmlsZSAvLz0+ICcvaG9tZS9haS9hLmNzcydcbiAgICAgICAgICAgICAgICAgKi9cbiAgICAgICAgICAgICAgICB0aGlzLmZpbGUgPSBvcHRzLmZyb207XG4gICAgICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgICAgIHRoaXMuZmlsZSA9IHBhdGgucmVzb2x2ZShvcHRzLmZyb20pO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG5cbiAgICAgICAgbGV0IG1hcCA9IG5ldyBQcmV2aW91c01hcCh0aGlzLmNzcywgb3B0cyk7XG4gICAgICAgIGlmICggbWFwLnRleHQgKSB7XG4gICAgICAgICAgICAvKipcbiAgICAgICAgICAgICAqIEBtZW1iZXIge1ByZXZpb3VzTWFwfSAtIFRoZSBpbnB1dCBzb3VyY2UgbWFwIHBhc3NlZCBmcm9tXG4gICAgICAgICAgICAgKiAgICAgICAgICAgICAgICAgICAgICAgICBhIGNvbXBpbGF0aW9uIHN0ZXAgYmVmb3JlIFBvc3RDU1NcbiAgICAgICAgICAgICAqICAgICAgICAgICAgICAgICAgICAgICAgIChmb3IgZXhhbXBsZSwgZnJvbSBTYXNzIGNvbXBpbGVyKS5cbiAgICAgICAgICAgICAqXG4gICAgICAgICAgICAgKiBAZXhhbXBsZVxuICAgICAgICAgICAgICogcm9vdC5zb3VyY2UuaW5wdXQubWFwLmNvbnN1bWVyKCkuc291cmNlcyAvLz0+IFsnYS5zYXNzJ11cbiAgICAgICAgICAgICAqL1xuICAgICAgICAgICAgdGhpcy5tYXAgPSBtYXA7XG4gICAgICAgICAgICBsZXQgZmlsZSA9IG1hcC5jb25zdW1lcigpLmZpbGU7XG4gICAgICAgICAgICBpZiAoICF0aGlzLmZpbGUgJiYgZmlsZSApIHRoaXMuZmlsZSA9IHRoaXMubWFwUmVzb2x2ZShmaWxlKTtcbiAgICAgICAgfVxuXG4gICAgICAgIGlmICggIXRoaXMuZmlsZSApIHtcbiAgICAgICAgICAgIHNlcXVlbmNlICs9IDE7XG4gICAgICAgICAgICAvKipcbiAgICAgICAgICAgICAqIEBtZW1iZXIge3N0cmluZ30gLSBUaGUgdW5pcXVlIElEIG9mIHRoZSBDU1Mgc291cmNlLiBJdCB3aWxsIGJlXG4gICAgICAgICAgICAgKiAgICAgICAgICAgICAgICAgICAgY3JlYXRlZCBpZiBgZnJvbWAgb3B0aW9uIGlzIG5vdCBwcm92aWRlZFxuICAgICAgICAgICAgICogICAgICAgICAgICAgICAgICAgIChiZWNhdXNlIFBvc3RDU1MgZG9lcyBub3Qga25vdyB0aGUgZmlsZSBwYXRoKS5cbiAgICAgICAgICAgICAqXG4gICAgICAgICAgICAgKiBAZXhhbXBsZVxuICAgICAgICAgICAgICogY29uc3Qgcm9vdCA9IHBvc3Rjc3MucGFyc2UoY3NzKTtcbiAgICAgICAgICAgICAqIHJvb3Quc291cmNlLmlucHV0LmZpbGUgLy89PiB1bmRlZmluZWRcbiAgICAgICAgICAgICAqIHJvb3Quc291cmNlLmlucHV0LmlkICAgLy89PiBcIjxpbnB1dCBjc3MgMT5cIlxuICAgICAgICAgICAgICovXG4gICAgICAgICAgICB0aGlzLmlkICAgPSAnPGlucHV0IGNzcyAnICsgc2VxdWVuY2UgKyAnPic7XG4gICAgICAgIH1cbiAgICAgICAgaWYgKCB0aGlzLm1hcCApIHRoaXMubWFwLmZpbGUgPSB0aGlzLmZyb207XG4gICAgfVxuXG4gICAgZXJyb3IobWVzc2FnZSwgbGluZSwgY29sdW1uLCBvcHRzID0geyB9KSB7XG4gICAgICAgIGxldCByZXN1bHQ7XG4gICAgICAgIGxldCBvcmlnaW4gPSB0aGlzLm9yaWdpbihsaW5lLCBjb2x1bW4pO1xuICAgICAgICBpZiAoIG9yaWdpbiApIHtcbiAgICAgICAgICAgIHJlc3VsdCA9IG5ldyBDc3NTeW50YXhFcnJvcihtZXNzYWdlLCBvcmlnaW4ubGluZSwgb3JpZ2luLmNvbHVtbixcbiAgICAgICAgICAgICAgICBvcmlnaW4uc291cmNlLCBvcmlnaW4uZmlsZSwgb3B0cy5wbHVnaW4pO1xuICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgcmVzdWx0ID0gbmV3IENzc1N5bnRheEVycm9yKG1lc3NhZ2UsIGxpbmUsIGNvbHVtbixcbiAgICAgICAgICAgICAgICB0aGlzLmNzcywgdGhpcy5maWxlLCBvcHRzLnBsdWdpbik7XG4gICAgICAgIH1cblxuICAgICAgICByZXN1bHQuaW5wdXQgPSB7IGxpbmUsIGNvbHVtbiwgc291cmNlOiB0aGlzLmNzcyB9O1xuICAgICAgICBpZiAoIHRoaXMuZmlsZSApIHJlc3VsdC5pbnB1dC5maWxlID0gdGhpcy5maWxlO1xuXG4gICAgICAgIHJldHVybiByZXN1bHQ7XG4gICAgfVxuXG4gICAgLyoqXG4gICAgICogUmVhZHMgdGhlIGlucHV0IHNvdXJjZSBtYXAgYW5kIHJldHVybnMgYSBzeW1ib2wgcG9zaXRpb25cbiAgICAgKiBpbiB0aGUgaW5wdXQgc291cmNlIChlLmcuLCBpbiBhIFNhc3MgZmlsZSB0aGF0IHdhcyBjb21waWxlZFxuICAgICAqIHRvIENTUyBiZWZvcmUgYmVpbmcgcGFzc2VkIHRvIFBvc3RDU1MpLlxuICAgICAqXG4gICAgICogQHBhcmFtIHtudW1iZXJ9IGxpbmUgICAtIGxpbmUgaW4gaW5wdXQgQ1NTXG4gICAgICogQHBhcmFtIHtudW1iZXJ9IGNvbHVtbiAtIGNvbHVtbiBpbiBpbnB1dCBDU1NcbiAgICAgKlxuICAgICAqIEByZXR1cm4ge2ZpbGVQb3NpdGlvbn0gcG9zaXRpb24gaW4gaW5wdXQgc291cmNlXG4gICAgICpcbiAgICAgKiBAZXhhbXBsZVxuICAgICAqIHJvb3Quc291cmNlLmlucHV0Lm9yaWdpbigxLCAxKSAvLz0+IHsgZmlsZTogJ2EuY3NzJywgbGluZTogMywgY29sdW1uOiAxIH1cbiAgICAgKi9cbiAgICBvcmlnaW4obGluZSwgY29sdW1uKSB7XG4gICAgICAgIGlmICggIXRoaXMubWFwICkgcmV0dXJuIGZhbHNlO1xuICAgICAgICBsZXQgY29uc3VtZXIgPSB0aGlzLm1hcC5jb25zdW1lcigpO1xuXG4gICAgICAgIGxldCBmcm9tID0gY29uc3VtZXIub3JpZ2luYWxQb3NpdGlvbkZvcih7IGxpbmUsIGNvbHVtbiB9KTtcbiAgICAgICAgaWYgKCAhZnJvbS5zb3VyY2UgKSByZXR1cm4gZmFsc2U7XG5cbiAgICAgICAgbGV0IHJlc3VsdCA9IHtcbiAgICAgICAgICAgIGZpbGU6ICAgdGhpcy5tYXBSZXNvbHZlKGZyb20uc291cmNlKSxcbiAgICAgICAgICAgIGxpbmU6ICAgZnJvbS5saW5lLFxuICAgICAgICAgICAgY29sdW1uOiBmcm9tLmNvbHVtblxuICAgICAgICB9O1xuXG4gICAgICAgIGxldCBzb3VyY2UgPSBjb25zdW1lci5zb3VyY2VDb250ZW50Rm9yKGZyb20uc291cmNlKTtcbiAgICAgICAgaWYgKCBzb3VyY2UgKSByZXN1bHQuc291cmNlID0gc291cmNlO1xuXG4gICAgICAgIHJldHVybiByZXN1bHQ7XG4gICAgfVxuXG4gICAgbWFwUmVzb2x2ZShmaWxlKSB7XG4gICAgICAgIGlmICggL15cXHcrOlxcL1xcLy8udGVzdChmaWxlKSApIHtcbiAgICAgICAgICAgIHJldHVybiBmaWxlO1xuICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgcmV0dXJuIHBhdGgucmVzb2x2ZSh0aGlzLm1hcC5jb25zdW1lcigpLnNvdXJjZVJvb3QgfHwgJy4nLCBmaWxlKTtcbiAgICAgICAgfVxuICAgIH1cblxuICAgIC8qKlxuICAgICAqIFRoZSBDU1Mgc291cmNlIGlkZW50aWZpZXIuIENvbnRhaW5zIHtAbGluayBJbnB1dCNmaWxlfSBpZiB0aGUgdXNlclxuICAgICAqIHNldCB0aGUgYGZyb21gIG9wdGlvbiwgb3Ige0BsaW5rIElucHV0I2lkfSBpZiB0aGV5IGRpZCBub3QuXG4gICAgICogQHR5cGUge3N0cmluZ31cbiAgICAgKlxuICAgICAqIEBleGFtcGxlXG4gICAgICogY29uc3Qgcm9vdCA9IHBvc3Rjc3MucGFyc2UoY3NzLCB7IGZyb206ICdhLmNzcycgfSk7XG4gICAgICogcm9vdC5zb3VyY2UuaW5wdXQuZnJvbSAvLz0+IFwiL2hvbWUvYWkvYS5jc3NcIlxuICAgICAqXG4gICAgICogY29uc3Qgcm9vdCA9IHBvc3Rjc3MucGFyc2UoY3NzKTtcbiAgICAgKiByb290LnNvdXJjZS5pbnB1dC5mcm9tIC8vPT4gXCI8aW5wdXQgY3NzIDE+XCJcbiAgICAgKi9cbiAgICBnZXQgZnJvbSgpIHtcbiAgICAgICAgcmV0dXJuIHRoaXMuZmlsZSB8fCB0aGlzLmlkO1xuICAgIH1cblxufVxuXG5leHBvcnQgZGVmYXVsdCBJbnB1dDtcblxuLyoqXG4gKiBAdHlwZWRlZiAge29iamVjdH0gZmlsZVBvc2l0aW9uXG4gKiBAcHJvcGVydHkge3N0cmluZ30gZmlsZSAgIC0gcGF0aCB0byBmaWxlXG4gKiBAcHJvcGVydHkge251bWJlcn0gbGluZSAgIC0gc291cmNlIGxpbmUgaW4gZmlsZVxuICogQHByb3BlcnR5IHtudW1iZXJ9IGNvbHVtbiAtIHNvdXJjZSBjb2x1bW4gaW4gZmlsZVxuICovXG4iXX0=
