module.exports = function () {
  return {
    files: [
      'lib/**/*@(.js)'
    ],

    tests: [
      './test/lib/**/*.spec@(.js)'
    ],

    env: {
      type: 'node'
    },

    testFramework: 'mocha'
  }
}
