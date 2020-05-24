//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法

package leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

class LC77{
    public static void main(String[] args) {
        Solution solution = new LC77().new Solution();
        // TO TEST
        System.out.println(solution.combine(4, 2));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        // k 个 格子
        // n 个数中任选两个填充
        public List<List<Integer>> combine(int n, int k) {
            dfss(n, k, 0, new ArrayList<>());
            return res;
        }
        private void dfss(int n, int k, int index, List<Integer> curr) {
            if (curr.size() == k) {
                res.add(new ArrayList<>(curr));
                return;
            }
            for (int i = index; i < n; i++) {
                curr.add(i + 1);
                dfss(n, k, i + 1, curr);
                curr.remove(curr.size() - 1);
            }
        }


        // 递归 重复子问题：选好一个数，剩下的k-1个位置再次选择
        // List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> combine02(int n, int k) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
            }
            dfs(nums, 0, k, new ArrayList<>());
            return res;
        }
        private void dfs(int[] nums, int index, int k, List<Integer> curr) {
            if (curr.size() == k) {
                res.add(new ArrayList<>(curr));
                return;
            }
            for (int i = index; i < nums.length; i++) {
                curr.add(nums[i]);
                dfs(nums, i + 1, k, curr);
                curr.remove(curr.size() - 1);
                // 用临时变量代替curr往下一层传，可以不用清空当前层数据，因为当前层的curr没有影响
                // List<Integer> tmp = new ArrayList<>(curr);
                // tmp.add(nums[i]);
                // dfs(nums, i + 1, k, tmp);
            }
        }

        public List<List<Integer>> combine01(int n, int k) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i +  1;
            }
            helper(0, nums, k, new ArrayList<>());
            return res;
        }
        private void helper(int index, int[] nums, int k, List<Integer> curr) {
            // 终止条件
            if (k == curr.size()) {
                res.add(new ArrayList<>(curr));
                return;
            }
            // 当前层：剩余的nums[index, nums.length]中任意选一个放入curr
            for (int i = index; i < nums.length; i++) {
                curr.add(nums[i]);
                // 进入下一层
                helper(i + 1, nums, k, curr);
                // 清除当前层状态
                curr.remove(curr.size() - 1);
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
