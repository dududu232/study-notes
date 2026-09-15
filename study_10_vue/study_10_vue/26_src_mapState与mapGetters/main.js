import Vue from 'vue';
import App from './App.vue';
/*引入插件*/
import plugins from "@/plugins";
//引入store
import store from './store';

Vue.config.productionTip = false

/*使用插件*/
Vue.use(plugins);

// console.log(store)
 new Vue({
  
  render: h => h(App),
  store,
}).$mount('#app')
