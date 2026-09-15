package com.test.testSort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/28/15:29
 * @Description:
 */
public class QuickSort {
    public static void main(String[] args) {

        int[] arr = {1,1,1,1,1,1,1,1,1,1};
        for (int i = 0; i < 10; i++) {
            double v = Math.random() * 10;
            arr[i] = Integer.parseInt(String.valueOf(Math.floor(v)).substring(0,1));
        }
        quickSort(arr,0,9);
//        for (int i : arr) {
//            System.out.println(i);
//        }
        String join = String.join(",",Arrays.stream(arr).mapToObj(String::valueOf).toArray(String[]::new));
        System.out.println(join);
    }

    public static void quickSort(int[] arr,int left,int right){
        if (left>right){
            return;
        }
        int i = left;
        int j = right;
        //选择基准数
        int base = arr[left];

        while (i!=j){
            while (arr[j]>=base && j>i){
                j--;
            }

            while (arr[i]<=base && j>i){
                i ++;
            }

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //
        arr[left] = arr[i];
        arr[i] = base;

        quickSort(arr,0,i-1);
        quickSort(arr,i+1,right);

    }
}
