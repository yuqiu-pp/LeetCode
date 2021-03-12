//在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。 
//
// 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。 
//
// 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示
//为 [3,6] 。 
//
// 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。 
//
// 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abbxxxxzzy"
//输出：[[3,6]]
//解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
// 
//
// 示例 2： 
//
// 
//输入：s = "abc"
//输出：[]
//解释："a","b" 和 "c" 均不是符合要求的较大分组。
// 
//
// 示例 3： 
//
// 
//输入：s = "abcdddeeeeaabbbcd"
//输出：[[3,5],[6,9],[12,14]]
//解释：较大分组为 "ddd", "eeee" 和 "bbb" 
//
// 示例 4： 
//
// 
//输入：s = "aba"
//输出：[]
// 
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅含小写英文字母 
// 
// Related Topics 数组 
// 👍 75 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PL830{
    public static void main(String[] args) {
        Solution solution = new PL830().new Solution();
        // TO TEST
        System.out.println(solution.largeGroupPositions("abcdddeeeeaabbbcd"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> largeGroupPositions(String s) {
            List<List<Integer>> res = new ArrayList<>();

            int len = s.length();
            int num = 1;
            for (int i = 0; i < len; i++) {
                if (i == len -1 || s.charAt(i) != s.charAt(i+1)) {
                    if (num >= 3) {
                        res.add(Arrays.asList(i - num + 1, i));
                    }
                    num = 1;
                } else {
                    num ++;
                }
            }
            return res;
        }

        // s.charAt 方式比s.toCharArray 使用内存少点

        public List<List<Integer>> largeGroupPositions01(String s) {
            List<List<Integer>> res = new ArrayList<>();

            char[] chars = s.toCharArray();
            // 至少是一个字符，所以初始化为1
            int num = 1;
            for (int i = 0; i < chars.length; i++) {
                // 用当前和前一个字符比较，然后计数    比   left 和 i++比更简单，不需要特殊处理
                // 两个判断条件顺序不能反
                if (i == chars.length - 1 || chars[i] != chars[i+1] ) {
                    if (num >= 3) {
                        res.add(Arrays.asList(i - num + 1, i));
                    }
                    num = 1;
                } else {
                    num ++;
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

