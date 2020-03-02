# Stylelint Plugin for Webpack [v[1, 3]](https://github.com/JaKXz/stylelint-webpack-plugin/blob/master/package.json#L58-L59)

[![Greenkeeper badge](https://badges.greenkeeper.io/JaKXz/stylelint-webpack-plugin.svg)](https://greenkeeper.io/)
[![npm version](https://badge.fury.io/js/stylelint-webpack-plugin.svg)](https://badge.fury.io/js/stylelint-webpack-plugin)
[![Build Status](https://travis-ci.org/JaKXz/stylelint-webpack-plugin.svg?branch=master)](https://travis-ci.org/JaKXz/stylelint-webpack-plugin)
[![Coverage Status](https://coveralls.io/repos/github/JaKXz/stylelint-webpack-plugin/badge.svg?branch=master)](https://coveralls.io/github/JaKXz/stylelint-webpack-plugin?branch=master)
[![js-semistandard-style](https://img.shields.io/badge/code%20style-semistandard-red.svg)](https://github.com/Flet/semistandard)

> ## Why you might want to use this plugin instead of [stylelint-loader](https://github.com/adrianhall/stylelint-loader)

**stylelint-loader** lints the files you `require` or the ones you define as an `entry` in your webpack config. But `@imports` in those files are not followed, so just the main file (for each require/entry) is linted.

Instead, with **stylelint-webpack-plugin** you just define file globs, like stylelint does by default. So you get all your files linted.


## Install

```bash
npm install --save-dev stylelint stylelint-webpack-plugin
# OR
yarn add --dev stylelint stylelint-webpack-plugin
```

## Usage

In your webpack configuration

```js
var StyleLintPlugin = require('stylelint-webpack-plugin');

module.exports = {
  // ...
  plugins: [
    new StyleLintPlugin(options),
  ],
  // ...
}
```

### Options

See [stylelint options](http://stylelint.io/user-guide/node-api/#options) for the complete list of options. This object is passed straight to the `stylelint.lint` function and has the following defaults:

* `configFile`: You can change the config file location. Default: (`undefined`), handled by [stylelint's cosmiconfig module](http://stylelint.io/user-guide/configuration/).
* `context`: String indicating the root of your SCSS files. Default: inherits from webpack config.
* `emitErrors`: Pipe stylelint 'error' severity messages to the error message handler in webpack's current instance. Note when this property is disabled (false) all stylelint messages are piped to webpack's warning message handler. Default: `true`
* `failOnError`: Throw a fatal error in the global build process (e.g. kill your entire build process on any stylelint 'error' severity message). Default: `false`
* `files`: Change the glob pattern for finding files. Must be relative to `options.context`. Default: `['**/*.s?(a|c)ss']`
* `formatter`: Use a custom formatter to print errors to the console. Default: `require('stylelint').formatters.string`
* `lintDirtyModulesOnly`: Lint only changed files, skip lint on start. Default: `false`
* [`syntax`](https://stylelint.io/user-guide/node-api/#syntax): e.g. use `'scss'` to lint .scss files. Default: `undefined`

## Errors

The plugin will dump full reporting of errors.
Set `failOnError` to true if you want webpack build process breaking with any stylelint error.
You can use the `quiet` option to not print stylelint output to the console.


## Acknowledgement

This project is basically a modified version of `sasslint-webpack-plugin`. It changed considerably
since stylelint is async, and its Node API changes compared with sasslint.

Thanks to Javier ([@vieron](https://github.com/vieron)) for originally publishing this plugin, and passing the torch to our [growing list of contributors](https://github.com/JaKXz/stylelint-webpack-plugin/graphs/contributors)! :smile:

## [License](LICENSE)
