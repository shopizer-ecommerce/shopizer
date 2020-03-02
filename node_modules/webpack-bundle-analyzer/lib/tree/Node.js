'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Node = function () {
  function Node(name, parent) {
    _classCallCheck(this, Node);

    this.name = name;
    this.parent = parent;
  }

  _createClass(Node, [{
    key: 'path',
    get: function get() {
      var path = [];
      var node = this;

      while (node) {
        path.push(node.name);
        node = node.parent;
      }

      return path.reverse().join('/');
    }
  }]);

  return Node;
}();

exports.default = Node;
;