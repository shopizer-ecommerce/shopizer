# Developing vue-fontawesome

## Tasks

The following commands are available through `npm run` or `yarn`:

Command     | Purpose
---         | ---
build       | Build a development version of the library using Rollup
dist        | Build a production version of the library using Rollup
test        | Execute unit tests

## Release this project
<a name="release"></a>

1. Update `package.json` and change `version`
1. Update `README.md` and add any contributors
1. Update the `CHANGELOG.md`
1. `npm run dist`
1. `git commit -a -m 'Release VERSION'`
1. `git push`
1. Create a [new release](https://github.com/FortAwesome/vue-fontawesome/releases/new) with CHANGELOG details
