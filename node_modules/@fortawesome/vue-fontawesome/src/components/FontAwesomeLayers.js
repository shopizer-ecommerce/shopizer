import { config } from '@fortawesome/fontawesome-svg-core'
import { addStaticClass } from '../utils'

export default {
  name: 'FontAwesomeLayers',

  functional: true,

  props: {
    fixedWidth: {
      type: Boolean,
      default: false
    }
  },

  render (createElement, context) {
    const { familyPrefix } = config
    let { data: { staticClass } } = context

    const classes = [
      `${familyPrefix}-layers`,
      ...(context.props.fixedWidth ? [`${familyPrefix}-fw`] : [])
    ]

    return createElement(
      'div',
      {
        ...context.data,
        staticClass: addStaticClass(staticClass, classes)
      },
      context.children
    )
  }
}
