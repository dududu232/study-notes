<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <!--销毁当前组件的实例对象时，该组件及该组件的子组件自定义事件将失效-->
    <!--直接绑定-->
<!--    <HelloWorld msg="Welcome to Your Vue.js App" v-on:zhaijiajia="getName"/>-->

    <!--动态绑定事件-->
    <HelloWorld msg="Welcome to Your Vue.js App"  ref="helloWorld"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data(){
    return{
      a:1
    }
  },
  methods:{
    getName(name){
      console.log(name)
    }
  },
  mounted() {
    //动态绑定事件
    let that = this;
    setTimeout(function () {
      that.$refs.helloWorld.$on('zhaijiajia',that.getName)
    },1000)
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
