# vue-tree-navigation [![Version](https://img.shields.io/npm/v/vue-tree-navigation.svg)](https://www.npmjs.com/package/vue-tree-navigation) [![Coverage Status](https://coveralls.io/repos/github/MisRob/vue-tree-navigation/badge.svg?branch=master)](https://coveralls.io/github/MisRob/vue-tree-navigation?branch=master) [![License](https://img.shields.io/npm/l/vue-tree-navigation.svg)](https://www.npmjs.com/package/vue-tree-navigation)

> Vue.js 2 tree navigation with vue-router support

For more information see [documentation/demo](https://vue-tree-navigation.misrob.cz)

## Features

- unlimited number of levels
- optional [vue-router](https://router.vuejs.org/en/) support (v2.0.0 or higher)
- a possibility to define a default open level
- auto-open a level when a corresponding URL visited
- focused on core functionality, only necessary styles included
- elements are provided with meaningful classes to make customizations comfortable (for example `NavigationItem--active`, `NavigationLevel--level-1`, `NavigationLevel--closed`)
- external URLs support

## Example

```html
<template>
  <vue-tree-navigation :items="items" :defaultOpenLevel="1" />
</template>

<script>
  export default {
    data() {
      return {
        items: [
          { name: 'Products', children: [                       // category label
            { name: 'Shoes', route: 'shoes' }                   // /shoes
          ]},
          { name: 'About', route: 'about', children: [          // /about
            { name: 'Contact', route: 'contact', children: [    // /about/contact       
              { name: 'E-mail', element: 'email' },             // /about/contact#email
              { name: 'Phone', element: 'phone' }               // /about/contact#phone
            ]},
          ]},
          { name: 'Github', external: 'https://github.com' },   // https://github.com
        ],
      };
    },
  };
</script>
```

## Installation

### NPM

```console
$ npm install vue-tree-navigation
```

*main.js*

```javascript
import VueTreeNavigation from 'vue-tree-navigation';

Vue.use(VueTreeNavigation);
```

### Include with a script tag

```html
<script src="https://unpkg.com/vue-tree-navigation@3.0.0/dist/vue-tree-navigation.js"></script>

<script>
  Vue.use(VueTreeNavigation)
</script>
```

*Example*

```html
<div id="app">
  <vue-tree-navigation :items="items" :defaultOpenLevel="1" />
</div>

<script>
  Vue.use(VueTreeNavigation)

  const app = new Vue({
    el: '#app',
    data: {
      items: [
        ...
      ],
    }
  })
</script>
```

## Requirements

- [Vue.js](https://vuejs.org/)

## Developers

```console
$ yarn dev

$ yarn build

$ yarn prettier
$ yarn lint

$ yarn unit
$ yarn unit --verbose
```
