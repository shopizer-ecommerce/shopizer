'use strict';

var extractZip = require('extract-zip');
var fs = require('fs');
var helper = require('./lib/chromedriver');
var request = require('request');
var kew = require('kew');
var mkdirp = require('mkdirp');
var path = require('path');
var del = require('del');
var util = require('util');

var libPath = path.join(__dirname, 'lib', 'chromedriver');
var cdnUrl = process.env.npm_config_chromedriver_cdnurl || process.env.CHROMEDRIVER_CDNURL || 'https://chromedriver.storage.googleapis.com';
var configuredfilePath = process.env.npm_config_chromedriver_filepath || process.env.CHROMEDRIVER_FILEPATH;

// adapt http://chromedriver.storage.googleapis.com/
cdnUrl = cdnUrl.replace(/\/+$/, '');
var downloadUrl = cdnUrl + '/%s/chromedriver_%s.zip';
var platform = process.platform;

var chromedriver_version = process.env.npm_config_chromedriver_version || process.env.CHROMEDRIVER_VERSION || helper.version;
if (platform === 'linux') {
  if (process.arch === 'x64') {
    platform += '64';
  } else {
    console.log('Only Linux 64 bits supported.');
    process.exit(1);
  }
} else if (platform === 'darwin' || platform === 'freebsd') {
  if (process.arch === 'x64') {
    platform = 'mac64';
  } else {
    console.log('Only Mac 64 bits supported.');
    process.exit(1);
  }
} else if (platform !== 'win32') {
  console.log('Unexpected platform or architecture:', process.platform, process.arch);
  process.exit(1);
}

var tmpPath = findSuitableTempDirectory();
var downloadedFile = '';
var promise = kew.resolve(true);

promise = promise.then(function () {
  if (chromedriver_version === 'LATEST')
    return getLatestVersion(getRequestOptions(cdnUrl + '/LATEST_RELEASE'));
});

// Start the install.
promise = promise.then(function () {
  if (configuredfilePath) {
    console.log('Using file: ', configuredfilePath);
    downloadedFile = configuredfilePath;
  } else {
    downloadUrl = util.format(downloadUrl, chromedriver_version, platform);
    var fileName = downloadUrl.split('/').pop();
    downloadedFile = path.join(tmpPath, fileName);
    console.log('Downloading', downloadUrl);
    console.log('Saving to', downloadedFile);
    return requestBinary(getRequestOptions(downloadUrl), downloadedFile);
  }
});

promise.then(function () {
  return extractDownload(downloadedFile, tmpPath);
})
  .then(function () {
    return copyIntoPlace(tmpPath, libPath);
  })
  .then(function () {
    return fixFilePermissions();
  })
  .then(function () {
    console.log('Done. ChromeDriver binary available at', helper.path);
  })
  .fail(function (err) {
    console.error('ChromeDriver installation failed', err);
    process.exit(1);
  });


function findSuitableTempDirectory() {
  var now = Date.now();
  var candidateTmpDirs = [
    process.env.TMPDIR || process.env.TMP || process.env.npm_config_tmp,
    '/tmp',
    path.join(process.cwd(), 'tmp')
  ];

  for (var i = 0; i < candidateTmpDirs.length; i++) {
    if (!candidateTmpDirs[i]) continue;
    var candidatePath = path.join(candidateTmpDirs[i], 'chromedriver');
    try {
      mkdirp.sync(candidatePath, '0777');
      var testFile = path.join(candidatePath, now + '.tmp');
      fs.writeFileSync(testFile, 'test');
      fs.unlinkSync(testFile);
      return candidatePath;
    } catch (e) {
      console.log(candidatePath, 'is not writable:', e.message);
    }
  }

  console.error('Can not find a writable tmp directory, please report issue on https://github.com/giggio/chromedriver/issues/ with as much information as possible.');
  process.exit(1);
}


