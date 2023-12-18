import Vue from 'vue'
import Vuex from 'vuex'
import homeControl from "./modules/homeControl";

Vue.use(Vuex)

const store = new Vuex.Store({

    state: {},
    getters: {},
    mutations: {},
    modules: {
        homeControl,
    }
})

export default store