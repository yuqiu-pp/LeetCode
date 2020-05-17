//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 请找出其中最小的元素。 
//
// 你可以假设数组中不存在重复元素。 
//
// 示例 1: 
//
// 输入: [3,4,5,1,2]
//输出: 1 
//
// 示例 2: 
//
// 输入: [4,5,6,7,0,1,2]
//输出: 0 
// Related Topics 数组 二分查找

package leetcode.editor.cn;

class LC153{
    public static void main(String[] args) {
        Solution solution = new LC153().new Solution();
        // TO TEST
        int[] nums = {2,1};
        System.out.println(solution.findMin(nums));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                // 最小值 即连续区间的left
                if (nums[left] <= nums[right]) {
                    return nums[left];
                }

                int mid = left + (right - left) / 2;
                // 左边连续，最小值在右边区间.  等号条件不能少
                if (nums[left] <= nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
