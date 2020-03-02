'use strict';

const stylelint = require('stylelint');
const _ = require('lodash');
const addEmptyLineBefore = require('./addEmptyLineBefore');
const hasEmptyLineBefore = require('./hasEmptyLineBefore');
const removeEmptyLinesBefore = require('./removeEmptyLinesBefore');

module.exports = function checkEmptyLineBefore(firstPropData, secondPropData, sharedInfo) {
	const firstPropIsUnspecified = !firstPropData.orderData;
	const secondPropIsUnspecified = !secondPropData.orderData;

	// Now check newlines between ...
	const firstPropSeparatedGroup = !firstPropIsUnspecified
		? firstPropData.orderData.separatedGroup
		: sharedInfo.lastKnownSeparatedGroup;
	const secondPropSeparatedGroup = !secondPropIsUnspecified
		? secondPropData.orderData.separatedGroup
		: sharedInfo.lastKnownSeparatedGroup;

	if (firstPropSeparatedGroup !== secondPropSeparatedGroup && !secondPropIsUnspecified) {
		// Get an array of just the property groups, remove any solo properties
		const groups = _.reject(sharedInfo.expectation, _.isString);

		// secondProp seperatedGroups start at 2 so we minus 2 to get the 1st item
		// from our groups array
		const emptyLineBefore = _.get(groups[secondPropSeparatedGroup - 2], 'emptyLineBefore');

		if (!hasEmptyLineBefore(secondPropData.node) && emptyLineBefore === 'always') {
			if (sharedInfo.context.fix && !sharedInfo.disableFix) {
				addEmptyLineBefore(secondPropData.node, sharedInfo.context.newline);
			} else {
				stylelint.utils.report({
					message: sharedInfo.messages.expectedEmptyLineBefore(secondPropData.name),
					node: secondPropData.node,
					result: sharedInfo.result,
					ruleName: sharedInfo.ruleName,
				});
			}
		} else if (hasEmptyLineBefore(secondPropData.node) && emptyLineBefore === 'never') {
			if (sharedInfo.context.fix && !sharedInfo.disableFix) {
				removeEmptyLinesBefore(secondPropData.node, sharedInfo.context.newline);
			} else {
				stylelint.utils.report({
					message: sharedInfo.messages.rejectedEmptyLineBefore(secondPropData.name),
					node: secondPropData.node,
					result: sharedInfo.result,
					ruleName: sharedInfo.ruleName,
				});
			}
		}
	}

	sharedInfo.lastKnownSeparatedGroup = secondPropSeparatedGroup;
};
