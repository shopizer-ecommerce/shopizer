'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _lodash = require('lodash');

var _lodash2 = _interopRequireDefault(_lodash);

var _Node2 = require('./Node');

var _Node3 = _interopRequireDefault(_Node2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var BaseFolder = function (_Node) {
  _inherits(BaseFolder, _Node);

  function BaseFolder(name, parent) {
    _classCallCheck(this, BaseFolder);

    var _this = _possibleConstructorReturn(this, (BaseFolder.__proto__ || Object.getPrototypeOf(BaseFolder)).call(this, name, parent));

    _this.children = Object.create(null);
    return _this;
  }

  _createClass(BaseFolder, [{
    key: 'getChild',
    value: function getChild(name) {
      return this.children[name];
    }
  }, {
    key: 'addChildModule',
    value: function addChildModule(module) {
      var name = module.name;

      var currentChild = this.children[name];

      // For some reason we already have this node in children and it's a folder.
      if (currentChild && currentChild instanceof BaseFolder) return;

      if (currentChild) {
        // We already have this node in children and it's a module.
        // Merging it's data.
        currentChild.mergeData(module.data);
      } else {
        // Pushing new module
        module.parent = this;
        this.children[name] = module;
      }

      delete this._size;
      delete this._src;
    }
  }, {
    key: 'addChildFolder',
    value: function addChildFolder(folder) {
      folder.parent = this;
      this.children[folder.name] = folder;
      delete this._size;
      delete this._src;

      return folder;
    }
  }, {
    key: 'walk',
    value: function walk(walker) {
      var state = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};
      var deep = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : true;

      var stopped = false;

      _lodash2.default.each(this.children, function (child) {
        if (deep && child.walk) {
          state = child.walk(walker, state, stop);
        } else {
          state = walker(child, state, stop);
        }

        if (stopped) return false;
      });

      return state;

      function stop(finalState) {
        stopped = true;
        return finalState;
      }
    }
  }, {
    key: 'toChartData',
    value: function toChartData() {
      return {
        label: this.name,
        path: this.path,
        statSize: this.size,
        groups: _lodash2.default.invokeMap(this.children, 'toChartData')
      };
    }
  }, {
    key: 'src',
    get: function get() {
      if (!_lodash2.default.has(this, '_src')) {
        this._src = this.walk(function (node, src, stop) {
          if (node.src === undefined) return stop(undefined);
          return src += node.src;
        }, '', false);
      }

      return this._src;
    }
  }, {
    key: 'size',
    get: function get() {
      if (!_lodash2.default.has(this, '_size')) {
        this._size = this.walk(function (node, size) {
          return size + node.size;
        }, 0, false);
      }

      return this._size;
    }
  }]);

  return BaseFolder;
}(_Node3.default);

exports.default = BaseFolder;
;