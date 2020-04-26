//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

class LC189{
    public static void main(String[] args) {
        Solution solution = new LC189().new Solution();
        // TO TEST
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        solution.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 注意 K可能大于数组长度，要取模
        public void rotate(int[] nums, int k) {
            int len = nums.length;
            int[] a = new int[len];
            for (int i = 0; i < len; i++) {
                a[(i+k)%len] = nums[i];
            }
            for (int i = 0; i < len; i++) {
                nums[i] = a[i];
            }
        }

        public void rotate01(int[] nums, int k) {
            Stack<Integer> stack = new Stack<>();
            int len = nums.length;
            for (int i = 0; i < k; i++) {
                stack.push(nums[len-1-i]);
            }
            len = 0;
            System.arraycopy(nums, 0, nums, k, nums.length-k);
            while (!stack.isEmpty()) {
                nums[len++] = stack.pop();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
