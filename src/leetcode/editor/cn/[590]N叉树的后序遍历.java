//给定一个 N 叉树，返回其节点值的后序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其后序遍历: [5,6,3,2,4,1]. 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LC590{
    public static void main(String[] args) {
        Solution solution = new LC590().new Solution();
        // TO TEST
        // System.out.println(solution.postorder());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public List<Integer> postorder(Node root) {
            List<Integer> list = new ArrayList<>();
            bfs(root, list);
            return list;
        }

        public void bfs(Node node, List<Integer> list) {
            if (node == null) {
                return;
            }
            for (Node nd : node.children) {
                bfs(nd, list);
            }
            list.add(node.val);
        }
    }

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
//leetcode submit region end(Prohibit modification and deletion)

}
