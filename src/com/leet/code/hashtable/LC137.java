package com.leet.code.hashtable;

import java.util.Arrays;
import java.util.HashMap;

public class LC137 {

    public static void main(String[] args) {
        int[] nums = {0,1,0,1,0,1,99};
        LC137 solution = new LC137();

        System.out.println(solution.singleNumber(nums));
    }

    /*
   执行用时 :3 ms, 在所有 Java 提交中击败了85.72%的用户
   内存消耗 :36.8 MB, 在所有 Java 提交中击败了95.94%的用户
    */
    // 算法复杂度:java默认快速排序O(n log(n))+O(n)，因为有了排序,并且步幅是2,所以遍历时大部分情况都小于O(n)
    // 2.排序 + 遍历
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);

        int ret = 0;
        for (int i = 0; i < nums.length; i+=3) {
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
    执行用时 :7 ms, 在所有 Java 提交中击败了49.08%的用户
    内存消耗 :36.6 MB, 在所有 Java 提交中击败了95.94%的用户
     */
    // 算法复杂度O(n)；空间复杂度O(n)
    // 1.散列表
    // 如果存在，且vlue=2，则删除；  如果无，则添加； 如果为1，则加1。 最后剩余的则为只出现一次的数字
    public int singleNumber1(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>(len);

        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(nums[i])){
                if (hashMap.get(nums[i]) == 1){
                    hashMap.put(nums[i], 2);
                }else{
                    hashMap.remove(nums[i]);
                }
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
