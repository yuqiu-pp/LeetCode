package com.leet.code.array;


import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.HashMap;
import java.util.Map;

public class LC771 {

    // 其它方法：将字符变成字符串，然后利用String的contains功能
    // for (int i = 0; i < S.length(); i++) {
    //     if (J.contains((String.valueOf(S.charAt(i))))) count++;
    // }


    /**
     *  执行用时 :4 ms, 在所有 python 提交中击败了33.65%的用户
        内存消耗 :34.8 MB, 在所有 python 提交中击败了90.83%的用户
     */
    // 先将J建立hash表，然后查询hash表
    // O(J + S)
    // 理论上优于 O(S * J)，但实际执行时间大于方法1
    public int numJewelsInStones(String J, String S) {
        int lenS = S.length();
        int lenJ = J.length();
        int n = 0;
        HashMap<Character, Integer> hashMap = new HashMap();

        // 遍历石头
        for (int i = 0; i < lenS; i++) {
            char c = S.charAt(i);
            if (hashMap.containsKey(c)){
                hashMap.put(c, hashMap.get(c)+1);
            }else {
                hashMap.put(c, 1);
            }
        }
        for (int i = 0; i < lenJ; i++) {
            char c = J.charAt(i);
            if (hashMap.containsKey(c)){
                n += hashMap.get(c);
            }
        }

        return n;
    }

    /**
     *  执行用时 : 1ms, 在所有 python 提交中击败了99%的用户
     */
    // O(S * J)
    public int numJewelsInStones1(String J, String S) {
        int lenS = S.length();
        int lenJ = J.length();
        int n = 0;

        // 遍历石头
        for (int i = 0; i < lenS; i++) {
            char c = S.charAt(i);
            // 石头是否存在与宝石类型中
            for (int j = 0; j < lenJ; j++) {
                if (c == J.charAt(j)){
                    n++;
                }
            }
        }
        return n;
    }

    // 优化：如果i不属于J，将不再J中进行比较，直接跳过
    // 算法复杂度：O(J * S-n), n为所求返回值
    // J = "aA", S = "aAAbbbb"，该方法避免了bbbb的查询操作，但增加的运算也较多
    public int numJewelsInStones2(String J, String S) {
        int lenS = S.length();
        int lenJ = J.length();
        int n = 0;
        HashMap<Character, Integer> hashMap = new HashMap();

        // 遍历石头
        for (int i = 0; i < lenS; i++) {
            char c = S.charAt(i);
            if (hashMap.containsKey(c)){
                continue;
            }
            // 石头是否存在与宝石类型中
            int j = 0;
            for (j = 0; j < lenJ; j++) {
                if (c == J.charAt(j)){
                    n++;
                    break;
                }
            }
            if (j == lenJ){
                hashMap.put(c, 1);
            }
        }
        return n;
    }
}
