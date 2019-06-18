package com.leet.code;

import java.lang.reflect.Modifier;

public class LC81 {

    public boolean search(int[] nums, int target) {

        // 二分查找，找到旋转点
        // 但改点的值可能重复，所以还需要向前探测
        // 旋转点特征：它左边的值，都大于最后一个点的值
        // mid > nums[length-1]，说明是在旋转点左边
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right){
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        System.out.println(mid);
        return true;
    }

    public static void main(String[] args) {
        LC81 solution = new LC81();
        int[] nums = {2,5,6,0,1,1,2};
        int target = 3;
        solution.search(nums, target);
    }
}
