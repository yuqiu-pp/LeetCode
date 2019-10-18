package com.leet.code.hashtable;


import java.util.Arrays;
import java.util.HashMap;

public class LC260 {
    public static void main(String[] args) {
        int[] nums = {1403617094,-490450406,-1756388866,-967931676,1878401007,1878401007,-74743538,1403617094,-1756388866,-74743538,-490450406,-1895772685};
        LC260 solution = new LC260();

        for (int i : solution.singleNumber(nums)) {
            System.out.println(i);
        }
    }

    /*
    执行用时 :3 ms, 在所有 Java 提交中击败了41%的用户
    内存消耗 :38.7 MB, 在所有 Java 提交中击败了94.75%的用户
    */
    // 算法复杂度:java默认快速排序O(n log(n))+O(n)，因为有了排序,并且步幅是2,所以遍历时大部分情况都小于O(n)
    // 2.排序 + 遍历
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);

        int[] ret = {0, 0};
        int j = 0;
        for (int i = 0; i < nums.length; i+=2) {
            if (i+1 >= nums.length){
                ret[j] = nums[i];
            }else if (i+2 >= nums.length){
                ret[j++] = nums[i++];
                ret[j] = nums[i];
            }
            else {
                if (nums[i] != nums[i+1]){
                    ret[j++] = nums[i];
                    if (j == ret.length){
                        break;
                    }
                    i++;
                    if (nums[i] != nums[i+1]){
                        ret[j++] = nums[i];
                        break;
                    }

                }
            }
        }

        return ret;
    }


    /*
    执行用时 :7 ms, 在所有 Java 提交中击败了30.58%的用户
    内存消耗 :37.7 MB, 在所有 Java 提交中击败了97.90%的用户
     */
    // 算法复杂度O(n)；空间复杂度O(n)
    // 1.散列表
    public int[] singleNumber1(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>(len);

        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(nums[i])){
                hashMap.remove(nums[i]);
            }else {
                hashMap.put(nums[i], 1);
            }
        }

        // 应该只剩一个元素
        int[] ret = {0, 0};
        int i = 0;
        for (int key : hashMap.keySet()){
            ret[i++] = key;
        }

        return ret;
    }
}
