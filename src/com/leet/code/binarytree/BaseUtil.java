package com.leet.code.binarytree;

public class BaseUtil {

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
