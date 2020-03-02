<template>
  <header class="header">
    <div class="header__brand">
      <svg
        ref="nav__open"
        tabindex="0"
        @keyup.enter="openNavigation"
        @click="openNavigation"
        class="header__hamburger" 
        viewBox="0 0 512 512" 
        xmlns="http://www.w3.org/2000/svg">
        <path d="M424 394H89a8 8 0 0 1 0-16h335a8 8 0 0 1 0 16zM424 265H89a8 8 0 0 1 0-16h335a8 8 0 0 1 0 16zM424 135H89a8 8 0 0 1 0-16h335a8 8 0 0 1 0 16z"/>
      </svg>
      <span class="header__name"><img src="../assets/shopizer-cart.png"/><b>Technical documentation</b></span>
    </div>

    <nav class="nav">
	    <a id="ghBranchVersion" class="download" href="https://github.com/shopizer-ecommerce/shopizer"></a>
    </nav>
  </header>
</template>

<script>
import ConfigManager from '../services/configManager'

export default {
  name: 'header-component',
  data () {
    return {
      isVisible: false,
      isDesktop: false,
      name: ConfigManager.getBaseConfig().appName,
      list: ConfigManager.getBaseConfig().headerNavigation
    }
  },
  created: function () {
    if (window.innerWidth >= 780) {
      this.isDesktop = true
    }
    window.addEventListener('resize', this.handleResize)
  },
  beforeDestroy: function () {
    window.removeEventListener('resize', this.handleResize)
  },
  methods: {
    handleResize (event) {
      if (event.currentTarget.innerWidth >= 780) {
        this.isDesktop = true
      } else {
        this.isDesktop = false
      }
    },
    openNavigation () {
      this.$emit('toggleMenu')
    },
    closeNavigation (event) {
      this.$emit('toggleMenu')
    }
  }
}
</script>
