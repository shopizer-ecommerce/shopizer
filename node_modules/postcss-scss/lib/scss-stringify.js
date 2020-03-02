'use strict';

exports.__esModule = true;
exports.default = scssStringify;

var _scssStringifier = require('./scss-stringifier');

var _scssStringifier2 = _interopRequireDefault(_scssStringifier);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function scssStringify(node, builder) {
    var str = new _scssStringifier2.default(builder);
    str.stringify(node);
}
module.exports = exports['default'];
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNjc3Mtc3RyaW5naWZ5LmVzNiJdLCJuYW1lcyI6WyJzY3NzU3RyaW5naWZ5Iiwibm9kZSIsImJ1aWxkZXIiLCJzdHIiLCJTY3NzU3RyaW5naWZpZXIiLCJzdHJpbmdpZnkiXSwibWFwcGluZ3MiOiI7OztrQkFFd0JBLGE7O0FBRnhCOzs7Ozs7QUFFZSxTQUFTQSxhQUFULENBQXVCQyxJQUF2QixFQUE2QkMsT0FBN0IsRUFBc0M7QUFDakQsUUFBSUMsTUFBTSxJQUFJQyx5QkFBSixDQUFvQkYsT0FBcEIsQ0FBVjtBQUNBQyxRQUFJRSxTQUFKLENBQWNKLElBQWQ7QUFDSCIsImZpbGUiOiJzY3NzLXN0cmluZ2lmeS5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBTY3NzU3RyaW5naWZpZXIgZnJvbSAnLi9zY3NzLXN0cmluZ2lmaWVyJztcblxuZXhwb3J0IGRlZmF1bHQgZnVuY3Rpb24gc2Nzc1N0cmluZ2lmeShub2RlLCBidWlsZGVyKSB7XG4gICAgbGV0IHN0ciA9IG5ldyBTY3NzU3RyaW5naWZpZXIoYnVpbGRlcik7XG4gICAgc3RyLnN0cmluZ2lmeShub2RlKTtcbn1cbiJdfQ==
