//根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。 
//
// 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dea
//d）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律： 
//
// 
// 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； 
// 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活； 
// 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡； 
// 如果死细胞周围正好有三个活细胞，则该位置死细胞复活； 
// 
//
// 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生
//和死亡是同时发生的。 
//
// 
//
// 示例： 
//
// 输入： 
//[
//  [0,1,0],
//  [0,0,1],
//  [1,1,1],
//  [0,0,0]
//]
//输出：
//[
//  [0,0,0],
//  [1,0,1],
//  [0,1,1],
//  [0,1,0]
//] 
//
// 
//
// 进阶： 
//
// 
// 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。 
// 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？ 
// 
// Related Topics 数组

package leetcode.editor.cn;

import java.util.Arrays;


class LC289{
    public static void main(String[] args) {
        Solution solution = new LC289().new Solution();
        // TO TEST
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        solution.gameOfLife(board);
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
    }

    class Solution {
        public void gameOfLife(int[][] board) {
            int rows = board.length;
            int cols = board[0].length;
            // 遍历周边8个点的方向(下标)数组   . 可以封装为一个函数
            int[] idx = { -1, -1, -1, 0, 0, 1, 1, 1};
            int[] jdy = { -1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int sum = 0;
                    for (int k = 0; k < 8; k++) {
                        int x = i + idx[k];
                        int y = j + jdy[k];
                        if (x < 0 || y < 0 || x > rows-1 || y > cols-1) {
                            continue;
                        }
                        sum += board[x][y] & 0x01;
                    }
                    // if (sum < 2 || sum > 3) {
                        // 第2bit置0，可不操作
                    // }
                    if ((board[i][j] == 1 && sum == 2) || sum == 3) {
                        // 第2bit置1
                        board[i][j] |= 0x02;
                    }
                }
            }
            // 统一更新, 第2bit右移1位
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    board[i][j] >>= 1;
                }
            }
        }
    }

    class Solution02 {
        // 0 ms	37.7 MB
        public void gameOfLife(int[][] board) {
            // 拷贝数组，作为更新依据
            int row = board.length;
            int col = board[0].length;
            int[][] copy = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    copy[i][j] = board[i][j];
                }
            }
            // 访问结点周围8个位置
            int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int sum = 0;
                    for (int k = 0; k < 8; k++) {
                        int idx = i + dx[k];
                        int jdy = j + dy[k];
                        // 点是否存在
                        if (idx < 0 || jdy < 0 || idx > row-1 || jdy > col-1) {
                            continue;
                        }
                        sum += copy[idx][jdy];
                    }
                    // 更新board的数据
                    if (sum < 2 || sum > 3) {
                        board[i][j] = 0;
                    }
                    if ((board[i][j] == 1 && sum == 2) || sum == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    // 二维数组 一个节点遍历周围8个点的方法
    // 用两bit标识状态，以节约空间
    //leetcode submit region begin(Prohibit modification and deletion)
    // 0 ms	37.9 MB
    class Solution01 {
        // 因为新的更新会影响后面的状态，所以先缓存，最后统一更新
        // 用什么做缓存？要缓存3个值：i-j-01
        // 可选：另一个数组，空间开销
        //      Map<Map<i,j>,01>，太折腾，效率也不高
        //      因为状态只有01，可以用bit缓存。 bit0-原值，bit1-新值
        public void gameOfLife(int[][] board) {
            int rows = board.length;
            int cols = board[0].length;
            // 遍历周边8个点的方向(下标)数组   . 可以封装为一个函数
            // int[] idx = {0, 1, 0, -1, -1, -1, 1, 1};
            // int[] jdx = {1, 0, -1, 0, 1, -1, 1, -1};
            int[] idx = { -1, -1, -1, 0, 0, 1, 1, 1};
            int[] jdx = { -1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 周围8个点
                    int liveAmount = 0;
                    for (int k = 0; k < 8; k++) {
                        int x = i + idx[k];
                        int y = j + jdx[k];
                        if (x < 0 || y < 0 || x > rows - 1 || y > cols - 1) {
                            continue;
                        }
                        liveAmount += board[x][y] & 0x01;
                    }
                    // 判断条件
                    if (liveAmount < 2 || liveAmount > 3) {
                        board[i][j] &= 0xFD;
                    }
                    if ((board[i][j] == 1 && liveAmount == 2) || liveAmount == 3) {
                        board[i][j] |= 0x02;
                    }
                }
            }
            // 统一更新状态
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    board[i][j] >>= 1;
                }
            }
        }



        public void gameOfLife1(int[][] board) {
            // 复制board，因为新的更新会影响后面的状态
            // 扩充 外边界，这样计算sum时可以统一处理
            int rows = board.length;
            int cols = board[0].length;
            int[][] copyBoard = new int[rows+2][cols+2];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    copyBoard[i+1][j+1] = board[i][j];
                }
            }
            for (int i = 1; i < rows+1; i++) {
                for (int j = 1; j < cols+1; j++) {
                    // 周围8个点
                    int sum = copyBoard[i-1][j-1] + copyBoard[i-1][j] + copyBoard[i-1][j+1] +
                            copyBoard[i][j-1] + copyBoard[i][j+1] +
                            copyBoard[i+1][j-1] + copyBoard[i+1][j] + copyBoard[i+1][j+1];
                    // 变化条件
                    // if (sum < 2 || sum > 3){
                    //     board[i-1][j-1] = 0;
                    // }
                    // if (board[i-1][j-1]==1 && (sum==2 || sum==3)){
                    //     board[i-1][j-1] = 1;
                    // }
                    // if (sum>3){
                    //     board[i-1][j-1] = 0;
                    // }
                    // if (sum==3){
                    //     board[i-1][j-1] = 1;
                    // }
                    // 合并
                    if (sum < 2 || sum > 3){
                        board[i-1][j-1] = 0;
                    }
                    if ((board[i-1][j-1]==1 && sum==2) || sum==3){
                        board[i-1][j-1] = 1;
                    }
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
