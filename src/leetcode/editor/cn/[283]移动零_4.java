//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

class LC283{
    public static void main(String[] args) {
        Solution solution = new LC283().new Solution();
        // TO TEST
        int[] nums = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 双指针，一个指向0的位置，另一个指向非0
        // 一次遍历，交换
        public void moveZeroes(int[] nums) {
            for (int i = 0, j = 0; i < nums.length; i++) {
                // 没遇到0前，i和j一起往前走，实现保持非零元素的相对顺序
                if (nums[i] != 0) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = tmp;
                }
            }
        }
        public void moveZeroes03(int[] nums) {
            for (int i = 0, j = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = tmp;
                }
            }
        }

        public void moveZeroes2(int[] nums) {
            for (int i = 0, j = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = tmp;
                }
            }
        }
        public void moveZeroes1(int[] nums) {
            for (int i = 0, j = 0 ; i < nums.length; i++) {
                if (nums[i] != 0) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = tmp;
                }
            }
        }

        // 两次遍历，只是覆盖0的位置
        public void moveZeroes0(int[] nums) {
            int currZero = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[currZero++] = nums[i];
                }
            }
            for (int i = currZero; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
