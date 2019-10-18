package com.leet.code.unionfind;

import java.util.HashMap;

public class LC990 {


    /**
     *  执行用时 :9 ms, 在所有 Java 提交中击败了41.46%的用户
        内存消耗 :36.8 MB, 在所有 Java 提交中击败了86.36%的用户
     */
    // union find
    // == 时，建表。建表前先查询，如果已存在则不需要处理。避免生成环；
    // != 时，查找。如果p与q返回结果相同，则返false

    // 查询按单个字母查询，==号两侧字母顺序就没关系了
    //
    // a==b ->  hash<a, b>。如果key=a已存在，则去val作为key继续向后查
    public boolean equationsPossible(String[] equations) {
        HashMap<Character, Character> hashMap = new HashMap<>();
        String[] noEqual = new String[equations.length];
        int noEqualNum = 0;

        for (String str : equations){
            char[] equa = str.toCharArray();
            // 每个字符等于自己
            init (str, hashMap);

            if (equa[0] == equa[3]){
                if (equa[1] == '!'){
                    return false;
                }else {
                    continue;
                }
            }

            if (equa[1] == '='){
                union(equa[0], equa[3], hashMap);
            }
            else{
                noEqual[noEqualNum++] = str;
            }
        }

        // 检查不等式
        for (int i = 0; i < noEqualNum; i++) {
            char[] ch = noEqual[i].toCharArray();
            if (find(ch[0], hashMap) == find(ch[3], hashMap)){
                return false;
            }
        }

        return true;
    }


    public void init(String equa, HashMap<Character, Character> hashMap){
        char[] data = equa.toCharArray();
        char key = data[0];
        if (!hashMap.containsKey(key)){
            hashMap.put(key, key);
        }

        key = data[3];
        if (!hashMap.containsKey(key)){
            hashMap.put(key, key);
        }
    }


    // hash表建立关联
    public void union(char p, char q, HashMap<Character, Character> hashMap){
        // p,q关系如果可以查到，说明已经存在，不需要再处理
        if (find(p, hashMap) == find(q, hashMap)){
            return;
        }

        char key = p;
        char val = hashMap.get(key);
        while (val != key){
            key = hashMap.get(val);
            val = hashMap.get(key);
        }
        hashMap.put(key, q);
    }


    public char find(char ch, HashMap<Character, Character> hashMap){
        char val = ' ';
        char key = ch;
        while (hashMap.containsKey(key)){
            val = hashMap.get(key);
            if (val == key){
                break;
            }
            key = val;
        }
        return val;
    }


    public static void main(String[] args) {
        String[] equations = {"f==a","a==b","f!=e","a==c","b==e","c==f"};

        LC990 solution  = new LC990();
        System.out.println(solution.equationsPossible(equations));


    }
}
