package com.leet.code.binarytree;

import java.util.SplittableRandom;

public class LC437 {

    public static void main(String[] args) {
        BaseUtil baseUtil = new BaseUtil();

        // Integer[] B = {1,-2,-3,1,3,-2,null,-1};
        // int sum = -1;
        Integer[] B = {5,4,8,11,null,13,4,7,2,null,null,null,null,5,1};
        int sum = 22; // 3
        // Integer[] B = {-2,null,-3};
        // int sum = -5;

        LC437 solution = new LC437();
        TreeNode root = baseUtil.createBSTByArray(B, 0);

        System.out.println(solution.pathSum1(root, sum));
    }


    int ret = 0;
    int tmpSum = 0;

    // 从上向上，递归遍历每个节点，作为根节点，查找合法的路径
    public int pathSum(TreeNode root, int sum) {
        preOrder(root, sum);
        return ret;
    }

    /**
     *  执行用时 :16 ms, 在所有 java 提交中击败了85.44%的用户
        内存消耗 :39.2 MB, 在所有 java 提交中击败了70.19%的用户
     */
    // 算法复杂度：O(n*n)
    // 空间复杂度：递归最大树的高度
    public void preOrder(TreeNode node, int sum){
        if (node == null){
            return;
        }

        findSubPath(node, sum);
        preOrder(node.left, sum);
        preOrder(node.right, sum);
    }

    // node作为跟节点，计算满足和=sum的数量
    public void findSubPath(TreeNode node, int subSum){
        if (node == null){
            return;
        }
        if (node.val == subSum){
            ret ++;
        }
        // 根节点小于sum，查找左右子树
        if (node.left != null){
            findSubPath(node.left, subSum - node.val);
        }

        if (node.right != null){
            findSubPath(node.right, subSum - node.val);
        }
    }



    // [二]
    // 两个方法思想是一致的，只是代码更简洁了
    /**
     *  执行用时 :14 ms, 在所有 java 提交中击败了89.44%的用户
        内存消耗 :39.3 MB, 在所有 java 提交中击败了68.02%的用户
     */
    // 从上向上，递归遍历每个节点，作为根节点，查找合法的路径
    public int pathSum1(TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        return findPath(root, sum) + pathSum1(root.left, sum) + pathSum1(root.right, sum);
    }

    // 从根节点开始，分别查找符合条件的路径数量
    public int findPath(TreeNode node, int sum){
        if (node == null){
            return 0;
        }

        int res = 0;
        if (node.val == sum){
            res ++;
        }
        if (node.left != null){
            res += findPath(node.left, sum - node.val);
        }
        if (node.right != null){
            res += findPath(node.right, sum - node.val);
        }

        return res;

    }

}
