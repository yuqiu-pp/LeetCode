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
    // 后序  尾位置是root   将中序分成3份.  这样递归下去
    // 中序遍历映射hash表，方便检索index
    HashMap<Integer, Integer> map = new HashMap<>();
    int p = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return new TreeNode(postorder[0]);
        }

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        p = n - 1;
        return build(inorder, postorder, 0, n-1);
    }

    public TreeNode build(int[] inorder, int[]postorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[p]);
        int i = map.get(postorder[p]);
        // p 的递归，递归一直是先构建右子树，所以p从右向左依次递减每次都是取最后一把元素
        p = p - 1;

        root.right = build(inorder, postorder, i+1, end);
        root.left = build(inorder, postorder, start, i-1);
        return root;
    }



}
//leetcode submit region end(Prohibit modification and deletion)

}

