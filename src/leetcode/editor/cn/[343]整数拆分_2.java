//给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 
//
// 示例 1: 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 说明: 你可以假设 n 不小于 2 且不大于 58。 
// Related Topics 数学 动态规划 
// 👍 347 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

class PL343{
    public static void main(String[] args) {
        Solution solution = new PL343().new Solution();
        // TO TEST
        System.out.println(solution.integerBreak(10));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // n 拆分 i*(n-i) 得到一个积，其中(n-i)可以继续拆分，重复子问题
        // 1. 递归 + 记忆化搜索
        // 2. 动态规划
        // dp[n]  n拆分后的最大值
        // 方程 dp[n] = max(i * (n - i), i * dp[n - i])
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;
            dp[2] = 1;
            // 从n=2开始计算，一直算到dp[n]
            for (int m = 3; m <= n; m++) {
                // int max = 0;
                // 计算每个n的dp
                for (int i = 1; i < m; i++) {
                    dp[m] = Math.max(dp[m], Math.max(i * (m - i), i * dp[m - i]));
                }
                // dp[m] = max;
            }
            return dp[n];
        }

        HashMap<Integer, Integer> cache = new HashMap<>();
        public int integerBreak04(int n) {
            if (n < 2) {
                return 0;
            }
            // if (n == 2) {
            //     return 1;
            // }
            if (cache.containsKey(n)) {
                return cache.get(n);
            }
            int max = 0;
            // 从1开始拆分到n-i
            for (int i = 1; i < n; i++) {
                max = Math.max(max, Math.max(i * (n - i), i * integerBreak04(n - i)));
            }
            cache.put(n, max);
            return max;
        }



        // n 拆分 j 和 [n-j] , 乘积方式有两种： max( j*[n-j] 和 j*integerBreak(n-j) )
        // j 从 1…n/2
        // 1.递归  超时
        // 优化  记忆化搜索

        // 2.动态规划
        // j * integerBreak(n-j) 中的integerBreak(n-j) 改为 dp[n-i] , 其实与记忆化搜索很类似了
        // dp[i] 整数i的最大乘积
        // dp[i] = max( j*[n-j], j*dp[n-j] )
        public int integerBreak03(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;
            // 从n=2开始计算，一直推出n
            for (int i = 2; i <= n; i++) {
                // 计算每个n
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                }
            }
            return dp[n];
        }


        HashMap<Integer, Integer> map = new HashMap<>();

        public int integerBreak01(int n) {
            if (n < 2) {
                return 0;
            }
            // if (n == 2) {
            //     return 1;
            // }
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int res = 0;
            for (int i = 1; i <= n / 2; i++) {
            // for (int i = 1; i < n - 1; i++) {
                res = Math.max(res, Math.max(i * (n - i), i * integerBreak01(n - i)));
            }
            map.put(n, res);
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

