'use strict';

const stylelint = require('stylelint');
const postcss = require('postcss');
const _ = require('lodash');
const checkAlphabeticalOrder = require('../checkAlphabeticalOrder');

module.exports = function checkOrder(firstPropData, secondPropData, allPropData, sharedInfo) {
	if (firstPropData.unprefixedName === secondPropData.unprefixedName) {
		// If first property has no prefix and second property has prefix
		if (
			!postcss.vendor.prefix(firstPropData.name).length &&
			postcss.vendor.prefix(secondPropData.name).length
		) {
			return false;
		}

		return true;
	}

	const firstPropIsUnspecified = !firstPropData.orderData;
	const secondPropIsUnspecified = !secondPropData.orderData;

	// Check actual known properties
	if (!firstPropIsUnspecified && !secondPropIsUnspecified) {
		return firstPropData.orderData.expectedPosition <= secondPropData.orderData.expectedPosition;
	}

	if (firstPropIsUnspecified && !secondPropIsUnspecified) {
		// If first prop is unspecified, look for a specified prop before it to
		// compare to the current prop
		const priorSpecifiedPropData = _.findLast(allPropData.slice(0, -1), d => Boolean(d.orderData));

		if (
			priorSpecifiedPropData &&
			priorSpecifiedPropData.orderData &&
			priorSpecifiedPropData.orderData.expectedPosition > secondPropData.orderData.expectedPosition
		) {
			stylelint.utils.report({
				message: sharedInfo.messages.expected(secondPropData.name, priorSpecifiedPropData.name),
				node: secondPropData.node,
				result: sharedInfo.result,
				ruleName: sharedInfo.ruleName,
			});

			return true; // avoid logging another warning
		}
	}

	const unspecified = sharedInfo.unspecified;

	// Now deal with unspecified props
	// Starting with bottomAlphabetical as it requires more specific conditionals
	if (unspecified === 'bottomAlphabetical' && !firstPropIsUnspecified && secondPropIsUnspecified) {
		return true;
	}

	if (unspecified === 'bottomAlphabetical' && secondPropIsUnspecified && firstPropIsUnspecified) {
		if (checkAlphabeticalOrder(firstPropData, secondPropData)) {
			return true;
		}

		return false;
	}

	if (unspecified === 'bottomAlphabetical' && firstPropIsUnspecified) {
		return false;
	}

	if (firstPropIsUnspecified && secondPropIsUnspecified) {
		return true;
	}

	if (unspecified === 'ignore' && (firstPropIsUnspecified || secondPropIsUnspecified)) {
		return true;
	}

	if (unspecified === 'top' && firstPropIsUnspecified) {
		return true;
	}

	if (unspecified === 'top' && secondPropIsUnspecified) {
		return false;
	}

	if (unspecified === 'bottom' && secondPropIsUnspecified) {
		return true;
	}

	if (unspecified === 'bottom' && firstPropIsUnspecified) {
		return false;
	}
};
