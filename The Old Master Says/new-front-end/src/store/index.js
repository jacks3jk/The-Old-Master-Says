import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */

export default new Vuex.Store({
  state: {
  },
  mutations: {
  }
})