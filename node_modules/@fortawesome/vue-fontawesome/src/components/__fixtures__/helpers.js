import Vue from 'vue/dist/vue'
import FontAwesomeIcon from '../FontAwesomeIcon'

export function compileAndMount (str, params = {}) {
  const res = Vue.compile(str)
  const vm = new Vue({
    ...params,
    render: res.render,
    staticRenderFn: res.staticRenderFn
  })

  vm.$mount()

  return vm
}

export function mountFromProps (propsData = {}) {
  const opts = {
    render: (h) => h(
      FontAwesomeIcon,
      { props: propsData }
    )
  }

  const vm = new Vue(opts)
  vm.$mount()

  return vm
}
