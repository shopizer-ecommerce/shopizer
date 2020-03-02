"use strict";

const parser = require("./parser");
const autoParser = require("./auto-parser");

function parse (source, opts) {
	if (!opts.syntax) {
		opts.syntax = this;
	}
	return parser(source, opts) || autoParser(source, opts);
}

module.exports = parse;
