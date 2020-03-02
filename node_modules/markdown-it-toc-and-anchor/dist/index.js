"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

exports.default = function (md, options) {
  options = _extends({
    toc: true,
    tocClassName: "markdownIt-TOC",
    tocFirstLevel: 1,
    tocLastLevel: 6,
    tocCallback: null,
    anchorLink: true,
    anchorLinkSymbol: "#",
    anchorLinkBefore: true,
    anchorClassName: "markdownIt-Anchor",
    resetIds: true,
    anchorLinkSpace: true,
    anchorLinkSymbolClassName: null
  }, options);

  markdownItSecondInstance = (0, _clone2.default)(md);

  // initialize key ids for each instance
  headingIds = {};

  md.core.ruler.push("init_toc", function (state) {
    Token = state.Token;
    var tokens = state.tokens;

    // reset key ids for each document
    if (options.resetIds) {
      headingIds = {};
    }

    var tocArray = [];
    var tocMarkdown = "";
    var tocTokens = [];

    for (var i = 0; i < tokens.length; i++) {
      if (tokens[i].type !== "heading_close") {
        continue;
      }

      var heading = tokens[i - 1];
      var heading_close = tokens[i];

      if (heading.type === "inline") {
        var content = void 0;
        if (heading.children && heading.children[0].type === "link_open") {
          // headings that contain links have to be processed
          // differently since nested links aren't allowed in markdown
          content = heading.children[1].content;
          heading._tocAnchor = makeSafe(content, headingIds);
        } else {
          content = heading.content;
          heading._tocAnchor = makeSafe(heading.children.reduce(function (acc, t) {
            return acc + t.content;
          }, ""), headingIds);
        }

        tocArray.push({
          content: content,
          anchor: heading._tocAnchor,
          level: +heading_close.tag.substr(1, 1)
        });
      }
    }

    tocMarkdown = generateTocMarkdownFromArray(tocArray, options);

    tocTokens = markdownItSecondInstance.parse(tocMarkdown, {});

    // Adding tocClassName to 'ul' element
    if (_typeof(tocTokens[0]) === "object" && tocTokens[0].type === "bullet_list_open") {
      var attrs = tocTokens[0].attrs = tocTokens[0].attrs || [];
      attrs.push(["class", options.tocClassName]);
    }

    tocHtml = markdownItSecondInstance.renderer.render(tocTokens, markdownItSecondInstance.options);

    if (typeof state.env.tocCallback === "function") {
      state.env.tocCallback.call(undefined, tocMarkdown, tocArray, tocHtml);
    } else if (typeof options.tocCallback === "function") {
      options.tocCallback.call(undefined, tocMarkdown, tocArray, tocHtml);
    } else if (typeof md.options.tocCallback === "function") {
      md.options.tocCallback.call(undefined, tocMarkdown, tocArray, tocHtml);
    }
  });

  md.inline.ruler.after("emphasis", "toc", function (state, silent) {

    var token = void 0;
    var match = void 0;

    while (state.src.indexOf("\n") >= 0 && state.src.indexOf("\n") < state.src.indexOf(TOC)) {
      if (state.tokens.slice(-1)[0].type === "softbreak") {
        state.src = state.src.split("\n").slice(1).join("\n");
        state.pos = 0;
      }
    }

    if (
    // Reject if the token does not start with @[
    state.src.charCodeAt(state.pos) !== 0x40 || state.src.charCodeAt(state.pos + 1) !== 0x5B ||

    // Don’t run any pairs in validation mode
    silent) {
      return false;
    }

    // Detect TOC markdown
    match = TOC_RE.exec(state.src);
    match = !match ? [] : match.filter(function (m) {
      return m;
    });
    if (match.length < 1) {
      return false;
    }

    // Build content
    token = state.push("toc_open", "toc", 1);
    token.markup = TOC;
    token = state.push("toc_body", "", 0);
    token = state.push("toc_close", "toc", -1);

    // Update pos so the parser can continue
    var newline = state.src.indexOf("\n");
    if (newline !== -1) {
      state.pos = state.pos + newline;
    } else {
      state.pos = state.pos + state.posMax + 1;
    }

    return true;
  });

  var originalHeadingOpen = md.renderer.rules.heading_open || function () {
    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    var tokens = args[0],
        idx = args[1],
        options = args[2],
        self = args[4];

    return self.renderToken(tokens, idx, options);
  };

  md.renderer.rules.heading_open = function () {
    for (var _len2 = arguments.length, args = Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
      args[_key2] = arguments[_key2];
    }

    var tokens = args[0],
        idx = args[1];


    var attrs = tokens[idx].attrs = tokens[idx].attrs || [];
    var anchor = tokens[idx + 1]._tocAnchor;
    attrs.push(["id", anchor]);

    if (options.anchorLink) {
      renderAnchorLink.apply(undefined, [anchor, options].concat(args));
    }

    return originalHeadingOpen.apply(this, args);
  };

  md.renderer.rules.toc_open = function () {
    return "";
  };
  md.renderer.rules.toc_close = function () {
    return "";
  };
  md.renderer.rules.toc_body = function () {
    return "";
  };

  if (options.toc) {
    md.renderer.rules.toc_body = function () {
      return tocHtml;
    };
  }
};

