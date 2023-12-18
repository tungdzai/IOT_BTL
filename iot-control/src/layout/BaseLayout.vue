<template>
  <div class="base-layout">
    <div class="container">
      <div>
        <p>Nhiệt độ :{{ getDataDHT11.temperature}}</p>
        <p>Độ ẩm :{{ getDataDHT11.humidity}}</p>
      </div>
    </div>
    <fan-control/>
    <hr>
    <lamp-control/>
  </div>
</template>

<script>
import FanControl from "../components/FanControl.vue";
import LampControl from "../components/LampControl.vue";
import {mapGetters,mapActions} from "vuex";

export default {
  name: "BaseLayout",
  data() {
    return {
    };
  },
  mounted: function () {
    this.fetchDataDHT11();
  },
  computed: {
    ...mapGetters('homeControl', ['getDataDHT11']),
  },
  methods:{
    ...mapActions('homeControl', ['fetchDataDHT11']),
  },
  components: {
    FanControl,
    LampControl
  }
}
</script>

<style scoped>
.base-layout {
  max-width: 35%;
  margin: 0 auto;
  padding: 15px;
  background-color: #efdfc4;
  border-radius: 10px;
}
.container{
  display: flex;
  justify-content: space-between;
}

</style>