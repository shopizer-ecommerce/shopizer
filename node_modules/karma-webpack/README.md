[![npm][npm]][npm-url]
[![node][node]][node-url]
[![deps][deps]][deps-url]
[![test][test]][test-url]
[![coverage][cover]][cover-url]
[![chat][chat]][chat-url]

<div align="center">
  <a href='https://github.com/karma-runner/karma'>
    <img width="180" height="180"
      src="https://worldvectorlogo.com/logos/karma.svg">
  </a>
  <a href="https://github.com/webpack/webpack">
    <img width="200" height="200"
      src="https://cdn.rawgit.com/webpack/media/e7485eb2/logo/icon.svg">
  </a>
  <h1>Karma Webpack</h1>
  <p>Use webpack to preprocess files in karma<p>
</div>

<h2 align="center">Install</h2>

```bash
npm i -D karma-webpack
```

<h2 align="center">Usage</h2>

**karma.conf.js**
```js
module.exports = (config) => {
  config.set({
    // ... normal karma configuration
    files: [
      // all files ending in "_test"
      { pattern: 'test/*_test.js', watched: false },
      { pattern: 'test/**/*_test.js', watched: false }
      // each file acts as entry point for the webpack configuration
    ],

    preprocessors: {
      // add webpack as preprocessor
      'test/*_test.js': [ 'webpack' ],
      'test/**/*_test.js': [ 'webpack' ]
    },

    webpack: {
      // karma watches the test entry points
      // (you don't need to specify the entry option)
      // webpack watches dependencies

      // webpack configuration
    },

    webpackMiddleware: {
      // webpack-dev-middleware configuration
      // i. e.
      stats: 'errors-only'
    }
  })
}
```

### `Alternative Usage`

This configuration is more performant, but you cannot run single test anymore (only the complete suite).

The above configuration generates a `webpack` bundle for each test. For many test cases this can result in many big files. The alternative configuration creates a single bundle with all test cases.

**karma.conf.js**
```js
files: [
  // only specify one entry point
  // and require all tests in there
  'test/index_test.js'
],

preprocessors: {
  // add webpack as preprocessor
  'test/index_test.js': [ 'webpack' ]
},
```

**test/index_test.js**
```js
// require all modules ending in "_test" from the
// current directory and all subdirectories
const testsContext = require.context(".", true, /_test$/)

testsContext.keys().forEach(testsContext)
```

Every test file is required using the [require.context](https://webpack.js.org/guides/dependency-management/#require-context) and compiled with webpack into one test bundle.

### `Source Maps`

You can use the `karma-sourcemap-loader` to get the source maps generated for your test bundle.

```bash
npm i -D karma-sourcemap-loader
```

And then add it to your preprocessors.

**karma.conf.js**
```js
preprocessors: {
  'test/test_index.js': [ 'webpack', 'sourcemap' ]
}
```

And tell `webpack` to generate sourcemaps.

**webpack.config.js**
```js
webpack: {
  // ...
  devtool: 'inline-source-map'
}
```

<h2 align="center">Options</h2>

This is the full list of options you can specify in your `karma.conf.js`

|Name|Type|Default|Description|
|:--:|:--:|:-----:|:----------|
|[**`webpack`**](#webpack)|`{Object}`|`{}`|Pass `webpack.config.js` to `karma`|
|[**`webpackMiddleware`**](#webpackmiddleware)|`{Object}`|`{}`|Pass `webpack-dev-middleware` configuration to `karma`|
|[**`beforeMiddleware`**](#beforemiddleware)|`{Object}`|`{}`|Pass custom middleware configuration to `karma`, **before** any `karma` middleware runs|

### `webpack`

`webpack` configuration (`webpack.config.js`).

### `webpackMiddleware`

Configuration for `webpack-dev-middleware`.

### `beforeMiddleware`

`beforeMiddleware` is a `webpack` option that allows injecting middleware before
karma's own middleware runs. This loader provides a `webpackBlocker`
middleware that will block tests from running until code recompiles. That is,
given this scenario

1. Have a browser open on the karma debug page (http://localhost:9876/debug.html)
2. Make a code change
3. Refresh

Without the `webpackBlocker` middleware karma will serve files from before
the code change. With the `webpackBlocker` middleware the loader will not serve
the files until the code has finished recompiling.

> **⚠️ The `beforeMiddleware` option is only supported in `karma >= v1.0.0`**

<h2 align="center">Maintainers</h2>

<table>
  <tbody>
    <tr>
      <td align="center">
        <img width="150" height="150"
        src="https://avatars.githubusercontent.com/u/4650931?v=3&s=150">
        </br>
        <a href="https://github.com/MikaAK">Mika Kalathil</a>
      </td>
      <td align="center">
        <img width="150" height="150"
        src="https://avatars.githubusercontent.com/u/8420490?v=3&s=150">
        <a href="https://github.com/d3viant0ne">Joshua Wiens</a>
      </td>
      <td align="center">
        <img width="150" height="150" src="https://avatars.githubusercontent.com/u/1919664?v=3&s=150">
        <a href="https://github.com/goldhand">Will Farley</a>
      </td>
      <td align="center">
        <img width="150" height="150"
        src="https://avatars.githubusercontent.com/u/1307954?v=3&s=150">
        <a href="https://github.com/DanielaValero">Daniela Valero</a>
      </td>
    </tr>
    <tr>
      <td align="center">
        <img width="150" height="150"
        src="https://avatars.githubusercontent.com/u/122108?v=3&s=150">
        <a href="https://github.com/jon301">Jonathan Trang</a>
      </td>
      <td align="center">
        <img width="150" height="150"
        src="https://avatars.githubusercontent.com/u/3285723?v=3&s=150">
        <a href="https://github.com/carlos-">Carlos Morales</a>
      </td>
    </tr>
  <tbody>
</table>


[npm]: https://img.shields.io/npm/v/karma-webpack.svg
[npm-url]: https://npmjs.com/package/karma-webpack

[node]: https://img.shields.io/node/v/karma-webpack.svg
[node-url]: https://nodejs.org

[deps]: https://david-dm.org/webpack-contrib/karma-webpack.svg
[deps-url]: https://david-dm.org/webpack-contrib/karma-webpack

[chat]: https://img.shields.io/badge/gitter-webpack%2Fwebpack-brightgreen.svg
[chat-url]: https://gitter.im/webpack/webpack

[test]: http://img.shields.io/travis/webpack-contrib/karma-webpack.svg
[test-url]: https://travis-ci.org/webpack-contrib/karma-webpack

[cover]: https://codecov.io/gh/webpack-contrib/karma-webpack/branch/master/graph/badge.svg
[cover-url]: https://codecov.io/gh/webpack-contrib/karma-webpack
