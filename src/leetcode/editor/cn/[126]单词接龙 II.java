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

import java.util.*;

class LC126{
    public static void main(String[] args) {
        Solution solution = new LC126().new Solution();
        // TO TEST
        List<String> wodList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(solution.findLadders("hit", "cog", wodList));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> res = new ArrayList<>();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            // 访问速度变为O(1)
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) {
                return res;
            }
            // boolean flag = false;
            // Set<String> isVisited = new HashSet<>();
            Queue<List<String>> queue = new LinkedList<>();
            queue.add(Arrays.asList(beginWord));
            // isVisited.add(beginWord);
            // while (!queue.isEmpty() && !flag) {
            while (!queue.isEmpty()) {
                int n = queue.size();
                Set<String> subVistied = new HashSet<>();
                for (int i = 0; i < n; i++) {
                    List<String> poll = queue.poll();
                    String word = poll.get(poll.size() - 1);
                    char[] chars = word.toCharArray();
                    for (int j = 0; j < chars.length; j++) {
                        char tmp = chars[j];
                        // 依次替换每一个字符
                        for (char ch = 'a'; ch <= 'z'; ch++) {
                            if (tmp == ch) {
                                continue;
                            }
                            chars[j] = ch;
                            String str = new String(chars);
                            // if (wordSet.contains(str) && !isVisited.contains(str)) {
                            if (wordSet.contains(str)) {
                                List<String> list = new ArrayList<>(poll);
                                list.add(str);
                                subVistied.add(str);
                                if (str.equals(endWord)) {
                                    // flag = true;
                                    res.add(list);
                                }
                                queue.offer(list);
                            }
                        }
                        chars[j] = tmp;
                    }
                }
                if (res.size() > 0) {
                    break;
                }
                // 也可以将访问过的都删除，就不需要记录
                // isVisited.addAll(subVistied);
                wordSet.removeAll(subVistied);
            }
            return res;
        }

        public List<List<String>> findLadders02(String beginWord, String endWord, List<String> wordList) {
            if (wordList.size() == 0 || !wordList.contains(endWord)) {
                if (wordList.size() == 0 || !wordList.contains(endWord)) {
                    return res;
                }
                return res;
            }
            List<String> list = new ArrayList<>();
            list.add(beginWord);
            dfs2(beginWord, endWord, wordList, list);
            Set<List<String>> set = new LinkedHashSet<>();
            for (int i = 0; i < res.size(); i++) {
                set.add(res.get(i));
            }
            res.clear();
            // set.forEach(x -> res.add(x));
            res.addAll(set);
            return res;
        }

        private void dfs2(String begin, String end, List<String> wordList, List<String> curr) {
            if (curr.contains(end)) {
                if (res.size() == 0) {
                    res.add(new ArrayList<>(curr));
                } else {
                    if (res.get(0).size() > curr.size()) {
                        res.clear();
                        res.add(new ArrayList<>(curr));
                    }
                    if (res.get(0).size() == curr.size()) {
                        res.add(new ArrayList<>(curr));
                    }
                }
                return;
            }
            for (int i = 0; i < wordList.size(); i++) {
                String tmp = wordList.get(i);
                if (curr.contains(wordList.get(i))) {
                    continue;
                }
                if (isCanConvert(begin,wordList.get(i))) {
                    curr.add(wordList.get(i));
                    // 新转换的词 再去查找
                    dfs2(wordList.get(i), end, wordList, curr);
                    curr.remove(curr.size() - 1);
                }
            }
        }

        private boolean isCanConvert(String a, String b) {
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


        // 超出时间限制  还需排重
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
