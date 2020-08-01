//
//
// 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿
//过地下城并通过对抗恶魔来拯救公主。 
//
// 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。 
//
// 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么
//包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。 
//
// 为了尽快到达公主，骑士决定每次只向右或向下移动一步。 
//
// 
//
// 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。 
//
// 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。 
//
// 
// 
// -2 (K) 
// -3 
// 3 
// 
// 
// -5 
// -10 
// 1 
// 
// 
// 10 
// 30 
// -5 (P) 
// 
// 
//
//
// 
//
// 说明: 
//
// 
// 
// 骑士的健康点数没有上限。 
// 
// 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。 
// Related Topics 二分查找 动态规划

package leetcode.editor.cn;

class LC174{
    public static void main(String[] args) {
        Solution solution = new LC174().new Solution();
        // TO TEST
        int[][] nums = {{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
        System.out.println(solution.calculateMinimumHP(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 抽象：启点到终点 路径和的最小值
        // 与m * n题类似
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon == null || dungeon.length == 0) {
                return 0;
            }
            int rows = dungeon.length;
            int cols = dungeon[0].length;
            int[][] dp = new int[rows][cols];
            // 终点时剩余1时，那么起点需要的值最小
            dp[rows - 1][cols - 1] = Math.max(1 - dungeon[rows - 1][cols - 1], 1);
            // 处理 边
            for (int i = rows - 2; i >= 0; i--) {
                dp[i][cols - 1] = Math.max(dp[i + 1][cols - 1] - dungeon[i][cols - 1], 1);
            }
            for (int i = cols - 2; i >= 0; i--) {
                dp[rows - 1][i] = Math.max(dp[rows - 1][i + 1] - dungeon[rows - 1][i], 1);
            }
            //
            for (int i = rows - 2; i >= 0 ; i--) {
                for (int j = cols - 2; j >= 0 ; j--) {
                    // 用max，因为要通过点，血必须够用
                    dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
                }
            }
            return dp[0][0];
        }

        // dp[i][j] 表示从坐标 (i,j)到终点所需的最小初始值
        // 要能通过格子，则 dp - dungeon > 0，且dp至少为1，所以dp = max(dp - dungeon, 1)
        // 点分两类：边界上的点，来源只有一个方向
        //          其它点两个来源， dp[i + 1][j], dp[i][j+1]) ，要取min
        // dp[i][j] = max(min(dp[i + 1][j], dp[i][j+1]), 1)
        public int calculateMinimumHP02(int[][] dungeon) {
            if (dungeon.length == 0 || dungeon[0].length == 0) {
                return 0;
            }
            int m = dungeon.length;
            int n = dungeon[0].length;
            int dp[][] = new int[m][n];
            dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
            for (int i = m - 2; i >= 0; i--) {
                dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
            }
            for (int i = n - 2; i >= 0; i--) {
                dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 1);
            }
            for (int i = m - 2; i >= 0; i--) {
                for (int j = n - 2; j >= 0; j--) {
                    dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j+1]) - dungeon[i][j], 1);
                    // int up = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                    // int left = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                    // dp[i][j] = Math.min(up, left);
                }
            }
            return dp[0][0];
        }

        public int calculateMinimumHP01(int[][] dungeon) {
            if (dungeon.length == 0 || dungeon[0].length == 0) {
                return 0;
            }
            int dp[][] = new int[dungeon.length][dungeon[0].length];
            dp[0][0] = dungeon[0][0];

            for (int i = 1; i < dungeon.length; i++) {
                dp[0][i] = dp[0][i - 1] + dungeon[0][i];
            }
            for (int i = 1; i < dungeon[0].length; i++) {
                dp[i][0] = dp[i - 1][0] + dungeon[i][0];
            }
            for (int i = 1; i < dungeon.length; i++) {
                for (int j = 1; j < dungeon[0].length; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            int min = dp[dungeon.length - 1][dungeon[0].length - 1];
            return min > 0 ? min : -min;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
