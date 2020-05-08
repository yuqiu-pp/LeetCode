//给定一个整数数组 nums，将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：[5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：[5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// -50000 <= A[i] <= 50000 
// 
//

package leetcode.editor.cn;


import java.util.Arrays;
import java.util.stream.Collectors;

class LC912{

    public static void main(String[] args) {
        Solution solution = new LC912().new Solution();
        // TO TEST
        int[] nums = {5,2,3,1};
        System.out.println(Arrays.toString(solution.sortArray(nums)));
        // 将int包装为Integer，才能用toList
        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(System.out::print);
        System.out.println();

        // Integer可以直接用asList
        Integer[] numss = {5,2,3,1}; // int[] 不能打印
        Arrays.asList(numss).forEach(n -> System.out.println(n));
        Arrays.asList(numss).forEach(System.out::println);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArray(int[] nums) {
            Arrays.sort(nums);
            return nums;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
