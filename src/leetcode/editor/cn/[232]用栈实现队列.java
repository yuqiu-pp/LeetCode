//请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）： 
//
// 实现 MyQueue 类： 
//
// 
// void push(int x) 将元素 x 推到队列的末尾 
// int pop() 从队列的开头移除并返回元素 
// int peek() 返回队列开头的元素 
// boolean empty() 如果队列为空，返回 true ；否则，返回 false 
// 
//
// 
//
// 说明： 
//
// 
// 你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
// 
// 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。 
// 
//
// 
//
// 进阶： 
//
// 
// 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyQueue", "push", "push", "peek", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 1, 1, false]
//
//解释：
//MyQueue myQueue = new MyQueue();
//myQueue.push(1); // queue is: [1]
//myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//myQueue.peek(); // return 1
//myQueue.pop(); // return 1, queue is [2]
//myQueue.empty(); // return false
// 
//
// 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x <= 9 
// 最多调用 100 次 push、pop、peek 和 empty 
// 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作） 
// 
// Related Topics 栈 设计 
// 👍 304 👎 0

package leetcode.editor.cn;

import java.util.Stack;

class PL232{
    public static void main(String[] args) {
        MyQueue obj = new PL232().new MyQueue();
        // TO TEST
        // System.out.println(solution.ImplementQueueUsingStacks());
        // MyQueue obj = new MyQueue();
        int x = 2;
        obj.push(x);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class MyQueue {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private int front;

    /** Initialize your data structure here. */
    public MyQueue() {

    }
    
    /** Push element x to the back of queue.
     *  元素都保存在s1中，s1栈顶就是队首元素，s2仅仅是用来调整队列顺序
     */
    public void push(int x) {
        if (s1.isEmpty()) {
            front = x;
        }
        // 全部转移到s2，是入队顺序的倒序
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        // 这里是不是可以直接push到s1
        s2.push(x);
        // s2数据导入到s1，是入队的正序
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int res = s1.pop();
        if (!s1.isEmpty()) {
            front = s1.peek();
        }
        return res;
    }
    
    /** Get the front element. */
    public int peek() {
        return front;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}

