//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。 
//
// 
//
// 示例: 
//
// 输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"] 
// Related Topics 字符串 回溯算法 
// 👍 366 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LC93{
    public static void main(String[] args) {
        Solution solution = new LC93().new Solution();
        // TO TEST
        System.out.println(solution.restoreIpAddresses("25525511135"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> res = new ArrayList<>();

        // public List<String> restoreIpAddresses(String s) {
        //     if (s.length() < 4) {
        //         return new ArrayList<>();
        //     }
        //     char[] chars = s.toCharArray();
        //     helper(chars, 0, 4, new StringBuilder());
        // }
        // start 剩余字符数据起始位置
        // n 剩余的格子数
        // sb 当前的ip字符串
        // private void helper(char[] chars, int start, int n, StringBuilder sb){
        //     for (int i = start; i < chars.length - start; i++) {
        //         剩余的长度合法
            // }
        // }

        public List<String> restoreIpAddresses(String s) {
            backtrack(s, 0, "", 0);
            return res;
        }
        private void backtrack(String s, int start, String curr, int count) {
            if (count > 4) {
                return;
            }
            // 且 s 的字符都用完
            if (count == 4 && start == s.length()) {
                res.add(curr);
                return;
            }
            // 当前层 取一个数字加入curr
            for (int i = 1; i < 4; i++) {
                if (start + i > s.length()) {
                    break;
                }
                String sub  = s.substring(start, start + i);
                // "0.0.0.0" 要判断长度 > 1
                if ((sub.startsWith("0") && sub.length() > 1) || Integer.parseInt(sub) > 255) {
                    continue;
                }
                String tmp = curr + sub + (count < 3 ? "." : "");
                backtrack(s, start + i, tmp, count + 1);
            }
        }

        public List<String> restoreIpAddresses01(String s) {
            restoreIp(s, 0, "", 0);
            return res;
        }
        // idx  起始位置
        // resored 已经拼的ip串
        // count 已经有的ip地址段数量
        private void restoreIp(String ip, int idx, String resored, int count) {
            if (count > 4) {
                return;
            }
            if (count == 4 && idx == ip.length()) {
                res.add(resored);
            }
            // 每段最长3位
            for (int i = 1; i < 4; i++) {
                // 字符都用完了
                if (idx + i > ip.length()) {
                    break;
                }
                String s = ip.substring(idx, idx + i);
                // 不合法条件
                if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) >= 256)) {
                    continue;
                }
                String tmp = resored + s + (count < 4 ? "." : "");
                restoreIp(ip, idx + i, tmp, count + 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
