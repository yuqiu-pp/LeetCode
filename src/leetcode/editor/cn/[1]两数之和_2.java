//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class LC1{
    public static void main(String[] args) {
        Solution solution = new LC1().new Solution();
        // TO TEST
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // a+b=tafget  转化为  a=target-b
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{i,map.get(target - nums[i])};
                }
                map.put(nums[i], i);
            }
            return null;
        }
        public int[] twoSum01(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            // 建map时，先检查是否存在，遍历一遍hash表就可以，同时也可以省去判断i是否是自己
            for (int i = 0; i < nums.length; i++) {
                int n = target - nums[i];
                if (map.containsKey(n)) {
                    return new int[]{i, map.get(n)};
                } else {
                    map.put(nums[i], i);
                }
            }
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
