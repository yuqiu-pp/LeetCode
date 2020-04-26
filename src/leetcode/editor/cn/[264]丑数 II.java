//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是只包含质因数 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划

package leetcode.editor.cn;

import java.util.PriorityQueue;
import java.util.Queue;

class LC264{
    public static void main(String[] args) {
        Solution solution = new LC264().new Solution();
        // TO TEST
        System.out.println(solution.nthUglyNumber(10));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 每个值分别计算 *2、*3、*5 ，得到新的值
        //
        public int nthUglyNumber(int n) {
            if (n == 1) {
                return 1;
            }
            Queue<Long> queue = new PriorityQueue<>();
            queue.add(1L);
            for (long i = 1; i < n; i++) {
                long tmp = queue.poll();
                // 防止重复计算
                // 最小的树都在堆顶，所以取堆顶可以用来判断是否重复值
                while (!queue.isEmpty() && queue.peek()==tmp) {
                    tmp = queue.poll();
                }

                queue.add(tmp * 2);
                queue.add(tmp * 3);
                queue.add(tmp * 5);
            }
            return queue.poll().intValue();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
