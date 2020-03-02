"use strict";

const getSyntax = require("./get-syntax");

function autoParser (source, opts) {
	const syntax = getSyntax(opts);
	const document = syntax.parse(source, opts);
	// document.source.syntax = syntax;
	Object.defineProperty(document.source, "syntax", {
		get: () => syntax,
	});
	return document;
}

module.exports = autoParser;
