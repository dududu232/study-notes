<template>
    <div>
        <h2>{{name}}</h2>
        <h2>{{address}}</h2>
    </div>
</template>

<script>
    //1. 安装pubsub-js    npm i pubsub-js
    import pubsub from 'pubsub-js';
    export default {
        name: "SchoolVue",
        data(){
            return{
                name: "尚硅谷",
                address: "地址"
            }
        },
        mounted() {
            this.pubId = pubsub.subscribe('hello',function (msgName,data) {
                console.log("接受到了",msgName,data)
            })
        },
        beforeDestroy() {
            pubsub.unsubscribe(this.pubId);   //需要取消订阅
        }
    }
</script>

<style scoped>

</style>
