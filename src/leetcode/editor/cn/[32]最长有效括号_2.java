//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划

package leetcode.editor.cn;

import java.util.Stack;

class LC32{
    public static void main(String[] args) {
        Solution solution = new LC32().new Solution();
        // TO TEST
        System.out.println(solution.longestValidParentheses("()(()"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {

            return 0;
        }





        // s[i]=左  dp[i] = dp[i-1]
        // s[i]=右  如果s[i-1]=左，有效括号   dp[i]=dp[i-2]+2
        //          如果s[i-1]=右，要找是否有匹配的左括号，如何找？
        //  dp[i-1]是s[i-1]位置的有效括号数，跳过它，如果前面s[i-len-1]=左，则匹配, dp[i]=dp[i-len-2]+2
        //                                                             否则，dp[i]=dp[i-1]
        public int longestValidParentheses04(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] chars = s.toCharArray();
            int[] dp = new int[chars.length];
            dp[0] = 0;
            int max = 0;
            for (int i = 1; i < chars.length; i++) {
                // 求的是某段的最大值，有max记录，且括号匹配时，+2，所以左括号时不做计算
                if (chars[i] == '(') {
                    // dp[i] = dp[i - 1];
                } else {
                    if (chars[i - 1] == '(') {
                        dp[i] = i < 2 ? 2 : dp[i - 2] + 2;
                    } else {
                        if (i >= dp[i - 1] + 1 && chars[i - dp[i - 1] - 1] == '(') {
                            dp[i] = dp[i - 1] + ((i - dp[i - 1]) > 2 ? dp[i - dp[i - 1] - 2] + 2 : 2);
                        }
                        // 没有匹配到左括号的右括号，说明是个分界点，不构成连续合法串的分界点
                        else {
                            // dp[i] = dp[i - 1];
                        }
                    }
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        // s[i] = ), s[i-1]=(,  dp[i]=dp[i-2]+2
        //         , s[i-1]=),  dp[i]=dp[i-1] + dp[i-dp[i-1] -2 ]
        public int longestValidParentheses03(String s) {
            int[] dp = new int[s.length()];
            char[] chars = s.toCharArray();
            int max = 0;
            for (int i = 1; i < s.length(); i++) {
                if (chars[i] == ')') {
                    if (chars[i - 1] == '(') {
                        dp[i] = i > 2 ? dp[i - 2] + 2 : 2;
                    }
                    else if (i - dp[i - 1] > 0 && chars[i - dp[i - 1] - 1] == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) > 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        // 栈  () 时，长度+2
        // 合法子串的定义："()(()" -> "()"
        public int longestValidParentheses02(String s) {
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int max = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        stack.push(i);
                    } else {
                        // i - stack.peek()  字符串长度
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
            return max;
        }

        // dp[i]  当前位置最长有效子串长度
        // 方程    s[i]=(   dp[i] = dp[i-1]
        //        s[i]=)   s[i-1] = (    dp[i] = dp[i-1] + 2
        //                 s[i-1] = )    dp[i] =


        // dp[i] i结尾时，最大有效字符串长度
        // 状态转移  s.charAt(i) == ')'时，
        //          1.s[i - 1] == '('， dp[i] = dp[i - 2] + 2;
        //          2.s[i - 1] == ')', dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
        public int longestValidParentheses01(String s) {
            int max = 0;
            int dp[] = new int[s.length()];
            // for的起始值 要注意
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    }
                    // 这里的s.charAt(i - dp[i - 1] - 1) == '(') 不能丢
                    else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) > 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
