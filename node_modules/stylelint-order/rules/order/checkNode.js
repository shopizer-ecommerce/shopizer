'use strict';

const stylelint = require('stylelint');
const _ = require('lodash');
const checkOrder = require('./checkOrder');
const getOrderData = require('./getOrderData');

module.exports = function checkNode(node, sharedInfo) {
	// Skip if it's an empty rule
	if (!node.nodes || !node.nodes.length) {
		return;
	}

	const allNodesData = [];

	node.each(function processEveryNode(child) {
		// Skip comments
		if (child.type === 'comment') {
			return;
		}

		// Receive node description and expectedPosition
		const nodeOrderData = getOrderData(sharedInfo.expectedOrder, child);

		const nodeData = {
			description: nodeOrderData.description,
			node: child,
		};

		if (nodeOrderData.expectedPosition) {
			nodeData.expectedPosition = nodeOrderData.expectedPosition;
		}

		const previousNodeData = _.last(allNodesData);

		allNodesData.push(nodeData);

		// Skip first node
		if (!previousNodeData) {
			return;
		}

		const isCorrectOrder = checkOrder(previousNodeData, nodeData, allNodesData, sharedInfo);

		if (isCorrectOrder) {
			return;
		}

		stylelint.utils.report({
			message: sharedInfo.messages.expected(nodeData.description, previousNodeData.description),
			node: child,
			result: sharedInfo.result,
			ruleName: sharedInfo.ruleName,
		});
	});
};
