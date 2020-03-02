'use strict';

exports.__esModule = true;

var _comment = require('postcss/lib/comment');

var _comment2 = _interopRequireDefault(_comment);

var _parser = require('postcss/lib/parser');

var _parser2 = _interopRequireDefault(_parser);

var _nestedDeclaration = require('./nested-declaration');

var _nestedDeclaration2 = _interopRequireDefault(_nestedDeclaration);

var _scssTokenize = require('./scss-tokenize');

var _scssTokenize2 = _interopRequireDefault(_scssTokenize);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ScssParser = function (_Parser) {
    _inherits(ScssParser, _Parser);

    function ScssParser() {
        _classCallCheck(this, ScssParser);

        return _possibleConstructorReturn(this, _Parser.apply(this, arguments));
    }

    ScssParser.prototype.createTokenizer = function createTokenizer() {
        this.tokenizer = (0, _scssTokenize2.default)(this.input);
    };

    ScssParser.prototype.rule = function rule(tokens) {
        var withColon = false;
        var brackets = 0;
        var value = '';
        for (var _iterator = tokens, _isArray = Array.isArray(_iterator), _i = 0, _iterator = _isArray ? _iterator : _iterator[Symbol.iterator]();;) {
            var _ref;

            if (_isArray) {
                if (_i >= _iterator.length) break;
                _ref = _iterator[_i++];
            } else {
                _i = _iterator.next();
                if (_i.done) break;
                _ref = _i.value;
            }

            var i = _ref;

            if (withColon) {
                if (i[0] !== 'comment' && i[0] !== '{') {
                    value += i[1];
                }
            } else if (i[0] === 'space' && i[1].indexOf('\n') !== -1) {
                break;
            } else if (i[0] === '(') {
                brackets += 1;
            } else if (i[0] === ')') {
                brackets -= 1;
            } else if (brackets === 0 && i[0] === ':') {
                withColon = true;
            }
        }

        if (!withColon || value.trim() === '' || /^[a-zA-Z-:#]/.test(value)) {
            _Parser.prototype.rule.call(this, tokens);
        } else {

            tokens.pop();
            var node = new _nestedDeclaration2.default();
            this.init(node);

            var last = tokens[tokens.length - 1];
            if (last[4]) {
                node.source.end = { line: last[4], column: last[5] };
            } else {
                node.source.end = { line: last[2], column: last[3] };
            }

            while (tokens[0][0] !== 'word') {
                node.raws.before += tokens.shift()[1];
            }
            node.source.start = { line: tokens[0][2], column: tokens[0][3] };

            node.prop = '';
            while (tokens.length) {
                var type = tokens[0][0];
                if (type === ':' || type === 'space' || type === 'comment') {
                    break;
                }
                node.prop += tokens.shift()[1];
            }

            node.raws.between = '';

            var token = void 0;
            while (tokens.length) {
                token = tokens.shift();

                if (token[0] === ':') {
                    node.raws.between += token[1];
                    break;
                } else {
                    node.raws.between += token[1];
                }
            }

            if (node.prop[0] === '_' || node.prop[0] === '*') {
                node.raws.before += node.prop[0];
                node.prop = node.prop.slice(1);
            }
            node.raws.between += this.spacesAndCommentsFromStart(tokens);
            this.precheckMissedSemicolon(tokens);

            for (var _i2 = tokens.length - 1; _i2 > 0; _i2--) {
                token = tokens[_i2];
                if (token[1] === '!important') {
                    node.important = true;
                    var string = this.stringFrom(tokens, _i2);
                    string = this.spacesFromEnd(tokens) + string;
                    if (string !== ' !important') {
                        node.raws.important = string;
                    }
                    break;
                } else if (token[1] === 'important') {
                    var cache = tokens.slice(0);
                    var str = '';
                    for (var j = _i2; j > 0; j--) {
                        var _type = cache[j][0];
                        if (str.trim().indexOf('!') === 0 && _type !== 'space') {
                            break;
                        }
                        str = cache.pop()[1] + str;
                    }
                    if (str.trim().indexOf('!') === 0) {
                        node.important = true;
                        node.raws.important = str;
                        tokens = cache;
                    }
                }

                if (token[0] !== 'space' && token[0] !== 'comment') {
                    break;
                }
            }

            this.raw(node, 'value', tokens);

            if (node.value.indexOf(':') !== -1) {
                this.checkMissedSemicolon(tokens);
            }

            this.current = node;
        }
    };

    ScssParser.prototype.comment = function comment(token) {
        if (token[6] === 'inline') {
            var node = new _comment2.default();
            this.init(node, token[2], token[3]);
            node.raws.inline = true;
            node.source.end = { line: token[4], column: token[5] };

            var text = token[1].slice(2);
            if (/^\s*$/.test(text)) {
                node.text = '';
                node.raws.left = text;
                node.raws.right = '';
            } else {
                var match = text.match(/^(\s*)([^]*[^\s])(\s*)$/);
                var fixed = match[2].replace(/(\*\/|\/\*)/g, '*//*');
                node.text = fixed;
                node.raws.left = match[1];
                node.raws.right = match[3];
                node.raws.text = match[2];
            }
        } else {
            _Parser.prototype.comment.call(this, token);
        }
    };

    ScssParser.prototype.raw = function raw(node, prop, tokens) {
        _Parser.prototype.raw.call(this, node, prop, tokens);
        if (node.raws[prop]) {
            var scss = node.raws[prop].raw;
            node.raws[prop].raw = tokens.reduce(function (all, i) {
                if (i[0] === 'comment' && i[6] === 'inline') {
                    var text = i[1].slice(2).replace(/(\*\/|\/\*)/g, '*//*');
                    return all + '/*' + text + '*/';
                } else {
                    return all + i[1];
                }
            }, '');
            if (scss !== node.raws[prop].raw) {
                node.raws[prop].scss = scss;
            }
        }
    };

    return ScssParser;
}(_parser2.default);

exports.default = ScssParser;
module.exports = exports['default'];
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNjc3MtcGFyc2VyLmVzNiJdLCJuYW1lcyI6WyJTY3NzUGFyc2VyIiwiY3JlYXRlVG9rZW5pemVyIiwidG9rZW5pemVyIiwiaW5wdXQiLCJydWxlIiwidG9rZW5zIiwid2l0aENvbG9uIiwiYnJhY2tldHMiLCJ2YWx1ZSIsImkiLCJpbmRleE9mIiwidHJpbSIsInRlc3QiLCJwb3AiLCJub2RlIiwiTmVzdGVkRGVjbGFyYXRpb24iLCJpbml0IiwibGFzdCIsImxlbmd0aCIsInNvdXJjZSIsImVuZCIsImxpbmUiLCJjb2x1bW4iLCJyYXdzIiwiYmVmb3JlIiwic2hpZnQiLCJzdGFydCIsInByb3AiLCJ0eXBlIiwiYmV0d2VlbiIsInRva2VuIiwic2xpY2UiLCJzcGFjZXNBbmRDb21tZW50c0Zyb21TdGFydCIsInByZWNoZWNrTWlzc2VkU2VtaWNvbG9uIiwiaW1wb3J0YW50Iiwic3RyaW5nIiwic3RyaW5nRnJvbSIsInNwYWNlc0Zyb21FbmQiLCJjYWNoZSIsInN0ciIsImoiLCJyYXciLCJjaGVja01pc3NlZFNlbWljb2xvbiIsImN1cnJlbnQiLCJjb21tZW50IiwiQ29tbWVudCIsImlubGluZSIsInRleHQiLCJsZWZ0IiwicmlnaHQiLCJtYXRjaCIsImZpeGVkIiwicmVwbGFjZSIsInNjc3MiLCJyZWR1Y2UiLCJhbGwiLCJQYXJzZXIiXSwibWFwcGluZ3MiOiI7Ozs7QUFBQTs7OztBQUNBOzs7O0FBRUE7Ozs7QUFDQTs7Ozs7Ozs7Ozs7O0lBRXFCQSxVOzs7Ozs7Ozs7eUJBRWpCQyxlLDhCQUFrQjtBQUNkLGFBQUtDLFNBQUwsR0FBaUIsNEJBQWMsS0FBS0MsS0FBbkIsQ0FBakI7QUFDSCxLOzt5QkFFREMsSSxpQkFBS0MsTSxFQUFRO0FBQ1QsWUFBSUMsWUFBWSxLQUFoQjtBQUNBLFlBQUlDLFdBQVksQ0FBaEI7QUFDQSxZQUFJQyxRQUFZLEVBQWhCO0FBQ0EsNkJBQWVILE1BQWYsa0hBQXdCO0FBQUE7O0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTs7QUFBQSxnQkFBZEksQ0FBYzs7QUFDcEIsZ0JBQUtILFNBQUwsRUFBaUI7QUFDYixvQkFBS0csRUFBRSxDQUFGLE1BQVMsU0FBVCxJQUFzQkEsRUFBRSxDQUFGLE1BQVMsR0FBcEMsRUFBMEM7QUFDdENELDZCQUFTQyxFQUFFLENBQUYsQ0FBVDtBQUNIO0FBQ0osYUFKRCxNQUlPLElBQUtBLEVBQUUsQ0FBRixNQUFTLE9BQVQsSUFBb0JBLEVBQUUsQ0FBRixFQUFLQyxPQUFMLENBQWEsSUFBYixNQUF1QixDQUFDLENBQWpELEVBQXFEO0FBQ3hEO0FBQ0gsYUFGTSxNQUVBLElBQUtELEVBQUUsQ0FBRixNQUFTLEdBQWQsRUFBb0I7QUFDdkJGLDRCQUFZLENBQVo7QUFDSCxhQUZNLE1BRUEsSUFBS0UsRUFBRSxDQUFGLE1BQVMsR0FBZCxFQUFvQjtBQUN2QkYsNEJBQVksQ0FBWjtBQUNILGFBRk0sTUFFQSxJQUFLQSxhQUFhLENBQWIsSUFBa0JFLEVBQUUsQ0FBRixNQUFTLEdBQWhDLEVBQXNDO0FBQ3pDSCw0QkFBWSxJQUFaO0FBQ0g7QUFDSjs7QUFFRCxZQUFLLENBQUNBLFNBQUQsSUFBY0UsTUFBTUcsSUFBTixPQUFpQixFQUEvQixJQUFxQyxlQUFlQyxJQUFmLENBQW9CSixLQUFwQixDQUExQyxFQUF1RTtBQUNuRSw4QkFBTUosSUFBTixZQUFXQyxNQUFYO0FBQ0gsU0FGRCxNQUVPOztBQUVIQSxtQkFBT1EsR0FBUDtBQUNBLGdCQUFJQyxPQUFPLElBQUlDLDJCQUFKLEVBQVg7QUFDQSxpQkFBS0MsSUFBTCxDQUFVRixJQUFWOztBQUVBLGdCQUFJRyxPQUFPWixPQUFPQSxPQUFPYSxNQUFQLEdBQWdCLENBQXZCLENBQVg7QUFDQSxnQkFBSUQsS0FBSyxDQUFMLENBQUosRUFBYTtBQUNUSCxxQkFBS0ssTUFBTCxDQUFZQyxHQUFaLEdBQWtCLEVBQUVDLE1BQU1KLEtBQUssQ0FBTCxDQUFSLEVBQWlCSyxRQUFRTCxLQUFLLENBQUwsQ0FBekIsRUFBbEI7QUFDSCxhQUZELE1BRU87QUFDSEgscUJBQUtLLE1BQUwsQ0FBWUMsR0FBWixHQUFrQixFQUFFQyxNQUFNSixLQUFLLENBQUwsQ0FBUixFQUFpQkssUUFBUUwsS0FBSyxDQUFMLENBQXpCLEVBQWxCO0FBQ0g7O0FBRUQsbUJBQU9aLE9BQU8sQ0FBUCxFQUFVLENBQVYsTUFBaUIsTUFBeEIsRUFBZ0M7QUFDNUJTLHFCQUFLUyxJQUFMLENBQVVDLE1BQVYsSUFBb0JuQixPQUFPb0IsS0FBUCxHQUFlLENBQWYsQ0FBcEI7QUFDSDtBQUNEWCxpQkFBS0ssTUFBTCxDQUFZTyxLQUFaLEdBQW9CLEVBQUVMLE1BQU1oQixPQUFPLENBQVAsRUFBVSxDQUFWLENBQVIsRUFBc0JpQixRQUFRakIsT0FBTyxDQUFQLEVBQVUsQ0FBVixDQUE5QixFQUFwQjs7QUFFQVMsaUJBQUthLElBQUwsR0FBWSxFQUFaO0FBQ0EsbUJBQU90QixPQUFPYSxNQUFkLEVBQXNCO0FBQ2xCLG9CQUFJVSxPQUFPdkIsT0FBTyxDQUFQLEVBQVUsQ0FBVixDQUFYO0FBQ0Esb0JBQUl1QixTQUFTLEdBQVQsSUFBZ0JBLFNBQVMsT0FBekIsSUFBb0NBLFNBQVMsU0FBakQsRUFBNEQ7QUFDeEQ7QUFDSDtBQUNEZCxxQkFBS2EsSUFBTCxJQUFhdEIsT0FBT29CLEtBQVAsR0FBZSxDQUFmLENBQWI7QUFDSDs7QUFFRFgsaUJBQUtTLElBQUwsQ0FBVU0sT0FBVixHQUFvQixFQUFwQjs7QUFFQSxnQkFBSUMsY0FBSjtBQUNBLG1CQUFPekIsT0FBT2EsTUFBZCxFQUFzQjtBQUNsQlksd0JBQVF6QixPQUFPb0IsS0FBUCxFQUFSOztBQUVBLG9CQUFJSyxNQUFNLENBQU4sTUFBYSxHQUFqQixFQUFzQjtBQUNsQmhCLHlCQUFLUyxJQUFMLENBQVVNLE9BQVYsSUFBcUJDLE1BQU0sQ0FBTixDQUFyQjtBQUNBO0FBQ0gsaUJBSEQsTUFHTztBQUNIaEIseUJBQUtTLElBQUwsQ0FBVU0sT0FBVixJQUFxQkMsTUFBTSxDQUFOLENBQXJCO0FBQ0g7QUFDSjs7QUFFRCxnQkFBSWhCLEtBQUthLElBQUwsQ0FBVSxDQUFWLE1BQWlCLEdBQWpCLElBQXdCYixLQUFLYSxJQUFMLENBQVUsQ0FBVixNQUFpQixHQUE3QyxFQUFrRDtBQUM5Q2IscUJBQUtTLElBQUwsQ0FBVUMsTUFBVixJQUFvQlYsS0FBS2EsSUFBTCxDQUFVLENBQVYsQ0FBcEI7QUFDQWIscUJBQUthLElBQUwsR0FBWWIsS0FBS2EsSUFBTCxDQUFVSSxLQUFWLENBQWdCLENBQWhCLENBQVo7QUFDSDtBQUNEakIsaUJBQUtTLElBQUwsQ0FBVU0sT0FBVixJQUFxQixLQUFLRywwQkFBTCxDQUFnQzNCLE1BQWhDLENBQXJCO0FBQ0EsaUJBQUs0Qix1QkFBTCxDQUE2QjVCLE1BQTdCOztBQUVBLGlCQUFLLElBQUlJLE1BQUlKLE9BQU9hLE1BQVAsR0FBZ0IsQ0FBN0IsRUFBZ0NULE1BQUksQ0FBcEMsRUFBdUNBLEtBQXZDLEVBQTRDO0FBQ3hDcUIsd0JBQVF6QixPQUFPSSxHQUFQLENBQVI7QUFDQSxvQkFBSXFCLE1BQU0sQ0FBTixNQUFhLFlBQWpCLEVBQStCO0FBQzNCaEIseUJBQUtvQixTQUFMLEdBQWlCLElBQWpCO0FBQ0Esd0JBQUlDLFNBQVMsS0FBS0MsVUFBTCxDQUFnQi9CLE1BQWhCLEVBQXdCSSxHQUF4QixDQUFiO0FBQ0EwQiw2QkFBUyxLQUFLRSxhQUFMLENBQW1CaEMsTUFBbkIsSUFBNkI4QixNQUF0QztBQUNBLHdCQUFJQSxXQUFXLGFBQWYsRUFBOEI7QUFDMUJyQiw2QkFBS1MsSUFBTCxDQUFVVyxTQUFWLEdBQXNCQyxNQUF0QjtBQUNIO0FBQ0Q7QUFFSCxpQkFURCxNQVNPLElBQUlMLE1BQU0sQ0FBTixNQUFhLFdBQWpCLEVBQThCO0FBQ2pDLHdCQUFJUSxRQUFRakMsT0FBTzBCLEtBQVAsQ0FBYSxDQUFiLENBQVo7QUFDQSx3QkFBSVEsTUFBUSxFQUFaO0FBQ0EseUJBQUssSUFBSUMsSUFBSS9CLEdBQWIsRUFBZ0IrQixJQUFJLENBQXBCLEVBQXVCQSxHQUF2QixFQUE0QjtBQUN4Qiw0QkFBSVosUUFBT1UsTUFBTUUsQ0FBTixFQUFTLENBQVQsQ0FBWDtBQUNBLDRCQUFJRCxJQUFJNUIsSUFBSixHQUFXRCxPQUFYLENBQW1CLEdBQW5CLE1BQTRCLENBQTVCLElBQ0FrQixVQUFTLE9BRGIsRUFFRTtBQUNFO0FBQ0g7QUFDRFcsOEJBQU1ELE1BQU16QixHQUFOLEdBQVksQ0FBWixJQUFpQjBCLEdBQXZCO0FBQ0g7QUFDRCx3QkFBSUEsSUFBSTVCLElBQUosR0FBV0QsT0FBWCxDQUFtQixHQUFuQixNQUE0QixDQUFoQyxFQUFtQztBQUMvQkksNkJBQUtvQixTQUFMLEdBQWlCLElBQWpCO0FBQ0FwQiw2QkFBS1MsSUFBTCxDQUFVVyxTQUFWLEdBQXNCSyxHQUF0QjtBQUNBbEMsaUNBQVNpQyxLQUFUO0FBQ0g7QUFDSjs7QUFFRCxvQkFBSVIsTUFBTSxDQUFOLE1BQWEsT0FBYixJQUF3QkEsTUFBTSxDQUFOLE1BQWEsU0FBekMsRUFBb0Q7QUFDaEQ7QUFDSDtBQUNKOztBQUVELGlCQUFLVyxHQUFMLENBQVMzQixJQUFULEVBQWUsT0FBZixFQUF3QlQsTUFBeEI7O0FBRUEsZ0JBQUlTLEtBQUtOLEtBQUwsQ0FBV0UsT0FBWCxDQUFtQixHQUFuQixNQUE0QixDQUFDLENBQWpDLEVBQW9DO0FBQ2hDLHFCQUFLZ0Msb0JBQUwsQ0FBMEJyQyxNQUExQjtBQUNIOztBQUVELGlCQUFLc0MsT0FBTCxHQUFlN0IsSUFBZjtBQUNIO0FBQ0osSzs7eUJBRUQ4QixPLG9CQUFRZCxLLEVBQU87QUFDWCxZQUFJQSxNQUFNLENBQU4sTUFBYSxRQUFqQixFQUEyQjtBQUN2QixnQkFBSWhCLE9BQU8sSUFBSStCLGlCQUFKLEVBQVg7QUFDQSxpQkFBSzdCLElBQUwsQ0FBVUYsSUFBVixFQUFnQmdCLE1BQU0sQ0FBTixDQUFoQixFQUEwQkEsTUFBTSxDQUFOLENBQTFCO0FBQ0FoQixpQkFBS1MsSUFBTCxDQUFVdUIsTUFBVixHQUFtQixJQUFuQjtBQUNBaEMsaUJBQUtLLE1BQUwsQ0FBWUMsR0FBWixHQUFtQixFQUFFQyxNQUFNUyxNQUFNLENBQU4sQ0FBUixFQUFrQlIsUUFBUVEsTUFBTSxDQUFOLENBQTFCLEVBQW5COztBQUVBLGdCQUFJaUIsT0FBT2pCLE1BQU0sQ0FBTixFQUFTQyxLQUFULENBQWUsQ0FBZixDQUFYO0FBQ0EsZ0JBQUssUUFBUW5CLElBQVIsQ0FBYW1DLElBQWIsQ0FBTCxFQUEwQjtBQUN0QmpDLHFCQUFLaUMsSUFBTCxHQUFrQixFQUFsQjtBQUNBakMscUJBQUtTLElBQUwsQ0FBVXlCLElBQVYsR0FBa0JELElBQWxCO0FBQ0FqQyxxQkFBS1MsSUFBTCxDQUFVMEIsS0FBVixHQUFrQixFQUFsQjtBQUNILGFBSkQsTUFJTztBQUNILG9CQUFJQyxRQUFRSCxLQUFLRyxLQUFMLENBQVcseUJBQVgsQ0FBWjtBQUNBLG9CQUFJQyxRQUFRRCxNQUFNLENBQU4sRUFBU0UsT0FBVCxDQUFpQixjQUFqQixFQUFpQyxNQUFqQyxDQUFaO0FBQ0F0QyxxQkFBS2lDLElBQUwsR0FBa0JJLEtBQWxCO0FBQ0FyQyxxQkFBS1MsSUFBTCxDQUFVeUIsSUFBVixHQUFrQkUsTUFBTSxDQUFOLENBQWxCO0FBQ0FwQyxxQkFBS1MsSUFBTCxDQUFVMEIsS0FBVixHQUFrQkMsTUFBTSxDQUFOLENBQWxCO0FBQ0FwQyxxQkFBS1MsSUFBTCxDQUFVd0IsSUFBVixHQUFrQkcsTUFBTSxDQUFOLENBQWxCO0FBQ0g7QUFDSixTQW5CRCxNQW1CTztBQUNILDhCQUFNTixPQUFOLFlBQWNkLEtBQWQ7QUFDSDtBQUNKLEs7O3lCQUVEVyxHLGdCQUFJM0IsSSxFQUFNYSxJLEVBQU10QixNLEVBQVE7QUFDcEIsMEJBQU1vQyxHQUFOLFlBQVUzQixJQUFWLEVBQWdCYSxJQUFoQixFQUFzQnRCLE1BQXRCO0FBQ0EsWUFBS1MsS0FBS1MsSUFBTCxDQUFVSSxJQUFWLENBQUwsRUFBdUI7QUFDbkIsZ0JBQUkwQixPQUFPdkMsS0FBS1MsSUFBTCxDQUFVSSxJQUFWLEVBQWdCYyxHQUEzQjtBQUNBM0IsaUJBQUtTLElBQUwsQ0FBVUksSUFBVixFQUFnQmMsR0FBaEIsR0FBc0JwQyxPQUFPaUQsTUFBUCxDQUFlLFVBQUNDLEdBQUQsRUFBTTlDLENBQU4sRUFBWTtBQUM3QyxvQkFBS0EsRUFBRSxDQUFGLE1BQVMsU0FBVCxJQUFzQkEsRUFBRSxDQUFGLE1BQVMsUUFBcEMsRUFBK0M7QUFDM0Msd0JBQUlzQyxPQUFPdEMsRUFBRSxDQUFGLEVBQUtzQixLQUFMLENBQVcsQ0FBWCxFQUFjcUIsT0FBZCxDQUFzQixjQUF0QixFQUFzQyxNQUF0QyxDQUFYO0FBQ0EsMkJBQU9HLE1BQU0sSUFBTixHQUFhUixJQUFiLEdBQW9CLElBQTNCO0FBQ0gsaUJBSEQsTUFHTztBQUNILDJCQUFPUSxNQUFNOUMsRUFBRSxDQUFGLENBQWI7QUFDSDtBQUNKLGFBUHFCLEVBT25CLEVBUG1CLENBQXRCO0FBUUEsZ0JBQUs0QyxTQUFTdkMsS0FBS1MsSUFBTCxDQUFVSSxJQUFWLEVBQWdCYyxHQUE5QixFQUFvQztBQUNoQzNCLHFCQUFLUyxJQUFMLENBQVVJLElBQVYsRUFBZ0IwQixJQUFoQixHQUF1QkEsSUFBdkI7QUFDSDtBQUNKO0FBQ0osSzs7O0VBbEttQ0csZ0I7O2tCQUFuQnhELFUiLCJmaWxlIjoic2Nzcy1wYXJzZXIuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgQ29tbWVudCBmcm9tICdwb3N0Y3NzL2xpYi9jb21tZW50JztcbmltcG9ydCBQYXJzZXIgIGZyb20gJ3Bvc3Rjc3MvbGliL3BhcnNlcic7XG5cbmltcG9ydCBOZXN0ZWREZWNsYXJhdGlvbiBmcm9tICcuL25lc3RlZC1kZWNsYXJhdGlvbic7XG5pbXBvcnQgc2Nzc1Rva2VuaXplciAgICAgZnJvbSAnLi9zY3NzLXRva2VuaXplJztcblxuZXhwb3J0IGRlZmF1bHQgY2xhc3MgU2Nzc1BhcnNlciBleHRlbmRzIFBhcnNlciB7XG5cbiAgICBjcmVhdGVUb2tlbml6ZXIoKSB7XG4gICAgICAgIHRoaXMudG9rZW5pemVyID0gc2Nzc1Rva2VuaXplcih0aGlzLmlucHV0KTtcbiAgICB9XG5cbiAgICBydWxlKHRva2Vucykge1xuICAgICAgICBsZXQgd2l0aENvbG9uID0gZmFsc2U7XG4gICAgICAgIGxldCBicmFja2V0cyAgPSAwO1xuICAgICAgICBsZXQgdmFsdWUgICAgID0gJyc7XG4gICAgICAgIGZvciAoIGxldCBpIG9mIHRva2VucyApIHtcbiAgICAgICAgICAgIGlmICggd2l0aENvbG9uICkge1xuICAgICAgICAgICAgICAgIGlmICggaVswXSAhPT0gJ2NvbW1lbnQnICYmIGlbMF0gIT09ICd7JyApIHtcbiAgICAgICAgICAgICAgICAgICAgdmFsdWUgKz0gaVsxXTtcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICB9IGVsc2UgaWYgKCBpWzBdID09PSAnc3BhY2UnICYmIGlbMV0uaW5kZXhPZignXFxuJykgIT09IC0xICkge1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgfSBlbHNlIGlmICggaVswXSA9PT0gJygnICkge1xuICAgICAgICAgICAgICAgIGJyYWNrZXRzICs9IDE7XG4gICAgICAgICAgICB9IGVsc2UgaWYgKCBpWzBdID09PSAnKScgKSB7XG4gICAgICAgICAgICAgICAgYnJhY2tldHMgLT0gMTtcbiAgICAgICAgICAgIH0gZWxzZSBpZiAoIGJyYWNrZXRzID09PSAwICYmIGlbMF0gPT09ICc6JyApIHtcbiAgICAgICAgICAgICAgICB3aXRoQ29sb24gPSB0cnVlO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG5cbiAgICAgICAgaWYgKCAhd2l0aENvbG9uIHx8IHZhbHVlLnRyaW0oKSA9PT0gJycgfHwgL15bYS16QS1aLTojXS8udGVzdCh2YWx1ZSkgKSB7XG4gICAgICAgICAgICBzdXBlci5ydWxlKHRva2Vucyk7XG4gICAgICAgIH0gZWxzZSB7XG5cbiAgICAgICAgICAgIHRva2Vucy5wb3AoKTtcbiAgICAgICAgICAgIGxldCBub2RlID0gbmV3IE5lc3RlZERlY2xhcmF0aW9uKCk7XG4gICAgICAgICAgICB0aGlzLmluaXQobm9kZSk7XG5cbiAgICAgICAgICAgIGxldCBsYXN0ID0gdG9rZW5zW3Rva2Vucy5sZW5ndGggLSAxXTtcbiAgICAgICAgICAgIGlmIChsYXN0WzRdKSB7XG4gICAgICAgICAgICAgICAgbm9kZS5zb3VyY2UuZW5kID0geyBsaW5lOiBsYXN0WzRdLCBjb2x1bW46IGxhc3RbNV0gfTtcbiAgICAgICAgICAgIH0gZWxzZSB7XG4gICAgICAgICAgICAgICAgbm9kZS5zb3VyY2UuZW5kID0geyBsaW5lOiBsYXN0WzJdLCBjb2x1bW46IGxhc3RbM10gfTtcbiAgICAgICAgICAgIH1cblxuICAgICAgICAgICAgd2hpbGUgKHRva2Vuc1swXVswXSAhPT0gJ3dvcmQnKSB7XG4gICAgICAgICAgICAgICAgbm9kZS5yYXdzLmJlZm9yZSArPSB0b2tlbnMuc2hpZnQoKVsxXTtcbiAgICAgICAgICAgIH1cbiAgICAgICAgICAgIG5vZGUuc291cmNlLnN0YXJ0ID0geyBsaW5lOiB0b2tlbnNbMF1bMl0sIGNvbHVtbjogdG9rZW5zWzBdWzNdIH07XG5cbiAgICAgICAgICAgIG5vZGUucHJvcCA9ICcnO1xuICAgICAgICAgICAgd2hpbGUgKHRva2Vucy5sZW5ndGgpIHtcbiAgICAgICAgICAgICAgICBsZXQgdHlwZSA9IHRva2Vuc1swXVswXTtcbiAgICAgICAgICAgICAgICBpZiAodHlwZSA9PT0gJzonIHx8IHR5cGUgPT09ICdzcGFjZScgfHwgdHlwZSA9PT0gJ2NvbW1lbnQnKSB7XG4gICAgICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgICAgICBub2RlLnByb3AgKz0gdG9rZW5zLnNoaWZ0KClbMV07XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIG5vZGUucmF3cy5iZXR3ZWVuID0gJyc7XG5cbiAgICAgICAgICAgIGxldCB0b2tlbjtcbiAgICAgICAgICAgIHdoaWxlICh0b2tlbnMubGVuZ3RoKSB7XG4gICAgICAgICAgICAgICAgdG9rZW4gPSB0b2tlbnMuc2hpZnQoKTtcblxuICAgICAgICAgICAgICAgIGlmICh0b2tlblswXSA9PT0gJzonKSB7XG4gICAgICAgICAgICAgICAgICAgIG5vZGUucmF3cy5iZXR3ZWVuICs9IHRva2VuWzFdO1xuICAgICAgICAgICAgICAgICAgICBicmVhaztcbiAgICAgICAgICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgICAgICAgICBub2RlLnJhd3MuYmV0d2VlbiArPSB0b2tlblsxXTtcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIGlmIChub2RlLnByb3BbMF0gPT09ICdfJyB8fCBub2RlLnByb3BbMF0gPT09ICcqJykge1xuICAgICAgICAgICAgICAgIG5vZGUucmF3cy5iZWZvcmUgKz0gbm9kZS5wcm9wWzBdO1xuICAgICAgICAgICAgICAgIG5vZGUucHJvcCA9IG5vZGUucHJvcC5zbGljZSgxKTtcbiAgICAgICAgICAgIH1cbiAgICAgICAgICAgIG5vZGUucmF3cy5iZXR3ZWVuICs9IHRoaXMuc3BhY2VzQW5kQ29tbWVudHNGcm9tU3RhcnQodG9rZW5zKTtcbiAgICAgICAgICAgIHRoaXMucHJlY2hlY2tNaXNzZWRTZW1pY29sb24odG9rZW5zKTtcblxuICAgICAgICAgICAgZm9yIChsZXQgaSA9IHRva2Vucy5sZW5ndGggLSAxOyBpID4gMDsgaS0tKSB7XG4gICAgICAgICAgICAgICAgdG9rZW4gPSB0b2tlbnNbaV07XG4gICAgICAgICAgICAgICAgaWYgKHRva2VuWzFdID09PSAnIWltcG9ydGFudCcpIHtcbiAgICAgICAgICAgICAgICAgICAgbm9kZS5pbXBvcnRhbnQgPSB0cnVlO1xuICAgICAgICAgICAgICAgICAgICBsZXQgc3RyaW5nID0gdGhpcy5zdHJpbmdGcm9tKHRva2VucywgaSk7XG4gICAgICAgICAgICAgICAgICAgIHN0cmluZyA9IHRoaXMuc3BhY2VzRnJvbUVuZCh0b2tlbnMpICsgc3RyaW5nO1xuICAgICAgICAgICAgICAgICAgICBpZiAoc3RyaW5nICE9PSAnICFpbXBvcnRhbnQnKSB7XG4gICAgICAgICAgICAgICAgICAgICAgICBub2RlLnJhd3MuaW1wb3J0YW50ID0gc3RyaW5nO1xuICAgICAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgICAgIGJyZWFrO1xuXG4gICAgICAgICAgICAgICAgfSBlbHNlIGlmICh0b2tlblsxXSA9PT0gJ2ltcG9ydGFudCcpIHtcbiAgICAgICAgICAgICAgICAgICAgbGV0IGNhY2hlID0gdG9rZW5zLnNsaWNlKDApO1xuICAgICAgICAgICAgICAgICAgICBsZXQgc3RyICAgPSAnJztcbiAgICAgICAgICAgICAgICAgICAgZm9yIChsZXQgaiA9IGk7IGogPiAwOyBqLS0pIHtcbiAgICAgICAgICAgICAgICAgICAgICAgIGxldCB0eXBlID0gY2FjaGVbal1bMF07XG4gICAgICAgICAgICAgICAgICAgICAgICBpZiAoc3RyLnRyaW0oKS5pbmRleE9mKCchJykgPT09IDAgJiZcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICB0eXBlICE9PSAnc3BhY2UnXG4gICAgICAgICAgICAgICAgICAgICAgICApIHtcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICBicmVhaztcbiAgICAgICAgICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgICAgICAgICAgICAgIHN0ciA9IGNhY2hlLnBvcCgpWzFdICsgc3RyO1xuICAgICAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgICAgIGlmIChzdHIudHJpbSgpLmluZGV4T2YoJyEnKSA9PT0gMCkge1xuICAgICAgICAgICAgICAgICAgICAgICAgbm9kZS5pbXBvcnRhbnQgPSB0cnVlO1xuICAgICAgICAgICAgICAgICAgICAgICAgbm9kZS5yYXdzLmltcG9ydGFudCA9IHN0cjtcbiAgICAgICAgICAgICAgICAgICAgICAgIHRva2VucyA9IGNhY2hlO1xuICAgICAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICAgICAgaWYgKHRva2VuWzBdICE9PSAnc3BhY2UnICYmIHRva2VuWzBdICE9PSAnY29tbWVudCcpIHtcbiAgICAgICAgICAgICAgICAgICAgYnJlYWs7XG4gICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICB0aGlzLnJhdyhub2RlLCAndmFsdWUnLCB0b2tlbnMpO1xuXG4gICAgICAgICAgICBpZiAobm9kZS52YWx1ZS5pbmRleE9mKCc6JykgIT09IC0xKSB7XG4gICAgICAgICAgICAgICAgdGhpcy5jaGVja01pc3NlZFNlbWljb2xvbih0b2tlbnMpO1xuICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICB0aGlzLmN1cnJlbnQgPSBub2RlO1xuICAgICAgICB9XG4gICAgfVxuXG4gICAgY29tbWVudCh0b2tlbikge1xuICAgICAgICBpZiAodG9rZW5bNl0gPT09ICdpbmxpbmUnKSB7XG4gICAgICAgICAgICBsZXQgbm9kZSA9IG5ldyBDb21tZW50KCk7XG4gICAgICAgICAgICB0aGlzLmluaXQobm9kZSwgdG9rZW5bMl0sIHRva2VuWzNdKTtcbiAgICAgICAgICAgIG5vZGUucmF3cy5pbmxpbmUgPSB0cnVlO1xuICAgICAgICAgICAgbm9kZS5zb3VyY2UuZW5kICA9IHsgbGluZTogdG9rZW5bNF0sIGNvbHVtbjogdG9rZW5bNV0gfTtcblxuICAgICAgICAgICAgbGV0IHRleHQgPSB0b2tlblsxXS5zbGljZSgyKTtcbiAgICAgICAgICAgIGlmICggL15cXHMqJC8udGVzdCh0ZXh0KSApIHtcbiAgICAgICAgICAgICAgICBub2RlLnRleHQgICAgICAgPSAnJztcbiAgICAgICAgICAgICAgICBub2RlLnJhd3MubGVmdCAgPSB0ZXh0O1xuICAgICAgICAgICAgICAgIG5vZGUucmF3cy5yaWdodCA9ICcnO1xuICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICBsZXQgbWF0Y2ggPSB0ZXh0Lm1hdGNoKC9eKFxccyopKFteXSpbXlxcc10pKFxccyopJC8pO1xuICAgICAgICAgICAgICAgIGxldCBmaXhlZCA9IG1hdGNoWzJdLnJlcGxhY2UoLyhcXCpcXC98XFwvXFwqKS9nLCAnKi8vKicpO1xuICAgICAgICAgICAgICAgIG5vZGUudGV4dCAgICAgICA9IGZpeGVkO1xuICAgICAgICAgICAgICAgIG5vZGUucmF3cy5sZWZ0ICA9IG1hdGNoWzFdO1xuICAgICAgICAgICAgICAgIG5vZGUucmF3cy5yaWdodCA9IG1hdGNoWzNdO1xuICAgICAgICAgICAgICAgIG5vZGUucmF3cy50ZXh0ICA9IG1hdGNoWzJdO1xuICAgICAgICAgICAgfVxuICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgc3VwZXIuY29tbWVudCh0b2tlbik7XG4gICAgICAgIH1cbiAgICB9XG5cbiAgICByYXcobm9kZSwgcHJvcCwgdG9rZW5zKSB7XG4gICAgICAgIHN1cGVyLnJhdyhub2RlLCBwcm9wLCB0b2tlbnMpO1xuICAgICAgICBpZiAoIG5vZGUucmF3c1twcm9wXSApIHtcbiAgICAgICAgICAgIGxldCBzY3NzID0gbm9kZS5yYXdzW3Byb3BdLnJhdztcbiAgICAgICAgICAgIG5vZGUucmF3c1twcm9wXS5yYXcgPSB0b2tlbnMucmVkdWNlKCAoYWxsLCBpKSA9PiB7XG4gICAgICAgICAgICAgICAgaWYgKCBpWzBdID09PSAnY29tbWVudCcgJiYgaVs2XSA9PT0gJ2lubGluZScgKSB7XG4gICAgICAgICAgICAgICAgICAgIGxldCB0ZXh0ID0gaVsxXS5zbGljZSgyKS5yZXBsYWNlKC8oXFwqXFwvfFxcL1xcKikvZywgJyovLyonKTtcbiAgICAgICAgICAgICAgICAgICAgcmV0dXJuIGFsbCArICcvKicgKyB0ZXh0ICsgJyovJztcbiAgICAgICAgICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgICAgICAgICByZXR1cm4gYWxsICsgaVsxXTtcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICB9LCAnJyk7XG4gICAgICAgICAgICBpZiAoIHNjc3MgIT09IG5vZGUucmF3c1twcm9wXS5yYXcgKSB7XG4gICAgICAgICAgICAgICAgbm9kZS5yYXdzW3Byb3BdLnNjc3MgPSBzY3NzO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG4gICAgfVxuXG59XG4iXX0=
