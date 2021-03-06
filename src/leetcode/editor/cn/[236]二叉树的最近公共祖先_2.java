//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
// Related Topics 树

package leetcode.editor.cn;

class LC236{
    public static void main(String[] args) {
        Solution solution = new LC236().new Solution();
        // TO TEST
        // System.out.println(solution.lowestCommonAncestor(null));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 宏观上看，即只看root节点
        // 1、root==null  返回null 或 roo
        // 2、p or q == root  结果是root
        // 3、p、q分别在root的左、右子树中，即(left != null && right != null)  结果是root
        // 4、p、q都在root的一个子树中，即(left或right有一个是null)，返回非空者，重复1~3
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || p == root || q == root) {
                return root;
            }
            // 本层逻辑
            // 进入下一层
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) {
                return root;
            }
            return left != null ? left : right;
        }

        public TreeNode lowestCommonAncestor01(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) {
                return root;
            }
            // else if (left != null) {
            //     return left;
            // } else if (right != null) {
            //     return right;
            // } else {
            //      return null;
            // }
            // 代替上面语句
            return left != null ? left : right;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
