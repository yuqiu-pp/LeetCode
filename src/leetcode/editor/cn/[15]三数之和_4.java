//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针

package leetcode.editor.cn;

import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.security.util.Length;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

class LC15{
    public static void main(String[] args) {
        Solution solution = new LC15().new Solution();
        // TO TEST
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 三层循环，暴力会超时
        // a + b + c = 0  转化为  c + b = -a 即两数求和，用双指针  需要数组有序
        public List<List<Integer>> threeSum(int[] nums) {
            // Set<List<Integer>> res = new HashSet<>();
            int len = nums.length;
            Arrays.sort(nums);
            // 优化：因为排序过，且相同值都会跳过，所以可以不用Set，直接用List
            List<List<Integer>> res = new ArrayList<>();

            // 三数和为0，如果排序后的第一个元素大于0，则无解   由44ms提升到42ms
            if (len == 0 || nums[0] > 0) {
                // return new ArrayList<>(res);
                return res;
            }

            for (int i = 0; i < len - 2; i++) {
                // 优化：相同值跳过  42ms 提升到 25ms
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                // 后面的按两数之和算
                int a = - nums[i];
                int l = i + 1;
                int r = len - 1;
                while (l < r) {
                    int sum = nums[l] + nums[r];
                    if (sum == a) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        // 	596 ms
                        // l ++;
                        // r --;
                        // 优化，这样处理时间比较长，因为l++后数据可能有重复的，可以直接跳过  44ms
                        while(l < r && nums[l] == nums[l + 1]) {
                            l ++;
                        }
                        l ++;
                        while (l < r && nums[r] == nums[r - 1]) {
                            r --;
                        }
                        r --;
                    }
                    else if (sum > a) {
                        r --;
                    } else {
                        l ++;
                    }
                    // 优化：连续小于sum的数也可以直接跳过   基本没有缩短时间，差别不大，这步不做逻辑更清楚
                    // while (l < r && nums[l] + nums[r] > a) {
                    //     r --;
                    // }
                    // while (l < r && nums[l] + nums[r] < a) {
                    //     l ++;
                    // }
                }
            }
            // return new ArrayList<>(res);
            return res;
        }

        // 3层循环：注意每层循环的结束条件
        // 转化为两数之和：a+b = -c，就容易想起用hash表处理了. 但重复数据怎么办？排重？
        // 双指针：首先要排序，然后双指针。确定一个值，然后后面的数据从两边往中间靠。和大于0，大端往减小的方向移动；和小于0，小端往增大方向移动
        public List<List<Integer>> threeSum04(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i] > 0) {
                    break;
                }
                // 相同值跳过
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    // l + r = -i，三者和为0
                    if (nums[l] + nums[r] + nums[i] == 0) {
                        res.add(Arrays.asList(nums[l], nums[r], nums[i]));
                        // 有相同值，跳过
                        l++;
                        while (l < r && nums[l] == nums[l-1]) {
                            l++;
                        }
                        r--;
                        while (l < r && nums[r] == nums[r+1]) {
                            r--;
                        }
                    }
                    // l + r > -i，需要将l+r减小才能等于0，r是最大，所以向r减小的方向移动，直到找到(局部循环)
                    while (l < r && (nums[l] + nums[r] + nums[i] > 0)) {
                        r--;
                    }
                    // l + r < -i，需要将l+r增大才能等于0，l是最小，所以向l增大的方向移动
                    while (l < r && (nums[l] + nums[r] + nums[i] < 0)) {
                        l++;
                    }
                }
            }
            return res;
        }

        // hashmap 超时
        public List<List<Integer>> threeSum03(int[] nums) {
            int len = nums.length;
            HashMap<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                map.put(nums[i], i);
            }
            Set<List<Integer>> set = new HashSet<>();
            for (int i = 0; i < len - 1; i++) {
                for (int j = i+1; j < len; j++) {
                    int n = 0 - nums[i] - nums[j];
                    if (map.containsKey(n)) {
                        int val = map.get(n);
                        if (val != i && val != j) {
                            int[] a = {n, nums[i], nums[j]};
                            Arrays.sort(a);
                            // 1.使用Arrays.stream将int[]转换成IntStream。
                            // 2.使用IntStream中的boxed()装箱。将IntStream转换成Stream<Integer>。
                            // 3.使用Stream的collect()，将Stream<T>转换成List<T>
                            set.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
                        }
                    }
                }
            }
            return new ArrayList<>(set);
        }

        public List<List<Integer>> threeSum02(int[] nums) {
            Arrays.sort(nums);
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < len-2; i++) {
                if (nums[i] > 0) {
                    break;
                }
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                for (int l = i+1, r = len-1; l < r;) {
                    int n = nums[i] + nums[l] + nums[r] ;
                    if (0 == n) {
                        // List<Integer> list = new ArrayList<>();
                        // list.add(nums[i]);
                        // list.add(nums[l]);
                        // list.add(nums[r]);
                        // res.add(list);
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        // 代码冗余比较多
                        l ++;
                        // while (nums[l] == nums[l-1]) {
                        // 不判断l < r 数组会溢出
                        while (l < r && nums[l] == nums[l-1]) {
                            l ++;
                        }
                        r --;
                        while (l < r && nums[r] == nums[r+1]){
                            r --;
                        }
                    } else if (n < 0) {
                        l ++;
                        // 即使后面有重复的也不会 和=0
                        // while (nums[l] == nums[l-1]) {
                        //     l ++;
                        // }
                    } else {
                        r --;
                    }
                }
            }
            return res;
        }

        // 1.暴力
        public List<List<Integer>> threeSum01(int[] nums) {
            int len = nums.length;
            Set<List<Integer>> res = new HashSet<>();
            Arrays.sort(nums);
            for (int i = 0; i < len-2; i++) { // 注意结束条件
                for (int j = i+1; j < len-1; j++) { // 注意起始条件
                    for (int k = j+1; k < len; k++) {
                        // 直接最里层判断3数的和，避免维护sum的状态
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            // List<Integer> list = new ArrayList<>();
                            // list.add(nums[i]);
                            // list.add(nums[j]);
                            // list.add(nums[k]);
                            // 排序是为了去重
                            // list.sort(Comparator.comparingInt(x -> x));
                            // res.add(list);
                            // 替换上面代码   先对nums排序，再三重循环
                            res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }
                    }
                    // sum = nums[i];
                }
                // sum = 0;
            }
            return new ArrayList<>(res);
            }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
