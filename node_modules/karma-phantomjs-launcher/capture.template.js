(function (phantom) {
  var page = require('webpage').create();

  <% if (exitOnResourceError) { %>
  page.onResourceError = function() {
    phantom.exit(1)
  }
  <% } %>

  <% _.forOwn(pageOptions, function(value, key) { %>
  page.<%= key %> = <%= value %>
  <% }) %>

  <% _.forOwn(pageSettingsOptions, function(value, key) { %>
  page.settings.<%= key %> = <%= value %>
  <% }) %>

  page.onConsoleMessage = function () {
      console.log.apply(console, arguments)
  }

  <% if (debug) { %>
  function debugPage() {
    console.log('Launch the debugger page at http://localhost:9000/webkit/inspector/inspector.html?page=2')

    var debuggerWait = 15000
    console.log('Waiting ' + (debuggerWait / 1000) + ' seconds for debugger page to launch...')

    var launchPage = function () {
      console.log('Launching page <%= url %>...')
      page.open('<%= url %>')
    }

    setTimeout(launchPage, 15000)
  }
  debugPage()
  <% } else { %>
  page.open('<%= url %>')
  <% } %>
}(phantom))
