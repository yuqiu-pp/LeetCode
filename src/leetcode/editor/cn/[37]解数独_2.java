//编写一个程序，通过已填充的空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// Note: 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法

package leetcode.editor.cn;


import java.util.Arrays;

class LC37{
    public static void main(String[] args) {
        Solution solution = new LC37().new Solution();
        // TO TEST
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        solution.solveSudoku(board);
        Arrays.asList(board).forEach(System.out::println);
        // System.out.println(Arrays.asList(board));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solveSudoku(char[][] board) {
            if (board == null || board.length == 0) return;
            backtrack01(0, board);
        }
        private boolean backtrack01(int row, char[][] board) {
            // 每行开始，每列都试一下，如果合法，向下一行递归
            for (int i = row; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == '.') {
                        // 1-9 每个数字都试一下，如何合法向下一行递归
                        for (char k = '1'; k <= '9'; k++) {
                            if (isValid01(board, i, j, k)) {
                                board[i][j] = k;
                                // 当前行每列都有填数，所以不能i+1
                                if (backtrack01(i, board)) {
                                    // ?
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        // 9个数都尝试了，返回失败
                        return false;
                    }
                }
            }
            return true;
        }
        // 每行都从0开始
        private boolean backtrack(int row, char[][] board) {
            // 递归出口
            // 当前层  0-9选一个放
            for (int i = row; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        for (char c = '1'; c <= '9'; c++) {
                            // char tmp = board[i][j];
                            // board[i][j] = c;
                            // Arrays.asList(board[row]).forEach(System.out::println);
                            // 将c作为参数传入，
                            if(isValid(board, i, j, c)) {
                                board[i][j] = c;
                                if (backtrack(i, board)) {
                                    return true;
                                } else {
                                    // 还原
                                    board[i][j] = '.';
                                }
                            }
                            // board[i][j] = tmp;
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        // row,col  字符c要填的位置
        // 判断放在该位置后是否合法：列是否有与c相同字符、行是否有、宫格内是否有
        private boolean isValid01(char[][] board, int row, int col, char c) {
            for (int i = 0; i < 9; i++) {
                // row行中的9列是否有与c相同
                if (board[row][i] == c) {
                    return false;
                }
                // col列中的每行是否有
                if (board[i][col] == c) {
                    return false;
                }
                // 宫格内9个位置  坐标转化  row=i/3 col=i%3
                int x = 3 * (row/3) + i/3;
                int y = 3 * (col/3) + i%3;
                if (board[x][y] == c) {
                    return false;
                }
            }
            return true;
        }

        private boolean isValid(char[][] board, int row, int col, char c){
            for(int i = 0; i < 9; i++) {
                if(board[i][col] != '.' && board[i][col] == c) return false; //check row
                if(board[row][i] != '.' && board[row][i] == c) return false; //check column
                if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                        board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
            }
            return true;
        }


        // 判断整个棋盘是否合法，其实只需要判断填数位置即可
        private boolean isAvailable(char[][] board) {
            int[][] row = new int[9][9];
            int[][] col = new int[9][9];
            int[][] box = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        // n 也用0-9表示
                        int n = board[i][j] - '0' - 1;
                        int r = i / 3 * 3 + j / 3;
                        if (row[i][n] == 1 || col[j][n] == 1 || box[r][n] == 1) {
                            return false;
                        }
                        row[i][n] = 1;
                        col[j][n] = 1;
                        box[r][n] = 1;
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
