<template>
  <div>
    <h1>求和:{{sum}}</h1>
    <h1>当前总人数: {{personList.length}}</h1>
    <h1>当前总人数: {{b}}</h1>
    <select v-model.number="num">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
    </select>
    <button @click="increment(num)">+</button>
    <button @click="decrement(num)">-</button>


    <button @click="addPerson">点击添加一个王五</button>

    <hr>
    <person-vue></person-vue>
  </div>
</template>

<script>
import {mapState,mapMutations,mapActions} from 'vuex';
import PersonVue from './PersonVue.vue';
export default {
    name: "CountVue",
    components:{PersonVue},
    data(){
        return {
            
        }
    },
    methods: {
        ...mapState('countAbout',['sum']),
        ...mapMutations('countAbout',['increment','decrement']),

        ...mapActions('personAbout',['addPerson']),
        
        
    },
    computed:{
       
        ...mapState('personAbout',['personList']),


        ...mapState('a',['b']),
        num:{
            get(){
                return this.$store.state.countAbout.num
            },
            set(val){
                this.$store.state.countAbout.num = val
            }
        }
    },
    mounted(){
        
    }
}
</script>

<style scoped>
    button {
        margin-left: 10px;
    }
</style>