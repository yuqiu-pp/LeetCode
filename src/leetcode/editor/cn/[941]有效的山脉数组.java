//给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。 
//
// 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组： 
//
// 
// A.length >= 3 
// 在 0 < i < A.length - 1 条件下，存在 i 使得：
// 
// A[0] < A[1] < ... A[i-1] < A[i] 
// A[i] > A[i+1] > ... > A[A.length - 1] 
// 
// 
// 
//
// 
//
// 
//
// 
//
// 示例 1： 
//
// 输入：[2,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：[3,5,5]
//输出：false
// 
//
// 示例 3： 
//
// 输入：[0,3,2,1]
//输出：true 
//
// 
//
// 提示： 
//
// 
// 0 <= A.length <= 10000 
// 0 <= A[i] <= 10000 
// 
//
// 
//
// 
// Related Topics 数组 
// 👍 70 👎 0

package leetcode.editor.cn;

class PL941{
    public static void main(String[] args) {
        Solution solution = new PL941().new Solution();
        // TO TEST
        System.out.println(solution.validMountainArray(new int[]{0,1,1,3,1}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1. 遍历数组  先升序，然后降序
        // 2. 官方， 思路一致，代码简练
        public boolean validMountainArray(int[] A) {
            int len = A.length;
            int i = 0;
            while (i + 1 < len && A[i] < A[i + 1]) {
                i ++;
            }
            if (i == 0 || i == len -1) {
                return false;
            }
            while (i + 1 < len && A[i] > A[i + 1]) {
                i ++;
            }
            return i == len - 1;
        }

        public boolean validMountainArray01(int[] A) {
            if (A.length < 3) {
                return false;
            }
            int maxIndex = 0;
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] >= A[i + 1]) {
                    maxIndex = i;
                    break;
                }
            }
            // 没有递减的点
            if (maxIndex == 0) {
                return false;
            }
            for (int i = maxIndex; i < A.length - 1; i++) {
                if (A[i] <= A[i + 1]) {
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

