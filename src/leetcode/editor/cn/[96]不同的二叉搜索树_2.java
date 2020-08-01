//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？ 
//
// 示例: 
//
// 输入: 3
//输出: 5
//解释:
//给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3 
// Related Topics 树 动态规划

package leetcode.editor.cn;

import java.util.HashMap;

class LC96{
    public static void main(String[] args) {
        Solution solution = new LC96().new Solution();
        // TO TEST
        System.out.println(solution.numTrees(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 有序数组，遍历选任一点作为root，
    // 左右两边的数据分别再去构建一个二叉搜索树，对应root的左右节点
    // f(左)=0, f(n) = f(右)
    // f(右)=0, f(n) = f(左)
    // 都不等于0, f(n) = f(左) * f(右)
    // 总的数量 = 每个点的f(n)和
    // f(0) = 1, f(1) = 1, f(2) = 2,  数量只与节点个数有关
    // 注意f(0)=1 空树也是
    class Solution {
        HashMap<Integer, Integer> map = new HashMap<>();

        public int numTrees(int n) {
            if (n < 2) {
                return 1;
            }
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int res = 0;
            // 题目是 1~n
            for (int i = 1; i <= n; i++) {
                res += numTrees(i - 1) * numTrees(n - i);
            }
            map.put(n, res);
            return res;
        }



        // 优化：递归有重复计算， 记忆化搜索
        // Map<Integer, Integer> map = new HashMap<>();
        public int numTrees03(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            int res = 0;
            // 任意一点做root
            if (map.containsKey(n)) {
                return map.get(n);
            }
            for (int i = 1; i <= n; i++) {
                res += numTrees03(i - 1) * numTrees03(n - i);
            }
            map.put(n, res);
            return res;
        }

        // hash表超时， 原因：map的东西不对，应该直接map(n)
        // ？ 但为啥比递归还慢

        public int numTrees02(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            map.put(0, 1);
            map.put(1, 1);
            int res = 0;
            // 任意一点做root
            for (int i = 1; i <= n; i++) {
                // int left = map.containsKey(i - 1) ? map.get(i - 1) : numTrees(i - 1);
                // int right = map.containsKey(n - i) ? map.get(n - i) : numTrees(n - i);
                int left = 0;
                int right = 0;
                if (map.containsKey(i - 1)) {
                    left = map.get(i - 1);
                    map.put(i - 1, left);
                } else {
                    left = numTrees02(i - 1);
                }

                if (map.containsKey(n - i)) {
                    right = map.get(n - i);
                    map.put(n - i, right);
                } else {
                    right = numTrees02(n - i);
                }
                // 优化：递归有重复计算， 记忆优化
                res += left * right;

            }
            return res;
        }

        // 递归
        public int numTrees01(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            int res = 0;
            // 任意一点做root
            for (int i = 1; i <= n; i++) {
                res += numTrees01(i - 1) * numTrees01(n - i);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}