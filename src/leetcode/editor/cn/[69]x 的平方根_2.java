//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找

package leetcode.editor.cn;

class LC69{
    public static void main(String[] args) {
        Solution solution = new LC69().new Solution();
        // TO TEST
        System.out.println(solution.mySqrt(8));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 结果在(0, x) 之间，转化为在区间内查找targe
        // 满足二分条件
        public int mySqrt(int x) {
            // 特殊值忘了
            if (x == 0 || x == 1) {
                return x;
            }

            long left = 0;
            long right = x;
            while (left <= right) {
                // 防止溢出  要用long  不能用int
                long mid = left + (right - left) / 2;
                // 这种是不精确的，不能这么判断
                // if (mid * mid == x) {
                //     return mid;
                // }
                if (mid * mid < x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // 一定有值，不会返回 -1
            // return -1;
            // 为什么返回right ？ 因为循环结束的条件是  left > right, 题目要求的结果是取整数下界
            return (int) right;
        }

        // 结果在 (0, x) 之间，单调区间，可以用索引取值
        public int mySqrt01(int x) {
            if (x == 0 || x == 1) {
                return x;
            }
            long l = 1;
            long r = x;
            while (l <= r) {
                // 防止溢出
                long mid = l + (r - l) / 2;
                if (mid * mid > x) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // 为什么返回right ？
            return (int) r;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
