package com.study;


import org.omg.CORBA.Object;

import java.util.*;

public class LeetCode {
    public static void main(String[] args) {
        System.out.println(0 / 2);


        Solution solution = new Solution();
        String aa = "abcabcbb";
        System.out.println(solution.lengthOfLongestSubstring3(aa));

    }
}

class Solution {
    /**
     * 1. 两数之和
     *
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     *
     */
    /**
     * 方法一  枚举   时间复杂度: O(N^2)  空间复杂度: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * 方法二：哈希表  时间复杂度: O(n) 空间复杂度: O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ints = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ints[0] = map.get(target - nums[i]);
                ints[1] = i;
                return ints;
            }
            map.put(nums[i], i);
        }
        return ints;
    }

    /**
     * 2. 两数相加
     * <p>
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode startNode = null;
        ListNode preNode = null;
        int n = 0;
        while (l1 != null || l2 != null || n != 0) {
            ListNode node = new ListNode();
            int i = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + n;
            if (i >= 10) {
                node.val = i - 10;
                n = 1;
            } else {
                node.val = i;
                n = 0;
            }
            if (startNode == null) {
                startNode = node;
            } else if (startNode.next == null) {
                startNode.next = node;
            }
            if (preNode != null) {
                preNode.next = node;
            }
            preNode = node;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return startNode;
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    /**
     * 方法一:  暴力枚举  时间复杂度O(n^2)  空间复杂度O(1): 字符的 ASCII 码范围为 0 ~ 127
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            Set<Character> set = new HashSet<>();
            // List<Character> list = new ArrayList<>();
            set.add(chars[i]);
            //list.add(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                char aChar = chars[j];
//                if (list.indexOf(aChar)>-1){
                if (set.contains(aChar)) {
                    break;
                } else {
//                    list.add(aChar);
                    set.add(aChar);
                }
            }
            /*if (result<list.size()){
                result = list.size();
            }*/
//            result = Math.max(result,list.size());
            result = Math.max(result, set.size());
            if (chars.length - (i + 1) <= result) {
                return result;
            }
        }

        return result;
    }

    /**
     * 方法二:  滑动窗口  时间复杂度O(n)  空间复杂度O(1) :字符的 ASCII 码范围为 0 ~ 127
     */
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0, len = s.length();
        for (int j = 0; j < len; j++) {
            if (dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }

    /**
     * 方法三: 动态规划
     */
    public int lengthOfLongestSubstring3(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0, len = s.length();
        for (int j = 0; j < len; j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * <p>
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;

        int k1 = nums1.length == 0 ? -1 : (nums1.length +1)/ 2 -1;
        int k2 = nums2.length == 0 ? -1 : (nums2.length+1) / 2-1;

        if (k1 == -1){
            return nums2[k2];
        }
        if (k2==-1){
            return nums1[k1];
        }
        //指针移动,保证分割线左边的所有数小于分割线右边
        while (nums1[k1]>=nums2[k2+1] || nums1[k1+1] <= nums2[k2]){
            //数组一的左侧中存在数字大于数组二右侧
            if (nums1[k1]>=nums2[k2+1]){
                k1--;
                k2++;
                //数组二的左侧中存在数字大于数组一右侧
            }else if (nums1[k1+1] <= nums2[k2]){
                k1++;
                k2--;
            }

        }

        if (length%2 == 0){ //长度为偶数
            return (Math.max(nums1[k1],nums2[k2])+(Math.min(nums1[k1+1],nums2[k2+1])))/2.0;
        }else{  //长度为奇数
            return Math.max(nums1[k1],nums2[k2]);
        }





    }


    //最长回文子串
    //中心扩散法
    public String longestPalindrome(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        if (!Objects.isNull(s) && length > 2) {
            String result = s.substring(0, 1);
            for (int i = 1; i < length - 1; i++) {
                int left = i, right = i;
                while (right + 1 < length && chars[i] == chars[right + 1]) {
                    right++;
                }
                while (left > 0 && chars[i] == chars[left - 1]) {
                    left--;
                }

                while (left - 1 >= 0 && right + 1 < length && chars[left - 1] == chars[right + 1]) {
                    left--;
                    right++;
                }
                String substring = s.substring(left, right + 1);
                if (substring.length() > result.length()) {
                    result = substring;
                }

            }
            return result;
        } else if (length == 1 || Objects.isNull(s) || chars[0] == chars[1]) {
            return s;
        } else {
            return s.substring(0, 1);
        }
    }
}


