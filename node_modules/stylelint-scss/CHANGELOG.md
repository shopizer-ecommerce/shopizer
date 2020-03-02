# 2.5.0

- Added: `at-least-one-space` option to `dollar-variable-colon-space-after` rule.
- Fixed: `dollar-variable-colon-newline-after` now does not require a newline for Sass maps and multiline variables with parentheses when `always-multi-line` option is used.

# 2.4.0

- Added: support for stylelint version 9.
- Fixed: `dollar-variable-colon-newline-after` now allows multiline variables when `always` option is used.

# 2.3.0

- Added: `dollar-variable-default` rule.

# 2.2.0

- Added: `at-function-named-arguments` rule.
- Added: `at-mixin-named-arguments` rule.

# 2.1.0

- Added: `at-else-if-parentheses-space-before` rule.
- Added: `at-function-parentheses-space-before` rule.
- Added: `at-mixin-parentheses-space-before` rule.

# 2.0.1

- Fixed: `selector-no-redundant-nesting-selector` now handles multiple nested selectors.

# 2.0.0

This version updates stylelint to version 8 and removes 2 rules that were deprecated in earlier versions.

- Breaking changes:
  - Updated: stylelint dependency from version 7 to version 8.
  - Changed: stylelint is now listed in `peerDependencies` instead of `dependencies`. This means that you need to have `stylelint` installed in your project before using `stylelint-scss`.
  - Changed: white/blacklists and ignore* options to be case sensitive by default. See https://github.com/stylelint/stylelint/pull/2709
  - Removed: 2 deprecated rules
    - `at-import-no-partial-extension`
    - `at-mixin-no-argumentless-call-parentheses`

# 1.5.2

- Fixed: `operator-no-unspaced` support escaped operators by handling them in `sassValueParser`.
- Fixed: `declaration-nested-properties` support escaped selectors by checking for escaped characters in `parseNestedPropRoot`.

# 1.5.1

- Fixed: `at-rule-no-unknown` add missing export to `ruleName`.
- Fixed: `at-rule-no-unknown` add options validation.

# 1.5.0

- Added: `at-rule-no-unknown` rule.

# 1.4.4

- Fixed: `at-if-closing-brace-newline-after`: support `@elseif`.

# 1.4.3

- Fixed: `at-mixin-no-argumentless-call-parentheses` messages

# 1.4.2:

- Fixed: false positives in inline comment detecting by `findCommentsInRaws` if a comment is the first/last in a file/line
- Fixed: `findCommentsInRaws` error in function detection

# 1.4.1

- Fixed: mixed import names for `at-else-closing-brace-space-after` and `at-else-empty-line-before` rules.
- Fixed: false positives for nested props rules (`:not()`-like selectors, strings, numbers).

# 1.4.0

- Added: `at-else-closing-brace-newline-after` rule.
- Added: `at-else-closing-brace-space-after` rule.
- Added: `at-if-closing-brace-newline-after` rule.
- Added: `at-if-closing-brace-space-after` rule.
- Added: `at-else-empty-line-before` rule.
- Added: `declaration-nested-properties` rule.
- Added: `declaration-nested-properties-no-divided-groups` rule.
- Added: `dollar-variable-empty-line-before` rule.
- Added: `ignore: "local"|"global"` to the `dollar-variable-pattern` rule.
- Added: `docs` folder to `npm` package.
- Removed: `src` folder from `npm` package.
- Removed: NodeJS 0.12.x support, stylelint-scss now requires NodeJS > 4.2.1 LTS or greater

# 1.3.4

- Fixed: parsing `-` and `+` at the operation start in `operator-` rules.
- Fixed: `findCommentsInRaws` false positives on comments inside strings (applicable to rules `double-slash-comment-inline`, `double-slash-comment-whitespace-inside`, `operator-no-unspaced`).

# 1.3.3

- Fixed: parsing `%` character by `operator-` rules.
- Fixed: false positives on `operator-` rules.

# 1.3.2

- Fixed: `findCommentsInRaws` fail on parsing selectors like `p:not(.not-p)` (applicable to rules `double-slash-comment-inline`, `double-slash-comment-whitespace-inside`, `operator-no-unspaced`).
- Fixed: 'double-slash-comment-whitespace-inside' false positives on empty comments (e.g. `//`).
- Fixed: `findCommentsInRaws` giving wrong column number (applicable to rules `double-slash-comment-inline`, `double-slash-comment-whitespace-inside`, `operator-no-unspaced`).

# 1.3.1

- Fixed: `findCommentsInRaws` for multiline CSS comments and text for //-comments (`double-slash-comment-` rules and `operator-no-unspaced` rule).

# 1.3.0

- Added: `at-mixin-argumentless-call-parentheses` rule (with "always"/"never" behavior as a replacement for `at-mixin-no-argumentless-call-parentheses`).
- Added: `dollar-variable-colon-newline-after` rule.
- Added: `dollar-variable-colon-space-after` rule.
- Added: `dollar-variable-colon-space-before` rule.
- Added: `double-slash-comment-empty-line-before` rule.
- Added: `double-slash-comment-inline` rule.
- Added: `double-slash-comment-whitespace-inside` rule.
- Added: `operator-no-newline-after` rule.
- Added: `operator-no-newline-before` rule.
- Added: `operator-no-unspaced` rule.
- Deprecated: `at-mixin-no-argumentless-call-parentheses`.
- Fixed: `partial-no-import` failing when linting a code string (not in an actual file, e.g. via stylelilnt Node API).
- Updated stylelint dependency to version 7.

# 1.2.1

- Fixed: `at-function-pattern`, `at-mixin-pattern` failing if there are parens inside a parameters list.

# 1.2.0

- Added: `partial-no-import` rule.
- Added: `media-feature-value-dollar-variable` rule.
- Added: `at-import-partial-extension-blacklist` rule.
- Added: `at-import-partial-extension-whitelist` rule.
- Deprecated: `at-import-no-partial-extension` rule.
- Fixed: `dollar-variable-no-missing-interpolation` was throwing an error on older Node.js versions.

# 1.1.1

- Fixed: newlines inside braces in `at-function-pattern`, `at-mixin-pattern`.
- Fixed: false positives and false negatives in `selector-no-redundant-nesting-selector`.

# 1.1.0

- Added: `at-mixin-no-argumentless-call-parentheses` rule.
- Added: `at-import-no-partial-leading-underscore` rule.
- Added: `at-import-no-partial-extension` rule.
- Added: `percent-placeholder-pattern` rule.
- Fixed: `selector-no-redundant-nesting-selector` no longer warns about BEM syntax.
- Fixed: bug causing rules to ignore severity levels `warning` / `error` and report `ignore` instead.

# 1.0.0

- Added: `at-extend-no-missing-placeholder` rule.
- Added: `at-function-pattern` rule.
- Added: `at-mixin-pattern` rule.
- Added: `dollar-variable-no-missing-interpolation` rule.
- Added: `dollar-variable-pattern` rule.
- Added: `selector-no-redundant-nesting-selector` rule.
