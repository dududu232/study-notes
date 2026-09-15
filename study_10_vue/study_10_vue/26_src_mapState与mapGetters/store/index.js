import Vuex from 'vuex';
import actions from './actions'
import mutations from './mutations';
import state from './state';
import getters from './getters';
import Vue from 'vue';

//必须在这use,否则报错
Vue.use(Vuex);



//创建并暴露store
 export default new Vuex.Store({
    actions,  //响应组件中的一些动作
    mutations,   //用于操作数据
    state,     //用于存储数据
    getters    //加工state中的数据
})
