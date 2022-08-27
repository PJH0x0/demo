package com.pjh.utils;

import java.util.ArrayList;
import java.util.List;

public class SimpleTokenReader implements TokenReader{

    List<Token> tokens = new ArrayList<>();
    int index = 0;
    @Override
    public Token pop() {
        if (index < 0 || index >= tokens.size()) return null;
        Token token = tokens.get(index);
        index++;
        return token;
    }

    @Override
    public Token peek() {
        if (index < 0 || index >= tokens.size()) return null;
        return tokens.get(index);
    }

    @Override
    public void push(Token token) {
        tokens.add(token);
    }

    @Override
    public int getPos() {
        return index;
    }

    @Override
    public void setPos(int position) {
        index = position;
    }

    @Override
    public void dump() {
        System.out.println(tokens.toString());
    }
}
