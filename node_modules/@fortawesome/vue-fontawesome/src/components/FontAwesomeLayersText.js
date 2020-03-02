import { parse, text } from '@fortawesome/fontawesome-svg-core'
import convert from '../converter'
import { objectWithKey } from '../utils'

export default {
  name: 'FontAwesomeLayersText',

  functional: true,

  props: {
    value: {
      type: [String, Number],
      default: ''
    },
    transform: {
      type: [String, Object],
      default: null
    }
  },

  render (createElement, context) {
    const { props } = context
    const transform = objectWithKey('transform', (typeof props.transform === 'string') ? parse.transform(props.transform) : props.transform)

    const renderedText = text(props.value.toString(), { ...transform })

    const { abstract } = renderedText

    const convertCurry = convert.bind(null, createElement)

    return convertCurry(abstract[0], {}, context.data)
  }
}
