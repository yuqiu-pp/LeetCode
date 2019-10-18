package com.leet.code.list;

public class LC24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }

        // 哨兵
        ListNode node = new ListNode(-1);
        node.next = head;
        head = node;
        ListNode i = head;
        ListNode j = head.next;
        ListNode k = head.next.next;

        // i指向交换结点的前序结点，j和k是交换结点
        while(true){
            i.next = k;
            j.next = k.next;
            k.next = j;
            if(j.next != null && j.next.next != null){
                i = j;
                j = i.next;
                k = i.next.next;
            }else{
                break;
            }
        }

        return head.next;
    }


    public static void main(String[] args) {
        LC24 solution = new LC24();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode head = solution.swapPairs(node1);
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
