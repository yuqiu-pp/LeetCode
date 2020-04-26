package com.leet.code.trietree;

class TrieNode {
    char data;
    TrieNode[] children = new TrieNode[26+1];
    boolean isEnd = false;

    TrieNode(char c){
        this.data = c;
    }
}
