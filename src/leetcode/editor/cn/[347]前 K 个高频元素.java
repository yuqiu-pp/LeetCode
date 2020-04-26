//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表

package leetcode.editor.cn;

import java.util.*;

class LC347{
    public static void main(String[] args) {
        Solution solution = new LC347().new Solution();
        // TO TEST
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k)));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 统计每个数字的频率，HashMap<val, 频次>   O(N)
            // 容量为k的大顶堆<频次，val>插入，添加是O(log(k))，要进行N次
            // 输出结果，复杂度为O(klog(k))
            public int[] topKFrequent(int[] nums, int k) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int n : nums) {
                    map.put(n, map.getOrDefault(n, 0) + 1);
                }
                Queue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue()-a.getValue());
                // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                //     maxHeap.add(entry);
                // }
                // 代替上面语句
                maxHeap.addAll(map.entrySet());

                int[] res = new int[k];
                for (int i = 0; i < k; i++) {
                    res[i] = maxHeap.poll().getKey();
                }
                return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
