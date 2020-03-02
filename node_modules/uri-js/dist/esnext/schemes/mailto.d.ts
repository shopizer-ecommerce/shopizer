import { URIComponents, URIOptions } from "../uri";
export interface MailtoHeaders {
    [hfname: string]: string;
}
export interface MailtoComponents extends URIComponents {
    to: Array<string>;
    headers?: MailtoHeaders;
    subject?: string;
    body?: string;
}
declare var _default: {
    scheme: string;
    parse: (components: MailtoComponents, options: URIOptions) => MailtoComponents;
    serialize: (components: MailtoComponents, options: URIOptions) => URIComponents;
};
export default _default;
