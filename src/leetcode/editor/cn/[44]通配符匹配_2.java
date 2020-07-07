//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
// Related Topics 贪心算法 字符串 动态规划 回溯算法

package leetcode.editor.cn;

class LC44{
    public static void main(String[] args) {
        Solution solution = new LC44().new Solution();
        // TO TEST
        System.out.println(solution.isMatch("abefcdgiescdfimde", "ab*cd?i*de"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 重复子问题：  如果s和p的最后一个字符可以匹配，就变成前n-1子串的问题
        // 两个指针分支指向字符串，向后推进， 无法匹配返false
        // 匹配的判断：1.字符相同  2. ?匹配任何单独字符  3.*匹配时，指向*的指针不移动  4.只剩下*时，返true
        // * 的生命周期 要注意  不能已遇到第一个d就结束，所以*的位置始终要记录，直到出现第二个*替换
        // "i esc d fim  de", "i *  de"

        public boolean isMatch(String s, String p) {
            int i = 0, j = 0;
            int iStart = -1, jStart = -1;
            int sLen = s.length(), pLen = p.length();
            while (i < sLen) {
                if (j < pLen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                    i ++;
                    j ++;
                } else if (j < pLen && p.charAt(j) == '*') {
                    iStart = i;
                    // jStart指向*，j指向*的下一个字符
                    jStart = j ++;
                } else if (iStart > 0) {
                    // s继续向前
                    i = ++ iStart;
                    // 只要*有效，j始终指向*的下一个字符
                    j = jStart + 1;
                } else {
                    return false;
                }
            }
            while (j < pLen && p.charAt(j) == '*') {
                j ++;
            }
            return j == pLen;
        }

        public boolean isMatch01(String s, String p) {
            int i = 0, j = 0;
            int istart = -1, jstart = -1;
            int sl = s.length();
            int pl = p.length();

            while (i < sl) {
                char cs = s.charAt(i);
                char cp = p.charAt(j);
                if (j < pl && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                    i ++;
                    j ++;
                } else if (j < pl && p.charAt(j) == '*') {
                    // 标出*，让j指向下一个元素，当下一个元素==s[i]时，标记失效
                    // p指向*的下一个元素，因为要用它来结束*的生命周期
                    istart = i;
                    jstart = j ++;
                }
                // 必须是-1，因为i可以为0
                else if (istart > -1) {
                    i = ++istart;
                    j = jstart + 1;
                } else {
                    return false;
                }
            }
            while (j < pl && p.charAt(j) == '*'){
                j ++;
            }
            return j == pl;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
