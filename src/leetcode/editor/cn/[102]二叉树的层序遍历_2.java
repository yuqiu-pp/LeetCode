//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索

package leetcode.editor.cn;

import java.util.*;

class LC102{
    public static void main(String[] args) {
        Solution solution = new LC102().new Solution();
        // TO TEST
        // System.out.println(solution.levelOrder());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1.DFS
        // 2.BFS
        // offer()来加入元素，使用poll()来获取并移出元素. add()和remove()方法在失败的时候会抛出异常
        // 使用前端而不移出该元素，使用element()或者peek()方法
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> levelOrder(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int n = queue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    TreeNode node = queue.poll();
                    list.add(node.val);
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                }
                res.add(list);
            }
            return res;
        }

        public List<List<Integer>> levelOrder02(TreeNode root) {
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            dfs(root, 0, map);
            // for (List<Integer> list : map.values()) {
            //     res.add(list);
            // }
            res.addAll(map.values());
            return res;
        }
        private void dfs(TreeNode node, int level, HashMap<Integer, List<Integer>> map) {
            if (node == null) {
                return;
            }
            // List<Integer> list = map.getOrDefault(level, new ArrayList<>());
            // list.add(node.val);
            // map.put(level, list);
            // 代替上面代码   省略掉了hashmap
            if (level >= res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            // 进入下一层
            dfs(node.left, level + 1, map);
            dfs(node.right, level + 1, map);

        }

        public List<List<Integer>> levelOrder01(TreeNode root) {
            if (root == null) {
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            bfs(root, queue);
            return res;
        }
        private void bfs(TreeNode node, Queue<TreeNode> queue) {
            while (!queue.isEmpty()) {
                int n = queue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    TreeNode curr = queue.poll();
                    list.add(curr.val);
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                }
                res.add(list);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
