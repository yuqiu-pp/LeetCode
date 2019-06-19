package com.leet.code;



public class LC81 {

    public boolean search(int[] nums, int target) {

        // 二分查找，找到旋转点
        // 旋转点特征：它两边分别单调递增
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right){
            mid = left + (right - left) / 2;
            // 等于mid的情况在这里判断，后面就不需考虑这种情况了
            if (target == nums[mid]){
                return true;
            }

            // mid在左边的递增区间
            if (nums[mid] > nums[left]){
                // 引入target比较，避免第二次查找
                if (target >= nums[left] && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            // mid在右边递增区间
            else if (nums[mid] < nums[right]){
                if (target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
            else{
                if (nums[mid] == nums[left]){
                    left += 1;
                }
                if (nums[mid] == nums[right]){
                    right -= 1;
                }
            }
        }

        return false;
    }

    public boolean search1(int[] nums, int target) {

        // 二分查找，找到旋转点
        // 但改点的值可能重复，所以还需要向前探测
        // 旋转点特征：它左边的值，都大于最后一个点的值
        // mid > nums[length-1]，说明是在旋转点左边
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right){
            mid = left + (right - left) / 2;

            // nums[mid] > nums[right]  等价于 nums[mid] > nums[0]
            if (nums[mid] > nums[right]){
                left = mid + 1;
            }else if (nums[mid] < nums[right]) {
                // 以右端为基准判断，所以不能-1，那样可能跳过最小值
                right = mid;
            }else {
                // 最右端向左移动
                right -= 1;
                if (target == nums[right]){
                    return true;
                }
            }

            if (left == right){
                mid = left;
                break;
            }
        }

        if (target == nums[mid]){
            return true;
        }

        // 没有旋转，单调升
        if (mid == 0){
            left = 0;
            right = nums.length - 1;
        }else {
            if (target <= nums[nums.length - 1]){
                left = mid;
                right = nums.length - 1;
            }else {
                left = 0;
                right = mid;
            }
        }

        // 标准二分
        while (left <= right){
            mid = left + (right - left) / 2;
            if (target == nums[mid]){
                return true;
            }
            if (target > nums[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LC81 solution = new LC81();
        int[] nums = {2,2,2,3,2,2};
        int target = 3;
        System.out.println(solution.search(nums, target));
    }
}
