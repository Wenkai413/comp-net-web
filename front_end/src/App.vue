<template>
  <div id="app" >
    <!--      <main-tab-bar style="z-index: 100"></main-tab-bar>-->
    <v-app>
      <main-tab-bar style="z-index: 100" v-show="isLogging()"></main-tab-bar>
      <router-view></router-view>
    </v-app>
  </div>
</template>

<script>
import MainTabBar from "./components/content/mainTabbar/MainTabBar";
export default {
  name: 'App',
  components: {
    MainTabBar
  },
  methods:{
    isLogging(){
      let path = this.$route.path;
      console.log(path);
      return path!=="/login" && path!=="/signUp";
    }
  },

}
</script>

<style>
@import "./assets/css/base.css";
</style>
