"use strict";

const cssSyntax = require("./css-syntax");
const extname = {
	styl: "stylus",
	sss: "sugarss",
};

function defaultConfig (opts, lang) {
	let syntax;
	if (lang === "scss" || lang === "sass" || lang === "less") {
		syntax = require("postcss-" + lang);
	} else if (lang === "sugarss") {
		syntax = require("sugarss");
	} else {
		syntax = cssSyntax(opts);
	}
	return syntax;
}

function getSyntax (opts, lang) {
	if (!lang) {
		lang = opts.from && /\.(\w+)(?:\?.+)?$/.exec(opts.from);
		if (lang) {
			lang = lang[1].toLowerCase();
			lang = extname[lang] || lang;
		} else {
			lang = "css";
		}
	}

	let config = opts.syntax.config;

	if (!config) {
		return defaultConfig(opts, lang);
	}

	if (typeof config === "function") {
		config = config(opts, lang);
	}

	if (config) {
		config = config[lang] || config.css || config;
		if (typeof config === "string") {
			config = require(config);
		}
	} else {
		return defaultConfig(opts, lang);
	}

	return Object.assign(cssSyntax(opts), config);
}

module.exports = getSyntax;
