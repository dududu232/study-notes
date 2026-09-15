import Vuex from 'vuex';
import Vue from 'vue';


//必须在这use,否则报错
Vue.use(Vuex);

const countAbout = {
    namespaced: true,
    state: {
        sum: 0,
        num: 1
    },
    actions: {},
    mutations: {
        increment(state,value){
            state.sum += value;
        },
        decrement(state,value){
            state.sum -=value;
        }
    },
    getters: {}

}

const personAbout = {
    namespaced: true,
    state: {
        personList: [
            {id:1,name:"张三"},
            {id:2,name:"李四"}
        ]
    },
    actions: {
        addPerson(context,value){
            context.state.personList.push({id:3,name:'王五'})
            context.commit('addP',value)
        }
    },
    mutations: {
        addP(state,value){
            console.log(state,value)
        }
    },
    getters: {}

}

const testAbout = {
    namespaced: true,
    state: {
        b:1
    },
    'actions': {},
    'mutations': {},
    "getter": {}
}

//创建并暴露store
 export default new Vuex.Store({
    modules: {countAbout,personAbout,a:testAbout}
})
