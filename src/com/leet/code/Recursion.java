package com.leet.code;

import java.util.ArrayList;
import java.util.Arrays;

public class Recursion {
    // 偶数个元素                 奇数个元素
    // [1, 6, 1, 5, 1, 4]        [2, 6, 1, 5, 1]
    //  小 大 小 大  小 大         小 大 小 大 小
    // 要符合上面的格式，需要对素组进行排序，然后分成大、小两部分
    // 元素是奇数个时，小的那部分要多一个元素
    public void wiggleSort(int[] nums) {
        int len = nums.length;

        if (len == 0){
            return;
        }
        // 数组排序
        int[] rst = Arrays.copyOf(nums, len);
        Arrays.sort(rst);
        for (int i = 0; i < len; i++) {
            nums[i] = 0;
        }

        // 取大数部分的起始位置
        int big = 0;
        if (len % 2 == 0){
            big = len / 2;
        }else {
            big = len / 2 + 1;
            nums[len-1] = rst[0];
        }
        // 每次分别从大、小两部分取一个数写入
        // [4,5,5,6] 分别从两部分的前面开始取数，不能满足这种情况，所以从后往前取数
        for (int i = 0; i < len - big; i++) {
            nums[i*2] = rst[big - 1 - i];
            nums[i*2+1] = rst[len -1 - i];
        }
    }


    /**
     *  执行用时 : 22 ms, 在Arranging Coins的Java提交中击败了79.62% 的用户
        内存消耗 : 32.8 MB, 在Arranging Coins的Java提交中击败了92.65% 的用户
     */
    public int arrangeCoins(int n) {
        if (n == 0){
            return 0;
        }

        // 循环分解，直到剩余数量 < 分解次数+1
        int i = 0;
        for (i = 1; i < n; i++) {
            n -= i;
            if (n < i + 1){
                break;
            }
        }

        return i;
    }

    // public double myPow1(double x, int n) {
    //
    //     double rst = 1.0;
    //     int m = 0;
    //     for (int i = n; i > 1; i/=2) {
    //         rst *= rst;
    //         m += 2;
    //         // 奇次方需要多乘一次
    //         if (i % 2 != 0){
    //             rst *= x;
    //             m += 1;
    //         }
    //
    //     }
    //     for (int j = 0; j < n-m; j++) {
    //         rst *= x;
    //     }
    //
    //     if (n < 0){
    //         rst *= 1/rst;
    //     }
    //
    //     return rst;
    // }


    /**
     *  执行用时 : 2 ms, 在Pow(x, n)的Java提交中击败了99.78% 的用户
        内存消耗 : 32.9 MB, 在Pow(x, n)的Java提交中击败了89.11% 的用户
     */
    // 把幂次方n除2，结果就是两个幂次方是n/2的数的相乘，这样只需要计算1次n/2次方的运算
    // 依次类推，直到指数变为1，不能再分
    // 如果指数为奇数，二分之后，计算结果时需要多乘一次x
    // LeetCode提交，报栈溢出
    public double myPow(double x, int n) {
        // 递归出口
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return x;
        }
        // 指数是负数
        if (n == -1){
            return 1/x;
        }

        // 计算n/2次方
        double rst = myPow(x, n/2);
        // 计算n次方
        rst = rst * rst;
        // 奇次方需要多乘一次
        if (n % 2 != 0){
            if (n > 0){
                rst *= x;
            }else {
                rst *= 1/x;
            }

        }

        return rst;
    }


    // LeetCode 687  longest-univalue-path
    public int longestUnivaluePath(TreeNode root) {

        // 最长路径 = max(left子树深度, right)
        // 递归结束条件：叶节点；  子节点值与节点值不同；
        if (root == null){
            return 0;
        }

        if (root.val != root.left.val){
            return 1;
        }else {
            // m = longestUnivaluePath(root.left) + ;
        }

        return 0;
    }

    public void inOrderTraverse(TreeNode root){
        if (root == null){
            return;
        }

        System.out.println(root.val);
        inOrderTraverse(root.left);
        inOrderTraverse(root.right);
    }


    public ArrayList createTreeByArray1(int[] a){
        int len = a.length;

        // 初始化二叉树，用数组存储

        ArrayList<TreeNode> tree = new ArrayList<TreeNode>(len);

        for (int i = 0; i < len; i++) {
            tree.add(new TreeNode(a[i]));
        }
        // 左子树节点在数组中的位置  2*i+1
        // 右子树  2*i+2
        for (int i = 0; i < len; i++) {
            if (2 * i + 1 < len) {
                tree.get(i).left = tree.get(2 * i + 1);
            }
            if (2 * i + 2 < len){
                tree.get(i).right = tree.get(2 * i + 2);
            }

        }

        return tree;
    }


    public TreeNode createTreeByArray(int[] a, int index){
        int len = a.length;

        // 初始化二叉树，用数组存储
        // 创建当前节点
        TreeNode node = null;

        // 左子树节点在数组中的位置  2*i+1
        // 右子树  2*i+2
        if (index < len) {
            node = new TreeNode(a[index]);
            // 创建左节点
            node.left = createTreeByArray(a, 2 * index + 1);
            // 创建右节点
            node.right = createTreeByArray(a, 2 * index + 2);
        }

        return node;
    }

    public static void main(String[] args) {
        int[] A = {5,3,1,2,6,7,8,5,5};
        Recursion solution = new Recursion();

        solution.wiggleSort(A);
        for (int i : A){
            System.out.println(i);
        }

        // int[] A = {5,4,5,1,1,5};
        // ArrayList<TreeNode> treeBin = solution.createTreeByArray1(A);
        // for (TreeNode t: treeBin) {
        //     System.out.println(t.val + " " + t +" " + t.left + " " + t.right);
        // }
        //
        // TreeNode root = solution.createTreeByArray(A, 0);
        // solution.inOrderTraverse(treeBin.get(0));

    }
}
