//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LC126{
    public static void main(String[] args) {
        Solution solution = new LC126().new Solution();
        // TO TEST
        List<String> wodList = new ArrayList<>(Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"));
        System.out.println(solution.findLadders01("qa", "sq", wodList));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> res = new ArrayList<>();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {


            return null;
        }


        // 超出时间限制
        public List<List<String>> findLadders01(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return res;
            }
            // 依次替换每个字母
            List<String> list = new ArrayList<>();
            list.add(beginWord);
            dfs(beginWord, endWord, wordList, list);

            return res;
        }
        // curr 替换后的字符串
        private void dfs(String beginWord, String endWord, List<String> wordList, List<String> curr) {
            // terminator
            if (curr.contains(endWord)) {
                if (res.size() != 0) {
                    if (curr.size() < res.get(0).size()) {
                        res.clear();
                    } else if (curr.size() > res.get(0).size())  {
                        return;
                    }
                }
                res.add(new ArrayList<>(curr));
                System.out.println(curr);
                return;
            }
            if (curr.size() == wordList.size()) {
                return;
            }
            for (int i = 0; i < beginWord.length(); i++) {
                char ch = beginWord.charAt(i);
                // if (beginWord.equals("ba") && i == 1) {
                //     System.out.println("ba");
                // }
                // 从26个字母中选一个替换一个字符
                for (int j = 0; j < chars.length; j++) {
                    if (ch == chars[j]) {
                        continue;
                    }
                    char[] begin = beginWord.toCharArray();
                    begin[i] = chars[j];
                    String str = String.valueOf(begin);
                    if (!wordList.contains(str) || curr.contains(str)) {
                        continue;
                    }
                    curr.add(str);
                    dfs(str, endWord, wordList, curr);
                    curr.remove(curr.size() - 1);
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