var _clone = require("clone");

var _clone2 = _interopRequireDefault(_clone);

var _uslug = require("uslug");

var _uslug2 = _interopRequireDefault(_uslug);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

var TOC = "@[toc]";
var TOC_RE = /^@\[toc\]/im;

var markdownItSecondInstance = function markdownItSecondInstance() {};
var Token = function Token() {};
var headingIds = {};
var tocHtml = "";

var repeat = function repeat(string, num) {
  return new Array(num + 1).join(string);
};

var makeSafe = function makeSafe(string, headingIds) {
  var key = (0, _uslug2.default)(string); // slugify
  if (!headingIds[key]) {
    headingIds[key] = 0;
  }
  headingIds[key]++;
  return key + (headingIds[key] > 1 ? "-" + headingIds[key] : "");
};

var space = function space() {
  return _extends({}, new Token("text", "", 0), { content: " " });
};

var renderAnchorLinkSymbol = function renderAnchorLinkSymbol(options) {
  if (options.anchorLinkSymbolClassName) {
    return [_extends({}, new Token("span_open", "span", 1), {
      attrs: [["class", options.anchorLinkSymbolClassName]]
    }), _extends({}, new Token("text", "", 0), {
      content: options.anchorLinkSymbol
    }), new Token("span_close", "span", -1)];
  } else {
    return [_extends({}, new Token("text", "", 0), {
      content: options.anchorLinkSymbol
    })];
  }
};

var renderAnchorLink = function renderAnchorLink(anchor, options, tokens, idx) {
  var _tokens$children;

  var linkTokens = [_extends({}, new Token("link_open", "a", 1), {
    attrs: [["class", options.anchorClassName], ["href", "#" + anchor]]
  })].concat(_toConsumableArray(renderAnchorLinkSymbol(options)), [new Token("link_close", "a", -1)]);

  // `push` or `unshift` according to anchorLinkBefore option
  // space is at the opposite side.
  var actionOnArray = {
    false: "push",
    true: "unshift"
  };

  // insert space between anchor link and heading ?
  if (options.anchorLinkSpace) {
    linkTokens[actionOnArray[!options.anchorLinkBefore]](space());
  }
  (_tokens$children = tokens[idx + 1].children)[actionOnArray[options.anchorLinkBefore]].apply(_tokens$children, _toConsumableArray(linkTokens));
};

var treeToMarkdownBulletList = function treeToMarkdownBulletList(tree) {
  var indent = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 0;
  return tree.map(function (item) {
    var indentation = "  ";
    var node = repeat(indentation, indent) + "*";
    if (item.heading.content) {
      node += " " + ("[" + item.heading.content + "](#" + item.heading.anchor + ")\n");
    } else {
      node += "\n";
    }
    if (item.nodes.length) {
      node += treeToMarkdownBulletList(item.nodes, indent + 1);
    }
    return node;
  }).join("");
};

var generateTocMarkdownFromArray = function generateTocMarkdownFromArray(headings, options) {
  var tree = { nodes: [] };
  // create an ast
  headings.forEach(function (heading) {
    if (heading.level < options.tocFirstLevel || heading.level > options.tocLastLevel) {
      return;
    }

    var i = 1;
    var lastItem = tree;
    for (; i < heading.level - options.tocFirstLevel + 1; i++) {
      if (lastItem.nodes.length === 0) {
        lastItem.nodes.push({
          heading: {},
          nodes: []
        });
      }
      lastItem = lastItem.nodes[lastItem.nodes.length - 1];
    }
    lastItem.nodes.push({
      heading: heading,
      nodes: []
    });
  });

  return treeToMarkdownBulletList(tree.nodes);
};