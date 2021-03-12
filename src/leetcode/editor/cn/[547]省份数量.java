//
// 
// 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连
//。 
//
// 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。 
//
// 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 
//isConnected[i][j] = 0 表示二者不直接相连。 
//
// 返回矩阵中 省份 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 200 
// n == isConnected.length 
// n == isConnected[i].length 
// isConnected[i][j] 为 1 或 0 
// isConnected[i][i] == 1 
// isConnected[i][j] == isConnected[j][i] 
// 
// 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 398 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class PL547{
    public static void main(String[] args) {
        Solution solution = new PL547().new Solution();
        // TO TEST
        System.out.println(solution.findCircleNum02(new int[][]{{1,0,1},{1,1,0},{0,0,1}}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // {1,0,1}, 行表示和A有关联的结点，所以都要扫描，每个点都要DFS和入BFS的队列
        // {1,1,0},
        // {0,0,1}}
        // 1. 深度优先
        // 2. 广度优先  每个城市作为跟节点，广度优先遍历，相邻为1入队列，遍历结束res++
        // 3. 并查集
        public int findCircleNum(int[][] M) {
            int[] parent = new int[M.length];
            Arrays.fill(parent, -1);
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if (M[i][j] == 1 && i != j) {
                        union(parent, i, j);
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == -1) {
                    count ++;
                }
            }
            return count;
        }
        // hash表维护关系链，value = 关联点的key
        // 数组作为简单的hash表，index作为key
        // parent是根据矩阵所有元素值建立
        private int find(int parent[], int i) {
            if (parent[i] == -1) {
                return i;
            }
            return find(parent, parent[i]);
        }
        // 合并到一个集合
        private void union(int parent[], int x, int y) {
            int xset = find(parent, x);
            int yset = find(parent, y);
            if (xset != yset) {
                parent[xset] = yset;
            }
        }


        public int findCircleNum02(int[][] isConnected) {
            int n = isConnected.length;
            boolean[] isVisited = new boolean[n];
            int res = 0;
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (!isVisited[i]) {
                    queue.offer(i);
                    while (!queue.isEmpty()) {
                        int j = queue.poll();
                        isVisited[j] = true;
                        for (int k = 0; k < n; k++) {
                            if (isConnected[j][k] == 1 && !isVisited[k]) {
                                queue.offer(k);
                            }
                        }
                    }
                    res ++;
                }
            }

            return res;
        }

        public int findCircleNum01(int[][] isConnected) {
            int n = isConnected.length;
            boolean[] isVisited = new boolean[n];
            int res = 0;
            for (int i = 0; i < n; i++) {
                // 没有访问过
                if (!isVisited[i]) {
                    // 能关联到的都置为true，作为一个省
                    // 因为矩阵中自己和自己是1，所以至少会有一个省，res可以直接++
                    dfs(isConnected, isVisited, n, i);
                    res ++;
                }
            }
            return res;
        }
        private void dfs(int[][] connected, boolean[] visited, int n, int i) {
            for (int j = 0; j < n; j++) {
                if (connected[i][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    dfs(connected, visited, n, j);
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

