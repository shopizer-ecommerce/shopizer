# karma-phantomjs-shim

Provides shims when running tests in PhantomJS.

## Use

Install the plugin with `npm`:

    npm install karma-phantomjs-shim

Configure Karma to load the plugin as a framework:

```js
module.exports = function(config) {
  config.set({
    frameworks: ['phantomjs-shim']
    // additional settings here ...
  });
};
```

If you've defined the plugins section in your Karma config file you'll also need to add a `karma-phantomjs-shim` entry to your plugins array:

```js
module.exports = function(config) {
  config.set({
    // [...]
    plugins: ['karma-phantomjs-shim']
    // [...]
  });
};
```

## Shims

 * `CustomEvent`
 * `Function.prototype.bind`
 * `Object.assign`
 * `String.prototype.includes`
 * `String.prototype.repeat`
 * `String.prototype.startsWith`
 * `requestAnimationFrame`

Want more?  [Pull requests welcome!](https://github.com/tschaub/karma-phantomjs-shim)
