var path = require('path');
process.env.PATH += path.delimiter + path.join(__dirname, 'chromedriver');
exports.path = process.platform === 'win32' ? path.join(__dirname, 'chromedriver', 'chromedriver.exe') : path.join(__dirname, 'chromedriver', 'chromedriver');
exports.version = '2.37';
exports.start = function(args) {
  exports.defaultInstance = require('child_process').execFile(exports.path, args);
  return exports.defaultInstance;
};
exports.stop = function () {
  if (exports.defaultInstance != null){
    exports.defaultInstance.kill();
  }
};
