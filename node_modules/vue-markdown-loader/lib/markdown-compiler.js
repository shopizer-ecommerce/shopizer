var loaderUtils = require('loader-utils');
var hljs = require('highlight.js');
var cheerio = require('cheerio');
var markdown = require('markdown-it');
var Token = require('markdown-it/lib/token');

/**
 * `<pre></pre>` => `<pre v-pre></pre>`
 * `<code></code>` => `<code v-pre></code>`
 * @param  {string} str
 * @return {string}
 */
var addVuePreviewAttr = function(str) {
  return str.replace(/(<pre|<code)/g, '$1 v-pre');
};

/**
 * renderHighlight
 * @param  {string} str
 * @param  {string} lang
 */
var renderHighlight = function(str, lang) {
  if (!(lang && hljs.getLanguage(lang))) {
    return '';
  }

  return hljs.highlight(lang, str, true).value;
};

/**
 * html => vue file template
 * @param  {[type]} html [description]
 * @return {[type]}      [description]
 */
var renderVueTemplate = function(html, wrapper) {
  var $ = cheerio.load(html, {
    decodeEntities: false,
    lowerCaseAttributeNames: false,
    lowerCaseTags: false
  });

  var output = {
    style: $.html('style'),
    // get only the first script child. Causes issues if multiple script files in page.
    script: $.html($('script').first())
  };
  var result;

  $('style').remove();
  $('script').remove();

  result =
    `<template><${wrapper}>` +
    $.html() +
    `</${wrapper}></template>\n` +
    output.style +
    '\n' +
    output.script;

  return result;
};

module.exports = function(source) {
  this.cacheable && this.cacheable();
  var parser, preprocess;
  var params = loaderUtils.getOptions(this) || {};
  var vueMarkdownOptions = this._compilation.__vueMarkdownOptions__;
  var opts = Object.create(vueMarkdownOptions.__proto__); // inherit prototype
  var preventExtract = false;

  opts = Object.assign(opts, params, vueMarkdownOptions); // assign attributes

  if (opts.preventExtract) {
    delete opts.preventExtract;
    preventExtract = true;
  }

  if (typeof opts.render === 'function') {
    parser = opts;
  } else {
    opts = Object.assign(
      {
        preset: 'default',
        html: true,
        highlight: renderHighlight,
        wrapper: 'section'
      },
      opts
    );

    var plugins = opts.use;
    preprocess = opts.preprocess;

    delete opts.use;
    delete opts.preprocess;

    parser = markdown(opts.preset, opts);

    //add ruler:extract script and style tags from html token content
    !preventExtract &&
      parser.core.ruler.push('extract_script_or_style', function replace(
        state
      ) {
        let tag_reg = new RegExp('<(script|style)(?:[^<]|<)+</\\1>', 'g');
        let newTokens = [];
        state.tokens
          .filter(token => token.type == 'fence' && token.info == 'html')
          .forEach(token => {
            let tokens = (token.content.match(tag_reg) || []).map(content => {
              let t = new Token('html_block', '', 0);
              t.content = content;
              return t;
            });
            if (tokens.length > 0) {
              newTokens.push.apply(newTokens, tokens);
            }
          });
        state.tokens.push.apply(state.tokens, newTokens);
      });

    if (plugins) {
      plugins.forEach(function(plugin) {
        if (Array.isArray(plugin)) {
          parser.use.apply(parser, plugin);
        } else {
          parser.use(plugin);
        }
      });
    }
  }

  /**
   * override default parser rules by adding v-pre attribute on 'code' and 'pre' tags
   * @param {Array<string>} rules rules to override
   */
  function overrideParserRules(rules) {
    if (parser && parser.renderer && parser.renderer.rules) {
      var parserRules = parser.renderer.rules;
      rules.forEach(function(rule) {
        if (parserRules && parserRules[rule]) {
          var defaultRule = parserRules[rule];
          parserRules[rule] = function() {
            return addVuePreviewAttr(defaultRule.apply(this, arguments));
          };
        }
      });
    }
  }

  overrideParserRules(['code_inline', 'code_block', 'fence']);

  if (preprocess) {
    source = preprocess.call(this, parser, source);
  }

  source = source.replace(/@/g, '__at__');

  var content = parser.render(source).replace(/__at__/g, '@');
  var result = renderVueTemplate(content, opts.wrapper);

  if (opts.raw) {
    return result;
  } else {
    return 'module.exports = ' + JSON.stringify(result);
  }
};
