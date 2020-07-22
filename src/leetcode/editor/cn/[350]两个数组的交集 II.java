//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 排序 哈希表 双指针 二分查找

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class LC350{
    public static void main(String[] args) {
        Solution solution = new LC350().new Solution();
        // TO TEST
        System.out.println(Arrays.toString(solution.intersect(new int[]{1,2,2,1}, new int[]{2,2})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 如果nums元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中。那么就无法高效地对nums进行排序
        // 这时用hashMap方法

        // hashMap<key, count>  出现次数
        public int[] intersect(int[] nums1, int[] nums2) {
            HashMap<Integer, Integer> map = new HashMap<>();
            // 优化，选择length小的num进行hash
            for (int i = 0; i < nums1.length; i++) {
                map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums2.length; i++) {
                if (map.containsKey(nums2[i])) {
                    for (int j = 0; j < map.get(nums2[i]); j++) {
                        res.add(nums2[i]);
                    }
                    map.remove(nums2[i]);
                }
            }
            int[] arr = new int[res.size()];
            for (int i = 0; i < res.size(); i++) {
                arr[i] = res.get(i);
            }
            return arr;
        }

            // 排序 + 双指针
        public int[] intersect01(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;
            List<Integer> res = new ArrayList<>();
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    res.add(nums1[i]);
                    i ++;
                    j ++;
                } else if (nums1[i] < nums2[j]) {
                    i ++;
                    if (res.size() != 0) {
                        break;
                    }
                } else {
                    j ++;
                    if (res.size() != 0) {
                        break;
                    }
                }
            }
            int[] arr = new int[res.size()];
            for (int k = 0; k < res.size(); k++) {
                arr[k] = res.get(k);
            }
            return arr;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}