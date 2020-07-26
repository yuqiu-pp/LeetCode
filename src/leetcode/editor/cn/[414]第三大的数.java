//给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。 
//
// 示例 1: 
//
// 
//输入: [3, 2, 1]
//
//输出: 1
//
//解释: 第三大的数是 1.
// 
//
// 示例 2: 
//
// 
//输入: [1, 2]
//
//输出: 2
//
//解释: 第三大的数不存在, 所以返回最大的数 2 .
// 
//
// 示例 3: 
//
// 
//输入: [2, 2, 3, 1]
//
//输出: 1
//
//解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
//存在两个值为2的数，它们都排第二。
// 
// Related Topics 数组

package leetcode.editor.cn;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

class LC414{
    public static void main(String[] args) {
        Solution solution = new LC414().new Solution();
        // TO TEST
        System.out.println(solution.thirdMax(new int[]{3, 2, 1}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 小顶堆 比堆顶大，入堆
        // 利用set排重
        public int thirdMax(int[] nums) {
            if (nums.length < 2) {
                return nums[0];
            }
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            HashSet<Integer> set = new HashSet<>();
            int max = nums[0];
            for (int i = 0; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    continue;
                }
                set.add(nums[i]);
                max = Math.max(max, nums[i]);
                if (priorityQueue.size() < 3) {
                    priorityQueue.offer(nums[i]);
                } else {
                    if (nums[i] > priorityQueue.peek()) {
                        priorityQueue.poll();
                        priorityQueue.offer(nums[i]);
                    }
                }
            }
            if (priorityQueue.size() == 3) {
                max = priorityQueue.poll();
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
