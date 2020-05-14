//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
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
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索

package leetcode.editor.cn;

import java.util.*;

class LC127{
    public static void main(String[] args) {
        Solution solution = new LC127().new Solution();
        // TO TEST
        List<String> wodList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(solution.ladderLength("hit", "cog", wodList));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) {
            return 0;
        }
        int[] isVisited = new int[wordList.size()];
        int level = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (queue.size() > 0) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    if (isVisited[j] == 1) {
                        continue;
                    }
                    if (canConvert(str, wordList.get(j))) {
                        queue.offer(wordList.get(j));
                        if (endWord.equals(wordList.get(j))) {
                            return level + 1;
                        }
                        isVisited[j] = 1;
                    }
                }
            }
        }
        return 0;
    }

        private boolean canConvert(String str, String s) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (str.charAt(i) != s.charAt(i)) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
            // 不能直接返true
            return count == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
