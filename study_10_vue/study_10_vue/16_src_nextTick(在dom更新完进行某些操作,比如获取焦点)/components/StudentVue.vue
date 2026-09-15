<template>
    <div>
        <h2>{{name}}</h2>
        <h2>{{sex}}</h2>
        <button @click="sendStudentName">发送学生姓名给School组件</button>
    </div>
</template>

<script>
    import pubsub from 'pubsub-js';
    export default {
        name: "StudentVue",
        data(){
            return{
                name:'zs',
                sex: "男"
            }
        },
        methods:{
            sendStudentName(){
                pubsub.publish('hello',this.name)
            }
        }
    }
</script>

<style scoped>

</style>
