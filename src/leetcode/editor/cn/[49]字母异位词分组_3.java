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

import java.util.*;

class LC49{
    public static void main(String[] args) {
        Solution solution = new LC49().new Solution();
        // TO TEST
        String[] strs  = {"eat", "tea", "tan", "ate", "nat", "bat"};
        // String[] strs = {"duh","ill"};
        List<List<String>> res = solution.groupAnagrams(strs);
        for (List<String> ss : res) {
            System.out.println(Arrays.toString(ss.toArray()));
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // string -> char[]  求和 , hash表保存
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                // 不同组合，求和可能相同
                // int sum = 0;
                // for (int i = 0; i < chars.length; i++) {
                //     sum += chars[i];
                // }
                Arrays.sort(chars);
                String sum = String.valueOf(chars);
                List<String> list = map.getOrDefault(sum, new ArrayList<>());
                list.add(str);
                map.put(sum, list);
            }
            // List<List<String>> res = new ArrayList<>();
            // res.addAll(map.values());
            // return res;
            return new ArrayList<>(map.values());
        }

        // 判断异位词：string -> char[] , 排序后比较
        // 相同的分类：HashMap记录，key：排序后string值， value：List<string>
        public List<List<String>> groupAnagrams02(String[] strs) {
            HashMap<String, List<String>> map = new HashMap<>();
            for (String s : strs) {
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                String key = Arrays.toString(chars);
                List<String> list = map.getOrDefault(key, new LinkedList<>());
                list.add(s);
                map.put(key, list);
            }
            // List<List<String>> res = new ArrayList<>();
            // for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            //     res.add(entry.getValue());
            // }
            // return res;
            return new ArrayList<>(map.values());
        }

        // HashMap<key,List<>>  key 字符排序后的string
        public List<List<String>> groupAnagrams01(String[] strs) {
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
