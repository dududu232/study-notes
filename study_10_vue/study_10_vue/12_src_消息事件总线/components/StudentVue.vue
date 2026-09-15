<template>
    <div>
        <h2>{{name}}</h2>
        <h2>{{sex}}</h2>

        <button @click="demo">给School组件传值</button>
    </div>
</template>

<script>
    export default {
        name: "StudentVue",
        data(){
            return{
                name:'zs',
                sex: "男"
            }
        },
        methods:{
            demo(){
                this.$bus.$emit('hello',1111);
            }
        }
    }
</script>

<style scoped>

</style>
