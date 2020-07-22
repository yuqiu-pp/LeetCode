//给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。 
//
// 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。 
//
// 说明: 
//
// 
// 返回的下标值（index1 和 index2）不是从零开始的。 
// 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 
// 
//
// 示例: 
//
// 输入: numbers = [2, 7, 11, 15], target = 9
//输出: [1,2]
//解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。 
// Related Topics 数组 双指针 二分查找

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

class LC167{
    public static void main(String[] args) {
        Solution solution = new LC167().new Solution();
        // TO TEST
        int[] numbers = {2, 7, 11, 15};
        System.out.println(Arrays.toString(solution.twoSum(numbers, 9)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 有序数组，可以用双指针
        public int[] twoSum(int[] numbers, int target) {
            int len = numbers.length;
            int l = 0;
            int r = len - 1;
            // int[] res = new int[2];
            while (l < r) {
                if (numbers[l] + numbers[r] == target) {
                    return new int[] {l + 1, r + 1};
                }
                if (numbers[l] + numbers[r] < target) {
                    l ++;
                } else {
                    r --;
                }
            }
            return null;
        }


        // i + j = target ,   i = target - j, 判断target - j是否在numbers中
        public int[] twoSum01(int[] numbers, int target) {
            // <num[i], i>
            // 可能有key重复的情况，但相加可以等于target，所以不能采用先做hash表的方式
            // 改为递增的方式，先判断，再入hash表
            HashMap<Integer, Integer> map = new HashMap<>();
            int len = numbers.length;
            int[] res = new int[2];
            for (int i = 0; i < len; i++) {
                int tmp = target - numbers[i];
                if (map.containsKey(tmp)) {
                    res[0] = i > map.get(tmp) ? map.get(tmp) + 1 : i + 1;
                    res[1] = i > map.get(tmp) ? i + 1  : map.get(tmp) + 1 ;
                    return res;
                } else {
                    map.put(numbers[i], i);
                }
            }
            return null;


            // for (int i = 0; i < len; i++) {
            //     map.put(numbers[i], i);
            // }

            // int[] res = new int[2];
            // for (int i = 0; i < len; i++) {
            //     int tmp = target - numbers[i];
            //     if (map.containsKey(tmp)) {
            //         if (i > map.get(tmp)) {
            //             res[0] = map.get(tmp) + 1;
            //             res[1] = i + 1;
            //         } else {
            //             res[0] = i + 1;
            //             res[1] = map.get(tmp) + 1;
            //         }
            //     }
            // }
            // return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}