//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LC51{
    public static void main(String[] args) {
        Solution solution = new LC51().new Solution();
        // TO TEST
        System.out.println(solution.solveNQueens(4));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> res = new ArrayList<>();

        // 放好一层后，进入第二层放
        public List<List<String>> solveNQueens(int n) {
            char[][] board = new char[n][n];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    board[i][j] = '.';
                }
            }
            backtrack(0, n, board);
            return res;
        }
        // row 当前是第几层 , level
        // n 总共有几层,  board.length
        // board 棋盘
        private void backtrack(int row, int n, char[][] board) {
            // 终止条件
            if (row >= n) {
                res.add(construct(board));
                return;
            }
            // 当前层逻辑：按顺序从n列中选一个合法位置
            for (int col = 0; col < n; col++) {
                if (isValid(row, col, board)) {
                    board[row][col] = 'Q';
                    // 进入下一层
                    backtrack(row + 1, n, board);
                    // 清除当前层状态
                    board[row][col] = '.';
                }
            }
        }

        private List<String> construct(char[][] board) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                String s = new String(board[i]);
                list.add(s);
            }
            return list;
        }

        // 只有当前row之上的3条线
        private boolean isValid(int row, int col, char[][] board) {
            // 检查同一列是否有Q
            for (int i = 0; i < row; i++) {
                if (board[i][col] == 'Q') {
                    return false;
                }
            }
            // 左上  row，col -- ，直到有一个为0
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0 ; i--, j--) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }
            // 右上
            for (int i = row - 1, j = col + 1; i >= 0 && j < board.length ; i--, j++) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }
        // O(n*n) 复杂度(6 ms)，比isValid (3ms) 慢
        private boolean validate(int row, int col, char[][] board) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 'Q' && (col - j == row - i || j - col == row - i || j == col)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
