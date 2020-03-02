<a name="1.3.0"></a>
# [1.3.0](https://github.com/karma-runner/karma-mocha/compare/v1.2.0...v1.3.0) (2016-11-09)


### Bug Fixes

* polyfill Date.now to restore IE compat ([246f25a](https://github.com/karma-runner/karma-mocha/commit/246f25a))
* **deps:** remove peer dependency on mocha ([0bbf932](https://github.com/karma-runner/karma-mocha/commit/0bbf932)), closes [#114](https://github.com/karma-runner/karma-mocha/issues/114)


### Features

* support mocha opts ([11a0dd8](https://github.com/karma-runner/karma-mocha/commit/11a0dd8)), closes [#99](https://github.com/karma-runner/karma-mocha/issues/99)



<a name="1.2.0"></a>
# [1.2.0](https://github.com/karma-runner/karma-mocha/compare/v1.1.1...v1.2.0) (2016-09-26)


### Bug Fixes

* "remove mocha stack entries" was too greedy ([c893d0a](https://github.com/karma-runner/karma-mocha/commit/c893d0a))


### Features

* **reporter:** add timestamps to results ([7b41f52](https://github.com/karma-runner/karma-mocha/commit/7b41f52))
* **reporter:** expose mocha test properties ([e4eb5fd](https://github.com/karma-runner/karma-mocha/commit/e4eb5fd))



<a name="1.1.1"></a>
## [1.1.1](https://github.com/karma-runner/karma-mocha/compare/v1.1.0...v1.1.1) (2016-06-26)


### Bug Fixes

* handle non existing mochaConfig ([dcb74a5](https://github.com/karma-runner/karma-mocha/commit/dcb74a5))



<a name="1.1.0"></a>
# [1.1.0](https://github.com/karma-runner/karma-mocha/compare/v1.0.1...v1.1.0) (2016-06-26)


### Features

* allow requiring files via karma.conf.js ([f00d6b3](https://github.com/karma-runner/karma-mocha/commit/f00d6b3)), closes [#84](https://github.com/karma-runner/karma-mocha/issues/84)
* **deps:** update dependencies ([fe04ebb](https://github.com/karma-runner/karma-mocha/commit/fe04ebb))



<a name="1.0.1"></a>
## [1.0.1](https://github.com/karma-runner/karma-mocha/compare/v0.2.2...v1.0.1) (2016-05-02)


### Bug Fixes

* Do not lose stack trace at debug.html ([0b5a392](https://github.com/karma-runner/karma-mocha/commit/0b5a392))



<a name="0.2.2"></a>
## [0.2.2](https://github.com/karma-runner/karma-mocha/compare/v0.2.1...v0.2.2) (2016-02-15)



<a name="0.2.1"></a>
## [0.2.1](https://github.com/karma-runner/karma-mocha/compare/v0.2.0...v0.2.1) (2015-11-13)


### Bug Fixes

* Tests that call this.skip() are marked as failed instead of skipped fix [#77](https://github.com/karma-runner/karma-mocha/issues/77) ([73e554f](https://github.com/karma-runner/karma-mocha/commit/73e554f))



<a name="0.2.0"></a>
# [0.2.0](https://github.com/karma-runner/karma-mocha/compare/v0.1.10...v0.2.0) (2015-06-22)


### Bug Fixes

* **config:** grep is interpreted as regexp ([01d11e6](https://github.com/karma-runner/karma-mocha/commit/01d11e6))
* **npm:** Make .npmignore more sensible to dot files ([4ebf361](https://github.com/karma-runner/karma-mocha/commit/4ebf361)), closes [#59](https://github.com/karma-runner/karma-mocha/issues/59)
* Drop karma as a peerDependency. ([5c11809](https://github.com/karma-runner/karma-mocha/commit/5c11809))



<a name="0.1.10"></a>
## [0.1.10](https://github.com/karma-runner/karma-mocha/compare/v0.1.9...v0.1.10) (2014-12-04)



<a name="0.1.9"></a>
## [0.1.9](https://github.com/karma-runner/karma-mocha/compare/v0.1.8...v0.1.9) (2014-08-23)


### Bug Fixes

* properly format err messages with new lines ([1144690](https://github.com/karma-runner/karma-mocha/commit/1144690)), closes [#40](https://github.com/karma-runner/karma-mocha/issues/40)



<a name="0.1.8"></a>
## [0.1.8](https://github.com/karma-runner/karma-mocha/compare/v0.1.7...v0.1.8) (2014-08-18)


### Bug Fixes

* properly remove mocha stack entries ([b4ee7f2](https://github.com/karma-runner/karma-mocha/commit/b4ee7f2)), closes [#44](https://github.com/karma-runner/karma-mocha/issues/44) [#41](https://github.com/karma-runner/karma-mocha/issues/41)



<a name="0.1.7"></a>
## [0.1.7](https://github.com/karma-runner/karma-mocha/compare/v0.1.6...v0.1.7) (2014-08-07)



<a name="0.1.6"></a>
## [0.1.6](https://github.com/karma-runner/karma-mocha/compare/v0.1.5...v0.1.6) (2014-07-17)


### Bug Fixes

* correctly prepare the new version (v0.1.5) ([ab2a23f](https://github.com/karma-runner/karma-mocha/commit/ab2a23f))



<a name="0.1.5"></a>
## [0.1.5](https://github.com/karma-runner/karma-mocha/compare/v0.1.4...v0.1.5) (2014-06-16)



<a name="0.1.4"></a>
## [0.1.4](https://github.com/karma-runner/karma-mocha/compare/v0.1.3...v0.1.4) (2014-06-15)


### Features

* Allow define html reporter on debug page. resolve [#20](https://github.com/karma-runner/karma-mocha/issues/20) ([7f8d4cc](https://github.com/karma-runner/karma-mocha/commit/7f8d4cc))



<a name="0.1.3"></a>
## [0.1.3](https://github.com/karma-runner/karma-mocha/compare/v0.1.2...v0.1.3) (2014-03-13)


### Bug Fixes

* grep for grunt-karma ([70aef65](https://github.com/karma-runner/karma-mocha/commit/70aef65))



<a name="0.1.2"></a>
## [0.1.2](https://github.com/karma-runner/karma-mocha/compare/v0.1.1...v0.1.2) (2014-02-18)


### Bug Fixes

* restores the ability to use grep ([2787886](https://github.com/karma-runner/karma-mocha/commit/2787886))



<a name="0.1.1"></a>
## [0.1.1](https://github.com/karma-runner/karma-mocha/compare/v0.1.0...v0.1.1) (2013-11-26)


### Features

* Pass client configuration to `mocha.setup method. ([4df9ba6](https://github.com/karma-runner/karma-mocha/commit/4df9ba6)), closes [#13](https://github.com/karma-runner/karma-mocha/issues/13)



<a name="0.1.0"></a>
# [0.1.0](https://github.com/karma-runner/karma-mocha/compare/v0.0.4...v0.1.0) (2013-08-06)



<a name="0.0.4"></a>
## [0.0.4](https://github.com/karma-runner/karma-mocha/compare/v0.0.3...v0.0.4) (2013-07-19)


### Features

* remove dump, as karma implements it ([e02af06](https://github.com/karma-runner/karma-mocha/commit/e02af06))



<a name="0.0.3"></a>
## [0.0.3](https://github.com/karma-runner/karma-mocha/compare/v0.0.2...v0.0.3) (2013-07-07)


### Bug Fixes

* config does not have to be defined ([edfa534](https://github.com/karma-runner/karma-mocha/commit/edfa534))



<a name="0.0.2"></a>
## [0.0.2](https://github.com/karma-runner/karma-mocha/compare/4f5470d...v0.0.2) (2013-07-05)


### Bug Fixes

* report time for skipped tests so that netTime is computed correctly ([4f5470d](https://github.com/karma-runner/karma-mocha/commit/4f5470d)), closes [#141](https://github.com/karma-runner/karma-mocha/issues/141)



