//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class LC49{
    public static void main(String[] args) {
        Solution solution = new LC49().new Solution();
        // TO TEST
        String[] strs  = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = solution.groupAnagrams(strs);
        for (List<String> ss : res) {
            System.out.println(Arrays.toString(ss.toArray()));
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // HashMap<key,List<>>  key 字符排序后的string
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> res = new HashMap<>();
            for (int i = 0; i < strs.length; i++) {
                char[] chs = strs[i].toCharArray();
                Arrays.sort(chs);
                String s = String.valueOf(chs);
                System.out.println(s);
                List<String> list =res.getOrDefault(s, new ArrayList<>());
                list.add(strs[i]);
                res.put(s, list);
            }
            // List<List<String>> result = new ArrayList<>();
            // for (List<String> l : res.values()) {
            //     result.add(l);
            // }
            // return result;
            return new ArrayList<>(res.values());
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
