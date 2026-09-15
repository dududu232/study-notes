//准备actions,用于响应组件中的一些动作
export default{

    jia(context,value){
        context.commit('JIA',value)
    },
    jian(context,value){ 
        context.commit('JIAN',value)
    }
}