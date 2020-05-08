//在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。 
//
// 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。 
//
// 返回可以通过分割得到的平衡字符串的最大数量。 
//
// 
//
// 示例 1： 
//
// 输入：s = "RLRRLLRLRL"
//输出：4
//解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
// 
//
// 示例 2： 
//
// 输入：s = "RLLLLRRRLR"
//输出：3
//解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
// 
//
// 示例 3： 
//
// 输入：s = "LLLLRRRR"
//输出：1
//解释：s 只能保持原样 "LLLLRRRR".
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s[i] = 'L' 或 'R' 
// 
// Related Topics 贪心算法 字符串

package leetcode.editor.cn;

import java.util.Stack;

class LC1221{
    public static void main(String[] args) {
        Solution solution = new LC1221().new Solution();
        // TO TEST
        System.out.println(solution.balancedStringSplit("RLRRLLRLRL"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int balancedStringSplit(String s) {
            int stack = 0;
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                // if (ch == 'R') {
                //     stack++;
                // } else {
                //     stack--;
                //     if (stack == 0) {
                //         res++;
                //     }
                // }
                stack += ch == 'R' ? 1 : -1;
                if (stack == 0) {
                    res++;
                }
            }
            return res;
        }

        // 用栈来缓存：1.栈空入栈；2.等于栈顶元素入栈；否则出栈，栈空sum++
        // 提高速度，用计数代替栈操作：L -1，R +1，sum=0，相当于栈空
        public int balancedStringSplit02(String s) {
            int sum = 0;
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                // char c = s.charAt(i);
                // if (c == 'L') {
                //     sum ++;
                // } else {
                //     sum --;
                // }
                sum += s.charAt(i) == 'L' ? 1 : -1;
                if (sum == 0) {
                    res ++;
                }
            }
            return res;
        }

        public int balancedStringSplit01(String s) {
            Stack<Character> stack = new Stack<>();
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (stack.isEmpty() || c == stack.peek()) {
                    stack.push(c);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res++;
                    }
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
