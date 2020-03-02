'use strict';

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }(); /**
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          * @fileOverview Ensures that no imported module imports the linted module.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          * @author Ben Mosher
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          */

var _ExportMap = require('../ExportMap');

var _ExportMap2 = _interopRequireDefault(_ExportMap);

var _moduleVisitor = require('eslint-module-utils/moduleVisitor');

var _moduleVisitor2 = _interopRequireDefault(_moduleVisitor);

var _docsUrl = require('../docsUrl');

var _docsUrl2 = _interopRequireDefault(_docsUrl);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

// todo: cache cycles / deep relationships for faster repeat evaluation
module.exports = {
  meta: {
    docs: { url: (0, _docsUrl2.default)('no-cycle') },
    schema: [(0, _moduleVisitor.makeOptionsSchema)({
      maxDepth: {
        description: 'maximum dependency depth to traverse',
        type: 'integer',
        minimum: 1
      }
    })]
  },

  create: function (context) {
    const myPath = context.getFilename();
    if (myPath === '<text>') return {}; // can't cycle-check a non-file

    const options = context.options[0] || {};
    const maxDepth = options.maxDepth || Infinity;

    function checkSourceValue(sourceNode, importer) {
      const imported = _ExportMap2.default.get(sourceNode.value, context);

      if (imported == null) {
        return; // no-unresolved territory
      }

      if (imported.path === myPath) {
        return; // no-self-import territory
      }

      const untraversed = [{ mget: () => imported, route: [] }];
      const traversed = new Set();
      function detectCycle(_ref) {
        let mget = _ref.mget,
            route = _ref.route;

        const m = mget();
        if (m == null) return;
        if (traversed.has(m.path)) return;
        traversed.add(m.path);

        for (let _ref2 of m.imports) {
          var _ref3 = _slicedToArray(_ref2, 2);

          let path = _ref3[0];
          var _ref3$ = _ref3[1];
          let getter = _ref3$.getter;
          let source = _ref3$.source;

          if (path === myPath) return true;
          if (traversed.has(path)) continue;
          if (route.length + 1 < maxDepth) {
            untraversed.push({
              mget: getter,
              route: route.concat(source)
            });
          }
        }
      }

      while (untraversed.length > 0) {
        const next = untraversed.shift(); // bfs!
        if (detectCycle(next)) {
          const message = next.route.length > 0 ? `Dependency cycle via ${routeString(next.route)}` : 'Dependency cycle detected.';
          context.report(importer, message);
          return;
        }
      }
    }

    return (0, _moduleVisitor2.default)(checkSourceValue, context.options[0]);
  }
};

