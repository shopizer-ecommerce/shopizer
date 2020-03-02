module.exports = function (grunt) {
  grunt.initConfig({
    pkgFile: 'package.json',
    'npm-contributors': {
      options: {
        commitMessage: 'chore: update contributors'
      }
    },
    bump: {
      options: {
        commitFiles: [
          'package.json',
          'CHANGELOG.md'
        ],
        commitMessage: 'chore: release v%VERSION%',
        pushTo: 'upstream'
      }
    },
    'auto-release': {
      options: {
        checkTravisBuild: false
      }
    },
    eslint: {
      target: ['index.js', 'gruntfile.js', 'karma.conf.js', 'test/*.js']
    },
    karma: {
      all: {
        configFile: 'karma.conf.js'
      }
    }
  })
  require('load-grunt-tasks')(grunt)
  grunt.registerTask('test', ['karma'])
  grunt.registerTask('default', ['eslint', 'test'])
  return grunt.registerTask('release', 'Bump the version and publish to NPM.', function (type) {
    return grunt.task.run(['npm-contributors', 'bump-only:' + (type || 'patch'), 'changelog', 'bump-commit', 'npm-publish'])
  })
}
