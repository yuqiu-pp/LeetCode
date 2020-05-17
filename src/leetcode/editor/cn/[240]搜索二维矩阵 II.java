//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// [
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
// Related Topics 二分查找 分治算法

package leetcode.editor.cn;

class LC240{
    public static void main(String[] args) {
        Solution solution = new LC240().new Solution();
        // TO TEST
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int[][] nul = {{}};
        System.out.println(solution.searchMatrix(nul, 20));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 每列的元素从上到下升序排列  利用这点先找到targe数量哪行，也可以二分查找
        // 每行的元素从左到右升序排列  每行进行二分查找  m * log n
        public boolean searchMatrix(int[][] matrix, int target) {
            for (int row = 0; row < matrix.length; row++) {
                int len = matrix[row].length;
                // 二分查找
                int left = 0;
                int right = matrix[row].length - 1;
                while (left <= right) {
                    int mid  = left + (right - left) / 2;
                    if (target == matrix[row][mid]) {
                        return true;
                    }
                    if (target < matrix[row][mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