function routeString(route) {
  return route.map(s => `${s.value}:${s.loc.start.line}`).join('=>');
}
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJ1bGVzL25vLWN5Y2xlLmpzIl0sIm5hbWVzIjpbIm1vZHVsZSIsImV4cG9ydHMiLCJtZXRhIiwiZG9jcyIsInVybCIsInNjaGVtYSIsIm1heERlcHRoIiwiZGVzY3JpcHRpb24iLCJ0eXBlIiwibWluaW11bSIsImNyZWF0ZSIsImNvbnRleHQiLCJteVBhdGgiLCJnZXRGaWxlbmFtZSIsIm9wdGlvbnMiLCJJbmZpbml0eSIsImNoZWNrU291cmNlVmFsdWUiLCJzb3VyY2VOb2RlIiwiaW1wb3J0ZXIiLCJpbXBvcnRlZCIsImdldCIsInZhbHVlIiwicGF0aCIsInVudHJhdmVyc2VkIiwibWdldCIsInJvdXRlIiwidHJhdmVyc2VkIiwiU2V0IiwiZGV0ZWN0Q3ljbGUiLCJtIiwiaGFzIiwiYWRkIiwiaW1wb3J0cyIsImdldHRlciIsInNvdXJjZSIsImxlbmd0aCIsInB1c2giLCJjb25jYXQiLCJuZXh0Iiwic2hpZnQiLCJtZXNzYWdlIiwicm91dGVTdHJpbmciLCJyZXBvcnQiLCJtYXAiLCJzIiwibG9jIiwic3RhcnQiLCJsaW5lIiwiam9pbiJdLCJtYXBwaW5ncyI6Ijs7eXBCQUFBOzs7OztBQUtBOzs7O0FBQ0E7Ozs7QUFDQTs7Ozs7O0FBRUE7QUFDQUEsT0FBT0MsT0FBUCxHQUFpQjtBQUNmQyxRQUFNO0FBQ0pDLFVBQU0sRUFBRUMsS0FBSyx1QkFBUSxVQUFSLENBQVAsRUFERjtBQUVKQyxZQUFRLENBQUMsc0NBQWtCO0FBQ3pCQyxnQkFBUztBQUNQQyxxQkFBYSxzQ0FETjtBQUVQQyxjQUFNLFNBRkM7QUFHUEMsaUJBQVM7QUFIRjtBQURnQixLQUFsQixDQUFEO0FBRkosR0FEUzs7QUFZZkMsVUFBUSxVQUFVQyxPQUFWLEVBQW1CO0FBQ3pCLFVBQU1DLFNBQVNELFFBQVFFLFdBQVIsRUFBZjtBQUNBLFFBQUlELFdBQVcsUUFBZixFQUF5QixPQUFPLEVBQVAsQ0FGQSxDQUVVOztBQUVuQyxVQUFNRSxVQUFVSCxRQUFRRyxPQUFSLENBQWdCLENBQWhCLEtBQXNCLEVBQXRDO0FBQ0EsVUFBTVIsV0FBV1EsUUFBUVIsUUFBUixJQUFvQlMsUUFBckM7O0FBRUEsYUFBU0MsZ0JBQVQsQ0FBMEJDLFVBQTFCLEVBQXNDQyxRQUF0QyxFQUFnRDtBQUM5QyxZQUFNQyxXQUFXLG9CQUFRQyxHQUFSLENBQVlILFdBQVdJLEtBQXZCLEVBQThCVixPQUE5QixDQUFqQjs7QUFFQSxVQUFJUSxZQUFZLElBQWhCLEVBQXNCO0FBQ3BCLGVBRG9CLENBQ1o7QUFDVDs7QUFFRCxVQUFJQSxTQUFTRyxJQUFULEtBQWtCVixNQUF0QixFQUE4QjtBQUM1QixlQUQ0QixDQUNwQjtBQUNUOztBQUVELFlBQU1XLGNBQWMsQ0FBQyxFQUFDQyxNQUFNLE1BQU1MLFFBQWIsRUFBdUJNLE9BQU0sRUFBN0IsRUFBRCxDQUFwQjtBQUNBLFlBQU1DLFlBQVksSUFBSUMsR0FBSixFQUFsQjtBQUNBLGVBQVNDLFdBQVQsT0FBb0M7QUFBQSxZQUFkSixJQUFjLFFBQWRBLElBQWM7QUFBQSxZQUFSQyxLQUFRLFFBQVJBLEtBQVE7O0FBQ2xDLGNBQU1JLElBQUlMLE1BQVY7QUFDQSxZQUFJSyxLQUFLLElBQVQsRUFBZTtBQUNmLFlBQUlILFVBQVVJLEdBQVYsQ0FBY0QsRUFBRVAsSUFBaEIsQ0FBSixFQUEyQjtBQUMzQkksa0JBQVVLLEdBQVYsQ0FBY0YsRUFBRVAsSUFBaEI7O0FBRUEsMEJBQXVDTyxFQUFFRyxPQUF6QyxFQUFrRDtBQUFBOztBQUFBLGNBQXhDVixJQUF3QztBQUFBO0FBQUEsY0FBaENXLE1BQWdDLFVBQWhDQSxNQUFnQztBQUFBLGNBQXhCQyxNQUF3QixVQUF4QkEsTUFBd0I7O0FBQ2hELGNBQUlaLFNBQVNWLE1BQWIsRUFBcUIsT0FBTyxJQUFQO0FBQ3JCLGNBQUljLFVBQVVJLEdBQVYsQ0FBY1IsSUFBZCxDQUFKLEVBQXlCO0FBQ3pCLGNBQUlHLE1BQU1VLE1BQU4sR0FBZSxDQUFmLEdBQW1CN0IsUUFBdkIsRUFBaUM7QUFDL0JpQix3QkFBWWEsSUFBWixDQUFpQjtBQUNmWixvQkFBTVMsTUFEUztBQUVmUixxQkFBT0EsTUFBTVksTUFBTixDQUFhSCxNQUFiO0FBRlEsYUFBakI7QUFJRDtBQUNGO0FBQ0Y7O0FBRUQsYUFBT1gsWUFBWVksTUFBWixHQUFxQixDQUE1QixFQUErQjtBQUM3QixjQUFNRyxPQUFPZixZQUFZZ0IsS0FBWixFQUFiLENBRDZCLENBQ0k7QUFDakMsWUFBSVgsWUFBWVUsSUFBWixDQUFKLEVBQXVCO0FBQ3JCLGdCQUFNRSxVQUFXRixLQUFLYixLQUFMLENBQVdVLE1BQVgsR0FBb0IsQ0FBcEIsR0FDWix3QkFBdUJNLFlBQVlILEtBQUtiLEtBQWpCLENBQXdCLEVBRG5DLEdBRWIsNEJBRko7QUFHQWQsa0JBQVErQixNQUFSLENBQWV4QixRQUFmLEVBQXlCc0IsT0FBekI7QUFDQTtBQUNEO0FBQ0Y7QUFDRjs7QUFFRCxXQUFPLDZCQUFjeEIsZ0JBQWQsRUFBZ0NMLFFBQVFHLE9BQVIsQ0FBZ0IsQ0FBaEIsQ0FBaEMsQ0FBUDtBQUNEO0FBL0RjLENBQWpCOztBQWtFQSxTQUFTMkIsV0FBVCxDQUFxQmhCLEtBQXJCLEVBQTRCO0FBQzFCLFNBQU9BLE1BQU1rQixHQUFOLENBQVVDLEtBQU0sR0FBRUEsRUFBRXZCLEtBQU0sSUFBR3VCLEVBQUVDLEdBQUYsQ0FBTUMsS0FBTixDQUFZQyxJQUFLLEVBQTlDLEVBQWlEQyxJQUFqRCxDQUFzRCxJQUF0RCxDQUFQO0FBQ0QiLCJmaWxlIjoicnVsZXMvbm8tY3ljbGUuanMiLCJzb3VyY2VzQ29udGVudCI6WyIvKipcbiAqIEBmaWxlT3ZlcnZpZXcgRW5zdXJlcyB0aGF0IG5vIGltcG9ydGVkIG1vZHVsZSBpbXBvcnRzIHRoZSBsaW50ZWQgbW9kdWxlLlxuICogQGF1dGhvciBCZW4gTW9zaGVyXG4gKi9cblxuaW1wb3J0IEV4cG9ydHMgZnJvbSAnLi4vRXhwb3J0TWFwJ1xuaW1wb3J0IG1vZHVsZVZpc2l0b3IsIHsgbWFrZU9wdGlvbnNTY2hlbWEgfSBmcm9tICdlc2xpbnQtbW9kdWxlLXV0aWxzL21vZHVsZVZpc2l0b3InXG5pbXBvcnQgZG9jc1VybCBmcm9tICcuLi9kb2NzVXJsJ1xuXG4vLyB0b2RvOiBjYWNoZSBjeWNsZXMgLyBkZWVwIHJlbGF0aW9uc2hpcHMgZm9yIGZhc3RlciByZXBlYXQgZXZhbHVhdGlvblxubW9kdWxlLmV4cG9ydHMgPSB7XG4gIG1ldGE6IHtcbiAgICBkb2NzOiB7IHVybDogZG9jc1VybCgnbm8tY3ljbGUnKSB9LFxuICAgIHNjaGVtYTogW21ha2VPcHRpb25zU2NoZW1hKHtcbiAgICAgIG1heERlcHRoOntcbiAgICAgICAgZGVzY3JpcHRpb246ICdtYXhpbXVtIGRlcGVuZGVuY3kgZGVwdGggdG8gdHJhdmVyc2UnLFxuICAgICAgICB0eXBlOiAnaW50ZWdlcicsXG4gICAgICAgIG1pbmltdW06IDEsXG4gICAgICB9LFxuICAgIH0pXSxcbiAgfSxcblxuICBjcmVhdGU6IGZ1bmN0aW9uIChjb250ZXh0KSB7XG4gICAgY29uc3QgbXlQYXRoID0gY29udGV4dC5nZXRGaWxlbmFtZSgpXG4gICAgaWYgKG15UGF0aCA9PT0gJzx0ZXh0PicpIHJldHVybiB7fSAvLyBjYW4ndCBjeWNsZS1jaGVjayBhIG5vbi1maWxlXG5cbiAgICBjb25zdCBvcHRpb25zID0gY29udGV4dC5vcHRpb25zWzBdIHx8IHt9XG4gICAgY29uc3QgbWF4RGVwdGggPSBvcHRpb25zLm1heERlcHRoIHx8IEluZmluaXR5XG5cbiAgICBmdW5jdGlvbiBjaGVja1NvdXJjZVZhbHVlKHNvdXJjZU5vZGUsIGltcG9ydGVyKSB7XG4gICAgICBjb25zdCBpbXBvcnRlZCA9IEV4cG9ydHMuZ2V0KHNvdXJjZU5vZGUudmFsdWUsIGNvbnRleHQpXG5cbiAgICAgIGlmIChpbXBvcnRlZCA9PSBudWxsKSB7XG4gICAgICAgIHJldHVybiAgLy8gbm8tdW5yZXNvbHZlZCB0ZXJyaXRvcnlcbiAgICAgIH1cblxuICAgICAgaWYgKGltcG9ydGVkLnBhdGggPT09IG15UGF0aCkge1xuICAgICAgICByZXR1cm4gIC8vIG5vLXNlbGYtaW1wb3J0IHRlcnJpdG9yeVxuICAgICAgfVxuXG4gICAgICBjb25zdCB1bnRyYXZlcnNlZCA9IFt7bWdldDogKCkgPT4gaW1wb3J0ZWQsIHJvdXRlOltdfV1cbiAgICAgIGNvbnN0IHRyYXZlcnNlZCA9IG5ldyBTZXQoKVxuICAgICAgZnVuY3Rpb24gZGV0ZWN0Q3ljbGUoe21nZXQsIHJvdXRlfSkge1xuICAgICAgICBjb25zdCBtID0gbWdldCgpXG4gICAgICAgIGlmIChtID09IG51bGwpIHJldHVyblxuICAgICAgICBpZiAodHJhdmVyc2VkLmhhcyhtLnBhdGgpKSByZXR1cm5cbiAgICAgICAgdHJhdmVyc2VkLmFkZChtLnBhdGgpXG5cbiAgICAgICAgZm9yIChsZXQgW3BhdGgsIHsgZ2V0dGVyLCBzb3VyY2UgfV0gb2YgbS5pbXBvcnRzKSB7XG4gICAgICAgICAgaWYgKHBhdGggPT09IG15UGF0aCkgcmV0dXJuIHRydWVcbiAgICAgICAgICBpZiAodHJhdmVyc2VkLmhhcyhwYXRoKSkgY29udGludWVcbiAgICAgICAgICBpZiAocm91dGUubGVuZ3RoICsgMSA8IG1heERlcHRoKSB7XG4gICAgICAgICAgICB1bnRyYXZlcnNlZC5wdXNoKHtcbiAgICAgICAgICAgICAgbWdldDogZ2V0dGVyLFxuICAgICAgICAgICAgICByb3V0ZTogcm91dGUuY29uY2F0KHNvdXJjZSksXG4gICAgICAgICAgICB9KVxuICAgICAgICAgIH1cbiAgICAgICAgfVxuICAgICAgfVxuXG4gICAgICB3aGlsZSAodW50cmF2ZXJzZWQubGVuZ3RoID4gMCkge1xuICAgICAgICBjb25zdCBuZXh0ID0gdW50cmF2ZXJzZWQuc2hpZnQoKSAvLyBiZnMhXG4gICAgICAgIGlmIChkZXRlY3RDeWNsZShuZXh0KSkge1xuICAgICAgICAgIGNvbnN0IG1lc3NhZ2UgPSAobmV4dC5yb3V0ZS5sZW5ndGggPiAwXG4gICAgICAgICAgICA/IGBEZXBlbmRlbmN5IGN5Y2xlIHZpYSAke3JvdXRlU3RyaW5nKG5leHQucm91dGUpfWBcbiAgICAgICAgICAgIDogJ0RlcGVuZGVuY3kgY3ljbGUgZGV0ZWN0ZWQuJylcbiAgICAgICAgICBjb250ZXh0LnJlcG9ydChpbXBvcnRlciwgbWVzc2FnZSlcbiAgICAgICAgICByZXR1cm5cbiAgICAgICAgfVxuICAgICAgfVxuICAgIH1cblxuICAgIHJldHVybiBtb2R1bGVWaXNpdG9yKGNoZWNrU291cmNlVmFsdWUsIGNvbnRleHQub3B0aW9uc1swXSlcbiAgfSxcbn1cblxuZnVuY3Rpb24gcm91dGVTdHJpbmcocm91dGUpIHtcbiAgcmV0dXJuIHJvdXRlLm1hcChzID0+IGAke3MudmFsdWV9OiR7cy5sb2Muc3RhcnQubGluZX1gKS5qb2luKCc9PicpXG59XG4iXX0=