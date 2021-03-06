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
        System.out.println(solution.numDecodings("100"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // dp[i]  到每个数字的最多解码数量
        // 方程
        // 如果s[i] = 0，只能和s[i-1]拼起来解码. 如果能拼 dp[i]=dp[i-2]
        // 如果s[i] != 0, 独立解码，不会增加解码数量，d[i] = d[i-1]
        //               且能和s[i-1]拼起来解码，会增加解码数量，此时dp[i]由两部分组成，dp[i-1]+dp[i-2]
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int len = s.length();
            char[] chars = s.toCharArray();
            int[] dp = new int[len];
            dp[0] = chars[0] == '0' ? 0 : 1;
            for (int i = 1; i < len; i++) {
                // if (chars[i] == '0') {
                    // int num = (chars[i - 1] - '0') * 10 + (chars[i] - '0');
                    // if (num >= 10 && num <= 26) {
                    //     dp[i] += i < 2 ? dp[0] : dp[i - 2];
                    // }
                // } else {
                //     dp[i] = dp[i - 1];
                    // int num = (chars[i - 1] - '0') * 10 + (chars[i] - '0');
                    // if (num >= 10 && num <= 26) {
                    //     dp[i] += i < 2 ? dp[0] : dp[i - 2];
                    // }
                // }
                // 代码优化  1.判断是否可以拼起来解码 提取出来
                //          2.if else 合并成 一个if
                if (chars[i] != 0) {
                    dp[i] = dp[i - 1];
                }
                int num = (chars[i - 1] - '0') * 10 + (chars[i] - '0');
                if (num >= 10 && num <= 26) {
                    dp[i] += i < 2 ? dp[0] : dp[i - 2];
                }
            }
            return dp[len - 1];
        }


        // dp[i]  到每个数字的最多解码数量
        // 转移方程  s[i]=0，它不会影响解码数量，dp[i] = dp[i-1]
        //          s[i]不能和s[i-1]组成可编码的两位数，它不会影响解码数量，dp[i] = dp[i-1]
        //              能组成，解码方法变为总数 =  dp[i] + dp[i-2]
        public int numDecodings02(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int[] dp = new int[s.length()];
            char[] chars = s.toCharArray();
            dp[0] = chars[0] == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                if (chars[i] != '0') {
                    dp[i] = dp[i - 1];
                }
                int num = (chars[i - 1] - '0') * 10 + (chars[i] - '0');
                if (num >= 10 && num <= 26) {
                    dp[i] += i == 1 ? 1 :  dp[i - 2];
                }
            }
            return dp[s.length() - 1];
        }


        // 状态 dp[i]: 到每个数字时的编码总数
        // 转移 ：如果[i]!=0，不增加解码数量， dp[i]=dp[i-1]
        //       接下来看[i]和[i-1] 是否可以组合进行解码，如果可以会增加解码数量，dp[i]+=dp[i-2]


        // 与爬楼梯类似，f(n)=f(n-1)+f(n-2)，多了限制条件

        // dp[i]： s[i]结尾的子串解密方法
        // 状态转移：dp[i] = dp[i - 1]   if s[i] != '0'
        // dp[i] += dp[i - 2]           if 10 <= int(s[i - 1..i]) <= 26
        public int numDecodings01(String s) {
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
