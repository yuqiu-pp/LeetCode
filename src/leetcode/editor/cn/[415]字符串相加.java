//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 注意： 
//
// 
// num1 和num2 的长度都小于 5100. 
// num1 和num2 都只包含数字 0-9. 
// num1 和num2 都不包含任何前导零。 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。 
// 
// Related Topics 字符串 
// 👍 227 👎 0

package leetcode.editor.cn;

import java.util.Stack;

class LC415{
    public static void main(String[] args) {
        Solution solution = new LC415().new Solution();
        // TO TEST
        System.out.println(solution.addStrings("12", "197"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 栈存储每位的计算结果
        // 两个指针从字符串尾部开始遍历
        // [i] = ch1 + ch2 + 进位
        public String addStrings(String num1, String num2) {
            int m = num1.length() - 1;
            int n = num2.length() - 1;
            // 进位
            int carry = 0;
            // 反转的实现：sb.append() sb.reverse();  或者 sb.insert(0, a)
            StringBuilder sb = new StringBuilder();
            //    0 2 3 4
            //  + 0 9 1 9    短字符可以看做前面补0，这样就能统一处理
            while (m >= 0 || n >= 0 || carry == 1) {
                int a = m >= 0 ? (num1.charAt(m) - '0') : 0;
                int b = n >= 0 ? (num2.charAt(n) - '0') : 0;
                int sum = a + b + carry;
                sb.insert(0, sum % 10);
                carry = sum / 10;
            }
            return sb.toString();
        }


        public String addStrings01(String num1, String num2) {
            int m = num1.length();
            int n = num2.length();
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            int res = 0;
            while (m > 0 && n > 0) {
                int ch1 = num1.charAt(m - 1) - '0';
                int ch2 = num2.charAt(n - 1) - '0';
                res = ch1 + ch2 + stack.pop();
                stack.push(res % 10);
                stack.push(res / 10);
                m --;
                n --;

            }
            // 剩余的子字符
            while (m > 0) {
                int ch1 = num1.charAt(m - 1) - '0';
                res = ch1 + stack.pop();
                stack.push(res % 10);
                stack.push(res / 10);
                m --;
            }
            while (n > 0) {
                int ch1 = num2.charAt(n - 1) - '0';
                res = ch1 + stack.pop();
                stack.push(res % 10);
                stack.push(res / 10);
                n --;
            }
            StringBuilder str = new StringBuilder();
            res = stack.pop();
            if (res != 0) {
                str.append(res);
            }
            while (!stack.isEmpty()) {
                str.append(stack.pop());
            }
            return str.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
