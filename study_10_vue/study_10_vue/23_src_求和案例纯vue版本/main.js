import Vue from 'vue'
import App from './App.vue'
/*引入插件*/
import plugins from "@/plugins";
import Vuex from 'vuex';

Vue.config.productionTip = false

/*使用插件*/
Vue.use(plugins);
Vue.use(Vuex);

new Vue({
  render: h => h(App),
}).$mount('#app')
