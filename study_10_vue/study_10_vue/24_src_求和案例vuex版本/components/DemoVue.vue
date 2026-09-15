<template>
    <div>
      <button @click="incrementOdd">当前求和为奇数再加</button>
      <button @click="incrementWait">等一等再加</button>
     
    </div>
  </template>
  
  <script>
  
  export default {
      name: "CountVue",
      
      data(){
          return {
          }
      },
      methods: {
          incrementOdd(){
              if(this.$store.state.sum%2) this.$store.state.sum+=this.$store.state.num
          },
          incrementWait(){
              setTimeout(()=>{
                  this.$store.state.sum+=this.$store.state.num
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