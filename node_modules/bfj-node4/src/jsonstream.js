'use strict'

const util = require('util')
const Readable = require('stream').Readable
const check = require('check-types')

util.inherits(JsonStream, Readable)

module.exports = JsonStream

function JsonStream (read) {
  if (check.not.instanceStrict(this, JsonStream)) {
    return new JsonStream(read)
  }

  check.assert.function(read, 'Invalid read implementation')

  this._read = function () { // eslint-disable-line no-underscore-dangle
    read()
  }

  return Readable.call(this, { encoding: 'utf8' })
}
