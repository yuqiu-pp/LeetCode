//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LC47{
    public static void main(String[] args) {
        Solution solution = new LC47().new Solution();
        // TO TEST
        System.out.println(solution.permuteUnique(new int[] {1, 1, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 1. 在46题基础上，利用set排重。  有可能需要排序后，才能通过set排重。 这里不需要排序
    // 2. 想清楚是什么情况下回产生相同结果：curr 相同， 剩余 集合相同。 想到的是用hash表保存，递归时查询
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            helper(list, nums.length, nums.length, new ArrayList<>());
            // for (List<Integer> r : set) {
            //     res.add(r);
            // }
            // 替换上面代码
            res.addAll(set);
            return res;
        }
        // 时间复杂度：O(n * n!)O(n∗n!)   2ms
        // list 剩余的元素。  每次删除
        // n 总的格子数量
        // m 还需要填几个元素
        // curr
        private void helper(List<Integer> list, int n, int m, List<Integer> curr) {
            // 递归终结条件
            if (curr.size() == n) {
                set.add(new ArrayList<>(curr));
                return;
            }
            // 当前层：每次取剩余元素集合的第一个，恢复集合时插入在末尾
            for (int i = 0; i < m; i++) {
                Integer d = list.get(0);
                curr.add(d);
                list.remove(d);
                // 下一层
                helper(list, n, n - curr.size(), curr);
                // 清除当前层
                list.add(d);
                curr.remove(curr.size() - 1);
            }
        }
        // 剩余集合：helper 通过remove结点方式
        // 也可以 用list.contains方式
        // 每次递归都执行for n  效率没有helper的高   3ms
        private void backtrack(int[] nums, List<Integer> curr) {
            if (curr.size() == nums.length) {
                res.add(new ArrayList<>(curr));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (!curr.contains(nums[i])) {
                    curr.add(nums[i]);
                    backtrack(nums, curr);
                    curr.remove(curr.size() - 1);
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
