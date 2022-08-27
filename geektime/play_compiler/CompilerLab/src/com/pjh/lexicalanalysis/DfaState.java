package com.pjh.lexicalanalysis;

public enum  DfaState {
    Initial,
    Identifier,
    IntLiteral,
    ID_int1,
    ID_int2,
    INT,
    Assignment,
    ADD,
    SUB,
    MULTIPLE,
    DIV,
    GT,
    GE;
}
