'use strict';

exports.__esModule = true;

var _stringifier = require('postcss/lib/stringifier');

var _stringifier2 = _interopRequireDefault(_stringifier);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ScssStringifier = function (_Stringifier) {
    _inherits(ScssStringifier, _Stringifier);

    function ScssStringifier() {
        _classCallCheck(this, ScssStringifier);

        return _possibleConstructorReturn(this, _Stringifier.apply(this, arguments));
    }

    ScssStringifier.prototype.comment = function comment(node) {
        var left = this.raw(node, 'left', 'commentLeft');
        var right = this.raw(node, 'right', 'commentRight');

        if (node.raws.inline) {
            var text = node.raws.text || node.text;
            this.builder('//' + left + text + right, node);
        } else {
            this.builder('/*' + left + node.text + right + '*/', node);
        }
    };

    ScssStringifier.prototype.decl = function decl(node, semicolon) {
        if (!node.isNested) {
            _Stringifier.prototype.decl.call(this, node, semicolon);
        } else {

            var between = this.raw(node, 'between', 'colon');
            var string = node.prop + between + this.rawValue(node, 'value');
            if (node.important) {
                string += node.raws.important || ' !important';
            }

            this.builder(string + '{', node, 'start');

            var after = void 0;
            if (node.nodes && node.nodes.length) {
                this.body(node);
                after = this.raw(node, 'after');
            } else {
                after = this.raw(node, 'after', 'emptyBody');
            }
            if (after) this.builder(after);
            this.builder('}', node, 'end');
        }
    };

    ScssStringifier.prototype.rawValue = function rawValue(node, prop) {
        var value = node[prop];
        var raw = node.raws[prop];
        if (raw && raw.value === value) {
            return raw.scss ? raw.scss : raw.raw;
        } else {
            return value;
        }
    };

    return ScssStringifier;
}(_stringifier2.default);

