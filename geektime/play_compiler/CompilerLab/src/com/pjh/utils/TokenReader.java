package com.pjh.utils;

public interface TokenReader {
    Token pop();
    Token peek();
    void push(Token token);
    int getPos();
    void setPos(int postion);
    void dump();
}
