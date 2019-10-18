package com.leet.code.hashtable;

import java.util.Arrays;
import java.util.HashMap;

public class LC136 {

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        LC136 solution = new LC136();

        System.out.println(solution.singleNumber(nums));
    }


    // 3.任何数异或自身=0，从头开始异或，最后剩余的数就是出现一次的数


    /*
    执行用时 :4 ms, 在所有 Java 提交中击败了37.26%的用户
    内存消耗 :38.8 MB, 在所有 Java 提交中击败了95.38%的用户
     */
    // 算法复杂度:java默认快速排序O(n log(n))+O(n)，因为有了排序,并且步幅是2,所以遍历时大部分情况都小于O(n)
    // 2.排序 + 遍历
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);

        int ret = 0;
        for (int i = 0; i < nums.length; i+=2) {
            if (i+1 >= nums.length){
                ret = nums[i];
            }else {
                if (nums[i] != nums[i+1]){
                    ret = nums[i];
                    break;
                }
            }
        }

        return ret;
    }


    /*
    执行用时 :16 ms
    内存消耗 :39.8 MB
     */
    // 算法复杂度O(n)；空间复杂度O(n)
    // 1.散列表
    // 如果存在，则删除；  如果无，则添加。 最后剩余的则为只出现一次的数字
    public int singleNumber01(int[] nums) {
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
        int ret = 0;
        for (int key : hashMap.keySet()){
            ret = key;
        }

        return ret;
    }
}
