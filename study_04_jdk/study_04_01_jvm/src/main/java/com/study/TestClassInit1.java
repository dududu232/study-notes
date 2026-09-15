package com.study;

public class TestClassInit1 {

    public static class Father{
        static int A = 1;
        static {
            A = 2;
        }
    }

     static class Son extends Father{
         static int B = A;
    }
    public static void main(String[] args) {
        System.out.println(Son.B);
    }
}
