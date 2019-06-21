package com.leet.code;

public class LC104 {

    /**
     * 执行用时 :1 ms, 在所有 Java 提交中击败了96.82%的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了76.15%的用户
     */
    // 递归
    // max(left, right) + 1
    public int maxDepth(TreeNode root) {
        // 递归出口
        if (root == null){
            return 0;
        }

        // 递推公式
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
