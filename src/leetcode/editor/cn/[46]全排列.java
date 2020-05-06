//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LC46{
    public static void main(String[] args) {
        Solution solution = new LC46().new Solution();
        // TO TEST
        System.out.println(solution.permute(new int[] {1, 2, 3}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            helper(list, nums.length, nums.length, new ArrayList<>());
            // backtrack(nums, new ArrayList<>());
            return res;
        }
        // 时间复杂度：O(n * n!)O(n∗n!)   2ms
        // 1. list 剩余的元素。  每次删除。   2. 另一种方式，利用boolean数组标识改位置的数是否被用过  3. list.contains
        // n 总的格子数量
        // m 还需要填几个元素
        // curr
        private void helper(List<Integer> list, int n, int m, List<Integer> curr) {
            // 递归终结条件
            if (curr.size() == n) {
                res.add(new ArrayList<>(curr));
                return;
            }
            // 当前层
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
        // 每次递归都执行for n  效率没有helper的高  3ms
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
