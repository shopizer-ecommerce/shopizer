var path = require('path');

var init = function(files) {
  files.unshift({
    pattern: path.join(__dirname, 'shim.js'),
    included: true,
    served: true,
    watched: false
  });
};

init.$inject = ['config.files'];

module.exports = {
  'framework:phantomjs-shim': ['factory', init]
};
