//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//11110
//11010
//11000
//00000
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//11000
//11000
//00100
//00011
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class LC200{
    public static void main(String[] args) {
        Solution solution = new LC200().new Solution();
        // TO TEST
        char[][] grid = {{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0'}, {'0','0','0','1','1'}};
        System.out.println(solution.numIslands(new char[][]{}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 每个点都遍历所有能到达的为1的点，没有1时，为一个岛屿
        public int numIslands(char[][] grid) {
            // 这里用取值的方式，就要先判断grid!=null && gird.length>0
            // 直接在双重循环中用 i < ngrid.length，则可以省略上面的判断
            // int n = grid.length;
            // int m = grid[0].length;

            // 遍历过，直接置0就可以
            // int[][] visited = new int[n][n];
            int res = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        res ++;
                        dfs(grid, i, j);
                        // bfs(grid, i, j);
                    }
                }
            }
            return res;
        }
        // 找出当前点是所有子节点，加入队列，依次置0
        private void bfs(char[][] grid, int i, int j) {
            int r = grid.length;
            int c = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{i,j});
            while (!queue.isEmpty()) {
                int n = queue.size();
                for (int k = 0; k < n; k++) {
                    int[] poll = queue.poll();
                    int row = poll[0];
                    int col = poll[1];
                    // 相邻是4个方向
                    if (row + 1 < r && grid[row + 1][col] == '1') {
                        queue.offer(new int[]{row +1, col});
                        grid[row + 1][col] = '0';
                    }
                    if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                        queue.offer(new int[]{row - 1, col});
                        grid[row - 1][col] = '0';
                    }
                    if (col + 1 < c && grid[row][col + 1] == '1') {
                        queue.offer(new int[]{row, col + 1});
                        grid[row][col + 1] = '0';
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                        queue.offer(new int[]{row, col - 1});
                        grid[row][col - 1] = '0';
                    }
                }
            }
        }
        // 向4个方向递归的去把所有能到达的点置0
        private void dfs(char[][] grid, int i, int j) {
            int r = grid.length;
            int c = grid[0].length;
            if (i < 0 || j < 0 || i >= r || j >= c || grid[i][j] == '0') {
                return;
            }
            // 当前层逻辑
            grid[i][j] = '0';
            // 下一层：4个方向
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
