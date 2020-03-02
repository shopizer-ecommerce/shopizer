'use strict';

var _declaredScope = require('eslint-module-utils/declaredScope');

var _declaredScope2 = _interopRequireDefault(_declaredScope);

var _ExportMap = require('../ExportMap');

var _ExportMap2 = _interopRequireDefault(_ExportMap);

var _importDeclaration = require('../importDeclaration');

var _importDeclaration2 = _interopRequireDefault(_importDeclaration);

var _docsUrl = require('../docsUrl');

var _docsUrl2 = _interopRequireDefault(_docsUrl);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

module.exports = {
  meta: {
    docs: {
      url: (0, _docsUrl2.default)('namespace')
    },

    schema: [{
      'type': 'object',
      'properties': {
        'allowComputed': {
          'description': 'If `false`, will report computed (and thus, un-lintable) references ' + 'to namespace members.',
          'type': 'boolean',
          'default': false
        }
      },
      'additionalProperties': false
    }]
  },

  create: function namespaceRule(context) {

    // read options
    var _ref = context.options[0] || {},
        _ref$allowComputed = _ref.allowComputed;

    const allowComputed = _ref$allowComputed === undefined ? false : _ref$allowComputed;


    const namespaces = new Map();

    function makeMessage(last, namepath) {
      return `'${last.name}' not found in` + (namepath.length > 1 ? ' deeply ' : ' ') + `imported namespace '${namepath.join('.')}'.`;
    }

    return {

      // pick up all imports at body entry time, to properly respect hoisting
      'Program': function (_ref2) {
        let body = _ref2.body;

        function processBodyStatement(declaration) {
          if (declaration.type !== 'ImportDeclaration') return;

          if (declaration.specifiers.length === 0) return;

          const imports = _ExportMap2.default.get(declaration.source.value, context);
          if (imports == null) return null;

          if (imports.errors.length) {
            imports.reportErrors(context, declaration);
            return;
          }

          for (let specifier of declaration.specifiers) {
            switch (specifier.type) {
              case 'ImportNamespaceSpecifier':
                if (!imports.size) {
                  context.report(specifier, `No exported names found in module '${declaration.source.value}'.`);
                }
                namespaces.set(specifier.local.name, imports);
                break;
              case 'ImportDefaultSpecifier':
              case 'ImportSpecifier':
                {
                  const meta = imports.get(
                  // default to 'default' for default http://i.imgur.com/nj6qAWy.jpg
                  specifier.imported ? specifier.imported.name : 'default');
                  if (!meta || !meta.namespace) break;
                  namespaces.set(specifier.local.name, meta.namespace);
                  break;
                }
            }
          }
        }
        body.forEach(processBodyStatement);
      },

      // same as above, but does not add names to local map
      'ExportNamespaceSpecifier': function (namespace) {
        var declaration = (0, _importDeclaration2.default)(context);

        var imports = _ExportMap2.default.get(declaration.source.value, context);
        if (imports == null) return null;

        if (imports.errors.length) {
          imports.reportErrors(context, declaration);
          return;
        }

        if (!imports.size) {
          context.report(namespace, `No exported names found in module '${declaration.source.value}'.`);
        }
      },

      // todo: check for possible redefinition

      'MemberExpression': function (dereference) {
        if (dereference.object.type !== 'Identifier') return;
        if (!namespaces.has(dereference.object.name)) return;

        if (dereference.parent.type === 'AssignmentExpression' && dereference.parent.left === dereference) {
          context.report(dereference.parent, `Assignment to member of namespace '${dereference.object.name}'.`);
        }

        // go deep
        var namespace = namespaces.get(dereference.object.name);
        var namepath = [dereference.object.name];
        // while property is namespace and parent is member expression, keep validating
        while (namespace instanceof _ExportMap2.default && dereference.type === 'MemberExpression') {

          if (dereference.computed) {
            if (!allowComputed) {
              context.report(dereference.property, 'Unable to validate computed reference to imported namespace \'' + dereference.object.name + '\'.');
            }
            return;
          }

          if (!namespace.has(dereference.property.name)) {
            context.report(dereference.property, makeMessage(dereference.property, namepath));
            break;
          }

          const exported = namespace.get(dereference.property.name);
          if (exported == null) return;

          // stash and pop
          namepath.push(dereference.property.name);
          namespace = exported.namespace;
          dereference = dereference.parent;
        }
      },

      'VariableDeclarator': function (_ref3) {
        let id = _ref3.id,
            init = _ref3.init;

        if (init == null) return;
        if (init.type !== 'Identifier') return;
        if (!namespaces.has(init.name)) return;

        // check for redefinition in intermediate scopes
        if ((0, _declaredScope2.default)(context, init.name) !== 'module') return;

        // DFS traverse child namespaces
        function testKey(pattern, namespace) {
          let path = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : [init.name];

          if (!(namespace instanceof _ExportMap2.default)) return;

          if (pattern.type !== 'ObjectPattern') return;

          for (let property of pattern.properties) {
            if (property.type === 'ExperimentalRestProperty') {
              continue;
            }

            if (property.key.type !== 'Identifier') {
              context.report({
                node: property,
                message: 'Only destructure top-level names.'
              });
              continue;
            }

            if (!namespace.has(property.key.name)) {
              context.report({
                node: property,
                message: makeMessage(property.key, path)
              });
              continue;
            }

            path.push(property.key.name);
            testKey(property.value, namespace.get(property.key.name).namespace, path);
            path.pop();
          }
        }

        testKey(id, namespaces.get(init.name));
      }
    };
  }
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJ1bGVzL25hbWVzcGFjZS5qcyJdLCJuYW1lcyI6WyJtb2R1bGUiLCJleHBvcnRzIiwibWV0YSIsImRvY3MiLCJ1cmwiLCJzY2hlbWEiLCJjcmVhdGUiLCJuYW1lc3BhY2VSdWxlIiwiY29udGV4dCIsIm9wdGlvbnMiLCJhbGxvd0NvbXB1dGVkIiwibmFtZXNwYWNlcyIsIk1hcCIsIm1ha2VNZXNzYWdlIiwibGFzdCIsIm5hbWVwYXRoIiwibmFtZSIsImxlbmd0aCIsImpvaW4iLCJib2R5IiwicHJvY2Vzc0JvZHlTdGF0ZW1lbnQiLCJkZWNsYXJhdGlvbiIsInR5cGUiLCJzcGVjaWZpZXJzIiwiaW1wb3J0cyIsImdldCIsInNvdXJjZSIsInZhbHVlIiwiZXJyb3JzIiwicmVwb3J0RXJyb3JzIiwic3BlY2lmaWVyIiwic2l6ZSIsInJlcG9ydCIsInNldCIsImxvY2FsIiwiaW1wb3J0ZWQiLCJuYW1lc3BhY2UiLCJmb3JFYWNoIiwiZGVyZWZlcmVuY2UiLCJvYmplY3QiLCJoYXMiLCJwYXJlbnQiLCJsZWZ0IiwiY29tcHV0ZWQiLCJwcm9wZXJ0eSIsImV4cG9ydGVkIiwicHVzaCIsImlkIiwiaW5pdCIsInRlc3RLZXkiLCJwYXR0ZXJuIiwicGF0aCIsInByb3BlcnRpZXMiLCJrZXkiLCJub2RlIiwibWVzc2FnZSIsInBvcCJdLCJtYXBwaW5ncyI6Ijs7QUFBQTs7OztBQUNBOzs7O0FBQ0E7Ozs7QUFDQTs7Ozs7O0FBRUFBLE9BQU9DLE9BQVAsR0FBaUI7QUFDZkMsUUFBTTtBQUNKQyxVQUFNO0FBQ0pDLFdBQUssdUJBQVEsV0FBUjtBQURELEtBREY7O0FBS0pDLFlBQVEsQ0FDTjtBQUNFLGNBQVEsUUFEVjtBQUVFLG9CQUFjO0FBQ1oseUJBQWlCO0FBQ2YseUJBQ0UseUVBQ0EsdUJBSGE7QUFJZixrQkFBUSxTQUpPO0FBS2YscUJBQVc7QUFMSTtBQURMLE9BRmhCO0FBV0UsOEJBQXdCO0FBWDFCLEtBRE07QUFMSixHQURTOztBQXVCZkMsVUFBUSxTQUFTQyxh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UyxHQUFULENBQWFyQyxJQUEzQixDQUFMLEVBQXVDO0FBQ3JDUixzQkFBUXdCLE1BQVIsQ0FBZTtBQUNic0Isc0JBQU1WLFFBRE87QUFFYlcseUJBQVMxQyxZQUFZK0IsU0FBU1MsR0FBckIsRUFBMEJGLElBQTFCO0FBRkksZUFBZjtBQUlBO0FBQ0Q7O0FBRURBLGlCQUFLTCxJQUFMLENBQVVGLFNBQVNTLEdBQVQsQ0FBYXJDLElBQXZCO0FBQ0FpQyxvQkFBUUwsU0FBU2pCLEtBQWpCLEVBQXdCUyxVQUFVWCxHQUFWLENBQWNtQixTQUFTUyxHQUFULENBQWFyQyxJQUEzQixFQUFpQ29CLFNBQXpELEVBQW9FZSxJQUFwRTtBQUNBQSxpQkFBS0ssR0FBTDtBQUNEO0FBQ0Y7O0FBRURQLGdCQUFRRixFQUFSLEVBQVlwQyxXQUFXYyxHQUFYLENBQWV1QixLQUFLaEMsSUFBcEIsQ0FBWjtBQUNEO0FBbkpJLEtBQVA7QUFxSkQ7QUEzTGMsQ0FBakIiLCJmaWxlIjoicnVsZXMvbmFtZXNwYWNlLmpzIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IGRlY2xhcmVkU2NvcGUgZnJvbSAnZXNsaW50LW1vZHVsZS11dGlscy9kZWNsYXJlZFNjb3BlJ1xuaW1wb3J0IEV4cG9ydHMgZnJvbSAnLi4vRXhwb3J0TWFwJ1xuaW1wb3J0IGltcG9ydERlY2xhcmF0aW9uIGZyb20gJy4uL2ltcG9ydERlY2xhcmF0aW9uJ1xuaW1wb3J0IGRvY3NVcmwgZnJvbSAnLi4vZG9jc1VybCdcblxubW9kdWxlLmV4cG9ydHMgPSB7XG4gIG1ldGE6IHtcbiAgICBkb2NzOiB7XG4gICAgICB1cmw6IGRvY3NVcmwoJ25hbWVzcGFjZScpLFxuICAgIH0sXG5cbiAgICBzY2hlbWE6IFtcbiAgICAgIHtcbiAgICAgICAgJ3R5cGUnOiAnb2JqZWN0JyxcbiAgICAgICAgJ3Byb3BlcnRpZXMnOiB7XG4gICAgICAgICAgJ2FsbG93Q29tcHV0ZWQnOiB7XG4gICAgICAgICAgICAnZGVzY3JpcHRpb24nOlxuICAgICAgICAgICAgICAnSWYgYGZhbHNlYCwgd2lsbCByZXBvcnQgY29tcHV0ZWQgKGFuZCB0aHVzLCB1bi1saW50YWJsZSkgcmVmZXJlbmNlcyAnICtcbiAgICAgICAgICAgICAgJ3RvIG5hbWVzcGFjZSBtZW1iZXJzLicsXG4gICAgICAgICAgICAndHlwZSc6ICdib29sZWFuJyxcbiAgICAgICAgICAgICdkZWZhdWx0JzogZmFsc2UsXG4gICAgICAgICAgfSxcbiAgICAgICAgfSxcbiAgICAgICAgJ2FkZGl0aW9uYWxQcm9wZXJ0aWVzJzogZmFsc2UsXG4gICAgICB9LFxuICAgIF0sXG4gIH0sXG5cbiAgY3JlYXRlOiBmdW5jdGlvbiBuYW1lc3BhY2VSdWxlKGNvbnRleHQpIHtcblxuICAgIC8vIHJlYWQgb3B0aW9uc1xuICAgIGNvbnN0IHtcbiAgICAgIGFsbG93Q29tcHV0ZWQgPSBmYWxzZSxcbiAgICB9ID0gY29udGV4dC5vcHRpb25zWzBdIHx8IHt9XG5cbiAgICBjb25zdCBuYW1lc3BhY2VzID0gbmV3IE1hcCgpXG5cbiAgICBmdW5jdGlvbiBtYWtlTWVzc2FnZShsYXN0LCBuYW1lcGF0aCkge1xuICAgICAgIHJldHVybiBgJyR7bGFzdC5uYW1lfScgbm90IGZvdW5kIGluYCArXG4gICAgICAgICAgICAgIChuYW1lcGF0aC5sZW5ndGggPiAxID8gJyBkZWVwbHkgJyA6ICcgJykgK1xuICAgICAgICAgICAgICBgaW1wb3J0ZWQgbmFtZXNwYWNlICcke25hbWVwYXRoLmpvaW4oJy4nKX0nLmBcbiAgICB9XG5cbiAgICByZXR1cm4ge1xuXG4gICAgICAvLyBwaWNrIHVwIGFsbCBpbXBvcnRzIGF0IGJvZHkgZW50cnkgdGltZSwgdG8gcHJvcGVybHkgcmVzcGVjdCBob2lzdGluZ1xuICAgICAgJ1Byb2dyYW0nOiBmdW5jdGlvbiAoeyBib2R5IH0pIHtcbiAgICAgICAgZnVuY3Rpb24gcHJvY2Vzc0JvZHlTdGF0ZW1lbnQoZGVjbGFyYXRpb24pIHtcbiAgICAgICAgICBpZiAoZGVjbGFyYXRpb24udHlwZSAhPT0gJ0ltcG9ydERlY2xhcmF0aW9uJykgcmV0dXJuXG5cbiAgICAgICAgICBpZiAoZGVjbGFyYXRpb24uc3BlY2lmaWVycy5sZW5ndGggPT09IDApIHJldHVyblxuXG4gICAgICAgICAgY29uc3QgaW1wb3J0cyA9IEV4cG9ydHMuZ2V0KGRlY2xhcmF0aW9uLnNvdXJjZS52YWx1ZSwgY29udGV4dClcbiAgICAgICAgICBpZiAoaW1wb3J0cyA9PSBudWxsKSByZXR1cm4gbnVsbFxuXG4gICAgICAgICAgaWYgKGltcG9ydHMuZXJyb3JzLmxlbmd0aCkge1xuICAgICAgICAgICAgaW1wb3J0cy5yZXBvcnRFcnJvcnMoY29udGV4dCwgZGVjbGFyYXRpb24pXG4gICAgICAgICAgICByZXR1cm5cbiAgICAgICAgICB9XG5cbiAgICAgICAgICBmb3IgKGxldCBzcGVjaWZpZXIgb2YgZGVjbGFyYXRpb24uc3BlY2lmaWVycykge1xuICAgICAgICAgICAgc3dpdGNoIChzcGVjaWZpZXIudHlwZSkge1xuICAgICAgICAgICAgICBjYXNlICdJbXBvcnROYW1lc3BhY2VTcGVjaWZpZXInOlxuICAgICAgICAgICAgICAgIGlmICghaW1wb3J0cy5zaXplKSB7XG4gICAgICAgICAgICAgICAgICBjb250ZXh0LnJlcG9ydChzcGVjaWZpZXIsXG4gICAgICAgICAgICAgICAgICAgIGBObyBleHBvcnRlZCBuYW1lcyBmb3VuZCBpbiBtb2R1bGUgJyR7ZGVjbGFyYXRpb24uc291cmNlLnZhbHVlfScuYClcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgbmFtZXNwYWNlcy5zZXQoc3BlY2lmaWVyLmxvY2FsLm5hbWUsIGltcG9ydHMpXG4gICAgICAgICAgICAgICAgYnJlYWtcbiAgICAgICAgICAgICAgY2FzZSAnSW1wb3J0RGVmYXVsdFNwZWNpZmllcic6XG4gICAgICAgICAgICAgIGNhc2UgJ0ltcG9ydFNwZWNpZmllcic6IHtcbiAgICAgICAgICAgICAgICBjb25zdCBtZXRhID0gaW1wb3J0cy5nZXQoXG4gICAgICAgICAgICAgICAgICAvLyBkZWZhdWx0IHRvICdkZWZhdWx0JyBmb3IgZGVmYXVsdCBodHRwOi8vaS5pbWd1ci5jb20vbmo2cUFXeS5qcGdcbiAgICAgICAgICAgICAgICAgIHNwZWNpZmllci5pbXBvcnRlZCA/IHNwZWNpZmllci5pbXBvcnRlZC5uYW1lIDogJ2RlZmF1bHQnKVxuICAgICAgICAgICAgICAgIGlmICghbWV0YSB8fCAhbWV0YS5uYW1lc3BhY2UpIGJyZWFrXG4gICAgICAgICAgICAgICAgbmFtZXNwYWNlcy5zZXQoc3BlY2lmaWVyLmxvY2FsLm5hbWUsIG1ldGEubmFtZXNwYWNlKVxuICAgICAgICAgICAgICAgIGJyZWFrXG4gICAgICAgICAgICAgIH1cbiAgICAgICAgICAgIH1cbiAgICAgICAgICB9XG4gICAgICAgIH1cbiAgICAgICAgYm9keS5mb3JFYWNoKHByb2Nlc3NCb2R5U3RhdGVtZW50KVxuICAgICAgfSxcblxuICAgICAgLy8gc2FtZSBhcyBhYm92ZSwgYnV0IGRvZXMgbm90IGFkZCBuYW1lcyB0byBsb2NhbCBtYXBcbiAgICAgICdFeHBvcnROYW1lc3BhY2VTcGVjaWZpZXInOiBmdW5jdGlvbiAobmFtZXNwYWNlKSB7XG4gICAgICAgIHZhciBkZWNsYXJhdGlvbiA9IGltcG9ydERlY2xhcmF0aW9uKGNvbnRleHQpXG5cbiAgICAgICAgdmFyIGltcG9ydHMgPSBFeHBvcnRzLmdldChkZWNsYXJhdGlvbi5zb3VyY2UudmFsdWUsIGNvbnRleHQpXG4gICAgICAgIGlmIChpbXBvcnRzID09IG51bGwpIHJldHVybiBudWxsXG5cbiAgICAgICAgaWYgKGltcG9ydHMuZXJyb3JzLmxlbmd0aCkge1xuICAgICAgICAgIGltcG9ydHMucmVwb3J0RXJyb3JzKGNvbnRleHQsIGRlY2xhcmF0aW9uKVxuICAgICAgICAgIHJldHVyblxuICAgICAgICB9XG5cbiAgICAgICAgaWYgKCFpbXBvcnRzLnNpemUpIHtcbiAgICAgICAgICBjb250ZXh0LnJlcG9ydChuYW1lc3BhY2UsXG4gICAgICAgICAgICBgTm8gZXhwb3J0ZWQgbmFtZXMgZm91bmQgaW4gbW9kdWxlICcke2RlY2xhcmF0aW9uLnNvdXJjZS52YWx1ZX0nLmApXG4gICAgICAgIH1cbiAgICAgIH0sXG5cbiAgICAgIC8vIHRvZG86IGNoZWNrIGZvciBwb3NzaWJsZSByZWRlZmluaXRpb25cblxuICAgICAgJ01lbWJlckV4cHJlc3Npb24nOiBmdW5jdGlvbiAoZGVyZWZlcmVuY2UpIHtcbiAgICAgICAgaWYgKGRlcmVmZXJlbmNlLm9iamVjdC50eXBlICE9PSAnSWRlbnRpZmllcicpIHJldHVyblxuICAgICAgICBpZiAoIW5hbWVzcGFjZXMuaGFzKGRlcmVmZXJlbmNlLm9iamVjdC5uYW1lKSkgcmV0dXJuXG5cbiAgICAgICAgaWYgKGRlcmVmZXJlbmNlLnBhcmVudC50eXBlID09PSAnQXNzaWdubWVudEV4cHJlc3Npb24nICYmXG4gICAgICAgICAgICBkZXJlZmVyZW5jZS5wYXJlbnQubGVmdCA9PT0gZGVyZWZlcmVuY2UpIHtcbiAgICAgICAgICAgIGNvbnRleHQucmVwb3J0KGRlcmVmZXJlbmNlLnBhcmVudCxcbiAgICAgICAgICAgICAgICBgQXNzaWdubWVudCB0byBtZW1iZXIgb2YgbmFtZXNwYWNlICcke2RlcmVmZXJlbmNlLm9iamVjdC5uYW1lfScuYClcbiAgICAgICAgfVxuXG4gICAgICAgIC8vIGdvIGRlZXBcbiAgICAgICAgdmFyIG5hbWVzcGFjZSA9IG5hbWVzcGFjZXMuZ2V0KGRlcmVmZXJlbmNlLm9iamVjdC5uYW1lKVxuICAgICAgICB2YXIgbmFtZXBhdGggPSBbZGVyZWZlcmVuY2Uub2JqZWN0Lm5hbWVdXG4gICAgICAgIC8vIHdoaWxlIHByb3BlcnR5IGlzIG5hbWVzcGFjZSBhbmQgcGFyZW50IGlzIG1lbWJlciBleHByZXNzaW9uLCBrZWVwIHZhbGlkYXRpbmdcbiAgICAgICAgd2hpbGUgKG5hbWVzcGFjZSBpbnN0YW5jZW9mIEV4cG9ydHMgJiZcbiAgICAgICAgICAgICAgIGRlcmVmZXJlbmNlLnR5cGUgPT09ICdNZW1iZXJFeHByZXNzaW9uJykge1xuXG4gICAgICAgICAgaWYgKGRlcmVmZXJlbmNlLmNvbXB1dGVkKSB7XG4gICAgICAgICAgICBpZiAoIWFsbG93Q29tcHV0ZWQpIHtcbiAgICAgICAgICAgICAgY29udGV4dC5yZXBvcnQoZGVyZWZlcmVuY2UucHJvcGVydHksXG4gICAgICAgICAgICAgICAgJ1VuYWJsZSB0byB2YWxpZGF0ZSBjb21wdXRlZCByZWZlcmVuY2UgdG8gaW1wb3J0ZWQgbmFtZXNwYWNlIFxcJycgK1xuICAgICAgICAgICAgICAgIGRlcmVmZXJlbmNlLm9iamVjdC5uYW1lICsgJ1xcJy4nKVxuICAgICAgICAgICAgfVxuICAgICAgICAgICAgcmV0dXJuXG4gICAgICAgICAgfVxuXG4gICAgICAgICAgaWYgKCFuYW1lc3BhY2UuaGFzKGRlcmVmZXJlbmNlLnByb3BlcnR5Lm5hbWUpKSB7XG4gICAgICAgICAgICBjb250ZXh0LnJlcG9ydChcbiAgICAgICAgICAgICAgZGVyZWZlcmVuY2UucHJvcGVydHksXG4gICAgICAgICAgICAgIG1ha2VNZXNzYWdlKGRlcmVmZXJlbmNlLnByb3BlcnR5LCBuYW1lcGF0aCkpXG4gICAgICAgICAgICBicmVha1xuICAgICAgICAgIH1cblxuICAgICAgICAgIGNvbnN0IGV4cG9ydGVkID0gbmFtZXNwYWNlLmdldChkZXJlZmVyZW5jZS5wcm9wZXJ0eS5uYW1lKVxuICAgICAgICAgIGlmIChleHBvcnRlZCA9PSBudWxsKSByZXR1cm5cblxuICAgICAgICAgIC8vIHN0YXNoIGFuZCBwb3BcbiAgICAgICAgICBuYW1lcGF0aC5wdXNoKGRlcmVmZXJlbmNlLnByb3BlcnR5Lm5hbWUpXG4gICAgICAgICAgbmFtZXNwYWNlID0gZXhwb3J0ZWQubmFtZXNwYWNlXG4gICAgICAgICAgZGVyZWZlcmVuY2UgPSBkZXJlZmVyZW5jZS5wYXJlbnRcbiAgICAgICAgfVxuXG4gICAgICB9LFxuXG4gICAgICAnVmFyaWFibGVEZWNsYXJhdG9yJzogZnVuY3Rpb24gKHsgaWQsIGluaXQgfSkge1xuICAgICAgICBpZiAoaW5pdCA9PSBudWxsKSByZXR1cm5cbiAgICAgICAgaWYgKGluaXQudHlwZSAhPT0gJ0lkZW50aWZpZXInKSByZXR1cm5cbiAgICAgICAgaWYgKCFuYW1lc3BhY2VzLmhhcyhpbml0Lm5hbWUpKSByZXR1cm5cblxuICAgICAgICAvLyBjaGVjayBmb3IgcmVkZWZpbml0aW9uIGluIGludGVybWVkaWF0ZSBzY29wZXNcbiAgICAgICAgaWYgKGRlY2xhcmVkU2NvcGUoY29udGV4dCwgaW5pdC5uYW1lKSAhPT0gJ21vZHVsZScpIHJldHVyblxuXG4gICAgICAgIC8vIERGUyB0cmF2ZXJzZSBjaGlsZCBuYW1lc3BhY2VzXG4gICAgICAgIGZ1bmN0aW9uIHRlc3RLZXkocGF0dGVybiwgbmFtZXNwYWNlLCBwYXRoID0gW2luaXQubmFtZV0pIHtcbiAgICAgICAgICBpZiAoIShuYW1lc3BhY2UgaW5zdGFuY2VvZiBFeHBvcnRzKSkgcmV0dXJuXG5cbiAgICAgICAgICBpZiAocGF0dGVybi50eXBlICE9PSAnT2JqZWN0UGF0dGVybicpIHJldHVyblxuXG4gICAgICAgICAgZm9yIChsZXQgcHJvcGVydHkgb2YgcGF0dGVybi5wcm9wZXJ0aWVzKSB7XG4gICAgICAgICAgICBpZiAocHJvcGVydHkudHlwZSA9PT0gJ0V4cGVyaW1lbnRhbFJlc3RQcm9wZXJ0eScpIHtcbiAgICAgICAgICAgICAgY29udGludWVcbiAgICAgICAgICAgIH1cblxuICAgICAgICAgICAgaWYgKHByb3BlcnR5LmtleS50eXBlICE9PSAnSWRlbnRpZmllcicpIHtcbiAgICAgICAgICAgICAgY29udGV4dC5yZXBvcnQoe1xuICAgICAgICAgICAgICAgIG5vZGU6IHByb3BlcnR5LFxuICAgICAgICAgICAgICAgIG1lc3NhZ2U6ICdPbmx5IGRlc3RydWN0dXJlIHRvcC1sZXZlbCBuYW1lcy4nLFxuICAgICAgICAgICAgICB9KVxuICAgICAgICAgICAgICBjb250aW51ZVxuICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICBpZiAoIW5hbWVzcGFjZS5oYXMocHJvcGVydHkua2V5Lm5hbWUpKSB7XG4gICAgICAgICAgICAgIGNvbnRleHQucmVwb3J0KHtcbiAgICAgICAgICAgICAgICBub2RlOiBwcm9wZXJ0eSxcbiAgICAgICAgICAgICAgICBtZXNzYWdlOiBtYWtlTWVzc2FnZShwcm9wZXJ0eS5rZXksIHBhdGgpLFxuICAgICAgICAgICAgICB9KVxuICAgICAgICAgICAgICBjb250aW51ZVxuICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICBwYXRoLnB1c2gocHJvcGVydHkua2V5Lm5hbWUpXG4gICAgICAgICAgICB0ZXN0S2V5KHByb3BlcnR5LnZhbHVlLCBuYW1lc3BhY2UuZ2V0KHByb3BlcnR5LmtleS5uYW1lKS5uYW1lc3BhY2UsIHBhdGgpXG4gICAgICAgICAgICBwYXRoLnBvcCgpXG4gICAgICAgICAgfVxuICAgICAgICB9XG5cbiAgICAgICAgdGVzdEtleShpZCwgbmFtZXNwYWNlcy5nZXQoaW5pdC5uYW1lKSlcbiAgICAgIH0sXG4gICAgfVxuICB9LFxufVxuIl19