//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。 
//
// 注意：你不能在买入股票前卖出股票。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
// Related Topics 数组 动态规划 
// 👍 1298 👎 0

package leetcode.editor.cn;

class PL121{
    public static void main(String[] args) {
        Solution solution = new PL121().new Solution();
        // TO TEST
        System.out.println(solution.maxProfit(new int[]{7,1,5,3,6,4}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 只进行一次交易
        // 找两天之间的最大差额就是最大利润

        // 遍历 记录最大和最小值，但要求有先后顺序
        // 通过先判断最大值，再判断最小值
        public int maxProfit(int[] prices) {
            if (prices.length < 1) {
                return 0;
            }
            int min = prices[0];
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                // 每天价格与最低价的差额，即最大利润
                profit = Math.max(prices[i] - min, profit);
                min = Math.min(prices[i], min);
            }
            return profit;
        }

        // 暴力 找任何一对ij的组合
        public int maxProfit01(int[] prices) {
            int n = prices.length;
            int max = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    max = Math.max(max, prices[j] - prices[i]);
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

