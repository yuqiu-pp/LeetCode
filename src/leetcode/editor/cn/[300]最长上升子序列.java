//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划

package leetcode.editor.cn;

class LC300 {
    public static void main(String[] args) {
        Solution solution = new LC300().new Solution();
        // TO TEST
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(solution.lengthOfLIS(nums));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // tails[k] 的值代表 长度为k+1子序列 的尾部元素值。因为递增越慢长度会越长
        // 状态转移 每次选最小的去更新tail最后元素
        public int lengthOfLIS(int[] nums) {
            int[] tails = new int[nums.length];
            int res = 0;
            for (int num : nums) {
                int i = 0;
                int j = res;
                while (i < j) {
                    int m = i + (j - i) / 2;
                    if (tails[m] < num) {
                        i = m + 1;
                    } else {
                        j = m;
                    }
                }
                tails[i] = num;
                if (res == j) {
                    res++;
                }
            }
            return res;
        }

        // dp[i]  该点的最大子序列长度
        // 转移方程 每个点，检查前面比自己小的点，选出其中dp最大的
        public int lengthOfLIS01(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;

            int maxs = 1;
            for (int i = 0; i < dp.length; i++) {
                int max = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        max = Math.max(max, dp[j]);
                    }
                }
                dp[i] = max + 1;
                maxs = Math.max(maxs, dp[i]);
            }
            return maxs;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
