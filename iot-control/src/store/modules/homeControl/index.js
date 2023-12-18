import api from "@/api";

const state = {
    dht11: [],
    fan:  [],
    lamp: [],
    lights: [],
    sounds: []
}

const getters = {
    getDataDHT11: (state) => state.dht11,
}

const mutations = {
    setDHT11Data(state, data) {
        state.dht11 = data;
    },
    setFanData(state, data) {
        state.fan = data;
        localStorage.setItem('fan', JSON.stringify(data));
    },
    setLampData(state, data) {
        state.lamp = data;
        localStorage.setItem('lamp', JSON.stringify(data));
    },
    setLightsData(state, data) {
        state.lights = data;
    },
    setSoundsData(state, data) {
        state.sounds = data;
    }
}
const actions = {
    async fetchDataDHT11({commit}) {
        try {
            api.getDHT11().then(response => {
                commit('setDHT11Data', response.data);
            }).catch(error => {
                console.error(error);
            });
        } catch (error) {
            console.error(error);
        }
    },
    async fetchDataFan({commit}) {
        try {
            api.getFan().then(response => {
                commit('setFanData', response.data);
            }).catch(error => {
                console.error(error);
            });
        } catch (error) {
            console.error(error);
        }
    },
    async fetchDataLamp({commit}) {
        try {
            api.getLamp().then(response => {
                commit('setLampData', response.data);
            }).catch(error => {
                console.error(error);
            });
        } catch (error) {
            console.error(error);
        }
    },
}
export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions
}