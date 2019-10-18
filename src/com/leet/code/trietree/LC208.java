package com.leet.code.trietree;

public class LC208 {

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

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] pattern = word.toCharArray();
        TrieNode p = root;

        int index = 0;
        for (char c : pattern){
            index = c - 'a';
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

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] pattern = prefix.toCharArray();
        TrieNode p = root;

        int index = 0;
        for (char c : pattern){
            index = c - 'a';
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        return true;
    }


    public static void main(String[] args) {
        LC208 obj = new LC208();

        String[] words = {"apple"};
        for (String word : words){
            obj.insert(word);
        }

        System.out.println(obj.search("apple"));
        System.out.println(obj.search("app"));
        System.out.println(obj.startsWith("app"));
    }
}
