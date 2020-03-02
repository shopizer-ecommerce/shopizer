=== HEAD

=== 3.1.0 (October 11, 2017)

* Allow both `/**` and `/*` for module definitions [#125](https://github.com/postcss/postcss-bem-linter/pull/125)

=== 3.0.0 (July 21, 2017)

* Update PostCSS to `^6.0.6` - Drops support for Node 0.12
* Ignore underscore on implicitComponent to allow Scss partials [#115](https://github.com/postcss/postcss-bem-linter/pull/115)
* Use parent directory for implicitComponent if filename is 'index.css' [commit](https://github.com/postcss/postcss-bem-linter/commit/ad0bd56ea0721a3522067dfcc6aec0d0880bbe2d)
* Lint rulesets that only have an @extend keyword [#119](https://github.com/postcss/postcss-bem-linter/pull/119)
* Switch to Jest for unit tests
* Use Airbnb ESLint configuration

=== 2.7.1 (June 06, 2017)

* Fix issue with nested rules when using LESS syntax [#114](https://github.com/postcss/postcss-bem-linter/pull/114)

=== 2.7.0 (February 28, 2017)

* Add implicitComponents and implicitUtilities option ([#93](https://github.com/postcss/postcss-bem-linter/pull/93))

=== 2.6.0 (September 26, 2016)

* Allow component selector patterns without `{componentName}` interpolation.

=== 2.5.1 (May 1, 2016)

* Add support for nested selectors in utility stylesheets.

=== 2.5.0 (April 27, 2016)

* Add support for nested selectors.

=== 2.4.1 (March 28, 2016)

* Fix preset `componentName` patterns.

=== 2.4.0 (March 3, 2016)

* Allow chaining modifier classes in SUIT pattern.
* Allow chaining state classes in SUIT pattern.

=== 2.3.3 (February 15, 2016)

* Skip nodes that do not have `source` properties.

=== 2.3.2 (February 14, 2016)

* Prevent failure if a PostCSS node lacks a `source` property.

=== 2.3.1 (February 9, 2016)

* Ensure that `@keyframes` selectors are always skipped.

=== 2.3.0 (November 24, 2015)

* Add ability to ignore custom properties via `postcss-bem-linter: ignore` comments and the `ignoreCustomProperties` pattern.

=== 2.2.0 (November 15, 2015)

* Allow attribute selectors in BEM preset pattern.
* Fix bug causing pseudo-classes with operators like `:nth-child(3n+1)` to cause problems.
* Add flexibility (remove unnecessary strictness) in attribute part of preset patterns.

=== 2.1.0 (October 31, 2015)

* Support string patterns for everything: `componentName`, `componentSelectors` (with one description, and with `initial` and `combined`), `utilitySelectors`, and `ignoreSelectors` (with a single value or an array).

=== 2.0.0 (October 17, 2015)

* Add namespace option to BEM preset pattern.
* Light breaking change: Improve strictness of SUIT preset pattern: enforce proper camelCasing.

=== 1.2.0 (October 12, 2015)

* Support array of patterns for `ignoreSelectors`.

=== 1.1.0 (September 18, 2015)

* Support selective overriding of a chosen preset's patterns.
* Support `ignoreSelectors` pattern.
* Support ignoring utility selectors with a preceding comment.
* Add helpful error messages when user configuration is lacking patterns.

=== 1.0.1 (August 30, 2015)

* Use PostCSS's improved warning API to provide more precise locations.

=== 1.0.0 (August 26, 2015)

* Upgrade to PostCSS 5.

=== 0.6.0 (August 8, 2015)

* Support multiple definitions per file.
* Support comments to end definition enforcement.
* Support verbose comment syntax, e.g. `/* postcss-bem-linter: define ComponentName */`.

=== 0.5.0 (August 5, 2015)

* Add alternate signature for designating preset and preset options.
* Remove checks that `:root` rules only contain custom-properties, and that the `:root` selector is not grouped or combined with other selectors. Outsourcing these checks to [stylelint](https://github.com/stylelint/stylelint).

=== 0.4.0 (June 23, 2015)

* Support `/* postcss-bem-linter: ignore */` comments.

=== 0.3.0 (May 23, 2015)

* Support BEM format and custom formats.
* Make `strict mode` the default. Add `weak` mode.
* Support pseudo-selectors.
* Support utility linting.
* Add better warning logging.
* Use postcss 4.1.x.
* Allow adjoining attributes in SUITCSS selectors.

=== 0.2.0 (January 24, 2015)

* Use postcss 4.0.x API.

=== 0.1.1 (November 22, 2014)

* Skip `@keyframes` rules when validating selectors.

=== 0.1.0 (October 2, 2014)

* Initial release. Ported from rework-suit-conformance.
e1iv
