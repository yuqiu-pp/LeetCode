//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4412 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class PL3{
    public static void main(String[] args) {
        Solution solution = new PL3().new Solution();
        // TO TEST
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 滑动窗口
        // 假设我们选择字符串中的第k个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为r_k
        // 那么当我们选择第 k+1 个字符作为起始位置时，首先从 k+1 到 r_k 的字符显然是不重复的，
        // 并且由于少了原本的第 k 个字符，我们可以尝试继续增大 r_k，直到右侧出现了重复字符为止。
        public int lengthOfLongestSubstring(String s) {
            if (s.length() <= 1) {
                return s.length();
            }

            int left = 0, right = 0;
            int max = 0;
            HashSet<Character> set = new HashSet<>();
            while (right < s.length()) {
                // 有重复字符
                if (set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left));
                    left ++;
                } else {
                    set.add(s.charAt(right++));
                }
                max = Math.max(max, set.size());
            }
            return max;
        }

        public int lengthOfLongestSubstring02(String s) {
            if (s.length() <= 1) {
                return s.length();
            }
            // 只存放不重复的字符，这样size就是长度了
            Set<Character> set = new HashSet<>();
            int max = 0;
            int left = 0;
            int right = 0;
            while (right < s.length()) {
                if (!set.contains(s.charAt(right))) {
                    set.add(s.charAt(right));
                    right++;
                    max = Math.max(set.size(), max);
                } else {
                    set.remove(s.charAt(left));
                    left ++;
                }
            }
            return Math.max(set.size(), max);
        }

        public int lengthOfLongestSubstring01(String s) {
            if (s.length() == 0) {
                return 0;
            }
            // k,v 字符,索引
            HashMap<Character, Integer> map = new HashMap<>();
            int max = 0;
            int left = 0; // 左指针

            for (int i = 0; i < s.length(); i++) {
                // 判断字符是否重复
                if (map.containsKey(s.charAt(i))) {
                    // 重复 left跳过不重复的字符
                    // 这里的 left取值不太好理解，因为map中含有已经跳过的字符
                    left = Math.max(left, map.get(s.charAt(i))+1);
                }
                map.put(s.charAt(i), i);
                max = Math.max(max, i-left+1);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

