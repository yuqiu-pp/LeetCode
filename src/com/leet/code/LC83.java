package com.leet.code;

import java.util.HashMap;

public class LC83 {

    /**
     * 执行用时 :4 ms
     * 内存消耗 :34.5 MB
     */
    // 1. 通用方法（即使链表是无序的）
    // 节点移入新的链表，同时存入hash表；
    // 节点移入新链表前，先检查hash表中是否已经存在。若存在则丢弃；
    public ListNode deleteDuplicates3(ListNode head)
    {
        if (head == null){
            return head;
        }

        // 遍历链表读取每个节点，如果是不重复节点插入新链表中
        // 判断节点是否重复的方法：
        // 每次读出节点，先去hash表中查询，如果存在，则为重复节点；否则，插入hash表

        HashMap<Integer, Integer> map = new HashMap<>();
        // 遍历链表的节点指针. head节点是第一个，必然不会是因为重复要删除的节点，作为新链表head
        map.put(head.val, head.val);
        ListNode current = head.next;
        // 新链表的尾节点.  插入队尾时用
        ListNode tail = head;

        while (current != null){
            // 没找到，说明不是重复节点，该节点加入新链表和hash表
            if (!map.containsKey(current.val)){
                map.put(current.val, current.val);
                tail.next = current;
                tail = current;
            }
            current = current.next;
        }
        tail.next = null;
        return head;
    }

    /**
     * 执行用时 :2 ms
     * 内存消耗 :37 MB
     */
    // 2. 基于已排序链表
    // 遍历链表，如果cur.val == cur.next.val，cur.next = cur.next.next
    // 跳过重复元素
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode curr = head;

        while (curr != null && curr.next != null){
            if (curr.val == curr.next.val){
                curr.next = curr.next.next;
            }else {
                curr = curr.next;
            }
        }

        return head;
    }


    /**
     *  执行用时 :2 ms, 在所有 Java 提交中击败了84.33%的用户
        内存消耗 :36.6 MB, 在所有 Java 提交中击败了62.57%的用户
     */
    // 3. 递归套路
    // 结束条件：只剩一个点，即curr.next == null
    // 排重：返回的点 与 接收返回的点 值相同，直接返回“返回点”，否则返回“接收返回的点”
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        // 已去重的链表头节点
        // ListNode rst = deleteDuplicates(head.next);
        // if (head.val == rst.val){
        //     // 丢弃head
        //     head = rst;
        // }else {
        //     head.next = rst;
        // }

        // 优化  执行时间上差别不大，内存少了一个变量
        // head和返回的节点不重复时，此时已经拼好
        head.next = deleteDuplicates(head.next);
        // 但还需要检查一下head和返回的是否重复。重复的话，丢弃head
        if (head.val == head.next.val){
            head = head.next;
        }

        return head;
    }
}
