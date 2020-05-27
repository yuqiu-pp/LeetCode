//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LC120{
    public static void main(String[] args) {
        Solution solution = new LC120().new Solution();
        // TO TEST
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(-1));
        triangle.add(Arrays.asList(2,3));
        triangle.add(Arrays.asList(1,-1,-3));
        // triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(solution.minimumTotal(triangle));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // f(n) = min (triangle.get(level).get(index), triangle.get(level).get(index+1), )
    // 上面解题方式 其实是贪心算法，每步最优，但结果不一定最优
    // 正确解法：每一层，每一个点都计算，最后取最后一行最小值  ？ 感觉是遍历了，不像动态规划

    class Solution {
        // 自底向上  这样的话每个上层节点的值 都来自于 两个点，一个通用的公式即可
        // dp[j] = min(dp[j], dp[j+1]) , 也不存在值覆盖的情况
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle.size() == 0) {
                return 0;
            }
            int len = triangle.size();
            int[] dp = new int[len];

            // 用最后一层的值初始化dp[]
            for (int i = 0; i < len; i++) {
                dp[i] = triangle.get(len - 1).get(i);
            }
            // 倒数第二行开始
            for (int i = triangle.size() - 2; i >= 0;  i--) {
                for (int j = 0; j <= i; j++) {
                    dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];
        }

        // 自顶向下
        // 优化：当前层的值只取决于上一层，所以一维数组即可 . 但这样会同一层没处理完就会发生值覆盖，还需要引入临时变量缓存
        public int minimumTotal01(List<List<Integer>> triangle) {
            if (triangle.size() == 0) {
                return 0;
            }
            if (triangle.size() == 1) {
                return triangle.get(0).get(0);
            }
            int len = triangle.size();
            int dp[][] = new int[len][len];

            dp[0][0] = triangle.get(0).get(0);
            for (int i = 1; i < len; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                    } else if (i == j) {
                        dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                    } else {
                        // 两个路径都可以达到该点
                        dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
                    }
                }
            }
            int res = dp[len-1][0];
            for (int i = 1; i < len; i++) {
                if (dp[len-1][i] < res) {
                    res = dp[len-1][i];
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}