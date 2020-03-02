"use strict";

const stringify = require("./stringify");
const parse = require("./parse");

function initSyntax (syntax) {
	syntax.stringify = stringify.bind(syntax);
	syntax.parse = parse.bind(syntax);
	return syntax;
}

function syntax (config) {
	return initSyntax({
		config,
	});
}

initSyntax(syntax);
module.exports = syntax;
