//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1： 
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2： 
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯算法 
// 👍 876 👎 0

package leetcode.editor.cn;

import java.util.*;

class PL39{
    public static void main(String[] args) {
        Solution solution = new PL39().new Solution();
        // TO TEST
        List<List<Integer>> rst = solution.combinationSum00(new int[]{8,7,4,3}, 11);
        for (List<Integer> r : rst) {
            System.out.println(r);
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // targe = targe - 任选一数 ， 然后新target在集合中再次选择
        // 重复子问题，递归解
        // Set<List<Integer>> rst = new HashSet<>();
        List<List<Integer>> rst = new ArrayList<>();
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            helper(candidates, target, 0, new ArrayList<>());
            // return new ArrayList<>(rst);
            return rst;
        }
        // 优化：记录每个targe，计算过的不再重复了
        // 不能用记录计算过的target，因为不同的target-candidate[i]，可能得到同样的结果
        // 将candidate排序， 如果当前target-candidate[i]<0，后面的更大值就不用处理了，直接结束

        // 另，for循环遍历时，i的初始值从递归位置之后判断即可，不需要每次从头来，所以增加参数start
        // 同时start的引入也保证了不会出现重复结果
        private void helper(int[] candidates, int target, int start, List<Integer> curr) {
            if (target == 0) {
                // Collections.sort(curr);
                rst.add(curr);
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                target -= candidates[i];
                if (target < 0) {
                    return;
                }
                curr.add(candidates[i]);
                // 可以被重复使用，所以i不加1
                helper(candidates, target, i, new ArrayList<>(curr));
                // curr.remove((Integer)candidates[i]);   // object 方式  不加(Integer)，会被认为是索引
                curr.remove(curr.size() - 1);
                target += candidates[i];
            }
        }

        public List<List<Integer>> combinationSum00(int[] candidates, int target) {
            recur(candidates, target, 0, new ArrayList<>());
            // return new ArrayList<>(rst);
            return rst;
        }
        private void recur(int[] candidates, int target, int start, List<Integer> curr) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                // Collections.sort(curr);
                rst.add(curr);
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                target -= candidates[i];
                curr.add(candidates[i]);
                recur(candidates, target, i, new ArrayList<>(curr));
                // curr.remove((Integer)candidates[i]);   // object 方式  不加(Integer)，会被认为是索引
                curr.remove(curr.size() - 1);
                target += candidates[i];
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

