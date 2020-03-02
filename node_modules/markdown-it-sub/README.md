# markdown-it-sub

[![Build Status](https://img.shields.io/travis/markdown-it/markdown-it-sub/master.svg?style=flat)](https://travis-ci.org/markdown-it/markdown-it-sub)
[![NPM version](https://img.shields.io/npm/v/markdown-it-sub.svg?style=flat)](https://www.npmjs.org/package/markdown-it-sub)
[![Coverage Status](https://img.shields.io/coveralls/markdown-it/markdown-it-sub/master.svg?style=flat)](https://coveralls.io/r/markdown-it/markdown-it-sub?branch=master)

> Subscript (`<sub>`) tag plugin for [markdown-it](https://github.com/markdown-it/markdown-it) markdown parser.

__v1.+ requires `markdown-it` v4.+, see changelog.__

`H~2~0` => `H<sub>2</sub>O`

Markup is based on [pandoc](http://johnmacfarlane.net/pandoc/README.html#superscripts-and-subscripts) definition. But nested markup is currently not supported.


## Install

node.js, browser:

```bash
npm install markdown-it-sub --save
bower install markdown-it-sub --save
```

## Use

```js
var md = require('markdown-it')()
            .use(require('markdown-it-sub'));

md.render('H~2~0') // => '<p>H<sub>2</sub>O</p>'
```

_Differences in browser._ If you load script directly into the page, without
package system, module will add itself globally as `window.markdownitSub`.


## License

[MIT](https://github.com/markdown-it/markdown-it-sub/blob/master/LICENSE)
