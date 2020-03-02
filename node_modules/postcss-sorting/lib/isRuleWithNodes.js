'use strict';

module.exports = function isRuleWithNodes(node) {
	return (node.type === 'rule' || node.type === 'atrule') && node.nodes && node.nodes.length;
};
