//给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。 
//
// 
//
// 示例 1： 
//
// 输入：[-4,-1,0,3,10]
//输出：[0,1,9,16,100]
// 
//
// 示例 2： 
//
// 输入：[-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// -10000 <= A[i] <= 10000 
// A 已按非递减顺序排序。 
// 
// Related Topics 数组 双指针

package leetcode.editor.cn;

import java.util.Arrays;

class LC977{
    public static void main(String[] args) {
        Solution solution = new LC977().new Solution();
        // TO TEST
        int[] nums = {-4,-1,0,3,10};
        System.out.println(Arrays.toString(solution.sortedSquares(nums)));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortedSquares(int[] A) {
            int l = 0;
            int r = A.length - 1;
            int[] res = new int[A.length];
            int i = res.length - 1;
            // 相等的时候也要处理，否则可能少算一个值
            while (l <= r) {
                int m = A[l] * A[l];
                int n = A[r] * A[r];
                if (m > n) {
                    res[i--] = m;
                    l++;
                } else {
                    res[i--] = n;
                    r--;
                }
            }
            return res;
        }


        // 由于有序，平方大的数一定在两端，从两侧遍历，谁大谁进新数组(从尾部开始插入)
        public int[] sortedSquares01(int[] A) {
            int len = A.length;
            int[] res = new int[A.length];
            int l = 0;
            int r = len - 1;
            int i = r;
            while (l <= r) {
                if (A[l]*A[l] < A[r]*A[r]) {
                    res[i--] = A[r]*A[r];
                    r--;
                } else {
                    res[i--] = A[l]*A[l];
                    l++;
                }
            }
            return res;
        }

        public int[] sortedSquares00(int[] A) {
            int n = A.length;
            int[] res = new int[n];
            int i = 0;
            int j = n-1;
            for (int k = n-1; k >= 0; k--) {
                if (Math.abs(A[i]) > Math.abs(A[j])) {
                    res[k] = A[i] * A[i];
                    i++;
                } else {
                    res[k] = A[j] * A[j];
                    j--;
                }
            }
            return res;
            }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
