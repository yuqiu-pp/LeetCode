package com.leet.code;

public class LC767 {

    // root用特殊字符
    TrieNode root = new TrieNode('/');

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = root;
        char[] pattern = word.toCharArray();

        int index = 0;
        for (int i = 0; i < pattern.length; i++) {
            index = pattern[i] - 'a';
            if (p.children[index] == null){
                p.children[index] = new TrieNode(pattern[i]);
            }
            p = p.children[index];
        }
        p.isEnd = true;
    }


    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String wd : dict){
            insert(wd);
        }
    }


    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        char[] pattern = word.toCharArray();
        TrieNode p = root;

        int index = 0;
        for (int i = 0; i < word.length(); i++){
            index = pattern[i] - 'a';
            // 可能是替换了一个字母， 把他看成统配符，按通配符的思路去做
            // add "hello","hallo"   这种情况 hello也是合法，所以单词完全匹配不能直接返false
            // 不为null时，也要用非自身字符替换后去查
            if (p.children[index] == null){
                // 遍历children非空字母后的树，如果找完全匹配的情况，说明只是替换了一个字母，返true
                for (TrieNode q : p.children){
                    if (q != null){
                        if (find(word.substring(i+1, word.length()), q)){
                            return true;
                        }
                    }
                }
                return false;
            }
            // 每个字符都要执行，时间复杂度O(n*n)
            else
            {
                for (TrieNode q : p.children){
                    if (q != null && q.data != pattern[i]){
                        if (find(word.substring(i+1, word.length()), q)){
                            return true;
                        }
                    }
                }
                p = p.children[index];
            }
        }
        // 单词
        if (!p.isEnd){
            return false;
        }
        // 单词完全匹配
        return false;
    }

    // 单词完全匹配返回true
    public boolean find(String word, TrieNode node){
        TrieNode p = node;

        char[] wd = word.toCharArray();
        int i = 0;
        for (char c : wd){
            i = c - 'a';
            if (p.children[i] == null){
                return false;
            }
            p = p.children[i];
        }
        if (!p.isEnd){
            return false;
        }
        return true;
    }

}
