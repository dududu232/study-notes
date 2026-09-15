<template>
    <div>
        <h2>{{name}}</h2>
        <h2>{{address}}</h2>
    </div>
</template>

<script>
    export default {
        name: "SchoolVue",
        data(){
            return{
                name: "尚硅谷",
                address: "地址"
            }
        },
        mounted() {

        }
    }
</script>

<style scoped>

</style>
