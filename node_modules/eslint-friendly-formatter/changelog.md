
# eslint-friendly-formatter - Changelog
## v3.0.0
- **Build Scripts Changes**
  - Release v3.0.0 - [6819e5e]( https://github.com/royriojas/eslint-friendly-formatter/commit/6819e5e ), [Roy Riojas](https://github.com/Roy Riojas), 11/05/2017 04:49:13

    
- **Bug Fixes**
  - Make relative paths the default again - [c79ee4d]( https://github.com/royriojas/eslint-friendly-formatter/commit/c79ee4d ), [Roy Riojas](https://github.com/Roy Riojas), 11/05/2017 04:48:20

    
- **Documentation**
  - Generate changelog - [9ac3e48]( https://github.com/royriojas/eslint-friendly-formatter/commit/9ac3e48 ), [Roy Riojas](https://github.com/Roy Riojas), 02/01/2017 06:13:49

    
## v2.0.7
- **Build Scripts Changes**
  - Release v2.0.7 - [637842a]( https://github.com/royriojas/eslint-friendly-formatter/commit/637842a ), [Roy Riojas](https://github.com/Roy Riojas), 02/01/2017 06:13:48

    
  - update deps - [86f5588]( https://github.com/royriojas/eslint-friendly-formatter/commit/86f5588 ), [Roy Riojas](https://github.com/Roy Riojas), 02/01/2017 06:13:37

    
- **Documentation**
  - Generate changelog - [f83e205]( https://github.com/royriojas/eslint-friendly-formatter/commit/f83e205 ), [Roy Riojas](https://github.com/Roy Riojas), 13/07/2016 00:11:06

    
## v2.0.6
- **Build Scripts Changes**
  - Release v2.0.6 - [cdeb9b2]( https://github.com/royriojas/eslint-friendly-formatter/commit/cdeb9b2 ), [Roy Riojas](https://github.com/Roy Riojas), 13/07/2016 00:11:04

    
- **Bug Fixes**
  - Sort results alphabetically, by line and by column - [ee2c8f2]( https://github.com/royriojas/eslint-friendly-formatter/commit/ee2c8f2 ), [Cédric Malard](https://github.com/Cédric Malard), 12/07/2016 11:59:04

    Fix https://github.com/royriojas/eslint-friendly-formatter/issues/20
    
- **Documentation**
  - Generate changelog - [3575350]( https://github.com/royriojas/eslint-friendly-formatter/commit/3575350 ), [Roy Riojas](https://github.com/Roy Riojas), 23/05/2016 01:32:30

    
## v2.0.5
- **Build Scripts Changes**
  - Release v2.0.5 - [bb11799]( https://github.com/royriojas/eslint-friendly-formatter/commit/bb11799 ), [Roy Riojas](https://github.com/Roy Riojas), 23/05/2016 01:32:28

    
- **Features**
  - add `--eff-filter` option - [cac606d]( https://github.com/royriojas/eslint-friendly-formatter/commit/cac606d ), [Roy Riojas](https://github.com/Roy Riojas), 23/05/2016 01:30:10

    The `--eff-filter` filters the rules shown in the report. Usefule to
    filter only a given type of rule.
    
    **Example**
    
    The following will only show the warning for `global-require` ruleId
    
    ```bash
    eslint -f node_modules/eslint-friendly-formatter client/**/*.js server/**/*.js -- --eff-by-issue --eff-filter 'global-require' # notice the --
    ```
    
- **Documentation**
  - Generate changelog - [928e6db]( https://github.com/royriojas/eslint-friendly-formatter/commit/928e6db ), [Roy Riojas](https://github.com/Roy Riojas), 22/04/2016 14:27:13

    
## v2.0.4
- **Build Scripts Changes**
  - Release v2.0.4 - [211ead4]( https://github.com/royriojas/eslint-friendly-formatter/commit/211ead4 ), [Roy Riojas](https://github.com/Roy Riojas), 22/04/2016 14:27:12

    
- **Refactoring**
  - Update readme. - [2871952]( https://github.com/royriojas/eslint-friendly-formatter/commit/2871952 ), [Roy Riojas](https://github.com/Roy Riojas), 22/04/2016 14:26:45

    - Closes <a target="_blank" class="info-link" href="https://github.com/royriojas/eslint-friendly-formatter/issues/17"><span>#17</span></a>.
    - Closes <a target="_blank" class="info-link" href="https://github.com/royriojas/eslint-friendly-formatter/issues/19"><span>#19</span></a>.
    
- **Documentation**
  - Generate changelog - [d1f4f3a]( https://github.com/royriojas/eslint-friendly-formatter/commit/d1f4f3a ), [Roy Riojas](https://github.com/Roy Riojas), 18/04/2016 14:07:25

    
## v2.0.3
- **Build Scripts Changes**
  - Release v2.0.3 - [a238eab]( https://github.com/royriojas/eslint-friendly-formatter/commit/a238eab ), [Roy Riojas](https://github.com/Roy Riojas), 18/04/2016 14:07:23

    
- **Features**
  - Add an option to group by issue - [39ef078]( https://github.com/royriojas/eslint-friendly-formatter/commit/39ef078 ), [Roy Riojas](https://github.com/Roy Riojas), 18/04/2016 14:07:15

    Eslint does not support passing arguments, but we can pass any
    parameters using `-- --param-here`. So now inside the reporter we can get
    the value like `process.argv['--param-here']`. This can be used
    to pass flags to the reporter so now we can do the following
    
    `--eff-by-issue` will group the report by issue type
    
    ```bash
    eslint -f 'node_modules/eslint-friendly-formatter' -- --eff-by-issue
    ```
    
- **Documentation**
  - Generate changelog - [d12a6d1]( https://github.com/royriojas/eslint-friendly-formatter/commit/d12a6d1 ), [Roy Riojas](https://github.com/Roy Riojas), 14/04/2016 00:19:05

    
## v2.0.2
- **Build Scripts Changes**
  - Release v2.0.2 - [f8af585]( https://github.com/royriojas/eslint-friendly-formatter/commit/f8af585 ), [Roy Riojas](https://github.com/Roy Riojas), 14/04/2016 00:19:03

    
- **Bug Fixes**
  - sort results by number of ocurrences - [099515f]( https://github.com/royriojas/eslint-friendly-formatter/commit/099515f ), [Roy Riojas](https://github.com/Roy Riojas), 14/04/2016 00:18:50

    
- **Documentation**
  - Generate changelog - [0c89457]( https://github.com/royriojas/eslint-friendly-formatter/commit/0c89457 ), [Roy Riojas](https://github.com/Roy Riojas), 10/04/2016 02:47:25

    
## v2.0.1
- **Build Scripts Changes**
  - Release v2.0.1 - [69f49ac]( https://github.com/royriojas/eslint-friendly-formatter/commit/69f49ac ), [Roy Riojas](https://github.com/Roy Riojas), 10/04/2016 02:47:24

    
- **Refactoring**
  - Add `EFF_NO_LINK_RULES` param. Fixes [#18](https://github.com/royriojas/eslint-friendly-formatter/issues/18) - [7ed4647]( https://github.com/royriojas/eslint-friendly-formatter/commit/7ed4647 ), [Roy Riojas](https://github.com/Roy Riojas), 10/04/2016 02:44:06

    - In your terminal run
    
    ```bash
    export EFF_NO_LINK_RULES=true # rules ids won't be linked to
    documentation
    ```
    
    Also as part of this commit, rules that contain `/`, a common pattern
    in eslint plugins, like `react/jsx-quotes` will generate a link
    to a google search for that rule.
    
- **Documentation**
  - Generate changelog - [70f02c4]( https://github.com/royriojas/eslint-friendly-formatter/commit/70f02c4 ), [Roy Riojas](https://github.com/Roy Riojas), 08/04/2016 17:00:52

    
## v2.0.0
- **Build Scripts Changes**
  - Release v2.0.0 - [1b420f4]( https://github.com/royriojas/eslint-friendly-formatter/commit/1b420f4 ), [Roy Riojas](https://github.com/Roy Riojas), 08/04/2016 17:00:51

    
- **Features**
  - Clickable rules and summary count at the end - [8bcba89]( https://github.com/royriojas/eslint-friendly-formatter/commit/8bcba89 ), [Roy Riojas](https://github.com/Roy Riojas), 08/04/2016 17:00:40

    
- **Documentation**
  - Generate changelog - [9a0db2e]( https://github.com/royriojas/eslint-friendly-formatter/commit/9a0db2e ), [Roy Riojas](https://github.com/Roy Riojas), 30/08/2015 08:40:18

    
## v1.2.2
- **Build Scripts Changes**
  - Release v1.2.2 - [7410804]( https://github.com/royriojas/eslint-friendly-formatter/commit/7410804 ), [Roy Riojas](https://github.com/Roy Riojas), 30/08/2015 08:40:16

    
- **Features**
  - Ignore source code when the source property is really (really) long!. Fixes [#13](https://github.com/royriojas/eslint-friendly-formatter/issues/13) - [a19ff73]( https://github.com/royriojas/eslint-friendly-formatter/commit/a19ff73 ), [Roy Riojas](https://github.com/Roy Riojas), 30/08/2015 08:36:40

    
- **Other changes**
  - Update README.md - [93c77a1]( https://github.com/royriojas/eslint-friendly-formatter/commit/93c77a1 ), [Roy Riojas](https://github.com/Roy Riojas), 30/08/2015 01:45:55

    
- **Documentation**
  - Generate changelog - [691e0f8]( https://github.com/royriojas/eslint-friendly-formatter/commit/691e0f8 ), [royriojas](https://github.com/royriojas), 19/08/2015 05:39:27

    
## v1.2.1
- **Build Scripts Changes**
  - Release v1.2.1 - [f644f53]( https://github.com/royriojas/eslint-friendly-formatter/commit/f644f53 ), [royriojas](https://github.com/royriojas), 19/08/2015 05:39:25

    
- **Other changes**
  - add tests for errors without source info - [1b61a9d]( https://github.com/royriojas/eslint-friendly-formatter/commit/1b61a9d ), [Nikola Kovacs](https://github.com/Nikola Kovacs), 19/08/2015 03:48:07

    
  - Fix error when message.source is undefined. - [7a3f6f7]( https://github.com/royriojas/eslint-friendly-formatter/commit/7a3f6f7 ), [Nikola Kovacs](https://github.com/Nikola Kovacs), 18/08/2015 09:28:56

    This happens when the javascript file has a parsing error.
    
- **Documentation**
  - Generate changelog - [7021114]( https://github.com/royriojas/eslint-friendly-formatter/commit/7021114 ), [royriojas](https://github.com/royriojas), 14/08/2015 12:44:57

    
## v1.2.0
- **Build Scripts Changes**
  - Release v1.2.0 - [dd39097]( https://github.com/royriojas/eslint-friendly-formatter/commit/dd39097 ), [royriojas](https://github.com/royriojas), 14/08/2015 12:44:56

    
- **Documentation**
  - Generate changelog - [b406eae]( https://github.com/royriojas/eslint-friendly-formatter/commit/b406eae ), [royriojas](https://github.com/royriojas), 14/08/2015 12:44:35

    
## v1.1.1
- **Build Scripts Changes**
  - Release v1.1.1 - [d107a4c]( https://github.com/royriojas/eslint-friendly-formatter/commit/d107a4c ), [royriojas](https://github.com/royriojas), 14/08/2015 12:44:34

    
  - add build scripts - [16d984b]( https://github.com/royriojas/eslint-friendly-formatter/commit/16d984b ), [royriojas](https://github.com/royriojas), 14/08/2015 12:44:15

    
  - Update deps - [3a23367]( https://github.com/royriojas/eslint-friendly-formatter/commit/3a23367 ), [royriojas](https://github.com/royriojas), 14/08/2015 12:39:20

    
- **Enhancements**
  - Enforce the use of triple equal - [7cd1707]( https://github.com/royriojas/eslint-friendly-formatter/commit/7cd1707 ), [royriojas](https://github.com/royriojas), 14/08/2015 12:39:49

    
- **Other changes**
  - Fix arrow's position when source code contains tabs. - [08612a4]( https://github.com/royriojas/eslint-friendly-formatter/commit/08612a4 ), [Nikola Kovacs](https://github.com/Nikola Kovacs), 14/08/2015 09:03:37

    Fixes <a target="_blank" class="info-link" href="https://github.com/royriojas/eslint-friendly-formatter/issues/10"><span>#10</span></a>
    
  - add support for editor urls - [3e35a8b]( https://github.com/royriojas/eslint-friendly-formatter/commit/3e35a8b ), [Nikola Kovacs](https://github.com/Nikola Kovacs), 14/08/2015 08:37:28

    Fixes <a target="_blank" class="info-link" href="https://github.com/royriojas/eslint-friendly-formatter/issues/8"><span>#8</span></a>
    
- **Documentation**
  - Generate changelog - [35cea13]( https://github.com/royriojas/eslint-friendly-formatter/commit/35cea13 ), [royriojas](https://github.com/royriojas), 14/07/2015 01:29:37

    
## v1.1.0
- **Build Scripts Changes**
  - Release v1.1.0 - [afa0c60]( https://github.com/royriojas/eslint-friendly-formatter/commit/afa0c60 ), [royriojas](https://github.com/royriojas), 14/07/2015 01:28:33

    
  - Use `npm run test` instead of plain npm test - [df4c642]( https://github.com/royriojas/eslint-friendly-formatter/commit/df4c642 ), [royriojas](https://github.com/royriojas), 17/05/2015 12:35:05

    
- **Documentation**
  - Updated changelog with info about [#7](https://github.com/royriojas/eslint-friendly-formatter/issues/7) - [477acfb]( https://github.com/royriojas/eslint-friendly-formatter/commit/477acfb ), [royriojas](https://github.com/royriojas), 14/07/2015 01:27:42

    
- **Features**
  - add environment option `EFF_ABSOLUTE_PATHS`. Fixes [#7](https://github.com/royriojas/eslint-friendly-formatter/issues/7) - [271a749]( https://github.com/royriojas/eslint-friendly-formatter/commit/271a749 ), [royriojas](https://github.com/royriojas), 14/07/2015 01:20:57

    export the env variable `EFF_ABSOLUTE_PATHS` to make the paths in the
    reporter be absolute. If the environment variable is not found the
    paths will be reported as `eslint` send them.
    
    ```bash
    EFF_ABSOLUTE_PATHS=true eslint -f
    path/to/eslint-friendly-formatter/index.js’ file1.js file2.js dir/
    ```
    
    Or
    
    add to your profile
    
    ```bash
    export EFF_ABSOLUTE_PATHS=true
    ```
    
    Ugly hack. But should work until this issue is resolved:
    
    https://github.com/eslint/eslint/issues/2989
    
- **Other changes**
  - Update README.md - [f2f485c]( https://github.com/royriojas/eslint-friendly-formatter/commit/f2f485c ), [Roy Riojas](https://github.com/Roy Riojas), 05/07/2015 23:29:26

    
  - Update README.md - [5963c02]( https://github.com/royriojas/eslint-friendly-formatter/commit/5963c02 ), [Roy Riojas](https://github.com/Roy Riojas), 05/07/2015 23:27:59

    
  - Update README.md - [edd8854]( https://github.com/royriojas/eslint-friendly-formatter/commit/edd8854 ), [Roy Riojas](https://github.com/Roy Riojas), 05/07/2015 23:27:01

    
  - Add gulp - [6c9eb73]( https://github.com/royriojas/eslint-friendly-formatter/commit/6c9eb73 ), [Fahad Hossain](https://github.com/Fahad Hossain), 05/07/2015 06:08:16

    Add Gulp to the list of working examples
  - Update README.md - [62289ed]( https://github.com/royriojas/eslint-friendly-formatter/commit/62289ed ), [Roy Riojas](https://github.com/Roy Riojas), 26/06/2015 00:35:32

    Better description of the when to disable the gray coloring
  - Update README.md - [b284801]( https://github.com/royriojas/eslint-friendly-formatter/commit/b284801 ), [Roy Riojas](https://github.com/Roy Riojas), 26/06/2015 00:33:38

    
  - Update README.md - [9b00c00]( https://github.com/royriojas/eslint-friendly-formatter/commit/9b00c00 ), [Roy Riojas](https://github.com/Roy Riojas), 26/06/2015 00:32:41

    
  - Add Guake - [4ff1321]( https://github.com/royriojas/eslint-friendly-formatter/commit/4ff1321 ), [Ian VanSchooten](https://github.com/Ian VanSchooten), 25/06/2015 23:21:23

    Current master of Guake will also auto open a configured editor to the correct line number.
- **Cosmetic fixes**
  - Add a space for better readability - [4bec078]( https://github.com/royriojas/eslint-friendly-formatter/commit/4bec078 ), [royriojas](https://github.com/royriojas), 17/05/2015 12:24:37

    
## v1.0.8
- **Build Scripts Changes**
  - Release v1.0.8 - [fc60298]( https://github.com/royriojas/eslint-friendly-formatter/commit/fc60298 ), [royriojas](https://github.com/royriojas), 17/05/2015 12:11:44

    
  - Update prepush and changelogx dependency - [fddbf5a]( https://github.com/royriojas/eslint-friendly-formatter/commit/fddbf5a ), [royriojas](https://github.com/royriojas), 17/05/2015 11:50:01

    
- **Other changes**
  - Update README.md - [90c5c08]( https://github.com/royriojas/eslint-friendly-formatter/commit/90c5c08 ), [Roy Riojas](https://github.com/Roy Riojas), 17/05/2015 12:11:22

    
## v1.0.7
- **Build Scripts Changes**
  - Release v1.0.7 - [e00d482]( https://github.com/royriojas/eslint-friendly-formatter/commit/e00d482 ), [royriojas](https://github.com/royriojas), 17/05/2015 11:46:54

    
  - Fix for [#3](https://github.com/royriojas/eslint-friendly-formatter/issues/3). eslint-friendly-formatter failing to install - [bd77638]( https://github.com/royriojas/eslint-friendly-formatter/commit/bd77638 ), [royriojas](https://github.com/royriojas), 17/05/2015 11:44:17

    
#### Changelog
- **Documentation**
  - updated changelog - [468a649]( https://github.com/royriojas/eslint-friendly-formatter/commit/468a649 ), [royriojas](https://github.com/royriojas), 17/05/2015 11:45:24

    
  - updated the changelog - [dea8736]( https://github.com/royriojas/eslint-friendly-formatter/commit/dea8736 ), [royriojas](https://github.com/royriojas), 16/05/2015 17:05:58

    
## v1.0.6
- **Build Scripts Changes**
  - Release v1.0.6 - [b09e7a6]( https://github.com/royriojas/eslint-friendly-formatter/commit/b09e7a6 ), [royriojas](https://github.com/royriojas), 16/05/2015 17:04:15

    
- **Bug Fixes**
  - Support for solarized theme, or other themes that use gray for the background. Fixes [#2](https://github.com/royriojas/eslint-friendly-formatter/issues/2) - [a8c3c71]( https://github.com/royriojas/eslint-friendly-formatter/commit/a8c3c71 ), [royriojas](https://github.com/royriojas), 16/05/2015 17:02:33

    Basically this fix removes the gray color if the environment variable `EFF_NO_GRAY`
    is set to the string `true`.
    
    this can be done very easily in bash doing:
    
    ```bash
    export EFF_NO_GRAY=true
    ```
    
- **Other changes**
  - quick hack for solarized support - [40415ab]( https://github.com/royriojas/eslint-friendly-formatter/commit/40415ab ), [April Arcus](https://github.com/April Arcus), 09/05/2015 00:18:55

    
- **Documentation**
  - Added changelog - [d2afd28]( https://github.com/royriojas/eslint-friendly-formatter/commit/d2afd28 ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:37:10

    
## v1.0.5
- **Build Scripts Changes**
  - Release v1.0.5 - [86d90a3]( https://github.com/royriojas/eslint-friendly-formatter/commit/86d90a3 ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:36:35

    
  - Add lint task to automatically beautify and lint the code - [e6f8f2e]( https://github.com/royriojas/eslint-friendly-formatter/commit/e6f8f2e ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:12:28

    
  - Fixed missing changelogx section - [acf90b0]( https://github.com/royriojas/eslint-friendly-formatter/commit/acf90b0 ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:09:31

    
- **Documentation**
  - Better documentation for the integration with `intellij` or `webstorm` - [678886d]( https://github.com/royriojas/eslint-friendly-formatter/commit/678886d ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:36:21

    
- **Enhancements**
  - Use the full path to the file so IDEs like Webstorm or Intellij can parse the output - [4433a5e]( https://github.com/royriojas/eslint-friendly-formatter/commit/4433a5e ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:15:50

    
## v1.0.4
- **Build Scripts Changes**
  - Release v1.0.4 - [2af7d0c]( https://github.com/royriojas/eslint-friendly-formatter/commit/2af7d0c ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:08:13

    
  - Update deps - [b1e1539]( https://github.com/royriojas/eslint-friendly-formatter/commit/b1e1539 ), [Roy Riojas](https://github.com/Roy Riojas), 18/03/2015 04:07:56

    
  - Remove not used dep - [5b8433b]( https://github.com/royriojas/eslint-friendly-formatter/commit/5b8433b ), [Roy Riojas](https://github.com/Roy Riojas), 04/03/2015 03:04:49

    
  - Bump minor version - [097ceb0]( https://github.com/royriojas/eslint-friendly-formatter/commit/097ceb0 ), [Roy Riojas](https://github.com/Roy Riojas), 03/03/2015 07:13:55

    
  - Beautify and validate code with eslint - [b2bbaaf]( https://github.com/royriojas/eslint-friendly-formatter/commit/b2bbaaf ), [Roy Riojas](https://github.com/Roy Riojas), 03/03/2015 07:12:33

    
  - First commit - [3b57598]( https://github.com/royriojas/eslint-friendly-formatter/commit/3b57598 ), [Roy Riojas](https://github.com/Roy Riojas), 03/03/2015 06:33:23

    
- **Documentation**
  - bump version to include the fix in the typo in the Readme - [457516d]( https://github.com/royriojas/eslint-friendly-formatter/commit/457516d ), [Roy Riojas](https://github.com/Roy Riojas), 03/03/2015 15:13:02

    
  - Add module usage example - [1129146]( https://github.com/royriojas/eslint-friendly-formatter/commit/1129146 ), [Roy Riojas](https://github.com/Roy Riojas), 03/03/2015 06:39:05

    
  - Fix typo in Readme - [eebb0e2]( https://github.com/royriojas/eslint-friendly-formatter/commit/eebb0e2 ), [Roy Riojas](https://github.com/Roy Riojas), 03/03/2015 06:34:07

    
- **undefined**
  - typo - [4f7c296]( https://github.com/royriojas/eslint-friendly-formatter/commit/4f7c296 ), [Oleg Kislitsyn](https://github.com/Oleg Kislitsyn), 03/03/2015 15:02:15

    
- **Other changes**
  - Initial commit - [d448b2c]( https://github.com/royriojas/eslint-friendly-formatter/commit/d448b2c ), [Roy Riojas](https://github.com/Roy Riojas), 03/03/2015 03:00:04

    
