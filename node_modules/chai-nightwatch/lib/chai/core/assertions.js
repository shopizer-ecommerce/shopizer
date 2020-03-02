/*!
 * chai
 * http://chaijs.com
 * Copyright(c) 2011-2014 Jake Luer <jake@alogicalparadox.com>
 * MIT Licensed
 */

module.exports = function (chai, _) {
  var Assertion = chai.Assertion
    , toString = Object.prototype.toString
    , flag = _.flag;

  /**
   * ### Language Chains
   *
   * The following are provided as chainable getters to
   * improve the readability of your assertions. They
   * do not provide testing capabilities unless they
   * have been overwritten by a plugin.
   *
   * **Chains**
   *
   * - to
   * - be
   * - been
   * - is
   * - that
   * - which
   * - and
   * - has
   * - have
   * - with
   * - at
   * - of
   * - same
   *
   * @name language chains
   * @api public
   */

  [ 'to', 'be', 'been'
  , 'is', 'and', 'has', 'have'
  , 'with', 'that', 'which', 'at', 'does'
  , 'of', 'same' ].forEach(function (chain) {
    Assertion.addProperty(chain, function () {
      flag(this, chain, true);
      return this;
    });
  });

  /**
   * ### .not
   *
   * Negates any of assertions following in the chain.
   *
   *     expect(foo).to.not.equal('bar');
   *     expect(goodFn).to.not.throw(Error);
   *     expect({ foo: 'baz' }).to.have.property('foo')
   *       .and.not.equal('bar');
   *
   * @name not
   * @api public
   */

  Assertion.addProperty('not', function () {
    flag(this, 'negate', true);
  });

  /**
   * ### .deep
   *
   * Sets the `deep` flag, later used by the `equal` and
   * `property` assertions.
   *
   *     expect(foo).to.deep.equal({ bar: 'baz' });
   *     expect({ foo: { bar: { baz: 'quux' } } })
   *       .to.have.deep.property('foo.bar.baz', 'quux');
   *
   * `.deep.property` special characters can be escaped
   * by adding two slashes before the `.` or `[]`.
   *
   *     var deepCss = { '.link': { '[target]': 42 }};
   *     expect(deepCss).to.have.deep.property('\\.link.\\[target\\]', 42);
   *
   * @name deep
   * @api public
   */

  Assertion.addProperty('deep', function () {
    flag(this, 'deep', true);
  });

  /**
   * ### .any
   *
   * Sets the `any` flag, (opposite of the `all` flag)
   * later used in the `keys` assertion.
   *
   *     expect(foo).to.have.any.keys('bar', 'baz');
   *
   * @name any
   * @api public
   */

  Assertion.addProperty('any', function () {
    flag(this, 'any', true);
    flag(this, 'all', false)
  });


  /**
   * ### .all
   *
   * Sets the `all` flag (opposite of the `any` flag)
   * later used by the `keys` assertion.
   *
   *     expect(foo).to.have.all.keys('bar', 'baz');
   *
   * @name all
   * @api public
   */

  Assertion.addProperty('all', function () {
    flag(this, 'all', true);
    flag(this, 'any', false);
  });

  /**
   * ### .include(value)
   *
   * The `include` and `contain` assertions can be used as either property
   * based language chains or as methods to assert the inclusion of an object
   * in an array or a substring in a string. When used as language chains,
   * they toggle the `contains` flag for the `keys` assertion.
   *
   *     expect([1,2,3]).to.include(2);
   *     expect('foobar').to.contain('foo');
   *     expect({ foo: 'bar', hello: 'universe' }).to.include.keys('foo');
   *
   * @name include
   * @alias contain
   * @alias includes
   * @alias contains
   * @param {Object|String|Number} obj
   * @param {String} message _optional_
   * @api public
   */

  function includeChainingBehavior () {
    flag(this, 'contains', true);
  }

  function include (val, msg) {
    if (msg) {
      flag(this, 'message', msg);
    }
    flag(this, 'contains', val);
    var obj = flag(this, 'attributeFlag') ||
      flag(this, 'textFlag') ||
      flag(this, 'cssFlag') ||
      flag(this, 'valueFlag');

    if (!obj) {
      throw new Error('Expect expression error: attribute, value or text is missing.');
    }
  }

  Assertion.addChainableMethod('include', include, includeChainingBehavior);
  Assertion.addChainableMethod('contain', include, includeChainingBehavior);
  Assertion.addChainableMethod('contains', include, includeChainingBehavior);
  Assertion.addChainableMethod('includes', include, includeChainingBehavior);

  function startWith (val, msg) {
    if (msg) {
      flag(this, 'message', msg);
    }
    flag(this, 'startsWith', val);

    var obj = flag(this, 'attributeFlag') ||
      flag(this, 'textFlag') ||
      flag(this, 'cssFlag') ||
      flag(this, 'valueFlag');

    if (!obj) {
      throw new Error('Expect expression error: attribute, value or text is missing.');
    }
  }

  Assertion.addMethod('startWith', startWith);
  Assertion.addMethod('startsWith', startWith);

  function endWidth (val, msg) {
    if (msg) {
      flag(this, 'message', msg);
    }
    flag(this, 'endsWidth', val);

    var obj = flag(this, 'attributeFlag') ||
      flag(this, 'textFlag') ||
      flag(this, 'cssFlag') ||
      flag(this, 'valueFlag');

    if (!obj) {
      throw new Error('Expect expression error: attribute, value or text is missing.');
    }
  }

  Assertion.addMethod('endWidth', endWidth);
  Assertion.addMethod('endsWidth', endWidth);
  /**
   * ### .match(regexp)
   *
   * Asserts that the target matches a regular expression.
   *
   *     expect('foobar').to.match(/^foo/);
   *
   * @name match
   * @param {RegExp} RegularExpression
   * @param {String} message _optional_
   * @api public
   */
  function matches(re, msg) {
    if (!(re instanceof RegExp)) {
      throw new Error('matches requires first paramter to be a RegExp. ' + (typeof re) + ' given.');
    }
    if (msg) {
      flag(this, 'message', msg);
    }
    flag(this, 'matches', re);

    var obj = flag(this, 'attributeFlag') ||
      flag(this, 'textFlag') ||
      flag(this, 'cssFlag') ||
      flag(this, 'valueFlag');

    if (!obj) {
      throw new Error('Expect expression error: attribute, value or text is missing.');
    }
  }

  Assertion.addMethod('match', matches);
  Assertion.addMethod('matches', matches);

  /**
   * ### .equal(value)
   *
   * Asserts that the target is strictly equal (`===`) to `value`.
   * Alternately, if the `deep` flag is set, asserts that
   * the target is deeply equal to `value`.
   *
   *     expect('hello').to.equal('hello');
   *     expect(42).to.equal(42);
   *     expect(1).to.not.equal(true);
   *     expect({ foo: 'bar' }).to.not.equal({ foo: 'bar' });
   *     expect({ foo: 'bar' }).to.deep.equal({ foo: 'bar' });
   *
   * @name equal
   * @alias equals
   * @alias eq
   * @alias deep.equal
   * @param {Mixed} value
   * @param {String} message _optional_
   * @api public
   */

  function assertEqual (val, msg) {
    if (msg) {
      flag(this, 'message', msg);
    }
    flag(this, 'equal', val);
  }

  Assertion.addMethod('equal', assertEqual);
  Assertion.addMethod('equals', assertEqual);
  Assertion.addMethod('eq', assertEqual);
};
