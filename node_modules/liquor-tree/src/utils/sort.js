
function orderAsc (node0, node1) {
  if (node0.text < node1.text) {
    return -1
  }

  if (node0.text > node1.text) {
    return 1
  }

  return 0
}

function orderDesc (node0, node1) {
  if (node0.text < node1.text) {
    return 1
  }

  if (node0.text > node1.text) {
    return -1
  }

  return 0
}

function getCompareFunction (order) {
  switch (order.toLowerCase()) {
    case 'asc': return orderAsc
    case 'desc': return orderDesc
  }
}

export default (source, compareFunction) => {
  if (typeof compareFunction === 'string') {
    compareFunction = getCompareFunction(compareFunction)
  }

  if (Array.isArray(source) && typeof compareFunction === 'function') {
    source.sort(compareFunction)
  }
}
