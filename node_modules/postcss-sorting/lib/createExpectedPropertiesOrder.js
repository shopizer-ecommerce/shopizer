'use strict';

const _ = require('lodash');

module.exports = function createExpectedPropertiesOrder(input) {
	const order = {};

	if (_.isPlainObject(input[0])) {
		let propertyIndex = 0;

		input.forEach((group, groupIndex) => {
			group.properties.forEach(property => {
				order[property] = {
					propertyIndex,
					groupIndex,
					emptyLineBefore: group.emptyLineBefore,
				};

				propertyIndex++;
			});
		});
	} else {
		input.forEach((property, propertyIndex) => {
			order[property] = {
				propertyIndex,
			};
		});
	}

	return order;
};
