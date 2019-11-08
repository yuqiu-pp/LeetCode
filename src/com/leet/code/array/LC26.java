package com.leet.code.array;

import java.util.Arrays;

public class LC26 {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        LC26 solution = new LC26();

        System.out.println(solution.removeDuplicates(nums));
    }

    // 遍历一次数组，边查找重复，边移动
    // 两个指针，一个指向被替换的元素，一个指向选取的元素
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 2){
            return len;
        }

        int pre = 0;
        for (int i = 1; i < nums.length; i++) {
            // 找到要替换的位置
            if (nums[i] == nums[pre]){
                pre = i;
            }else if (pre != 0){
                nums[pre++] = nums[i];
            }
        }

        return len;
    }


    /**
     *  执行用时 :1 ms, 在所有 java 提交中击败了100.00%的用户
        内存消耗 :41.1 MB, 在所有 java 提交中击败了92.00%的用户
     */
    // 1.限定空间复杂度O(1)，所以只能用一个临时变量判断数据是否重复
    // 2.由于搬移数据消耗时间，所以最好先使用一个flag标记要移除的数据，最后做一次搬移
    //  flag的选择：因已知为排序数组，所以flag可以取num[0]-1，
    // 需要遍历两次数组（查找重复数据、搬移），算法复杂度：O(n)
    public int removeDuplicates1(int[] nums) {
        int len = nums.length;
        if (len == 0){
            return 0;
        }

        int flag = nums[0] - 1;
        int tmp = nums[0];

        // 查找重复数据
        for (int i = 1; i < nums.length; i++) {
            if (tmp == nums[i]){
                nums[i] = flag;
                len --;
            }else {
                tmp = nums[i];
            }
        }

        if (len == nums.length){
            return len;
        }

        // 搬移
        int pre = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == flag){
                // 记录第一个要替换的位置
                if (pre == 0){
                    pre = i;
                }
            }else if (pre != 0){
                // 找到了不等于flag的元素，要移动的位置
                nums[pre++] = nums[i];
            }
        }

        return len;
    }

    /**
     *  执行用时 :2 ms, 在所有 java 提交中击败了51.89%的用户
        内存消耗 :41.3 MB, 在所有 java 提交中击败了90.66%的用户
     */
    // 1.限定空间复杂度O(1)，所以只能用一个临时变量判断数据是否重复
    // 2.由于搬移数据消耗时间，所以最好先使用一个flag标记要移除的数据，最后做一次搬移
    // 3.搬移操作：因已知为有序数组，所以可以用排序来实现搬移的目的。
    //   同时由于是有序数组，flag可以取num[n]+1，大于最大值，这样就能保证排序后重复数据都来最后
    // 数组遍历 + 排序， 算法复杂度：O(n + arrays.sort)，
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if (len == 0){
            return 0;
        }

        int flag = nums[len - 1] + 1;
        int tmp = nums[0];

        // 查找重复数据
        for (int i = 1; i < nums.length; i++) {
            if (tmp == nums[i]){
                nums[i] = flag;
                len --;
            }else {
                tmp = nums[i];
            }
        }

        if (len == nums.length){
            return len;
        }

        Arrays.sort(nums);

        return len;
    }

}
