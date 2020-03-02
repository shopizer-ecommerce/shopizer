'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _get = function get(object, property, receiver) { if (object === null) object = Function.prototype; var desc = Object.getOwnPropertyDescriptor(object, property); if (desc === undefined) { var parent = Object.getPrototypeOf(object); if (parent === null) { return undefined; } else { return get(parent, property, receiver); } } else if ("value" in desc) { return desc.value; } else { var getter = desc.get; if (getter === undefined) { return undefined; } return getter.call(receiver); } };

var _lodash = require('lodash');

var _lodash2 = _interopRequireDefault(_lodash);

var _gzipSize = require('gzip-size');

var _gzipSize2 = _interopRequireDefault(_gzipSize);

var _Module = require('./Module');

var _Module2 = _interopRequireDefault(_Module);

var _BaseFolder2 = require('./BaseFolder');

var _BaseFolder3 = _interopRequireDefault(_BaseFolder2);

var _ConcatenatedModule = require('./ConcatenatedModule');

var _ConcatenatedModule2 = _interopRequireDefault(_ConcatenatedModule);

var _utils = require('./utils');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Folder = function (_BaseFolder) {
  _inherits(Folder, _BaseFolder);

  function Folder() {
    _classCallCheck(this, Folder);

    return _possibleConstructorReturn(this, (Folder.__proto__ || Object.getPrototypeOf(Folder)).apply(this, arguments));
  }

  _createClass(Folder, [{
    key: 'addModule',
    value: function addModule(moduleData) {
      var pathParts = (0, _utils.getModulePathParts)(moduleData);

      if (!pathParts) {
        return;
      }

      var _ref = [pathParts.slice(0, -1), _lodash2.default.last(pathParts)],
          folders = _ref[0],
          fileName = _ref[1];

      var currentFolder = this;

      _lodash2.default.each(folders, function (folderName) {
        var childNode = currentFolder.getChild(folderName);

        if (
        // Folder is not created yet
        !childNode ||
        // In some situations (invalid usage of dynamic `require()`) webpack generates a module with empty require
        // context, but it's moduleId points to a directory in filesystem.
        // In this case we replace this `File` node with `Folder`.
        // See `test/stats/with-invalid-dynamic-require.json` as an example.
        !(childNode instanceof Folder)) {
          childNode = currentFolder.addChildFolder(new Folder(folderName));
        }

        currentFolder = childNode;
      });

      var ModuleConstructor = moduleData.modules ? _ConcatenatedModule2.default : _Module2.default;
      var module = new ModuleConstructor(fileName, moduleData, this);
      currentFolder.addChildModule(module);
    }
  }, {
    key: 'toChartData',
    value: function toChartData() {
      return Object.assign({}, _get(Folder.prototype.__proto__ || Object.getPrototypeOf(Folder.prototype), 'toChartData', this).call(this), {
        parsedSize: this.parsedSize,
        gzipSize: this.gzipSize
      });
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

  return Folder;
}(_BaseFolder3.default);

exports.default = Folder;
;