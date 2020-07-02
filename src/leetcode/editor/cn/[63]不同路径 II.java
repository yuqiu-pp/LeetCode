//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 说明：m 和 n 的值均不超过 100。 
//
// 示例 1: 
//
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
// Related Topics 数组 动态规划

package leetcode.editor.cn;

class LC63{
    public static void main(String[] args) {
        Solution solution = new LC63().new Solution();
        // TO TEST
        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(solution.uniquePathsWithObstacles(grid));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            // if (m > 0) {
                int n = obstacleGrid[0].length;
            // }
            // 第一个点就是障碍
            if (obstacleGrid[0][0] == 1) {
                return 0;
            }
            // 到达第一个点的
            obstacleGrid[0][0] = 1;
            // 边上点的处理：障碍点后的都为0，否则为1
            for (int i = 1; i < m; i++) {
                obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
            }
            for (int i = 1; i < n; i++) {
                obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
            }
            // 状态转移 obstacleGrid[i][j] = 如果是障碍=0， 否则=obstacleGrid[i-1][j] + obstacleGrid[i][j-1]
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        obstacleGrid[i][j] = 0;
                    } else {
                        obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                    }
                }
            }
            return obstacleGrid[m - 1][n - 1];
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
