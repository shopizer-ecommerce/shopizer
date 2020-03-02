# karma-phantomjs-launcher

[![js-standard-style](https://img.shields.io/badge/code%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/karma-runner/karma-phantomjs-launcher)
 [![npm version](https://img.shields.io/npm/v/karma-phantomjs-launcher.svg?style=flat-square)](https://www.npmjs.com/package/karma-phantomjs-launcher) [![npm downloads](https://img.shields.io/npm/dm/karma-phantomjs-launcher.svg?style=flat-square)](https://www.npmjs.com/package/karma-phantomjs-launcher)

[![Build Status](https://img.shields.io/travis/karma-runner/karma-phantomjs-launcher/master.svg?style=flat-square)](https://travis-ci.org/karma-runner/karma-phantomjs-launcher) [![Dependency Status](https://img.shields.io/david/karma-runner/karma-phantomjs-launcher.svg?style=flat-square)](https://david-dm.org/karma-runner/karma-phantomjs-launcher) [![devDependency Status](https://img.shields.io/david/dev/karma-runner/karma-phantomjs-launcher.svg?style=flat-square)](https://david-dm.org/karma-runner/karma-phantomjs-launcher#info=devDependencies)

> Launcher for [PhantomJS].

## Installation

The easiest way is to keep `karma-phantomjs-launcher` as a devDependency in your `package.json`,
by running

```bash
$ npm install --save-dev karma-phantomjs-launcher
```

## Configuration

```js
// karma.conf.js
module.exports = function(config) {
  config.set({
    browsers: ['PhantomJS', 'PhantomJS_custom'],

    // you can define custom flags
    customLaunchers: {
      'PhantomJS_custom': {
        base: 'PhantomJS',
        options: {
          windowName: 'my-window',
          settings: {
            webSecurityEnabled: false
          },
        },
        flags: ['--load-images=true'],
        debug: true
      }
    },

    phantomjsLauncher: {
      // Have phantomjs exit if a ResourceError is encountered (useful if karma exits without killing phantom)
      exitOnResourceError: true
    }
  })
}
```

The `options` attribute allows you to initialize properties on
the phantomjs `page` object, so

```js
options: {
  windowName: 'my-window',
  settings: {
    webSecurityEnabled: false
  },
}
```

is equivalent to:

```js
var webPage = require('webpage')
var page = webPage.create()

page.windowName = 'my-window'
page.settings.webSecurityEnabled = false
```

You can pass list of browsers as a CLI argument too:
```bash
$ karma start --browsers PhantomJS_custom
```

If you set the `debug` option to `true`, you will be instructed to launch a web browser to
bring up the debugger. Note that you will want to put `debugger;` statements in your JavaScript
to hit breakpoints. You should be able to put breakpoints in both your test code and your client
code. Note that the `debug` option automatically adds the `--remote-debugger-port=9000` and
`--remote-debugger-autorun=yes` switches to PhantomJS.

----

For more information on Karma see the [homepage].


[homepage]: http://karma-runner.github.com
[PhantomJS]: http://phantomjs.org/
