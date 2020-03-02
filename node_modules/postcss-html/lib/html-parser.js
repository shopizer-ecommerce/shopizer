"use strict";

const htmlparser = require("htmlparser2");

function iterateCode (source, onStyleTag, onStyleAttribute) {
	let currentStyle = null;
	let level = 0;
	let isFirstTag = true;

	function onFirstTag () {
		// Found a tag, the structure is now confirmed as HTML
		if (isFirstTag) {
			if (parser.startIndex <= 0 || !source.slice(0, parser.startIndex).trim()) {
				level = 2;
			}
			isFirstTag = false;
		}
	}

	const parser = new htmlparser.Parser({
		oncomment: onFirstTag,
		onprocessinginstruction: onFirstTag,
		onopentag (name, attribute) {
			onFirstTag();

			if (!level) {
				level = 1;
			}

			// Test if current tag is a valid <style> tag.
			if (name !== "style") {
				return;
			}
			currentStyle = {
				attribute,
				startIndex: parser.endIndex + 1,
			};
		},

		onclosetag (name) {
			if (name !== "style" || currentStyle === null) {
				return;
			}
			currentStyle.content = source.slice(currentStyle.startIndex, parser.startIndex);
			onStyleTag(currentStyle);
			currentStyle = null;
		},

		onattribute (name, value) {
			if (name !== "style") {
				return;
			}
			onStyleAttribute(value, parser.endIndex);
		},
	});

	parser.parseComplete(source);

	return level;
}

function getSubString (str, regexp) {
	const subStr = str && regexp.exec(str);
	if (subStr) {
		return subStr[1].toLowerCase();
	}
}

function getLang (attribute) {
	return getSubString(attribute.type, /^\w+\/(?:x-)?(\w+)$/i) || getSubString(attribute.lang, /^(\w+)(?:\?.+)?$/) || "css";
}

function htmlParser (source, opts) {
	const styles = [];
	function onStyleTag (style) {
		const firstNewLine = /^[ \t]*\r?\n/.exec(style.content);
		style.lang = getLang(style.attribute);
		if (firstNewLine) {
			const offset = firstNewLine[0].length;
			style.startIndex += offset;
			style.content = style.content.slice(offset);
		}
		style.content = style.content.replace(/[ \t]*$/, "");
		style.isHTMLTag = true;
		styles.push(style);
	}
	function onStyleAttribute (content, index) {
		styles.push({
			content: content,
			startIndex: source.indexOf(content, index),
			isHTMLAttribute: true,
		});
	}
	const level = iterateCode(source, onStyleTag, onStyleAttribute);

	if (opts.from ? !level : level < 2) {
		return;
	}

	return styles;
}

module.exports = htmlParser;
