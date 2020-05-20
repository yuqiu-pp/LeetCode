//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串

package leetcode.editor.cn;

class LC680{
    public static void main(String[] args) {
        Solution solution = new LC680().new Solution();
        // TO TEST
        System.out.println(solution.validPalindrome("abc"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 判断回文方法：双指针，相同同时向中间夹逼
        // 利用可以删除一个字符：如果两边不等，需要删除一个字符再判断。  两种删除方法：左边or右边
        public boolean validPalindrome(String s) {
            char[] chars = s.toCharArray();
            int l = 0;
            int r = s.length() - 1;
            while (l < r) {
                if (chars[l] == chars[r]) {
                    l ++;
                    r --;
                } else {
                    // 删除左边后继续判断回文
                    // if (validPalind(chars, l + 1, r)) {
                    //     return true;
                    // } else {
                    //     删除右边
                        // return validPalind(chars, l, r - 1);
                    // }
                    return helper(chars, l + 1, r) || helper(chars, l, r - 1);
                }
            }
            return true;
        }
        // 判断回文
        private boolean helper(char[] chars, int l, int r) {
            while (l < r) {
                if (chars[l] != chars[r]) {
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
