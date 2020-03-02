import { URIComponents, URIOptions } from "../uri";
import { SCHEMES } from "../uri";

const UUID = /^[0-9A-Fa-f]{8}(?:\-[0-9A-Fa-f]{4}){3}\-[0-9A-Fa-f]{12}$/;

//RFC 4122
export default {
	scheme : "urn:uuid",

	parse : function (components:URIComponents, options:URIOptions):URIComponents {
		if (!options.tolerant && (!components.path || !components.path.match(UUID))) {
			components.error = components.error || "UUID is not valid.";
		}
		return components;
	},

	serialize : function (components:URIComponents, options:URIOptions):URIComponents {
		//ensure UUID is valid
		if (!options.tolerant && (!components.path || !components.path.match(UUID))) {
			//invalid UUIDs can not have this scheme
			components.scheme = undefined;
		} else {
			//normalize UUID
			components.path = (components.path || "").toLowerCase();
		}

		return SCHEMES["urn"].serialize(components, options);
	}
};
