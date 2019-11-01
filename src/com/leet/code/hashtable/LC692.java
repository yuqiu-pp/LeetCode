package com.leet.code.hashtable;


import java.util.*;

public class LC692 {


    public static void main(String[] args) {
        String[] words= {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        LC692 solution = new LC692();

        System.out.println(solution.topKFrequent(words, k));
    }


    // 用hash表记录每个单词出现次数，遍历hash表找前k个次数最多的单词
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>(words.length);

        for (String wd: words) {
            if (map.containsKey(wd)){
                map.put(wd, map.get(wd)+1);
            }else {
                map.put(wd, 1);
            }
        }
        // 以val作为key建立新从hash表
        HashMap<Integer, Set<String>> mapVal = new HashMap<>(words.length);
        int[] vals = new int[words.length];
        int i = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            // set去重
            Set<String> st = new HashSet<>();
            vals[i] = entry.getValue();
            if (mapVal.containsKey(vals[i])){
                st = mapVal.get(vals[i]);
                st.add(entry.getKey());
            }else {
                st.add(entry.getKey());
                mapVal.put(vals[i], st);
            }
            i ++;
        }

        List<String> lt = new ArrayList<>();
        // 找出前K个最大值
        Arrays.sort(vals);
        // for (int j = 0; j < k; j++) {
        //     Set<String> st = new HashSet<>();
        //     st = mapVal.get(vals[j]);
        //     if (st.size() > 1){
        //
        //     }else {
        //         st.add(st.)
        //     }
        // }

        return lt;
    }
}
