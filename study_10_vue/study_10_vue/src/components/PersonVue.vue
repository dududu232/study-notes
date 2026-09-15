<template>
    <div style="background-color: aqua;">
        <ul>
            <li v-for="personObj of personList" :key="personObj.id">{{personObj.name}}</li>
        </ul>
    </div>
  </template>
  
  <script>
  import {mapState} from 'vuex';
  export default {
      name: "PersonVue",
      
      data(){
          return {
          }
      },
      methods: {
         
      },
      computed: {
        ...mapState('personAbout',['personList']),
      }
  }
  </script>
  
  <style scoped>
      button {
          margin-left: 10px;
      }
  </style>