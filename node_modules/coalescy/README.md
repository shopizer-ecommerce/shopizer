[![NPM Version](http://img.shields.io/npm/v/coalescy.svg?style=flat)](https://npmjs.org/package/coalescy)
[![Build Status](http://img.shields.io/travis/royriojas/coalescy.svg?style=flat)](https://travis-ci.org/royriojas/coalescy)

# coalescy
> Simple function that return the first non null or undefined argument passed to it

## Install

```bash
npm i --save coalescy
```

## Usage

`coalescy` simply return the first non nully of the passed elements. Null if all the values are null

it works the same as

```javascript
a || b
```

but it works on falsie values too

## Example

```javascript 
var clsc = require('coalescy');
var obj = clsc(null, []); // obj = [];
obj = clsc(null, {}); // obj = {};
obj = clsc(null, [], {}); // obj = []; // the first non null
obj = clsc(null, undefined, 0, []) // 0
```