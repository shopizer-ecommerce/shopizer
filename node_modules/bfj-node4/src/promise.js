'use strict'

module.exports = options => options && options.Promise || require('bluebird')
