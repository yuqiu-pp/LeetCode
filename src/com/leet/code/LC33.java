package com.leet.code;


// 算法时间复杂度必须是 O(log n) 级别

public class LC33 {

    // 二分查找
    // 复杂度O(logN)
    // 1. 先找旋转点，也是值最小的点（该点后面的数据全部小于前半部分；改点位置可能是0，这样数组就是单调递增）
    // 2. 根据target 和 旋转点的大小关系，决定在那个半区用二分查找
    public int search(int[] nums, int target) {
        if (nums.length < 1){
            return -1;
        }

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

        // 2
        if (target == nums[mid]){
            return mid;
        }else if (target < nums[mid]){
            left = mid;
            right = nums.length - 1;
        }else {
            left = 0;
            right = (mid == 0) ? nums.length - 1 : mid ;
        }
        // 由于left加了1，所以这里要包括=号
        while (left < right){
            mid = left + (right - left) / 2;
            if (nums[mid] == target){
                return mid;
            }
            if (target > nums[mid]){
                // left+1 避免死循环
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        if (left == right && nums[left] == target){
            return left;
        }

        return -1;
    }


    // 遍历数组找到递减的位置
    // 复杂度O(N)
    public int search1(int[] nums, int target) {
        if (nums.length < 1){
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC33 solution = new LC33();
        int[] nums = {1,3};
        int target = 3;
        System.out.println(solution.search(nums, target));
    }
}
