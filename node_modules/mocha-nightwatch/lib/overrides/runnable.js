var create = require('lodash.create');
var MochaRunnable = require('../runnable');
var utils = require('../utils');

module.exports = Runnable;

function Runnable(title, fn) {
  this.title = title;
  this.fn = fn;
  this.async = 1;
  this.sync = false;
  this._timeout = 20000;
  this._slow = 75;
  this._enableTimeouts = true;
  this.timedOut = false;
  this._trace = new Error('done() called multiple times');
  this._retries = -1;
  this._currentRetry = 0;
  this.pending = false;
}

Runnable.prototype = create(MochaRunnable.prototype, {
  constructor: Runnable
});

Runnable.prototype.setNightwatchClient = function(client) {
  this._nightwatch = client;
  this._nightwatch.clearGlobalResult();
  return this;
};

/**
 * Run the test and invoke `fn(err)`.
 *
 * @api private
 * @param {Function} fn
 */
Runnable.prototype.run = function(fn) {
  var self = this;
  var start = new Date();
  var ctx = this.ctx;
  var finished;
  var emitted;

  // Sometimes the ctx exists, but it is not runnable
  if (ctx && ctx.runnable) {
    ctx.runnable(this);
  }

  // called multiple times
  function multiple(err) {
    if (emitted) {
      return;
    }
    emitted = true;
    self.emit('error', err || new Error('done() called multiple times; stacktrace may be inaccurate'));
  }

  // finished
  function done(err) {
    if (self.timedOut) {
      return;
    }

    if (finished) {
      return multiple(err || self._trace);
    }

    self.clearTimeout();
    self.duration = new Date() - start;
    finished = true;
    fn(err);
  }

  // for .resetTimeout()
  this.callback = done;

  try {
    if (this.type == 'test') {
      var module = [];
      if (this.parent.parent.title) {
        module.push(this.parent.parent.title);
      }
      module.push(this.parent.title);
      var moduleTitle = module.join('/').toLocaleLowerCase().replace(/\s+/g,'-');

      this._nightwatch.api('currentTest', {
        name : this.title,
        module : moduleTitle
      });
    }

    var args = [this._nightwatch.api()];

    if (this.type == 'hook') {
      args.push(done);
      // explicit async with `done` argument
      this.resetTimeout();
    }

    // TODO: support sync hooks
    this.fn.apply(ctx, args);
    if (this.type == 'hook' && this.title == '"before each" hook') {
      // don't restart the queue for before each
      return;
    }
    if (this.type == 'test') {
      this._nightwatch.once('complete', function() {
        self.duration = new Date() - start;
        finished = true;
        var results = this.results();
        var err = null;
        if (results.failed > 0 || results.errors > 0) {
          err = results.lastError;
        }
        fn(err);
      });
    }

    if (this._nightwatch.shouldRestartQueue()) {
      this._nightwatch.start();
    }
  } catch (err) {
    done(utils.getError(err));
  }
};
