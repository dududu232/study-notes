package com.study.testExtends;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/01/30/11:07
 * @Description:
 */
public abstract class AbstractTestExtendsServiceImpl implements TestExtendsService{

    @Override
    public void methodA() {
        System.out.println("methodA被调用");
        methodB();
    }


}
