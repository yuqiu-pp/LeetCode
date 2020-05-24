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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LC46{
    public static void main(String[] args) {
        Solution solution = new LC46().new Solution();
        // TO TEST
        System.out.println(solution.permute00(new int[] {1, 2, 3}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            // 每次从剩余集合中选择第一个，递归返回后还原集合时，将元素放在尾部，这样实现全排列
            List<Integer> list = new ArrayList<>();
            list.addAll(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            backtrack(list, nums.length, new ArrayList<>());
            return res;
        }
        private void backtrack(List<Integer> nums, int n, List<Integer> curr) {
            if (curr.size() == n) {
                res.add(new ArrayList<>(curr));
                return;
            }
            // 选一个加入curr
            for (int i = 0; i < nums.size(); i++) {
                int tmp = nums.get(0);
                curr.add(tmp);
                nums.remove(0);
                backtrack(nums, n, curr);
                nums.add(tmp);
                curr.remove(curr.size() - 1);
            }
        }


        public List<List<Integer>> permute02(int[] nums) {
            List<Integer> set = new ArrayList<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            dfs(nums.length, set, 0, new Integer[nums.length]);
            return res;
        }
        // n 总个数   level 已有个数
        private void dfs(int n, List<Integer> set, int level, Integer[] curr) {
            if (level == n) {
                res.add(new ArrayList<>(Arrays.asList(curr)));
                return;
            }
            for (int i = 0; i < n - level; i++) {
                curr[level] = set.get(0);
                set.remove(0);
                dfs(n, set, level + 1, curr);
                set.add(curr[level]);
            }
        }


        public List<List<Integer>> permute01(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            helper(list, nums.length, nums.length, new ArrayList<>());
            // backtrack(nums, new ArrayList<>());
            return res;
        }
        // 时间复杂度：O(n * n!)   2ms
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
        // 优化：isVisited标记
        public List<List<Integer>> permute00(int[] nums) {
            backtrack(nums, new ArrayList<>(), new int[nums.length]);
            return res;
        }
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
        private void backtrack(int[] nums, List<Integer> curr, int[] isVisted) {
            if (curr.size() == nums.length) {
                res.add(new ArrayList<>(curr));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (isVisted[i] == 1) {
                    continue;
                }
                curr.add(nums[i]);
                isVisted[i] = 1;
                backtrack(nums, curr, isVisted);
                isVisted[i] = 0;
                curr.remove(curr.size() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
