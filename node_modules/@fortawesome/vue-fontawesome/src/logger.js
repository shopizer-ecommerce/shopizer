let PRODUCTION = false

try {
  PRODUCTION = process.env.NODE_ENV === 'production'
} catch (e) { }

export default function (...args) {
  if (!PRODUCTION && console && typeof console.error === 'function') {
    console.error(...args)
  }
}
