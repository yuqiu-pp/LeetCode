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

import sun.rmi.server.InactiveGroupException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

class LC77{
    public static void main(String[] args) {
        Solution solution = new LC77().new Solution();
        // TO TEST
        System.out.println(solution.combine(4, 2));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 递归 重复子问题：选好一个数，剩下的k-1个位置再次选择
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
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
