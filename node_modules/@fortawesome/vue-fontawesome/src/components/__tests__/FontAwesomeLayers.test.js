import Vue from 'vue/dist/vue'
import FontAwesomeLayers from '../FontAwesomeLayers'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCoffee, faCircle } from '../__fixtures__/icons'
import { compileAndMount, mountFromProps } from '../__fixtures__/helpers'

beforeEach(() => {
  library.add(faCoffee, faCircle)
  Vue.component('font-awesome-layers', FontAwesomeLayers)
})

test('empty layers', () => {
  const vm = compileAndMount(
    `<font-awesome-layers />`
  )

  expect(vm.$el.children.length).toBe(0)
})

test('empty layers', () => {
  const vm = compileAndMount(
    `<font-awesome-layers><i /><i /></font-awesome-layers>`
  )

  expect(vm.$el.children.length).toBe(2)
})

describe('class handling', () => {
  test('extra static', () => {
    const vm = compileAndMount(
      `<font-awesome-layers class="extra" />`,
    )

    expect(vm.$el.getAttribute('class'))
      .toBe('extra fa-layers')
  })

  test('extra bound', () => {
    const vm = compileAndMount(
      `<font-awesome-layers :class="['extra']" />`,
    )

    expect(vm.$el.getAttribute('class'))
      .toBe('fa-layers extra')
  })

  test('fixed width', () => {
    const vm = compileAndMount(
      `<font-awesome-layers fixed-width />`,
    )

    expect(vm.$el.getAttribute('class'))
      .toBe('fa-layers fa-fw')
  })
})
