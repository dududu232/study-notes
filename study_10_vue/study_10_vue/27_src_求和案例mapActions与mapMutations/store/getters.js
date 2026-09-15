//当state中的数据需要加工后再使用,可以用getters
export default{
    bigSum(state){
        return state.sum * 10;
    }
}