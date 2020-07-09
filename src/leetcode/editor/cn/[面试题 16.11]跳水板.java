//你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方
//法，生成跳水板所有可能的长度。 
// 返回的长度需要从小到大排列。 
// 示例： 
// 输入：
//shorter = 1
//longer = 2
//k = 3
//输出： {3,4,5,6}
// 
// 提示： 
// 
// 0 < shorter <= longer 
// 0 <= k <= 100000 
// 
// Related Topics 递归 记忆化

package leetcode.editor.cn;

import java.util.*;

class LC1611{
    public static void main(String[] args) {
        Solution solution = new LC1611().new Solution();
        // TO TEST
        System.out.println(Arrays.toString(solution.divingBoard(1, 2, 3)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] divingBoard(int shorter, int longer, int k) {
            if (k == 0) {
                return new int[]{};
            }
            if (shorter == longer) {
                return new int[]{shorter * k};
            }
            int[] res = new int[k + 1];
            for (int i = 0; i <= k; i++) {
                // i块长板 + k-i块短板 组成
                res[i] = longer * i + shorter * (k - i);
            }

            return res;
        }

        // 问题抽象： k个格子，每个格子可以可以选择short和long中任意一个
        // 回溯   超时
        public Set<Integer> res = new HashSet<>();

        public int[] divingBoard01(int shorter, int longer, int k) {
            helper(shorter, longer, k, 0);
            int[] arr = new int[res.size()];
            int i = 0;
            for (int n : res) {
                arr[i ++] = n;
            }

            return arr;
        }
        private void helper(int a, int b, int k, int curr) {
            // 递归出口
            if (k == 0) {
                res.add(curr);
                return;
            }
            // 当前层  k 作为层计数
            helper(a, b, k - 1, curr + a);
            helper(a, b, k - 1, curr + b);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}