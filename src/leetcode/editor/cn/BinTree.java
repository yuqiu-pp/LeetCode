package leetcode.editor.cn;

public class BinTree {

    // 递归建树
    public TreeNode createBinTree(Integer[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int len = nums.length;
        // curr: i  left: 2*i+1  right: 2*i+2
        TreeNode root = new TreeNode(nums[0]);
        TreeNode node = root;
        // int i
        for (int i = 0; i < len; i++) {
            int n = i * 2 + 1;
            if (n < len) {
                node.left = new TreeNode(nums[n]);
            }
            n = i * 2 + 2;
            if (n < len) {
                root.right = new TreeNode(nums[n]);
            }
        }
        return root;
    }

    TreeNode addTreeNode(TreeNode node) {

        return node;
    }
}
