//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划

package leetcode.editor.cn;

class LC64{
    public static void main(String[] args) {
        Solution solution = new LC64().new Solution();
        // TO TEST
        // System.out.println(solution.minPathSum());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 状态 -- dp[][] 记录到每个点的数字总和
    // 转移 -- 递归，自顶向下  向下或向右  dp[i][j] = min(dp[i][j-1], dp[i-1][j])  + grid[i][j]
    //         递推，自底向上  向上或向左  dp[i][j] = min(dp[i][j+1], dp[i+1][j]) + grid[i][j]
    // 结果 -- 最后一个dp[][]
    public int minPathSum(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public int minPathSum01(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = grid.length -1 ; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j != grid[0].length - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (j == grid[0].length - 1 && i != grid.length - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (j != grid[0].length - 1 && i != grid.length - 1) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
