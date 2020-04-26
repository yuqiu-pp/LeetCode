package com.leet.code.array;

import java.util.HashMap;
import java.util.Map;

public class LC914_4 {
    public static void main(String[] args) {
        int[] deck = {1,2,3,4,4,3,2,1};
        LC914_4 solution = new LC914_4();

        System.out.println(solution.hasGroupsSizeX4(deck));
    }
    // 最大公约数的计算方法：欧几里得
    // hashmap.getOrDefault的用法

    public boolean hasGroupsSizeX4(int[] deck) {
        int[] stack = new int[deck.length+1];
        for (int i : deck) {
            stack[i] += 1;
        }
        int g = 0;
        for (int i: stack) {
            if (i != 0){   // 加了可以少些gcd的调用
                g = gcd1(i, g);
            }
        }
        return g > 1;
    }
    private int gcd1(int a, int b){
        // 递归时，a和b交换
        return b==0 ? a : gcd1(b, a%b);
        // if (b == 0){
        //     return a;
        // }
        // return gcd1(b, a%b);
    }

    // 最大公约数的计算方法：欧几里得
    // map.getOrDefault的用法
    public boolean hasGroupsSizeX0(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i : deck) {
            map.put(i, map.getOrDefault(i,0) + 1);
        }
        for (Integer i : map.values()) {
            res = gcd(i, res);
        }
        return res > 1;
    }

    // 用数组做hash表
    public boolean hasGroupsSizeX(int[] deck) {
        int[] map = new int[1000];
        for (int i : deck) {
            map[i] += 1;
        }
        int g = 0;
        for (int n : map) {
            g = gcd(g, n);
        }
        return g > 1;
    }



    public boolean hasGroupsSizeX1(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>(deck.length);
        for (int i : deck) {
            if (map.containsKey(i)){
                map.put(i, map.get(i)+1);
            }else {
                map.put(i, 1);
            }
        }
        int g = -1;
        for (Integer n : map.values()) {
            if (g == -1){
                g = n;
            }else {
                g = gcd(g, n);
            }
            if (g == 1){
                return false;
            }
        }
        return true;
    }

    private int gcd(int a, int b){
        if (b == 0){
            return a;
        }
        return gcd(b, a%b);

        // return b > 0 ? gcd(a, a%b) : a;  错误
        // return b > 0 ? gcd(b, a%b) : a;
    }
}
