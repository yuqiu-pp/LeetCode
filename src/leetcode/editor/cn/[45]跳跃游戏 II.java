//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组

package leetcode.editor.cn;

class LC45{
    public static void main(String[] args) {
        Solution solution = new LC45().new Solution();
        // TO TEST
        int[] nums = {2,3,1,1,4};
        System.out.println(solution.jump(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 动态规划：每步跳跃结果集中选最大值
        // public int jump(int[] nums) {
        //
        // }

        // 贪心：从后向前查找，每次找能跳到终点的最大位置
        // 最大位置的查找只能从前开始，第一个找到的可达点就是最远可达点
        // 时间复杂度：O(n*n)
        public int jump(int[] nums) {
            int curr = nums.length - 1;
            int res = 0;
            while (curr > 0) {
                for (int i = 0; i < curr; i++) {
                    if (i + nums[i] >= curr) {
                        curr = i;
                        res ++;
                        break;
                    }
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
