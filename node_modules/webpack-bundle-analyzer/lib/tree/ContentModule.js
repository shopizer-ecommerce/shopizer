'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _get = function get(object, property, receiver) { if (object === null) object = Function.prototype; var desc = Object.getOwnPropertyDescriptor(object, property); if (desc === undefined) { var parent = Object.getPrototypeOf(object); if (parent === null) { return undefined; } else { return get(parent, property, receiver); } } else if ("value" in desc) { return desc.value; } else { var getter = desc.get; if (getter === undefined) { return undefined; } return getter.call(receiver); } };

var _Module2 = require('./Module');

var _Module3 = _interopRequireDefault(_Module2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ContentModule = function (_Module) {
  _inherits(ContentModule, _Module);

  function ContentModule(name, data, ownerModule, parent) {
    _classCallCheck(this, ContentModule);

    var _this = _possibleConstructorReturn(this, (ContentModule.__proto__ || Object.getPrototypeOf(ContentModule)).call(this, name, data, parent));

    _this.ownerModule = ownerModule;
    return _this;
  }

  _createClass(ContentModule, [{
    key: 'getSize',
    value: function getSize(sizeType) {
      var ownerModuleSize = this.ownerModule[sizeType];

      if (ownerModuleSize !== undefined) {
        return Math.floor(this.size / this.ownerModule.size * ownerModuleSize);
      }
    }
  }, {
    key: 'toChartData',
    value: function toChartData() {
      return Object.assign({}, _get(ContentModule.prototype.__proto__ || Object.getPrototypeOf(ContentModule.prototype), 'toChartData', this).call(this), {
        inaccurateSizes: true
      });
    }
  }, {
    key: 'parsedSize',
    get: function get() {
      return this.getSize('parsedSize');
    }
  }, {
    key: 'gzipSize',
    get: function get() {
      return this.getSize('gzipSize');
    }
  }]);

  return ContentModule;
}(_Module3.default);

exports.default = ContentModule;
;