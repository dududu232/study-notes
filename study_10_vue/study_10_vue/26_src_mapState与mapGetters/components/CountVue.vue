<template>
  <div>
    <h1>当前求和为: {{sum}}</h1>
    <h1>当前求和乘十为: {{bigSum}}</h1>

    <select v-model.number="$store.state.num">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
    </select>
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
    <demo-vue></demo-vue>
  </div>
</template>

<script>
import DemoVue from "./DemoVue"
import {mapState,mapGetters} from 'vuex';
export default {
    name: "CountVue",
    components:{DemoVue},
    data(){
        return {
            
        }
    },
    methods: {
        increment(){
           this.$store.dispatch('jia',this.$store.state.num)
        },
        decrement(){
            this.$store.dispatch('jian',this.$store.state.num)
        },
        incrementOdd(){
            
        },
        incrementWait(){
            setTimeout(()=>{
                
            },500)
        }
    },
    computed:{
        //mapState对象写法   用于computed中的属性名与 state 属性名不同
        // ...mapState({sum:'sum'}),
        //mapState数组写法    用于computed中的属性名与 state 属性名相同
        ...mapState(['sum']),

        //mapGetters   用于computed中的属性名与 getter 属性名不同
        // ...mapGetters({bigSum:'bigSum'}),
        //mapGetters   用于computed中的属性名与 getter 属性名相同
        ...mapGetters(['bigSum'])
    }
}
</script>

<style scoped>
    button {
        margin-left: 10px;
    }
</style>