//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表

package leetcode.editor.cn;

import sun.nio.cs.FastCharsetProvider;
import sun.security.x509.RFC822Name;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class LC242{
    public static void main(String[] args) {
        Solution solution = new LC242().new Solution();
        // TO TEST
        System.out.println(solution.isAnagram("anagram", "nagaram"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 数组排序
        public boolean isAnagram(String s, String t) {
            char[] strs = s.toCharArray();
            char[] strt = t.toCharArray();
            Arrays.sort(strs);
            Arrays.sort(strt);
            return Arrays.equals(strs, strt);
        }

        // 因为只有字母，用数组代替hash表
        public boolean isAnagram02(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int[] chars = new int[26];
            for (int i = 0; i < s.length(); i++) {
                chars[s.charAt(i) - 'a'] ++;
                chars[t.charAt(i) - 'a'] --;
            }
            for (int i : chars) {
                if (i != 0) {
                    return false;
                }
            }
            return true;
        }
        // hashMap
        public boolean isAnagram01(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            HashMap<Character, Integer> maps = new HashMap<>();
            HashMap<Character, Integer> mapt = new HashMap<>();
             for (int i = 0; i <s.length(); i++) {
                char ch = s.charAt(i);
                // if (maps.containsKey(ch)) {
                //     maps.put(ch, maps.get(ch)+1);
                // } else {
                //     maps.put(ch, 1);
                // }
                // 替换上面语句
                maps.put(ch, maps.getOrDefault(ch, 0) + 1);
            }
            for (int i = 0; i <t.length(); i++) {
                char ch = t.charAt(i);
                mapt.put(ch, mapt.getOrDefault(ch, 0) + 1);
            }
            // for (Map.Entry<Character, Integer> entry : maps.entrySet()) {
            //      if (!Objects.equals(entry.getValue(), mapt.get(entry.getKey()))) {
            //          return false;
            //      }
            // }
            for (char ch : s.toCharArray()) {
                if (!maps.get(ch).equals(mapt.get(ch))) {
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
