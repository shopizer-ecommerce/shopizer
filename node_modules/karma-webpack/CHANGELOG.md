# Change Log

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

<a name="2.0.13"></a>
## [2.0.13](https://github.com/webpack-contrib/karma-webpack/compare/v2.0.12...v2.0.13) (2018-02-27)


### Bug Fixes

* **package:** use `babel-runtime` is a `dependency` (`dependencies`) ([#312](https://github.com/webpack-contrib/karma-webpack/issues/312)) ([c45cdf3](https://github.com/webpack-contrib/karma-webpack/commit/c45cdf3))



<a name="2.0.12"></a>
## [2.0.12](https://github.com/webpack-contrib/karma-webpack/compare/v2.0.11...v2.0.12) (2018-02-26)


### Bug Fixes

* **package:** add `babel-runtime` (`devDependencies`) ([#310](https://github.com/webpack-contrib/karma-webpack/issues/310)) ([6d5fa1c](https://github.com/webpack-contrib/karma-webpack/commit/6d5fa1c))
* **package:** downgrade `source-map` v0.7.0...0.5.6 ([#309](https://github.com/webpack-contrib/karma-webpack/issues/309)) ([070e04e](https://github.com/webpack-contrib/karma-webpack/commit/070e04e))



<a name="2.0.11"></a>
## [2.0.11](https://github.com/webpack-contrib/karma-webpack/compare/v2.0.10...v2.0.11) (2018-02-25)


### Bug Fixes

* **package:** add `main` field ([#306](https://github.com/webpack-contrib/karma-webpack/issues/306)) ([4f5ba3b](https://github.com/webpack-contrib/karma-webpack/commit/4f5ba3b))
* **package:** repository URL typo ([#304](https://github.com/webpack-contrib/karma-webpack/issues/304)) ([2338510](https://github.com/webpack-contrib/karma-webpack/commit/2338510))



<a name="2.0.10"></a>
## [2.0.10](https://github.com/webpac-contrib/karma-webpack/compare/v2.0.9...v2.0.10) (2018-02-25)


### Bug Fixes

* **karma-webpack:** correctly coalesce `idx` (`{Number}`) in filepaths ([#287](https://github.com/webpac-contrib/karma-webpack/issues/287)) ([5fa2409](https://github.com/webpac-contrib/karma-webpack/commit/5fa2409))
* correctly escape `{Regex}` in custom file handler ([#293](https://github.com/webpac-contrib/karma-webpack/issues/293)) ([906ed52](https://github.com/webpac-contrib/karma-webpack/commit/906ed52))
* disable `webpack` rebuilds on karma `--single-run` ([41d1912](https://github.com/webpac-contrib/karma-webpack/commit/41d1912))
* **package:** add `webpack >= v4.0.0` (`peerDependencies`) ([#301](https://github.com/webpac-contrib/karma-webpack/issues/301)) ([99ddad8](https://github.com/webpac-contrib/karma-webpack/commit/99ddad8))



<a name="2.0.9"></a>
## [2.0.9](https://github.com/webpack/karma-webpack/compare/v2.0.8...v2.0.9) (2017-12-14)


### Bug Fixes

* ensure webpack paths include a trailing slash ([#285](https://github.com/webpack/karma-webpack/issues/285)) ([be2b0e8](https://github.com/webpack/karma-webpack/commit/be2b0e8)), closes [#284](https://github.com/webpack/karma-webpack/issues/284)



<a name="2.0.8"></a>
## [2.0.8](https://github.com/webpack/karma-webpack/compare/v2.0.7...v2.0.8) (2017-12-14)


### Bug Fixes

* use `os.tmpdir()` to safely store `_karma_webpack_` ([#279](https://github.com/webpack/karma-webpack/issues/279)) ([0616dda](https://github.com/webpack/karma-webpack/commit/0616dda))



<a name="2.0.7"></a>
## [2.0.7](https://github.com/webpack/karma-webpack/compare/v2.0.6...v2.0.7) (2017-12-14)


`DEPRECATED due to publishing issue`



<a name="2.0.6"></a>
## [2.0.6](https://github.com/webpack/karma-webpack/compare/v2.0.5...v2.0.6) (2017-11-10)


### Bug Fixes

* work with sourcemaps when mocha is wrapped ([#237](https://github.com/webpack/karma-webpack/issues/237)) ([#238](https://github.com/webpack/karma-webpack/issues/238)) ([c952bcd](https://github.com/webpack/karma-webpack/commit/c952bcd))



<a name="2.0.5"></a>
## [2.0.5](https://github.com/webpack/karma-webpack/compare/v2.0.4...v2.0.5) (2017-10-08)


### Bug Fixes

* pass on error to callback for better error messages ([#262](https://github.com/webpack/karma-webpack/issues/262)) ([466581f](https://github.com/webpack/karma-webpack/commit/466581f))
* use `file.originalPath` instead of `file.path` ([#218](https://github.com/webpack/karma-webpack/issues/218)) ([0d075b0](https://github.com/webpack/karma-webpack/commit/0d075b0))



<a name="2.0.4"></a>
## [2.0.4](https://github.com/webpack/karma-webpack/compare/v2.0.3...v2.0.4) (2017-07-07)



<a name="2.0.3"></a>
## [2.0.3](https://github.com/webpack/karma-webpack/compare/v2.0.1...v2.0.3) (2017-03-15)


### Bug Fixes

* **readFile:** handle path doesn't exist error ([#208](https://github.com/webpack/karma-webpack/issues/208)) ([907ed72](https://github.com/webpack/karma-webpack/commit/907ed72))
* don't swallow configuration errors ([#232](https://github.com/webpack/karma-webpack/issues/232)) ([4216f13](https://github.com/webpack/karma-webpack/commit/4216f13))
* readFile on windows ([#226](https://github.com/webpack/karma-webpack/issues/226)) ([cad9f8b](https://github.com/webpack/karma-webpack/commit/cad9f8b))



<a name="2.0.2"></a>
## [2.0.2](https://github.com/webpack/karma-webpack/compare/v2.0.1...v2.0.2) (2017-01-19)


### Bug Fixes

* **readFile:** handle path doesn't exist error ([#208](https://github.com/webpack/karma-webpack/issues/208)) ([907ed72](https://github.com/webpack/karma-webpack/commit/907ed72))



<a name="2.0.1"></a>
## [2.0.1](https://github.com/webpack/karma-webpack/compare/v2.0.0...v2.0.1) (2017-01-11)

### Chores

 * **release:** patch version for release issue. No code changes.


<a name="2.0.0"></a>
## [2.0.0](https://github.com/webpack/karma-webpack/compare/v1.8.1...v2.0.0) (2017-01-11)

### Chores

* **package:** update webpack peerDependencies. ([9fd5fdf](https://github.com/webpack/karma-webpack/commit/9fd5fdf))


### Bug Fixes

* **config:** webpack rc4 schema enforcment (fixes [#193](https://github.com/webpack/karma-webpack/issues/193)) ([e6a3cb7](https://github.com/webpack/karma-webpack/commit/e6a3cb7))


### BREAKING CHANGES

* config: Remove entry:{} from test configurations

When updating to `"webpack": "2.2.0-rc.4"` & `"karma-webpack": "1.8.2"` you have to pull the `entry` property if it's set to an empty object so it defaults to a function within karma-webpack


<a name="1.8.1"></a>
## [1.8.1](https://github.com/webpack/karma-webpack/compare/v1.8.0...v1.8.1) (2016-12-27)

### Bug Fixes

* **build:** Removes dist from scm ([#158](https://github.com/webpack/karma-webpack/issues/158)) ([68ff1d5](https://github.com/webpack/karma-webpack/commit/68ff1d5))


<a name="1.8.0"></a>
## [1.8.0](https://github.com/webpack/karma-webpack/compare/v1.7.0...v1.8.0) (2016-08-07)

### Bug Fixes

* **build:** Removes dist from scm ([#158](https://github.com/webpack/karma-webpack/issues/158)) ([9ea6921](https://github.com/webpack/karma-webpack/commit/9ea6921))
* **config:** webpack rc4 schema enforcment (fixes [#193](https://github.com/webpack/karma-webpack/issues/193)) ([e6a3cb7](https://github.com/webpack/karma-webpack/commit/e6a3cb7))
* **provider:** no provider for variable name Fix [#146](https://github.com/webpack/karma-webpack/issues/146) ([43f18d3](https://github.com/webpack/karma-webpack/commit/43f18d3))


### Features

* **webpack:** add support for webpack2.1.0-beta ([bdd8c80](https://github.com/webpack/karma-webpack/commit/bdd8c80))
* **webpack:** add webpack blocker ([03f6495](https://github.com/webpack/karma-webpack/commit/03f6495))
* **karma:** karma execution blocker ([d776068](https://github.com/webpack/karma-webpack/commit/d776068))
* **webpack:** support chunks without errors ([7334dbc](https://github.com/webpack/karma-webpack/commit/7334dbc))
