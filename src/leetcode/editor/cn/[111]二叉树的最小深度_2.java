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

class LC111{
    public static void main(String[] args) {
        Solution solution = new LC111().new Solution();
        // TO TEST
        System.out.println(solution.minDepth(null));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
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
