package com.leet.code;


import sun.security.util.Length;

import java.util.*;

/**
 * 字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成
 * 输入:  words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出: "apple"
 */

public class LC720 {

    // root用特殊字符
    TrieNode root = new TrieNode('/');

    // trie树查找
    public boolean findWord(String word){
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        if (!p.isEnd){
            return false;
        }
        return true;
    }


    // 插入
    public void insert(char[] pattern){
        TrieNode p = root;

        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null){
                p.children[index] = new TrieNode(pattern[i]);
            }
            p = p.children[index];
        }
        p.isEnd = true;
    }


    // trie树
    // 数组排序后，从最短的单词开始建trie树，单词去掉一个字母能再树中查询到即合法
    // 记录合法单词中最长的
    public String longestWord2(String[] words) {
        sort(words);
        String rst = "";

        int n = words[0].length();
        int k = 0;
        // 用最短的单词初始化树
        for (int len = words.length; k < len; k++) {
            if (words[k].length() != n){
                break;
            }
            TrieNode p = root;
            for (int j = 0, ln = words[k].length(); j < ln; j++) {
                char c = words[k].charAt(j);
                int index = c - 'a';
                if (p.children[index] == null){
                    p.children[index] = new TrieNode(c);
                }
                p = p.children[index];
            }
            p.isEnd = true;
            if (words[k].length() > rst.length() ||
                    (words[k].length() == rst.length() && rst.compareTo(words[k]) > 0)){
                rst = words[k];
            }
        }

        for (int i = k, len = words.length; i < len; i++) {
            TrieNode p = root;
            int j = 0;
            int ln = words[i].length();
            // 减去末尾字母
            for ( ; j < ln-1; j++) {
                char c = words[i].charAt(j);
                int index = c - 'a';
                if (p.children[index] == null){
                    break;
                }
                p = p.children[index];
            }
            // 所有字母都匹配，插入尾字母
            if (j == ln -1){
                int index = words[i].charAt(j) - 'a';
                p.children[index] = new TrieNode(words[i].charAt(j));

                // 记录单词合法
                if (words[i].length() > rst.length() ||
                        (words[i].length() == rst.length() && rst.compareTo(words[i]) > 0)){
                    rst = words[i];
                }
            }
        }

        return rst;
    }

    // longestWord2 代码合并
    public String longestWord3(String[] words) {
        sort(words);
        String rst = "";

        int n = words[0].length();

        for (int i = 0; i < words.length; i++) {
            TrieNode p = root;
            int j = 0;
            int ln = words[i].length();
            // 用最短的单词直接插入
            if (words[i].length() != n){
                ln -= 1;
            }

            // 减去末尾字母查询
            for ( ; j < ln; j++) {
                char c = words[i].charAt(j);
                int index = c - 'a';
                if (p.children[index] == null){
                    if (words[i].length() != n){
                        break;
                    }
                    p.children[index] = new TrieNode(c);
                }
                p = p.children[index];
            }
            // 所有字母都匹配，插入尾字母
            if (j == ln){
                if (words[i].length() != n){
                    int index = words[i].charAt(j) - 'a';
                    p.children[index] = new TrieNode(words[i].charAt(j));
                }

                // 记录单词合法
                if (words[i].length() > rst.length() ||
                        (words[i].length() == rst.length() && rst.compareTo(words[i]) > 0)){
                    rst = words[i];
                }
            }
        }

        return rst;
    }


    // 按长度排序
    public void sort(String[] words){
        HashMap<Integer, List<String>> hashMap = new HashMap<>(words.length);
        // 去重
        Set<Integer> set = new HashSet<>();

        for (int i = 0, len = words.length; i < len; i++) {
            int ln = words[i].length();
            List list = null;
            if (!hashMap.containsKey(ln)){
                list = new LinkedList();
            }else {
                list = hashMap.get(ln);
            }
            list.add(words[i]);
            hashMap.put(ln, list);
            set.add(ln);
        }

        int[] wordsLen = new int[set.size()];
        int i = 0;
        for (int len : set) {
            wordsLen[i++] = len;
        }
        Arrays.sort(wordsLen);

        i = 0;
        for (int j = 0; j < wordsLen.length; j++) {
            List<String> list = hashMap.get(wordsLen[j]);
            for (String s: list) {
                words[i++] = s;
            }
        }
    }


    // 散列表
    // 单词没减少一个字母后，都能再散列表中查询到
    // 时间复杂度 n + n * m(单词长度)
    public String longestWord1(String[] words) {
        int len = words.length;
        // 建散列表
        HashMap<String, Integer> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            hashMap.put(words[i], i);
        }
        // 查询
        String rst = "";
        // 遍历每个单词
        for (int i = 0; i < len; i++) {
            String s = words[i];
            int ln = words[i].length();
            int j = 0;
            // 每次减少一个字母
            for (j = 1; j < ln; j++) {
                s = s.substring(0, ln-j);
                if (!hashMap.containsKey(s)){
                    break;
                }
            }
            if (j == ln){
                if (rst.length() < words[i].length() ||
                        // 单词长度相同
                        (rst.length() == words[i].length() && rst.compareTo(words[i]) > 0)){
                    rst = words[i];
                }
            }
        }

        return rst;
    }


    public boolean isOk(String word){
        TrieNode p = root;

        // 每个字母对应的isEnd必须为True，才合法
        char[] wd = word.toCharArray();
        for (int i = 0; i < wd.length; i++) {
            int index = wd[i] - 'a';
            if (p.children[index] == null || !p.children[index].isEnd){
                return false;
            }
            p = p.children[index];
        }
        return true;
    }


    /**
     *  执行用时 : 22 ms, 在Longest Word in Dictionary的Java提交中击败了80.91% 的用户
        内存消耗 : 47.6 MB, 在Longest Word in Dictionary的Java提交中击败了56.16% 的用户
     */

    // Trie树
    // 利用isEnd判断单词是否可以由其它单词增加字母得到。
    // 避免了longestWord2中的排序，降低时间复杂度
    public String longestWord(String[] words) {
        if (words.length ==0){
            return "";
        }

        // 单词建树
        for (String wd : words){
            insert(wd.toCharArray());
        }

        String rst = "";
        // 遍历每个单词，在trie中查找
        for (String word : words){
            if (isOk(word)){
                if (rst.length() < word.length() ||
                        (rst.length() == word.length() && rst.compareTo(word) > 0)){
                    rst = word;
                }
            }
        }
        return rst;
    }


    public static void main(String[] args) {
        String[] words = {"ts","e","x","pbhj","opto","xhigy","erikz","pbh","opt","erikzb","eri","erik","xlye","xhig","optoj","optoje","xly","pb","xhi","x","o"};
        // String[] words = {"w","wo","wor","worl","world"};

        LC720 solution = new LC720();
        System.out.println(solution.longestWord(words));
    }

}
