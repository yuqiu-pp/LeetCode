//有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"
//，"(())()" 和 "(()(()))" 都是有效的括号字符串。 
//
// 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。 
//
// 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。 
//
// 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。 
//
// 
//
// 示例 1： 
//
// 输入："(()())(())"
//输出："()()()"
//解释：
//输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
//删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。 
//
// 示例 2： 
//
// 输入："(()())(())(()(()))"
//输出："()()()()(())"
//解释：
//输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
//删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
// 
//
// 示例 3： 
//
// 输入："()()"
//输出：""
//解释：
//输入字符串为 "()()"，原语化分解得到 "()" + "()"，
//删除每个部分中的最外层括号后得到 "" + "" = ""。
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 10000 
// S[i] 为 "(" 或 ")" 
// S 是一个有效括号字符串 
// 
// Related Topics 栈

package leetcode.editor.cn;

import com.sun.codemodel.internal.JForEach;

import java.util.Stack;

class LC1021{
    public static void main(String[] args) {
        Solution solution = new LC1021().new Solution();
        // TO TEST
        System.out.println(solution.removeOuterParentheses("(()())(())(()(()))"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 栈空 ：代表一个原语
        // 去外层括号 ：利用栈空时，一定是最外层括号，把位置记录，然后再删除
        // 优化：用计数+-后=0 相当于栈空  与1112题类似
        // 优化：按字母插入新string
        public String removeOuterParentheses(String S) {
            StringBuilder s = new StringBuilder();
            int opened = 0; // 相当于记录栈的深度
            for (char c : S.toCharArray()) {
                if (c == '(' && opened++ > 0) {
                    s.append(c);
                }
                if (c == ')' && opened-- > 1) {
                    s.append(c);
                }
            }
            return s.toString();
        }
        public String removeOuterParentheses02(String S) {
            int stack = 0;
            StringBuilder res = new StringBuilder();
            int start = 1;
            for (int i = 0; i < S.length(); i++) {
                char ch = S.charAt(i);
                if (ch == '(') {
                    stack++;
                } else {
                    stack--;
                    if (stack == 0) {
                        res.append(S.substring(start, i));
                        start += 2;
                    }
                }
            }
            return res.toString();
        }

        public String removeOuterParentheses01(String S) {
            Stack<Character> stack = new Stack<>();
            StringBuilder res = new StringBuilder();
            int start = 1;
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                if (c == '(') {
                    stack.push(c);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res.append(S.substring(start, i));
                        start = i+2;
                    }
                }
            }
            return res.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
