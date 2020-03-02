'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

exports.isAbsolute = isAbsolute;
exports.isBuiltIn = isBuiltIn;
exports.isExternalModuleMain = isExternalModuleMain;
exports.isScopedMain = isScopedMain;
exports.default = resolveImportType;

var _cond = require('lodash/cond');

var _cond2 = _interopRequireDefault(_cond);

var _core = require('resolve/lib/core');

var _core2 = _interopRequireDefault(_core);

var _path = require('path');

var _resolve = require('eslint-module-utils/resolve');

var _resolve2 = _interopRequireDefault(_resolve);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function constant(value) {
  return () => value;
}

function baseModule(name) {
  if (isScoped(name)) {
    var _name$split = name.split('/'),
        _name$split2 = _slicedToArray(_name$split, 2);

    const scope = _name$split2[0],
          pkg = _name$split2[1];

    return `${scope}/${pkg}`;
  }

  var _name$split3 = name.split('/'),
      _name$split4 = _slicedToArray(_name$split3, 1);

  const pkg = _name$split4[0];

  return pkg;
}

function isAbsolute(name) {
  return name.indexOf('/') === 0;
}

function isBuiltIn(name, settings) {
  const base = baseModule(name);
  const extras = settings && settings['import/core-modules'] || [];
  return _core2.default[base] || extras.indexOf(base) > -1;
}

function isExternalPath(path, name, settings) {
  const folders = settings && settings['import/external-module-folders'] || ['node_modules'];
  return !path || folders.some(folder => -1 < path.indexOf((0, _path.join)(folder, name)));
}

const externalModuleRegExp = /^\w/;
function isExternalModule(name, settings, path) {
  return externalModuleRegExp.test(name) && isExternalPath(path, name, settings);
}

const externalModuleMainRegExp = /^[\w]((?!\/).)*$/;
function isExternalModuleMain(name, settings, path) {
  return externalModuleMainRegExp.test(name) && isExternalPath(path, name, settings);
}

const scopedRegExp = /^@[^/]+\/[^/]+/;
function isScoped(name) {
  return scopedRegExp.test(name);
}

const scopedMainRegExp = /^@[^/]+\/?[^/]+$/;
function isScopedMain(name) {
  return scopedMainRegExp.test(name);
}

function isInternalModule(name, settings, path) {
  return externalModuleRegExp.test(name) && !isExternalPath(path, name, settings);
}

function isRelativeToParent(name) {
  return name.indexOf('../') === 0;
}

const indexFiles = ['.', './', './index', './index.js'];
function isIndex(name) {
  return indexFiles.indexOf(name) !== -1;
}

function isRelativeToSibling(name) {
  return name.indexOf('./') === 0;
}

const typeTest = (0, _cond2.default)([[isAbsolute, constant('absolute')], [isBuiltIn, constant('builtin')], [isExternalModule, constant('external')], [isScoped, constant('external')], [isInternalModule, constant('internal')], [isRelativeToParent, constant('parent')], [isIndex, constant('index')], [isRelativeToSibling, constant('sibling')], [constant(true), constant('unknown')]]);

