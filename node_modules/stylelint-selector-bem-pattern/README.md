# stylelint-selector-bem-pattern

[![Build Status](https://travis-ci.org/davidtheclark/stylelint-selector-bem-pattern.svg?branch=master)](https://travis-ci.org/davidtheclark/stylelint-selector-bem-pattern)

A [stylelint](https://github.com/stylelint/stylelint) plugin that incorporates [postcss-bem-linter](https://github.com/postcss/postcss-bem-linter).

**LOOKING FOR NEW MAINTAINERS!** I am no longer using this package myself, so would like to hand it over to someone who is actively using it. See #33.

To learn more about postcss-bem-linter, please read [that module's documentation](https://github.com/postcss/postcss-bem-linter).

## Installation

```
npm install stylelint-selector-bem-pattern
```

Be warned: v0.2.0+ is only compatible with stylelint v3+. For earlier version of stylelint, use earlier versions of this.

## Usage

Add it to your stylelint config `plugins` array, then add `"plugin/selector-bem-pattern"` to your rules,
specifying your postcss-bem-linter settings as the primary option.

Even though postcss-bem-linter has the default setting of `{ preset: 'suit' }`, this plugin has
no default setting: if you want to use the SUIT preset, you must pass `{ preset: 'suit' }`,
and the rule will not work if you do not pass a primary option object.

Like so:

```js
// .stylelintrc
{
  "plugins": [
    "stylelint-selector-bem-pattern"
  ],
  "rules": {
    // ...
    "plugin/selector-bem-pattern": {
      "componentName": "[A-Z]+",
      "componentSelectors": {
        "initial": "^\\.{componentName}(?:-[a-z]+)?$",
        "combined": "^\\.combined-{componentName}-[a-z]+$"
      },
      "utilitySelectors": "^\\.util-[a-z]+$"
    },
    // ...
  }
}
```

For more examples of postcss-bem-linter configuration possibilities,
please read [that module's documentation](https://github.com/postcss/postcss-bem-linter).
Keep in mind that if your stylelint config is JSON you will have to use strings to
specify your selector patterns (as above).
