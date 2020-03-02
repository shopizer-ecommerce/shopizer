import { URIComponents, URIOptions } from "../uri";
import http from "./http";

export default {
	scheme : "https",
	domainHost : http.domainHost,
	parse : http.parse,
	serialize : http.serialize
}
