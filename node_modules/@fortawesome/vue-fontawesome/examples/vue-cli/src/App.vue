<template>
  <div id="app">
    <main class="w-100 min-vh-100 bg-gray8 white sans-serif pa6 flex flex-column justify-center items-center">
      <div class="mw8 center overflow-hidden">
        <h2 class="tc ttu tracked3 b f2 mt0 mb2 teal0 o-30">react-fontawesome</h2>

        <ul class="list ma0 pa0 flex flex-row flex-wrap teal4">
          <li class="pv3 ph2 ma0 link grow">
            <font-awesome-icon icon="coffee" size="4x" />
          </li>
          <li class="pv3 ph2 ma0 link grow">
            <font-awesome-icon :icon="['fas', 'coffee']" flip="horizontal" size="4x" />
          </li>
          <li class="pv3 ph2 ma0 link grow">
            <font-awesome-icon :icon="['far', 'comment']" size="4x" />
          </li>
          <li class="pv3 ph2 ma0 link grow">
            <font-awesome-icon icon="child" transform="shrink-4 down-2 right-2" :mask="['fas', 'circle']" size="4x" />
          </li>
          <li class="pv3 ph2 ma0 link grow">
            <font-awesome-icon :icon="['fab', 'twitter']" size="4x" />
          </li>
          <li class="pv3 ph2 ma0 link grow">
            <font-awesome-icon :icon="queen" size="4x" />
          </li>
          <li class="pv3 ph2 ma0 link grow">
            <font-awesome-layers full-width class="fa-4x">
              <font-awesome-icon :icon="['fas', 'archive']"/>
              <font-awesome-layers-text class="red8" transform="down-3 shrink-14" value="SECRET" />
            </font-awesome-layers>
          </li>
        </ul>
      </div>
    </main>
  </div>
</template>

<script>
import { faChessQueen } from '@fortawesome/free-solid-svg-icons'

export default {
  name: 'App',

  computed: {
    queen () {
      return faChessQueen
    }
  }
}
</script>
