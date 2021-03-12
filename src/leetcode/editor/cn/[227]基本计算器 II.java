//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 
//
// 整数除法仅保留整数部分。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "3+2*2"
//输出：7
// 
//
// 示例 2： 
//
// 
//输入：s = " 3/2 "
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：s = " 3+5 / 2 "
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 
// s 表示一个 有效表达式 
// 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内 
// 题目数据保证答案是一个 32-bit 整数 
// 
// 
// 
// Related Topics 栈 字符串 
// 👍 283 👎 0

package leetcode.editor.cn;

import java.util.Stack;

class PL227{
    public static void main(String[] args) {
        Solution solution = new PL227().new Solution();
        // TO TEST
        System.out.println(solution.calculate("3-2*2"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 利用栈
        // 数字和+压栈
        // -压栈负数
        // 乘除出栈计算，结果压栈
        public int calculate(String s) {
            Stack<Integer> stack = new Stack<>();
            int n = s.length();

            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if (ch == ' ') {
                    continue;
                }

                if (Character.isDigit(ch)) {

                } else {
                    // 运算符
                    if (ch == '+')
                    stack.push(ch);
                }

                switch (ch) {
                    case '+':

                }
            }
        }
        public int calculate01(String s) {
            Stack<Integer> stack = new Stack<>();
            char preSign = '+';
            int num = 0;
            int n = s.length();

            for (int i = 0; i < n; i++) {
                if (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                }
                // 当前是非数字
                if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                    switch (preSign){
                        case '+':
                            stack.push(num);
                            break;
                        case '-':
                            stack.push(-num);
                            break;
                        case '*':
                            stack.push(stack.pop() * num);
                            break;
                        default:
                            stack.push(stack.pop() / num);
                    }
                    //
                    preSign = s.charAt(i);
                    num = 0;
                }
            }
            int res = 0;
            while (!stack.isEmpty()) {
                res += stack.pop();
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

