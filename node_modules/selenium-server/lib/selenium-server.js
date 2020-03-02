var path = require('path'),
    meta = require(__dirname + '/../package.json');

module.exports = {
    path: path.join(
        __dirname, 
        'runner', 
        'selenium-server-standalone-' + meta.version + '.jar'
    )
};
