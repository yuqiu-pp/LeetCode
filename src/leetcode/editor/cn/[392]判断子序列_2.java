//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。 
//
// 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"ae
//c"不是）。 
//
// 示例 1: 
//s = "abc", t = "ahbgdc" 
//
// 返回 true. 
//
// 示例 2: 
//s = "axc", t = "ahbgdc" 
//
// 返回 false. 
//
// 后续挑战 : 
//
// 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码
//？ 
//
// 致谢: 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
// Related Topics 贪心算法 二分查找 动态规划 
// 👍 282 👎 0

package leetcode.editor.cn;

class LC392{
    public static void main(String[] args) {
        Solution solution = new LC392().new Solution();
        // TO TEST
        System.out.println(solution.isSubsequence("abc", "ahbgdc"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 两个指针遍历，直到s结束
        public boolean isSubsequence(String s, String t) {
            int sl = 0;
            int tl = 0;
            while (sl < s.length() && tl < t.length()) {
                if (s.charAt(sl) == t.charAt(tl)) {
                    sl ++;
                }
                tl ++;
            }
            return sl == s.length();
        }


        public boolean isSubsequence01(String s, String t) {
            int sl = 0;
            int tl = 0;
            for (int i = 0; i < s.length(); i++) {
                while (tl < t.length()) {
                    // 找到相等字符，s跳下一个
                    if (s.charAt(i) == t.charAt(tl)) {
                        // 都是最后一个字符
                        if (tl == (t.length() - 1) && i == (s.length() - 1)) {
                            return true;
                        }
                        tl ++;
                        break;
                    }
                    tl ++;
                }
                // t遍历完
                if (tl == t.length()) {
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
