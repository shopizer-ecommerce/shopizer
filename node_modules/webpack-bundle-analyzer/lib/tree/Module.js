'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _lodash = require('lodash');

var _lodash2 = _interopRequireDefault(_lodash);

var _gzipSize = require('gzip-size');

var _gzipSize2 = _interopRequireDefault(_gzipSize);

var _Node2 = require('./Node');

var _Node3 = _interopRequireDefault(_Node2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Module = function (_Node) {
  _inherits(Module, _Node);

  function Module(name, data, parent) {
    _classCallCheck(this, Module);

    var _this = _possibleConstructorReturn(this, (Module.__proto__ || Object.getPrototypeOf(Module)).call(this, name, parent));

    _this.data = data;
    return _this;
  }

  _createClass(Module, [{
    key: 'mergeData',
    value: function mergeData(data) {
      if (data.size) {
        this.size += data.size;
      }

      if (data.parsedSrc) {
        this.src = (this.src || '') + data.parsedSrc;
      }
    }
  }, {
    key: 'toChartData',
    value: function toChartData() {
      return {
        id: this.data.id,
        label: this.name,
        path: this.path,
        statSize: this.size,
        parsedSize: this.parsedSize,
        gzipSize: this.gzipSize
      };
    }
  }, {
    key: 'src',
    get: function get() {
      return this.data.parsedSrc;
    },
    set: function set(value) {
      this.data.parsedSrc = value;
      delete this._gzipSize;
    }
  }, {
    key: 'size',
    get: function get() {
      return this.data.size;
    },
    set: function set(value) {
      this.data.size = value;
    }
  }, {
    key: 'parsedSize',
    get: function get() {
      return this.src ? this.src.length : undefined;
    }
  }, {
    key: 'gzipSize',
    get: function get() {
      if (!_lodash2.default.has(this, '_gzipSize')) {
        this._gzipSize = this.src ? _gzipSize2.default.sync(this.src) : undefined;
      }

      return this._gzipSize;
    }
  }]);

  return Module;
}(_Node3.default);

exports.default = Module;
;