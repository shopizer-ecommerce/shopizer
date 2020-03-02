"use strict";

const stringify = require("postcss/lib/stringify");
const parse = require("postcss/lib/parse");

module.exports = opts => ({
	stringify,
	parse: opts.fix ? require("postcss-safe-parser") : parse,
});
