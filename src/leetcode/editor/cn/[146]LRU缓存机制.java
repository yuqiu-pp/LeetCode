//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。 
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
//
// 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计

package leetcode.editor.cn;

import java.util.*;

class LC146{
    public static void main(String[] args) {
        // Solution solution = new LC146().new Solution();
        // TO TEST
        // System.out.println(solution.LRUCache());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {
        // key, value

        private int capacity;
        // ?? 哪里有问题 key .  不能用List声明变量
        // 问题：LinkedList 只能从头和尾操作结点，所有不能用
        // private Map<Integer, Integer> lru = new HashMap<>();
        // private LinkedList<Integer> list = new LinkedList<>();

        private Map<Integer, Node> lru = new HashMap<>();
        private DoublyLinkedList list = new DoublyLinkedList();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (lru.containsKey(key)) {
                // 更新位置
                // list.removeLast();  不一定是尾结点
                // list.addFirst(key);
                // return lru.get(key);

                Node n = lru.get(key);
                list.remove(n);
                list.pushToFirst(n);
                return n.val;
            }
            return -1;
        }
        // 它应该在写入新数据之前删除最久未使用的数据值
        // 需要有序的数据结构 1.双向列表，但不能快速检索
        //                  引入 2.HashMap 解决检索速度问题
        public void put(int key, int value) {
            if (lru.containsKey(key)) {
                // list.removeLast();  remove(key) 不一定是last位置
                // list.addFirst(key);
                // lru.put(key, value);
                list.remove(lru.get(key));
            }
            // 需要插入
            else if (lru.size() >= capacity) {
                // lru.remove(list.getLast());
                // list.removeLast();
                // 删除队尾
                Node node = list.tail;
                lru.remove(node.key);
                list.remove(node);
            }
            // lru.put(key, value);
            // list.addFirst(key);

            Node node = new Node(key, value);
            lru.put(key, node);
            list.pushToFirst(node);
        }
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int k, int v) {key = k; val = v;}
    }

    class DoublyLinkedList {
        Node head;
        Node tail;

        public void pushToFirst(Node n) {
            n.next = head;
            n.prev = null;
            if (head == null) {
                tail = n;
            } else {
                head.prev = n;
            }
            head = n;
        }

        // 通用remove
        // 维护了尾节点，所以可以直接定位到
        public void remove(Node n) {
            if (n == head) {
                head = n.next;
            }
            if (n == tail) {
                tail = n.prev;
                // tail.next = null;  不需要加吗
            }
            if (n.next != null) {
                n.next.prev = n.prev;
            }
            if (n.prev != null) {
                n.prev.next = n.next;
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
