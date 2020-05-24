//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics 贪心算法 数组

package leetcode.editor.cn;

class LC122{
    public static void main(String[] args) {
        Solution solution = new LC122().new Solution();
        // TO TEST
        // int[] prices = {1,2,3,4,5};
        int[] prices = {7,1,5,3,6,4};
        System.out.println(solution.maxProfit(prices));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int[] buy = new int[len];
            int[] sell = new int[len];
            buy[0] = 0 - prices[0];
            sell[0] = 0;
            for (int i = 1; i < len; i++) {
                buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
                sell[i] = Math.max(buy[i-1] + prices[i], sell[i - 1]);
                System.out.println(buy[i]);
                System.out.println(sell[i]);
            }
            return Math.max(buy[len-1], sell[len-1]);
        }

        // 贪心适用场景：用动态规划有些杀鸡用牛刀的时候
        // 第i天与i+1天比较，涨就计入利润
        public int maxProfit03(int[] prices) {
            int res = 0;
            int len = prices.length;
            for (int i = 0; i < len - 1; i++) {
                int diff = prices[i + 1] - prices[i];
                if (diff > 0) {
                    res += diff;
                }
            }
            return res;
        }

        //  动态规划：找递推公式
        //  将buy和sell两个一维数组，合并为一个二维数组.  4 ms，比两个数组(2ms) 慢
        public int maxProfit02(int[] prices) {
            int n = prices.length;
            if (n <= 1) {
                return 0;
            }
            // 第二维表示buy-0/sell-1
            int[][] dp = new int[n][n];
            dp[0][0] = -prices[0];  // 第一天买
            dp[0][1] = 0;  // 没利润
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]);
                dp[i][1] = Math.max(dp[i-1][0] + prices[i], dp[i-1][1]);
            }
            return dp[n-1][1];
        }

        //  动态规划：找递推公式
        public int maxProfit01(int[] prices) {
            int n = prices.length;
            if (n <= 1) {
                return 0;
            }
            int[] buy = new int[n];
            int[] sell = new int[n];
            buy[0] = -prices[0];

            for (int i = 1; i < n; i++) {
                // 递推公式
                buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
                sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]);
            }
            return sell[n-1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
