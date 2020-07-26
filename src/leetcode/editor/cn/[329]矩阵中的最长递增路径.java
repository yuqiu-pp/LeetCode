//给定一个整数矩阵，找出最长递增路径的长度。 
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。 
//
// 示例 1: 
//
// 输入: nums = 
//[
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//] 
//输出: 4 
//解释: 最长递增路径为 [1, 2, 6, 9]。 
//
// 示例 2: 
//
// 输入: nums = 
//[
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//] 
//输出: 4 
//解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
// 
// Related Topics 深度优先搜索 拓扑排序 记忆化 
// 👍 282 👎 0

package leetcode.editor.cn;

class LC329{
    public static void main(String[] args) {
        Solution solution = new LC329().new Solution();
        // TO TEST
        System.out.println(solution.longestIncreasingPath(null));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int rows = 0;
        int cols = 0;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            rows = matrix.length;
            cols = matrix[0].length;
            int[][] mem = new int[rows][cols];
            int res = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    res = Math.max(res, dfs(matrix, i, j, mem));
                }
            }
            return res;
        }
        // 四个方向找比[i][j]小的元素，取最大值
        private int dfs(int[][] matrix, int i, int j, int[][] mem) {
            // 递归出口
            // 记忆化搜索
            if (mem[i][j] != 0) {
                return mem[i][j];
            }
            // 当前层    至少有自身的一个点
            mem[i][j] ++;
            // 下一层
            // 四个方向找比[i][j]小的元素，取最大值
            int max = mem[i][j];
            for (int[] dir : dirs) {
                int row = i + dir[0];
                int col = j + dir[1];
                if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row][col] > matrix[i][j]) {
                    max = Math.max(max, dfs(matrix, row, col, mem) + 1);
                }
            }
            mem[i][j] = max;
            // 恢复数据
            return mem[i][j];
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
