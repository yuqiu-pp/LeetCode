//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 深度优先搜索 链表 
// 👍 347 👎 0

package leetcode.editor.cn;

class PL109{
    public static void main(String[] args) {
        Solution solution = new PL109().new Solution();
        // TO TEST
        System.out.println(solution.sortedListToBST(null));
    }
    

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

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
        public TreeNode sortedListToBST(ListNode head) {
            // 转为数组
            // List<Integer> list = new ArrayList<>();
            // while (head.next != null) {
            //     list.add(head.val);
            // }

            return buildTree(head, null);
        }

        private TreeNode buildTree02(ListNode left, ListNode right) {
            if (left == right) {
                return null;
            }
            ListNode mid = getMid(left, right);
            TreeNode root = new TreeNode(mid.val);
            root.left = buildTree(left, mid);
            root.right = buildTree(mid.next, right);
            return root;
        }
        private ListNode getMid01(ListNode left, ListNode right) {
            ListNode fast = left;
            ListNode slow = right;
            while (fast != right && fast.next != right) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        private TreeNode buildTree(ListNode left, ListNode right) {
            if (left == right) {
                return null;
            }
            ListNode mid = getMid(left, right);
            TreeNode root = new TreeNode(mid.val);
            root.left = buildTree(left, mid);
            root.right = buildTree(mid.next, right);
            return root;
        }
        private ListNode getMid(ListNode left, ListNode right) {
            ListNode fast = left;
            ListNode slow = left;
            while (fast != right && fast.next != right) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        private TreeNode buildTree01(ListNode left, ListNode right) {
            if (left == right) {
                return null;
            }
            ListNode mid = getMedian(left, right);
            TreeNode root = new TreeNode(mid.val);
            root.left = buildTree01(left, mid);   // 这里mid没有减1，因为getMidian中有fast.next!=fast的限制
            root.right = buildTree01(mid.next, right);
            return root;
        }
        private ListNode getMedian(ListNode left, ListNode right) {
            ListNode fast = left;
            ListNode slow = left;
            while (fast != right && fast.next != right) {
                fast = fast.next;
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
    }

}

