'use strict';

exports.__esModule = true;

var _container = require('postcss/lib/container');

var _container2 = _interopRequireDefault(_container);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var NestedDeclaration = function (_Container) {
    _inherits(NestedDeclaration, _Container);

    function NestedDeclaration(defaults) {
        _classCallCheck(this, NestedDeclaration);

        var _this = _possibleConstructorReturn(this, _Container.call(this, defaults));

        _this.type = 'decl';
        _this.isNested = true;
        if (!_this.nodes) _this.nodes = [];
        return _this;
    }

    return NestedDeclaration;
}(_container2.default);

exports.default = NestedDeclaration;
module.exports = exports['default'];
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm5lc3RlZC1kZWNsYXJhdGlvbi5lczYiXSwibmFtZXMiOlsiTmVzdGVkRGVjbGFyYXRpb24iLCJkZWZhdWx0cyIsInR5cGUiLCJpc05lc3RlZCIsIm5vZGVzIiwiQ29udGFpbmVyIl0sIm1hcHBpbmdzIjoiOzs7O0FBQUE7Ozs7Ozs7Ozs7OztJQUVNQSxpQjs7O0FBRUYsK0JBQVlDLFFBQVosRUFBc0I7QUFBQTs7QUFBQSxxREFDbEIsc0JBQU1BLFFBQU4sQ0FEa0I7O0FBRWxCLGNBQUtDLElBQUwsR0FBZ0IsTUFBaEI7QUFDQSxjQUFLQyxRQUFMLEdBQWdCLElBQWhCO0FBQ0EsWUFBSyxDQUFDLE1BQUtDLEtBQVgsRUFBbUIsTUFBS0EsS0FBTCxHQUFhLEVBQWI7QUFKRDtBQUtyQjs7O0VBUDJCQyxtQjs7a0JBV2pCTCxpQiIsImZpbGUiOiJuZXN0ZWQtZGVjbGFyYXRpb24uanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgQ29udGFpbmVyIGZyb20gJ3Bvc3Rjc3MvbGliL2NvbnRhaW5lcic7XG5cbmNsYXNzIE5lc3RlZERlY2xhcmF0aW9uIGV4dGVuZHMgQ29udGFpbmVyIHtcblxuICAgIGNvbnN0cnVjdG9yKGRlZmF1bHRzKSB7XG4gICAgICAgIHN1cGVyKGRlZmF1bHRzKTtcbiAgICAgICAgdGhpcy50eXBlICAgICA9ICdkZWNsJztcbiAgICAgICAgdGhpcy5pc05lc3RlZCA9IHRydWU7XG4gICAgICAgIGlmICggIXRoaXMubm9kZXMgKSB0aGlzLm5vZGVzID0gW107XG4gICAgfVxuXG59XG5cbmV4cG9ydCBkZWZhdWx0IE5lc3RlZERlY2xhcmF0aW9uO1xuIl19
