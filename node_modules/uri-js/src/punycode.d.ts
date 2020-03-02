declare module 'punycode' {
	function ucs2decode(string:string):Array<number>;
	function ucs2encode(array:Array<number>):string;
	function decode(string:string):string;
	function encode(string:string):string;
	function toASCII(string:string):string;
	function toUnicode(string:string):string;

	export default {
		'version': '2.2.0',
		'ucs2': {
			'decode': ucs2decode,
			'encode': ucs2encode
		},
		'decode': decode,
		'encode': encode,
		'toASCII': toASCII,
		'toUnicode': toUnicode
	};
}
