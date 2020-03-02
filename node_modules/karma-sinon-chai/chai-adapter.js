(function(window, karma) {
  window.should = null;
  window.should = window.chai.should();
  window.expect = window.chai.expect;
  window.assert = window.chai.assert;

  var chaiConfig = karma.config.chai;
  if (chaiConfig) {
    for (var key in chaiConfig) {
      if (chaiConfig.hasOwnProperty(key)) {
        window.chai.config[key] = chaiConfig[key];
      }
    }
  }

  karma.loaded = function() {
    karma.start();
  };
})(window, window.__karma__);
