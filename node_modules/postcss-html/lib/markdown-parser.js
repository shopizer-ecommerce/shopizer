"use strict";

const remark = require("remark");
const findAllAfter = require("unist-util-find-all-after");

module.exports = function (source, opts) {
	// Skip known non-markdown files.
	if (opts.from && !/\.(?:md|markdown)(?:\?.+)?$/i.test(opts.from)) {
		return;
	}
	let isStructureMD = false;
	const ast = remark().parse(source);
	const blocks = findAllAfter(ast, 0, (node) => {
		if (node.type === "code" && node.lang) {
			isStructureMD = true;
			return /^(?:(?:[ps]?c)|le|wx|sa?|sugar)ss$/i.test(node.lang);
		}
	});

	if (!isStructureMD) {
		return;
	}

	return blocks.map((block) => {
		const startIndex = source.indexOf(block.value, block.position.start.offset);
		const content = source.slice(startIndex, block.position.end.offset).replace(/[ \t]*`*$/, "");
		return {
			startIndex: startIndex,
			lang: block.lang.toLowerCase(),
			isMarkdown: true,
			content: content,
		};
	});
};
