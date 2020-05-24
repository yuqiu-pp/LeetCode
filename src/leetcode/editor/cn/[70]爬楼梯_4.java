//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划

package leetcode.editor.cn;

class LC70{
    public static void main(String[] args) {
        Solution solution = new LC70().new Solution();
        // TO TEST
        System.out.println(solution.climbStairs(45));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    // 第一步走1，则总的可能性f(n-1)；如果第一步走2，则总的可能性f(n-2)
    // 递推公式 f(n) = f(n-1) + f(n-2), 斐波那契数
    class Solution {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            int first = 1;
            int second = 2;
            for (int i = 3; i <= n; i++) {
                int third = first + second;
                first = second;
                second = third;
            }
            return second;
        }
        // 注意 出口和起始条件
        // public int climbStairs(int n) {
        //     if (n < 2) {
        //         return n;
        //     }
        //     int[] dp = new int[n];
        // }

        public int climbStairs05(int n) {
            if (n == 1) {
                return 1;
            }
            int first = 1;
            int second = 2;
            for (int i = 3; i <= n; i++) {
                int third = first + second;
                first = second;
                second = third;
            }
            return second;
        }

        public int climbStairs04(int n) {
            if (n == 1) {
                return 1;
            }
            int first = 1;
            int second = 2;
            for (int i = 3; i <= n; i++) {
                int third = first + second;
                first = second;
                second = third;
            }
            return second;
        }

        // 超时
        public int climbStairs01(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            return climbStairs01(n-1) + climbStairs01(n-2);
        }

        public int climbStairs03(int n) {
            if (n == 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            return dp[n];
        }

        public int climbStairs02(int n) {
            if (n == 1) {
                return 1;
            }
            int first = 1;
            int second = 2;
            for (int i = 3; i <= n; i++) {
                int third = first + second;
                first = second;
                second = third;
            }
            return second;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
