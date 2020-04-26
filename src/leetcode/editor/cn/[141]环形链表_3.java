//给定一个链表，判断链表中是否有环。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。 
//
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
// Related Topics 链表 双指针

package leetcode.editor.cn;

import java.util.HashMap;

class LC141{
    public static void main(String[] args) {
        Solution solution = new LC141().new Solution();
        // TO TEST
        System.out.println(solution.hasCycle(null));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    //  * Definition for singly-linked list.
    // */
    public class Solution {
        // 快慢指针，类似于两个人操场跑圈
        public boolean hasCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null  && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }
        public boolean hasCycle02(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null  && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }

        // hashmap保存已访问过的点，如果发现重复，说明有环
        public boolean hasCycle01(ListNode head) {
            HashMap<ListNode, Integer> hashMap = new HashMap<>();
            while (head != null) {
                if (hashMap.containsKey(head)) {
                    return true;
                }
                hashMap.put(head, 1);
                head = head.next;
            }
            return false;
        }
    }

    class ListNode {
         int val;
         ListNode next;
         ListNode(int x) {
             val = x;
             next = null;
         }
     }
    //leetcode submit region end(Prohibit modification and deletion)

}
