var path = require('path');

var pattern = function(file) {
  return {pattern: file, included: true, served: true, watched: false};
};

var endsWith = function(substr) {
  return function(str) {
    return str.indexOf(substr) >= 0 && str.indexOf(substr) === (str.length - substr.length);
  };
};

var _isDuplicate = function(files, file) {
  var result = false;
  for (var i = 0; i < files.length; i++) {
    if (files[i]) {
      var pattern = files[i].pattern;
      result = result || endsWith(path.relative(__dirname, file))(pattern);
    }
  }
  return result;
};

var framework = function(files) {
  var isDuplicate = _isDuplicate.bind(this, files);

  /* Lolex */
  var lolexPath = path.dirname(require.resolve('lolex/package.json')) + '/lolex.js';

  /* Sinon */
  var sinonPath = path.dirname(require.resolve('sinon/package.json')) + '/pkg/sinon.js';
  if (!isDuplicate(sinonPath)) {
    files.unshift(pattern(lolexPath));
    files.unshift(pattern(sinonPath));
  }

  /* Chai */
  var chaiPath = path.dirname(require.resolve('chai/package.json')) + '/chai.js';
  if (!isDuplicate(chaiPath)) {
    files.unshift(pattern(chaiPath));
    files.push(pattern(path.join(__dirname, 'chai-adapter.js')));
  }

  /* Sinon-Chai */
  var sinonChaiPath = path.resolve(require.resolve('sinon-chai'));
  if (!isDuplicate(sinonChaiPath)) {
    files.push(pattern(sinonChaiPath))
  }
};

framework.$inject = ['config.files'];
module.exports = {'framework:sinon-chai': ['factory', framework]};
