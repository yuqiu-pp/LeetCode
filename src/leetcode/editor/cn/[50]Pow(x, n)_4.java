package leetcode.editor.cn;
//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
//
// 示例 1: 
//
// 输入: 2.00000, 10
//输出: 1024.00000
// 
//
// 示例 2: 
//
// 输入: 2.10000, 3
//输出: 9.26100
// 
//
// 示例 3: 
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25 
//
// 说明: 
//
// 
// -100.0 < x < 100.0 
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。 
// 
// Related Topics 数学 二分查找


import java.math.BigDecimal;

class LC50 {
    public static void main(String[] args) {
        Solution solution = new LC50().new Solution();
        double x = 2.0;
        int n = -2147483648;   // -2147483648
        System.out.println(solution.myPow(x, n));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // import java.math.BigDecimal;

    // 1.int取值范围：-2147483648~2147483647，[-2^31~2^31-1]，-2147483648~转正数时要注意越界
    // 解决：long m = -n   ;   直接计算 n/2 ，这样肯定不会越界
    // 2. pow 递归出口
    // 1）if(n==0) return 1;  这样在myPow中要处理n==1，return n % 2 == 0 ？ d : x * d
    // 2) if(n==1) return x;  这样在pow中就不用做上述判断了，但myPow中需要判断n==0的情况
    // 3. -n的递归
    // // 递归到最底层，n==1时已经返回1/x, 然后d=1/x，所以n%2==0直接返回d*d即可
    class Solution {
        // 拆分 n/2 的子问题
        public double myPow(double x, int n) {
            //  递归出口
            if (n == 0) {
                return 1;
            }
            // 拆分子问题
            int m = n / 2;
            if (n < 0) {
                x = 1 / x;
                m = -m;  // n = -n, -2147483648 会溢出
            }
            double res = myPow(x, m);
            // 合并子问题
            return n % 2 == 0 ? res * res : res * res * x;
        }

        public double myPow4(double x, int n){
            if (n == 0){
                return 1;
            }
            double d = myPow4(x, n/2);
            d *= d;
            if (n % 2 != 0){
                return n > 0 ? d * x : 1/x * d;
            }
            return d;
        }

        public double myPow40(double x, int n){
            if (n == 0){
                return 1;
            }
            if (n < 0){
                x = 1/x;
            }
            double d = pow40(x, Math.abs(n/2));
            return n % 2 == 0 ? d * d : x * d * d;
        }

        private double pow40(double x, int n) {
            if (n == 1){
                return x;
            }
            double d = pow40(x, n/2);
            return d * d;
        }


        public double myPow00(double x, int n) {
            if (n == 0){
                return 1;
            }
            double d = myPow(x, n/2);
            d = d * d;
            // 递归到最底层，n==1时已经返回1/x, 然后d=1/x，
            if (n % 2 != 0){
                return n > 0 ? x*d : 1/x*d;
            }
            return d;
        }

        public double myPow2(double x, int n) {
            if (n == 0){
                return 1;
            }
            // 大int整数abs后还是负数，加负号也还是负数
            // int取值范围：-2147483648~2147483647，[-2^31~2^31-1]，
            // 所以-2147483648，转换负号要变long
            if (n < 0){
                x = 1/x;
            }
            // n/2一定不会越界
            double d =  pow2(x, Math.abs(n/2));
            d = d * d;
            if (n % 2 != 0){
                return x * d;
            }
            return d;
        }

        public double pow2(double x, int n) {
            if (n == 1){
                return x;
            }
            double d = pow2(x, n/2);
            return d * d;
        }

        // 直接算
        public double myPow3(double x, int n) {
            if (n == 0){
                return 1;
            }
            return 1;
        }

        public double myPow1(double x, int n) {
            // 大int整数abs后还是负数，加负号也还是负数
            // int取值范围：-2147483648~2147483647，[-2^31~2^31-1]，
            // 所以-2147483648，转换负号要变long
            long m = n;
            if (n < 0){
                m = -m;
                x = 1/x;
            }
            return pow1(x, m);
            // return pow1(BigDecimal.valueOf(x), m).doubleValue();
        }

        public double pow1(double x, long n) {
            if (n == 0){
                return 1;
            }
            double d = pow1(x, n/2);
            d = d * d;
            // 偶数
            if (n % 2 != 0){
                d = d * x;
            }
            return d;
        }

        public BigDecimal pow11(BigDecimal x, long n) {
            if (n == 1){
                return x;
            }
            BigDecimal d = pow11(x, n/2);
            d = d.multiply(d);
            // 偶数
            if (n % 2 != 0){
                d = d.multiply(x);
            }
            return d;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}

