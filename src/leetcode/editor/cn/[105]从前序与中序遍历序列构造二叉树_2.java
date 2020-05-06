//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组

package leetcode.editor.cn;

import java.util.Arrays;

class LC105{
    public static void main(String[] args) {
        Solution solution = new LC105().new Solution();
        // TO TEST
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
        System.out.println(solution.buildTree(preOrder, inOrder));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 前序遍历：根 左 右      左即为左子树的根，所以前序就从这里开始
        // 中序遍历：左 根 右      根将数组分成左右子树的中序遍历，递归
        // pre [3,       5, 6, 2,   1, 0, 7, 4, 8]
        //     root       len=i
        // in  [6, 5, 2,   3,     7, 0, 4, 1, 8]
        //                 i
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 递归出口
            if (preorder.length == 0) {
                return null;
            }
            // prcess : 前序第一个点是跟节点；中序中该节点将树分为左右子树
            TreeNode node = new TreeNode(preorder[0]);
            int i = 0;
            while (preorder[0] == inorder[i]) {
                i++;
            }
            // 进入下一层
            node.left = buildTree(Arrays.copyOfRange(preorder, 1, i+1), Arrays.copyOfRange(inorder, 0, i));
            node.right = buildTree(Arrays.copyOfRange(preorder, i+1, preorder.length), Arrays.copyOfRange(inorder, i+1, inorder.length));
            return node;
        }

        public TreeNode buildTree01(int[] preorder, int[] inorder) {
            // 递归出口
            if (preorder.length == 0) {
                return null;
            }
            // 当前层逻辑：
            TreeNode root = new TreeNode(preorder[0]);
            int i = 0;
            // for (i = 0; i < inorder.length; i++) {
            //     if (inorder[i] == preorder[0]) {
            //         break;
            //     }
            // }
            while (inorder[i] != preorder[0]) {
                i++;
            }
            System.out.println(i);
            // 传递下一层
            root.left = buildTree(Arrays.copyOfRange(preorder,1,i+1), Arrays.copyOfRange(inorder,0,i));
            root.right = buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length), Arrays.copyOfRange(inorder,i+1,inorder.length));
            // 清除当前层数据
            return root;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
