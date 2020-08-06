//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索 
// 👍 444 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LC114{
    public static void main(String[] args) {
        Solution solution = new LC114().new Solution();
        // TO TEST
        // System.out.println(solution.flatten(null));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 如果能按 6 5 4 3 2 1 顺序遍历，每遍历一个节点就将当前节点的右指针更新为上一个节点
    // 后序遍历  root.right / root.left / root
    // 原地算法
    public void flatten(TreeNode root) {

    }


    TreeNode pre = null;
    public void flatten02(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        // 保存right
        root.right = pre;
        root.left = null;
        pre = root;
    }

    // 前序遍历 放入list，再做为右节点
    // 也可以queue代替list
    public void flatten01(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        helper01(root, list);
        TreeNode node = root;
        for (int i = 1; i < list.size(); i++) {
            node.left = null;
            node.right = list.get(i);
            node = node.right;
        }
    }
    private void helper01(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        helper01(node.left, list);
        helper01(node.right, list);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
