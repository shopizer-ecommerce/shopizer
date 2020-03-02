import { SCHEMES } from "./uri";
import http from "./schemes/http";
SCHEMES["http"] = http;
import https from "./schemes/https";
SCHEMES["https"] = https;
import mailto from "./schemes/mailto";
SCHEMES["mailto"] = mailto;
import urn from "./schemes/urn";
SCHEMES["urn"] = urn;
import uuid from "./schemes/urn-uuid";
SCHEMES["urn:uuid"] = uuid;
export * from "./uri";
//# sourceMappingURL=index.js.map