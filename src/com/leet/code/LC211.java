package com.leet.code;

import java.util.HashMap;

public class LC211 {
    // root用特殊字符
    TrieNode root = new TrieNode('/');
    // 通配符 单独建一个节点，每增加一个字母，通配的children都增加
    TrieNode docNode = new TrieNode('.');
    // 记录长度
    HashMap<Integer, Integer> hashMap = new HashMap<>();


    /** Initialize your data structure here. */
    // WordDictionary
    public LC211() {

    }

    /** Adds a word into the data structure. */
    // trie树的insert

    public void addWord(String word) {
        if (word.length() == 0){
            return;
        }
        if (!hashMap.containsKey(word.length())){
            hashMap.put(word.length(), 1);
        }

        TrieNode p = root;
        char[] pattern = word.toCharArray();

        int index = 0;
        for (int i = 0; i < pattern.length; i++) {
            index = pattern[i] - 'a';
            if (p.children[index] == null){
                p.children[index] = new TrieNode(pattern[i]);
                docNode.children[index] = p.children[index];
            }
            p = p.children[index];
        }
        p.isEnd = true;
        p.high = pattern.length;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] pattern = word.toCharArray();
        int docNum = 0;
        int charNum = 0;
        TrieNode p = root;

        if (!hashMap.containsKey(word.length())){
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (pattern[i] == '.'){
                p = docNode;
                docNum ++;
            }else {
                int index = pattern[i] - 'a';
                if (p.children[index] == null){
                    return false;
                }
                p = p.children[index];
                charNum ++;
            }

        }
        if (p != docNode){
            if (docNum + charNum != p.high || !p.isEnd){
                return false;
            }
        }
        // 通配符结尾，还需要判断是否有合适字母可以替换
        else{

        }

        return true;
    }


    public static void main(String[] args) {
        LC211 obj = new LC211();

        // String[] words = {"bad","dad","mad"};
        String[] words = {"at","and","an","add","bat"};
        for (String word : words){
            obj.addWord(word);
        }

        // String[] search = {"pad","bad",".ad","b.."};
        String[] search = {"b.","a.","ab",".a",".b","ab.",".",".."};
        for (String word : search){
            System.out.println(obj.search(word));
        }

    }
}
