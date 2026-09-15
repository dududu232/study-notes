package com.study;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2025/03/01/11:20
 * @Description: 用栈实现中缀表达式转后缀表达式
 */



import java.util.*;

/**
 * 用栈实现中缀表达式转后缀表达式：
 * 初始化一个栈，用于保存暂时还不能确定运算顺序的运算符。
 * 从左到右处理各个元素，直到末尾。可能遇到三种情况：
 *     ① 遇到操作数。直接加入后缀表达式。
 *     ② 遇到界限符。遇到“（”直接入栈；遇到“）”则依次弹出栈内运算符并加入后缀表达式，直到弹
 *          出“（”为止。注意：“（”不加入后缀表达式。
 *     ③ 遇到运算符。依次弹出栈中优先级高于或等于当前运算符的所有运算符，并加入后缀表达式，
 *          若碰到“（”或栈空则停止。之后再把当前运算符入栈。
 * 按上述方法处理完所有字符后，将栈中剩余运算符依次弹出，并加入后缀表达式。
 */
public class InfixToSuffixConversion {

    /**
     *
     * @param infix  中缀表达式
     * @return 后缀表达式
     */
    private List<String> infixToSuffixConversion(String infix) {
        List<String> result = new ArrayList<>();
        String numRegex = "^[+-]?(\\d+\\.\\d+|\\d+|\\.\\d+)$";
        String operatorRegex = "[+\\-*/]";
        Stack<String> stack = new Stack<>();
        Map<String, Integer> priorityMap = getPriorityMap();
        Stack<String> cacheStack = new Stack<>();

        String num = "";
        for (int i = 0; i < infix.length(); i++) {
            String s = String.valueOf(infix.charAt(i));
            if (s.matches(numRegex)) {
                num += s;
            } else {
                result.add(num);
                num = "";
                if ("(".equals(s)) {
                    stack.push(s);
                }else if (")".equals(s)){
                    while(!"(".equals(stack.peek())){
                        result.add(stack.pop());
                    }
                    stack.pop();
                }else if (s.matches(operatorRegex)){
                    while (!stack.empty() && !"(".equals(stack.peek())){
                        Integer integer = priorityMap.get(stack.peek());
                        Integer integer1 = priorityMap.get(s);
                        if (integer>= integer1){
                            result.add(stack.pop());
                        }else{
                            break;
                        }

                    }
                    stack.push(s);
                }
            }
        }
        result.add(num);
        while(!stack.empty()){
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * 计算中缀表达式
     * @return
     */
    private int countInfixConversion(String infixConversion){

        String numRegex = "^[+-]?(\\d+\\.\\d+|\\d+|\\.\\d+)$";  //匹配数字
        String operatorRegex = "[+\\-*/]";  //匹配操作符
        String symbolRegex = "[+\\-*/()]";  //匹配界限符和操作符

        Map<String, Integer> priorityMap = getPriorityMap();  //操作符优先级

        Stack<Integer> numStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();

        String num = "";
        for (int i = 0; i < infixConversion.length(); i++) {
            String s = String.valueOf(infixConversion.charAt(i));
            if (s.matches(numRegex)){
                num+=s;
            }else{
                if (!"".equals(num)){
                    numStack.push(Integer.valueOf(num));
                    num="";
                }
                if ("(".equals(s)){
                    symbolStack.push(s);
                }else if (")".equals(s)){
                    while (!"(".equals(symbolStack.peek())){
                        count(numStack,symbolStack.pop());
                    }
                    symbolStack.pop();
                }else if (s.matches(operatorRegex)){
                    while (!symbolStack.empty() && !"(".equals(symbolStack.peek())){
                        Integer integer = priorityMap.get(symbolStack.peek());
                        Integer integer1 = priorityMap.get(s);
                        if (integer>= integer1){
                            count(numStack,symbolStack.pop());
                        }else{
                            break;
                        }

                    }
                    symbolStack.push(s);
                }
            }
        }
        numStack.push(Integer.valueOf(num));
        while(!symbolStack.empty()){
            count(numStack,symbolStack.pop());
        }
        Integer pop = numStack.pop();
        if (!numStack.empty())throw new RuntimeException("表达式有问题");
        return pop;
    }

    private void count(Stack<Integer> numStack,String operator){
        int result = 0;
        Integer right = numStack.pop();
        Integer left = numStack.pop();
        switch (operator){
            case "+":{
                result=right+left;
                break;
            }
            case "-":{
                result=left-right;
                break;
            }
            case "*":{
                result=left*right;
                break;
            }
            case "/":{
                result=left/right;
                break;
            }
        }
        numStack.push(result);
    }

    /**
     * 获取操作符优先级
     * @return
     */
    private Map<String,Integer> getPriorityMap(){
        Map<String, Integer> map = new HashMap<>();
        map.put("+",1);
        map.put("-",1);
        map.put("*",2);
        map.put("/",2);
        return map;
    }

    /**
     * 1+1
     * 1+2*3/4  -->   1  2 3 * 4 / +
     *
     * 1+2*3-4  --->1 2 3 * + 4 -
     *  11+2*3-4
     *  (11+2)*3-4  --> 11 2 + 3 * 4 -
     */
    public static void main(String[] args) {
        String infix = "1+2*3/2";
        InfixToSuffixConversion instance = new InfixToSuffixConversion();

        //List<String> list = instance.infixToSuffixConversion(infix);
        int i = instance.countInfixConversion(infix);
        System.out.println(i);


    }

}
