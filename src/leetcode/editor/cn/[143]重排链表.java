//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 361 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class PL143{
    public static void main(String[] args) {
        Solution solution = new PL143().new Solution();
        // TO TEST
        // System.out.println(solution.ReorderList());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        // 链表节点存入list
        List<ListNode> list = new ArrayList<>();
        while (head.next != null) {
            list.add(head);
            head = head.next;
        }
        // 双指针拼接新的链表
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i ++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j --;
        }
        list.get(i).next = null;
    }

    public void reorderList01(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i ++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j --;
        }
        list.get(i).next = null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

