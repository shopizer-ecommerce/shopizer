module.exports = function( grunt ) {

'use strict';

var
	// files
	coreFiles = [
		'splitter.js'
	],

	cssFiles = [
		'examples/main.css'
	],

	// minified files
	minify = {
		options: {
			preserveComments: false
		},
		main: {
			options: {
				banner: createBanner( coreFiles )
			},
			files: {
				'dist/jquery-splitter.min.js': 'dist/jquery-splitter.js'
			}
		}
	},

	compareFiles = {
		all: [
			'dist/jquery-splitter.js',
			'dist/jquery-splitter.min.js'
		]
	};


function expandFiles( files ) {
	return grunt.util._.pluck( grunt.file.expandMapping( files ), 'src' ).map(function( values ) {
		return values[ 0 ];
	});
}

// grunt plugins
require( 'load-grunt-tasks' )( grunt );

function stripDirectory( file ) {
	return file.replace( /.+\/(.+?)>?$/, '$1' );
}

function createBanner( files ) {
	// strip folders
	var fileNames = files && files.map( stripDirectory );
	return '/*! <%= pkg.title || pkg.name %> - v<%= pkg.version %> - ' +
		'<%= grunt.template.today("isoDate") %>\n' +
		'<%= pkg.homepage ? " * " + pkg.homepage + "\\n" : "" %>' +
		(files ? ' * Includes: ' + fileNames.join(', ') + '\n' : '') +
		' * Copyright <%= grunt.template.today("yyyy") %> <%= pkg.author.name %>\n' +
		' * Licensed <%= _.pluck(pkg.licenses, "type").join(", ") %>\n */\n\n\n';
}

grunt.initConfig({
	pkg: grunt.file.readJSON( 'package.json' ),
	files: {
		dist: '<%= pkg.name %>-<%= pkg.version %>'
	},
	compare_size: compareFiles,
	concat: {
		core: {
			options: {
				banner: createBanner( coreFiles ),
				stripBanners: {
					block: true
				}
			},
			src: coreFiles,
			dest: 'dist/jquery-splitter.js'
		}
	},
	jscs: {
	    options: {
	        config: '.jscs.json'
	    },
		core: coreFiles,
		grunt: [ 'Gruntfile.js' ]
	},
	uglify: minify,
	htmllint: {
		all: grunt.file.expand( [ 'examples/**/*.html' ] )
	},
	qunit: {
		files: expandFiles( 'tests/unit/**/*.html' ),
		options: {
			page: {
				viewportSize: { width: 700, height: 500 }
			}
		}
	},
	jshint: {
		options: {
			jshintrc: true
		},
		all: [
			'splitter.js',
			'Gruntfile.js',
			'tests/unit/**/*.js'
		]
	},
	csslint: {
		base_theme: {
			src: cssFiles,
			options: {
				csslintrc: '.csslintrc'
			}
		}
	},

	esformatter: {
		options: {
			preset: 'jquery'
		},
		core: coreFiles,
		tests: 'tests/unit/**/*.js',
		grunt: 'Gruntfile.js'
	}
});

grunt.registerTask( 'default', [ 'lint', /* 'test', */ 'sizer' ]);
grunt.registerTask( 'lint', [ 'jshint', /* 'jscs', */ 'csslint' /*, 'htmllint' */ ]);
grunt.registerTask( 'test', [ 'qunit' ]);
grunt.registerTask( 'sizer', [ 'concat', 'uglify', 'compare_size' ]);

};
