//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表

package leetcode.editor.cn;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class LC94{
    public static void main(String[] args) {
        Solution solution = new LC94().new Solution();
        // TO TEST
        Integer[] nums = {1,null,2,3};
        BinTree binTree = new BinTree();
        TreeNode root = binTree.createBinTree(nums);
        System.out.println(Arrays.toString(solution.inorderTraversal(root).toArray()));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        List<Integer> res = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return res;
            }
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }

        // -----------栈---------------
        // 在递归的过程中使用了系统栈，所以在迭代的解法中常用Stack来模拟系统栈
        public List<Integer> inorderTraversal03(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            List<Integer> res = new ArrayList<>();
            TreeNode curr = root;
            while (curr != null ||  !stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
            return res;
        }

        // -----------树 递归----------------
        public List<Integer> inorderTraversal02(TreeNode root) {
            if (root ==  null) {
                return null;
            }
            List<Integer> list = new ArrayList<>();
            inOrderTraver(root, list);
            return list;
        }

        public void inOrderTraver(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            inOrderTraver(root.left, list);
            list.add(root.val);
            inOrderTraver(root.right, list);
        }

        // ---------------------------
        List<Integer> list = new ArrayList<>();
        public List<Integer> inorderTraversal01(TreeNode root) {
            if (root == null) {
                return list;
            }
            inorderTraversal01(root.left);
            list.add(root.val);
            inorderTraversal01(root.right);
            return list;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
