package com.leet.code;

public class TrieNode {
    char data;
    TrieNode[] children = new TrieNode[26+1];
    boolean isEnd = false;
    int high = 0;

    public TrieNode(char c){
        this.data = c;
    }
}
