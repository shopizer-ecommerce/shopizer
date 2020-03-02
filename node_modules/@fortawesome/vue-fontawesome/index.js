(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('@fortawesome/fontawesome-svg-core')) :
	typeof define === 'function' && define.amd ? define(['exports', '@fortawesome/fontawesome-svg-core'], factory) :
	(factory((global['vue-fontawesome'] = {}),global.FontAwesome));
}(this, (function (exports,fontawesomeSvgCore) { 'use strict';

	var commonjsGlobal = typeof window !== 'undefined' ? window : typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {};

	function createCommonjsModule(fn, module) {
		return module = { exports: {} }, fn(module, module.exports), module.exports;
	}

	var humps = createCommonjsModule(function (module) {
	(function(global) {

	  var _processKeys = function(convert, obj, options) {
	    if(!_isObject(obj) || _isDate(obj) || _isRegExp(obj) || _isBoolean(obj) || _isFunction(obj)) {
	      return obj;
	    }

	    var output,
	        i = 0,
	        l = 0;

	    if(_isArray(obj)) {
	      output = [];
	      for(l=obj.length; i<l; i++) {
	        output.push(_processKeys(convert, obj[i], options));
	      }
	    }
	    else {
	      output = {};
	      for(var key in obj) {
	        if(Object.prototype.hasOwnProperty.call(obj, key)) {
	          output[convert(key, options)] = _processKeys(convert, obj[key], options);
	        }
	      }
	    }
	    return output;
	  };

	  // String conversion methods

	  var separateWords = function(string, options) {
	    options = options || {};
	    var separator = options.separator || '_';
	    var split = options.split || /(?=[A-Z])/;

	    return string.split(split).join(separator);
	  };

	  var camelize = function(string) {
	    if (_isNumerical(string)) {
	      return string;
	    }
	    string = string.replace(/[\-_\s]+(.)?/g, function(match, chr) {
	      return chr ? chr.toUpperCase() : '';
	    });
	    // Ensure 1st char is always lowercase
	    return string.substr(0, 1).toLowerCase() + string.substr(1);
	  };

	  var pascalize = function(string) {
	    var camelized = camelize(string);
	    // Ensure 1st char is always uppercase
	    return camelized.substr(0, 1).toUpperCase() + camelized.substr(1);
	  };

	  var decamelize = function(string, options) {
	    return separateWords(string, options).toLowerCase();
	  };

	  // Utilities
	  // Taken from Underscore.js

	  var toString = Object.prototype.toString;

	  var _isFunction = function(obj) {
	    return typeof(obj) === 'function';
	  };
	  var _isObject = function(obj) {
	    return obj === Object(obj);
	  };
	  var _isArray = function(obj) {
	    return toString.call(obj) == '[object Array]';
	  };
	  var _isDate = function(obj) {
	    return toString.call(obj) == '[object Date]';
	  };
	  var _isRegExp = function(obj) {
	    return toString.call(obj) == '[object RegExp]';
	  };
	  var _isBoolean = function(obj) {
	    return toString.call(obj) == '[object Boolean]';
	  };

	  // Performant way to determine if obj coerces to a number
	  var _isNumerical = function(obj) {
	    obj = obj - 0;
	    return obj === obj;
	  };

	  // Sets up function which handles processing keys
	  // allowing the convert function to be modified by a callback
	  var _processor = function(convert, options) {
	    var callback = options && 'process' in options ? options.process : options;

	    if(typeof(callback) !== 'function') {
	      return convert;
	    }

	    return function(string, options) {
	      return callback(string, convert, options);
	    }
	  };

	  var humps = {
	    camelize: camelize,
	    decamelize: decamelize,
	    pascalize: pascalize,
	    depascalize: decamelize,
	    camelizeKeys: function(object, options) {
	      return _processKeys(_processor(camelize, options), object);
	    },
	    decamelizeKeys: function(object, options) {
	      return _processKeys(_processor(decamelize, options), object, options);
	    },
	    pascalizeKeys: function(object, options) {
	      return _processKeys(_processor(pascalize, options), object);
	    },
	    depascalizeKeys: function () {
	      return this.decamelizeKeys.apply(this, arguments);
	    }
	  };

	  if (typeof undefined === 'function' && undefined.amd) {
	    undefined(humps);
	  } else if ('object' !== 'undefined' && module.exports) {
	    module.exports = humps;
	  } else {
	    global.humps = humps;
	  }

	})(commonjsGlobal);
	});

	var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) {
	  return typeof obj;
	} : function (obj) {
	  return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
	};

	var defineProperty = function (obj, key, value) {
	  if (key in obj) {
	    Object.defineProperty(obj, key, {
	      value: value,
	      enumerable: true,
	      configurable: true,
	      writable: true
	    });
	  } else {
	    obj[key] = value;
	  }

	  return obj;
	};

	var _extends = Object.assign || function (target) {
	  for (var i = 1; i < arguments.length; i++) {
	    var source = arguments[i];

	    for (var key in source) {
	      if (Object.prototype.hasOwnProperty.call(source, key)) {
	        target[key] = source[key];
	      }
	    }
	  }

	  return target;
	};

	var objectWithoutProperties = function (obj, keys) {
	  var target = {};

	  for (var i in obj) {
	    if (keys.indexOf(i) >= 0) continue;
	    if (!Object.prototype.hasOwnProperty.call(obj, i)) continue;
	    target[i] = obj[i];
	  }

	  return target;
	};

	var toConsumableArray = function (arr) {
	  if (Array.isArray(arr)) {
	    for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) arr2[i] = arr[i];

	    return arr2;
	  } else {
	    return Array.from(arr);
	  }
	};

	function styleToObject(style) {
	  return style.split(';').map(function (s) {
	    return s.trim();
	  }).filter(function (s) {
	    return s;
	  }).reduce(function (acc, pair) {
	    var i = pair.indexOf(':');
	    var prop = humps.camelize(pair.slice(0, i));
	    var value = pair.slice(i + 1).trim();

	    acc[prop] = value;

	    return acc;
	  }, {});
	}

	function classToObject(cls) {
	  return cls.split(/\s+/).reduce(function (acc, c) {
	    acc[c] = true;

	    return acc;
	  }, {});
	}

	function combineClassObjects() {
	  for (var _len = arguments.length, objs = Array(_len), _key = 0; _key < _len; _key++) {
	    objs[_key] = arguments[_key];
	  }

	  return objs.reduce(function (acc, obj) {
	    if (Array.isArray(obj)) {
	      acc = acc.concat(obj);
	    } else {
	      acc.push(obj);
	    }

	    return acc;
	  }, []);
	}

	function convert(h, element) {
	  var props = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : {};
	  var data = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : {};

	  var children = (element.children || []).map(convert.bind(null, h));

	  var mixins = Object.keys(element.attributes || {}).reduce(function (acc, key) {
	    var val = element.attributes[key];

	    switch (key) {
	      case 'class':
	        acc['class'] = classToObject(val);
	        break;
	      case 'style':
	        acc['style'] = styleToObject(val);
	        break;
	      default:
	        acc.attrs[key] = val;
	    }

	    return acc;
	  }, { 'class': {}, style: {}, attrs: {} });

	  var _data$class = data.class,
	      dClass = _data$class === undefined ? {} : _data$class,
	      _data$style = data.style,
	      dStyle = _data$style === undefined ? {} : _data$style,
	      _data$attrs = data.attrs,
	      dAttrs = _data$attrs === undefined ? {} : _data$attrs,
	      remainingData = objectWithoutProperties(data, ['class', 'style', 'attrs']);


	  if (typeof element === 'string') {
	    return element;
	  } else {
	    return h(element.tag, _extends({
	      class: combineClassObjects(mixins.class, dClass),
	      style: _extends({}, mixins.style, dStyle),
	      attrs: _extends({}, mixins.attrs, dAttrs)
	    }, remainingData, {
	      props: props
	    }), children);
	  }
	}

	var PRODUCTION = false;

	try {
	  PRODUCTION = process.env.NODE_ENV === 'production';
	} catch (e) {}

	function log () {
	  if (!PRODUCTION && console && typeof console.error === 'function') {
	    var _console;

	    (_console = console).error.apply(_console, arguments);
	  }
	}

	function objectWithKey(key, value) {
	  return Array.isArray(value) && value.length > 0 || !Array.isArray(value) && value ? defineProperty({}, key, value) : {};
	}

	function classList(props) {
	  var _classes;

	  var classes = (_classes = {
	    'fa-spin': props.spin,
	    'fa-pulse': props.pulse,
	    'fa-fw': props.fixedWidth,
	    'fa-border': props.border,
	    'fa-li': props.listItem,
	    'fa-flip-horizontal': props.flip === 'horizontal' || props.flip === 'both',
	    'fa-flip-vertical': props.flip === 'vertical' || props.flip === 'both'
	  }, defineProperty(_classes, 'fa-' + props.size, props.size !== null), defineProperty(_classes, 'fa-rotate-' + props.rotation, props.rotation !== null), defineProperty(_classes, 'fa-pull-' + props.pull, props.pull !== null), _classes);

	  return Object.keys(classes).map(function (key) {
	    return classes[key] ? key : null;
	  }).filter(function (key) {
	    return key;
	  });
	}

	function addStaticClass(to, what) {
	  var val = (to || '').length === 0 ? [] : [to];

	  return val.concat(what).join(' ');
	}

	function normalizeIconArgs(icon) {
	  if (icon === null) {
	    return null;
	  }

	  if ((typeof icon === 'undefined' ? 'undefined' : _typeof(icon)) === 'object' && icon.prefix && icon.iconName) {
	    return icon;
	  }

	  if (Array.isArray(icon) && icon.length === 2) {
	    return { prefix: icon[0], iconName: icon[1] };
	  }

	  if (typeof icon === 'string') {
	    return { prefix: 'fas', iconName: icon };
	  }
	}

	var FontAwesomeIcon = {
	  name: 'FontAwesomeIcon',

	  functional: true,

	  props: {
	    border: {
	      type: Boolean,
	      default: false
	    },
	    fixedWidth: {
	      type: Boolean,
	      default: false
	    },
	    flip: {
	      type: String,
	      default: null,
	      validator: function validator(value) {
	        return ['horizontal', 'vertical', 'both'].indexOf(value) > -1;
	      }
	    },
	    icon: {
	      type: [Object, Array, String],
	      required: true
	    },
	    mask: {
	      type: [Object, Array, String],
	      default: null
	    },
	    listItem: {
	      type: Boolean,
	      default: false
	    },
	    pull: {
	      type: String,
	      default: null,
	      validator: function validator(value) {
	        return ['right', 'left'].indexOf(value) > -1;
	      }
	    },
	    pulse: {
	      type: Boolean,
	      default: false
	    },
	    rotation: {
	      type: Number,
	      default: null,
	      validator: function validator(value) {
	        return [90, 180, 270].indexOf(value) > -1;
	      }
	    },
	    size: {
	      type: String,
	      default: null,
	      validator: function validator(value) {
	        return ['lg', 'xs', 'sm', '1x', '2x', '3x', '4x', '5x', '6x', '7x', '8x', '9x', '10x'].indexOf(value) > -1;
	      }
	    },
	    spin: {
	      type: Boolean,
	      default: false
	    },
	    transform: {
	      type: [String, Object],
	      default: null
	    },
	    symbol: {
	      type: [Boolean, String],
	      default: false
	    }
	  },

	  render: function render(createElement, context) {
	    var props = context.props;
	    var iconArgs = props.icon,
	        maskArgs = props.mask,
	        symbol = props.symbol;

	    var icon = normalizeIconArgs(iconArgs);
	    var classes = objectWithKey('classes', classList(props));
	    var transform = objectWithKey('transform', typeof props.transform === 'string' ? fontawesomeSvgCore.parse.transform(props.transform) : props.transform);
	    var mask = objectWithKey('mask', normalizeIconArgs(maskArgs));

	    var renderedIcon = fontawesomeSvgCore.icon(icon, _extends({}, classes, transform, mask, { symbol: symbol }));

	    if (!renderedIcon) {
	      return log('Could not find one or more icon(s)', icon, mask);
	    }

	    var abstract = renderedIcon.abstract;

	    var convertCurry = convert.bind(null, createElement);

	    return convertCurry(abstract[0], {}, context.data);
	  }
	};

	var FontAwesomeLayers = {
	  name: 'FontAwesomeLayers',

	  functional: true,

	  props: {
	    fixedWidth: {
	      type: Boolean,
	      default: false
	    }
	  },

	  render: function render(createElement, context) {
	    var familyPrefix = fontawesomeSvgCore.config.familyPrefix;
	    var staticClass = context.data.staticClass;


	    var classes = [familyPrefix + '-layers'].concat(toConsumableArray(context.props.fixedWidth ? [familyPrefix + '-fw'] : []));

	    return createElement('div', _extends({}, context.data, {
	      staticClass: addStaticClass(staticClass, classes)
	    }), context.children);
	  }
	};

	var FontAwesomeLayersText = {
	  name: 'FontAwesomeLayersText',

	  functional: true,

	  props: {
	    value: {
	      type: [String, Number],
	      default: ''
	    },
	    transform: {
	      type: [String, Object],
	      default: null
	    }
	  },

	  render: function render(createElement, context) {
	    var props = context.props;

	    var transform = objectWithKey('transform', typeof props.transform === 'string' ? fontawesomeSvgCore.parse.transform(props.transform) : props.transform);

	    var renderedText = fontawesomeSvgCore.text(props.value.toString(), _extends({}, transform));

	    var abstract = renderedText.abstract;


	    var convertCurry = convert.bind(null, createElement);

	    return convertCurry(abstract[0], {}, context.data);
	  }
	};

	exports.FontAwesomeIcon = FontAwesomeIcon;
	exports.FontAwesomeLayers = FontAwesomeLayers;
	exports.FontAwesomeLayersText = FontAwesomeLayersText;

	Object.defineProperty(exports, '__esModule', { value: true });

})));
