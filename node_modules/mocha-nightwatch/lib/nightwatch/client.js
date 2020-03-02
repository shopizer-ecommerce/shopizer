module.exports = Nightwatch;

function Nightwatch(nightwatch, runner, test_settings) {
  this._instance = null;

  test_settings.output = false;
  this._instance = nightwatch.initClient(test_settings);

  this._instance.on('error', function(err) {
    runner.failOnError(err);
  }.bind(this));
}

Nightwatch.prototype.get = function() {
  return this._instance;
};
