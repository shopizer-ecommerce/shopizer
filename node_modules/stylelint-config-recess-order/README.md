# Recess* Property Order [<img src="https://s3.amazonaws.com/media-p.slid.es/uploads/467124/images/2872758/stylelint-icon-black.svg" alt="StyleLint" width="90" height="90" align="right">][stylelint]

[![npm version][npm-img]][npm-url]
[![npm downloads][npm-dls]][npm-url]
[![dependency status][david-img]][david-url]
[![build status][travis-img]][travis-url]
[![github issues][issues-img]][issues-url]

A [Stylelint] config that sorts CSS properties the way [Recess] did and [Bootstrap] does.

*With some modifications & additions for modern properties.


## Usage
1. Add [stylelint] and this package to your project:  
    ```sh
    npm install --save-dev stylelint stylelint-config-recess-order
    ```
2. Configure your stylelint configuration file to extend this package:  
    ```js
    {
      "extends": "stylelint-config-recess-order",
      "rules": {
        // Add overrides and additional rules here
      }
    }
    ```


## References
[@mdo on CSS Property Order][mdo-order]


[npm-url]: https://www.npmjs.com/package/stylelint-config-recess-order
[npm-img]: https://img.shields.io/npm/v/stylelint-config-recess-order.svg?style=flat-square
[npm-dls]: https://img.shields.io/npm/dt/stylelint-config-recess-order.svg?style=flat-square
[david-url]: https://david-dm.org/stormwarning/stylelint-config-recess-order
[david-img]: https://img.shields.io/david/stormwarning/stylelint-config-recess-order.svg?style=flat-square
[travis-url]: https://travis-ci.org/stormwarning/stylelint-config-recess-order
[travis-img]: https://img.shields.io/travis/stormwarning/stylelint-config-recess-order.svg?style=flat-square
[issues-url]: https://github.com/stormwarning/stylelint-config-recess-order/issues
[issues-img]: https://img.shields.io/github/issues/stormwarning/stylelint-config-recess-order.svg?style=flat-square

[stylelint]: https://github.com/stylelint/stylelint
[Recess]: https://github.com/twitter/recess/blob/master/lib/lint/strict-property-order.js
[Bootstrap]: https://github.com/twbs/bootstrap/blob/v4-dev/scss/.scss-lint.yml#L128

[mdo-order]: http://markdotto.com/2011/11/29/css-property-order/
