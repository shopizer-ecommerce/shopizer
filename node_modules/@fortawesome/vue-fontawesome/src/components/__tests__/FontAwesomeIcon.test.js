import Vue from 'vue/dist/vue'
import FontAwesomeIcon from '../FontAwesomeIcon'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCoffee, faCircle } from '../__fixtures__/icons'
import { compileAndMount, mountFromProps } from '../__fixtures__/helpers'

beforeEach(() => {
  library.add(faCoffee, faCircle)
  Vue.component('font-awesome-icon', FontAwesomeIcon)
})

test('using array format', () => {
  const vm = mountFromProps({ icon: ['fas', 'coffee'] })

  expect(vm.$el.tagName).toBe('svg')
  expect(vm.$el.classList.contains('fa-coffee')).toBeTruthy()
})

test('using string format', () => {
  const vm = mountFromProps({ icon: 'coffee' })

  expect(vm.$el.tagName).toBe('svg')
  expect(vm.$el.classList.contains('fa-coffee')).toBeTruthy()
})

test('missing icon', () => {
  const vm = mountFromProps({ icon: ['fas', 'noicon'] })

  expect(vm.$el.tagName).toBeFalsy()
})

test('using iconDefinition', () => {
  const vm = mountFromProps({ icon: faCoffee })

  expect(vm.$el.tagName).toBe('svg')
  expect(vm.$el.classList.contains('fa-coffee')).toBeTruthy()
})

describe('unrelated Vue data options', () => {
  test('with extra static class', () => {
    const vm = compileAndMount(
      `<font-awesome-icon class="extra" :icon="icon" />`,
      { data: { icon: faCoffee } }
    )

    expect(vm.$el.classList.contains('extra')).toBeTruthy()
  })

  test('with extra bound class', () => {
    const vm = compileAndMount(
      `<font-awesome-icon :class="['extra1', {'extra2': true}]" :icon="icon" />`,
      { data: { icon: faCoffee } }
    )

    expect(vm.$el.classList.contains('extra1')).toBeTruthy()
    expect(vm.$el.classList.contains('extra2')).toBeTruthy()
  })

  test('with extra style', () => {
    const vm = compileAndMount(
      `<font-awesome-icon :style="{'font-size': '42px'}" :icon="icon" />`,
      { data: { icon: faCoffee } }
    )

    expect(vm.$el.style.getPropertyValue('font-size')).toBe('42px')
  })

  test('with extra DOM property', () => {
    const vm = compileAndMount(
      `<font-awesome-icon rel="local" :icon="icon" />`,
      { data: { icon: faCoffee } }
    )

    expect(vm.$el.getAttribute('rel')).toBe('local')
  })

  test('with listener', () => {
    let hasBeenClicked = false

    const vm = compileAndMount(
      `<font-awesome-icon @click="clicked" :icon="icon" />`,
      { data: { icon: faCoffee }, methods: { clicked () { hasBeenClicked = true } } }
    )

    expect(hasBeenClicked).toBeFalsy()
    vm.$el.dispatchEvent(new Event('click'))
    expect(hasBeenClicked).toBeTruthy()
  })
})

test('using border', () => {
  const vm = mountFromProps({ icon: faCoffee, border: true })

  expect(vm.$el.classList.contains('fa-border')).toBeTruthy()
})

test('using fixedWidth', () => {
  const vm = mountFromProps({ icon: faCoffee, fixedWidth: true })

  expect(vm.$el.classList.contains('fa-fw')).toBeTruthy()
})

describe('using flip', () => {
  test('horizontal', () => {
    const vm = mountFromProps({ icon: faCoffee, flip: "horizontal" })

    expect(vm.$el.classList.contains('fa-flip-horizontal')).toBeTruthy()
  })

  test('vertical', () => {
    const vm = mountFromProps({ icon: faCoffee, flip: "vertical" })

    expect(vm.$el.classList.contains('fa-flip-vertical')).toBeTruthy()
  })

  test('both', () => {
    const vm = mountFromProps({ icon: faCoffee, flip: "both" })

    expect(vm.$el.classList.contains('fa-flip-horizontal')).toBeTruthy()
    expect(vm.$el.classList.contains('fa-flip-vertical')).toBeTruthy()
  })
})

test('using listItem', () => {
  const vm = mountFromProps({ icon: faCoffee, listItem: true })

  expect(vm.$el.classList.contains('fa-li')).toBeTruthy()
})

describe('using pull', () => {
  test('right', () => {
    const vm = mountFromProps({ icon: faCoffee, pull: "right" })

    expect(vm.$el.classList.contains('fa-pull-right')).toBeTruthy()
  })

  test('left', () => {
    const vm = mountFromProps({ icon: faCoffee, pull: "left" })

    expect(vm.$el.classList.contains('fa-pull-left')).toBeTruthy()
  })
})

test('using pulse', () => {
  const vm = mountFromProps({ icon: faCoffee, pulse: true })

  expect(vm.$el.classList.contains('fa-pulse')).toBeTruthy()
})

describe('using rotation', () => {
  test('90', () => {
    const vm = mountFromProps({ icon: faCoffee, rotation: 90 })

    expect(vm.$el.classList.contains('fa-rotate-90')).toBeTruthy()
  })

  test('180', () => {
    const vm = mountFromProps({ icon: faCoffee, rotation: 180 })

    expect(vm.$el.classList.contains('fa-rotate-180')).toBeTruthy()
  })

  test('270', () => {
    const vm = mountFromProps({ icon: faCoffee, rotation: 270 })

    expect(vm.$el.classList.contains('fa-rotate-270')).toBeTruthy()
  })
})

test('using size', () => {
  ['lg', 'xs', 'sm', '1x', '2x', '3x', '4x', '5x', '6x', '7x', '8x', '9x', '10x'].forEach(size => {
    const vm = mountFromProps({ icon: faCoffee, size: size })

    expect(vm.$el.classList.contains(`fa-${size}`)).toBeTruthy()
  })
})

test('using spin', () => {
  const vm = mountFromProps({ icon: faCoffee, spin: true })

  expect(vm.$el.classList.contains('fa-spin')).toBeTruthy()
})

describe('using transform', () => {
  test('string', () => {
    const vm = mountFromProps({ icon: faCoffee, transform: 'grow-40 left-4 rotate-15' })

    expect(vm.$el).toBeTruthy()
  })

  test('object', () => {
    const vm = mountFromProps({ icon: faCoffee, transform: { flipX: false, flipY: false, rotate: 15, size: 56, x: -4, y: 0 } })

    expect(vm.$el).toBeTruthy()
  })
})

describe('mask', () => {
  test('will add icon', () => {
    const vm = mountFromProps({ icon: faCoffee, mask: faCircle })

    expect(vm.$el.innerHTML).toMatch(/clipPath/)
  })

  test('will add icon referencing librbary', () => {
    const vm = mountFromProps({ icon: ['fas', 'coffee'], mask: ['fas', 'circle'] })
  })
})

describe('symbol', () => {
  test("will not create a symbol", () => {
    const vm = mountFromProps({ icon: faCoffee })

    expect(vm.$el.style.getPropertyValue('display'))
      .toBe('')
  })

  test("will create a symbol", () => {
    const vm = mountFromProps({ icon: faCoffee, symbol: 'coffee-icon' })

    expect(vm.$el.style.getPropertyValue('display'))
      .toBe('none')
    expect(vm.$el.children[0].tagName)
      .toBe('symbol')
  })
})
