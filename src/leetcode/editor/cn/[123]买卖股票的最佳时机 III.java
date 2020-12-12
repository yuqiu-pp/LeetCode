//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [3,3,5,0,0,3,1,4]
//输出: 6
//解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
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
//解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。 
// Related Topics 数组 动态规划 
// 👍 553 👎 0

package leetcode.editor.cn;

class PL123{
    public static void main(String[] args) {
        Solution solution = new PL123().new Solution();
        // TO TEST
        System.out.println(solution.maxProfit(new int[]{7,1,5,3,6,4}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 统计维度：第i天、第j次（最多2次交易）、买卖k（0-买  1-卖）
        // j 和 k 应该是组合出4种情况
        // [i][0][0] [i][0][1]  [i][1][0]  [i][1][1]
        // dp[i][0][0] = dp[i-1][0][1] - price[i], 不买，利润=前一天 dp[i-1][0][0]
        // dp[i][0][1] = dp[i-1][0][0] + price[i]
        // dp[i][1][0] = dp[i-1][0][1] - price[i]
        // dp[i][1][1] = dp[i-1][1][0] + price[i]
        // 递推公式：第i天，第j次买  dp[i][j][0] = dp[i-1][j-1][1]-price[i], 不卖，利润=前一天 dp[i-1][j][0]
        //                第j次卖  dp[i][j][1] = dp[
        public int maxProfit(int[] prices) {

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

