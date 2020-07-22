//给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 num
//s[i] 的元素的数量。 
//
// 示例: 
//
// 输入: [5,2,6,1]
//输出: [2,1,1,0] 
//解释:
//5 的右侧有 2 个更小的元素 (2 和 1).
//2 的右侧仅有 1 个更小的元素 (1).
//6 的右侧有 1 个更小的元素 (1).
//1 的右侧有 0 个更小的元素.
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法

package leetcode.editor.cn;

import java.util.*;

import java.util.Collections;

import static java.util.Collections.reverse;

class LC315{
    public static void main(String[] args) {
        Solution solution = new LC315().new Solution();
        // TO TEST
        System.out.println(solution.countSmaller(new int[] {5,2,6,1}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> countSmaller(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new ArrayList<>();
            }
            int len = nums.length;
            // 数组合list性能差别不大
            // List<Integer> res = new ArrayList<>();
            // res.add(0);
            Integer[] res = new Integer[len];
            int k = 1;
            res[len - k++] = 0;
            // 反向插入排序
            for (int i = len - 2; i >= 0; i--) {
                int j = i + 1;
                int temp = nums[i];
                while (j < len && nums[j] >= temp) {
                    // 这里是两数交互，使i后变成一个有序数组
                    nums[j - 1] = nums[j];
                    j ++;
                }
                nums[j - 1] = temp;
                // res.add(len - j);
                res[len - k++] = len - j;
            }
            // Collections.reverse(res);
            return Arrays.asList(res);
        }

        // public List<Integer> countSmaller(int[] nums) {
        //     List<Integer> res = new ArrayList<>();
        //     List
        //     for (int i = 0; i < nums.length; i++) {
        //         int count = helper(nums, i);
        //         res.add(count);
        //     }
        //     return res;
        // }
        // private int helper(int[] nums, int i) {
        //     int[]
        // }

        // 暴力， 超出时限
        // 两层循环，O(N*N)
        // 优化：内层循环变logN ， j...end 排序，在二分查找
        //                    ,  插入排序
        //                    ， 归并
        public List<Integer> countSmaller01(int[] nums) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int count = 0;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] > nums[j]) {
                        count ++;
                    }
                }
                res.add(count);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
