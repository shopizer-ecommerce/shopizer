PostCSS HTML Syntax
====

[![NPM version](https://img.shields.io/npm/v/postcss-html.svg?style=flat-square)](https://www.npmjs.com/package/postcss-html)
[![Travis](https://img.shields.io/travis/gucong3000/postcss-html.svg)](https://travis-ci.org/gucong3000/postcss-html)
[![Codecov](https://img.shields.io/codecov/c/github/gucong3000/postcss-html.svg)](https://codecov.io/gh/gucong3000/postcss-html)

<img align="right" width="95" height="95"
	title="Philosopher’s stone, logo of PostCSS"
	src="http://postcss.github.io/postcss/logo.svg">

[PostCSS](https://github.com/postcss/postcss) Syntax for parsing HTML / [Markdown](https://daringfireball.net/projects/markdown/syntax) / [Vue component](https://vue-loader.vuejs.org/)

## Getting Started

First thing's first, install the module:

```
npm install postcss-html --save-dev
```

If you want support SCSS/SASS/LESS/SugarSS syntax, you need to install the corresponding module.

- SCSS: [PostCSS-SCSS](https://github.com/postcss/postcss-scss)
- SASS: [PostCSS-SASS](https://github.com/aleshaoleg/postcss-sass)
- LESS: [PostCSS-LESS](https://github.com/shellscape/postcss-less)
- SugarSS: [SugarSS](https://github.com/postcss/sugarss)

## Use Cases

```js
var syntax = require('postcss-html');
postcss(plugins).process(source, { syntax: syntax }).then(function (result) {
	// An alias for the result.css property. Use it with syntaxes that generate non-CSS output.
	result.content
});
```

### Style Transformations

The main use case of this plugin is to apply PostCSS transformations to HTML / [Markdown](https://daringfireball.net/projects/markdown/syntax) / [Vue component](https://vue-loader.vuejs.org/). For example, if you need to lint SCSS in `*.vue` with [Stylelint](http://stylelint.io/); or you need add vendor prefixes to CSS in `*.html` with [Autoprefixer](https://github.com/postcss/autoprefixer).

### Syntax Infer for Stylesheet Files

When passing a stylesheet file, syntaxe can automatically be inferred from the following file extensions: `.less`, `.sass`, `.scss` and `.sss`, others will be inferred as CSS.

### Custom unknown syntax

```js
var syntax = require('postcss-html');
postcss(plugins).process(html, {
	syntax: syntax({
		stylus: require('postcss-stylus')
	})
}).then(function (result) {
	result.content
});
```
