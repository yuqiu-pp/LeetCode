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

import java.util.HashSet;
import java.util.Set;

class LC1713{
    public static void main(String[] args) {
        Solution solution = new LC1713().new Solution();
        // TO TEST
        String[] dictionary = {"wccm","wiw","uwwiwcmiiiwmmwicuwu","mw"};
        String sentence = "iwiwwwmuiccwwwwwmumwwwmcciwwuiwcicwwuwicuiwciwmiwicwuwwmuimccwucuuim";
        System.out.println(solution.respace(dictionary, sentence));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // dp[i] 当前字符位置，不合法字符数量
        // 方程  有两种情况：dp[i] = dp[i-1] + 1, 没有合法单词(包含当前字符)
        //                 dp[i] = dp[i-1] + 1 - word.len, 判断单词的方法 0->i依次减少一个字符，查看是否在dict中
        // 取其中的最小值
        public int respace(String[] dictionary, String sentence){
            if (sentence.length() == 0) {
                return 0;
            }
            HashSet<String> dict = new HashSet<>();
            for (int i = 0; i < dictionary.length; i++) {
                dict.add(dictionary[i]);
            }

            int dp[] = new int[sentence.length()];
            dp[0] = 1;

            for (int i = 1; i < sentence.length(); i++) {
                dp[i] = dp[i - 1] + 1;
                for (int j = 0; j <= i; j++) {
                    String word = sentence.substring(j, i + 1);
                    if (dict.contains(word)) {
                        dp[i] = Math.min(dp[i], dp[j - 1]);
                    }
                }

            }
            return dp[sentence.length() - 1];
        }


        // dp[i] 当前字符i前，有多少个不构成单词的字母。  注意，不包含当前字符i
        // 有两种来源： i前面字符都不构成单词，dp[i]=dp[i-1] + 1
        //            i前面有单词在字典中，dp[i] 等于 单词前dp[i-len]
        // 题目要求最少， 取上面两种情况的min
        // ? 如何判断i前面是否有单词在dict中    substring.  注意endIndex不包括当前字符，所以i指向单词下一个字符
        public int respace03(String[] dictionary, String sentence) {
            if (dictionary.length == 0 || sentence.length() == 0) {
                return 0;
            }
            Set<String> dict = new HashSet<>();
            for (String s : dictionary) {
                dict.add(s);
            }
            char[] chars = sentence.toCharArray();
            int[] dp = new int[chars.length + 1];
            dp[0] = 0;
            // substring.  注意endIndex不包括当前字符，要多往后走一步
            for (int i = 1; i <= chars.length; i++) {
                dp[i] = dp[i - 1] + 1;
                // i 多走了一步，所以这里不需要=j
                for (int j = 0; j < i; j++) {
                    if (dict.contains(sentence.substring(j, i))) {
                        dp[i] = Math.min(dp[i], dp[j]);
                    }
                }
            }
            return dp[chars.length];
        }

        public int respace02(String[] dictionary, String sentence) {
            if (dictionary.length == 0 || sentence.length() == 0) {
                return 0;
            }
            Set<String> dict = new HashSet<>();
            for (String s : dictionary) {
                dict.add(s);
            }
            int[] dp = new int[sentence.length() + 1];
            dp[0] = 0;
            for (int i = 1; i <= sentence.length(); i++) {
                dp[i] = dp[i - 1] + 1;
                for (int j = 0; j <= i; j++) {
                    // 这里substring不包括 endIndex ，所以i 要 <=
                    if (dict.contains(sentence.substring(j, i))) {
                        dp[i] = Math.min(dp[j], dp[i]);
                    }
                }
            }
            return dp[sentence.length()];
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
