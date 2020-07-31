//给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 
//请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例： 
//
// matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//
//返回 13。
// 
//
// 
//
// 提示： 
//你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。 
// Related Topics 堆 二分查找

package leetcode.editor.cn;

import java.util.*;

class LC378{
    public static void main(String[] args) {
        Solution solution = new LC378().new Solution();
        int[][] matrix = {
           { 1,  5,  9},
           {10, 11, 13},
           {12, 13, 15}
        };
        // TO TEST
        System.out.println(solution.kthSmallest(matrix, 8));
    }

    // 默认构造方法：PriorityQueue() 根据其自然顺序对元素进行排序
    // PriorityQueue(Comparator<? super E> comparator) Comparator可以是对象.val  (node1, node2) -> node2.val - node1.val

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 第K小  大顶堆  比堆顶小，则入堆
        // 按题意，不需要排重

        public int kthSmallest(int[][] matrix, int k) {
            Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
            Set<Integer> set = new HashSet<>();
            // 遍历
            int m = matrix.length;
            int n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // if (set.contains(matrix[i][j])) {
                    //     continue;
                    // }
                    if (queue.size() < k) {
                        queue.offer(matrix[i][j]);
                    } else {
                        if (matrix[i][j] < queue.peek()) {
                            queue.poll();
                            queue.offer(matrix[i][j]);
                        }
                    }
                    // set.add(matrix[i][j]);
                }
            }
            // while (!queue.isEmpty()) {
            //     System.out.println(queue.poll());
            // }
            return queue.poll();
        }


        // 堆排序
        public int kthSmallest02(int[][] matrix, int k) {
            Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (queue.size() < k) {
                        queue.add(matrix[i][j]);
                    } else {
                        if (queue.peek() > matrix[i][j]) {
                            queue.poll();
                            queue.add(matrix[i][j]);
                        }
                    }
                }
            }
            return queue.peek();
        }


        public int kthSmallest01(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0];
            int right = matrix[n - 1][n - 1];
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (check(matrix, mid, k, n)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        public boolean check(int[][] matrix, int mid, int k, int n) {
            int i = n - 1;
            int j = 0;
            int num = 0;
            while (i >= 0 && j < n) {
                if (matrix[i][j] <= mid) {
                    num += i + 1;
                    j ++;
                } else {
                    i --;
                }
            }
            return num >= k;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}