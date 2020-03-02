'use strict';

const stylelint = require('stylelint');
const postcss = require('postcss');
const _ = require('lodash');
const utils = require('../../utils');
const checkEmptyLineBefore = require('./checkEmptyLineBefore');
const checkOrder = require('./checkOrder');

module.exports = function checkNode(node, sharedInfo) {
	// Skip if it's an empty rule
	if (!node.nodes || !node.nodes.length) {
		return;
	}

	const allPropData = [];
	const allNodesData = [];

	sharedInfo.lastKnownSeparatedGroup = 1;

	node.each(function processEveryNode(child) {
		let nodeData = {
			node: child,
		};

		if (child.type === 'decl') {
			const prop = child.prop;

			if (utils.isStandardSyntaxProperty(prop) && !utils.isCustomProperty(prop)) {
				let unprefixedPropName = postcss.vendor.unprefixed(prop);

				// Hack to allow -moz-osx-font-smoothing to be understood
				// just like -webkit-font-smoothing
				if (unprefixedPropName.indexOf('osx-') === 0) {
					unprefixedPropName = unprefixedPropName.slice(4);
				}

				nodeData = {
					name: prop,
					unprefixedName: unprefixedPropName,
					orderData: sharedInfo.expectedOrder[unprefixedPropName],
					node: child,
				};

				allPropData.push(nodeData);
			}
		}

		allNodesData.push(nodeData);

		// current node should be a standard declaration
		if (
			child.type !== 'decl' ||
			!utils.isStandardSyntaxProperty(nodeData.node.prop) ||
			utils.isCustomProperty(nodeData.node.prop)
		) {
			return;
		}

		// First, check order
		const previousPropData = _.nth(allPropData, -2);

		// Skip first decl
		if (previousPropData && (!sharedInfo.context.fix || sharedInfo.disableFix)) {
			const isCorrectOrder = checkOrder(previousPropData, nodeData, allPropData, sharedInfo);

			if (!isCorrectOrder) {
				stylelint.utils.report({
					message: sharedInfo.messages.expected(nodeData.name, previousPropData.name),
					node: child,
					result: sharedInfo.result,
					ruleName: sharedInfo.ruleName,
				});
			}
		}

		// Second, check emptyLineBefore
		let previousNodeData = _.nth(allNodesData, -2);

		// if previous node is shared-line comment, use second previous node
		if (
			previousNodeData &&
			previousNodeData.node.type === 'comment' &&
			previousNodeData.node.raw('before').indexOf('\n') === -1
		) {
			previousNodeData = _.nth(allNodesData, -3);
		}

		// skip first decl
		if (!previousNodeData) {
			return;
		}

		if (previousNodeData.node.type !== 'decl') {
			return;
		}

		// previous node should be a standard declaration
		if (
			!utils.isStandardSyntaxProperty(previousNodeData.node.prop) ||
			utils.isCustomProperty(previousNodeData.node.prop)
		) {
			return;
		}

		checkEmptyLineBefore(previousPropData, nodeData, sharedInfo);
	});
};
