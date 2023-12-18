<template>
  <div class="lamp-control">
    <h3> Điều khiển đèn</h3>
    <ul>
      <li>
        <p>Thủ công</p>
        <div class="btn-lamp">
          <input type="checkbox" id="checkbox4"  v-model="isManualModeLamp" @change="toggleManualModeLamp" />
          <label for="checkbox4"></label>
        </div>
      </li>
      <li>
        <p>Âm thanh</p>
        <div class="btn-lamp">
          <input type="checkbox" id="checkbox5" v-model="isSoundModeLamp" @change="toggleSoundModeLamp"/>
          <label for="checkbox5"></label>
        </div>
      </li>
      <li>
        <p>Cảm biến</p>
        <div class="btn-lamp">
          <input type="checkbox" id="checkbox6" v-model="isSensorModeLamp" @change="toggleSensorModeLamp"/>
          <label for="checkbox6"></label>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {mapActions, mapState} from 'vuex';
import api from "@/api";
export default {
  name: "LampControl",
  data() {
    return {
      isManualModeLamp: false,
      isSoundModeLamp: false,
      isSensorModeLamp: false
    }
  },
  mounted() {
    this.fetchDataLamp();
    this.loadDataLamp();
  },
  computed: {
    ...mapState('homeControl', ['lamp']),
  },
  watch: {},
  methods: {
    ...mapActions('homeControl', ['fetchDataLamp']),
    loadDataLamp() {
      this.fetchDataLamp();
      const lampStateFromLocalStorage = localStorage.getItem('lamp');
      if (lampStateFromLocalStorage) {
        const lampStateObject = JSON.parse(lampStateFromLocalStorage);
        const operationMode = lampStateObject.operationMode;
        const manualMode = lampStateObject.manualMode;
        if (operationMode === "MANUAL" && manualMode === "OFF") {
          this.isManualModeLamp = true;
        } else if (operationMode === "SOUND" && manualMode === "ON") {
          this.isSoundModeLamp = true;
        }if (operationMode === "SENSOR" && manualMode === "OFF") {
          this.isSensorModeLamp = true;
        }
      }
    },
    toggleManualModeLamp() {
      const requestData = {
        operationMode: "MANUAL",
        manualMode: this.isManualModeLamp ? "OFF" : "ON"
      };
      this.isSoundModeLamp = false;
      this.isSensorModeLamp = false;
      api.lampControl(requestData).then(response => {
        console.log(response.data);
      }).catch(error => {
        console.error(error);
      });
    },
    toggleSoundModeLamp() {
      const requestData = {
        operationMode: "SOUND",
        manualMode: this.isSoundModeLamp ? "ON" : "OFF"
      };
      this.isManualModeLamp = false;
      this.isSensorModeLamp = false;
      api.lampControl(requestData).then(response => {
        console.log(response.data);
      }).catch(error => {
        console.error(error);
      });

    },
    toggleSensorModeLamp() {
      const requestData = {
        operationMode: "SENSOR",
        manualMode: this.isSensorModeLamp ? "OFF" : "ON"
      };
      this.isManualModeLamp = false;
      this.isSoundModeLamp = false;
      api.lampControl(requestData).then(response => {
        console.log(response.data);
      }).catch(error => {
        console.error(error);
      });
    },

  }
}
</script>

<style lang="scss" scoped>
ul{
  list-style: none;
}
ul li{
  display: flex;
  align-items: center;
  width: 50%;
  justify-content: space-around;
}
.btn-lamp{
  display: flex;
  align-items: center;
  input{
    display: block;
    opacity: 0
  }
  label{
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
  label:after{
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
  input:checked + label{
    background: #009900;
  }
  input:checked + label:after{
    left: auto;
    right: 2px
  }
}
</style>