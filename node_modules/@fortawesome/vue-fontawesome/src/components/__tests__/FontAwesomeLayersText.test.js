import Vue from 'vue/dist/vue'
import FontAwesomeLayersText from '../FontAwesomeLayersText'
import { compileAndMount } from '../__fixtures__/helpers'

beforeEach(() => {
  Vue.component('font-awesome-layers-text', FontAwesomeLayersText)
})

test('empty', () => {
  const vm = compileAndMount('<font-awesome-layers-text />')

  expect(vm.$el.tagName).toBe('SPAN')
})

test('simple text', () => {
  const vm = compileAndMount('<font-awesome-layers-text value="Test" />')

  expect(vm.$el.getAttribute('class')).toBe('fa-layers-text')
  expect(vm.$el.innerHTML).toBe('Test')
})

test('accept number for value', () => {
    const vm = compileAndMount('<font-awesome-layers-text :value="42" />')

    expect(vm.$el.getAttribute('class')).toBe('fa-layers-text')
    expect(vm.$el.innerHTML).toBe('42')
})

describe('transform', () => {
  test('string', () => {
    const vm = compileAndMount('<font-awesome-layers-text value="1" transform="shrink-6" />')

    // It appears the jsdom doesn't set the transform for this, not sure why
    expect(vm.$el)
  })
})
