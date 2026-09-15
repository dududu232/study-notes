<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h3>Installed CLI Plugins</h3>
    <button @click="sendStudentName">nihao</button>

    <button @click="unbind">点击解绑事件</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String,
  },
  mounted() {
    this.a = 2
    console.log(this)
  },
  methods:{
    sendStudentName(){
      this.$emit('zhaijiajia',"zs")
    },
    //解绑事件
    unbind(){
      /*解绑一个*/
      // this.$off('zhaijiajia')
      //解绑多个
      // this.$off(['zhaijiajia','demo'])

      /*解绑所有绑定事件*/
      this.$off();
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
