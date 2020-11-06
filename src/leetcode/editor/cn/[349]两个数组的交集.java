//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 281 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class PL349{
    public static void main(String[] args) {
        Solution solution = new PL349().new Solution();
        // TO TEST
        System.out.println(Arrays.toString(solution.intersection(new int[]{1,2,2,1}, new int[]{2,2})));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1. 先排序（NlogN），再遍历
        // 2. 两个数组放入set中，这样查询效率是O(1)
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;
            Set<Integer> set = new HashSet<>();
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                    i ++;
                    j ++;
                } else if (nums1[i] < nums2[j]) {
                    i ++;
                } else {
                    j ++;
                }
            }
            int[] res = new int[set.size()];
            i = 0;
            for (Integer n : set) {
                res[i++] = n;
            }
            // for (int k = 0; k < list.size(); k++) {
            //     res[k] = list.toArray();
            // }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

