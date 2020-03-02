<template>
  <div class="markdown-body">
    <markdown></markdown>
  </div>
</template>

<script>
import markdown from './markdown.md'
import 'highlight.js/styles/github.css'
import 'github-markdown-css'

export default {
  components: {
    markdown
  }
}
</script>
<style>
  .markdown-body {
    box-sizing: border-box;
    min-width: 200px;
    max-width: 980px;
    margin: 0 auto;
    padding: 45px;
  }
</style>