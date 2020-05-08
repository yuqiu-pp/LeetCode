//给定一个二叉树，返回它的 前序 遍历。 
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
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LC144{
    public static void main(String[] args) {
        Solution solution = new LC144().new Solution();
        // TO TEST
        // System.out.println(solution.preorderTraversal());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            dfs(root, list);
            return list;
        }
        private void helper(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            helper(root.left, list);
            helper(root.right, list);
        }

        public void dfs(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            dfs(root.left, list);
            dfs(root.right, list);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
