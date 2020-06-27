//给定两个字符串 s 和 t，判断它们是否是同构的。 
//
// 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。 
//
// 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。 
//
// 示例 1: 
//
// 输入: s = "egg", t = "add"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "foo", t = "bar"
//输出: false 
//
// 示例 3: 
//
// 输入: s = "paper", t = "title"
//输出: true 
//
// 说明: 
//你可以假设 s 和 t 具有相同的长度。 
// Related Topics 哈希表

package leetcode.editor.cn;

import java.util.HashMap;

class LC205{
    public static void main(String[] args) {
        Solution solution = new LC205().new Solution();
        // TO TEST
        System.out.println(solution.isIsomorphic("ab", "aa"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 两种检查映射的方式：mapping 'e' -> 'a' and 'g' -> 'd'.
        //                  'e' -> 1, 'a'-> 1, and 'g' -> 2, 'd' -> 2
        public boolean isIsomorphic(String s, String t) {
            int[] m1 = new int[256];
            int[] m2 = new int[256];
            for (int i = 0; i < s.length(); i++) {
                if (m1[s.charAt(i)] != m2[t.charAt(i)]) {
                    return false;
                }
                m1[s.charAt(i)] = i+1;
                m2[t.charAt(i)] = i+1;
            }
            return true;
        }

        // map 检查是否包含value   containsValues()
        public boolean isIsomorphic01(String s, String t) {
            HashMap<Character, Character> hashMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c1 = s.charAt(i);
                char c2 = t.charAt(i);
                if (hashMap.containsKey(c1)) {
                    if (!hashMap.get(c1).equals(c2)) {
                        return false;
                    }
                } else {
                    if (!hashMap.containsValue(c2)) {
                        hashMap.put(c1, c2);
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
