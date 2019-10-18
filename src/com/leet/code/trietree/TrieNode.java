package com.leet.code.trietree;

public class TrieNode {
    char data;
    TrieNode[] children = new TrieNode[26+1];
    boolean isEnd = false;

    public TrieNode(char c){
        this.data = c;
    }
}
