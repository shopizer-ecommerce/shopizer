;(function (window) {

// backwards compatible version of (Array|String).prototype.includes
var includes = function (collection, element, startIndex) {
  if (!collection || !collection.length) {
    return false
  }

  // strings support indexOf already
  if (typeof collection === 'string') {
    return collection.indexOf(element, startIndex) !== -1
  }

  if (Array.prototype.indexOf) {
    return collection.indexOf(element, startIndex) !== -1
  }

  for (var i = startIndex || 0, len = collection.length; i < len; i++) {
    if (collection[i] === element) {
      return true
    }
  }
}

// Date.now polyfill for IE <= 8
if (!Date.now) {
  Date.now = function () {
    return +new Date()
  }
}

var formatError = function (error) {
  var stack = error.stack
  var message = error.message

  if (stack) {
    if (message && !includes(stack, message)) {
      stack = message + '\n' + stack
    }

    // remove mocha stack entries
    return stack.replace(/\n.+\/mocha\/mocha\.js\?\w*:[\d:]+\)?(?=(\n|$))/g, '')
  }

  return message
}

var processAssertionError = function (error_) {
  var error

  if (window.Mocha && error_.hasOwnProperty('showDiff')) {
    error = {
      name: error_.name,
      message: error_.message,
      showDiff: error_.showDiff
    }

    if (error.showDiff) {
      error.actual = window.Mocha.utils.stringify(error_.actual)
      error.expected = window.Mocha.utils.stringify(error_.expected)
    }
  }

  return error
}

// non-compliant version of Array::reduce.call (requires memo argument)
var arrayReduce = function (array, reducer, memo) {
  for (var i = 0, len = array.length; i < len; i++) {
    memo = reducer(memo, array[i])
  }
  return memo
}

var createMochaReporterNode = function () {
  var mochaRunnerNode = document.createElement('div')
  mochaRunnerNode.setAttribute('id', 'mocha')
  document.body.appendChild(mochaRunnerNode)
}

var haveMochaConfig = function (karma) {
  return karma.config && karma.config.mocha
}

var createMochaReporterConstructor = function (tc, pathname) {
  var isDebugPage = /debug.html$/.test(pathname)

  // Set custom reporter on debug page
  if (isDebugPage && haveMochaConfig(tc) && tc.config.mocha.reporter) {
    createMochaReporterNode()
    return tc.config.mocha.reporter
  }

  // TODO(vojta): error formatting
  return function (runner) {
    // runner events
    // - start
    // - end
    // - suite
    // - suite end
    // - test
    // - test end
    // - pass
    // - fail
    // - pending

    runner.on('start', function () {
      tc.info({total: runner.total})
    })

    runner.on('end', function () {
      tc.complete({
        coverage: window.__coverage__
      })
    })

    runner.on('test', function (test) {
      test.$startTime = Date.now()
      test.$errors = []
      test.$assertionErrors = []
    })

    runner.on('pending', function (test) {
      test.pending = true
    })

    runner.on('fail', function (test, error) {
      var simpleError = formatError(error)
      var assertionError = processAssertionError(error)

      if (test.type === 'hook') {
        test.$errors = isDebugPage ? [error] : [simpleError]
        test.$assertionErrors = assertionError ? [assertionError] : []
        runner.emit('test end', test)
      } else {
        test.$errors.push(isDebugPage ? error : simpleError)
        if (assertionError) test.$assertionErrors.push(assertionError)
      }
    })

    runner.on('test end', function (test) {
      var skipped = test.pending === true

      var result = {
        id: '',
        description: test.title,
        suite: [],
        success: test.state === 'passed',
        skipped: skipped,
        time: skipped ? 0 : test.duration,
        log: test.$errors || [],
        assertionErrors: test.$assertionErrors || [],
        startTime: test.$startTime,
        endTime: Date.now()
      }

      var pointer = test.parent
      while (!pointer.root) {
        result.suite.unshift(pointer.title)
        pointer = pointer.parent
      }

      if (haveMochaConfig(tc) && tc.config.mocha.expose && tc.config.mocha.expose.forEach) {
        result.mocha = {}
        tc.config.mocha.expose.forEach(function (prop) {
          if (test.hasOwnProperty(prop)) {
            result.mocha[prop] = test[prop]
          }
        })
      }

      tc.result(result)
    })
  }
}
/* eslint-disable no-unused-vars */
var createMochaStartFn = function (mocha) {
  /* eslint-enable no-unused-vars */
  return function (config) {
    var clientArguments
    config = config || {}
    clientArguments = config.args

    if (clientArguments) {
      if (Object.prototype.toString.call(clientArguments) === '[object Array]') {
        arrayReduce(clientArguments, function (isGrepArg, arg) {
          if (isGrepArg) {
            mocha.grep(new RegExp(arg))
          } else if (arg === '--grep') {
            return true
          } else {
            var match = /--grep=(.*)/.exec(arg)

            if (match) {
              mocha.grep(new RegExp(match[1]))
            }
          }
          return false
        }, false)
      }

      /**
       * TODO(maksimrv): remove when karma-grunt plugin will pass
       * clientArguments how Array
       */
      if (clientArguments.grep) {
        mocha.grep(clientArguments.grep)
      }
    }

    mocha.run()
  }
}

// Default configuration
var mochaConfig = {
  reporter: createMochaReporterConstructor(window.__karma__, window.location.pathname),
  ui: 'bdd',
  globals: ['__cov*']
}

// Pass options from client.mocha to mocha
/* eslint-disable no-unused-vars */
var createConfigObject = function (karma) {
  /* eslint-enable no-unused-vars */

  if (!karma.config || !karma.config.mocha) {
    return mochaConfig
  }

  // Copy all properties to mochaConfig
  for (var key in karma.config.mocha) {
    // except for reporter, require, or expose
    if (includes(['reporter', 'require', 'expose'], key)) {
      continue
    }

    // and merge the globals if they exist.
    if (key === 'globals') {
      mochaConfig.globals = mochaConfig.globals.concat(karma.config.mocha[key])
      continue
    }

    mochaConfig[key] = karma.config.mocha[key]
  }
  return mochaConfig
}

  window.__karma__.start = createMochaStartFn(window.mocha)
  window.mocha.setup(createConfigObject(window.__karma__))
})(window)
