import objectToNode from '@/utils/objectToNode'

/**
  Every Node has certain format:
  {
    id,           // Unique Node id. By default it generates using uuidV4
    text,         // Node text
    children,     // List of children. Each children has the same format
    parent,       // Parent Node or null. The tree is able to have more than 1 root node
    state,        // States of Node. Ex.: selected, checked and so on
    data          // Any types of data. It is similar to `storage`.
                  // Ex.: data: {myAwesomeProperty: 10}. To get this property you need: Node.data('myAwesomeProperty')
  }
*/

const defaultPropertyNames = {
  id: 'id',
  text: 'text',
  children: 'children',
  state: 'state',
  data: 'data',
  isBatch: 'isBatch'
}

function convertNames (obj, names) {
  return {
    id: obj[names.id],
    text: obj[names.text],
    children: obj[names.children],
    state: obj[names.state],
    data: obj[names.data],
    isBatch: obj[names.isBatch]
  }
}

const TreeParser = {
  parse (data, tree, options = {}) {
    if (typeof data === 'string') {
      data = JSON.parse(data)
    }

    if (!Array.isArray(data)) {
      data = [data]
    }

    const p = Object.assign(
      {},
      defaultPropertyNames,
      options
    )

    const preparedItems = data.map(function converter (item) {
      const convertedItem = convertNames(item, p)

      // Possible to receive 1 child like a simple object. It must be converted to an array
      // We do not have checks on the correctness of the format. A developer should pass correct format
      if (convertedItem.children && !Array.isArray(convertedItem.children)) {
        convertedItem.children = [convertedItem.children]
      }

      if (convertedItem.children) {
        convertedItem.children = convertedItem.children.map(converter)
      }

      return convertedItem
    })

    return preparedItems.map(item => objectToNode(tree, item))
  }
}

export { TreeParser }
