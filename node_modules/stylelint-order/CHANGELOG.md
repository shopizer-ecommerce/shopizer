# Change Log
All notable changes to this project will be documented in this file.
This project adheres to [Semantic Versioning](http://semver.org/).

## 0.7.0

* Specified `stylelint` in `peerDependencies` rather in `dependencies`. Following [stylelint's plugin guide](https://github.com/stylelint/stylelint/blob/master/docs/developer-guide/plugins.md#peer-dependencies).

## 0.6.0

* Migrated to `stylelint@8.0.0`.

## 0.5.0
* Added autofixing for every rule! Please read docs before using this feature, because each rule has some caveats. stylelint 7.11+ is required for this feature.
* Removed SCSS nested properties support.
* Removed property shortcuts in `properties-order`. Before this version it was possible to define only e.g. `padding` and it would define position for all undefined `padding-*` properties. Now every property should be explicitly defined in a config.
* Removed deprecation warnings:
	* `declaration-block-order`
	* `declaration-block-properties-order`
	* `declaration-block-properties-alphabetical-order`
	* `declaration-block-properties-specified-order`
	* `declaration-block-property-groups-structure`

## 0.4.4
* Fixed false negative for blockless at-rules in `order`.

## 0.4.3
* Fixed regression in `properties-order` introduced in 0.4.2.

## 0.4.2
* Fixed: `order` and `properties-order` weren't recognize SCSS nested properties as declarations.

## 0.4.1
* Fixed `properties-order` bug, when non-standard declaration is following after a standard one

## 0.4.0
* Removed `declaration-block-properties-specified-order`. Instead use `properties-order` rule.
* Removed `declaration-block-property-groups-structure`. Instead use `properties-order` rule.
* Renamed `declaration-block-order` to `order`
* Renamed `declaration-block-properties-alphabetical-order` to `properties-alphabetical-order`
* Added `properties-order` rule. It combines removed `declaration-block-properties-specified-order`, `declaration-block-property-groups-structure`, and now support flexible order. Basically it's like [`declaration-block-properties-order` in stylelint 6.5.0](https://github.com/stylelint/stylelint/tree/6.5.0/src/rules/declaration-block-properties-order), but better :)

## 0.3.0
* Changed: Breaking! `declaration-block-property-groups-structure` now uses `declaration-block-properties-specified-order` rather stylelint's deprecated `declaration-block-properties-order`. Flexible group order isn't supported anymore
* Added: `declaration-block-order` support new `rule` extended object, which have new `selector` option. Rules in order can be specified by their selector
* Added: New keyword `at-variables` in `declaration-block-order`
* Added: New keyword `less-mixins` in `declaration-block-order`

## 0.2.2
* Fixed tests for `declaration-block-property-groups-structure` which were broken by previous fix ¯﻿\﻿_﻿(﻿ツ﻿)﻿_﻿/﻿¯

## 0.2.1
* Fixed incorrect severity level for `declaration-block-properties-order` which is called from `declaration-block-property-groups-structure`

## 0.2.0
* Breaking: Renamed `property-groups-structure` to `declaration-block-property-groups-structure`
* Added `declaration-block-properties-specified-order` rule
* Fixed unavailability of `declaration-block-properties-alphabetical-order` rule

## 0.1.0
* Initial release.
