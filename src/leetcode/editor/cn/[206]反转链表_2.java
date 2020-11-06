//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1276 👎 0

package leetcode.editor.cn;

class PL206{
    public static void main(String[] args) {
        Solution solution = new PL206().new Solution();
        // TO TEST
        System.out.println(solution.reverseList(null));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

 // * Definition for singly-linked list.
 // public class ListNode {
 //     int val;
 //     ListNode next;
 //     ListNode(int x) { val = x; }
 // }

    class Solution {
        // 反转列表
        // 1.栈反转顺序
        // 2.迭代
        // 3.递归
        public ListNode reverseList(ListNode head) {
            // ListNode prev = head;
            // ListNode curr = head.next;
            // 这里prev必须设为null，因为反转后链表的尾节点要指向null
            ListNode prev = null;
            ListNode curr = head.next;
            while (curr != null) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }
            return prev;
        }

        public ListNode reverseList03(ListNode head) {
            // if (head == null || head.next == null) {  非必须
            //     return head;
            // }
            ListNode prev = null;
            // ListNode curr = head.next;  指向next会少一个节点
            ListNode curr = head;
            while (curr != null) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }
            return prev;
        }

        public ListNode reverseList01(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTmp;
            }
            return prev;
        }

        public ListNode reverseList02(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 这里传入的参数是head.next
            ListNode p = reverseList(head.next);
            // 递归到next的空时返回，这是head.next指向最后一个节点
            head.next.next = head;
            head.next = null;
            // p指向head
            return p;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

