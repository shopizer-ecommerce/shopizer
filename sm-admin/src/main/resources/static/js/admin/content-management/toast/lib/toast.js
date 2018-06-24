(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    define([], function() {
    	return (root.toast = factory());
    });
  } else if (typeof exports === 'object') {
    module.exports = factory();
  } else {
    root.toast = factory();
  }
}(this, function() {
var handled_resources = {};

function Toast() {
	var head = document.getElementsByTagName('head')[0],
		
	// Load as much resources as we can
	loadResources = function(resources) {
		// Waiting for DOM readiness then load resources
		if(!head) {
			setTimeout(function() {
				loadResources(resources);
			}, 50);
		}
		// Load resources
		else if(resources.length) {
			var i = -1,
				resource,
				callback;
			while(resource = resources[++i]) {
				// Resource
				if(typeof resource == 'string') {
					loadResource(resource);
				}
				// Callback
				else if(typeof resource == 'function') {
					callback = resource;
					break;
				}
			}
			watchResources(callback, Array.prototype.slice.call(resources, i+1));
		}
	},

	// Load one resource
	loadResource = function(resource) {
		// Extract resource type
		var implicit_type = /\.(\w+)$/.exec(resource),
			explicit_type = /^\[(\w+)\](.+)/.exec(resource),
			type, node;
		if(explicit_type !== null) {
			type = explicit_type[1];
			resource = explicit_type[2];
		}
		else if(implicit_type !== null) {
			type = implicit_type[1];
		}
		else {
			return;
		}
		// Verify if the resource is not already handled
		if(resource in handled_resources) {
			return;
		}
		// Mark the resource as handled (but not loaded yet)
		handled_resources[resource] = false;
		// Load resource
		switch(type) {
			case 'js':
				// Create SCRIPT element
				node = document.createElement('script');
				node.src = resource;
                node.async = false;
				head.appendChild(node);
				// Watch loading state
				var version = navigator.appVersion.match(/MSIE (\d)/);
				if(version !== null && parseInt(version[1], 10) < 9) {
					// IE<9
					node.onreadystatechange = function() {
						if(/ded|co/.test(this.readyState)) {
							handled_resources[resource] = true;
                            node.onreadystatechange = null;
						}
					};
				}
				else {
					// Other browsers
					node.onload = function() {
						handled_resources[resource] = true;
                        node.onload = null;
					};
				}
				break;
			case 'css':
				// Create LINK element
				node = document.createElement('link');
				node.rel = 'styleSheet';
				node.href = resource;
				head.appendChild(node);
				// Watch loading state
				watchStylesheet(node, resource);
				break;
			default:
				delete handled_resources[resource];
				return;
		}
	},

	// Watch if all resources have been loaded
	watchResources = function(callback, resourcesToLoad) {
		for(var resource in handled_resources) {
			if(!handled_resources[resource]) {
				setTimeout(function() {
					watchResources(callback, resourcesToLoad);
				}, 50);
				return;
			}
		}
		if(typeof callback == 'function') {
			callback();
		}
		loadResources(resourcesToLoad);
	},

	// Watch if a CSS resource has been loaded
	watchStylesheet = function(node, resource) {
		if(node.sheet || node.styleSheet) {
			handled_resources[resource] = true;
		}
		else {
			setTimeout(function() {
				watchStylesheet(node, resource);
			}, 50);
		}
	};
	
	// Load resources
	loadResources(arguments);
}
return Toast;
}));
