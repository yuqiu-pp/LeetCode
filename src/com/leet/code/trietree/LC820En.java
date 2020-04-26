package com.leet.code.trietree;

import java.util.*;

public class LC820En {
    public static void main(String[] args) {
        String[] words = {"me","time"};
        LC820En solution = new LC820En();

        System.out.println(solution.minimumLengthEncoding(words));
    }

    TrieNodeEn root = new TrieNodeEn();
    List<TrieNodeEn> leaves = new ArrayList<>();

    // 可以用HashMap<Character, TrieNode> 来实现 TrieNode[] children = new TrieNode[]
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : set) {
            TrieNodeEn curr = root;
            for (int i = word.length()-1; i >= 0; i--) {
                char a  = word.charAt(i);
                if (!curr.next.containsKey(a)){
                    curr.next.put(a, new TrieNodeEn());
                }
                curr = curr.next.get(a);
            }
            // 记录叶节点，及单词对应的长度
            curr.depth = word.length() + 1;
            leaves.add(curr);
        }

        int num = 0;
        for (TrieNodeEn leaf : leaves) {
            if (leaf.next.isEmpty()){
                num += leaf.depth;
            }
        }
        return num;
    }

}
