# Intro

Node wrapper for Selenium

# Install

    npm install -g selenium-server

# Use

    selenium &

# Using via node

The package exports a `path` string that contains the path to the
selenium server binary/executable.

Below is an example of using this package via node.

```javascript
var seleniumServer = require('selenium-server');
var binPath = seleniumServer.path;
console.log(binPath);
...
```
