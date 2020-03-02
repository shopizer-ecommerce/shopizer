import resolve from 'rollup-plugin-node-resolve'
import commonJs from 'rollup-plugin-commonjs'
import babel from 'rollup-plugin-babel'

const name = 'vue-fontawesome'
const globals = {
  '@fortawesome/fontawesome-svg-core': 'FontAwesome'
}

export default {
  external: [
    '@fortawesome/fontawesome-svg-core'
  ],
  input: 'src/index.js',
  output: [
    {
      name,
      globals,
      format: 'umd',
      exports: 'named',
      file: 'index.js',
    },
    {
      name,
      globals,
      format: 'es',
      file: 'index.es.js'
    }
  ],
  plugins: [
    resolve({
      jsnext: true,
      main: true
    }),
    commonJs({
      include: 'node_modules/**'
    }),
    babel({
      babelrc: false,
      presets: [
        ['env', {
          debug: true,
          targets: {"browsers": ["> 1%", "last 2 versions", "ie > 9"]},
          modules: false
        }],
        'stage-3'
      ],
      plugins: [
        'external-helpers'
      ],
      exclude: 'node_modules/**'
    })
  ]
}
