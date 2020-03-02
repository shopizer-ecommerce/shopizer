var create = require('lodash.create');
var utils = require('../utils');
var filter = utils.filter;
var indexOf = utils.indexOf;
var some = utils.some;
var NightwatchClient = require('../nightwatch/client');
var MochaRunner = require('../runner');
module.exports = Runner;

function Runner(suite, delay) {
  MochaRunner.call(this, suite, delay);
}

Runner.prototype = create(MochaRunner.prototype, {
  constructor : Runner
});

Runner.prototype.run = function(nightwatch, test_settings, fn) {
  var self = this;
  var rootSuite = this.suite;
  var client = new NightwatchClient(nightwatch, this, test_settings);

  if (this.hasOnly) {
    filterOnly(rootSuite);
  }

  fn = fn || function() {};

  function uncaught(err) {
    self.uncaught(err);
  }

  function setClient(test) {
    test.setNightwatchClient(client.get());
  }

  function start() {
    self.emit('start');
    self.runSuite(rootSuite, function() {
      self.emit('end');
    });

    self.on('test', setClient);
    self.on('hook', setClient);
  }

  // callback
  this.on('end', function() {
    process.removeListener('uncaughtException', uncaught);
    fn(self.failures);
  });

  // uncaught exception
  process.on('uncaughtException', uncaught);

  if (this._delay) {
    // for reporters, I guess.
    // might be nice to debounce some dots while we wait.
    this.emit('waiting', rootSuite);
    rootSuite.once('run', start);
  } else {
    start();
  }

  return this;
};

Runner.prototype.failOnError = function(err) {
  var runnable = this.currentRunnable;
  if (!runnable) {
    return;
  }

  runnable.clearTimeout();

  // Ignore errors if complete
  if (runnable.state) {
    return;
  }
  this.fail(runnable, err);

  // recover from test
  if (runnable.type === 'test') {
    this.emit('test end', runnable);
    this.hookUp('afterEach', this.next);
    return;
  }

  // bail on hooks
  this.emit('end');
};

/**
 * Filter suites based on `isOnly` logic.
 *
 * @param {Array} suite
 * @returns {Boolean}
 * @api private
 */
function filterOnly (suite) {
  if (suite._onlyTests.length) {
    // If the suite contains `only` tests, run those and ignore any nested suites.
    suite.tests = suite._onlyTests;
    suite.suites = [];
  } else {
    // Otherwise, do not run any of the tests in this suite.
    suite.tests = [];
    utils.forEach(suite._onlySuites, function (onlySuite) {
      // If there are other `only` tests/suites nested in the current `only` suite, then filter that `only` suite.
      // Otherwise, all of the tests on this `only` suite should be run, so don't filter it.
      if (hasOnly(onlySuite)) {
        filterOnly(onlySuite);
      }
    });
    // Run the `only` suites, as well as any other suites that have `only` tests/suites as descendants.
    suite.suites = filter(suite.suites, function (childSuite) {
      return indexOf(suite._onlySuites, childSuite) !== -1 || filterOnly(childSuite);
    });
  }
  // Keep the suite only if there is something to run
  return suite.tests.length || suite.suites.length;
}

/**
 * Determines whether a suite has an `only` test or suite as a descendant.
 *
 * @param {Array} suite
 * @returns {Boolean}
 * @api private
 */
function hasOnly (suite) {
  return suite._onlyTests.length || suite._onlySuites.length || some(suite.suites, hasOnly);
}