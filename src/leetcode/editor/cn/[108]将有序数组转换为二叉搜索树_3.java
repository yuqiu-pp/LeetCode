//将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定有序数组: [-10,-3,0,5,9],
//
//一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 树 深度优先搜索

package leetcode.editor.cn;

class LC108{
    public static void main(String[] args) {
        Solution solution = new LC108().new Solution();
        // TO TEST
        int[] nums = {-10,-3,0,5,9};
        System.out.println(solution.sortedArrayToBST(nums));
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
        // 每次选mid作为root，然后左右两半都是重复过程
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }
        private TreeNode helperr(int[] nums, int l, int r) {
            if (l > r) {
                return null;
            }
            int mid = l + (r - l) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = helperr(nums, l, mid - 1);
            node.right = helperr(nums, mid + 1, r);
            return node;
        }


        // 二叉搜索树的中序遍历得到是有序数组
        // 每次取mid作为root，保持左右子树平衡
        // 重复子问题：取mid作为root后，左右都是相同的操作，递归
        public TreeNode sortedArrayToBST02(int[] nums) {
            return recur(nums, 0, nums.length - 1);
        }
        private TreeNode recur(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }
            int mid = left + (right - left) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = recur(nums, left, mid - 1);
            node.right = recur(nums, mid + 1, right);
            return node;
        }


        public TreeNode sortedArrayToBST01(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        private TreeNode helper(int[] nums, int left, int right) {
            if (left > right) {
                // 竟然不知道这里要反啥
                return null;
            }
            int mid = left + (right - left) / 2;
            TreeNode note = new TreeNode(nums[mid]);
            note.left = helper(nums, left, mid - 1);
            note.right = helper(nums, mid + 1, right);
            return note;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
