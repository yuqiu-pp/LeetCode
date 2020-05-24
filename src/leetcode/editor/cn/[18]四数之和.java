//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针

package leetcode.editor.cn;

import java.util.*;

class LC18{
    public static void main(String[] args) {
        Solution solution = new LC18().new Solution();
        // TO TEST
        int[] nums = {1, 0, -1, 0, -2, 2};
        System.out.println(solution.fourSum(nums, 0));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            int len = nums.length;
            Arrays.sort(nums);

            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < len - 3; i++) {
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                target -= nums[i];
                for (int j = i + 1; j < len - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j-1]) {
                        continue;
                    }
                    target -= nums[j];
                    int l = j + 1;
                    int r = len - 1;
                    while (l < r) {
                        if (nums[l] + nums[r] == target) {
                            // List<Integer> list = new ArrayList<>();
                            // Integer[] arr = {nums[i], nums[j], nums[l], nums[r]};
                            // list.addAll(Arrays.asList(arr));
                            // res.add(list);
                            res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                            // 相等时，没有做l和r的移动
                            l ++;
                            r --;
                        }
                        // 小于target ， left向大的方向移动
                        else if (nums[l] + nums[r] < target) {
                            l ++;
                        } else {
                            r --;
                        }
                    }
                    target += nums[j];
                }
                target += nums[i];
            }
            return res;
        }

        // 依次取数
        public List<List<Integer>> fourSum01(int[] nums, int target) {
            int len = nums.length;
            Set<List<Integer>> set = new HashSet<>();
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < len - 3; i++) {
                for (int j = i + 1; j < len - 2; j++) {
                    for (int k = j + 1; k < len - 1; k++) {
                        for (int l = k + 1; l < len; l++) {
                            if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                                List<Integer> list = new ArrayList<>();
                                Integer[] arr = {nums[i], nums[j], nums[k], nums[l]};
                                Arrays.sort(arr);
                                list.addAll(Arrays.asList(arr));
                                set.add(list);
                            }
                        }

                    }
                }
            }
            res.addAll(set);
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
