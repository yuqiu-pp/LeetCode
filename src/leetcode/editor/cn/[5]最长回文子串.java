//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2702 👎 0

package leetcode.editor.cn;

class PL5{
    public static void main(String[] args) {
        Solution solution = new PL5().new Solution();
        // TO TEST
        System.out.println(solution.longestPalindrome("aca"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 2.动态规划
        // dp[i][j] s[i...j]是否为回文
        // dp[i][j] = dp[i+1][j-1] + 1  子串s[i+1 ... j-1]是回文
        // 没改对  ？、
        public String longestPalindrome(String s) {
            if (s.length() < 2) {
                return s;
            }
            int max = 1;
            int start = 0;
            boolean[][] dp = new boolean[s.length()][s.length()];
            char[] chars = s.toCharArray();

            for (int i = 1; i < chars.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (chars[i] != chars[j]) {
                        dp[i][j] = false;
                    } else {
                        if (i - j <3) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                    if (dp[i][j] && j - i + 1 > max) {
                        max = j - i + 1;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + max);
        }

        // 1.暴力 每个字符开始，依次向后的子串是否是回文，选最大的  n*n
        public String longestPalindrome01(String s) {
            if (s.length() < 2) {
                return s;
            }
            // 等于1的情况已经返回，如果没有找到更长的回文，返回第一个字符
            int max = 1;
            int start = 0;

            for (int i = 0; i < s.length() - 1; i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    // 每次substring开销太大， 可以isValid增加位置参数
                    // 只保留max长度和起始位置
                    // String tmp = s.substring(j, i);
                    // max判断放前面，避免不必要的isValids的调用开销
                    if ((j - i + 1) > max && isValid(s, i, j)) {
                        max = j - i + 1;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + max);
        }
        public boolean isValid(String s, int l, int r) {
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    return false;
                }
                l ++;
                r --;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

