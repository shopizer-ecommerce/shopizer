var path = require('path')
var extend = require('util')._extend
var minimist = require('minimist')

var createPattern = function (path) {
  return {pattern: path, included: true, served: true, watched: false}
}

var initMocha = function (files, config) {
  var mochaPath = path.dirname(require.resolve('mocha'))
  files.unshift(createPattern(path.join(__dirname, 'adapter.js')))

  config = config || {}
  config.client = config.client || {}
  var mochaConfig = config.client.mocha = getMochaOpts(config.client.mocha || {})

  if (mochaConfig.require && mochaConfig.require.map) {
    mochaConfig.require.map(function (requirePath) {
      return files.unshift(createPattern(requirePath))
    })
  }

  files.unshift(createPattern(path.join(mochaPath, 'mocha.js')))

  if (mochaConfig.reporter) {
    files.unshift(createPattern(path.join(mochaPath, 'mocha.css')))
  }
}

initMocha.$inject = ['config.files', 'config']

module.exports = {
  'framework:mocha': ['factory', initMocha]
}

function getMochaOpts (mochaConfig) {
  var optsPath = typeof mochaConfig.opts === 'string' ? mochaConfig.opts : 'test/mocha.opts'

  if (!mochaConfig.opts) {
    return mochaConfig
  }

  delete mochaConfig.opts

  var fs = require('fs')
  if (!fs.existsSync(optsPath)) {
    return mochaConfig
  }

  return extend(normalizeOpts(minimist(fs.readFileSync(optsPath, 'utf8')
    .replace(/\\\s/g, '%20')
    .split(/\s/)
    .filter(Boolean)
    .map(function (value) {
      return value.replace(/%20/g, ' ')
    }))), mochaConfig)

  function normalizeOpts (opts) {
    opts = [
      'require',

      'ui',
      'reporter',
      'globals',
      'grep',
      'timeout',
      'slow',
      'bail',
      'ignoreLeaks'
    ].reduce(function (result, optName) {
      if (opts.hasOwnProperty(optName)) {
        result[optName] = opts[optName]
      }

      return result
    }, {})

    opts.require = [].concat(opts.require || [])

    return opts
  }
}
