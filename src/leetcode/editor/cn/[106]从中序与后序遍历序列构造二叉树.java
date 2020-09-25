//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组 
// 👍 308 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

class PL106{
    public static void main(String[] args) {
        Solution solution = new PL106().new Solution();
        // TO TEST
        // System.out.println(solution.buildTree());
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
    // 后序  尾位置是root   将中序分成3份
    // 中序遍历映射hash表，方便检索index
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
    }
    public TreeNode build(int[] inorder, int[]postorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[end]);
        int i = map.get(postorder[end]);
        end = end - 1;

        root.right = build(inorder, postorder, i+1, end);
        root.left = build(inorder, postorder, start, i-1);
        return root;
    }


    public TreeNode helper(int[] inorder, int instart, int inend, int[] postorder, int pstart, int pend) {
        TreeNode root = new TreeNode(postorder[pend]);

        root.right = helper(inorder, postorder[pend]+1, inend, postorder, )
        root.left = helper(inorder, instart, map.get(postorder[pend] - 1, postorder, ))

        for (int i = instart; i < inend; i++) {
            if (inorder[i] == postorder[pend]) {
                root.left = helper(inorder, 0, i, postorder, )
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

