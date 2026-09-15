//用于操作数据
export default{
    JIA(state,value){
        state.sum += value;
    },
    JIAN(state,value){
        state.sum -= value;
    },
    JIANSHUANGBEI(state,value){
        state.sum -= (value*2);
    }
}