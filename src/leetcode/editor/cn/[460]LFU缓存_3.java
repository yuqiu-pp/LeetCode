//设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。 
//
// get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。 
//put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平
//局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。 
//
// 一个项目的使用次数就是该项目被插入后对其调用 get 和 put 函数的次数之和。使用次数会在对应项目被移除后置为 0。 
//
// 进阶： 
//你是否可以在 O(1) 时间复杂度内执行两项操作？ 
//
// 示例： 
//
// LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回 1
//cache.put(3, 3);    // 去除 key 2
//cache.get(2);       // 返回 -1 (未找到key 2)
//cache.get(3);       // 返回 3
//cache.put(4, 4);    // 去除 key 1
//cache.get(1);       // 返回 -1 (未找到 key 1)
//cache.get(3);       // 返回 3
//cache.get(4);       // 返回 4 
// Related Topics 设计

package leetcode.editor.cn;

import java.util.*;

class LC460{
    public static void main(String[] args) {
        LFUCache cache = new LC460().new LFUCache(3);
        // TO TEST
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);    // 去除 key 2
        cache.put(4, 4);    // 去除 key 1
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);    // 去除 key 1
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
    }
    // 「先看访问频次，再看访问时间」
    // my: 维护两个数据结构，一个记录HashMap<key, value>，另一个记录访问次数HashMap<key, num>
    //     漏掉了：访问频次可能相同的处理,所以要有一个根据频次索引的结构
    //     1.HashMap<num,LinkList<node>>。链表可以从顺序上知道相同频次时访问的先后次序.双向列表头尾操作O(1)
    //     2.TreeSet平衡二叉树，可以根据构造函数指定的Compartor排序存储。pollFirst返回并删除最小的；remove；add
    //     3.PriorityQueue,也就是小顶堆，最小的访问次数是堆顶。 ？访问先后次序-需要单独的变量记录
    //     4.LinkHashSet,有序集合,代替LinkList。remove(.iterator.next取头结点)；add尾部部；put，如果key一样，只会替换key对应的value

    //leetcode submit region begin(Prohibit modification and deletion)
    // --------------HashMap<num,TreeSet<node>>---------------------
    // 40 ms	48.1 MB
    class LFUCache {
        HashMap<Integer, Node> cache;
        TreeSet<Node> treeSet;
        int capacity;
        int stamp;

        public LFUCache(int capacity) {
            this.cache = new HashMap<>(capacity);
            this.treeSet = new TreeSet<>((o1, o2) -> (o1.freq == o2.freq) ? o1.stamp - o2.stamp : o1.freq - o2.freq);
            this.capacity = capacity;
            this.stamp = 0;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            treeSet.remove(node);
            node.freq ++;
            node.stamp = stamp++;
            treeSet.add(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            // 已存在，不涉及空间变化
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                get(key);
            } else {
                if (cache.size() == capacity) {
                    Node node = treeSet.pollFirst();
                    cache.remove(node.key);
                }
                Node node = new Node(key, value, 1, stamp++);
                cache.put(key, node);
                treeSet.add(node);
            }
        }

        class Node {
            int key;
            int value;
            int freq;
            int stamp;

            Node(int k, int v, int f, int s) {
                this.value = v;
                this.key = k;
                this.freq = f;
                this.stamp = s;
            }
        }
    }

    // --------------HashMap<num,PriorityQueue<node>>----------------------------------
    // 93 ms	47.4 MB
    class LFUCache03 {
        HashMap<Integer, Node> cache;
        PriorityQueue<Node> queue;
        int capacity;
        int stamp = 0;

        public LFUCache03(int capacity) {
            cache = new HashMap<>(capacity);
            if (capacity > 0) {
                queue = new PriorityQueue<>(capacity);
            }
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            // 删除原节点
            queue.remove(node);
            // 更新节点访问次数和时间戳，重新进队
            node.freq += 1;
            node.stamp = stamp++;
            queue.offer(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                // 直接调用get，比重新代码效率高，从118ms减为93ms
                get(key);
            } else {
                // 校验空间
                if (capacity == cache.size()) {
                    Node node = queue.poll();
                    cache.remove(node.key);
                }
                // 插入.  新节点的stamp
                Node node = new Node(key, value, 1, stamp++);
                cache.put(key, node);
                queue.offer(node);
            }
        }

        class Node implements Comparable<Node> {
            int key;
            int value;
            int freq;
            int stamp;

            Node(int k, int v, int f, int s) {
                this.value = v;
                this.key = k;
                this.freq = f;
                this.stamp = s;
            }

            @Override
            public int compareTo(Node o) {
                // 先比较访问次数，相同再比较stamp
                if (this.freq == o.freq) {
                    return this.stamp - o.stamp;
                } else {
                    return this.freq - o.freq;
                }
            }
        }
    }

    // ---------------HashMap<num,LinkHashSet<node>>。---------------------------------------
    // 27 ms	47.9 MB
    class LFUCache02 {
        HashMap<Integer, Node> cache;
        HashMap<Integer, LinkedHashSet<Node>> frequency; // <freq, node>
        int minFreq = 0;
        int capacity = 0;

        public LFUCache02(int capacity) {
            this.cache = new HashMap<>(capacity);
            this.frequency = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)){
                return -1;
            }
            Node node = cache.get(key);
            // 按freq，在原链表中删除结点。根据链表中剩余数量更新minFreq
            delNodeFromFreqLink(node);
            // 新链表中增加结点
            addNodeToFreqLink(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0){
                return;
            }
            // key在cache中存在
            if (cache.containsKey(key)) {
                // 相当于get，只是value要更新
                cache.get(key).value = value;
                get(key);
            } else {
                // 校验容器是否有空间
                if (capacity == cache.size()) {
                    // 清理空间
                    clearCacheCapacity();
                }
                // 插入新node
                addToCache(key, value);
            }
        }

        private void addNodeToFreqLink(Node node) {
            int freq = node.freq;
            if (!frequency.containsKey(freq + 1)) {
                LinkedHashSet<Node> set = new LinkedHashSet<>();
                frequency.put(freq + 1, set);
            }
            node.freq += 1;
            frequency.get(freq + 1).add(node);
        }

        private void delNodeFromFreqLink(Node node) {
            int freq = node.freq;
            LinkedHashSet set = frequency.get(freq);
            set.remove(node);
            if (minFreq == freq && set.size() == 0) {
                minFreq = freq + 1;
            }
        }

        private void clearCacheCapacity() {
            // 找到要删除的node
            Node node = frequency.get(minFreq).iterator().next();
            cache.remove(node.key);
            frequency.get(minFreq).remove(node);
        }

        private void addToCache(int key, int value) {
            Node node = new Node(key, value, 1);
            cache.put(key, node);
            if (!frequency.containsKey(1)) {
                LinkedHashSet<Node> set = new LinkedHashSet<>();
                frequency.put(1, set);
            }
            minFreq = 1;
            frequency.get(1).add(node);
        }

        private void addToCache_node(int key, int value) {
            Node node = new Node(key, value, 1);
            cache.put(key, node);
            LinkedHashSet<Node> set = frequency.get(1);
            if (set == null) {
                set = new LinkedHashSet<>();
            }
            set.add(node);
            minFreq = 1;
        }

        class Node {
            int key;
            int value;
            int freq;

            Node(int k, int v, int f) {
                this.key = k;
                this.value = v;
                this.freq = f;
            }
        }
    }

    // --------------HashMap<num,LinkList<node>>----------------------------------
    // 44 ms	47.5 MB
    class LFUCache01 {
        Map<Integer, Node> cache;
        Map<Integer, LinkedList<Node>> frequency;
        int capacity = 0;
        int minFreq = 0;

        public LFUCache01(int capacity) {
            this.cache = new HashMap<>(capacity);
            this.frequency = new HashMap<>();
            this.capacity = capacity;
            this.minFreq = 0;
        }

        public int get01(int key) {
            if (!cache.containsKey(key)){
                return -1;
            }
            Node node = cache.get(key);
            //  从原freq对应的链表里移除, 并更新min
            delNode(node);
            // 加入新freq对应的链表
            node.freq ++;
            addNodeToHead(node);
            return node.value;
        }

        public void put01(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                delNode(node);
                node.freq ++;
                node.value = value; // 和get的区别
                addNodeToHead(node);
            } else {
                if (capacity == cache.size()) {
                    // 要O(1)定位到删除的freq需要维护minFreq来记录 ? link为空的处理
                    removeNodeFromTail();
                }
                Node node = new Node(key, value, 1);
                minFreq = 1;
                cache.put(key, node);
                addNodeToHead(node);
            }
        }

        // 删除link中的结点, 并更新min
        private void delNode(Node node) {
            LinkedList<Node> linkedList = frequency.get(node.freq);
            linkedList.remove(node);
            if (linkedList.size() == 0) {
                // frequency.remove(node.freq);  这里不用删，再put时就不用new了
                if (minFreq == node.freq) {
                    minFreq += 1;
                }
            }
        }

        // 从Link尾部删除访问次数最低，且访问时间最久
        private void removeNodeFromTail(){
            LinkedList<Node> linkedList = frequency.get(minFreq);
            Node node = linkedList.removeLast();
            if (linkedList.size() == 0) {
                frequency.remove(node.freq);
            }
            cache.remove(node.key);
        }

        // 在Link头插入新访问的结点
        private void addNodeToHead(Node node){
            LinkedList<Node> list = frequency.get(node.freq);
            if (list == null) {
                list = new LinkedList<>();
                frequency.put(node.freq, list);
            }
            list.addFirst(node);

            // ---- 代替上面的语句，但性能下降
            // frequency.computeIfAbsent(node.freq, k -> new LinkedList<>());
        }

        // a class remember frequency and recentness
        class Node {
            int key;
            int value;
            int freq;

            Node(int k, int v, int f){
                this.key = k;
                this.freq =f;
                this.value = v;
            }
        }
    }

    class LFUCache05 {
        // 这里通过counts来中转cache和freq，逻辑上不如node清晰
        HashMap<Integer, Integer> cache;  // k,v
        HashMap<Integer, Integer> counts;  // k,count
        HashMap<Integer, LinkedHashSet<Integer>> freq;  // count, Link<key>
        int capacity = 0;
        int minFreq = 0;

        public LFUCache05(int capacity) {
            this.cache = new HashMap<>(capacity);
            this.counts = new HashMap<>(capacity);
            this.freq = new HashMap<>();
            this.capacity = capacity;
        }

        public int get05(int key) {
            // 不存在返-1
            if (!cache.containsKey(key)) {
                return -1;
            }
            // 更新访问次数：先删除count，在添加到count + 1
            int count = counts.get(key);
            counts.put(key, count + 1);
            freq.get(count).remove(key);
            if (count == minFreq && freq.get(count).size() == 0) {
                // freq.remove(count);
                minFreq++;
            }
            if (!freq.containsKey(count + 1)) {
                freq.put(count + 1, new LinkedHashSet<>());
            }
            freq.get(count + 1).add(key);
            return cache.get(key);
        }

        public void put05(int key, int value) {
            if (capacity <= 0) {
                return;
            }
            if (cache.containsKey(key)) {
                cache.put(key, value);
                get05(key);
            } else {
                // 检查空间
                if (capacity == cache.size()) {
                    // 取LinkHashSet的头结点
                    int k = freq.get(minFreq).iterator().next();
                    freq.get(minFreq).remove(k);
                    cache.remove(k);
                    // counts.remove(k);  重新写cache时，count会更新为1，所以不删除也可以
                }
                // 插入数据
                cache.put(key, value);
                counts.put(key, 1);
                if (!freq.containsKey(1)) {
                    freq.put(1, new LinkedHashSet<>());
                }
                // 插入LinkHashSet的尾部
                freq.get(1).add(key);
                minFreq = 1;
            }
        }
    }

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
