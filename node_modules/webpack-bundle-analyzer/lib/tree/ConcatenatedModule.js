'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _get = function get(object, property, receiver) { if (object === null) object = Function.prototype; var desc = Object.getOwnPropertyDescriptor(object, property); if (desc === undefined) { var parent = Object.getPrototypeOf(object); if (parent === null) { return undefined; } else { return get(parent, property, receiver); } } else if ("value" in desc) { return desc.value; } else { var getter = desc.get; if (getter === undefined) { return undefined; } return getter.call(receiver); } };

var _lodash = require('lodash');

var _lodash2 = _interopRequireDefault(_lodash);

var _Module2 = require('./Module');

var _Module3 = _interopRequireDefault(_Module2);

var _ContentModule = require('./ContentModule');

var _ContentModule2 = _interopRequireDefault(_ContentModule);

var _ContentFolder = require('./ContentFolder');

var _ContentFolder2 = _interopRequireDefault(_ContentFolder);

var _utils = require('./utils');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ConcatenatedModule = function (_Module) {
  _inherits(ConcatenatedModule, _Module);

  function ConcatenatedModule(name, data, parent) {
    _classCallCheck(this, ConcatenatedModule);

    var _this = _possibleConstructorReturn(this, (ConcatenatedModule.__proto__ || Object.getPrototypeOf(ConcatenatedModule)).call(this, name, data, parent));

    _this.name += ' (concatenated)';
    _this.children = Object.create(null);
    _this.fillContentModules();
    return _this;
  }

  _createClass(ConcatenatedModule, [{
    key: 'fillContentModules',
    value: function fillContentModules() {
      var _this2 = this;

      _lodash2.default.each(this.data.modules, function (moduleData) {
        return _this2.addContentModule(moduleData);
      });
    }
  }, {
    key: 'addContentModule',
    value: function addContentModule(moduleData) {
      var _this3 = this;

      var pathParts = (0, _utils.getModulePathParts)(moduleData);

      if (!pathParts) {
        return;
      }

      var _ref = [pathParts.slice(0, -1), _lodash2.default.last(pathParts)],
          folders = _ref[0],
          fileName = _ref[1];

      var currentFolder = this;

      _lodash2.default.each(folders, function (folderName) {
        var childFolder = currentFolder.getChild(folderName);

        if (!childFolder) {
          childFolder = currentFolder.addChildFolder(new _ContentFolder2.default(folderName, _this3));
        }

        currentFolder = childFolder;
      });

      var module = new _ContentModule2.default(fileName, moduleData, this);
      currentFolder.addChildModule(module);
    }
  }, {
    key: 'getChild',
    value: function getChild(name) {
      return this.children[name];
    }
  }, {
    key: 'addChildModule',
    value: function addChildModule(module) {
      module.parent = this;
      this.children[module.name] = module;
    }
  }, {
    key: 'addChildFolder',
    value: function addChildFolder(folder) {
      folder.parent = this;
      this.children[folder.name] = folder;
      return folder;
    }
  }, {
    key: 'toChartData',
    value: function toChartData() {
      return Object.assign({}, _get(ConcatenatedModule.prototype.__proto__ || Object.getPrototypeOf(ConcatenatedModule.prototype), 'toChartData', this).call(this), {
        concatenated: true,
        groups: _lodash2.default.invokeMap(this.children, 'toChartData')
      });
    }
  }]);

  return ConcatenatedModule;
}(_Module3.default);

exports.default = ConcatenatedModule;
;