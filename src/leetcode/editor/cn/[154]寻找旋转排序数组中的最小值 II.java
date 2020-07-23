//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 请找出其中最小的元素。 
//
// 注意数组中可能存在重复的元素。 
//
// 示例 1： 
//
// 输入: [1,3,5]
//输出: 1 
//
// 示例 2： 
//
// 输入: [2,2,2,0,1]
//输出: 0 
//
// 说明： 
//
// 
// 这道题是 寻找旋转排序数组中的最小值 的延伸题目。 
// 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？ 
// 
// Related Topics 数组 二分查找

package leetcode.editor.cn;

class LC154{
    public static void main(String[] args) {
        Solution solution = new LC154().new Solution();
        // TO TEST
        System.out.println(solution.findMin(new int[]{3,4,5,1,2}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 为什么选r进行比较，而不能用l
        // m=2 时，有 numbers[m] > numbers[i] ，如下所示，不能确定最小值一定在那侧
        // numbers=[1,2,3,4,5]   l在，
        // numbers=[3,4,5,1,2]   l不在
        // 因为 r 值肯定在最小值所在的有序数组中最大值,所以当==时，r--
        public int findMin(int[] numbers) {
            int len = numbers.length;
            int l = 0;
            int r = len - 1;
            int mid = 0;
            while(l < r) {
                mid = l + (r - l) / 2;
                if (numbers[r] > numbers[mid]) {
                    r = mid;
                } else if (numbers[r] < numbers[mid]) {
                    l = mid + 1;
                } else {
                    r --;
                }
            }
            return numbers[l];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
