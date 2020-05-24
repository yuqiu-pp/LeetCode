//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索

package leetcode.editor.cn;

import sun.security.x509.RFC822Name;

import java.util.ArrayList;
import java.util.List;

class LC98{
    public static void main(String[] args) {
        Solution solution = new LC98().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(10);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(15);
        root.left = left;
        root.right = right;
        TreeNode m = new TreeNode(6);
        TreeNode n = new TreeNode(20);
        right.left = m;
        right.right = n;
        System.out.println(solution.isValidBST(root));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 左子树小于当前节点  root.left.val < root.val； root.right.val > root.val
        // 子树也是二叉搜索树  递归，但子树节点值要小于根节点
        public boolean isValidBST(TreeNode root) {
            return subIsValid(root, null, null);
        }
        private boolean subIsValid01(TreeNode node, TreeNode lower, TreeNode upper) {
            if (node == null) {
                return true;
            }
            if (lower != null && node.val <= lower.val) {
                return false;
            }
            if (upper != null && node.val >= upper.val) {
                return false;
            }
            return subIsValid01(node.left, lower, node) &&  subIsValid01(node.right, node, upper);
        }
        //  如果传值的话，就要判 左右子树是否为空，逻辑比较复杂，所以传节点比较好
        // private boolean subIsValid(TreeNode node, int low, int high) {
        // 传节点 是 从下一层 往上比较的思路，省去了先判空再操作的复杂性
        private boolean subIsValid(TreeNode node, TreeNode lower, TreeNode upper) {
            if (node == null) {
                return true;
            }
            if (lower != null && node.val <= lower.val) {
                return false;
            }
            if (upper != null && node.val >= upper.val) {
                return false;
            }
            return subIsValid(node.left, lower, node) && subIsValid(node.right, node, upper);
        }

        // 1.子树值满足与当前节点的关系
        // 2.子树值要满足与根节点的关系，所处递归时要把root的值传递下去
        public boolean isValidBST03(TreeNode root) {
            return dfs(root, null, null);
        }
        private boolean dfs(TreeNode node, TreeNode lower, TreeNode upper) {
            // terminator
            if (node == null) {
                return true;
            }
            // process：满足两个条件，进入下一层
            // 这种判断有问题，当右子树中的左节点，要大于跟节点值，判断出问题
            // 所以边界是根据左或右动态变化的，要传递
            // if (node.left != null && (node.left.val >= node.val || node.left.val >= rootVal)) {
            //     return false;
            if (lower != null && node.val <= lower.val) {
                return false;
            }
            if (upper != null && node.val >= upper.val ) {
                return false;
            }
            // 这里不能传null了
            return dfs(node.left, null, node) && dfs(node.right, node, null);
        }


        // 1. 递归判断子树是否是二叉搜索树。
        // 注意：所有左子树结点都小于root，右子树结点都大于root，这个条件。 因此要传入上下界
        // 2. 中序遍历二叉树，如果得到的数组是递增，即为二叉搜索树
        public boolean isValidBST02(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            // 中序遍历  O(n)
            inOrderTree(root, list);
            // 验证递增 O(n)
            if (list.size() < 1) {
                return true;
            }
            int tmp = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (tmp >= list.get(i)) {
                    return false;
                }
                tmp = list.get(i);
            }
            return true;
        }
        private void inOrderTree(TreeNode root, List<Integer> list) {
            // 递归出口
            if (root == null) {
                return;
            }
            inOrderTree(root.left, list);
            list.add(root.val);
            inOrderTree(root.right, list);
        }

        // 1. 递归判断子树是否是二叉搜索树
        public boolean isValidBST01(TreeNode root) {
            return helper(root, null, null);
        }
        public boolean helper(TreeNode node, TreeNode lower, TreeNode upper) {
            if (node == null) {
                return true;
            }
            if (lower != null && node.val <= lower.val) {
                return false;
            }
            if (upper != null && node.val >= upper.val) {
                return false;
            }
            return helper(node.left, lower, node) && helper(node.right, node, upper);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
