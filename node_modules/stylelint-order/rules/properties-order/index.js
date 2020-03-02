'use strict';

const stylelint = require('stylelint');
const _ = require('lodash');
const postcssSorting = require('postcss-sorting');
const utils = require('../../utils');
const checkNode = require('./checkNode');
const createExpectedOrder = require('./createExpectedOrder');
const createFlatOrder = require('./createFlatOrder');
const validatePrimaryOption = require('./validatePrimaryOption');

const ruleName = utils.namespace('properties-order');

const messages = stylelint.utils.ruleMessages(ruleName, {
	expected: (first, second) => `Expected "${first}" to come before "${second}"`,
	expectedEmptyLineBefore: property => `Expected an empty line before property "${property}"`,
	rejectedEmptyLineBefore: property => `Unexpected an empty line before property "${property}"`,
});

const rule = function(expectation, options, context) {
	context = context || {};

	return function(root, result) {
		const validOptions = stylelint.utils.validateOptions(
			result,
			ruleName,
			{
				actual: expectation,
				possible: validatePrimaryOption,
			},
			{
				actual: options,
				possible: {
					unspecified: ['top', 'bottom', 'ignore', 'bottomAlphabetical'],
					disableFix: _.isBoolean,
				},
				optional: true,
			}
		);

		if (!validOptions) {
			return;
		}

		// By default, ignore unspecified properties
		let unspecified = _.get(options, ['unspecified'], 'ignore');

		const disableFix = _.get(options, ['disableFix'], false);

		if (context.fix && !disableFix) {
			if (unspecified === 'ignore') {
				unspecified = 'bottom';
			}

			const sortingOptions = {
				'properties-order': createFlatOrder(expectation),
				'unspecified-properties-position': unspecified,
			};

			postcssSorting(sortingOptions)(root);
		}

		const expectedOrder = createExpectedOrder(expectation);

		const sharedInfo = {
			expectedOrder,
			expectation,
			unspecified,
			messages,
			ruleName,
			result,
			context,
			disableFix,
		};

		// Check all rules and at-rules recursively
		root.walk(function processRulesAndAtrules(node) {
			if (node.type === 'rule' || node.type === 'atrule') {
				checkNode(node, sharedInfo);
			}
		});
	};
};

rule.primaryOptionArray = true;

rule.ruleName = ruleName;
rule.messages = messages;
module.exports = rule;