function resolveImportType(name, context) {
  return typeTest(name, context.settings, (0, _resolve2.default)(name, context));
}
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvcmUvaW1wb3J0VHlwZS5qcyJdLCJuYW1lcyI6WyJpc0Fic29sdXRlIiwiaXNCdWlsdEluIiwiaXNFeHRlcm5hbE1vZHVsZU1haW4iLCJpc1Njb3BlZE1haW4iLCJyZXNvbHZlSW1wb3J0VHlwZSIsImNvbnN0YW50IiwidmFsdWUiLCJiYXNlTW9kdWxlIiwibmFtZSIsImlzU2NvcGVkIiwic3BsaXQiLCJzY29wZSIsInBrZyIsImluZGV4T2YiLCJzZXR0aW5ncyIsImJhc2UiLCJleHRyYXMiLCJpc0V4dGVybmFsUGF0aCIsInBhdGgiLCJmb2xkZXJzIiwic29tZSIsImZvbGRlciIsImV4dGVybmFsTW9kdWxlUmVnRXhwIiwiaXNFeHRlcm5hbE1vZHVsZSIsInRlc3QiLCJleHRlcm5hbE1vZHVsZU1haW5SZWdFeHAiLCJzY29wZWRSZWdFeHAiLCJzY29wZWRNYWluUmVnRXhwIiwiaXNJbnRlcm5hbE1vZHVsZSIsImlzUmVsYXRpdmVUb1BhcmVudCIsImluZGV4RmlsZXMiLCJpc0luZGV4IiwiaXNSZWxhdGl2ZVRvU2libGluZyIsInR5cGVUZXN0IiwiY29udGV4dCJdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7UUFtQmdCQSxVLEdBQUFBLFU7UUFJQUMsUyxHQUFBQSxTO1FBaUJBQyxvQixHQUFBQSxvQjtRQVVBQyxZLEdBQUFBLFk7a0JBaUNRQyxpQjs7QUFuRnhCOzs7O0FBQ0E7Ozs7QUFDQTs7QUFFQTs7Ozs7O0FBRUEsU0FBU0MsUUFBVCxDQUFrQkMsS0FBbEIsRUFBeUI7QUFDdkIsU0FBTyxNQUFNQSxLQUFiO0FBQ0Q7O0FBRUQsU0FBU0MsVUFBVCxDQUFvQkMsSUFBcEIsRUFBMEI7QUFDeEIsTUFBSUMsU0FBU0QsSUFBVCxDQUFKLEVBQW9CO0FBQUEsc0JBQ0dBLEtBQUtFLEtBQUwsQ0FBVyxHQUFYLENBREg7QUFBQTs7QUFBQSxVQUNYQyxLQURXO0FBQUEsVUFDSkMsR0FESTs7QUFFbEIsV0FBUSxHQUFFRCxLQUFNLElBQUdDLEdBQUksRUFBdkI7QUFD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QUNKLFNBQUQsRUFBWUksU0FBUyxTQUFULENBQVosQ0FGb0IsRUFHcEIsQ0FBQ2tCLGdCQUFELEVBQW1CbEIsU0FBUyxVQUFULENBQW5CLENBSG9CLEVBSXBCLENBQUNJLFFBQUQsRUFBV0osU0FBUyxVQUFULENBQVgsQ0FKb0IsRUFLcEIsQ0FBQ3VCLGdCQUFELEVBQW1CdkIsU0FBUyxVQUFULENBQW5CLENBTG9CLEVBTXBCLENBQUN3QixrQkFBRCxFQUFxQnhCLFNBQVMsUUFBVCxDQUFyQixDQU5vQixFQU9wQixDQUFDMEIsT0FBRCxFQUFVMUIsU0FBUyxPQUFULENBQVYsQ0FQb0IsRUFRcEIsQ0FBQzJCLG1CQUFELEVBQXNCM0IsU0FBUyxTQUFULENBQXRCLENBUm9CLEVBU3BCLENBQUNBLFNBQVMsSUFBVCxDQUFELEVBQWlCQSxTQUFTLFNBQVQsQ0FBakIsQ0FUb0IsQ0FBTCxDQUFqQjs7QUFZZSxTQUFTRCxpQkFBVCxDQUEyQkksSUFBM0IsRUFBaUMwQixPQUFqQyxFQUEwQztBQUN2RCxTQUFPRCxTQUFTekIsSUFBVCxFQUFlMEIsUUFBUXBCLFFBQXZCLEVBQWlDLHVCQUFRTixJQUFSLEVBQWMwQixPQUFkLENBQWpDLENBQVA7QUFDRCIsImZpbGUiOiJjb3JlL2ltcG9ydFR5cGUuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgY29uZCBmcm9tICdsb2Rhc2gvY29uZCdcbmltcG9ydCBjb3JlTW9kdWxlcyBmcm9tICdyZXNvbHZlL2xpYi9jb3JlJ1xuaW1wb3J0IHsgam9pbiB9IGZyb20gJ3BhdGgnXG5cbmltcG9ydCByZXNvbHZlIGZyb20gJ2VzbGludC1tb2R1bGUtdXRpbHMvcmVzb2x2ZSdcblxuZnVuY3Rpb24gY29uc3RhbnQodmFsdWUpIHtcbiAgcmV0dXJuICgpID0+IHZhbHVlXG59XG5cbmZ1bmN0aW9uIGJhc2VNb2R1bGUobmFtZSkge1xuICBpZiAoaXNTY29wZWQobmFtZSkpIHtcbiAgICBjb25zdCBbc2NvcGUsIHBrZ10gPSBuYW1lLnNwbGl0KCcvJylcbiAgICByZXR1cm4gYCR7c2NvcGV9LyR7cGtnfWBcbiAgfVxuICBjb25zdCBbcGtnXSA9IG5hbWUuc3BsaXQoJy8nKVxuICByZXR1cm4gcGtnXG59XG5cbmV4cG9ydCBmdW5jdGlvbiBpc0Fic29sdXRlKG5hbWUpIHtcbiAgcmV0dXJuIG5hbWUuaW5kZXhPZignLycpID09PSAwXG59XG5cbmV4cG9ydCBmdW5jdGlvbiBpc0J1aWx0SW4obmFtZSwgc2V0dGluZ3MpIHtcbiAgY29uc3QgYmFzZSA9IGJhc2VNb2R1bGUobmFtZSlcbiAgY29uc3QgZXh0cmFzID0gKHNldHRpbmdzICYmIHNldHRpbmdzWydpbXBvcnQvY29yZS1tb2R1bGVzJ10pIHx8IFtdXG4gIHJldHVybiBjb3JlTW9kdWxlc1tiYXNlXSB8fCBleHRyYXMuaW5kZXhPZihiYXNlKSA+IC0xXG59XG5cbmZ1bmN0aW9uIGlzRXh0ZXJuYWxQYXRoKHBhdGgsIG5hbWUsIHNldHRpbmdzKSB7XG4gIGNvbnN0IGZvbGRlcnMgPSAoc2V0dGluZ3MgJiYgc2V0dGluZ3NbJ2ltcG9ydC9leHRlcm5hbC1tb2R1bGUtZm9sZGVycyddKSB8fCBbJ25vZGVfbW9kdWxlcyddXG4gIHJldHVybiAhcGF0aCB8fCBmb2xkZXJzLnNvbWUoZm9sZGVyID0+IC0xIDwgcGF0aC5pbmRleE9mKGpvaW4oZm9sZGVyLCBuYW1lKSkpXG59XG5cbmNvbnN0IGV4dGVybmFsTW9kdWxlUmVnRXhwID0gL15cXHcvXG5mdW5jdGlvbiBpc0V4dGVybmFsTW9kdWxlKG5hbWUsIHNldHRpbmdzLCBwYXRoKSB7XG4gIHJldHVybiBleHRlcm5hbE1vZHVsZVJlZ0V4cC50ZXN0KG5hbWUpICYmIGlzRXh0ZXJuYWxQYXRoKHBhdGgsIG5hbWUsIHNldHRpbmdzKVxufVxuXG5jb25zdCBleHRlcm5hbE1vZHVsZU1haW5SZWdFeHAgPSAvXltcXHddKCg/IVxcLykuKSokL1xuZXhwb3J0IGZ1bmN0aW9uIGlzRXh0ZXJuYWxNb2R1bGVNYWluKG5hbWUsIHNldHRpbmdzLCBwYXRoKSB7XG4gIHJldHVybiBleHRlcm5hbE1vZHVsZU1haW5SZWdFeHAudGVzdChuYW1lKSAmJiBpc0V4dGVybmFsUGF0aChwYXRoLCBuYW1lLCBzZXR0aW5ncylcbn1cblxuY29uc3Qgc2NvcGVkUmVnRXhwID0gL15AW14vXStcXC9bXi9dKy9cbmZ1bmN0aW9uIGlzU2NvcGVkKG5hbWUpIHtcbiAgcmV0dXJuIHNjb3BlZFJlZ0V4cC50ZXN0KG5hbWUpXG59XG5cbmNvbnN0IHNjb3BlZE1haW5SZWdFeHAgPSAvXkBbXi9dK1xcLz9bXi9dKyQvXG5leHBvcnQgZnVuY3Rpb24gaXNTY29wZWRNYWluKG5hbWUpIHtcbiAgcmV0dXJuIHNjb3BlZE1haW5SZWdFeHAudGVzdChuYW1lKVxufVxuXG5mdW5jdGlvbiBpc0ludGVybmFsTW9kdWxlKG5hbWUsIHNldHRpbmdzLCBwYXRoKSB7XG4gIHJldHVybiBleHRlcm5hbE1vZHVsZVJlZ0V4cC50ZXN0KG5hbWUpICYmICFpc0V4dGVybmFsUGF0aChwYXRoLCBuYW1lLCBzZXR0aW5ncylcbn1cblxuZnVuY3Rpb24gaXNSZWxhdGl2ZVRvUGFyZW50KG5hbWUpIHtcbiAgcmV0dXJuIG5hbWUuaW5kZXhPZignLi4vJykgPT09IDBcbn1cblxuY29uc3QgaW5kZXhGaWxlcyA9IFsnLicsICcuLycsICcuL2luZGV4JywgJy4vaW5kZXguanMnXVxuZnVuY3Rpb24gaXNJbmRleChuYW1lKSB7XG4gIHJldHVybiBpbmRleEZpbGVzLmluZGV4T2YobmFtZSkgIT09IC0xXG59XG5cbmZ1bmN0aW9uIGlzUmVsYXRpdmVUb1NpYmxpbmcobmFtZSkge1xuICByZXR1cm4gbmFtZS5pbmRleE9mKCcuLycpID09PSAwXG59XG5cbmNvbnN0IHR5cGVUZXN0ID0gY29uZChbXG4gIFtpc0Fic29sdXRlLCBjb25zdGFudCgnYWJzb2x1dGUnKV0sXG4gIFtpc0J1aWx0SW4sIGNvbnN0YW50KCdidWlsdGluJyldLFxuICBbaXNFeHRlcm5hbE1vZHVsZSwgY29uc3RhbnQoJ2V4dGVybmFsJyldLFxuICBbaXNTY29wZWQsIGNvbnN0YW50KCdleHRlcm5hbCcpXSxcbiAgW2lzSW50ZXJuYWxNb2R1bGUsIGNvbnN0YW50KCdpbnRlcm5hbCcpXSxcbiAgW2lzUmVsYXRpdmVUb1BhcmVudCwgY29uc3RhbnQoJ3BhcmVudCcpXSxcbiAgW2lzSW5kZXgsIGNvbnN0YW50KCdpbmRleCcpXSxcbiAgW2lzUmVsYXRpdmVUb1NpYmxpbmcsIGNvbnN0YW50KCdzaWJsaW5nJyldLFxuICBbY29uc3RhbnQodHJ1ZSksIGNvbnN0YW50KCd1bmtub3duJyldLFxuXSlcblxuZXhwb3J0IGRlZmF1bHQgZnVuY3Rpb24gcmVzb2x2ZUltcG9ydFR5cGUobmFtZSwgY29udGV4dCkge1xuICByZXR1cm4gdHlwZVRlc3QobmFtZSwgY29udGV4dC5zZXR0aW5ncywgcmVzb2x2ZShuYW1lLCBjb250ZXh0KSlcbn1cbiJdfQ==