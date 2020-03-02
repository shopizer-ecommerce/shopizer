
function nodeIterator (context, method, ...args) {
  context.forEach(node => node[method](...args))
}

export default class Selection extends Array {
  constructor (tree, items = []) {
    /*eslint semi: 0 */
    super();

    this.tree = tree;
    this.push(...items)
  }

  remove () {
    nodeIterator(this, 'remove')
    return this
  }

  expand () {
    nodeIterator(this, 'expand')
    return this
  }

  collapse () {
    nodeIterator(this, 'collapse')
    return this
  }

  select (extendList) {
    nodeIterator(this, 'select', extendList)
    return this
  }

  unselect () {
    nodeIterator(this, 'unselect')
    return this
  }

  check () {
    if (this.tree.options.checkbox) {
      nodeIterator(this, 'check')
    }

    return this
  }

  uncheck () {
    if (this.tree.options.checkbox) {
      nodeIterator(this, 'uncheck')
    }

    return this
  }
}
