package com.leet.code.binarytree;



public class LC687 {

    public static void main(String[] args) {
        // Integer[] B = {1,null,1,null,null,1,1,null,null,null,null,1,1,1};
        Integer[] B = {5,4,5,1,1,5};
        LC687 solution = new LC687();
        TreeNode root = solution.createBSTByArray(B, 0);

        System.out.println(solution.longestUnivaluePath(root));
    }

    int max = 0;

    /**
     *  执行用时 :8 ms, 在所有 java 提交中击败了39.19%的用户
        内存消耗 :40 MB, 在所有 java 提交中击败了96.51%的用户
     */
    // num = left + right，左右子树中相连的、且值相同的点数量和
    // left.val = root.val，则left + 1
    // right 相同
    public int longestUnivaluePath(TreeNode root) {
        if (root == null){
            return 0;
        }
        subPath(root);
        return max;
    }

    // 遍历每个节点
    public int subPath(TreeNode node){
        if (node == null){
            return 0;
        }
        // 从最底层遍历
        // 当前根节点的最大值=left+right，但是向上一层递归返回max(left, right)
        //        5
        //     4     5       此做跟节点时num=left+right， 但要给上层的子节点时要返回max(left, right)
        //         5   5
        //       5

        // 1. 先递归到底层
        int leftNum = subPath(node.left);
        int rightNum = subPath(node.right);

        int left = 0;
        int right = 0;
        if (node.left != null && node.left.val == node.val){
            left = leftNum + 1;         // 2.这里用leftNum代替subPath(node.left)，保证从底层开始
        }
        if (node.right != null && node.right.val == node.val){
            right = rightNum + 1;
        }

        max = Math.max(max, left+right);
        return Math.max(left, right);
    }

// =======================================================

    /**
     *  执行用时 :12 ms, 在所有 java 提交中击败了13.72%的用户
        内存消耗 :39.4 MB, 在所有 java 提交中击败了97.67%的用户
     */
    // 从上向下，递归遍历每个节点，作为根节点，计算最大值
    // 自顶向下的一种方式
    // 时间复杂度：O(n*n) 每个节点都有遍历整个树，计算max
    // 空间复杂度：O(h) 递归最大深度是树高度。遇到值不相等递归就会结束，而且越往下层的节点深度越低。一般不会到达最大深度
    public int longestUnivaluePath1(TreeNode root) {
        if (root == null){
            return 0;
        }
        inOrder(root);
        return max;
    }

    // 遍历二叉树
    public void inOrder(TreeNode node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        int ret = maxNums(node, node);
        if (ret > max){
            max = ret;
        }
        inOrder(node.right);
    }


    // 从父节点开始，从两边子树找与父节点值相同的节点数量
    // num = max(left) + max(right)
    public int maxNums(TreeNode node, TreeNode curRoot){
        if (node.left == null && node.right == null){
            return 0;
        }
        int left = 0;
        int right = 0;
        if (node.left != null && node.right != null){
            // 左右子树值相同时，如果是跟节点num=left+right，否则num=max(left,right)+1
            if (node.val == node.left.val && node.val == node.right.val){
                if (node == curRoot){
                    return 2 + maxNums(node.left, curRoot) + maxNums(node.right, curRoot);
                }else {
                    return Math.max(maxNums(node.left, curRoot), maxNums(node.right, curRoot)) + 1;
                }
            }
        }

        if (node.left != null && node.val == node.left.val) {
            left = maxNums(node.left, curRoot) + 1;
        }
        if (node.right != null && node.val == node.right.val) {
            right = maxNums(node.right, curRoot) + 1;
        }

        return Math.max(left, right);
    }




    public TreeNode createBSTByArray(Integer[] a, int index){
        int len = a.length;

        // 创建当前节点
        TreeNode node = null;

        // root放index=0
        // 左子树节点在数组中的位置  2*i+1
        // 右子树  2*i+2
        if (index < len && a[index]!=null) {
            node = new TreeNode(a[index]);
            // 创建左节点
            node.left = createBSTByArray(a, 2 * index + 1);
            // 创建右节点
            node.right = createBSTByArray(a, 2 * index + 2);
        }
        return node;
    }
}
