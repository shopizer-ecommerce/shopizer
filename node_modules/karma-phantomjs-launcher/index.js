var fs = require('fs')
var path = require('path')
var _ = require('lodash')

function serializeOption (value) {
  if (typeof value === 'function') {
    return value.toString()
  }
  return JSON.stringify(value)
}

var phantomJSExePath = function () {
  // If the path we're given by phantomjs is to a .cmd, it is pointing to a global copy.
  // Using the cmd as the process to execute causes problems cleaning up the processes
  // so we walk from the cmd to the phantomjs.exe and use that instead.
  var phantomSource = require('phantomjs-prebuilt').path

  if (path.extname(phantomSource).toLowerCase() === '.cmd') {
    var phantomPackage = require('phantomjs-prebuilt/package.json')
    return path.join(
      path.dirname(phantomSource),
      ['', 'node_modules', phantomPackage._location, 'lib', 'phantom', ''].join('//'),
      phantomPackage.bin.phantomjs
    )
  }

  return phantomSource
}

var PhantomJSBrowser = function (baseBrowserDecorator, config, args, logger) {
  var log = logger.create('phantomjs.launcher')

  baseBrowserDecorator(this)

  var options = args && args.options || config && config.options || {}
  var providedFlags = args && args.flags || config && config.flags || []

  this._start = function (url) {
    // create the js file that will open karma
    var captureFile = this._tempDir + '/capture.js'
    var pageOptions = {}
    var pageSettingsOptions = {}

    _.forOwn(options, function (optionsValue, optionsKey) {
      if (optionsKey !== 'settings') { // settings cannot be overriden, it should be extended!
        pageOptions[optionsKey] = serializeOption(optionsValue)
      } else {
        // key === settings
        _.forOwn(optionsValue, function (settingsValue, settingsKey) {
          pageSettingsOptions[settingsKey] = serializeOption(settingsValue)
        })
      }
    })

    var file = fs.readFileSync(path.join(__dirname, 'capture.template.js'))

    var compiled = _.template(file.toString())
    var captureCode = compiled({
      debug: args.debug,
      exitOnResourceError: config && config.exitOnResourceError,
      pageOptions: pageOptions,
      pageSettingsOptions: pageSettingsOptions,
      url: url
    })

    fs.writeFileSync(captureFile, captureCode)

    // PhantomJS has the following convention for arguments
    // phantomjs [switchs] [options] [script] [argument [argument [...]]]
    // with  options being equal to flags
    var flags = providedFlags
    if (args.debug) {
      flags.push('--remote-debugger-port=9000')
      flags.push('--remote-debugger-autorun=yes')
    }

    flags.push(captureFile)

    // and start phantomjs
    this._execCommand(this._getCommand(), flags)

    this._process.stderr.on('data', function (data) {
      log.error('' + data)
    })

    this._process.stdout.on('data', function (data) {
      log.debug('' + data)
    })

    if (args.debug) {
      log.info('ACTION REQUIRED:')
      log.info('')
      log.info('  Launch browser at')
      log.info('  http://localhost:9000/webkit/inspector/inspector.html?page=2')
      log.info('')
      log.info('Waiting 15 seconds ...')
    }
  }
}

PhantomJSBrowser.prototype = {
  name: 'PhantomJS',

  DEFAULT_CMD: {
    linux: require('phantomjs-prebuilt').path,
    darwin: require('phantomjs-prebuilt').path,
    win32: phantomJSExePath()
  },
  ENV_CMD: 'PHANTOMJS_BIN'
}

PhantomJSBrowser.$inject = ['baseBrowserDecorator', 'config.phantomjsLauncher', 'args', 'logger']

// PUBLISH DI MODULE
module.exports = {
  'launcher:PhantomJS': ['type', PhantomJSBrowser]
}
