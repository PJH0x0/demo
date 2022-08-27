package com.pjh.lexicalanalysis;

import com.pjh.utils.SimpleTokenReader;
import com.pjh.utils.Token;
import com.pjh.utils.TokenReader;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyze {
    TokenReader mTokenReader;
    public TokenReader startExpressionAnalyze(String expression) throws IllegalArgumentException{
        if (null == expression || expression.length() == 0) throw new IllegalArgumentException("expression size is 0");
        mTokenReader = new SimpleTokenReader();
        Token token = new Token();
        DfaState state = DfaState.Initial;
        for (char ch : expression.toCharArray()) {
            switch (state) {
                case Initial:

                    state = initToken(ch, token);
                    break;
                case Identifier:
                    if (isDigit(ch) || isAlpha(ch)) {
                        token.tokenText.append(ch);
                    } else {
                        mTokenReader.push(token);
                        token = new Token();
                        state = initToken(ch, token);
                    }
                    break;
                case GT:
                    if (ch == '=') {
                        state = DfaState.GE;
                        token.tokenText.append(ch);
                        token.type = Token.TokenType.GE;
                    } else {
                        mTokenReader.push(token);
                        token = new Token();
                        state = initToken(ch, token);
                    }
                    break;
                case GE:
                case Assignment:
                case ADD:
                case MULTIPLE:
                case DIV:
                case SUB:
                    mTokenReader.push(token);
                    token = new Token();
                    state = initToken(ch, token);
                    break;
                case IntLiteral:
                    if (isDigit(ch)) {
                        token.tokenText.append(ch);
                    } else {
                        mTokenReader.push(token);
                        token = new Token();
                        state = initToken(ch, token);
                    }
                    break;
                case ID_int1:
                    if (ch == 'n') {
                        token.tokenText.append(ch);
                        state = DfaState.ID_int2;
                    } else if (isDigit(ch) || isAlpha(ch)){
                        token.tokenText.append(ch);
                        state = DfaState.Identifier;
                    } else {
                        mTokenReader.push(token);
                        token = new Token();
                        state = initToken(ch, token);
                    }
                    break;
                case ID_int2:
                    if (ch == 't') {
                        token.tokenText.append(ch);
                        state = DfaState.INT;
                        token.type = Token.TokenType.ID_INT;
                    } else if (isAlpha(ch) || isDigit(ch)) {
                        token.tokenText.append(ch);
                        state = DfaState.Identifier;
                    } else {
                        mTokenReader.push(token);
                        token = new Token();
                        state = initToken(ch, token);
                    }
                    break;
                case INT:
                    if (isDigit(ch) || isDigit(ch)) {
                        token.tokenText.append(ch);
                        state = DfaState.Identifier;
                        token.type = Token.TokenType.ID_INT;
                    } else {
                        mTokenReader.push(token);
                        token = new Token();
                        state = initToken(ch, token);
                    }
                    break;
            }
        }
        mTokenReader.push(token);
        System.out.println(mTokenReader);
        return mTokenReader;
    }
    public DfaState initToken(char ch, Token token) {

        DfaState newState = DfaState.Initial;
        token.tokenText.append(ch);
        if (ch == 'i') {
            newState = DfaState.ID_int1;
            token.type = Token.TokenType.Identifier;
        } else if (isAlpha(ch)) {
            newState =  DfaState.Identifier;
            token.type = Token.TokenType.Identifier;
        } else if (isDigit(ch)) {
            newState = DfaState.IntLiteral;
            token.type = Token.TokenType.IntLiteral;
        } else if (ch == '>') {
            newState = DfaState.GT;
            token.type = Token.TokenType.GT;
        } else if (ch == '=') {
            newState = DfaState.Assignment;
            token.type = Token.TokenType.Assignment;
        } else if (ch == '+') {
            newState = DfaState.ADD;
            token.type = Token.TokenType.ADD;
        } else if (ch == '-'){
            newState = DfaState.SUB;
            token.type = Token.TokenType.SUB;
        } else if (ch == '*') {
            newState = DfaState.MULTIPLE;
            token.type = Token.TokenType.MULTIPLE;
        } else if (ch == '/') {
            newState = DfaState.DIV;
            token.type = Token.TokenType.DIV;
        }
        return newState;
    }
    public boolean isAlpha(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
    public boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
