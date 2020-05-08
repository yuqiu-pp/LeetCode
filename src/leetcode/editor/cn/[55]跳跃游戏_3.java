//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组

package leetcode.editor.cn;



class LC55{
    public static void main(String[] args) {
        Solution solution = new LC55().new Solution();
        // TO TEST
        // int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {2,0,0};
        System.out.println(solution.canJump(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 贪心 ： 证明结果是最优，每一个都取可达的最大距离
        public boolean canJump(int[] nums) {
            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    // 注意是 i + nums[i]
                    max = Math.max(max, i + nums[i]);
                }
                if (max >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }

        public boolean canJump02(int[] nums) {
            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i <= max) {
                    max = Math.max(max, i + nums[i]);
                }
                if (max >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }

        // 超出时间限制
        public boolean canJump01(int[] nums) {
            return dfs(nums, 0, nums.length);
        }
        private boolean dfs(int[] nums, int index, int n) {
            if (index >= nums.length - 1) {
                return true;
            }
            if (nums[index] == 0) {
                return false;
            }
            // 当前层逻辑： 依次选一个数.  i从大的数开始尝试也会超时
            for (int i = 1; i <= nums[index]; i++) {
                boolean res = dfs(nums, index + i, n - i);
                if (res) {
                    return true;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
