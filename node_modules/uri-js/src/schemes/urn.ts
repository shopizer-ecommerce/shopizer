import { URIComponents, URIOptions } from "../uri";
import { pctEncChar, SCHEMES } from "../uri";

const NID$ = "(?:[0-9A-Za-z][0-9A-Za-z\\-]{1,31})";
const PCT_ENCODED$ = "(?:\\%[0-9A-Fa-f]{2})";
const TRANS$$ = "[0-9A-Za-z\\(\\)\\+\\,\\-\\.\\:\\=\\@\\;\\$\\_\\!\\*\\'\\/\\?\\#]";
const NSS$ = "(?:(?:" + PCT_ENCODED$ + "|" + TRANS$$ + ")+)";
const URN_SCHEME = new RegExp("^urn\\:(" + NID$ + ")$");
const URN_PATH = new RegExp("^(" + NID$ + ")\\:(" + NSS$ + ")$");
const URN_PARSE = /^([^\:]+)\:(.*)/;
const URN_EXCLUDED = /[\x00-\x20\\\"\&\<\>\[\]\^\`\{\|\}\~\x7F-\xFF]/g;

//RFC 2141
export default {
	scheme : "urn",

	parse : function (components:URIComponents, options:URIOptions):URIComponents {
		const matches = components.path && components.path.match(URN_PARSE);

		if (matches) {
			const scheme = "urn:" + matches[1].toLowerCase();
			let schemeHandler = SCHEMES[scheme];

			//in order to serialize properly,
			//every URN must have a serializer that calls the URN serializer
			if (!schemeHandler) {
				//create fake scheme handler
				schemeHandler = SCHEMES[scheme] = {
					scheme : scheme,
					parse : function (components:URIComponents, options:URIOptions):URIComponents {
						return components;
					},
					serialize : SCHEMES["urn"].serialize
				};
			}

			components.scheme = scheme;
			components.path = matches[2];

			components = schemeHandler.parse(components, options);
		} else {
			components.error = components.error || "URN can not be parsed.";
		}

		return components;
	},

	serialize : function (components:URIComponents, options:URIOptions):URIComponents {
		const scheme = components.scheme || options.scheme;

		if (scheme && scheme !== "urn") {
			const matches = scheme.match(URN_SCHEME) || ["urn:" + scheme, scheme];
			components.scheme = "urn";
			components.path = matches[1] + ":" + (components.path ? components.path.replace(URN_EXCLUDED, pctEncChar) : "");
		}

		return components;
	}
};
