//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 动态规划

package leetcode.editor.cn;

class LC309{
    public static void main(String[] args) {
        Solution solution = new LC309().new Solution();
        // TO TEST
        int[] prices = {1,2,3,0,2};
        System.out.println(solution.maxProfit(prices));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 买 0 两条路径 前两天的冻结、前一天买的状态不变  dp[i][0] = max(dp[i][2]-s[i], dp[i-1])
        // 卖 1 前一天的买 dp[i][1] = dp[i][0] + s[i]
        // 冻 2 前一天的卖 dp[i][2] = dp[i][1]
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][3];
            dp[0][0] = 0 - prices[0];
            dp[0][1] = 0;
            dp[0][2] = 0;
            for (int i = 1; i < prices.length; i++) {
                if (i >= 2) {
                    dp[i][0] = Math.max(dp[i-2][2] - prices[i], dp[i-1][0]);
                } else {
                    dp[i][0] = Math.max(dp[0][2] - prices[i], dp[i-1][0]);
                }

                dp[i][1] = dp[i-1][0] + prices[i];
                dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
            }
            int n = prices.length;
            return Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}