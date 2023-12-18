<template>
  <div class="fan-control">
    <h3> Điều khiển quạt</h3>
    <ul>
      <li>
        <p>Thủ công</p>
        <div class="btn-fan">
          <input type="checkbox" id="checkbox1" v-model="isManualMode" @change="toggleManualMode"/>
          <label for="checkbox1"></label>
        </div>
      </li>
      <li>
        <p>Âm thanh</p>
        <div class="btn-fan">
          <input type="checkbox" id="checkbox2" v-model="isSoundMode" @change="toggleSoundMode"/>
          <label for="checkbox2"></label>
        </div>
      </li>
      <li>
        <p>Cảm biến</p>
        <div class="btn-fan">
          <input type="checkbox" id="checkbox3" v-model="isSensorMode" @change="toggleSensorMode"/>
          <label for="checkbox3"></label>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {mapActions, mapState} from 'vuex';
import api from "@/api";

export default {
  name: "FanControl",
  data() {
    return {
      isManualMode: false,
      isSoundMode: false,
      isSensorMode: false
    }
  },
  mounted: function () {
    this.fetchDataFan();
    this.loadData();
  },
  computed: {
    ...mapState('homeControl', ['fan']),
  },
  watch: {},
  methods: {
    ...mapActions('homeControl', ['fetchDataFan']),
    loadData() {
      this.fetchDataFan();
      const fanStateFromLocalStorage = localStorage.getItem('fan');
      if (fanStateFromLocalStorage) {
        const fanStateObject = JSON.parse(fanStateFromLocalStorage);
        const operationMode = fanStateObject.operationMode;
        const manualMode = fanStateObject.manualMode;
        if (operationMode === "MANUAL" && manualMode === "OFF") {
          this.isManualMode = true;
        } else if (operationMode === "SOUND" && manualMode === "ON") {
          this.isSoundMode = true;
        }if (operationMode === "SENSOR" && manualMode === "OFF") {
          this.isSensorMode = true;
        }
      }
    },
    toggleManualMode() {
      const requestData = {
        operationMode: "MANUAL",
        manualMode: this.isManualMode ? "OFF" : "ON"
      };
      this.isSoundMode = false;
      this.isSensorMode = false;
      api.fanControl(requestData).then(response => {
        console.log(response.data);
      }).catch(error => {
        console.error(error);
      });
    },
    toggleSoundMode() {
      const requestData = {
        operationMode: "SOUND",
        manualMode: this.isSoundMode ? "ON" : "OFF"
      };
      this.isManualMode = false;
      this.isSensorMode = false;
      api.fanControl(requestData).then(response => {
        console.log(response.data);
      }).catch(error => {
        console.error(error);
      });

    },
    toggleSensorMode() {
      const requestData = {
        operationMode: "SENSOR",
        manualMode: this.isSensorMode ? "OFF" : "ON"
      };
      this.isManualMode = false;
      this.isSoundMode = false;
      api.fanControl(requestData).then(response => {
        console.log(response.data);
      }).catch(error => {
        console.error(error);
      });
    },

  }
}
</script>

<style lang="scss" scoped>
ul {
  list-style: none;
}

ul li {
  display: flex;
  align-items: center;
  width: 50%;
  justify-content: space-around;
}

.btn-fan {
  display: flex;
  align-items: center;

  input {
    display: block;
    opacity: 0
  }

  label {
    position: relative;
    width: 54px;
    height: 26px;
    display: inline-block;
    background: #666666;
    border-radius: 30px;
    cursor: pointer;
    transition: all 0.3s;
    -moz-transition: all 0.3s;
    -webkit-transition: all 0.3s;
  }

  label:after {
    content: "";
    position: absolute;
    left: 2px;
    top: 2px;
    width: 20px;
    height: 21px;
    background: #FFF;
    border-radius: 50%;
    box-shadow: 1px 3px 6px #666666;
  }

  input:checked + label {
    background: #009900;
  }

  input:checked + label:after {
    left: auto;
    right: 2px
  }
}
</style>