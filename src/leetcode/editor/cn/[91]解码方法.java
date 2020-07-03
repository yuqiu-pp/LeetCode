//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划

package leetcode.editor.cn;

class LC91{
    public static void main(String[] args) {
        Solution solution = new LC91().new Solution();
        // TO TEST
        System.out.println(solution.numDecodings("10"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 状态 dp[i]: 到每个数字时的编码总数
        // 转移 ：如果[i]!=0，不增加解码数量， dp[i]=dp[i-1]
        //       接下来看[i]和[i-1] 是否可以组合进行解码，如果可以会增加解码数量，dp[i]+=dp[i-2]


        // 与爬楼梯类似，f(n)=f(n-1)+f(n-2)，多了限制条件

        // dp[i]： s[i]结尾的子串解密方法
        // 状态转移：dp[i] = dp[i - 1]   if s[i] != '0'
        // dp[i] += dp[i - 2]           if 10 <= int(s[i - 1..i]) <= 26
        public int numDecodings(String s) {
            int len = s.length();
            if (len == 0 || s.charAt(0) == '0') {
                return 0;
            }
            char[] chars = s.toCharArray();
            int[] dp = new int[len];
            dp[0] = 1;
            for (int i = 1; i < len; i++) {
                if (chars[i] != '0') {
                    dp[i] = dp[i - 1];
                }
                int num  = 10 * (chars[i - 1] - '0') + (chars[i] - '0');
                if (num >= 10 && num <=26) {
                    if (i == 1) {
                        dp[i] ++;
                    } else {
                        dp[i] += dp[i - 2];
                    }
                }
            }
            return dp[len - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
