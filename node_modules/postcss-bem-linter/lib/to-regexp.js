'use strict';

module.exports = (source) => {
  if (Array.isArray(source)) {
    return source.map(regexpify);
  }
  return regexpify(source);
};

function regexpify(source) {
  if (typeof source === 'string') {
    if (!source.length) {
      throw new Error('You passed an empty pattern');
    }

    try {
      return new RegExp(source);
    } catch (e) {
      throw new Error(`"${source}" is not a valid regular expression`);
    }
  } else {
    return source;
  }
}
