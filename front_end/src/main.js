import Vue from 'vue'
import App from './App'
import router from './router'
import store from "./store";
import Vuetify from "vuetify";
import vuetify from '@/plugins/vuetify'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
Vue.config.productionTip = false

/* eslint-disable no-new */
Vue.use(Vuetify);
new Vue({
  el: '#app',
  vuetify: new Vuetify(),
  router,
  store,
  render: h => h(App)
}).$mount('#app')

