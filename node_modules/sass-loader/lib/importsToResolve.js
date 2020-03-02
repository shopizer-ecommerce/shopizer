"use strict";

const path = require("path");

// libsass uses this precedence when importing files without extension
const extPrecedence = [".scss", ".sass", ".css"];

/**
 * When libsass tries to resolve an import, it uses a special algorithm.
 * Since the sass-loader uses webpack to resolve the modules, we need to simulate that algorithm. This function
 * returns an array of import paths to try.
 *
 * @param {string} request
 * @returns {Array<string>}
 */
function importsToResolve(request) {
    // libsass' import algorithm works like this:
    // In case there is no file extension...
    //   - Prefer modules starting with '_'.
    //   - File extension precedence: .scss, .sass, .css.
    // In case there is a file extension...
    //   - If the file is a CSS-file, do not include it all, but just link it via @import url().
    //   - The exact file name must match (no auto-resolving of '_'-modules).

    // Keep in mind: ext can also be something like '.datepicker' when the true extension is omitted and the filename contains a dot.
    // @see https://github.com/webpack-contrib/sass-loader/issues/167
    const ext = path.extname(request);
    const basename = path.basename(request);
    const dirname = path.dirname(request);
    const startsWithUnderscore = basename.charAt(0) === "_";
    const hasCssExt = ext === ".css";
    const hasSassExt = ext === ".scss" || ext === ".sass";

    // a module import is an identifier like 'bootstrap-sass'
    // We also need to check for dirname since it might also be a deep import like 'bootstrap-sass/something'
    let isModuleImport = request.charAt(0) !== "." && dirname === ".";

    if (dirname.charAt(0) === "@") {
        // Check whether it is a deep import from scoped npm package
        // (i.e. @pkg/foo/file), if so, process import as file import;
        // otherwise, if we import from root npm scoped package (i.e. @pkg/foo)
        // process import as a module import.
        isModuleImport = !(dirname.indexOf("/") > -1);
    }

    return (isModuleImport && [request]) || // Do not modify module imports
        (hasCssExt && []) || // Do not import css files
        (hasSassExt && [request]) || // Do not modify imports with explicit extensions
        (startsWithUnderscore ? [] : extPrecedence) // Do not add underscore imports if there is already an underscore
            .map(ext => "_" + basename + ext)
            .concat(
                extPrecedence.map(ext => basename + ext)
            ).map(
                file => dirname + "/" + file // No path.sep required here, because imports inside SASS are usually with /
            );
}

module.exports = importsToResolve;
