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
        List<String> res = new ArrayList<>();
        // 双向BFS：每次遍历一层时，从节点更少的一端遍历。并不是真正的每次都都从两端各走一次
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (wordList == null || !wordList.contains(endWord)) {
                return 0;
            }
            Set<String> wordSet = new HashSet<>();
            wordSet.addAll(wordList);
            wordSet.add(beginWord);

            Queue<String> queue1 = new LinkedList<>();
            Queue<String> queue2 = new LinkedList<>();
            // 已经遍历过的节点
            Set<String> visited1 = new HashSet<>();
            Set<String> visited2 = new HashSet<>();
            queue1.offer(beginWord);
            queue2.offer(endWord);
            visited1.add(beginWord);
            visited2.add(endWord);

            int res = 0;
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                res ++;
                // 从节点更少的一端遍历
                if (queue1.size() > queue2.size()) {
                    Queue tmp = queue1;
                    queue1 = queue2;
                    queue2 = tmp;
                    Set<String> set = visited1;
                    visited1 = visited2;
                    visited2 = set;
                }
                int size = queue1.size();
                while (size-- > 0) {
                    String s = queue1.poll();
                    char[] chars = s.toCharArray();
                    for (int i = 0; i < s.length(); i++) {
                        char ch = chars[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            chars[i] = c;
                            String ns = String.valueOf(chars);
                            // 访问过
                            if (visited1.contains(ns)) {
                                continue;
                            }
                            // 两端相遇
                            if (visited2.contains(ns)) {
                                return res + 1;
                            }
                            // 标记为已访问
                            if (wordSet.contains(ns)) {
                                visited1.add(ns);
                                queue1.offer(ns);
                            }
                        }
                        chars[i] = ch;
                    }
                }
            }
            return 0;
        }


        // BFS找替换词加入队列  1, 从wordList中选词，判断是否能变为poll。 list元素多时，性能差
        //                 2，poll变换一个字母，判断是否在wordList中。 单词长度短时，性能优于1
        // 65 ms
        public int ladderLength03(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>();
            wordSet.addAll(wordList);
            int res = 0;

            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            while (!queue.isEmpty()) {
                res ++;
                int n = queue.size();
                for (int i = 0; i < n; i++) {
                    String str = queue.poll();
                    // 查找能替换得到的所有节点，放入队列
                    for (int j = 0; j < str.length(); j++) {
                        char[] chars = str.toCharArray();
                        char tmp = chars[j];
                        for (char ch = 'a'; ch <= 'z'; ch++) {
                            if (ch == tmp) {
                                continue;
                            }
                            chars[j] = ch;
                            String s = String.valueOf(chars);
                            if (wordSet.contains(s)) {
                                queue.offer(s);
                                wordSet.remove(s);
                                if (s.equals(endWord)) {
                                    return res + 1;
                                }
                            }
                        }
                    }
                }
                // wordSet.removeAll(list);
            }
            // 这里不能直接返res，queue至少会走一层 begingword
            return 0;
        }
        private List<String> canConvert(String str, Set<String> wordSet) {
            List<String> list = new ArrayList<>();

            char[] chars = str.toCharArray();
            for (int j = 0; j < str.length(); j++) {
                char tmp = chars[j];
                for (char ch = 'a'; ch < 'z'; ch++) {
                    chars[j] = ch;
                    String s = String.valueOf(chars);
                    if (wordSet.contains(s)) {
                        list.add(s);
                    }
                }
                chars[j] = tmp;
            }
            return list;
        }


        // 	654 ms    ?  boolean[] isVistied  比  int[] 效率低
        public int ladderLength02(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            int res = 0;
            boolean[] isVistied = new boolean[wordList.size()];
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            while (!queue.isEmpty()) {
                res ++;
                // 求最短路径，所以出现过的单词不再使用，直接从wordList中删除
                int n = queue.size();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    String poll = queue.poll();
                    // 找替换词加入队列  1, 从wordList中选词，判断是否能变为poll
                    //                 2，poll变换一个字母，判断是否在wordList中
                    for (int j = 0; j < wordList.size(); j++) {
                        String str = wordList.get(j);
                        if (isVistied[j]) {
                            continue;
                        }
                        if (canConvert(poll, str)) {
                            queue.offer(str);
                            isVistied[j] = true;
                            if (str.equals(endWord)) {
                                return res + 1;
                            }
                        }
                    }
                }
                // 效率低
                // wordList.removeAll(list);
            }
            return res;
        }
        private boolean canConvert(String a, String b) {
            if (a.length() != b.length()) {
                return false;
            }
            int count = 0;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    count ++;
                }
                if (count > 1) {
                    return false;
                }
            }
            return count == 1;
        }


        // 558 ms
        public int ladderLength01(String beginWord, String endWord, List<String> wordList) {
            if (wordList.size() == 0 || !wordList.contains(endWord)) {
                return 0;
            }
            boolean[] isVisited = new boolean[wordList.size()];
            int countVisited = 0;
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            while (queue.size() > 0) {
                int n = queue.size();
                countVisited ++;
                for (int i = 0; i < n; i++) {
                    String poll = queue.poll();
                    for (int j = 0; j < wordList.size(); j++) {
                        if (isVisited[j]) {
                            continue;
                        }
                        if (isCanConvert(poll, wordList.get(j))) {
                            queue.offer(wordList.get(j));
                            isVisited[j] = true;
                            if (endWord.equals(wordList.get(j))) {
                                return countVisited + 1;
                            }
                        }
                    }
                }
            }
            return 0;
        }

        private boolean isCanConvert(String a, String b) {
            int count = 0;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
            return count == 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
