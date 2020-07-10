//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法

package leetcode.editor.cn;

import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

import java.util.*;

import static java.util.stream.Collectors.toList;

class LC22{
    public static void main(String[] args) {
        Solution solution = new LC22().new Solution();
        // TO TEST
        System.out.println(solution.generateParenthesis(3));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // n个格子 往里面填数  回溯
        List<String> res = new ArrayList<>();
        public List<String> generateParenthesis(int n) {
            helper(n * 2, 0, 0, "");
            return res;
        }
        // k 层数,  m 左括号数量
        private void helper(int n, int k, int m, String curr) {
            if (n == k) {
                res.add(curr);
                return;
            }
            // 当前层：选一个括号放入
            if (m < n / 2) {
                helper(n, k + 1, m + 1, curr + '(');
            }
            // 右括号数量小于左括号
            if (m > k - m) {
                helper(n, k + 1, m, curr + ')');
            }
        }


        // 括号合法性：（ 随便加，不超数量即可； ）只有左括号数量大于右括号
        // 放一个（，剩下n-1个位置递归的放
        public List<String> generateParenthesis03(int n) {
            List<String> res = new ArrayList<>();
            bfs(n, 0, 0, "", res);
            return res;
        }
        private void bfs(int n, int left, int right, String str, List<String> res) {
            if (left == n && right == n) {
                res.add(str);
                return;
            }
            if (left < n) {
                bfs(n, left + 1, right, str + "(", res);
            }
            if (right < left) {
                bfs(n, left, right + 1, str + ")", res);
            }
        }

        public List<String> generateParenthesis02(int n) {
            List<String> res = new ArrayList<>();
            helper(0, 0, n, "", res);
            return res;
        }
        private void helper(int left, int right, int n, String str, List<String> res) {
            if (left == n && right == n) {
                res.add(str);
            }
            // process curr level: 两种选择
            if (left < n) {
                helper(left+1, right, n, str+"(", res);
            }
            if (right < left) {
                helper(left, right+1, n, str+")", res);
            }
        }


        public List<String> generateParenthesis01(int n) {
            List<String> list = new ArrayList<>();
            _generate(0, n * 2, 0, 0, "", list);
            return list;
        }
        // 递归：选定一个括号后，求n-1个的组合
        // 括号的合法性判断：左括号 随时加，只要不超过允许数量
        //                右括号 必须有多余的左括号，即右数量小于左
        public void _generate(int level, int max, int l, int r,  String s, List<String> list) {
            // 递归出口
            if (level >= max) {
                list.add(s);
                return;
            }
            // 当前层逻辑处理： 加左括号，或者右括号
            // String s1 = s + ")";
            // String s2 = s + "(";
            // 传递到下一层
            if (l < max/2) {
                _generate(level+1, max, l+1, r, s + "(", list);
            }
            if (r < l) {
                _generate(level+1, max, l, r+1, s + ")", list);
            }

            // 清除当前层数据
        }}

    //leetcode submit region end(Prohibit modification and deletion)

}
