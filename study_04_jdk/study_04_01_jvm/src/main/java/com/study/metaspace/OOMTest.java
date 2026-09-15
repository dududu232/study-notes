package com.study.metaspace;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/08/15:24
 * @Description:
 */
public class OOMTest extends ClassLoader{

    public static void main(String[] args) {
        int j = 0;
        try {
            OOMTest test = new OOMTest();
            for (int i = 0; i < 20000000; i++) {

                ClassWriter writer = new ClassWriter(0);
                writer.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                byte[] bytes = writer.toByteArray();
                test.defineClass("Class"+i,bytes,0,bytes.length);
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }

}
