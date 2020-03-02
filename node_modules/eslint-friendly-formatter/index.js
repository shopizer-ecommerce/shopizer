/**
 * Based on Stylish reporter from Sindre Sorhus
 */
'use strict';

var chalk = require('chalk'),
  table = require('text-table'),
  extend = require('extend');

var path = require('path');

var process = require('./process');
var minimist = require('minimist');
var clsc = require('coalescy');

//------------------------------------------------------------------------------
// Helpers
//------------------------------------------------------------------------------

/**
 * Given a word and a count, append an s if count is not one.
 * @param {string} word A word in its singular form.
 * @param {int} count A number controlling whether word should be pluralized.
 * @returns {string} The original word with an s on the end if count is not one.
 */
function pluralize(word, count) {
  return (count === 1 ? word : word + 's');
}

var parseBoolEnvVar = function(varName) {
  var env = process.env || { };
  return env[varName] === 'true';
};

var subtleLog = function(args) {
  return parseBoolEnvVar('EFF_NO_GRAY') ? args : chalk.gray(args);
};

var getEnvVar = function(varName) {
  var env = process.env || { };
  return env[varName] || false;
};

var getFileLink = function(_path, line, column) {
  var scheme = getEnvVar('EFF_EDITOR_SCHEME');
  if (scheme === false) {
    return false;
  }
  return scheme.replace('{file}', encodeURIComponent(_path)).replace('{line}', line).replace('{column}', column);
};

var getKeyLink = function(key) {
  var noLinkRules = parseBoolEnvVar('EFF_NO_LINK_RULES');
  var url = key.indexOf('/') > -1 ? 'https://google.com/#q=' : 'http://eslint.org/docs/rules/';
  return (!noLinkRules) ? chalk.underline(subtleLog(url + chalk.white(encodeURIComponent(key)))) : chalk.white(key);
};

var printSummary = function(hash, title, method) {
  var res = '\n\n' + chalk[method](title + ':\n');
  res += table(
    Object.keys(hash).sort(function(a, b) {
      return hash[a] > hash[b] ? -1 : 1;
    }).map(function(key) {
      return [
        '',
        hash[key],
        getKeyLink(key)
      ];
    }), {
      align: [
        '',
        'r',
        'l'
      ],
      stringLength: function(str) {
        return chalk.stripColor(str).length;
      }
    });
  return res;
};

//------------------------------------------------------------------------------
// Public Interface
//------------------------------------------------------------------------------

module.exports = function(results) {

  var output = '\n',
    total = 0,
    errors = 0,
    warnings = 0,
    summaryColor = 'yellow';

  results = results || [];

  var entries = [];

  var absolutePathsToFile = parseBoolEnvVar('EFF_ABSOLUTE_PATHS');

  var restArgs = process.argv.slice(process.argv.indexOf('--') + 1);
  var parsedArgs = minimist(restArgs);

  var groupByIssue = parsedArgs['eff-by-issue'];
  var filterRule = parsedArgs['eff-filter'];

  absolutePathsToFile = clsc(parsedArgs['eff-absolute-paths'], absolutePathsToFile);

  var errorsHash = { };
  var warningsHash = { };

  results.forEach(function(result) {
    var messages = result.messages || [];
    entries = entries.concat(messages.map(function(message) {
      return extend({
        filePath: absolutePathsToFile ? path.resolve(result.filePath) : path.relative('.', result.filePath)
      }, message);
    }));
  });

  entries.sort(function(a, b) {
    if (a.severity > b.severity) {
      return 1;
    }
    if (a.severity < b.severity) {
      return -1;
    }

    if (groupByIssue) {
      if (a.ruleId > b.ruleId) {
        return 1;
      }
      if (a.ruleId < b.ruleId) {
        return -1;
      }
    }

    var pathSort = a.filePath.localeCompare(b.filePath);
    if (pathSort) {
      return pathSort;
    }

    if (a.line > b.line) {
      return 1;
    }
    if (a.line < b.line) {
      return -1;
    }

    if (a.column > b.column) {
      return 1;
    }
    if (a.column < b.column) {
      return -1;
    }

    return 0;
  });

  output += table(
        entries.reduce(function(seq, message) {
          var messageType;

          if (filterRule) {
            if (message.ruleId !== filterRule) {
              return seq;
            }
          }

          if (message.fatal || message.severity === 2) {
            messageType = chalk.red('✘');
            summaryColor = 'red';
            errorsHash[message.ruleId] = (errorsHash[message.ruleId] || 0) + 1;
            errors++;
          } else {
            messageType = chalk.yellow('⚠');
            warningsHash[message.ruleId] = (warningsHash[message.ruleId] || 0) + 1;
            warnings++;
          }

          var line = message.line || 0;
          var column = message.column || 0;

          var arrow = '';
          var hasSource = message.source !== undefined && message.source.length < 1000;
          if (hasSource) {
            for (var i = 0; i < message.column; i++) {
              if (message.source.charAt(i) === '\t') {
                arrow += '\t';
              } else {
                arrow += ' ';
              }
            }
            arrow += '^';
          }

          var filePath = message.filePath;
          var link = getFileLink(filePath, line, column);
          var filename = subtleLog(filePath + ':' + line + ':' + column);

          seq.push([
            '',
            messageType + '  ' + getKeyLink(message.ruleId || ''),
            message.message.replace(/\.$/, ''),
            '$MARKER$  ' + (link === false ? chalk.underline(filename) : filename) +
              (link === false ? '' : '$MARKER$  ' + chalk.underline(subtleLog(link))) + '$MARKER$  ' +
              (hasSource ? subtleLog(message.source) + '$MARKER$  ' + subtleLog(arrow) : '') + '$MARKER$'
          ]);
          return seq;
        }, []), {
          align: [
            '',
            'l',
            'l',
            'l'
          ],
          stringLength: function(str) {
            return chalk.stripColor(str).length;
          }
        }).replace(/\$MARKER\$/g, '\n') + '\n\n';

  total = entries.length;

  if (total > 0) {
    output += chalk[summaryColor].bold([
      '✘ ',
      total,
      pluralize(' problem', total),
      ' (',
      errors,
      pluralize(' error', errors),
      ', ',
      warnings,
      pluralize(' warning', warnings),
      ')\n'
    ].join(''));

    if (errors > 0) {
      output += printSummary(errorsHash, 'Errors', 'red');
    }

    if (warnings > 0) {
      output += printSummary(warningsHash, 'Warnings', 'yellow');
    }
  }

  if (process.stdout.isTTY && !process.env.CI) {
    output = '\u001B]50;CurrentDir=' + process.cwd() + '\u0007' + output;
  }

  return total > 0 ? output : '';
};
