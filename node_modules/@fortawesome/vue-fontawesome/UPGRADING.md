# Upgrading Guide

See the [CHANGELOG.md](./CHANGELOG.md) for detailed information about what has changed between versions.

This guide is useful to figure out what you need to do between breaking changes.

As always, [submit issues](https://github.com/FortAwesome/vue-fontawesome/issues/new) that you run into with this guide or with these upgrades to us.

## 0.0.x to 0.1.0

### Renamed packages

The following packages have been renamed as part of 5.1.0 of Font Awesome.

_All packages are in the [@fortawesome NPM scope](https://www.npmjs.com/search?q=scope:fortawesome&page=1&ranking=optimal)_

| Old package(1)           | New package            |
|--------------------------|------------------------|
| fontawesome              | fontawesome-svg-core   |
| fontawesome-free-solid   | free-solid-svg-icons   |
| fontawesome-free-regular | free-regular-svg-icons |
| fontawesome-free-brands  | free-brands-svg-icons  |
| fontawesome-pro-solid    | pro-solid-svg-icons    |
| fontawesome-pro-regular  | pro-regular-svg-icons  |
| fontawesome-pro-light    | pro-light-svg-icons    |

(1) Old packages have now been deprecated. They are still available but will only receive high priority patch release fixes.

**You'll need to update your package.json file with the renamed packages and new versions.**

### No more default imports

Recently we spent a good deal of time supporting TypeScript to enable us to
create the Angular Font Awesome component. During that adventure we
[were](https://basarat.gitbooks.io/typescript/docs/tips/defaultIsBad.html)
[convinced](https://blog.neufund.org/why-we-have-banned-default-exports-and-you-should-do-the-same-d51fdc2cf2ad)
that we were going to remove default exports from all of our components,
libraries, and packages. This is complete with the umbrella release of `5.1.0` of Font Awesome.

What does that mean?

~~Old way:~~

```javascript
import fontawesome from '@fortawesome/fontawesome'
import solid from '@fortawesome/fontawesome-free-solid'
import faTwitter from '@fortawesome/fontawesome-free-brands/faTwitter'
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

import fontaweome.library.add(solid, faTwitter)
```

New way:

```javascript
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/fontawesome-free-solid'
import { faTwitter } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import library.add(fas, faTwitter)
```

This is also a valid way to import icons that works if your tool does not support tree shaking:

```javascript
import { faTwitter } from '@fortawesome/free-brands-svg-icons/faTwitter'
```

### Improved support for tree shaking

Tree shaking is now functional by default and no additional configuration is required to make it work.

The `shakable.es.js` module has been removed and is no longer needed.

If you've previously configured tree shaking by modifying your webpack or rollup you can safely remove these.

**We recommend that you check your bundle size after upgrading an ensure that file sizes are as you would expect.**

```javascript
module.exports = {
  // ...
  resolve: {
    alias: {
      '@fortawesome/fontawesome-free-solid$': '@fortawesome/fontawesome-free-solid/shakable.es.js'
    }
  }
}
```

```javascript
const alias = require('rollup-plugin-alias')

rollup({
  // ...
  plugins: [
    alias({
      '@fortawesome/fontawesome-free-solid': 'node_modules/@fortawesome/fontawesome-free-solid/shakable.es.js'
    })
  ]
})
```

### Mixed modes with automatic replacement of `<i>` tags to `<svg>`

If you were previously relying on Font Awesome to replace any `<i>` tags in
your page or app with `<svg>` you'll need to explicitly control that now.

```javascript
import { dom } from '@fortawesome/fontawesome-svg-core'

dom.watch() // This will kick of the replacement of i tags and configure a MutationObserver
```
