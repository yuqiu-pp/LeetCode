//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class LC111{
    public static void main(String[] args) {
        Solution solution = new LC111().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;
        TreeNode m = new TreeNode(15);
        TreeNode n = new TreeNode(7);
        right.left = m;
        right.right = n;
        System.out.println(solution.minDepth02(root));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 遍历每个路径，记录最小值
        // bfs  最小层 点的左右子树都为空
        private int minDepth02(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int deep = 0;
            while (!queue.isEmpty()) {
                deep ++;
                int n = queue.size();
                for (int i = 0; i < n; i++) {
                    TreeNode node = queue.poll();
                    if (node.left == null && node.right == null) {
                        return deep;
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return deep;
        }

        // 注意：当有一个子树为空时，返回两个子树中的max；否则，返回两者中的min
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            if (left == 0 || right == 0) {
                // return Math.max(left, right);
                return 1 + left + right;
            }
            return 1 + Math.min(left, right);
        }

        // dfs
        public int minDepth01(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            if (left == 0 || right == 0) {
                // return 1 + Math.max(left, right);
                return 1 + left + right;
            } else {
                return 1 + Math.min(left, right);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
