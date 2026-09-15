<template>
  <div>
    <h1>当前求和为: {{$store.state.sum}}</h1>
    <h1>当前求和乘十为: {{$store.getters.bigSum}}</h1>

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
    }
}
</script>

<style scoped>
    button {
        margin-left: 10px;
    }
</style>