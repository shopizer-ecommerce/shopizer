# karma-mocha

[![js-standard-style](https://img.shields.io/badge/code%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/karma-runner/karma-mocha)
 [![npm version](https://img.shields.io/npm/v/karma-mocha.svg?style=flat-square)](https://www.npmjs.com/package/karma-mocha) [![npm downloads](https://img.shields.io/npm/dm/karma-mocha.svg?style=flat-square)](https://www.npmjs.com/package/karma-mocha)

[![Build Status](https://img.shields.io/travis/karma-runner/karma-mocha/master.svg?style=flat-square)](https://travis-ci.org/karma-runner/karma-mocha) [![Dependency Status](https://img.shields.io/david/karma-runner/karma-mocha.svg?style=flat-square)](https://david-dm.org/karma-runner/karma-mocha) [![devDependency Status](https://img.shields.io/david/dev/karma-runner/karma-mocha.svg?style=flat-square)](https://david-dm.org/karma-runner/karma-mocha#info=devDependencies)

> Adapter for the [Mocha](http://mochajs.org/) testing framework.

## Installation

Install `karma-mocha` and `mocha` into to your project via `npm`:

```shell
$ npm install karma-mocha mocha --save-dev
```

`karma-mocha` should work with any version of `mocha`.

Since `karma-mocha` is an adapter for [Karma](http://karma-runner.github.io), you likely have it installed already, but in case you don't:  

```shell
$ npm install karma --save-dev
```

If you're having trouble, Karma provides [detailed instructions](http://karma-runner.github.io/1.0/intro/installation.html) on installation. 

## Configuration
Following code shows the default configuration...
```js
// karma.conf.js
module.exports = function(config) {
  config.set({
    frameworks: ['mocha'],

    files: [
      '*.js'
    ]
  });
};
```

If you want to pass configuration options directly to mocha you can
do this in the following way

```js
// karma.conf.js
module.exports = function(config) {
  config.set({
    frameworks: ['mocha'],

    files: [
      '*.js'
    ],

    client: {
      mocha: {
        // change Karma's debug.html to the mocha web reporter
        reporter: 'html',

        // require specific files after Mocha is initialized
        require: [require.resolve('bdd-lazy-var/bdd_lazy_var_global')],

        // custom ui, defined in required file above
        ui: 'bdd-lazy-var/global',
      }
    }
  });
};
```

If you want run only some tests matching a given pattern you can
do this in the following way

```sh
karma start &
karma run -- --grep=<pattern>
```

or

```js
module.exports = function(config) {
  config.set({
    ...
    client: {
      mocha: {
        grep: '<pattern>', // passed directly to mocha
        ...
      }
      ...
    }
  });
};
```

If you want to expose test properties specific to `mocha`, you can use the `expose` option:

```js
module.exports = function(config) {
  config.set({
    ...
    client: {
      mocha: {
        expose: ['body'] // This will be exposed in a reporter as `result.mocha.body`
        ...
      }
      ...
    }
  });
};
```

If you already have a configuration for Mocha in an opts file, you can use the `opts` option:

```js
module.exports = function(config) {
  config.set({
    ...
    client: {
      mocha: {
        opts: 'test/mocha.opts' // You can set opts to equal true then plugin will load opts from default location 'test/mocha.opts'
        ...
      }
      ...
    }
  });
};
```

## Internals

On the end of each test `karma-mocha` passes to `karma` result object with fields:

* `description` Test title.
* `suite` List of titles of test suites.
* `success` True if test is succeed, false otherwise.
* `skipped` True if test is skipped.
* `time` Test duration.
* `log` List of errors.
* `startTime` Milliseconds since epoch that the test started
* `endTime` Milliseconds since epoch that the test ended
* `assertionErrors` List of additional error info: 
    * `name` Error name.
    * `message` Error message.
    * `actual` Actual data in assertion, serialized to string.
    * `expected` Expected data in assertion, serialized to string.
    * `showDiff` True if it is configured by assertion to show diff.
* `mocha` An optional object listed if you use the `expose` option

This object will be passed to test reporter.

NB. the start and end times are added by the adapter whereas the duration is calculated by Mocha - as such they probably will not match arithmetically. Ie. `endTime - startTime !== duration`. These fields have been added so that timestamped reports can be matched up with other timestamped reports from the target device (eg. memory profiling data collected outside the browser)

----

For more information on Karma see the [homepage].


[homepage]: http://karma-runner.github.com
