# match-at [![Build Status](https://travis-ci.org/spicyj/match-at.svg?branch=master)](https://travis-ci.org/spicyj/match-at)

## Introduction

Like `String.prototype.match` if it only checked the regex at the given index instead of searching the entire string.

```js
matchAt(/world/, 'hello world', 6);  // ['world']
matchAt(/world/, 'hello world', 0);  // null
```

Almost like `'hello world'.slice(i).match(/^world/)` except the resulting match object's `.index` property corresponds to the original string, and it doesn't actually slice the string. Most engines optimize taking a substring so this probably isn't particularly valuable in practice, but it was an entertaining exercise and could be useful if you reminisce about these semantics.

## License

MIT.
