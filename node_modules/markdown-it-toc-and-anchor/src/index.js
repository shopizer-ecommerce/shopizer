import clone from "clone"
import uslug from "uslug"

const TOC = "@[toc]"
const TOC_RE = /^@\[toc\]/im

let markdownItSecondInstance = () => {}
let Token = () => {}
let headingIds = {}
let tocHtml = ""

const repeat = (string, num) => new Array(num + 1).join(string)

const makeSafe = (string, headingIds) => {
  const key = uslug(string) // slugify
  if (!headingIds[key]) {
    headingIds[key] = 0
  }
  headingIds[key]++
  return key + (
    headingIds[key] > 1 ? `-${headingIds[key]}` : ""
  )
}

const space = () => {
  return { ...(new Token("text", "", 0)), content: " " }
}

const renderAnchorLinkSymbol = (options) => {
  if (options.anchorLinkSymbolClassName) {
    return [
      {
        ...(new Token("span_open", "span", 1)),
        attrs: [
          [ "class", options.anchorLinkSymbolClassName ],
        ],
      },
      {
        ...(new Token("text", "", 0)),
        content: options.anchorLinkSymbol,
      },
      new Token("span_close", "span", -1),
    ]
  }
  else {
    return [
      {
        ...(new Token("text", "", 0)),
        content: options.anchorLinkSymbol,
      },
    ]
  }
}

const renderAnchorLink = (anchor, options, tokens, idx) => {
  const linkTokens = [
    {
      ...(new Token("link_open", "a", 1)),
      attrs: [
        [ "class", options.anchorClassName ],
        [ "href", `#${anchor}` ],
      ],
    },
    ...(renderAnchorLinkSymbol(options)),
    new Token("link_close", "a", -1),
  ]

  // `push` or `unshift` according to anchorLinkBefore option
  // space is at the opposite side.
  const actionOnArray = {
    false: "push",
    true: "unshift",
  }

  // insert space between anchor link and heading ?
  if (options.anchorLinkSpace) {
    linkTokens[actionOnArray[!options.anchorLinkBefore]](space())
  }
  tokens[idx + 1].children[
    actionOnArray[options.anchorLinkBefore]
  ](...linkTokens)
}

const treeToMarkdownBulletList = (tree, indent = 0) => tree.map(item => {
  const indentation = "  "
  let node = `${ repeat(indentation, indent) }*`
  if (item.heading.content) {
    node += " " +
            `[${ item.heading.content }](#${ item.heading.anchor })\n`
  }
  else {
    node += "\n"
  }
  if (item.nodes.length) {
    node += treeToMarkdownBulletList(item.nodes, indent + 1)
  }
  return node
}).join("")

const generateTocMarkdownFromArray = (headings, options) => {
  const tree = { nodes: [] }
  // create an ast
  headings.forEach(heading => {
    if (heading.level < options.tocFirstLevel
        || heading.level > options.tocLastLevel) {
      return
    }

    let i = 1
    let lastItem = tree
    for (; i < heading.level - options.tocFirstLevel + 1; i++) {
      if (lastItem.nodes.length === 0) {
        lastItem.nodes.push({
          heading: {},
          nodes: [],
        })
      }
      lastItem = lastItem.nodes[lastItem.nodes.length - 1]
    }
    lastItem.nodes.push({
      heading: heading,
      nodes: [],
    })
  })

  return treeToMarkdownBulletList(tree.nodes)
}

