package com.leet.code.link;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC142 {

    // O(N)
    // 执行用时 :14 ms
    // 内存消耗 :34.7 MB
    // 检测环形链表
    // 遍历链表，访问过的点存入hash表，发现重复则有环
    // hashMap<val, pos>
    public ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        HashMap<ListNode, Integer> hashMap = new HashMap<>();

        ListNode curr = head;
        int pos = 0;
        while (curr != null){
            if (hashMap.containsKey(curr)){
                return curr;
            }
            hashMap.put(curr, pos++);
            curr = curr.next;
        }

        return null;
    }

    // 执行用时 : 18 ms
    // 内存消耗 : 36.6 MB
    // HashSet虽然少存放一个值，但效率并没有hashMap高
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> hashMap = new HashSet<>();
        ListNode curr = head;

        while (curr != null){
            if (hashMap.contains(curr)){
                return curr;
            }
            hashMap.add(curr);
            curr = curr.next;
        }

        return null;
    }


    public static void main(String[] args) {
        LC142 solution = new LC142();
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(-7);
        ListNode node3 = new ListNode(7);
        ListNode node4 = new ListNode(-4);
        ListNode node5 = new ListNode(19);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(-9);
        ListNode node8 = new ListNode(-5);
        ListNode node9 = new ListNode(-2);
        ListNode node10 = new ListNode(-5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node8;
        System.out.println(solution.detectCycle1(node1).val);
    }

}
