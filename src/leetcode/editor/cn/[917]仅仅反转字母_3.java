//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串

package leetcode.editor.cn;

import java.util.Arrays;

class LC917{
    public static void main(String[] args) {
        Solution solution = new LC917().new Solution();
        // TO TEST  "a-bC-dEf-ghIj"
        System.out.println(solution.reverseOnlyLetters("a-bC-dEf-ghIj"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseOnlyLetters(String S) {
            int l = 0;
            int r = S.length() - 1;
            char[] strs = S.toCharArray();
            while (l < r) {
                // isChar存在重复调用的情况
                // 优化：当时数字时，局部循环跳过
                if (isChar(strs[l])  && isChar(strs[r])) {
                    char ch = strs[l];
                    strs[l] = strs[r];
                    strs[r] = ch;
                    l++;
                    r--;
                }
                if (!isChar(strs[l])) {
                    l++;
                }
                if (!isChar(strs[r])) {
                    r--;
                }
            }
            return String.valueOf(strs);
        }

        private boolean isChar(char c) {
            return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
        }

        // 双指针 夹逼：遇到非字母跳过
        public String reverseOnlyLetters02(String S) {
            char[] chars = S.toCharArray();
            int len = chars.length;
            int l = 0;
            int r = len - 1;
            while (l < r) {
                while (l < r && !isChar(chars[l])) {
                    l++;
                }
                while (l < r && !isChar(chars[r])) {
                    r--;
                }
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++;
                r--;
            }
            return String.valueOf(chars);
        }
        boolean isChar02(char c) {
            return ((c>='a' && c<='z') || (c>='A' && c<='Z'));
        }

        public String reverseOnlyLetters01(String S) {
            int len = S.length();
            if (len < 2) {
                return S;
            }
            char[] chars = S.toCharArray();
            int l = 0;
            int r = len - 1;
            while (l < r) {
                // if (!isChar(chars[l])) {
                //     l ++;
                // }
                // 所有都要跳过
                while (l < r && !isChar(chars[l])) {
                    l ++;
                }

                while (l < r && !isChar(chars[r])) {
                    r --;
                }
                char c = chars[l];
                chars[l] = chars[r];
                chars[r] = c;
                l ++;
                r --;
            }
            return String.valueOf(chars);
        }
        boolean isChar01(char c) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                return true;
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
