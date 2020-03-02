'use strict';

const _ = require('lodash');

module.exports = function createExpectedOrder(input) {
	const order = {};
	let expectedPosition = 0;
	let separatedGroup = 1;

	appendGroup(input);

	function appendGroup(items) {
		items.forEach(item => appendItem(item, false));
	}

	function appendItem(item, inFlexibleGroup) {
		if (_.isString(item)) {
			// In flexible groups, the expectedPosition does not ascend
			// to make that flexibility work;
			// otherwise, it will always ascend
			if (!inFlexibleGroup) {
				expectedPosition += 1;
			}

			order[item] = { separatedGroup, expectedPosition };

			return;
		}

		// If item is not a string, it's a group...
		if (item.emptyLineBefore) {
			separatedGroup += 1;
		}

		if (item.order && item.order === 'flexible') {
			expectedPosition += 1;

			item.properties.forEach(property => {
				appendItem(property, true);
			});
		} else {
			appendGroup(item.properties);
		}
	}

	return order;
};