export default function(md, options) {
  options = {
    toc: true,
    tocClassName: "markdownIt-TOC",
    tocFirstLevel: 1,
    tocLastLevel: 6,
    tocCallback: null,
    anchorLink: true,
    anchorLinkSymbol: "#",
    anchorLinkBefore: true,
    anchorClassName: "markdownIt-Anchor",
    resetIds: true,
    anchorLinkSpace: true,
    anchorLinkSymbolClassName: null,
    ...options,
  }

  markdownItSecondInstance = clone(md)

  // initialize key ids for each instance
  headingIds = {}

  md.core.ruler.push("init_toc", function(state) {
    Token = state.Token
    const tokens = state.tokens

    // reset key ids for each document
    if (options.resetIds) {
      headingIds = {}
    }

    const tocArray = []
    let tocMarkdown = ""
    let tocTokens = []

    for (let i = 0; i < tokens.length; i++) {
      if (tokens[i].type !== "heading_close") {
        continue
      }

      const heading = tokens[i - 1]
      const heading_close = tokens[i]

      if (heading.type === "inline") {
        let content
        if (heading.children && heading.children[0].type === "link_open") {
          // headings that contain links have to be processed
          // differently since nested links aren't allowed in markdown
          content = heading.children[1].content
          heading._tocAnchor = makeSafe(content, headingIds)
        }
        else {
          content = heading.content
          heading._tocAnchor = makeSafe(heading.children
              .reduce((acc, t) => acc + t.content, ""), headingIds)
        }

        tocArray.push({
          content,
          anchor: heading._tocAnchor,
          level: +heading_close.tag.substr(1, 1),
        })
      }
    }

    tocMarkdown = generateTocMarkdownFromArray(tocArray, options)

    tocTokens = markdownItSecondInstance.parse(tocMarkdown, {})

    // Adding tocClassName to 'ul' element
    if (typeof tocTokens[0] === "object" &&
        tocTokens[0].type === "bullet_list_open") {
      const attrs = tocTokens[0].attrs = tocTokens[0].attrs || []
      attrs.push([ "class", options.tocClassName ])
    }

    tocHtml = markdownItSecondInstance.renderer.render(
        tocTokens, markdownItSecondInstance.options
    )

    if (typeof state.env.tocCallback === "function") {
      state.env.tocCallback.call(undefined, tocMarkdown, tocArray, tocHtml)
    }
    else if (typeof options.tocCallback === "function") {
      options.tocCallback.call(undefined, tocMarkdown, tocArray, tocHtml)
    }
    else if (typeof md.options.tocCallback === "function") {
      md.options.tocCallback.call(undefined, tocMarkdown, tocArray, tocHtml)
    }
  })

  md.inline.ruler.after(
    "emphasis",
    "toc",
    (state, silent) => {

      let token
      let match

      while (
        state.src.indexOf("\n") >= 0 &&
        state.src.indexOf("\n") < state.src.indexOf(TOC)
      ) {
        if (state.tokens.slice(-1)[0].type === "softbreak") {
          state.src = state.src.split("\n").slice(1).join("\n")
          state.pos = 0
        }
      }

      if (
        // Reject if the token does not start with @[
        state.src.charCodeAt(state.pos) !== 0x40 ||
        state.src.charCodeAt(state.pos + 1) !== 0x5B ||

        // Don’t run any pairs in validation mode
        silent
      ) {
        return false
      }

      // Detect TOC markdown
      match = TOC_RE.exec(state.src)
      match = !match ? [] : match.filter((m) => m)
      if (match.length < 1) {
        return false
      }

      // Build content
      token = state.push("toc_open", "toc", 1)
      token.markup = TOC
      token = state.push("toc_body", "", 0)
      token = state.push("toc_close", "toc", -1)

      // Update pos so the parser can continue
      const newline = state.src.indexOf("\n")
      if (newline !== -1) {
        state.pos = state.pos + newline
      }
      else {
        state.pos = state.pos + state.posMax + 1
      }

      return true
    }
  )

  const originalHeadingOpen = md.renderer.rules.heading_open ||
    function(...args) {
      const [ tokens, idx, options, , self ] = args
      return self.renderToken(tokens, idx, options)
    }

  md.renderer.rules.heading_open = function(...args) {
    const [ tokens, idx, , , ] = args

    const attrs = tokens[idx].attrs = tokens[idx].attrs || []
    const anchor = tokens[idx + 1]._tocAnchor
    attrs.push([ "id", anchor ])

    if (options.anchorLink) {
      renderAnchorLink(anchor, options, ...args)
    }

    return originalHeadingOpen.apply(this, args)
  }

  md.renderer.rules.toc_open = () => ""
  md.renderer.rules.toc_close = () => ""
  md.renderer.rules.toc_body = () => ""

  if (options.toc) {
    md.renderer.rules.toc_body =
      () => tocHtml
  }
}
