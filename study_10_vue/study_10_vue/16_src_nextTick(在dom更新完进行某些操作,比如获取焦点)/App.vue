<template>
  <div id="app">
    <school-vue></school-vue>
    <hr>
    <student-vue></student-vue>
  </div>
</template>

<script>
import StudentVue from "./components/StudentVue";
import SchoolVue from "./components/SchoolVue";
export default {
  name: 'App',
  components: {
    StudentVue,SchoolVue
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
