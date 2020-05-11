//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LC169{
    public static void main(String[] args) {
        Solution solution = new LC169().new Solution();
        // TO TEST
        int[] nums = {3, 3, 4};
        System.out.println(solution.majorityElement(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1. hash表统计。  可以用数组作为hash表，num[i]做key，次数做value. 优化：创建表时，就可以判断
        // 2. 排序，依次遍历+计数
        // 3. 排序，nums[n/2]位置的数，因为它的数量超过一半   就是数组排序O(nlogn)
        // 4. 分治  分别查找   O(nlogn)
        public int majorityElement(int[] nums) {
            return dividAndConquer(nums, 0, nums.length - 1);
        }
        private int divid(int[] nums, int low, int high) {
            if (low == high) {
                return nums[low];
            }
            int mid = low + (low + high) / 2;
            int lower = divid(nums, low, mid);
            int higher = divid(nums, mid + 1, high);
            if (lower == higher) {
                return lower;
            }
            return countInRange(nums,lower,low,high) > countInRange(nums,higher,mid+1,high) ? lower : higher;
        }
        private int dividAndConquer(int[] nums, int low, int high) {
            // 终止条件
            if (low == high) {
                return nums[low];
            }
            // 当前层：
            int mid = (high - low) / 2 + low;
            // 下一层
            int left = dividAndConquer(nums, low, mid);
            int right = dividAndConquer(nums, mid + 1, high);

            if (left == right) {
                return left;
            }
            int leftCount = countInRange(nums, left, low, high);
            int rightCount = countInRange(nums, right, low, high);
            return leftCount > rightCount ? left : right;
        }
        private int countInRange(int[] nums, int val, int low, int high) {
            int res = 0;
            for (int i = low; i <= high; i++) {
                if (nums[i] == val) {
                    res++;
                }
            }
            return res;
        }

        // sort
        public int majorityElement02(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length/2];
        }

        // hashmap
        public int majorityElement01(int[] nums) {
            int len = nums.length;
            HashMap<Integer, Integer> hashmap = new HashMap<>();
            for (int i = 0; i < len; i++) {
                // int n = hashmap.getOrDefault(nums[i], 0);
                // if (n > len / 2) {
                //     return nums[i];
                // }
                // hashmap.put(nums[i], n + 1);
                // 替换上面代码
                hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0) + 1);
                if (hashmap.get(nums[i]) > len / 2) {
                    return nums[i];
                }
            }
            return -1;
            // 改进：在创建hash表时，维护最大值.   差别不大
            // 进一步改进： 创建hash表时，随时比对 n/2
            // int max = 0;
            // int res = 0;
            // for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            //     res = max < entry.getValue() ? entry.getKey() : res;
            //     max = Math.max(entry.getValue(), max);
            // }
            // return res;

            // Map.Entry<Integer, Integer> majorityEntry = null;
            // for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            //     if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
            //         majorityEntry = entry;
            //     }
            // }
            // return majorityEntry.getKey();

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
