# jQuery contextMenu plugin & polyfill #

---

> [**This repository now has a new maintainer**](https://github.com/swisnl/jQuery-contextMenu/issues/257)

---

__IMPORTANT: 2.0.0 is release and has change the default names of the icon classes in order to stop CSS conflicts with frameworks which define the class 'icon'.__


[![Travis Build Status](https://travis-ci.org/swisnl/jQuery-contextMenu.svg?branch=master)](https://travis-ci.org/swisnl/jQuery-contextMenu)

$.contextMenu is a management facility for - you guessed it - context menus. It was designed for an application where there are hundreds of elements that may show a context menu - so intialization speed and memory usage are kept fairly small. It also allows to register context menus without providing actual markup, as $.contextMenu generates DOMElements as needed.

[features](http://swisnl.github.io/jQuery-contextMenu/index.html) - 
[demo](http://swisnl.github.io/jQuery-contextMenu/demo.html) - 
[documentation](http://swisnl.github.io/jQuery-contextMenu/docs.html)


## Dependencies ##

* jQuery 1.8.2
* jQuery UI position (optional but recommended)

## Usage ##

register contextMenu from javascript:

```javascript
$.contextMenu({
    // define which elements trigger this menu
    selector: ".with-cool-menu",
    // define the elements of the menu
    items: {
        foo: {name: "Foo", callback: function(key, opt){ alert("Foo!"); }},
        bar: {name: "Bar", callback: function(key, opt){ alert("Bar!") }}
    }
    // there's more, have a look at the demos and docs...
});
```

have a look at the [demos](http://swisnl.github.io/jQuery-contextMenu/demo.html).


## HTML5 Compatibility ##

Firefox 8 implemented contextmenu using the &lt;menuitem&gt; tags for menu-structure. The specs however state that &lt;command&gt; tags should be used for this purpose. $.contextMenu accepts both.

Firefox 8 does not yet fully implement the contextmenu specification ([Ticket #617528](https://bugzilla.mozilla.org/show_bug.cgi?id=617528)). The elements
[a](http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#using-the-a-element-to-define-a-command),
[button](http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#using-the-button-element-to-define-a-command),
[input](http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#using-the-input-element-to-define-a-command) and
[option](http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#using-the-option-element-to-define-a-command) 
usable as commands are being ignored altogether. It also doesn't (optically) distinguish between checkbox/radio and regular commands ([Bug #705292](https://bugzilla.mozilla.org/show_bug.cgi?id=705292)).

* [contextmenu specs](http://www.w3.org/TR/html5/interactive-elements.html#context-menus)
* [command specs](http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html)
* [Browser support according to caniuse.com](http://caniuse.com/#search=context%20menu)

Note: While the specs note &lt;option&gt;s to be renderd as regular commands, $.contextMenu will render an actual &lt;select&gt;. import contextMenu from HTML5 &lt;menu&gt;:

```javascript
$.contextMenu("html5");
```

## Interaction Principles ##

You're (obviously) able to use the context menu with your mouse. Once it is opened, you can also use the keyboard to (fully) navigate it.

* ↑ (up) previous item in list, will skip disabled elements and wrap around
* ↓ (down) next item in, will skip disabled elements and wrap around
* → (right) dive into sub-menu
* ← (left) rise from sub-menu
* ↵ (return) invoke command
* ⇥ (tab) next item or input element, will skip disabled elements and wrap around
* ⇪ ⇥ (shift tab) previous item or input element, will skip disabled elements and wrap around
* ⎋ (escape) close menu
* ⌴ (space) captured and ignore to avoid page scrolling (for consistency with native menus)
* ⇞ (page up) captured and ignore to avoid page scrolling (for consistency with native menus)
* ⇟ (page down) captured and ignore to avoid page scrolling (for consistency with native menus)
* ↖ (home) first item in list, will skip disabled elements
* ↘ (end) last item in list, will skip disabled elements

Besides the obvious, browser also react to alphanumeric key strokes. Hitting <code>r</code> in a context menu will make Firefox (8) reload the page immediately. Chrome selects the option to see infos on the page, Safari selects the option to print the document. Awesome, right? Until trying the same on Windows I did not realize that the browsers were using the access-key for this. I would've preferred typing the first character of something, say "s" for "save" and then iterate through all the commands beginning with s. But that's me - what do I know about UX? Anyways, $.contextMenu now also supports accesskey handling.


## Authors ##

* [Björn Brala](https://github.com/swisnl)
* [Rodney Rehm](https://github.com/rodneyrehm) (original creator)
* [Christiaan Baartse](https://github.com/christiaan) (single callback per menu)
* [Addy Osmani](https://github.com/addyosmani) (compatibility with native context menu in Firefox 8)


## License ##

$.contextMenu is published under the [MIT license](http://www.opensource.org/licenses/mit-license)

## Special thanks ##

Font-Awesome icons used from [encharm/Font-Awesome-SVG-PNG](https://github.com/encharm/Font-Awesome-SVG-PNG).

## Changelog ##

###  2.0.1 (December 3rd 2015 ###

* Remove executable bit from jquery.contextMenu.js (thanks @jacknagel)
* Fixed a problem there was when using a function for icons (thanks @RareDevil)
* Fixed a problem where submenus resized wrong (thanks @RareDevil)
* Fixed a problem where the contextmenu would open another menu (thanks @RareDevil) - ([Issue #252](https://github.com/swisnl/jQuery-contextMenu/issues/252) and [Issue #293](https://github.com/swisnl/jQuery-contextMenu/issues/293))
* Fixed regression of node name's not being appended to the label of input elements. (thanks @RareDevil)
* Add check that root.$layer exists, to prevent calling hide() on an defined object. (thanks @andreasrosdal)


### 2.0.0 (October 28th 2015) ###

* __This version changes the default names of the icon classes in order to stop CSS conflicts with frameworks which define the class 'icon'.__ In order to keep the icon names the same as before this change you can change the defaults on the classnames for the icons ([docs on classNames option](http://swisnl.github.io/jQuery-contextMenu/docs.html#options-classNames)). The classnames will probably be "context-menu-icon-*" as proposed earlier by @rodneyrehm.
* You can not use SASS to customize your contextmenu. The gulp command build-icons takes all the SVG icons from src/icons and builds them into a font. In order to this we needed to break backwards compatibility. This does mean the new CSS does not have the old .icon class defined which makes it a lot more stable within CSS frameworks. The first revision of the documentation is found [here](documentation/docs/customize.md).
* The 1.x branch will be maintained for a while with bugfixes. But support for 1.x will be dropped in the coming months.
* Reverted the change from 1.7.0: .html() changed back to .text() since it is an security issue (thanks @arai-a)

### 1.10.1 (October 25th 2015) ###

* Added gulp command (integration-test-paths) to change the paths in the integration tests to the correct path after they are overwritten by the documentation generator.
* Make sure the contextmenu is not outside the client area by (thanks to @arai-a)
* Update jQuery dependecy so that it will not result in double installation of jQuery when using npm (thanks to @fredericlb)

### 1.9.1 (October 11th 2015) ###

* Fixed a bug where the classNames options would fail on a submenu.
* New documentation site and generation using [couscous](https://github.com/CouscousPHP/Couscous)

### 1.9.0 (October 1st 2015) ###

* Make classes configurable for those that can easily conflict. See the [docs on classNames option](http://swisnl.github.io/jQuery-contextMenu/docs.html#options-classNames). This also prepares to change classnames to non conflicting defaults so the hassle with frameworks as bootstrap will stop.
* Fix for handling of seperator string. It threw an error on the protected property of String.$node
* Fix for opening the contextmenu at coordinate 0,0 (by [Andreme](https://github.com/andreme))
* Fixed check for jQuery UI ([Issue #182](https://github.com/swisnl/jQuery-contextMenu/issues/182))
* Updated doc for function argument for icon

### 1.8.1 (September 14th 2015) ###

* Updated readme.
* Updated dist files

### 1.8.0 (September 14th 2015) - dist files not updated! ###

* Added dist folder with compiled JS and CSS, added these files to package and bower configuration.
* Fixed doc link for jQuery UI position ([Issue #274](https://github.com/swisnl/jQuery-contextMenu/issues/274))
* Item icon can now be a callback to dynamically decide on icon class. - ([Issue #158](https://github.com/swisnl/jQuery-contextMenu/issues/158), [Issue #129](https://github.com/swisnl/jQuery-contextMenu/issues/129), [Issue #151](https://github.com/swisnl/jQuery-contextMenu/issues/151), [Issue #249](https://github.com/swisnl/jQuery-contextMenu/issues/249))
* Small fix to calculating width and height on screen edges when padding is present.

### 1.7.0 (August 29th 2015) ###

* Touch support optimisations (by kccarter76) 
* changed .text to .html so there are no extra span's fixed - ([Issue #252](https://github.com/swisnl/jQuery-contextMenu/issues/252))
* added visibility callback to item definition
* copy the HTML5 icon attribute when creating from HTML5 elements 
* growing menu when opening multiple times fixed - ([Issue #197](https://github.com/swisnl/jQuery-contextMenu/issues/197))
* fixed failure to run tests

### 1.6.8 (August 18th 2015) ###

* changes for new maintainer

### 1.6.7 (May 21st 2015) ###

* looking for maintainer note
* publish to npm

### 1.6.6 (July 12th 2014) ###

* fixing bower manifest

### 1.6.5 (January 20th 2013) ###

* fixing "opening a second menu can break the layer" - ([Issue #105](https://github.com/swisnl/jQuery-contextMenu/issues/105))

### 1.6.4 (January 19th 2013) ###

* fixing [jQuery plugin manifest](https://github.com/swisnl/jQuery-contextMenu/commit/413b1ecaba0aeb4e50f97cee35f7c367435e7830#commitcomment-2465216), again. yep. I'm that kind of a guy. :(

### 1.6.3 (January 19th 2013) ###

* fixing [jQuery plugin manifest](https://github.com/swisnl/jQuery-contextMenu/commit/413b1ecaba0aeb4e50f97cee35f7c367435e7830#commitcomment-2465216)

### 1.6.2 (January 19th 2013) ###

* fixing "menu won't close" regression introduced by 1.6.1

### 1.6.1 (January 19th 2013) ###

* fixing potential html parsing problem
* upgrading to jQuery UI position v1.10.0
* replaced `CRLF` by `LF` (no idea how this happened in the first place...)
* adding `options.reposition` to dis/allow simply relocating a menu instead of rebuilding it ([Issue #104](https://github.com/swisnl/jQuery-contextMenu/issues/104))

### 1.6.0 (December 29th 2012) ###

* adding [DOM Element bound context menus](http://swisnl.github.io/jQuery-contextMenu/demo/on-dom-element.html) - ([Issue 88](https://github.com/swisnl/jQuery-contextMenu/issues/88))
* adding class `context-menu-active` to define state on active trigger element - ([Issue 92](https://github.com/swisnl/jQuery-contextMenu/issues/92))
* adding [demo for TouchSwipe](http://swisnl.github.io/jQuery-contextMenu/demo/trigger-swipe.html) activation
* adding export of internal functions and event handlers - ([Issue 101](https://github.com/swisnl/jQuery-contextMenu/issues/101))
* fixing key "watch" might translate to Object.prototype.watch in callbacks map - ([Issue 93](https://github.com/swisnl/jQuery-contextMenu/issues/93))
* fixing menu and submenu width calculation - ([Issue 18](https://github.com/swisnl/jQuery-contextMenu/issues/18))
* fixing unused variables - ([Issue 100](https://github.com/swisnl/jQuery-contextMenu/issues/100))
* fixing iOS "click" compatibility problem - ([Issue 83](https://github.com/swisnl/jQuery-contextMenu/issues/83))
* fixing separators to not be clickable - ([Issue 85](https://github.com/swisnl/jQuery-contextMenu/issues/85))
* fixing issues with fixed positioned triggers ([Issue 95](https://github.com/swisnl/jQuery-contextMenu/issues/95))
* fixing word break problem - ([Issue 80](https://github.com/swisnl/jQuery-contextMenu/issues/80))

### 1.5.25 (October 8th 2012) ###

* upgrading to jQuery 1.8.2 ([Issue 78](https://github.com/swisnl/jQuery-contextMenu/issues/78))
* upgrading to jQuery UI position 1.9.0 RC1 ([Issue 78](https://github.com/swisnl/jQuery-contextMenu/issues/78))

### 1.5.24 (August 30th 2012) ###

* adding context menu options to input command events ([Issue 72](https://github.com/swisnl/jQuery-contextMenu/issues/72), dtex)
* code cosmetics for JSLint

### 1.5.23 (August 22nd 2012) ###

* fixing reposition/close issue on scrolled documents ([Issue 69](https://github.com/swisnl/jQuery-contextMenu/issues/69))
* fixing jQuery reference ([Issue 68](https://github.com/swisnl/jQuery-contextMenu/issues/68))

### 1.5.22 (July 16th 2012) ###

* fixing issue with animation and remove on hide (Issue #64)

### 1.5.21 (July 14th 2012) ###

* fixing backdrop would not remove on destroy (Issue #63)

### 1.5.20 (June 26th 2012) ###

Note: git tag of version is `v1.6.20`?!

* fixing backdrop would not position properly in IE6 (Issue #59)
* fixing nested input elements not accessible in Chrome / Safari (Issue #58)

### 1.5.19 ###

Note: git tag of version is missing...?!

* fixing sub-menu positioning when `$.ui.position` is not available (Issue #56)

### 1.5.18 ###

Note: git tag of version is missing...?!

* fixing html5 `<menu>` import (Issue #53)

### 1.5.17 (June 4th 2012) ###

* fixing `options` to default to `options.trigger = "right"`
* fixing variable name typo (Within Issue #51)
* fixing menu not closing while opening other menu (Within Issue #51)
* adding workaround for `contextmenu`-bug in Firefox 12 (Within Issue #51)

### 1.5.16 (May 29th 2012) ###

* added vendor-prefixed user-select to CSS
* fixed issue with z-indexing when `<body>` is used as a trigger (Issue #49)

### 1.5.15 (May 26th 2012) ###

* allowing to directly open another element's menu while a menu is shown (Issue #48)
* fixing autohide option that would not properly hide the menu

### 1.5.14 (May 22nd 2012) ###

* options.build() would break default options (Issue #47)
* $.contextMenu('destroy') would not remove backdrop

### 1.5.13 (May 4th 2012) ###

* exposing $trigger to dynamically built custom menu-item types (Issue #42)
* fixing repositioning of open menu (formerly accidental re-open)
* adding asynchronous example
* dropping ignoreRightClick in favor of proper event-type detection

### 1.5.12 (May 2nd 2012) ###

* prevent invoking callback of first item of a sub-menu when clicking on the sub-menu-item (Issue #41)

### 1.5.11 (April 27th 2012) ###

* providing `opt.$trigger` to show event (Issue #39)

### 1.5.10 (April 21st 2012) ###

* ignoreRightClick would not prevent right click when menu is already open (Issue #38)

### 1.5.9 (March 10th 2012) ###

* If build() did not return any items, an empty menu was shown (Issue #33)

### 1.5.8 (January 28th 2012) ###

* Capturing Page Up and Page Down keys to ignore like space (Issue #30)
* Added Home / End keys to jump to first / last command of menu (Issue #29)
* Bug hitting enter in an &lt;input&gt; would yield an error (Issue #28)

### 1.5.7 (January 21st 2012) ###

* Non-ASCII character in jquery.contextMenu.js caused compatibility issues in Rails (Issue #27)

### 1.5.6 (January 8th 2012) ###

* Bug contextmenu event was not passed to build() callback (Issue #24)
* Bug sub-menu markers would not display properly in Safari and Chrome (Issue #25)

### 1.5.5 (January 6th 2012) ###

* Bug Internet Explorer would not close menu when giving input elements focus (Issue #23)

### 1.5.4 (January 5th 2012) ##

* Bug not set z-index of sub-menus might not overlap the main menu correctly (Issue #22)

### 1.5.3 (January 1st 2012) ###

* Bug `console.log is undefined`

### 1.5.2 (December 25th 2012) ###

* Bug sub-menus would not properly update their disabled states (Issue #16) [again…]
* Bug sub-menus would not properly adjust width accoring to min-width and max-width (Issue #18)

### 1.5.1 (December 18th 2011) ###

* Bug sub-menus would not properly update their disabled states (Issue #16)

### 1.5 (December 13th 2011) ###

* Added [dynamic menu creation](http://swisnl.github.io/jQuery-contextMenu/demo/dynamic-create.html) (Issue #15)

### 1.4.4 (December 12th 2011) ###

* Bug positioning &lt;menu&gt; when trigger element is `position:fixed` (Issue #14)

### 1.4.3 (December 11th 2011) ###

* Bug key handler would caputure all key strokes while menu was visible (essentially disabling F5 and co.)

### 1.4.2 (December 6th 2011) ###

* Bug opt.$trigger was not available to disabled callbacks
* jQuery bumped to 1.7.1

### 1.4.1 (November 9th 2011) ###

* Bug where &lt;menu&gt; imports would not pass action (click event) properly

### 1.4 (November 7th 2011) ###

* Upgraded to jQuery 1.7 (changed dependecy!)
* Added internal events `contextmenu:focus`, `contextmenu:blur` and `contextmenu:hide`
* Added custom &lt;command&gt; types
* Bug where `className` wasn't properly set on &lt;menu&gt;

### 1.3 (September 5th 2011) ###

* Added support for accesskeys
* Bug where two sub-menus could be open simultaneously

### 1.2.2 (August 24th 2011) ###

* Bug in HTML5 import

### 1.2.1 (August 24th 2011) ###

* Bug in HTML5 detection

### 1.2 (August 24th 2011) ###

* Added compatibility to &lt;menuitem&gt; for Firefox 8
* Upgraded to jQuery 1.6.2

### 1.1 (August 11th 2011) ###

* Bug #1 TypeError on HTML5 action passthru
* Bug #2 disbaled callback not invoked properly
* Feature #3 auto-hide option for hover trigger
* Feature #4 option to use a single callback for all commands, rather than registering the same function for each item
* Option to ignore right-click (original "contextmenu" event trigger) for non-right-click triggers

### 1.0 (July 7th 2011) ###

* Initial $.contextMenu handler
