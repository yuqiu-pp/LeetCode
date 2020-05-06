//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法

package leetcode.editor.cn;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

class LC78{
    public static void main(String[] args) {
        Solution solution = new LC78().new Solution();
        // TO TEST
        int[] nums = {1, 2, 3};
        System.out.println(solution.subsets(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            // 选 1、2、3 …… n
            for (int i = 0; i < nums.length+1; i++) {
                helper(nums, 0, i, new ArrayList<>());
            }
            return res;
        }
        private void helper(int[] nums, int index, int n, List<Integer>curr) {
            // 两种方式都可以，只是中间递归过程不一样
            if (n == index) {
                // if (n == curr.size()) {
                res.add(new ArrayList<>(curr));
                return;
            }
            // 当前层：从剩余数组，选一个加入
            for (int i = index; i < nums.length; i++) {
                curr.add(nums[i]);
                // 进入下一层
                helper(nums, i + 1, n, curr);
                // 清除当前层
                curr.remove(curr.size() - 1);
            }
        }

        // -------------------------
        // 每次选1、2…… n个，重复过程 就是从 选几个
        public List<List<Integer>> subsets01(int[] nums) {
            int n = nums.length;

            // 每次选i个数
            for (int i = 0; i < n + 1; i++) {
                backtrack(i, 0, nums, new ArrayList<Integer>());
            }
            return res;
        }

        private void backtrack(int i, int start, int[] subnums, List<Integer> curr) {
            if (curr.size() == i) {
                res.add(new ArrayList<>(curr));
                return;
            }
            for (int j = start; j < subnums.length; j++) {
                curr.add(subnums[j]);
                backtrack(i, j + 1, subnums, curr);
                curr.remove(curr.size() - 1);
            }
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)

}
