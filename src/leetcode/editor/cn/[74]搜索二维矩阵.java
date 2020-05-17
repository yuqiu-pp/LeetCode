//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 示例 1: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false 
// Related Topics 数组 二分查找

package leetcode.editor.cn;

class LC74{
    public static void main(String[] args) {
        Solution solution = new LC74().new Solution();
        // TO TEST
        int[][] matrix = {  {1,   3,  5,  7},
                            {10, 11, 16, 20},
                            {23, 30, 34, 50}  };
        System.out.println(solution.searchMatrix(matrix, 3));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 二阶转化为一阶有序数组，用二分  row=i/n, col=i%n
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            if (m == 0) {
                return false;
            }
            // 必须先检查[0]是否有值
            int n = matrix[0].length;
            // 标准二分
            int left = 0;
            int right = m * n - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int row = mid / (n);
                int col = mid % (n);
                if (matrix[row][col] == target) {
                    return true;
                }
                if (matrix[row][col] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
