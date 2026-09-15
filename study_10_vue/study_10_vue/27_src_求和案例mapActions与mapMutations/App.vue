<template>
  <div id="app">
    <count-vue></count-vue>
    <hr>
  </div>
</template>

<script>
  import CountVue from './components/CountVue';
  export default {
    name: 'App',
    components: {
      CountVue
    },
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
