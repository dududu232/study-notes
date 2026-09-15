package com.study.classLoader;

public class TestClassLoader {
    public static void main(String[] args) {

        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);   //sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);  //sun.misc.Launcher$ExtClassLoader@1b6d3586

        //获取上层
        ClassLoader parent = extClassLoader.getParent();
        System.out.println(parent);  //null

        //用户自定义类来说: 默认使用系统类加载器进行加载
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println(classLoader);   //sun.misc.Launcher$AppClassLoader@18b4aac2

        //核心类库都是使用BootstrapClassLoader加载的
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);  //null
    }
}
