"use strict";
const cssSyntax = require("./css-syntax");

function stringify (document, builder) {
	if (document.source.syntax) {
		return document.source.syntax.stringify(document, builder);
	} else {
		const firstOriginalRoot = document.nodes.find(root => root.source && root.source.syntax);
		let defaultSyntax;
		document.nodes.forEach((root, i) => {
			let syntax = root.source && root.source.syntax;
			if (syntax) {
				if (root.raws.beforeStart) {
					builder(root.raws.beforeStart, root, "beforeStart");
				}
				defaultSyntax = syntax;
			} else {
				if (!i && firstOriginalRoot) {
					builder(firstOriginalRoot.raws.beforeStart, root, "beforeStart");
					firstOriginalRoot.raws.beforeStart = "";
				}
				syntax = defaultSyntax || (
					defaultSyntax = firstOriginalRoot
						? firstOriginalRoot.source.syntax
						: cssSyntax({})
				);
			}
			if (root.source && root.source.isHTMLAttribute) {
				return syntax.stringify(root, string => {
					builder.apply(
						null,
						[
							string.replace(/[\r\n\t\s]+/g, " "),
						].concat(
							Array.from(arguments).slice(1)
						)
					);
				});
			} else {
				syntax.stringify(root, builder);
			}
		});
		builder(document.raws.afterEnd, document, "afterEnd");
	}
}

module.exports = stringify;
