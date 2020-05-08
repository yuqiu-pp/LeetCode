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


import java.util.Stack;

class LC1021{
    public static void main(String[] args) {
        Solution solution = new LC1021().new Solution();
        // TO TEST
        System.out.println(solution.removeOuterParentheses01("(()())(())(()(()))"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeOuterParentheses(String S) {
            Stack<Character> stack = new Stack<>();
            StringBuilder res = new StringBuilder();
            // 左括号压栈
            // 右括号出栈，出栈时如果栈空，substring加入结果String中，这种方式不是最优

            int count = 0;
            for (int i = 0; i < S.length(); i++) {
                char ch = S.charAt(i);
                // 左括号压栈，除了栈空后的第一个左括号外，其余都可以直接入结果String中
                // 优化1：其实这里的 栈只是用来记录左右的匹配关系，所以可以用一个变量代替
                // 优化2：两个if合并
                // if (ch == '(') {
                    // stack.push(ch);  优化1
                    // count ++;
                    // if (stack.size() > 1) {
                    // if (count > 1) {
                    //     res.append(ch);
                    // }
                // }
                if (ch == '(' && ++count > 1) {
                    res.append(ch);
                }
                // 右括号出栈，除了栈空前的最后一个右括号外，其余都可以直接入结果String中
                // else {
                    // if (stack.size() > 1) {
                    // if (count > 1) {
                    //     res.append(ch);
                    // }
                    // stack.pop();
                    // count --;
                // }
                if (ch == ')' && count-- > 1) {
                    res.append(ch);
                }
            }
            return res.toString();
        }

        // 栈空 ：代表一个原语
        // 去外层括号 ：利用栈空时，一定是最外层括号，把位置记录，然后再删除
        // 优化：用计数+-后=0 相当于栈空  与1112题类似
        // 优化：按字母插入新string
        public String removeOuterParentheses03(String S) {
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
