package com.study;

import com.study.CreatObjectTest;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/08/20:11
 * @Description:
 */
public class CreatObject implements Cloneable , Serializable {
    public int a = 1;

    public static void main(String[] args) {
        try {
            //调用的是空参构造，要求空参构造必须public
            CreatObjectTest creatObjectTest = CreatObjectTest.class.newInstance();

            //获取构造器对象创建
            Constructor<CreatObjectTest> constructor = CreatObjectTest.class.getConstructor(Long.class);
            long a = 1;
            CreatObjectTest objectTest = constructor.newInstance(a);

            //在本类中调用clone()
            CreatObject creatObject = new CreatObject();
            creatObject.test();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

    private void test() throws CloneNotSupportedException {
        CreatObject object = new CreatObject();
        object.a = 2;
        CreatObject clone =(CreatObject)object.clone();
        System.out.println(clone.a);
        System.out.println(object == clone);
    }
}
