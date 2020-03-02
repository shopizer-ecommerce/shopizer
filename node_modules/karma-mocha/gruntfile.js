module.exports = function (grunt) {
  grunt.initConfig({
    pkgFile: 'package.json',
    files: {
      adapter: [
        'src/adapter.js'
      ]
    },
    build: {
      adapter: '<%= files.adapter %>'
    },
    eslint: {
      target: [
        '<%= files.adapter %>',
        '*.js',
        'lib/index.js',
        'tasks/*.js',
        'test/**/*.js'
      ]
    },
    karma: {
      adapter: {
        configFile: 'karma.conf.js',
        autoWatch: false,
        singleRun: true,
        reporters: ['dots']
      }
    },
    'npm-publish': {
      options: {
        requires: ['build']
      }
    },
    'npm-contributors': {
      options: {
        commitMessage: 'chore: update contributors'
      }
    },
    conventionalChangelog: {
      release: {
        options: {
          changelogOpts: {
            preset: 'angular'
          }
        },
        src: 'CHANGELOG.md'
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
    }
  })

  require('load-grunt-tasks')(grunt)
  grunt.loadTasks('tasks')
  grunt.registerTask('default', ['build', 'eslint', 'test'])
  grunt.registerTask('test', ['karma'])

  grunt.registerTask('release', 'Build, bump and publish to NPM.', function (type) {
    grunt.task.run([
      'npm-contributors',
      'bump:' + (type || 'patch') + ':bump-only',
      'build',
      'conventionalChangelog',
      'bump-commit',
      'npm-publish'
    ])
  })
}
