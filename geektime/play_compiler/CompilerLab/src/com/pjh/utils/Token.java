package com.pjh.utils;

public class Token {
    public enum TokenType {
        NULL,
        Identifier,
        IntLiteral,
        ID_INT,
        Assignment,
        ADD,
        SUB,
        MULTIPLE,
        DIV,
        GT,
        GE;
    }
    public TokenType type = TokenType.NULL;
    public StringBuilder tokenText = new StringBuilder();

    public TokenType getType() {
        return type;
    }
    public String getTokenText() {
        return tokenText.toString();
    }

    @Override
    public String toString() {
        return type.toString() + " : " + tokenText.toString();
    }
}
