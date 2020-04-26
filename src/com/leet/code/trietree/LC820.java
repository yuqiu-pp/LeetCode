package com.leet.code.trietree;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC820 {
    public static void main(String[] args) {
        String[] words = {"me","time"};
        LC820 solution = new LC820();

        System.out.println(solution.minimumLengthEncoding(words));
    }

    // [1]
    // Input L1: ["time","me","bell"]
    // L2: ["time","bell"]
    public int minimumLengthEncoding(String[] words)  {
        Set<String> s = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                if (s.contains(word.substring(i))){
                    s.remove(word.substring(i));
                }
            }
        }
        int n = 0;
        for (String word : s) {
            n += word.length() + 1;
        }
        return n;
    }


    TrieNode root = new TrieNode('/');
    int num = 0;
    //[2]
    // Trie树，但要建成后缀树
    // 根据题目需求，建树先建字符串长的，这样统计才能正确
    // Arrays.sort到排序的参数 lambda
    public int minimumLengthEncoding0(String[] words)  {
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        // 创建Trie树
        TrieNode root = new TrieNode('/');
        for (String word : words) {
            // 按后缀建树
            insertSuffix(word);
        }
        return num;
    }

    private void insertSuffix(String word){
        TrieNode curr = this.root;
        // 记录已经有的节点数量
        int count = 0;
        boolean isNew = false;
        for (int i = word.length(); i >= 1; i--) {
            char ch = word.charAt(i-1);
            int index = ch - 'a';
            if (curr.children[index] == null){
                curr.children[index] = new TrieNode(ch);
                num ++;
                isNew = true;
            }else {
                count ++;
            }
            curr = curr.children[index];
        }
        if (isNew){
            num += count + 1;
        }
    }

    // 统计叶节点的个数
    private int traversalLeaf(TrieNode node){
        return 0;
    }
}
