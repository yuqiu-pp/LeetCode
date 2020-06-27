//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 
//
// 示例 1： 
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
// 
//
// 示例 2： 
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 
//
// 说明： 
//
// 
// 无空格字符构成一个单词。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 进阶： 
//
// 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。 
// Related Topics 字符串

package leetcode.editor.cn;

class LC151{
    public static void main(String[] args) {
        Solution solution = new LC151().new Solution();
        // TO TEST
        System.out.println(solution.reverseWords("the sky is blue"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 转为字符串数组，双指针
        public String reverseWords(String s) {
            s = s.trim();
            String[] words = s.split("\\s+");
            int i = 0;
            int j = words.length - 1;
            // String[] tmp = new String[1];
            while (i < j) {
                String tmp = words[i];
                words[i++] = words[j];
                words[j--] = tmp;
            }

            return String.join(" ", words);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
