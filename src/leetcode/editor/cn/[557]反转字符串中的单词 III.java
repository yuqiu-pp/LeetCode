//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 示例 1: 
//
// 
//输入: "Let's take LeetCode contest"
//输出: "s'teL ekat edoCteeL tsetnoc" 
// 
//
// 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// Related Topics 字符串

package leetcode.editor.cn;

class LC557{
    public static void main(String[] args) {
        Solution solution = new LC557().new Solution();
        // TO TEST
        System.out.println(solution.reverseWords("Let's take LeetCode contest"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseWords(String s) {
            // StringBuilder  reverse和toString
            StringBuilder rst = new StringBuilder();
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ' ') {
                    word.append(s.charAt(i));
                } else {
                    rst.append(word.reverse());
                    rst.append(" ");
                    word.setLength(0);
                }
            }
            rst.append(word.reverse());
            return rst.toString();
        }

        // 拆分成单词，分别反转
        public String reverseWords01(String s) {
            String[] words = s.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                words[i] = reverse(words[i]);
            }
            return String.join(" ", words);
        }

        public String reverse(String str) {
            char[] chars = str.toCharArray();
            int i = 0;
            int j = chars.length - 1;
            while (i < j) {
                char tmp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = tmp;
            }
            return String.valueOf(chars);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
