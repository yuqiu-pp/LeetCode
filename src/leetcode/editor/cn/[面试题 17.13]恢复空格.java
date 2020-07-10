//哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’
//t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一
//本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。 
//
//
// 注意：本题相对原题稍作改动，只需返回未识别的字符数 
//
// 
//
// 示例： 
//
// 输入：
//dictionary = ["looked","just","like","her","brother"]
//sentence = "jesslookedjustliketimherbrother"
//输出： 7
//解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
// 
//
// 提示： 
//
// 
// 0 <= len(sentence) <= 1000 
// dictionary中总字符数不超过 150000。 
// 你可以认为dictionary和sentence中只包含小写字母。 
// 
// Related Topics 记忆化 字符串

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class LC1713{
    public static void main(String[] args) {
        Solution solution = new LC1713().new Solution();
        // TO TEST
        String[] dictionary = {"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrother";
        System.out.println(solution.respace(dictionary, sentence));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 把文章断开，要求未识别的字符最少    说明匹配方式不止一种，要找最少的。 动态规划
        // 子串是否是单词 Trie树
        // s[i] 全看做未匹配，则有 dp[i] = dp[i-1] + 1
        // s[i] 前 s[j..i-1] 是word  dp[i] = min(dp[i], dp[i-len])   i-len = j
        // ? dp[i] 是没有匹配单词的情况，应该是最大值吧
        // ？ len 怎么确定   for循环，从0开始挨个试
        public int respace(String[] dictionary, String sentence) {
            Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
            int n = sentence.length();
            int[] dp = new int[n];
            dp[0] = 1;
            char[] chars = sentence.toCharArray();
            for (int i = 1; i < n; i++) {
                // System.out.println(chars[i]);
                dp[i] = dp[i - 1] + 1;
                // 子串是否在字典中
                for (int j = 0; j <= i; j++) {
                    // System.out.print(sentence.substring(j, i+1));
                    // System.out.println( " " + j);
                    if (dict.contains(sentence.substring(j, i+1))) {
                        if (j > 1) {
                            dp[i] = Math.min(dp[i], dp[j - 1]);
                        } else {
                            dp[i] = 0;
                        }
                    }
                }
            }
            return dp[n - 1];
        }

        public int respace01(String[] dictionary, String sentence) {
            // 构建Trie树
            TrieNd root = new TrieNd('/');
            for (String s : dictionary) {
                root.add(s);
            }
            char[] chars = sentence.toCharArray();
            int[] dp = new int[chars.length];
            StringBuilder curr = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                if (!root.find(String.valueOf(ch))) {
                    dp[i] = i == 0 ? 0 :  dp[i - 1] + 1;
                } else {
                    curr.append(String.valueOf(ch));
                }
            }

            return 0;
        }
        class TrieNd {
            char val;
            TrieNd[] children = new TrieNd[26 + 1];
            boolean endFlag;

            public TrieNd(char val) {
                this.val = val;
                this.endFlag = false;
            }

            public boolean isEndFlag() {
                return endFlag;
            }

            public void setEndFlag(boolean endFlag) {
                this.endFlag = endFlag;
            }

            public void add(String word) {
                TrieNd root = this;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (root.children[index] == null) {
                        root.children[index] = new TrieNd(chars[i]);
                    }
                    // else {
                        root = root.children[index];
                    // }
                }
                root.setEndFlag(true);
            }

            public boolean find(String word) {
                TrieNd root = this;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (root.children[index].val == chars[i]) {
                        root = root.children[index];
                    } else {
                        return false;
                    }
                }
                return root.isEndFlag();
            }

            public int search(String word) {
                TrieNd root = this;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (root.children[index].val == chars[i]) {
                        root = root.children[index];
                    } else {
                        return i;
                    }
                }
                if (root.isEndFlag()) {
                    return chars.length;
                } else {
                    return chars.length + 1;
                }

            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
