//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class LC387{
    public static void main(String[] args) {
        Solution solution = new LC387().new Solution();
        // TO TEST
        System.out.println(solution.firstUniqChar("loveleetcode"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 优化：数组做hash表
        public int firstUniqChar(String s) {
            int[] hashMap = new int[26];
            char[] chars = s.toCharArray();
            for (char c : chars) {
                hashMap[c - 'a'] ++;
            }
            for (int i = 0; i < chars.length; i++) {
                if (hashMap[chars[i] - 'a'] == 1) {
                    return i;
                }
            }
            return -1;
        }

        public int firstUniqChar01(String s) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int n = hashMap.getOrDefault(chars[i], 0);
                hashMap.put(chars[i], n + 1);
            }
            for (int i = 0; i < chars.length; i++) {
                if (hashMap.get(chars[i])  == 1) {
                    return i;
                }
            }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
