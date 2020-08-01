//给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。 
//
// 
//
// 示例： 
//
// 输入：3
//输出：
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//解释：
//以上的输出对应以下 5 种不同结构的二叉搜索树：
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 8 
// 
// Related Topics 树 动态规划

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LC95{
    public static void main(String[] args) {
        Solution solution = new LC95().new Solution();
        // TO TEST
        solution.generateTrees(3).forEach(System.out::println);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        return helper01(1, n);
    }
    private List<TreeNode> helper01(int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        if (left > right) {
            res.add(null);
            return res;
        }
        for (int i = left; i <= right; i++) {
            List<TreeNode> llist = helper01(left, i - 1);
            List<TreeNode> rlist = helper01(i + 1, right);
            // 从左右分别选点
            for (TreeNode l : llist) {
                for (TreeNode r : rlist) {
                    TreeNode node = new TreeNode(i);
                    node.left = l;
                    node.right = r;
                    res.add(node);
                }
            }
        }
        return res;
    }


    // 回溯
    private List<TreeNode> helper(int left, int right) {
        List<TreeNode> res = new ArrayList<>();

        if (left > right) {
            res.add(null);
            return res;
        }
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftNode = helper(left, i - 1);
            List<TreeNode> rightNode = helper(i + 1, right);
            for (TreeNode l : leftNode) {
                for (TreeNode r : rightNode) {
                    TreeNode node = new TreeNode(i);
                    node.left = l;
                    node.right = r;
                    res.add(node);
                }
            }

        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}