'use strict';

exports.__esModule = true;

var _declaration = require('./declaration');

var _declaration2 = _interopRequireDefault(_declaration);

var _tokenize = require('./tokenize');

var _tokenize2 = _interopRequireDefault(_tokenize);

var _comment = require('./comment');

var _comment2 = _interopRequireDefault(_comment);

var _atRule = require('./at-rule');

var _atRule2 = _interopRequireDefault(_atRule);

var _root = require('./root');

var _root2 = _interopRequireDefault(_root);

var _rule = require('./rule');

var _rule2 = _interopRequireDefault(_rule);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Parser = function () {
    function Parser(input) {
        _classCallCheck(this, Parser);

        this.input = input;

        this.root = new _root2.default();
        this.current = this.root;
        this.spaces = '';
        this.semicolon = false;

        this.createTokenizer();
        this.root.source = { input: input, start: { line: 1, column: 1 } };
    }

    Parser.prototype.createTokenizer = function createTokenizer() {
        this.tokenizer = (0, _tokenize2.default)(this.input);
    };

    Parser.prototype.parse = function parse() {
        var token = void 0;
        while (!this.tokenizer.endOfFile()) {
            token = this.tokenizer.nextToken();

            switch (token[0]) {

                case 'space':
                    this.spaces += token[1];
                    break;

                case ';':
                    this.freeSemicolon(token);
                    break;

                case '}':
                    this.end(token);
                    break;

                case 'comment':
                    this.comment(token);
                    break;

                case 'at-word':
                    this.atrule(token);
                    break;

                case '{':
                    this.emptyRule(token);
                    break;

                default:
                    this.other(token);
                    break;
            }
        }
        this.endFile();
    };

    Parser.prototype.comment = function comment(token) {
        var node = new _comment2.default();
        this.init(node, token[2], token[3]);
        node.source.end = { line: token[4], column: token[5] };

        var text = token[1].slice(2, -2);
        if (/^\s*$/.test(text)) {
            node.text = '';
            node.raws.left = text;
            node.raws.right = '';
        } else {
            var match = text.match(/^(\s*)([^]*[^\s])(\s*)$/);
            node.text = match[2];
            node.raws.left = match[1];
            node.raws.right = match[3];
        }
    };

    Parser.prototype.emptyRule = function emptyRule(token) {
        var node = new _rule2.default();
        this.init(node, token[2], token[3]);
        node.selector = '';
        node.raws.between = '';
        this.current = node;
    };

    Parser.prototype.other = function other(start) {
        var end = false;
        var type = null;
        var colon = false;
        var bracket = null;
        var brackets = [];

        var tokens = [];
        var token = start;
        while (token) {
            type = token[0];
            tokens.push(token);

            if (type === '(' || type === '[') {
                if (!bracket) bracket = token;
                brackets.push(type === '(' ? ')' : ']');
            } else if (brackets.length === 0) {
                if (type === ';') {
                    if (colon) {
                        this.decl(tokens);
                        return;
                    } else {
                        break;
                    }
                } else if (type === '{') {
                    this.rule(tokens);
                    return;
                } else if (type === '}') {
                    this.tokenizer.back(tokens.pop());
                    end = true;
                    break;
                } else if (type === ':') {
                    colon = true;
                }
            } else if (type === brackets[brackets.length - 1]) {
                brackets.pop();
                if (brackets.length === 0) bracket = null;
            }

            token = this.tokenizer.nextToken();
        }

        if (this.tokenizer.endOfFile()) end = true;
        if (brackets.length > 0) this.unclosedBracket(bracket);

        if (end && colon) {
            while (tokens.length) {
                token = tokens[tokens.length - 1][0];
                if (token !== 'space' && token !== 'comment') break;
                this.tokenizer.back(tokens.pop());
            }
            this.decl(tokens);
            return;
        } else {
            this.unknownWord(tokens);
        }
    };

    Parser.prototype.rule = function rule(tokens) {
        tokens.pop();

        var node = new _rule2.default();
        this.init(node, tokens[0][2], tokens[0][3]);

        node.raws.between = this.spacesAndCommentsFromEnd(tokens);
        this.raw(node, 'selector', tokens);
        this.current = node;
    };

    Parser.prototype.decl = function decl(tokens) {
        var node = new _declaration2.default();
        this.init(node);

        var last = tokens[tokens.length - 1];
        if (last[0] === ';') {
            this.semicolon = true;
            tokens.pop();
        }
        if (last[4]) {
            node.source.end = { line: last[4], column: last[5] };
        } else {
            node.source.end = { line: last[2], column: last[3] };
        }

        while (tokens[0][0] !== 'word') {
            if (tokens.length === 1) this.unknownWord(tokens);
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

        for (var i = tokens.length - 1; i > 0; i--) {
            token = tokens[i];
            if (token[1].toLowerCase() === '!important') {
                node.important = true;
                var string = this.stringFrom(tokens, i);
                string = this.spacesFromEnd(tokens) + string;
                if (string !== ' !important') node.raws.important = string;
                break;
            } else if (token[1].toLowerCase() === 'important') {
                var cache = tokens.slice(0);
                var str = '';
                for (var j = i; j > 0; j--) {
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

        if (node.value.indexOf(':') !== -1) this.checkMissedSemicolon(tokens);
    };

    Parser.prototype.atrule = function atrule(token) {
        var node = new _atRule2.default();
        node.name = token[1].slice(1);
        if (node.name === '') {
            this.unnamedAtrule(node, token);
        }
        this.init(node, token[2], token[3]);

        var prev = void 0;
        var shift = void 0;
        var last = false;
        var open = false;
        var params = [];

        while (!this.tokenizer.endOfFile()) {
            token = this.tokenizer.nextToken();

            if (token[0] === ';') {
                node.source.end = { line: token[2], column: token[3] };
                this.semicolon = true;
                break;
            } else if (token[0] === '{') {
                open = true;
                break;
            } else if (token[0] === '}') {
                if (params.length > 0) {
                    shift = params.length - 1;
                    prev = params[shift];
                    while (prev && prev[0] === 'space') {
                        prev = params[--shift];
                    }
                    if (prev) {
                        node.source.end = { line: prev[4], column: prev[5] };
                    }
                }
                this.end(token);
                break;
            } else {
                params.push(token);
            }

            if (this.tokenizer.endOfFile()) {
                last = true;
                break;
            }
        }

        node.raws.between = this.spacesAndCommentsFromEnd(params);
        if (params.length) {
            node.raws.afterName = this.spacesAndCommentsFromStart(params);
            this.raw(node, 'params', params);
            if (last) {
                token = params[params.length - 1];
                node.source.end = { line: token[4], column: token[5] };
                this.spaces = node.raws.between;
                node.raws.between = '';
            }
        } else {
            node.raws.afterName = '';
            node.params = '';
        }

        if (open) {
            node.nodes = [];
            this.current = node;
        }
    };

    Parser.prototype.end = function end(token) {
        if (this.current.nodes && this.current.nodes.length) {
            this.current.raws.semicolon = this.semicolon;
        }
        this.semicolon = false;

        this.current.raws.after = (this.current.raws.after || '') + this.spaces;
        this.spaces = '';

        if (this.current.parent) {
            this.current.source.end = { line: token[2], column: token[3] };
            this.current = this.current.parent;
        } else {
            this.unexpectedClose(token);
        }
    };

    Parser.prototype.endFile = function endFile() {
        if (this.current.parent) this.unclosedBlock();
        if (this.current.nodes && this.current.nodes.length) {
            this.current.raws.semicolon = this.semicolon;
        }
        this.current.raws.after = (this.current.raws.after || '') + this.spaces;
    };

    Parser.prototype.freeSemicolon = function freeSemicolon(token) {
        this.spaces += token[1];
        if (this.current.nodes) {
            var prev = this.current.nodes[this.current.nodes.length - 1];
            if (prev && prev.type === 'rule' && !prev.raws.ownSemicolon) {
                prev.raws.ownSemicolon = this.spaces;
                this.spaces = '';
            }
        }
    };

    // Helpers

    Parser.prototype.init = function init(node, line, column) {
        this.current.push(node);

        node.source = { start: { line: line, column: column }, input: this.input };
        node.raws.before = this.spaces;
        this.spaces = '';
        if (node.type !== 'comment') this.semicolon = false;
    };

    Parser.prototype.raw = function raw(node, prop, tokens) {
        var token = void 0,
            type = void 0;
        var length = tokens.length;
        var value = '';
        var clean = true;
        var next = void 0,
            prev = void 0;
        var pattern = /^([.|#])?([\w])+/i;

        for (var i = 0; i < length; i += 1) {
            token = tokens[i];
            type = token[0];

            if (type === 'comment' && node.type === 'rule') {
                prev = tokens[i - 1];
                next = tokens[i + 1];

                if (prev[0] !== 'space' && next[0] !== 'space' && pattern.test(prev[1]) && pattern.test(next[1])) {
                    value += token[1];
                } else {
                    clean = false;
                }

                continue;
            }

            if (type === 'comment' || type === 'space' && i === length - 1) {
                clean = false;
            } else {
                value += token[1];
            }
        }
        if (!clean) {
            var raw = tokens.reduce(function (all, i) {
                return all + i[1];
            }, '');
            node.raws[prop] = { value: value, raw: raw };
        }
        node[prop] = value;
    };

    Parser.prototype.spacesAndCommentsFromEnd = function spacesAndCommentsFromEnd(tokens) {
        var lastTokenType = void 0;
        var spaces = '';
        while (tokens.length) {
            lastTokenType = tokens[tokens.length - 1][0];
            if (lastTokenType !== 'space' && lastTokenType !== 'comment') break;
            spaces = tokens.pop()[1] + spaces;
        }
        return spaces;
    };

    Parser.prototype.spacesAndCommentsFromStart = function spacesAndCommentsFromStart(tokens) {
        var next = void 0;
        var spaces = '';
        while (tokens.length) {
            next = tokens[0][0];
            if (next !== 'space' && next !== 'comment') break;
            spaces += tokens.shift()[1];
        }
        return spaces;
    };

    Parser.prototype.spacesFromEnd = function spacesFromEnd(tokens) {
        var lastTokenType = void 0;
        var spaces = '';
        while (tokens.length) {
            lastTokenType = tokens[tokens.length - 1][0];
            if (lastTokenType !== 'space') break;
            spaces = tokens.pop()[1] + spaces;
        }
        return spaces;
    };

    Parser.prototype.stringFrom = function stringFrom(tokens, from) {
        var result = '';
        for (var i = from; i < tokens.length; i++) {
            result += tokens[i][1];
        }
        tokens.splice(from, tokens.length - from);
        return result;
    };

    Parser.prototype.colon = function colon(tokens) {
        var brackets = 0;
        var token = void 0,
            type = void 0,
            prev = void 0;
        for (var i = 0; i < tokens.length; i++) {
            token = tokens[i];
            type = token[0];

            if (type === '(') {
                brackets += 1;
            } else if (type === ')') {
                brackets -= 1;
            } else if (brackets === 0 && type === ':') {
                if (!prev) {
                    this.doubleColon(token);
                } else if (prev[0] === 'word' && prev[1] === 'progid') {
                    continue;
                } else {
                    return i;
                }
            }

            prev = token;
        }
        return false;
    };

    // Errors

    Parser.prototype.unclosedBracket = function unclosedBracket(bracket) {
        throw this.input.error('Unclosed bracket', bracket[2], bracket[3]);
    };

    Parser.prototype.unknownWord = function unknownWord(tokens) {
        throw this.input.error('Unknown word', tokens[0][2], tokens[0][3]);
    };

    Parser.prototype.unexpectedClose = function unexpectedClose(token) {
        throw this.input.error('Unexpected }', token[2], token[3]);
    };

    Parser.prototype.unclosedBlock = function unclosedBlock() {
        var pos = this.current.source.start;
        throw this.input.error('Unclosed block', pos.line, pos.column);
    };

    Parser.prototype.doubleColon = function doubleColon(token) {
        throw this.input.error('Double colon', token[2], token[3]);
    };

    Parser.prototype.unnamedAtrule = function unnamedAtrule(node, token) {
        throw this.input.error('At-rule without name', token[2], token[3]);
    };

    Parser.prototype.precheckMissedSemicolon = function precheckMissedSemicolon(tokens) {
        // Hook for Safe Parser
        tokens;
    };

    Parser.prototype.checkMissedSemicolon = function checkMissedSemicolon(tokens) {
        var colon = this.colon(tokens);
        if (colon === false) return;

        var founded = 0;
        var token = void 0;
        for (var j = colon - 1; j >= 0; j--) {
            token = tokens[j];
            if (token[0] !== 'space') {
                founded += 1;
                if (founded === 2) break;
            }
        }
        throw this.input.error('Missed semicolon', token[2], token[3]);
    };

    return Parser;
}();

exports.default = Parser;
module.exports = exports['default'];
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInBhcnNlci5lczYiXSwibmFtZXMiOlsiUGFyc2VyIiwiaW5wdXQiLCJyb290IiwiY3VycmVudCIsInNwYWNlcyIsInNlbWljb2xvbiIsImNyZWF0ZVRva2VuaXplciIsInNvdXJjZSIsInN0YXJ0IiwibGluZSIsImNvbHVtbiIsInRva2VuaXplciIsInBhcnNlIiwidG9rZW4iLCJlbmRPZkZpbGUiLCJuZXh0VG9rZW4iLCJmcmVlU2VtaWNvbG9uIiwiZW5kIiwiY29tbWVudCIsImF0cnVsZSIsImVtcHR5UnVsZSIsIm90aGVyIiwiZW5kRmlsZSIsIm5vZGUiLCJpbml0IiwidGV4dCIsInNsaWNlIiwidGVzdCIsInJhd3MiLCJsZWZ0IiwicmlnaHQiLCJtYXRjaCIsInNlbGVjdG9yIiwiYmV0d2VlbiIsInR5cGUiLCJjb2xvbiIsImJyYWNrZXQiLCJicmFja2V0cyIsInRva2VucyIsInB1c2giLCJsZW5ndGgiLCJkZWNsIiwicnVsZSIsImJhY2siLCJwb3AiLCJ1bmNsb3NlZEJyYWNrZXQiLCJ1bmtub3duV29yZCIsInNwYWNlc0FuZENvbW1lbnRzRnJvbUVuZCIsInJhdyIsImxhc3QiLCJiZWZvcmUiLCJzaGlmdCIsInByb3AiLCJzcGFjZXNBbmRDb21tZW50c0Zyb21TdGFydCIsInByZWNoZWNrTWlzc2VkU2VtaWNvbG9uIiwiaSIsInRvTG93ZXJDYXNlIiwiaW1wb3J0YW50Iiwic3RyaW5nIiwic3RyaW5nRnJvbSIsInNwYWNlc0Zyb21FbmQiLCJjYWNoZSIsInN0ciIsImoiLCJ0cmltIiwiaW5kZXhPZiIsInZhbHVlIiwiY2hlY2tNaXNzZWRTZW1pY29sb24iLCJuYW1lIiwidW5uYW1lZEF0cnVsZSIsInByZXYiLCJvcGVuIiwicGFyYW1zIiwiYWZ0ZXJOYW1lIiwibm9kZXMiLCJhZnRlciIsInBhcmVudCIsInVuZXhwZWN0ZWRDbG9zZSIsInVuY2xvc2VkQmxvY2siLCJvd25TZW1pY29sb24iLCJjbGVhbiIsIm5leHQiLCJwYXR0ZXJuIiwicmVkdWNlIiwiYWxsIiwibGFzdFRva2VuVHlwZSIsImZyb20iLCJyZXN1bHQiLCJzcGxpY2UiLCJkb3VibGVDb2xvbiIsImVycm9yIiwicG9zIiwiZm91bmRlZCJdLCJtYXBwaW5ncyI6Ijs7OztBQUFBOzs7O0FBQ0E7Ozs7QUFDQTs7OztBQUNBOzs7O0FBQ0E7Ozs7QUFDQTs7Ozs7Ozs7SUFFcUJBLE07QUFFakIsb0JBQVlDLEtBQVosRUFBbUI7QUFBQTs7QUFDZixhQUFLQSxLQUFMLEdBQWFBLEtBQWI7O0FBRUEsYUFBS0MsSUFBTCxHQUFpQixvQkFBakI7QUFDQSxhQUFLQyxPQUFMLEdBQWlCLEtBQUtELElBQXRCO0FBQ0EsYUFBS0UsTUFBTCxHQUFpQixFQUFqQjtBQUNBLGFBQUtDLFNBQUwsR0FBaUIsS0FBakI7O0FBRUEsYUFBS0MsZUFBTDtBQUNBLGFBQUtKLElBQUwsQ0FBVUssTUFBVixHQUFtQixFQUFFTixZQUFGLEVBQVNPLE9BQU8sRUFBRUMsTUFBTSxDQUFSLEVBQVdDLFFBQVEsQ0FBbkIsRUFBaEIsRUFBbkI7QUFDSDs7cUJBRURKLGUsOEJBQWtCO0FBQ2QsYUFBS0ssU0FBTCxHQUFpQix3QkFBVSxLQUFLVixLQUFmLENBQWpCO0FBQ0gsSzs7cUJBRURXLEssb0JBQVE7QUFDSixZQUFJQyxjQUFKO0FBQ0EsZUFBUSxDQUFDLEtBQUtGLFNBQUwsQ0FBZUcsU0FBZixFQUFULEVBQXNDO0FBQ2xDRCxvQkFBUSxLQUFLRixTQUFMLENBQWVJLFNBQWYsRUFBUjs7QUFFQSxvQkFBU0YsTUFBTSxDQUFOLENBQVQ7O0FBRUEscUJBQUssT0FBTDtBQUNJLHlCQUFLVCxNQUFMLElBQWVTLE1BQU0sQ0FBTixDQUFmO0FBQ0E7O0FBRUoscUJBQUssR0FBTDtBQUNJLHlCQUFLRyxhQUFMLENBQW1CSCxLQUFuQjtBQUNBOztBQUVKLHFCQUFLLEdBQUw7QUFDSSx5QkFBS0ksR0FBTCxDQUFTSixLQUFUO0FBQ0E7O0FBRUoscUJBQUssU0FBTDtBQUNJLHlCQUFLSyxPQUFMLENBQWFMLEtBQWI7QUFDQTs7QUFFSixxQkFBSyxTQUFMO0FBQ0kseUJBQUtNLE1BQUwsQ0FBWU4sS0FBWjtBQUNBOztBQUVKLHFCQUFLLEdBQUw7QUFDSSx5QkFBS08sU0FBTCxDQUFlUCxLQUFmO0FBQ0E7O0FBRUo7QUFDSSx5QkFBS1EsS0FBTCxDQUFXUixLQUFYO0FBQ0E7QUE1Qko7QUE4Qkg7QUFDRCxhQUFLUyxPQUFMO0FBQ0gsSzs7cUJBRURKLE8sb0JBQVFMLEssRUFBTztBQUNYLFlBQUlVLE9BQU8sdUJBQVg7QUFDQSxhQUFLQyxJQUFMLENBQVVELElBQVYsRUFBZ0JWLE1BQU0sQ0FBTixDQUFoQixFQUEwQkEsTUFBTSxDQUFOLENBQTFCO0FBQ0FVLGFBQUtoQixNQUFMLENBQVlVLEdBQVosR0FBa0IsRUFBRVIsTUFBTUksTUFBTSxDQUFOLENBQVIsRUFBa0JILFFBQVFHLE1BQU0sQ0FBTixDQUExQixFQUFsQjs7QUFFQSxZQUFJWSxPQUFPWixNQUFNLENBQU4sRUFBU2EsS0FBVCxDQUFlLENBQWYsRUFBa0IsQ0FBQyxDQUFuQixDQUFYO0FBQ0EsWUFBSyxRQUFRQyxJQUFSLENBQWFGLElBQWIsQ0FBTCxFQUEwQjtBQUN0QkYsaUJBQUtFLElBQUwsR0FBa0IsRUFBbEI7QUFDQUYsaUJBQUtLLElBQUwsQ0FBVUMsSUFBVixHQUFrQkosSUFBbEI7QUFDQUYsaUJBQUtLLElBQUwsQ0FBVUUsS0FBVixHQUFrQixFQUFsQjtBQUNILFNBSkQsTUFJTztBQUNILGdCQUFJQyxRQUFRTixLQUFLTSxLQUFMLENBQVcseUJBQVgsQ0FBWjtBQUNBUixpQkFBS0UsSUFBTCxHQUFrQk0sTUFBTSxDQUFOLENBQWxCO0FBQ0FSLGlCQUFLSyxJQUFMLENBQVVDLElBQVYsR0FBa0JFLE1BQU0sQ0FBTixDQUFsQjtBQUNBUixpQkFBS0ssSUFBTCxDQUFVRSxLQUFWLEdBQWtCQyxNQUFNLENBQU4sQ0FBbEI7QUFDSDtBQUNKLEs7O3FCQUVEWCxTLHNCQUFVUCxLLEVBQU87QUFDYixZQUFJVSxPQUFPLG9CQUFYO0FBQ0EsYUFBS0MsSUFBTCxDQUFVRCxJQUFWLEVBQWdCVixNQUFNLENBQU4sQ0FBaEIsRUFBMEJBLE1BQU0sQ0FBTixDQUExQjtBQUNBVSxhQUFLUyxRQUFMLEdBQWdCLEVBQWhCO0FBQ0FULGFBQUtLLElBQUwsQ0FBVUssT0FBVixHQUFvQixFQUFwQjtBQUNBLGFBQUs5QixPQUFMLEdBQWVvQixJQUFmO0FBQ0gsSzs7cUJBRURGLEssa0JBQU1iLEssRUFBTztBQUNULFlBQUlTLE1BQVcsS0FBZjtBQUNBLFlBQUlpQixPQUFXLElBQWY7QUFDQSxZQUFJQyxRQUFXLEtBQWY7QUFDQSxZQUFJQyxVQUFXLElBQWY7QUFDQSxZQUFJQyxXQUFXLEVBQWY7O0FBRUEsWUFBSUMsU0FBUyxFQUFiO0FBQ0EsWUFBSXpCLFFBQVFMLEtBQVo7QUFDQSxlQUFRSyxLQUFSLEVBQWdCO0FBQ1pxQixtQkFBT3JCLE1BQU0sQ0FBTixDQUFQO0FBQ0F5QixtQkFBT0MsSUFBUCxDQUFZMUIsS0FBWjs7QUFFQSxnQkFBS3FCLFNBQVMsR0FBVCxJQUFnQkEsU0FBUyxHQUE5QixFQUFvQztBQUNoQyxvQkFBSyxDQUFDRSxPQUFOLEVBQWdCQSxVQUFVdkIsS0FBVjtBQUNoQndCLHlCQUFTRSxJQUFULENBQWNMLFNBQVMsR0FBVCxHQUFlLEdBQWYsR0FBcUIsR0FBbkM7QUFFSCxhQUpELE1BSU8sSUFBS0csU0FBU0csTUFBVCxLQUFvQixDQUF6QixFQUE2QjtBQUNoQyxvQkFBS04sU0FBUyxHQUFkLEVBQW9CO0FBQ2hCLHdCQUFLQyxLQUFMLEVBQWE7QUFDVCw2QkFBS00sSUFBTCxDQUFVSCxNQUFWO0FBQ0E7QUFDSCxxQkFIRCxNQUdPO0FBQ0g7QUFDSDtBQUVKLGlCQVJELE1BUU8sSUFBS0osU0FBUyxHQUFkLEVBQW9CO0FBQ3ZCLHlCQUFLUSxJQUFMLENBQVVKLE1BQVY7QUFDQTtBQUVILGlCQUpNLE1BSUEsSUFBS0osU0FBUyxHQUFkLEVBQW9CO0FBQ3ZCLHlCQUFLdkIsU0FBTCxDQUFlZ0MsSUFBZixDQUFvQkwsT0FBT00sR0FBUCxFQUFwQjtBQUNBM0IsMEJBQU0sSUFBTjtBQUNBO0FBRUgsaUJBTE0sTUFLQSxJQUFLaUIsU0FBUyxHQUFkLEVBQW9CO0FBQ3ZCQyw0QkFBUSxJQUFSO0FBQ0g7QUFFSixhQXRCTSxNQXNCQSxJQUFLRCxTQUFTRyxTQUFTQSxTQUFTRyxNQUFULEdBQWtCLENBQTNCLENBQWQsRUFBOEM7QUFDakRILHlCQUFTTyxHQUFUO0FBQ0Esb0JBQUtQLFNBQVNHLE1BQVQsS0FBb0IsQ0FBekIsRUFBNkJKLFVBQVUsSUFBVjtBQUNoQzs7QUFFRHZCLG9CQUFRLEtBQUtGLFNBQUwsQ0FBZUksU0FBZixFQUFSO0FBQ0g7O0FBRUQsWUFBSyxLQUFLSixTQUFMLENBQWVHLFNBQWYsRUFBTCxFQUFrQ0csTUFBTSxJQUFOO0FBQ2xDLFlBQUtvQixTQUFTRyxNQUFULEdBQWtCLENBQXZCLEVBQTJCLEtBQUtLLGVBQUwsQ0FBcUJULE9BQXJCOztBQUUzQixZQUFLbkIsT0FBT2tCLEtBQVosRUFBb0I7QUFDaEIsbUJBQVFHLE9BQU9FLE1BQWYsRUFBd0I7QUFDcEIzQix3QkFBUXlCLE9BQU9BLE9BQU9FLE1BQVAsR0FBZ0IsQ0FBdkIsRUFBMEIsQ0FBMUIsQ0FBUjtBQUNBLG9CQUFLM0IsVUFBVSxPQUFWLElBQXFCQSxVQUFVLFNBQXBDLEVBQWdEO0FBQ2hELHFCQUFLRixTQUFMLENBQWVnQyxJQUFmLENBQW9CTCxPQUFPTSxHQUFQLEVBQXBCO0FBQ0g7QUFDRCxpQkFBS0gsSUFBTCxDQUFVSCxNQUFWO0FBQ0E7QUFDSCxTQVJELE1BUU87QUFDSCxpQkFBS1EsV0FBTCxDQUFpQlIsTUFBakI7QUFDSDtBQUNKLEs7O3FCQUVESSxJLGlCQUFLSixNLEVBQVE7QUFDVEEsZUFBT00sR0FBUDs7QUFFQSxZQUFJckIsT0FBTyxvQkFBWDtBQUNBLGFBQUtDLElBQUwsQ0FBVUQsSUFBVixFQUFnQmUsT0FBTyxDQUFQLEVBQVUsQ0FBVixDQUFoQixFQUE4QkEsT0FBTyxDQUFQLEVBQVUsQ0FBVixDQUE5Qjs7QUFFQWYsYUFBS0ssSUFBTCxDQUFVSyxPQUFWLEdBQW9CLEtBQUtjLHdCQUFMLENBQThCVCxNQUE5QixDQUFwQjtBQUNBLGFBQUtVLEdBQUwsQ0FBU3pCLElBQVQsRUFBZSxVQUFmLEVBQTJCZSxNQUEzQjtBQUNBLGFBQUtuQyxPQUFMLEdBQWVvQixJQUFmO0FBQ0gsSzs7cUJBRURrQixJLGlCQUFLSCxNLEVBQVE7QUFDVCxZQUFJZixPQUFPLDJCQUFYO0FBQ0EsYUFBS0MsSUFBTCxDQUFVRCxJQUFWOztBQUVBLFlBQUkwQixPQUFPWCxPQUFPQSxPQUFPRSxNQUFQLEdBQWdCLENBQXZCLENBQVg7QUFDQSxZQUFLUyxLQUFLLENBQUwsTUFBWSxHQUFqQixFQUF1QjtBQUNuQixpQkFBSzVDLFNBQUwsR0FBaUIsSUFBakI7QUFDQWlDLG1CQUFPTSxHQUFQO0FBQ0g7QUFDRCxZQUFLSyxLQUFLLENBQUwsQ0FBTCxFQUFlO0FBQ1gxQixpQkFBS2hCLE1BQUwsQ0FBWVUsR0FBWixHQUFrQixFQUFFUixNQUFNd0MsS0FBSyxDQUFMLENBQVIsRUFBaUJ2QyxRQUFRdUMsS0FBSyxDQUFMLENBQXpCLEVBQWxCO0FBQ0gsU0FGRCxNQUVPO0FBQ0gxQixpQkFBS2hCLE1BQUwsQ0FBWVUsR0FBWixHQUFrQixFQUFFUixNQUFNd0MsS0FBSyxDQUFMLENBQVIsRUFBaUJ2QyxRQUFRdUMsS0FBSyxDQUFMLENBQXpCLEVBQWxCO0FBQ0g7O0FBRUQsZUFBUVgsT0FBTyxDQUFQLEVBQVUsQ0FBVixNQUFpQixNQUF6QixFQUFrQztBQUM5QixnQkFBS0EsT0FBT0UsTUFBUCxLQUFrQixDQUF2QixFQUEyQixLQUFLTSxXQUFMLENBQWlCUixNQUFqQjtBQUMzQmYsaUJBQUtLLElBQUwsQ0FBVXNCLE1BQVYsSUFBb0JaLE9BQU9hLEtBQVAsR0FBZSxDQUFmLENBQXBCO0FBQ0g7QUFDRDVCLGFBQUtoQixNQUFMLENBQVlDLEtBQVosR0FBb0IsRUFBRUMsTUFBTTZCLE9BQU8sQ0FBUCxFQUFVLENBQVYsQ0FBUixFQUFzQjVCLFFBQVE0QixPQUFPLENBQVAsRUFBVSxDQUFWLENBQTlCLEVBQXBCOztBQUVBZixhQUFLNkIsSUFBTCxHQUFZLEVBQVo7QUFDQSxlQUFRZCxPQUFPRSxNQUFmLEVBQXdCO0FBQ3BCLGdCQUFJTixPQUFPSSxPQUFPLENBQVAsRUFBVSxDQUFWLENBQVg7QUFDQSxnQkFBS0osU0FBUyxHQUFULElBQWdCQSxTQUFTLE9BQXpCLElBQW9DQSxTQUFTLFNBQWxELEVBQThEO0FBQzFEO0FBQ0g7QUFDRFgsaUJBQUs2QixJQUFMLElBQWFkLE9BQU9hLEtBQVAsR0FBZSxDQUFmLENBQWI7QUFDSDs7QUFFRDVCLGFBQUtLLElBQUwsQ0FBVUssT0FBVixHQUFvQixFQUFwQjs7QUFFQSxZQUFJcEIsY0FBSjtBQUNBLGVBQVF5QixPQUFPRSxNQUFmLEVBQXdCO0FBQ3BCM0Isb0JBQVF5QixPQUFPYSxLQUFQLEVBQVI7O0FBRUEsZ0JBQUt0QyxNQUFNLENBQU4sTUFBYSxHQUFsQixFQUF3QjtBQUNwQlUscUJBQUtLLElBQUwsQ0FBVUssT0FBVixJQUFxQnBCLE1BQU0sQ0FBTixDQUFyQjtBQUNBO0FBQ0gsYUFIRCxNQUdPO0FBQ0hVLHFCQUFLSyxJQUFMLENBQVVLLE9BQVYsSUFBcUJwQixNQUFNLENBQU4sQ0FBckI7QUFDSDtBQUNKOztBQUVELFlBQUtVLEtBQUs2QixJQUFMLENBQVUsQ0FBVixNQUFpQixHQUFqQixJQUF3QjdCLEtBQUs2QixJQUFMLENBQVUsQ0FBVixNQUFpQixHQUE5QyxFQUFvRDtBQUNoRDdCLGlCQUFLSyxJQUFMLENBQVVzQixNQUFWLElBQW9CM0IsS0FBSzZCLElBQUwsQ0FBVSxDQUFWLENBQXBCO0FBQ0E3QixpQkFBSzZCLElBQUwsR0FBWTdCLEtBQUs2QixJQUFMLENBQVUxQixLQUFWLENBQWdCLENBQWhCLENBQVo7QUFDSDtBQUNESCxhQUFLSyxJQUFMLENBQVVLLE9BQVYsSUFBcUIsS0FBS29CLDBCQUFMLENBQWdDZixNQUFoQyxDQUFyQjtBQUNBLGFBQUtnQix1QkFBTCxDQUE2QmhCLE1BQTdCOztBQUVBLGFBQU0sSUFBSWlCLElBQUlqQixPQUFPRSxNQUFQLEdBQWdCLENBQTlCLEVBQWlDZSxJQUFJLENBQXJDLEVBQXdDQSxHQUF4QyxFQUE4QztBQUMxQzFDLG9CQUFReUIsT0FBT2lCLENBQVAsQ0FBUjtBQUNBLGdCQUFLMUMsTUFBTSxDQUFOLEVBQVMyQyxXQUFULE9BQTJCLFlBQWhDLEVBQStDO0FBQzNDakMscUJBQUtrQyxTQUFMLEdBQWlCLElBQWpCO0FBQ0Esb0JBQUlDLFNBQVMsS0FBS0MsVUFBTCxDQUFnQnJCLE1BQWhCLEVBQXdCaUIsQ0FBeEIsQ0FBYjtBQUNBRyx5QkFBUyxLQUFLRSxhQUFMLENBQW1CdEIsTUFBbkIsSUFBNkJvQixNQUF0QztBQUNBLG9CQUFLQSxXQUFXLGFBQWhCLEVBQWdDbkMsS0FBS0ssSUFBTCxDQUFVNkIsU0FBVixHQUFzQkMsTUFBdEI7QUFDaEM7QUFFSCxhQVBELE1BT08sSUFBSTdDLE1BQU0sQ0FBTixFQUFTMkMsV0FBVCxPQUEyQixXQUEvQixFQUE0QztBQUMvQyxvQkFBSUssUUFBUXZCLE9BQU9aLEtBQVAsQ0FBYSxDQUFiLENBQVo7QUFDQSxvQkFBSW9DLE1BQVEsRUFBWjtBQUNBLHFCQUFNLElBQUlDLElBQUlSLENBQWQsRUFBaUJRLElBQUksQ0FBckIsRUFBd0JBLEdBQXhCLEVBQThCO0FBQzFCLHdCQUFJN0IsUUFBTzJCLE1BQU1FLENBQU4sRUFBUyxDQUFULENBQVg7QUFDQSx3QkFBS0QsSUFBSUUsSUFBSixHQUFXQyxPQUFYLENBQW1CLEdBQW5CLE1BQTRCLENBQTVCLElBQWlDL0IsVUFBUyxPQUEvQyxFQUF5RDtBQUNyRDtBQUNIO0FBQ0Q0QiwwQkFBTUQsTUFBTWpCLEdBQU4sR0FBWSxDQUFaLElBQWlCa0IsR0FBdkI7QUFDSDtBQUNELG9CQUFLQSxJQUFJRSxJQUFKLEdBQVdDLE9BQVgsQ0FBbUIsR0FBbkIsTUFBNEIsQ0FBakMsRUFBcUM7QUFDakMxQyx5QkFBS2tDLFNBQUwsR0FBaUIsSUFBakI7QUFDQWxDLHlCQUFLSyxJQUFMLENBQVU2QixTQUFWLEdBQXNCSyxHQUF0QjtBQUNBeEIsNkJBQVN1QixLQUFUO0FBQ0g7QUFDSjs7QUFFRCxnQkFBS2hELE1BQU0sQ0FBTixNQUFhLE9BQWIsSUFBd0JBLE1BQU0sQ0FBTixNQUFhLFNBQTFDLEVBQXNEO0FBQ2xEO0FBQ0g7QUFDSjs7QUFFRCxhQUFLbUMsR0FBTCxDQUFTekIsSUFBVCxFQUFlLE9BQWYsRUFBd0JlLE1BQXhCOztBQUVBLFlBQUtmLEtBQUsyQyxLQUFMLENBQVdELE9BQVgsQ0FBbUIsR0FBbkIsTUFBNEIsQ0FBQyxDQUFsQyxFQUFzQyxLQUFLRSxvQkFBTCxDQUEwQjdCLE1BQTFCO0FBQ3pDLEs7O3FCQUVEbkIsTSxtQkFBT04sSyxFQUFPO0FBQ1YsWUFBSVUsT0FBUSxzQkFBWjtBQUNBQSxhQUFLNkMsSUFBTCxHQUFZdkQsTUFBTSxDQUFOLEVBQVNhLEtBQVQsQ0FBZSxDQUFmLENBQVo7QUFDQSxZQUFLSCxLQUFLNkMsSUFBTCxLQUFjLEVBQW5CLEVBQXdCO0FBQ3BCLGlCQUFLQyxhQUFMLENBQW1COUMsSUFBbkIsRUFBeUJWLEtBQXpCO0FBQ0g7QUFDRCxhQUFLVyxJQUFMLENBQVVELElBQVYsRUFBZ0JWLE1BQU0sQ0FBTixDQUFoQixFQUEwQkEsTUFBTSxDQUFOLENBQTFCOztBQUVBLFlBQUl5RCxhQUFKO0FBQ0EsWUFBSW5CLGNBQUo7QUFDQSxZQUFJRixPQUFTLEtBQWI7QUFDQSxZQUFJc0IsT0FBUyxLQUFiO0FBQ0EsWUFBSUMsU0FBUyxFQUFiOztBQUVBLGVBQVEsQ0FBQyxLQUFLN0QsU0FBTCxDQUFlRyxTQUFmLEVBQVQsRUFBc0M7QUFDbENELG9CQUFRLEtBQUtGLFNBQUwsQ0FBZUksU0FBZixFQUFSOztBQUVBLGdCQUFLRixNQUFNLENBQU4sTUFBYSxHQUFsQixFQUF3QjtBQUNwQlUscUJBQUtoQixNQUFMLENBQVlVLEdBQVosR0FBa0IsRUFBRVIsTUFBTUksTUFBTSxDQUFOLENBQVIsRUFBa0JILFFBQVFHLE1BQU0sQ0FBTixDQUExQixFQUFsQjtBQUNBLHFCQUFLUixTQUFMLEdBQWlCLElBQWpCO0FBQ0E7QUFDSCxhQUpELE1BSU8sSUFBS1EsTUFBTSxDQUFOLE1BQWEsR0FBbEIsRUFBd0I7QUFDM0IwRCx1QkFBTyxJQUFQO0FBQ0E7QUFDSCxhQUhNLE1BR0EsSUFBSzFELE1BQU0sQ0FBTixNQUFhLEdBQWxCLEVBQXVCO0FBQzFCLG9CQUFLMkQsT0FBT2hDLE1BQVAsR0FBZ0IsQ0FBckIsRUFBeUI7QUFDckJXLDRCQUFRcUIsT0FBT2hDLE1BQVAsR0FBZ0IsQ0FBeEI7QUFDQThCLDJCQUFPRSxPQUFPckIsS0FBUCxDQUFQO0FBQ0EsMkJBQVFtQixRQUFRQSxLQUFLLENBQUwsTUFBWSxPQUE1QixFQUFzQztBQUNsQ0EsK0JBQU9FLE9BQU8sRUFBRXJCLEtBQVQsQ0FBUDtBQUNIO0FBQ0Qsd0JBQUttQixJQUFMLEVBQVk7QUFDUi9DLDZCQUFLaEIsTUFBTCxDQUFZVSxHQUFaLEdBQWtCLEVBQUVSLE1BQU02RCxLQUFLLENBQUwsQ0FBUixFQUFpQjVELFFBQVE0RCxLQUFLLENBQUwsQ0FBekIsRUFBbEI7QUFDSDtBQUNKO0FBQ0QscUJBQUtyRCxHQUFMLENBQVNKLEtBQVQ7QUFDQTtBQUNILGFBYk0sTUFhQTtBQUNIMkQsdUJBQU9qQyxJQUFQLENBQVkxQixLQUFaO0FBQ0g7O0FBRUQsZ0JBQUssS0FBS0YsU0FBTCxDQUFlRyxTQUFmLEVBQUwsRUFBa0M7QUFDOUJtQyx1QkFBTyxJQUFQO0FBQ0E7QUFDSDtBQUNKOztBQUVEMUIsYUFBS0ssSUFBTCxDQUFVSyxPQUFWLEdBQW9CLEtBQUtjLHdCQUFMLENBQThCeUIsTUFBOUIsQ0FBcEI7QUFDQSxZQUFLQSxPQUFPaEMsTUFBWixFQUFxQjtBQUNqQmpCLGlCQUFLSyxJQUFMLENBQVU2QyxTQUFWLEdBQXNCLEtBQUtwQiwwQkFBTCxDQUFnQ21CLE1BQWhDLENBQXRCO0FBQ0EsaUJBQUt4QixHQUFMLENBQVN6QixJQUFULEVBQWUsUUFBZixFQUF5QmlELE1BQXpCO0FBQ0EsZ0JBQUt2QixJQUFMLEVBQVk7QUFDUnBDLHdCQUFRMkQsT0FBT0EsT0FBT2hDLE1BQVAsR0FBZ0IsQ0FBdkIsQ0FBUjtBQUNBakIscUJBQUtoQixNQUFMLENBQVlVLEdBQVosR0FBb0IsRUFBRVIsTUFBTUksTUFBTSxDQUFOLENBQVIsRUFBa0JILFFBQVFHLE1BQU0sQ0FBTixDQUExQixFQUFwQjtBQUNBLHFCQUFLVCxNQUFMLEdBQW9CbUIsS0FBS0ssSUFBTCxDQUFVSyxPQUE5QjtBQUNBVixxQkFBS0ssSUFBTCxDQUFVSyxPQUFWLEdBQW9CLEVBQXBCO0FBQ0g7QUFDSixTQVRELE1BU087QUFDSFYsaUJBQUtLLElBQUwsQ0FBVTZDLFNBQVYsR0FBc0IsRUFBdEI7QUFDQWxELGlCQUFLaUQsTUFBTCxHQUFzQixFQUF0QjtBQUNIOztBQUVELFlBQUtELElBQUwsRUFBWTtBQUNSaEQsaUJBQUttRCxLQUFMLEdBQWUsRUFBZjtBQUNBLGlCQUFLdkUsT0FBTCxHQUFlb0IsSUFBZjtBQUNIO0FBQ0osSzs7cUJBRUROLEcsZ0JBQUlKLEssRUFBTztBQUNQLFlBQUssS0FBS1YsT0FBTCxDQUFhdUUsS0FBYixJQUFzQixLQUFLdkUsT0FBTCxDQUFhdUUsS0FBYixDQUFtQmxDLE1BQTlDLEVBQXVEO0FBQ25ELGlCQUFLckMsT0FBTCxDQUFheUIsSUFBYixDQUFrQnZCLFNBQWxCLEdBQThCLEtBQUtBLFNBQW5DO0FBQ0g7QUFDRCxhQUFLQSxTQUFMLEdBQWlCLEtBQWpCOztBQUVBLGFBQUtGLE9BQUwsQ0FBYXlCLElBQWIsQ0FBa0IrQyxLQUFsQixHQUEwQixDQUFDLEtBQUt4RSxPQUFMLENBQWF5QixJQUFiLENBQWtCK0MsS0FBbEIsSUFBMkIsRUFBNUIsSUFBa0MsS0FBS3ZFLE1BQWpFO0FBQ0EsYUFBS0EsTUFBTCxHQUFjLEVBQWQ7O0FBRUEsWUFBSyxLQUFLRCxPQUFMLENBQWF5RSxNQUFsQixFQUEyQjtBQUN2QixpQkFBS3pFLE9BQUwsQ0FBYUksTUFBYixDQUFvQlUsR0FBcEIsR0FBMEIsRUFBRVIsTUFBTUksTUFBTSxDQUFOLENBQVIsRUFBa0JILFFBQVFHLE1BQU0sQ0FBTixDQUExQixFQUExQjtBQUNBLGlCQUFLVixPQUFMLEdBQWUsS0FBS0EsT0FBTCxDQUFheUUsTUFBNUI7QUFDSCxTQUhELE1BR087QUFDSCxpQkFBS0MsZUFBTCxDQUFxQmhFLEtBQXJCO0FBQ0g7QUFDSixLOztxQkFFRFMsTyxzQkFBVTtBQUNOLFlBQUssS0FBS25CLE9BQUwsQ0FBYXlFLE1BQWxCLEVBQTJCLEtBQUtFLGFBQUw7QUFDM0IsWUFBSyxLQUFLM0UsT0FBTCxDQUFhdUUsS0FBYixJQUFzQixLQUFLdkUsT0FBTCxDQUFhdUUsS0FBYixDQUFtQmxDLE1BQTlDLEVBQXVEO0FBQ25ELGlCQUFLckMsT0FBTCxDQUFheUIsSUFBYixDQUFrQnZCLFNBQWxCLEdBQThCLEtBQUtBLFNBQW5DO0FBQ0g7QUFDRCxhQUFLRixPQUFMLENBQWF5QixJQUFiLENBQWtCK0MsS0FBbEIsR0FBMEIsQ0FBQyxLQUFLeEUsT0FBTCxDQUFheUIsSUFBYixDQUFrQitDLEtBQWxCLElBQTJCLEVBQTVCLElBQWtDLEtBQUt2RSxNQUFqRTtBQUNILEs7O3FCQUVEWSxhLDBCQUFjSCxLLEVBQU87QUFDakIsYUFBS1QsTUFBTCxJQUFlUyxNQUFNLENBQU4sQ0FBZjtBQUNBLFlBQUssS0FBS1YsT0FBTCxDQUFhdUUsS0FBbEIsRUFBMEI7QUFDdEIsZ0JBQUlKLE9BQU8sS0FBS25FLE9BQUwsQ0FBYXVFLEtBQWIsQ0FBbUIsS0FBS3ZFLE9BQUwsQ0FBYXVFLEtBQWIsQ0FBbUJsQyxNQUFuQixHQUE0QixDQUEvQyxDQUFYO0FBQ0EsZ0JBQUs4QixRQUFRQSxLQUFLcEMsSUFBTCxLQUFjLE1BQXRCLElBQWdDLENBQUNvQyxLQUFLMUMsSUFBTCxDQUFVbUQsWUFBaEQsRUFBK0Q7QUFDM0RULHFCQUFLMUMsSUFBTCxDQUFVbUQsWUFBVixHQUF5QixLQUFLM0UsTUFBOUI7QUFDQSxxQkFBS0EsTUFBTCxHQUFjLEVBQWQ7QUFDSDtBQUNKO0FBQ0osSzs7QUFFRDs7cUJBRUFvQixJLGlCQUFLRCxJLEVBQU1kLEksRUFBTUMsTSxFQUFRO0FBQ3JCLGFBQUtQLE9BQUwsQ0FBYW9DLElBQWIsQ0FBa0JoQixJQUFsQjs7QUFFQUEsYUFBS2hCLE1BQUwsR0FBYyxFQUFFQyxPQUFPLEVBQUVDLFVBQUYsRUFBUUMsY0FBUixFQUFULEVBQTJCVCxPQUFPLEtBQUtBLEtBQXZDLEVBQWQ7QUFDQXNCLGFBQUtLLElBQUwsQ0FBVXNCLE1BQVYsR0FBbUIsS0FBSzlDLE1BQXhCO0FBQ0EsYUFBS0EsTUFBTCxHQUFjLEVBQWQ7QUFDQSxZQUFLbUIsS0FBS1csSUFBTCxLQUFjLFNBQW5CLEVBQStCLEtBQUs3QixTQUFMLEdBQWlCLEtBQWpCO0FBQ2xDLEs7O3FCQUVEMkMsRyxnQkFBSXpCLEksRUFBTTZCLEksRUFBTWQsTSxFQUFRO0FBQ3BCLFlBQUl6QixjQUFKO0FBQUEsWUFBV3FCLGFBQVg7QUFDQSxZQUFJTSxTQUFTRixPQUFPRSxNQUFwQjtBQUNBLFlBQUkwQixRQUFTLEVBQWI7QUFDQSxZQUFJYyxRQUFTLElBQWI7QUFDQSxZQUFJQyxhQUFKO0FBQUEsWUFBVVgsYUFBVjtBQUNBLFlBQU1ZLFVBQVUsbUJBQWhCOztBQUVBLGFBQU0sSUFBSTNCLElBQUksQ0FBZCxFQUFpQkEsSUFBSWYsTUFBckIsRUFBNkJlLEtBQUssQ0FBbEMsRUFBc0M7QUFDbEMxQyxvQkFBUXlCLE9BQU9pQixDQUFQLENBQVI7QUFDQXJCLG1CQUFRckIsTUFBTSxDQUFOLENBQVI7O0FBRUEsZ0JBQUtxQixTQUFTLFNBQVQsSUFBc0JYLEtBQUtXLElBQUwsS0FBYyxNQUF6QyxFQUFrRDtBQUM5Q29DLHVCQUFPaEMsT0FBT2lCLElBQUksQ0FBWCxDQUFQO0FBQ0EwQix1QkFBTzNDLE9BQU9pQixJQUFJLENBQVgsQ0FBUDs7QUFFQSxvQkFDSWUsS0FBSyxDQUFMLE1BQVksT0FBWixJQUNBVyxLQUFLLENBQUwsTUFBWSxPQURaLElBRUFDLFFBQVF2RCxJQUFSLENBQWEyQyxLQUFLLENBQUwsQ0FBYixDQUZBLElBR0FZLFFBQVF2RCxJQUFSLENBQWFzRCxLQUFLLENBQUwsQ0FBYixDQUpKLEVBS0U7QUFDRWYsNkJBQVNyRCxNQUFNLENBQU4sQ0FBVDtBQUNILGlCQVBELE1BT087QUFDSG1FLDRCQUFRLEtBQVI7QUFDSDs7QUFFRDtBQUNIOztBQUVELGdCQUFLOUMsU0FBUyxTQUFULElBQXNCQSxTQUFTLE9BQVQsSUFBb0JxQixNQUFNZixTQUFTLENBQTlELEVBQWtFO0FBQzlEd0Msd0JBQVEsS0FBUjtBQUNILGFBRkQsTUFFTztBQUNIZCx5QkFBU3JELE1BQU0sQ0FBTixDQUFUO0FBQ0g7QUFDSjtBQUNELFlBQUssQ0FBQ21FLEtBQU4sRUFBYztBQUNWLGdCQUFJaEMsTUFBTVYsT0FBTzZDLE1BQVAsQ0FBZSxVQUFDQyxHQUFELEVBQU03QixDQUFOO0FBQUEsdUJBQVk2QixNQUFNN0IsRUFBRSxDQUFGLENBQWxCO0FBQUEsYUFBZixFQUF1QyxFQUF2QyxDQUFWO0FBQ0FoQyxpQkFBS0ssSUFBTCxDQUFVd0IsSUFBVixJQUFrQixFQUFFYyxZQUFGLEVBQVNsQixRQUFULEVBQWxCO0FBQ0g7QUFDRHpCLGFBQUs2QixJQUFMLElBQWFjLEtBQWI7QUFDSCxLOztxQkFFRG5CLHdCLHFDQUF5QlQsTSxFQUFRO0FBQzdCLFlBQUkrQyxzQkFBSjtBQUNBLFlBQUlqRixTQUFTLEVBQWI7QUFDQSxlQUFRa0MsT0FBT0UsTUFBZixFQUF3QjtBQUNwQjZDLDRCQUFnQi9DLE9BQU9BLE9BQU9FLE1BQVAsR0FBZ0IsQ0FBdkIsRUFBMEIsQ0FBMUIsQ0FBaEI7QUFDQSxnQkFBSzZDLGtCQUFrQixPQUFsQixJQUNEQSxrQkFBa0IsU0FEdEIsRUFDa0M7QUFDbENqRixxQkFBU2tDLE9BQU9NLEdBQVAsR0FBYSxDQUFiLElBQWtCeEMsTUFBM0I7QUFDSDtBQUNELGVBQU9BLE1BQVA7QUFDSCxLOztxQkFFRGlELDBCLHVDQUEyQmYsTSxFQUFRO0FBQy9CLFlBQUkyQyxhQUFKO0FBQ0EsWUFBSTdFLFNBQVMsRUFBYjtBQUNBLGVBQVFrQyxPQUFPRSxNQUFmLEVBQXdCO0FBQ3BCeUMsbUJBQU8zQyxPQUFPLENBQVAsRUFBVSxDQUFWLENBQVA7QUFDQSxnQkFBSzJDLFNBQVMsT0FBVCxJQUFvQkEsU0FBUyxTQUFsQyxFQUE4QztBQUM5QzdFLHNCQUFVa0MsT0FBT2EsS0FBUCxHQUFlLENBQWYsQ0FBVjtBQUNIO0FBQ0QsZUFBTy9DLE1BQVA7QUFDSCxLOztxQkFFRHdELGEsMEJBQWN0QixNLEVBQVE7QUFDbEIsWUFBSStDLHNCQUFKO0FBQ0EsWUFBSWpGLFNBQVMsRUFBYjtBQUNBLGVBQVFrQyxPQUFPRSxNQUFmLEVBQXdCO0FBQ3BCNkMsNEJBQWdCL0MsT0FBT0EsT0FBT0UsTUFBUCxHQUFnQixDQUF2QixFQUEwQixDQUExQixDQUFoQjtBQUNBLGdCQUFLNkMsa0JBQWtCLE9BQXZCLEVBQWlDO0FBQ2pDakYscUJBQVNrQyxPQUFPTSxHQUFQLEdBQWEsQ0FBYixJQUFrQnhDLE1BQTNCO0FBQ0g7QUFDRCxlQUFPQSxNQUFQO0FBQ0gsSzs7cUJBRUR1RCxVLHVCQUFXckIsTSxFQUFRZ0QsSSxFQUFNO0FBQ3JCLFlBQUlDLFNBQVMsRUFBYjtBQUNBLGFBQU0sSUFBSWhDLElBQUkrQixJQUFkLEVBQW9CL0IsSUFBSWpCLE9BQU9FLE1BQS9CLEVBQXVDZSxHQUF2QyxFQUE2QztBQUN6Q2dDLHNCQUFVakQsT0FBT2lCLENBQVAsRUFBVSxDQUFWLENBQVY7QUFDSDtBQUNEakIsZUFBT2tELE1BQVAsQ0FBY0YsSUFBZCxFQUFvQmhELE9BQU9FLE1BQVAsR0FBZ0I4QyxJQUFwQztBQUNBLGVBQU9DLE1BQVA7QUFDSCxLOztxQkFFRHBELEssa0JBQU1HLE0sRUFBUTtBQUNWLFlBQUlELFdBQVcsQ0FBZjtBQUNBLFlBQUl4QixjQUFKO0FBQUEsWUFBV3FCLGFBQVg7QUFBQSxZQUFpQm9DLGFBQWpCO0FBQ0EsYUFBTSxJQUFJZixJQUFJLENBQWQsRUFBaUJBLElBQUlqQixPQUFPRSxNQUE1QixFQUFvQ2UsR0FBcEMsRUFBMEM7QUFDdEMxQyxvQkFBUXlCLE9BQU9pQixDQUFQLENBQVI7QUFDQXJCLG1CQUFRckIsTUFBTSxDQUFOLENBQVI7O0FBRUEsZ0JBQUtxQixTQUFTLEdBQWQsRUFBb0I7QUFDaEJHLDRCQUFZLENBQVo7QUFDSCxhQUZELE1BRU8sSUFBS0gsU0FBUyxHQUFkLEVBQW9CO0FBQ3ZCRyw0QkFBWSxDQUFaO0FBQ0gsYUFGTSxNQUVBLElBQUtBLGFBQWEsQ0FBYixJQUFrQkgsU0FBUyxHQUFoQyxFQUFzQztBQUN6QyxvQkFBSyxDQUFDb0MsSUFBTixFQUFhO0FBQ1QseUJBQUttQixXQUFMLENBQWlCNUUsS0FBakI7QUFDSCxpQkFGRCxNQUVPLElBQUt5RCxLQUFLLENBQUwsTUFBWSxNQUFaLElBQXNCQSxLQUFLLENBQUwsTUFBWSxRQUF2QyxFQUFrRDtBQUNyRDtBQUNILGlCQUZNLE1BRUE7QUFDSCwyQkFBT2YsQ0FBUDtBQUNIO0FBQ0o7O0FBRURlLG1CQUFPekQsS0FBUDtBQUNIO0FBQ0QsZUFBTyxLQUFQO0FBQ0gsSzs7QUFFRDs7cUJBRUFnQyxlLDRCQUFnQlQsTyxFQUFTO0FBQ3JCLGNBQU0sS0FBS25DLEtBQUwsQ0FBV3lGLEtBQVgsQ0FBaUIsa0JBQWpCLEVBQXFDdEQsUUFBUSxDQUFSLENBQXJDLEVBQWlEQSxRQUFRLENBQVIsQ0FBakQsQ0FBTjtBQUNILEs7O3FCQUVEVSxXLHdCQUFZUixNLEVBQVE7QUFDaEIsY0FBTSxLQUFLckMsS0FBTCxDQUFXeUYsS0FBWCxDQUFpQixjQUFqQixFQUFpQ3BELE9BQU8sQ0FBUCxFQUFVLENBQVYsQ0FBakMsRUFBK0NBLE9BQU8sQ0FBUCxFQUFVLENBQVYsQ0FBL0MsQ0FBTjtBQUNILEs7O3FCQUVEdUMsZSw0QkFBZ0JoRSxLLEVBQU87QUFDbkIsY0FBTSxLQUFLWixLQUFMLENBQVd5RixLQUFYLENBQWlCLGNBQWpCLEVBQWlDN0UsTUFBTSxDQUFOLENBQWpDLEVBQTJDQSxNQUFNLENBQU4sQ0FBM0MsQ0FBTjtBQUNILEs7O3FCQUVEaUUsYSw0QkFBZ0I7QUFDWixZQUFJYSxNQUFNLEtBQUt4RixPQUFMLENBQWFJLE1BQWIsQ0FBb0JDLEtBQTlCO0FBQ0EsY0FBTSxLQUFLUCxLQUFMLENBQVd5RixLQUFYLENBQWlCLGdCQUFqQixFQUFtQ0MsSUFBSWxGLElBQXZDLEVBQTZDa0YsSUFBSWpGLE1BQWpELENBQU47QUFDSCxLOztxQkFFRCtFLFcsd0JBQVk1RSxLLEVBQU87QUFDZixjQUFNLEtBQUtaLEtBQUwsQ0FBV3lGLEtBQVgsQ0FBaUIsY0FBakIsRUFBaUM3RSxNQUFNLENBQU4sQ0FBakMsRUFBMkNBLE1BQU0sQ0FBTixDQUEzQyxDQUFOO0FBQ0gsSzs7cUJBRUR3RCxhLDBCQUFjOUMsSSxFQUFNVixLLEVBQU87QUFDdkIsY0FBTSxLQUFLWixLQUFMLENBQVd5RixLQUFYLENBQWlCLHNCQUFqQixFQUF5QzdFLE1BQU0sQ0FBTixDQUF6QyxFQUFtREEsTUFBTSxDQUFOLENBQW5ELENBQU47QUFDSCxLOztxQkFFRHlDLHVCLG9DQUF3QmhCLE0sRUFBUTtBQUM1QjtBQUNBQTtBQUNILEs7O3FCQUVENkIsb0IsaUNBQXFCN0IsTSxFQUFRO0FBQ3pCLFlBQUlILFFBQVEsS0FBS0EsS0FBTCxDQUFXRyxNQUFYLENBQVo7QUFDQSxZQUFLSCxVQUFVLEtBQWYsRUFBdUI7O0FBRXZCLFlBQUl5RCxVQUFVLENBQWQ7QUFDQSxZQUFJL0UsY0FBSjtBQUNBLGFBQU0sSUFBSWtELElBQUk1QixRQUFRLENBQXRCLEVBQXlCNEIsS0FBSyxDQUE5QixFQUFpQ0EsR0FBakMsRUFBdUM7QUFDbkNsRCxvQkFBUXlCLE9BQU95QixDQUFQLENBQVI7QUFDQSxnQkFBS2xELE1BQU0sQ0FBTixNQUFhLE9BQWxCLEVBQTRCO0FBQ3hCK0UsMkJBQVcsQ0FBWDtBQUNBLG9CQUFLQSxZQUFZLENBQWpCLEVBQXFCO0FBQ3hCO0FBQ0o7QUFDRCxjQUFNLEtBQUszRixLQUFMLENBQVd5RixLQUFYLENBQWlCLGtCQUFqQixFQUFxQzdFLE1BQU0sQ0FBTixDQUFyQyxFQUErQ0EsTUFBTSxDQUFOLENBQS9DLENBQU47QUFDSCxLOzs7OztrQkFyZ0JnQmIsTSIsImZpbGUiOiJwYXJzZXIuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgRGVjbGFyYXRpb24gZnJvbSAnLi9kZWNsYXJhdGlvbic7XG5pbXBvcnQgdG9rZW5pemVyICAgZnJvbSAnLi90b2tlbml6ZSc7XG5pbXBvcnQgQ29tbWVudCAgICAgZnJvbSAnLi9jb21tZW50JztcbmltcG9ydCBBdFJ1bGUgICAgICBmcm9tICcuL2F0LXJ1bGUnO1xuaW1wb3J0IFJvb3QgICAgICAgIGZyb20gJy4vcm9vdCc7XG5pbXBvcnQgUnVsZSAgICAgICAgZnJvbSAnLi9ydWxlJztcblxuZXhwb3J0IGRlZmF1bHQgY2xhc3MgUGFyc2VyIHtcblxuICAgIGNvbnN0cnVjdG9yKGlucHV0KSB7XG4gICAgICAgIHRoaXMuaW5wdXQgPSBpbnB1dDtcblxuICAgICAgICB0aGlzLnJvb3QgICAgICA9IG5ldyBSb290KCk7XG4gICAgICAgIHRoaXMuY3VycmVudCAgID0gdGhpcy5yb290O1xuICAgICAgICB0aGlzLnNwYWNlcyAgICA9ICcnO1xuICAgICAgICB0aGlzLnNlbWljb2xvbiA9IGZhbHNlO1xuXG4gICAgICAgIHRoaXMuY3JlYXRlVG9rZW5pemVyKCk7XG4gICAgICAgIHRoaXMucm9vdC5zb3VyY2UgPSB7IGlucHV0LCBzdGFydDogeyBsaW5lOiAxLCBjb2x1bW46IDEgfSB9O1xuICAgIH1cblxuICAgIGNyZWF0ZVRva2VuaXplcigpIHtcbiAgICAgICAgdGhpcy50b2tlbml6ZXIgPSB0b2tlbml6ZXIodGhpcy5pbnB1dCk7XG4gICAgfVxuXG4gICAgcGFyc2UoKSB7XG4gICAgICAgIGxldCB0b2tlbjtcbiAgICAgICAgd2hpbGUgKCAhdGhpcy50b2tlbml6ZXIuZW5kT2ZGaWxlKCkgKSB7XG4gICAgICAgICAgICB0b2tlbiA9IHRoaXMudG9rZW5pemVyLm5leHRUb2tlbigpO1xuXG4gICAgICAgICAgICBzd2l0Y2ggKCB0b2tlblswXSApIHtcblxuICAgICAgICAgICAgY2FzZSAnc3BhY2UnOlxuICAgICAgICAgICAgICAgIHRoaXMuc3BhY2VzICs9IHRva2VuWzFdO1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuXG4gICAgICAgICAgICBjYXNlICc7JzpcbiAgICAgICAgICAgICAgICB0aGlzLmZyZWVTZW1pY29sb24odG9rZW4pO1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuXG4gICAgICAgICAgICBjYXNlICd9JzpcbiAgICAgICAgICAgICAgICB0aGlzLmVuZCh0b2tlbik7XG4gICAgICAgICAgICAgICAgYnJlYWs7XG5cbiAgICAgICAgICAgIGNhc2UgJ2NvbW1lbnQnOlxuICAgICAgICAgICAgICAgIHRoaXMuY29tbWVudCh0b2tlbik7XG4gICAgICAgICAgICAgICAgYnJlYWs7XG5cbiAgICAgICAgICAgIGNhc2UgJ2F0LXdvcmQnOlxuICAgICAgICAgICAgICAgIHRoaXMuYXRydWxlKHRva2VuKTtcbiAgICAgICAgICAgICAgICBicmVhaztcblxuICAgICAgICAgICAgY2FzZSAneyc6XG4gICAgICAgICAgICAgICAgdGhpcy5lbXB0eVJ1bGUodG9rZW4pO1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuXG4gICAgICAgICAgICBkZWZhdWx0OlxuICAgICAgICAgICAgICAgIHRoaXMub3RoZXIodG9rZW4pO1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG4gICAgICAgIHRoaXMuZW5kRmlsZSgpO1xuICAgIH1cblxuICAgIGNvbW1lbnQodG9rZW4pIHtcbiAgICAgICAgbGV0IG5vZGUgPSBuZXcgQ29tbWVudCgpO1xuICAgICAgICB0aGlzLmluaXQobm9kZSwgdG9rZW5bMl0sIHRva2VuWzNdKTtcbiAgICAgICAgbm9kZS5zb3VyY2UuZW5kID0geyBsaW5lOiB0b2tlbls0XSwgY29sdW1uOiB0b2tlbls1XSB9O1xuXG4gICAgICAgIGxldCB0ZXh0ID0gdG9rZW5bMV0uc2xpY2UoMiwgLTIpO1xuICAgICAgICBpZiAoIC9eXFxzKiQvLnRlc3QodGV4dCkgKSB7XG4gICAgICAgICAgICBub2RlLnRleHQgICAgICAgPSAnJztcbiAgICAgICAgICAgIG5vZGUucmF3cy5sZWZ0ICA9IHRleHQ7XG4gICAgICAgICAgICBub2RlLnJhd3MucmlnaHQgPSAnJztcbiAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgIGxldCBtYXRjaCA9IHRleHQubWF0Y2goL14oXFxzKikoW15dKlteXFxzXSkoXFxzKikkLyk7XG4gICAgICAgICAgICBub2RlLnRleHQgICAgICAgPSBtYXRjaFsyXTtcbiAgICAgICAgICAgIG5vZGUucmF3cy5sZWZ0ICA9IG1hdGNoWzFdO1xuICAgICAgICAgICAgbm9kZS5yYXdzLnJpZ2h0ID0gbWF0Y2hbM107XG4gICAgICAgIH1cbiAgICB9XG5cbiAgICBlbXB0eVJ1bGUodG9rZW4pIHtcbiAgICAgICAgbGV0IG5vZGUgPSBuZXcgUnVsZSgpO1xuICAgICAgICB0aGlzLmluaXQobm9kZSwgdG9rZW5bMl0sIHRva2VuWzNdKTtcbiAgICAgICAgbm9kZS5zZWxlY3RvciA9ICcnO1xuICAgICAgICBub2RlLnJhd3MuYmV0d2VlbiA9ICcnO1xuICAgICAgICB0aGlzLmN1cnJlbnQgPSBub2RlO1xuICAgIH1cblxuICAgIG90aGVyKHN0YXJ0KSB7XG4gICAgICAgIGxldCBlbmQgICAgICA9IGZhbHNlO1xuICAgICAgICBsZXQgdHlwZSAgICAgPSBudWxsO1xuICAgICAgICBsZXQgY29sb24gICAgPSBmYWxzZTtcbiAgICAgICAgbGV0IGJyYWNrZXQgID0gbnVsbDtcbiAgICAgICAgbGV0IGJyYWNrZXRzID0gW107XG5cbiAgICAgICAgbGV0IHRva2VucyA9IFtdO1xuICAgICAgICBsZXQgdG9rZW4gPSBzdGFydDtcbiAgICAgICAgd2hpbGUgKCB0b2tlbiApIHtcbiAgICAgICAgICAgIHR5cGUgPSB0b2tlblswXTtcbiAgICAgICAgICAgIHRva2Vucy5wdXNoKHRva2VuKTtcblxuICAgICAgICAgICAgaWYgKCB0eXBlID09PSAnKCcgfHwgdHlwZSA9PT0gJ1snICkge1xuICAgICAgICAgICAgICAgIGlmICggIWJyYWNrZXQgKSBicmFja2V0ID0gdG9rZW47XG4gICAgICAgICAgICAgICAgYnJhY2tldHMucHVzaCh0eXBlID09PSAnKCcgPyAnKScgOiAnXScpO1xuXG4gICAgICAgICAgICB9IGVsc2UgaWYgKCBicmFja2V0cy5sZW5ndGggPT09IDAgKSB7XG4gICAgICAgICAgICAgICAgaWYgKCB0eXBlID09PSAnOycgKSB7XG4gICAgICAgICAgICAgICAgICAgIGlmICggY29sb24gKSB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0aGlzLmRlY2wodG9rZW5zKTtcbiAgICAgICAgICAgICAgICAgICAgICAgIHJldHVybjtcbiAgICAgICAgICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgICAgICAgICB9XG5cbiAgICAgICAgICAgICAgICB9IGVsc2UgaWYgKCB0eXBlID09PSAneycgKSB7XG4gICAgICAgICAgICAgICAgICAgIHRoaXMucnVsZSh0b2tlbnMpO1xuICAgICAgICAgICAgICAgICAgICByZXR1cm47XG5cbiAgICAgICAgICAgICAgICB9IGVsc2UgaWYgKCB0eXBlID09PSAnfScgKSB7XG4gICAgICAgICAgICAgICAgICAgIHRoaXMudG9rZW5pemVyLmJhY2sodG9rZW5zLnBvcCgpKTtcbiAgICAgICAgICAgICAgICAgICAgZW5kID0gdHJ1ZTtcbiAgICAgICAgICAgICAgICAgICAgYnJlYWs7XG5cbiAgICAgICAgICAgICAgICB9IGVsc2UgaWYgKCB0eXBlID09PSAnOicgKSB7XG4gICAgICAgICAgICAgICAgICAgIGNvbG9uID0gdHJ1ZTtcbiAgICAgICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIH0gZWxzZSBpZiAoIHR5cGUgPT09IGJyYWNrZXRzW2JyYWNrZXRzLmxlbmd0aCAtIDFdICkge1xuICAgICAgICAgICAgICAgIGJyYWNrZXRzLnBvcCgpO1xuICAgICAgICAgICAgICAgIGlmICggYnJhY2tldHMubGVuZ3RoID09PSAwICkgYnJhY2tldCA9IG51bGw7XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIHRva2VuID0gdGhpcy50b2tlbml6ZXIubmV4dFRva2VuKCk7XG4gICAgICAgIH1cblxuICAgICAgICBpZiAoIHRoaXMudG9rZW5pemVyLmVuZE9mRmlsZSgpICkgZW5kID0gdHJ1ZTtcbiAgICAgICAgaWYgKCBicmFja2V0cy5sZW5ndGggPiAwICkgdGhpcy51bmNsb3NlZEJyYWNrZXQoYnJhY2tldCk7XG5cbiAgICAgICAgaWYgKCBlbmQgJiYgY29sb24gKSB7XG4gICAgICAgICAgICB3aGlsZSAoIHRva2Vucy5sZW5ndGggKSB7XG4gICAgICAgICAgICAgICAgdG9rZW4gPSB0b2tlbnNbdG9rZW5zLmxlbmd0aCAtIDFdWzBdO1xuICAgICAgICAgICAgICAgIGlmICggdG9rZW4gIT09ICdzcGFjZScgJiYgdG9rZW4gIT09ICdjb21tZW50JyApIGJyZWFrO1xuICAgICAgICAgICAgICAgIHRoaXMudG9rZW5pemVyLmJhY2sodG9rZW5zLnBvcCgpKTtcbiAgICAgICAgICAgIH1cbiAgICAgICAgICAgIHRoaXMuZGVjbCh0b2tlbnMpO1xuICAgICAgICAgICAgcmV0dXJuO1xuICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgdGhpcy51bmtub3duV29yZCh0b2tlbnMpO1xuICAgICAgICB9XG4gICAgfVxuXG4gICAgcnVsZSh0b2tlbnMpIHtcbiAgICAgICAgdG9rZW5zLnBvcCgpO1xuXG4gICAgICAgIGxldCBub2RlID0gbmV3IFJ1bGUoKTtcbiAgICAgICAgdGhpcy5pbml0KG5vZGUsIHRva2Vuc1swXVsyXSwgdG9rZW5zWzBdWzNdKTtcblxuICAgICAgICBub2RlLnJhd3MuYmV0d2VlbiA9IHRoaXMuc3BhY2VzQW5kQ29tbWVudHNGcm9tRW5kKHRva2Vucyk7XG4gICAgICAgIHRoaXMucmF3KG5vZGUsICdzZWxlY3RvcicsIHRva2Vucyk7XG4gICAgICAgIHRoaXMuY3VycmVudCA9IG5vZGU7XG4gICAgfVxuXG4gICAgZGVjbCh0b2tlbnMpIHtcbiAgICAgICAgbGV0IG5vZGUgPSBuZXcgRGVjbGFyYXRpb24oKTtcbiAgICAgICAgdGhpcy5pbml0KG5vZGUpO1xuXG4gICAgICAgIGxldCBsYXN0ID0gdG9rZW5zW3Rva2Vucy5sZW5ndGggLSAxXTtcbiAgICAgICAgaWYgKCBsYXN0WzBdID09PSAnOycgKSB7XG4gICAgICAgICAgICB0aGlzLnNlbWljb2xvbiA9IHRydWU7XG4gICAgICAgICAgICB0b2tlbnMucG9wKCk7XG4gICAgICAgIH1cbiAgICAgICAgaWYgKCBsYXN0WzRdICkge1xuICAgICAgICAgICAgbm9kZS5zb3VyY2UuZW5kID0geyBsaW5lOiBsYXN0WzRdLCBjb2x1bW46IGxhc3RbNV0gfTtcbiAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgIG5vZGUuc291cmNlLmVuZCA9IHsgbGluZTogbGFzdFsyXSwgY29sdW1uOiBsYXN0WzNdIH07XG4gICAgICAgIH1cblxuICAgICAgICB3aGlsZSAoIHRva2Vuc1swXVswXSAhPT0gJ3dvcmQnICkge1xuICAgICAgICAgICAgaWYgKCB0b2tlbnMubGVuZ3RoID09PSAxICkgdGhpcy51bmtub3duV29yZCh0b2tlbnMpO1xuICAgICAgICAgICAgbm9kZS5yYXdzLmJlZm9yZSArPSB0b2tlbnMuc2hpZnQoKVsxXTtcbiAgICAgICAgfVxuICAgICAgICBub2RlLnNvdXJjZS5zdGFydCA9IHsgbGluZTogdG9rZW5zWzBdWzJdLCBjb2x1bW46IHRva2Vuc1swXVszXSB9O1xuXG4gICAgICAgIG5vZGUucHJvcCA9ICcnO1xuICAgICAgICB3aGlsZSAoIHRva2Vucy5sZW5ndGggKSB7XG4gICAgICAgICAgICBsZXQgdHlwZSA9IHRva2Vuc1swXVswXTtcbiAgICAgICAgICAgIGlmICggdHlwZSA9PT0gJzonIHx8IHR5cGUgPT09ICdzcGFjZScgfHwgdHlwZSA9PT0gJ2NvbW1lbnQnICkge1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgfVxuICAgICAgICAgICAgbm9kZS5wcm9wICs9IHRva2Vucy5zaGlmdCgpWzFdO1xuICAgICAgICB9XG5cbiAgICAgICAgbm9kZS5yYXdzLmJldHdlZW4gPSAnJztcblxuICAgICAgICBsZXQgdG9rZW47XG4gICAgICAgIHdoaWxlICggdG9rZW5zLmxlbmd0aCApIHtcbiAgICAgICAgICAgIHRva2VuID0gdG9rZW5zLnNoaWZ0KCk7XG5cbiAgICAgICAgICAgIGlmICggdG9rZW5bMF0gPT09ICc6JyApIHtcbiAgICAgICAgICAgICAgICBub2RlLnJhd3MuYmV0d2VlbiArPSB0b2tlblsxXTtcbiAgICAgICAgICAgICAgICBicmVhaztcbiAgICAgICAgICAgIH0gZWxzZSB7XG4gICAgICAgICAgICAgICAgbm9kZS5yYXdzLmJldHdlZW4gKz0gdG9rZW5bMV07XG4gICAgICAgICAgICB9XG4gICAgICAgIH1cblxuICAgICAgICBpZiAoIG5vZGUucHJvcFswXSA9PT0gJ18nIHx8IG5vZGUucHJvcFswXSA9PT0gJyonICkge1xuICAgICAgICAgICAgbm9kZS5yYXdzLmJlZm9yZSArPSBub2RlLnByb3BbMF07XG4gICAgICAgICAgICBub2RlLnByb3AgPSBub2RlLnByb3Auc2xpY2UoMSk7XG4gICAgICAgIH1cbiAgICAgICAgbm9kZS5yYXdzLmJldHdlZW4gKz0gdGhpcy5zcGFjZXNBbmRDb21tZW50c0Zyb21TdGFydCh0b2tlbnMpO1xuICAgICAgICB0aGlzLnByZWNoZWNrTWlzc2VkU2VtaWNvbG9uKHRva2Vucyk7XG5cbiAgICAgICAgZm9yICggbGV0IGkgPSB0b2tlbnMubGVuZ3RoIC0gMTsgaSA+IDA7IGktLSApIHtcbiAgICAgICAgICAgIHRva2VuID0gdG9rZW5zW2ldO1xuICAgICAgICAgICAgaWYgKCB0b2tlblsxXS50b0xvd2VyQ2FzZSgpID09PSAnIWltcG9ydGFudCcgKSB7XG4gICAgICAgICAgICAgICAgbm9kZS5pbXBvcnRhbnQgPSB0cnVlO1xuICAgICAgICAgICAgICAgIGxldCBzdHJpbmcgPSB0aGlzLnN0cmluZ0Zyb20odG9rZW5zLCBpKTtcbiAgICAgICAgICAgICAgICBzdHJpbmcgPSB0aGlzLnNwYWNlc0Zyb21FbmQodG9rZW5zKSArIHN0cmluZztcbiAgICAgICAgICAgICAgICBpZiAoIHN0cmluZyAhPT0gJyAhaW1wb3J0YW50JyApIG5vZGUucmF3cy5pbXBvcnRhbnQgPSBzdHJpbmc7XG4gICAgICAgICAgICAgICAgYnJlYWs7XG5cbiAgICAgICAgICAgIH0gZWxzZSBpZiAodG9rZW5bMV0udG9Mb3dlckNhc2UoKSA9PT0gJ2ltcG9ydGFudCcpIHtcbiAgICAgICAgICAgICAgICBsZXQgY2FjaGUgPSB0b2tlbnMuc2xpY2UoMCk7XG4gICAgICAgICAgICAgICAgbGV0IHN0ciAgID0gJyc7XG4gICAgICAgICAgICAgICAgZm9yICggbGV0IGogPSBpOyBqID4gMDsgai0tICkge1xuICAgICAgICAgICAgICAgICAgICBsZXQgdHlwZSA9IGNhY2hlW2pdWzBdO1xuICAgICAgICAgICAgICAgICAgICBpZiAoIHN0ci50cmltKCkuaW5kZXhPZignIScpID09PSAwICYmIHR5cGUgIT09ICdzcGFjZScgKSB7XG4gICAgICAgICAgICAgICAgICAgICAgICBicmVhaztcbiAgICAgICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgICAgICAgICBzdHIgPSBjYWNoZS5wb3AoKVsxXSArIHN0cjtcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgaWYgKCBzdHIudHJpbSgpLmluZGV4T2YoJyEnKSA9PT0gMCApIHtcbiAgICAgICAgICAgICAgICAgICAgbm9kZS5pbXBvcnRhbnQgPSB0cnVlO1xuICAgICAgICAgICAgICAgICAgICBub2RlLnJhd3MuaW1wb3J0YW50ID0gc3RyO1xuICAgICAgICAgICAgICAgICAgICB0b2tlbnMgPSBjYWNoZTtcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIGlmICggdG9rZW5bMF0gIT09ICdzcGFjZScgJiYgdG9rZW5bMF0gIT09ICdjb21tZW50JyApIHtcbiAgICAgICAgICAgICAgICBicmVhaztcbiAgICAgICAgICAgIH1cbiAgICAgICAgfVxuXG4gICAgICAgIHRoaXMucmF3KG5vZGUsICd2YWx1ZScsIHRva2Vucyk7XG5cbiAgICAgICAgaWYgKCBub2RlLnZhbHVlLmluZGV4T2YoJzonKSAhPT0gLTEgKSB0aGlzLmNoZWNrTWlzc2VkU2VtaWNvbG9uKHRva2Vucyk7XG4gICAgfVxuXG4gICAgYXRydWxlKHRva2VuKSB7XG4gICAgICAgIGxldCBub2RlICA9IG5ldyBBdFJ1bGUoKTtcbiAgICAgICAgbm9kZS5uYW1lID0gdG9rZW5bMV0uc2xpY2UoMSk7XG4gICAgICAgIGlmICggbm9kZS5uYW1lID09PSAnJyApIHtcbiAgICAgICAgICAgIHRoaXMudW5uYW1lZEF0cnVsZShub2RlLCB0b2tlbik7XG4gICAgICAgIH1cbiAgICAgICAgdGhpcy5pbml0KG5vZGUsIHRva2VuWzJdLCB0b2tlblszXSk7XG5cbiAgICAgICAgbGV0IHByZXY7XG4gICAgICAgIGxldCBzaGlmdDtcbiAgICAgICAgbGV0IGxhc3QgICA9IGZhbHNlO1xuICAgICAgICBsZXQgb3BlbiAgID0gZmFsc2U7XG4gICAgICAgIGxldCBwYXJhbXMgPSBbXTtcblxuICAgICAgICB3aGlsZSAoICF0aGlzLnRva2VuaXplci5lbmRPZkZpbGUoKSApIHtcbiAgICAgICAgICAgIHRva2VuID0gdGhpcy50b2tlbml6ZXIubmV4dFRva2VuKCk7XG5cbiAgICAgICAgICAgIGlmICggdG9rZW5bMF0gPT09ICc7JyApIHtcbiAgICAgICAgICAgICAgICBub2RlLnNvdXJjZS5lbmQgPSB7IGxpbmU6IHRva2VuWzJdLCBjb2x1bW46IHRva2VuWzNdIH07XG4gICAgICAgICAgICAgICAgdGhpcy5zZW1pY29sb24gPSB0cnVlO1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgfSBlbHNlIGlmICggdG9rZW5bMF0gPT09ICd7JyApIHtcbiAgICAgICAgICAgICAgICBvcGVuID0gdHJ1ZTtcbiAgICAgICAgICAgICAgICBicmVhaztcbiAgICAgICAgICAgIH0gZWxzZSBpZiAoIHRva2VuWzBdID09PSAnfScpIHtcbiAgICAgICAgICAgICAgICBpZiAoIHBhcmFtcy5sZW5ndGggPiAwICkge1xuICAgICAgICAgICAgICAgICAgICBzaGlmdCA9IHBhcmFtcy5sZW5ndGggLSAxO1xuICAgICAgICAgICAgICAgICAgICBwcmV2ID0gcGFyYW1zW3NoaWZ0XTtcbiAgICAgICAgICAgICAgICAgICAgd2hpbGUgKCBwcmV2ICYmIHByZXZbMF0gPT09ICdzcGFjZScgKSB7XG4gICAgICAgICAgICAgICAgICAgICAgICBwcmV2ID0gcGFyYW1zWy0tc2hpZnRdO1xuICAgICAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgICAgIGlmICggcHJldiApIHtcbiAgICAgICAgICAgICAgICAgICAgICAgIG5vZGUuc291cmNlLmVuZCA9IHsgbGluZTogcHJldls0XSwgY29sdW1uOiBwcmV2WzVdIH07XG4gICAgICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgdGhpcy5lbmQodG9rZW4pO1xuICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICBwYXJhbXMucHVzaCh0b2tlbik7XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIGlmICggdGhpcy50b2tlbml6ZXIuZW5kT2ZGaWxlKCkgKSB7XG4gICAgICAgICAgICAgICAgbGFzdCA9IHRydWU7XG4gICAgICAgICAgICAgICAgYnJlYWs7XG4gICAgICAgICAgICB9XG4gICAgICAgIH1cblxuICAgICAgICBub2RlLnJhd3MuYmV0d2VlbiA9IHRoaXMuc3BhY2VzQW5kQ29tbWVudHNGcm9tRW5kKHBhcmFtcyk7XG4gICAgICAgIGlmICggcGFyYW1zLmxlbmd0aCApIHtcbiAgICAgICAgICAgIG5vZGUucmF3cy5hZnRlck5hbWUgPSB0aGlzLnNwYWNlc0FuZENvbW1lbnRzRnJvbVN0YXJ0KHBhcmFtcyk7XG4gICAgICAgICAgICB0aGlzLnJhdyhub2RlLCAncGFyYW1zJywgcGFyYW1zKTtcbiAgICAgICAgICAgIGlmICggbGFzdCApIHtcbiAgICAgICAgICAgICAgICB0b2tlbiA9IHBhcmFtc1twYXJhbXMubGVuZ3RoIC0gMV07XG4gICAgICAgICAgICAgICAgbm9kZS5zb3VyY2UuZW5kICAgPSB7IGxpbmU6IHRva2VuWzRdLCBjb2x1bW46IHRva2VuWzVdIH07XG4gICAgICAgICAgICAgICAgdGhpcy5zcGFjZXMgICAgICAgPSBub2RlLnJhd3MuYmV0d2VlbjtcbiAgICAgICAgICAgICAgICBub2RlLnJhd3MuYmV0d2VlbiA9ICcnO1xuICAgICAgICAgICAgfVxuICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgbm9kZS5yYXdzLmFmdGVyTmFtZSA9ICcnO1xuICAgICAgICAgICAgbm9kZS5wYXJhbXMgICAgICAgICA9ICcnO1xuICAgICAgICB9XG5cbiAgICAgICAgaWYgKCBvcGVuICkge1xuICAgICAgICAgICAgbm9kZS5ub2RlcyAgID0gW107XG4gICAgICAgICAgICB0aGlzLmN1cnJlbnQgPSBub2RlO1xuICAgICAgICB9XG4gICAgfVxuXG4gICAgZW5kKHRva2VuKSB7XG4gICAgICAgIGlmICggdGhpcy5jdXJyZW50Lm5vZGVzICYmIHRoaXMuY3VycmVudC5ub2Rlcy5sZW5ndGggKSB7XG4gICAgICAgICAgICB0aGlzLmN1cnJlbnQucmF3cy5zZW1pY29sb24gPSB0aGlzLnNlbWljb2xvbjtcbiAgICAgICAgfVxuICAgICAgICB0aGlzLnNlbWljb2xvbiA9IGZhbHNlO1xuXG4gICAgICAgIHRoaXMuY3VycmVudC5yYXdzLmFmdGVyID0gKHRoaXMuY3VycmVudC5yYXdzLmFmdGVyIHx8ICcnKSArIHRoaXMuc3BhY2VzO1xuICAgICAgICB0aGlzLnNwYWNlcyA9ICcnO1xuXG4gICAgICAgIGlmICggdGhpcy5jdXJyZW50LnBhcmVudCApIHtcbiAgICAgICAgICAgIHRoaXMuY3VycmVudC5zb3VyY2UuZW5kID0geyBsaW5lOiB0b2tlblsyXSwgY29sdW1uOiB0b2tlblszXSB9O1xuICAgICAgICAgICAgdGhpcy5jdXJyZW50ID0gdGhpcy5jdXJyZW50LnBhcmVudDtcbiAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgIHRoaXMudW5leHBlY3RlZENsb3NlKHRva2VuKTtcbiAgICAgICAgfVxuICAgIH1cblxuICAgIGVuZEZpbGUoKSB7XG4gICAgICAgIGlmICggdGhpcy5jdXJyZW50LnBhcmVudCApIHRoaXMudW5jbG9zZWRCbG9jaygpO1xuICAgICAgICBpZiAoIHRoaXMuY3VycmVudC5ub2RlcyAmJiB0aGlzLmN1cnJlbnQubm9kZXMubGVuZ3RoICkge1xuICAgICAgICAgICAgdGhpcy5jdXJyZW50LnJhd3Muc2VtaWNvbG9uID0gdGhpcy5zZW1pY29sb247XG4gICAgICAgIH1cbiAgICAgICAgdGhpcy5jdXJyZW50LnJhd3MuYWZ0ZXIgPSAodGhpcy5jdXJyZW50LnJhd3MuYWZ0ZXIgfHwgJycpICsgdGhpcy5zcGFjZXM7XG4gICAgfVxuXG4gICAgZnJlZVNlbWljb2xvbih0b2tlbikge1xuICAgICAgICB0aGlzLnNwYWNlcyArPSB0b2tlblsxXTtcbiAgICAgICAgaWYgKCB0aGlzLmN1cnJlbnQubm9kZXMgKSB7XG4gICAgICAgICAgICBsZXQgcHJldiA9IHRoaXMuY3VycmVudC5ub2Rlc1t0aGlzLmN1cnJlbnQubm9kZXMubGVuZ3RoIC0gMV07XG4gICAgICAgICAgICBpZiAoIHByZXYgJiYgcHJldi50eXBlID09PSAncnVsZScgJiYgIXByZXYucmF3cy5vd25TZW1pY29sb24gKSB7XG4gICAgICAgICAgICAgICAgcHJldi5yYXdzLm93blNlbWljb2xvbiA9IHRoaXMuc3BhY2VzO1xuICAgICAgICAgICAgICAgIHRoaXMuc3BhY2VzID0gJyc7XG4gICAgICAgICAgICB9XG4gICAgICAgIH1cbiAgICB9XG5cbiAgICAvLyBIZWxwZXJzXG5cbiAgICBpbml0KG5vZGUsIGxpbmUsIGNvbHVtbikge1xuICAgICAgICB0aGlzLmN1cnJlbnQucHVzaChub2RlKTtcblxuICAgICAgICBub2RlLnNvdXJjZSA9IHsgc3RhcnQ6IHsgbGluZSwgY29sdW1uIH0sIGlucHV0OiB0aGlzLmlucHV0IH07XG4gICAgICAgIG5vZGUucmF3cy5iZWZvcmUgPSB0aGlzLnNwYWNlcztcbiAgICAgICAgdGhpcy5zcGFjZXMgPSAnJztcbiAgICAgICAgaWYgKCBub2RlLnR5cGUgIT09ICdjb21tZW50JyApIHRoaXMuc2VtaWNvbG9uID0gZmFsc2U7XG4gICAgfVxuXG4gICAgcmF3KG5vZGUsIHByb3AsIHRva2Vucykge1xuICAgICAgICBsZXQgdG9rZW4sIHR5cGU7XG4gICAgICAgIGxldCBsZW5ndGggPSB0b2tlbnMubGVuZ3RoO1xuICAgICAgICBsZXQgdmFsdWUgID0gJyc7XG4gICAgICAgIGxldCBjbGVhbiAgPSB0cnVlO1xuICAgICAgICBsZXQgbmV4dCwgcHJldjtcbiAgICAgICAgY29uc3QgcGF0dGVybiA9IC9eKFsufCNdKT8oW1xcd10pKy9pO1xuXG4gICAgICAgIGZvciAoIGxldCBpID0gMDsgaSA8IGxlbmd0aDsgaSArPSAxICkge1xuICAgICAgICAgICAgdG9rZW4gPSB0b2tlbnNbaV07XG4gICAgICAgICAgICB0eXBlICA9IHRva2VuWzBdO1xuXG4gICAgICAgICAgICBpZiAoIHR5cGUgPT09ICdjb21tZW50JyAmJiBub2RlLnR5cGUgPT09ICdydWxlJyApIHtcbiAgICAgICAgICAgICAgICBwcmV2ID0gdG9rZW5zW2kgLSAxXTtcbiAgICAgICAgICAgICAgICBuZXh0ID0gdG9rZW5zW2kgKyAxXTtcblxuICAgICAgICAgICAgICAgIGlmIChcbiAgICAgICAgICAgICAgICAgICAgcHJldlswXSAhPT0gJ3NwYWNlJyAmJlxuICAgICAgICAgICAgICAgICAgICBuZXh0WzBdICE9PSAnc3BhY2UnICYmXG4gICAgICAgICAgICAgICAgICAgIHBhdHRlcm4udGVzdChwcmV2WzFdKSAmJlxuICAgICAgICAgICAgICAgICAgICBwYXR0ZXJuLnRlc3QobmV4dFsxXSlcbiAgICAgICAgICAgICAgICApIHtcbiAgICAgICAgICAgICAgICAgICAgdmFsdWUgKz0gdG9rZW5bMV07XG4gICAgICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICAgICAgY2xlYW4gPSBmYWxzZTtcbiAgICAgICAgICAgICAgICB9XG5cbiAgICAgICAgICAgICAgICBjb250aW51ZTtcbiAgICAgICAgICAgIH1cblxuICAgICAgICAgICAgaWYgKCB0eXBlID09PSAnY29tbWVudCcgfHwgdHlwZSA9PT0gJ3NwYWNlJyAmJiBpID09PSBsZW5ndGggLSAxICkge1xuICAgICAgICAgICAgICAgIGNsZWFuID0gZmFsc2U7XG4gICAgICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgICAgIHZhbHVlICs9IHRva2VuWzFdO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG4gICAgICAgIGlmICggIWNsZWFuICkge1xuICAgICAgICAgICAgbGV0IHJhdyA9IHRva2Vucy5yZWR1Y2UoIChhbGwsIGkpID0+IGFsbCArIGlbMV0sICcnKTtcbiAgICAgICAgICAgIG5vZGUucmF3c1twcm9wXSA9IHsgdmFsdWUsIHJhdyB9O1xuICAgICAgICB9XG4gICAgICAgIG5vZGVbcHJvcF0gPSB2YWx1ZTtcbiAgICB9XG5cbiAgICBzcGFjZXNBbmRDb21tZW50c0Zyb21FbmQodG9rZW5zKSB7XG4gICAgICAgIGxldCBsYXN0VG9rZW5UeXBlO1xuICAgICAgICBsZXQgc3BhY2VzID0gJyc7XG4gICAgICAgIHdoaWxlICggdG9rZW5zLmxlbmd0aCApIHtcbiAgICAgICAgICAgIGxhc3RUb2tlblR5cGUgPSB0b2tlbnNbdG9rZW5zLmxlbmd0aCAtIDFdWzBdO1xuICAgICAgICAgICAgaWYgKCBsYXN0VG9rZW5UeXBlICE9PSAnc3BhY2UnICYmXG4gICAgICAgICAgICAgICAgbGFzdFRva2VuVHlwZSAhPT0gJ2NvbW1lbnQnICkgYnJlYWs7XG4gICAgICAgICAgICBzcGFjZXMgPSB0b2tlbnMucG9wKClbMV0gKyBzcGFjZXM7XG4gICAgICAgIH1cbiAgICAgICAgcmV0dXJuIHNwYWNlcztcbiAgICB9XG5cbiAgICBzcGFjZXNBbmRDb21tZW50c0Zyb21TdGFydCh0b2tlbnMpIHtcbiAgICAgICAgbGV0IG5leHQ7XG4gICAgICAgIGxldCBzcGFjZXMgPSAnJztcbiAgICAgICAgd2hpbGUgKCB0b2tlbnMubGVuZ3RoICkge1xuICAgICAgICAgICAgbmV4dCA9IHRva2Vuc1swXVswXTtcbiAgICAgICAgICAgIGlmICggbmV4dCAhPT0gJ3NwYWNlJyAmJiBuZXh0ICE9PSAnY29tbWVudCcgKSBicmVhaztcbiAgICAgICAgICAgIHNwYWNlcyArPSB0b2tlbnMuc2hpZnQoKVsxXTtcbiAgICAgICAgfVxuICAgICAgICByZXR1cm4gc3BhY2VzO1xuICAgIH1cblxuICAgIHNwYWNlc0Zyb21FbmQodG9rZW5zKSB7XG4gICAgICAgIGxldCBsYXN0VG9rZW5UeXBlO1xuICAgICAgICBsZXQgc3BhY2VzID0gJyc7XG4gICAgICAgIHdoaWxlICggdG9rZW5zLmxlbmd0aCApIHtcbiAgICAgICAgICAgIGxhc3RUb2tlblR5cGUgPSB0b2tlbnNbdG9rZW5zLmxlbmd0aCAtIDFdWzBdO1xuICAgICAgICAgICAgaWYgKCBsYXN0VG9rZW5UeXBlICE9PSAnc3BhY2UnICkgYnJlYWs7XG4gICAgICAgICAgICBzcGFjZXMgPSB0b2tlbnMucG9wKClbMV0gKyBzcGFjZXM7XG4gICAgICAgIH1cbiAgICAgICAgcmV0dXJuIHNwYWNlcztcbiAgICB9XG5cbiAgICBzdHJpbmdGcm9tKHRva2VucywgZnJvbSkge1xuICAgICAgICBsZXQgcmVzdWx0ID0gJyc7XG4gICAgICAgIGZvciAoIGxldCBpID0gZnJvbTsgaSA8IHRva2Vucy5sZW5ndGg7IGkrKyApIHtcbiAgICAgICAgICAgIHJlc3VsdCArPSB0b2tlbnNbaV1bMV07XG4gICAgICAgIH1cbiAgICAgICAgdG9rZW5zLnNwbGljZShmcm9tLCB0b2tlbnMubGVuZ3RoIC0gZnJvbSk7XG4gICAgICAgIHJldHVybiByZXN1bHQ7XG4gICAgfVxuXG4gICAgY29sb24odG9rZW5zKSB7XG4gICAgICAgIGxldCBicmFja2V0cyA9IDA7XG4gICAgICAgIGxldCB0b2tlbiwgdHlwZSwgcHJldjtcbiAgICAgICAgZm9yICggbGV0IGkgPSAwOyBpIDwgdG9rZW5zLmxlbmd0aDsgaSsrICkge1xuICAgICAgICAgICAgdG9rZW4gPSB0b2tlbnNbaV07XG4gICAgICAgICAgICB0eXBlICA9IHRva2VuWzBdO1xuXG4gICAgICAgICAgICBpZiAoIHR5cGUgPT09ICcoJyApIHtcbiAgICAgICAgICAgICAgICBicmFja2V0cyArPSAxO1xuICAgICAgICAgICAgfSBlbHNlIGlmICggdHlwZSA9PT0gJyknICkge1xuICAgICAgICAgICAgICAgIGJyYWNrZXRzIC09IDE7XG4gICAgICAgICAgICB9IGVsc2UgaWYgKCBicmFja2V0cyA9PT0gMCAmJiB0eXBlID09PSAnOicgKSB7XG4gICAgICAgICAgICAgICAgaWYgKCAhcHJldiApIHtcbiAgICAgICAgICAgICAgICAgICAgdGhpcy5kb3VibGVDb2xvbih0b2tlbik7XG4gICAgICAgICAgICAgICAgfSBlbHNlIGlmICggcHJldlswXSA9PT0gJ3dvcmQnICYmIHByZXZbMV0gPT09ICdwcm9naWQnICkge1xuICAgICAgICAgICAgICAgICAgICBjb250aW51ZTtcbiAgICAgICAgICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgICAgICAgICByZXR1cm4gaTtcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIHByZXYgPSB0b2tlbjtcbiAgICAgICAgfVxuICAgICAgICByZXR1cm4gZmFsc2U7XG4gICAgfVxuXG4gICAgLy8gRXJyb3JzXG5cbiAgICB1bmNsb3NlZEJyYWNrZXQoYnJhY2tldCkge1xuICAgICAgICB0aHJvdyB0aGlzLmlucHV0LmVycm9yKCdVbmNsb3NlZCBicmFja2V0JywgYnJhY2tldFsyXSwgYnJhY2tldFszXSk7XG4gICAgfVxuXG4gICAgdW5rbm93bldvcmQodG9rZW5zKSB7XG4gICAgICAgIHRocm93IHRoaXMuaW5wdXQuZXJyb3IoJ1Vua25vd24gd29yZCcsIHRva2Vuc1swXVsyXSwgdG9rZW5zWzBdWzNdKTtcbiAgICB9XG5cbiAgICB1bmV4cGVjdGVkQ2xvc2UodG9rZW4pIHtcbiAgICAgICAgdGhyb3cgdGhpcy5pbnB1dC5lcnJvcignVW5leHBlY3RlZCB9JywgdG9rZW5bMl0sIHRva2VuWzNdKTtcbiAgICB9XG5cbiAgICB1bmNsb3NlZEJsb2NrKCkge1xuICAgICAgICBsZXQgcG9zID0gdGhpcy5jdXJyZW50LnNvdXJjZS5zdGFydDtcbiAgICAgICAgdGhyb3cgdGhpcy5pbnB1dC5lcnJvcignVW5jbG9zZWQgYmxvY2snLCBwb3MubGluZSwgcG9zLmNvbHVtbik7XG4gICAgfVxuXG4gICAgZG91YmxlQ29sb24odG9rZW4pIHtcbiAgICAgICAgdGhyb3cgdGhpcy5pbnB1dC5lcnJvcignRG91YmxlIGNvbG9uJywgdG9rZW5bMl0sIHRva2VuWzNdKTtcbiAgICB9XG5cbiAgICB1bm5hbWVkQXRydWxlKG5vZGUsIHRva2VuKSB7XG4gICAgICAgIHRocm93IHRoaXMuaW5wdXQuZXJyb3IoJ0F0LXJ1bGUgd2l0aG91dCBuYW1lJywgdG9rZW5bMl0sIHRva2VuWzNdKTtcbiAgICB9XG5cbiAgICBwcmVjaGVja01pc3NlZFNlbWljb2xvbih0b2tlbnMpIHtcbiAgICAgICAgLy8gSG9vayBmb3IgU2FmZSBQYXJzZXJcbiAgICAgICAgdG9rZW5zO1xuICAgIH1cblxuICAgIGNoZWNrTWlzc2VkU2VtaWNvbG9uKHRva2Vucykge1xuICAgICAgICBsZXQgY29sb24gPSB0aGlzLmNvbG9uKHRva2Vucyk7XG4gICAgICAgIGlmICggY29sb24gPT09IGZhbHNlICkgcmV0dXJuO1xuXG4gICAgICAgIGxldCBmb3VuZGVkID0gMDtcbiAgICAgICAgbGV0IHRva2VuO1xuICAgICAgICBmb3IgKCBsZXQgaiA9IGNvbG9uIC0gMTsgaiA+PSAwOyBqLS0gKSB7XG4gICAgICAgICAgICB0b2tlbiA9IHRva2Vuc1tqXTtcbiAgICAgICAgICAgIGlmICggdG9rZW5bMF0gIT09ICdzcGFjZScgKSB7XG4gICAgICAgICAgICAgICAgZm91bmRlZCArPSAxO1xuICAgICAgICAgICAgICAgIGlmICggZm91bmRlZCA9PT0gMiApIGJyZWFrO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG4gICAgICAgIHRocm93IHRoaXMuaW5wdXQuZXJyb3IoJ01pc3NlZCBzZW1pY29sb24nLCB0b2tlblsyXSwgdG9rZW5bM10pO1xuICAgIH1cblxufVxuIl19
