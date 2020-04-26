//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组

package leetcode.editor.cn;


import java.math.BigDecimal;
import java.util.Arrays;

class LC66{
    public static void main(String[] args) {
        Solution solution = new LC66().new Solution();
        // TO TEST
        int[] digits = {9,9};
        System.out.println(Arrays.toString(solution.plusOne(digits)));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    // 1. 数组元素独立计算。取模%判断是否需要进位；取整/
    // 2. int[]-> String -> BigDecimal -> int[] （charAt[i]-'0'）
    class Solution {
        public int[] plusOne03(int[] digits) {
            int len = digits.length;
            for (int i = len - 1; i >= 0; i--) {
                digits[i] ++;
                digits[i] = digits[i] % 10;
                if (digits[1] != 0) {
                    return digits;
                }
            }
            int[] res = new int[len + 1];
            res[0] = 1;
            return res;
        }

        // 变成Bigdecimal加1，再拆分成数组
        public int[] plusOne01(int[] digits) {
            StringBuilder num = new StringBuilder();
            for (int i : digits) {
                num.append(i);
            }
            // BigDecimal 无精度问题的两种方式：new BigDecimal("")、BigDecimal.valueOf(1))
            String str = String.valueOf(new BigDecimal(num.toString()).add(BigDecimal.valueOf(1)));
            int[] res = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                // res[i] = Integer.valueOf(String.valueOf(str.charAt(i)));
                res[i] = str.charAt(i) - '0';
            }
            return res;
        }

        public int[] plusOne(int[] digits) {
            StringBuilder num = new StringBuilder();
            for (int i : digits) {
                num.append(i);
            }
            String plus = String.valueOf(new BigDecimal(num.toString()).add(BigDecimal.valueOf(1)));
            int[] res = new int[plus.length()];
            for (int i = 0; i < plus.length(); i++) {
                res[i] = plus.charAt(i) - '0';
            }
            return res;
        }

        public int[] plusOne02(int[] digits) {
            int len = digits.length;
            for (int i = digits.length - 1; i >= 0; i--) {
                digits[i] ++;
                digits[i] = digits[i] % 10;
                if (digits[i] != 0) {
                    return digits;
                }
            }
            // 需要扩展位数. 因为只有第一位是1，后面的都是0，所以不需要拷贝
            int[] res = new int[len + 1];
            res[0] = 1;
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}
