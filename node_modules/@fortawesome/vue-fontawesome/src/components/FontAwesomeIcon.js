import { parse as faParse, icon as faIcon } from '@fortawesome/fontawesome-svg-core'
import convert from '../converter'
import log from '../logger'
import { objectWithKey, classList } from '../utils'

function normalizeIconArgs (icon) {
  if (icon === null) {
    return null
  }

  if (typeof icon === 'object' && icon.prefix && icon.iconName) {
    return icon
  }

  if (Array.isArray(icon) && icon.length === 2) {
    return { prefix: icon[0], iconName: icon[1] }
  }

  if (typeof icon === 'string') {
    return { prefix: 'fas', iconName: icon }
  }
}

export default {
  name: 'FontAwesomeIcon',

  functional: true,

  props: {
    border: {
      type: Boolean,
      default: false
    },
    fixedWidth: {
      type: Boolean,
      default: false
    },
    flip: {
      type: String,
      default: null,
      validator: (value) => ['horizontal', 'vertical', 'both'].indexOf(value) > -1
    },
    icon: {
      type: [Object, Array, String],
      required: true
    },
    mask: {
      type: [Object, Array, String],
      default: null
    },
    listItem: {
      type: Boolean,
      default: false
    },
    pull: {
      type: String,
      default: null,
      validator: (value) => ['right', 'left'].indexOf(value) > -1
    },
    pulse: {
      type: Boolean,
      default: false
    },
    rotation: {
      type: Number,
      default: null,
      validator: (value) => [90, 180, 270].indexOf(value) > -1
    },
    size: {
      type: String,
      default: null,
      validator: (value) => ['lg', 'xs', 'sm', '1x', '2x', '3x', '4x', '5x', '6x', '7x', '8x', '9x', '10x'].indexOf(value) > -1
    },
    spin: {
      type: Boolean,
      default: false
    },
    transform: {
      type: [String, Object],
      default: null
    },
    symbol: {
      type: [Boolean, String],
      default: false
    }
  },

  render (createElement, context) {
    const { props } = context

    const { icon: iconArgs, mask: maskArgs, symbol } = props
    const icon = normalizeIconArgs(iconArgs)
    const classes = objectWithKey('classes', classList(props))
    const transform = objectWithKey('transform', (typeof props.transform === 'string') ? faParse.transform(props.transform) : props.transform)
    const mask = objectWithKey('mask', normalizeIconArgs(maskArgs))

    const renderedIcon = faIcon(
      icon,
      { ...classes, ...transform, ...mask, symbol }
    )

    if (!renderedIcon) {
      return log('Could not find one or more icon(s)', icon, mask)
    }

    const { abstract } = renderedIcon
    const convertCurry = convert.bind(null, createElement)

    return convertCurry(abstract[0], {}, context.data)
  }
}
