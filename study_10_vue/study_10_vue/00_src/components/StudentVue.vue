<template>
    <div>
        <h2>{{name}}</h2>
        <h2>{{sex}}</h2>

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

        }
    }
</script>

<style scoped>

</style>
