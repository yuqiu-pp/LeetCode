package com.leet.code;

import java.util.HashMap;



// 1.HashMap的效率很高，即使数据量大的时候；
// 2.循环判断字符串全是通配符效率不可取，大量数据时耗时太多；
// 3.addWord中判断单词长度为0的语句，在大量数据时耗时较多；
// 4.isEnd要用好；

public class LC211 {
    // root用特殊字符
    TrieNode root = new TrieNode('/');
    // 通配符 单独建一个节点，每增加一个字母，通配的children都增加
    // ！！！ 不能这么设计，因为可能同一个字符，在树中会出现多次，children指向就不唯一了
    // TrieNode docNode = new TrieNode('.');
    // 记录长度
    HashMap<Integer, Integer> hashMap = new HashMap<>();

    /** Initialize your data structure here. */
    // WordDictionary
    public LC211() {

    }

    /** Adds a word into the data structure. */
    // trie树的insert
    public void addWord(String word) {
        // ！！！最后一个用例，数据量大，这个语句会导致超时
        // if (word.length() == 0){
        //     return;
        // }
        // if (!hashMap.containsKey(word.length())){
        //     hashMap.put(word.length(), 1);
        // }

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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        // ！！！把两部分去掉哈希，总的时间还增加了。 说明哈希效率很高
        // if (!hashMap.containsKey(word.length())){
        //     return false;
        // }

        // ！！！挺耗时，增加会超时
        // 全是通配符
        // char[] pattern = word.toCharArray();
        // for (int j = 0; j < word.length(); j++) {
        //     if (pattern[j] != '.') {
        //         break;
        //     }
        //     if (j == word.length() - 1){
        //         return true;
        //     }
        // }
        return find(word, root);
    }

    // 时间复杂度:单词O(n*w)，当单词有通配符时O(26*w)
    public boolean find(String word, TrieNode root){
        char[] pattern = word.toCharArray();
        TrieNode p = root;

        for (int i = 0; i < word.length(); i++) {
            if (pattern[i] == '.'){
                // 用任何一个可能的字符代替'.'，继续匹配查找
                for (int j = 0; j < p.children.length; j++) {
                    if (p.children[j] != null){
                        // if (find(p.children[j].data + word.substring(i+1, word.length()), p)){
                        // 减少一次递归
                        if (find(word.substring(i+1, word.length()), p.children[j])){
                            return true;
                        }
                    }
                }
                return false;
            }else {
                int index = pattern[i] - 'a';
                if (p.children[index] == null){
                    return false;
                }
                p = p.children[index];
            }
        }
        if (!p.isEnd){
            return false;
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
        String[] search = {"a.","ab",".a",".b","ab.",".",".."};
        for (String word : search){
            System.out.println(obj.search(word));
        }

    }
}