function getRequestOptions(downloadPath) {
  var options = {uri: downloadPath, method: 'GET'};
  var protocol = options.uri.substring(0, options.uri.indexOf('//'));
  var proxyUrl = protocol === 'https:'
    ? process.env.npm_config_https_proxy
    : (process.env.npm_config_proxy || process.env.npm_config_http_proxy);
  if (proxyUrl) {
    options.proxy = proxyUrl;
  }

  options.strictSSL = !!process.env.npm_config_strict_ssl;

  // Use certificate authority settings from npm
  var ca = process.env.npm_config_ca;

  // Parse ca string like npm does
  if (ca && ca.match(/^".*"$/)) {
    try {
      ca = JSON.parse(ca.trim());
    } catch (e) {
      console.error('Could not parse ca string', process.env.npm_config_ca, e);
    }
  }

  if (!ca && process.env.npm_config_cafile) {
    try {
      ca = fs.readFileSync(process.env.npm_config_cafile, {encoding: 'utf8'})
        .split(/\n(?=-----BEGIN CERTIFICATE-----)/g);

      // Comments at the beginning of the file result in the first
      // item not containing a certificate - in this case the
      // download will fail
      if (ca.length > 0 && !/-----BEGIN CERTIFICATE-----/.test(ca[0])) {
        ca.shift();
      }

    } catch (e) {
      console.error('Could not read cafile', process.env.npm_config_cafile, e);
    }
  }

  if (ca) {
    console.log('Using npmconf ca');
    options.agentOptions = {
      ca: ca
    };
    options.ca = ca;
  }

  // Use specific User-Agent
  if (process.env.npm_config_user_agent) {
    options.headers = {'User-Agent': process.env.npm_config_user_agent};
  }

  return options;
}

function getLatestVersion(requestOptions) {
  var deferred = kew.defer();
  request(requestOptions, function (err, response, data) {
    if (err) {
      deferred.reject('Error with http(s) request: ' + err);
    } else {
      chromedriver_version = data.trim();
      deferred.resolve(true);
    }
  });
  return deferred.promise;
}

function requestBinary(requestOptions, filePath) {
  var deferred = kew.defer();

  var count = 0;
  var notifiedCount = 0;
  var outFile = fs.openSync(filePath, 'w');

  var client = request(requestOptions);

  client.on('error', function (err) {
    deferred.reject('Error with http(s) request: ' + err);
  });

  client.on('data', function (data) {
    fs.writeSync(outFile, data, 0, data.length, null);
    count += data.length;
    if ((count - notifiedCount) > 800000) {
      console.log('Received ' + Math.floor(count / 1024) + 'K...');
      notifiedCount = count;
    }
  });

  client.on('end', function () {
    console.log('Received ' + Math.floor(count / 1024) + 'K total.');
    fs.closeSync(outFile);
    deferred.resolve(true);
  });

  return deferred.promise;
}

function extractDownload(filePath, tmpPath) {
  var deferred = kew.defer();
  console.log('Extracting zip contents');
  extractZip(path.resolve(filePath), { dir: tmpPath }, function (err) {
    if (err) {
      deferred.reject('Error extracting archive: ' + err);
    } else {
      deferred.resolve(true);
    }
  });
  return deferred.promise;
}


function copyIntoPlace(tmpPath, targetPath) {
  return del(targetPath)
    .then(function() {
      console.log("Copying to target path", targetPath);
      fs.mkdirSync(targetPath);

      // Look for the extracted directory, so we can rename it.
      var files = fs.readdirSync(tmpPath);
      var promises = files.map(function(name) {
        var deferred = kew.defer();

        var file = path.join(tmpPath, name);
        var reader = fs.createReadStream(file);

        var targetFile = path.join(targetPath, name);
        var writer = fs.createWriteStream(targetFile);
        writer.on("close", function() {
          deferred.resolve(true);
        });

        reader.pipe(writer);
        return deferred.promise;
      });
      return kew.all(promises);
    });
}


function fixFilePermissions() {
  // Check that the binary is user-executable and fix it if it isn't (problems with unzip library)
  if (process.platform != 'win32') {
    var stat = fs.statSync(helper.path);
    // 64 == 0100 (no octal literal in strict mode)
    if (!(stat.mode & 64)) {
      console.log('Fixing file permissions');
      fs.chmodSync(helper.path, '755');
    }
  }
}