exports.default = ScssStringifier;
module.exports = exports['default'];
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNjc3Mtc3RyaW5naWZpZXIuZXM2Il0sIm5hbWVzIjpbIlNjc3NTdHJpbmdpZmllciIsImNvbW1lbnQiLCJub2RlIiwibGVmdCIsInJhdyIsInJpZ2h0IiwicmF3cyIsImlubGluZSIsInRleHQiLCJidWlsZGVyIiwiZGVjbCIsInNlbWljb2xvbiIsImlzTmVzdGVkIiwiYmV0d2VlbiIsInN0cmluZyIsInByb3AiLCJyYXdWYWx1ZSIsImltcG9ydGFudCIsImFmdGVyIiwibm9kZXMiLCJsZW5ndGgiLCJib2R5IiwidmFsdWUiLCJzY3NzIiwiU3RyaW5naWZpZXIiXSwibWFwcGluZ3MiOiI7Ozs7QUFBQTs7Ozs7Ozs7Ozs7O0lBRXFCQSxlOzs7Ozs7Ozs7OEJBRWpCQyxPLG9CQUFRQyxJLEVBQU07QUFDVixZQUFJQyxPQUFRLEtBQUtDLEdBQUwsQ0FBU0YsSUFBVCxFQUFlLE1BQWYsRUFBd0IsYUFBeEIsQ0FBWjtBQUNBLFlBQUlHLFFBQVEsS0FBS0QsR0FBTCxDQUFTRixJQUFULEVBQWUsT0FBZixFQUF3QixjQUF4QixDQUFaOztBQUVBLFlBQUtBLEtBQUtJLElBQUwsQ0FBVUMsTUFBZixFQUF3QjtBQUNwQixnQkFBSUMsT0FBT04sS0FBS0ksSUFBTCxDQUFVRSxJQUFWLElBQWtCTixLQUFLTSxJQUFsQztBQUNBLGlCQUFLQyxPQUFMLENBQWEsT0FBT04sSUFBUCxHQUFjSyxJQUFkLEdBQXFCSCxLQUFsQyxFQUF5Q0gsSUFBekM7QUFDSCxTQUhELE1BR087QUFDSCxpQkFBS08sT0FBTCxDQUFhLE9BQU9OLElBQVAsR0FBY0QsS0FBS00sSUFBbkIsR0FBMEJILEtBQTFCLEdBQWtDLElBQS9DLEVBQXFESCxJQUFyRDtBQUNIO0FBQ0osSzs7OEJBRURRLEksaUJBQUtSLEksRUFBTVMsUyxFQUFXO0FBQ2xCLFlBQUssQ0FBQ1QsS0FBS1UsUUFBWCxFQUFzQjtBQUNsQixtQ0FBTUYsSUFBTixZQUFXUixJQUFYLEVBQWlCUyxTQUFqQjtBQUNILFNBRkQsTUFFTzs7QUFFSCxnQkFBSUUsVUFBVSxLQUFLVCxHQUFMLENBQVNGLElBQVQsRUFBZSxTQUFmLEVBQTBCLE9BQTFCLENBQWQ7QUFDQSxnQkFBSVksU0FBVVosS0FBS2EsSUFBTCxHQUFZRixPQUFaLEdBQXNCLEtBQUtHLFFBQUwsQ0FBY2QsSUFBZCxFQUFvQixPQUFwQixDQUFwQztBQUNBLGdCQUFLQSxLQUFLZSxTQUFWLEVBQXNCO0FBQ2xCSCwwQkFBVVosS0FBS0ksSUFBTCxDQUFVVyxTQUFWLElBQXVCLGFBQWpDO0FBQ0g7O0FBRUQsaUJBQUtSLE9BQUwsQ0FBYUssU0FBUyxHQUF0QixFQUEyQlosSUFBM0IsRUFBaUMsT0FBakM7O0FBRUEsZ0JBQUlnQixjQUFKO0FBQ0EsZ0JBQUtoQixLQUFLaUIsS0FBTCxJQUFjakIsS0FBS2lCLEtBQUwsQ0FBV0MsTUFBOUIsRUFBdUM7QUFDbkMscUJBQUtDLElBQUwsQ0FBVW5CLElBQVY7QUFDQWdCLHdCQUFRLEtBQUtkLEdBQUwsQ0FBU0YsSUFBVCxFQUFlLE9BQWYsQ0FBUjtBQUNILGFBSEQsTUFHTztBQUNIZ0Isd0JBQVEsS0FBS2QsR0FBTCxDQUFTRixJQUFULEVBQWUsT0FBZixFQUF3QixXQUF4QixDQUFSO0FBQ0g7QUFDRCxnQkFBS2dCLEtBQUwsRUFBYSxLQUFLVCxPQUFMLENBQWFTLEtBQWI7QUFDYixpQkFBS1QsT0FBTCxDQUFhLEdBQWIsRUFBa0JQLElBQWxCLEVBQXdCLEtBQXhCO0FBQ0g7QUFDSixLOzs4QkFFRGMsUSxxQkFBU2QsSSxFQUFNYSxJLEVBQU07QUFDakIsWUFBSU8sUUFBUXBCLEtBQUthLElBQUwsQ0FBWjtBQUNBLFlBQUlYLE1BQVFGLEtBQUtJLElBQUwsQ0FBVVMsSUFBVixDQUFaO0FBQ0EsWUFBS1gsT0FBT0EsSUFBSWtCLEtBQUosS0FBY0EsS0FBMUIsRUFBa0M7QUFDOUIsbUJBQU9sQixJQUFJbUIsSUFBSixHQUFXbkIsSUFBSW1CLElBQWYsR0FBc0JuQixJQUFJQSxHQUFqQztBQUNILFNBRkQsTUFFTztBQUNILG1CQUFPa0IsS0FBUDtBQUNIO0FBQ0osSzs7O0VBL0N3Q0UscUI7O2tCQUF4QnhCLGUiLCJmaWxlIjoic2Nzcy1zdHJpbmdpZmllci5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBTdHJpbmdpZmllciBmcm9tICdwb3N0Y3NzL2xpYi9zdHJpbmdpZmllcic7XG5cbmV4cG9ydCBkZWZhdWx0IGNsYXNzIFNjc3NTdHJpbmdpZmllciBleHRlbmRzIFN0cmluZ2lmaWVyIHtcblxuICAgIGNvbW1lbnQobm9kZSkge1xuICAgICAgICBsZXQgbGVmdCAgPSB0aGlzLnJhdyhub2RlLCAnbGVmdCcsICAnY29tbWVudExlZnQnKTtcbiAgICAgICAgbGV0IHJpZ2h0ID0gdGhpcy5yYXcobm9kZSwgJ3JpZ2h0JywgJ2NvbW1lbnRSaWdodCcpO1xuXG4gICAgICAgIGlmICggbm9kZS5yYXdzLmlubGluZSApIHtcbiAgICAgICAgICAgIGxldCB0ZXh0ID0gbm9kZS5yYXdzLnRleHQgfHwgbm9kZS50ZXh0O1xuICAgICAgICAgICAgdGhpcy5idWlsZGVyKCcvLycgKyBsZWZ0ICsgdGV4dCArIHJpZ2h0LCBub2RlKTtcbiAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgIHRoaXMuYnVpbGRlcignLyonICsgbGVmdCArIG5vZGUudGV4dCArIHJpZ2h0ICsgJyovJywgbm9kZSk7XG4gICAgICAgIH1cbiAgICB9XG5cbiAgICBkZWNsKG5vZGUsIHNlbWljb2xvbikge1xuICAgICAgICBpZiAoICFub2RlLmlzTmVzdGVkICkge1xuICAgICAgICAgICAgc3VwZXIuZGVjbChub2RlLCBzZW1pY29sb24pO1xuICAgICAgICB9IGVsc2Uge1xuXG4gICAgICAgICAgICBsZXQgYmV0d2VlbiA9IHRoaXMucmF3KG5vZGUsICdiZXR3ZWVuJywgJ2NvbG9uJyk7XG4gICAgICAgICAgICBsZXQgc3RyaW5nICA9IG5vZGUucHJvcCArIGJldHdlZW4gKyB0aGlzLnJhd1ZhbHVlKG5vZGUsICd2YWx1ZScpO1xuICAgICAgICAgICAgaWYgKCBub2RlLmltcG9ydGFudCApIHtcbiAgICAgICAgICAgICAgICBzdHJpbmcgKz0gbm9kZS5yYXdzLmltcG9ydGFudCB8fCAnICFpbXBvcnRhbnQnO1xuICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICB0aGlzLmJ1aWxkZXIoc3RyaW5nICsgJ3snLCBub2RlLCAnc3RhcnQnKTtcblxuICAgICAgICAgICAgbGV0IGFmdGVyO1xuICAgICAgICAgICAgaWYgKCBub2RlLm5vZGVzICYmIG5vZGUubm9kZXMubGVuZ3RoICkge1xuICAgICAgICAgICAgICAgIHRoaXMuYm9keShub2RlKTtcbiAgICAgICAgICAgICAgICBhZnRlciA9IHRoaXMucmF3KG5vZGUsICdhZnRlcicpO1xuICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICBhZnRlciA9IHRoaXMucmF3KG5vZGUsICdhZnRlcicsICdlbXB0eUJvZHknKTtcbiAgICAgICAgICAgIH1cbiAgICAgICAgICAgIGlmICggYWZ0ZXIgKSB0aGlzLmJ1aWxkZXIoYWZ0ZXIpO1xuICAgICAgICAgICAgdGhpcy5idWlsZGVyKCd9Jywgbm9kZSwgJ2VuZCcpO1xuICAgICAgICB9XG4gICAgfVxuXG4gICAgcmF3VmFsdWUobm9kZSwgcHJvcCkge1xuICAgICAgICBsZXQgdmFsdWUgPSBub2RlW3Byb3BdO1xuICAgICAgICBsZXQgcmF3ICAgPSBub2RlLnJhd3NbcHJvcF07XG4gICAgICAgIGlmICggcmF3ICYmIHJhdy52YWx1ZSA9PT0gdmFsdWUgKSB7XG4gICAgICAgICAgICByZXR1cm4gcmF3LnNjc3MgPyByYXcuc2NzcyA6IHJhdy5yYXc7XG4gICAgICAgIH0gZWxzZSB7XG4gICAgICAgICAgICByZXR1cm4gdmFsdWU7XG4gICAgICAgIH1cbiAgICB9XG5cbn1cbiJdfQ==
