//给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。 
//
// 
//
// 示例 1： 
//
// 输入：["abcd","dcba","lls","s","sssll"]
//输出：[[0,1],[1,0],[3,2],[2,4]] 
//解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
// 
//
// 示例 2： 
//
// 输入：["bat","tab","cat"]
//输出：[[0,1],[1,0]] 
//解释：可拼接成的回文串为 ["battab","tabbat"] 
// Related Topics 字典树 哈希表 字符串 
// 👍 130 👎 0

package leetcode.editor.cn;

import java.util.*;

class PL336{
    public static void main(String[] args) {
        Solution solution = new PL336().new Solution();
        // TO TEST
        System.out.println(solution.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            // 字符串反转入map
            for (int i = 0; i < words.length; i++) {
                String rev = new StringBuilder(words[i]).reverse().toString();
                map.put(rev, i);
            }
            for (int i = 0; i < words.length; i++) {
                String curr = words[i];
                // 有与反转后的字符串相同的，一定构成回文
                if (map.containsKey(curr) && map.get(curr) != i) {
                    res.add(Arrays.asList(i, map.get(curr)));
                }
                // 左前缀
                for (int j = curr.length() - 1; j >= 0; j--) {
                    if (isPalindrome(curr, 0, j)) {
                        String sub = curr.substring(j + 1);
                        if (map.containsKey(sub)) {
                            res.add(Arrays.asList(map.get(sub), i));
                        }
                    }
                }
                // 右前缀
                for (int j = 0; j < curr.length(); j++) {
                    if (isPalindrome(curr, j, curr.length() - 1)) {
                        String sub = curr.substring(0, j);
                        if (map.containsKey(sub)) {
                            res.add(Arrays.asList(i, map.get(sub)));
                        }
                    }
                }

            }
            return res;
        }
        private boolean isPalindrome(String candidate, int left, int right) {
            while (left < right) {
                if (candidate.charAt(left) != candidate.charAt(right)) return false;
                left++;
                right--;
            }
            return true;
        }

        // 暴力 每个都和后面的拼，判断是否回文.  两个字符串要交换拼接  超时
        // 适当优化 先判断一下两个string的两端字符是否相等，相等才去拼接、判断
        public List<List<Integer>> palindromePairs01(String[] words) {
            List<List<Integer>> res = new ArrayList<>();
            int len = words.length;
            for (int i = 0; i < len; i++) {
                String left = words[i];
                for (int j = i + 1; j < len; j++) {
                    String right = words[j];
                    // 有一个事空串
                    if (left.length() == 0 || right.length() == 0) {
                        if (helper(left + right)) {
                            res.add(Arrays.asList(i, j));
                            res.add(Arrays.asList(j, i));
                        }
                    } else {
                        if (left.charAt(0) == right.charAt(right.length() - 1)) {
                            if (helper(left + right)) {
                                res.add(Arrays.asList(i, j));
                            }
                        }
                        if (left.charAt(left.length() - 1) == right.charAt(0)) {
                            // 组合起来判断是否为回文
                            if (helper(right + left)) {
                                res.add(Arrays.asList(j, i));
                            }
                        }
                    }
                }
            }
            return res;
        }
        private boolean helper(String word) {
            int i = 0;
            int j = word.length() - 1;
            while (i < j) {
                if (word.charAt(i) != word.charAt(j)) {
                    return false;
                }
                i ++;
                j --;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

