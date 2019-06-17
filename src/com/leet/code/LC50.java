package com.leet.code;

public class LC50 {

    // 实现 pow(x, n) ，即计算 x 的 n 次幂函数
    // 将n二等分递归的去计算，如果为奇数，多乘一次x

    // 不能采用：先算正数n的值后，再1/rst的方式
    // 当n超级大时，rst已经变成0了。应该是因为溢出
    // 所以用最基本的算法计算: n>0就是 1/x的连乘

    public double myPow(double x, int n) {
        if (n == 0){
            return 1;
        }
        
        // double rst = pow(x, Math.abs(n));
        // if (n < 0){
        //     rst = 1 / rst;
        // }

        double rst = pow(x, n);

        return rst;
    }


    public double pow(double x, int n) {
        if (n == 1){
            return x;
        }
        // 指数是负数返回 1/x，保证每次都是1/x相乘
        // 递归要避免出现 x * 1/x的情况
        if (n == -1){
            return 1 / x;
        }

        double d = pow(x, n/2);
        d = d * d;
        // 偶数
        if (n % 2 != 0){
            if (n > 0){
                d = d * x;
            }else {
                d = d * 1/x;
            }

        }
        return d;
    }


    public static void main(String[] args) {
        LC50 solution = new LC50();
        System.out.println(solution.myPow(2.00000, -2147483648));
    }

}
