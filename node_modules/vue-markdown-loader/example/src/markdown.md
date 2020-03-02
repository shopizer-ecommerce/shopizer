
# Hello

`<span>{{sss}}</span>`

> This is test.

- How are you?
- Fine, Thank you, and you?
- I'm fine， too. Thank you.
- 🌚

```javascript
import Vue from 'vue'

Vue.config.debug = true
```

<div class="test">
  {{ model }} test
</div>

<compo>{{ model }}</compo>

<div
  class="abc"
  @click="show = false">
  啊哈哈哈
</div>

> All script or style tags in html mark will be extracted.Script will be excuted, and style will be added to document head.
> Notice if there is a string instance which contains special word "&lt;/script>", it will fetch a SyntaxError.
> Due to the complexity to solve it, just don't do that.
```html
<style scoped>
  .test {
    background-color: green;
  }
</style>

<style scoped>
  .abc {
    background-color: yellow;
  }
</style>
<script>
  let a=1<2;
  let b="<-forget it-/script>";
  console.log("***This script tag is successfully extracted and excuted.***")
  module.exports = {
    components: {
      compo: {
        render(h) {
          return h('div', {
            style: {
              background: 'red'
            }
          }, this.$slots.default);
        }
      }
    },

    data () {
      return {
        model: 'abc'
      }
    }
  }
</script>
jjjjjjjjjjjjjjjjjjjjjj
<template>
  <div></div>
</template>
```



<div>
</div>

sadfsfs

大家哦哦好啊谁都发生地方上的冯绍峰s

> sahhhh

<compo>{{ model }}</compo>

```html
<compo>{{model }}{{model }}{{model }}{{model }}{{ model }}</compo>
```



<style src="./custom.css"></style>

## 引入 style 文件

<div class="custom">
  原谅色
</div>
