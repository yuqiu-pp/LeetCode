//给出一个无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
// 
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。 
// Related Topics 排序 数组 
// 👍 238 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PL57{
    public static void main(String[] args) {
        Solution solution = new PL57().new Solution();
        // TO TEST
        int[][] intervals = {{1,3},{6,9}};
        int[] newIntervals = {2,5};
        System.out.println(Arrays.toString(solution.insert(intervals, newIntervals)));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;

        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            // 新区间 比 第一个区间的left值还小，直接将新区间插入在第一个位置
            if (interval[0] > right) {
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            }
            // 新区间 比 当前区间的right值还大，所以一定在当前区间后面，当前区间插入
            else if (interval[1] < left) {
                ansList.add(interval);
            }
            // 新区间 和 当前区间有交集，合并成一个更大的区间
            else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        // 遍历完，新区间仍然没有插入，说明它是最大的区间，则将其插入在最后
        if (!placed) {
            ansList.add(new int[] {left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

