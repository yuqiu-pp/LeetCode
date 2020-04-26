//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class LC429{
    public static void main(String[] args) {
        Solution solution = new LC429().new Solution();
        // TO TEST
        // System.out.println(solution.N叉树的层序遍历());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 利用队列把每层当当数据都缓存起来
        // 如何在队列中记录每层的结束 ？当前队列的size
        public List<List<Integer>> levelOrder(Node root) {
            Queue<Node> queue = new LinkedList<>();
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            // 遍历的同时，node入队列
            queue.add(root);
            while (!queue.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    list.add(node.val);
                    queue.addAll(node.children);
                }
                res.add(list);
            }
            return res;
        }

        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }

        ;
    }
//leetcode submit region end(Prohibit modification and deletion)

}
