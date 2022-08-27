package com.pjh.syntacticparsing;

import com.pjh.utils.Token;
import com.pjh.utils.TokenReader;


import java.util.Deque;

import static com.pjh.syntacticparsing.SimpleASTNode.ASTNodeType.Multiple_Div;

public class SimpleCaculator {

    public SimpleASTNode intDeclare(TokenReader tokens) {
        SimpleASTNode node = null;
        Token token = tokens.peek();
        if (token != null && token.getType() == Token.TokenType.ID_INT) {
            tokens.pop();
            token = tokens.peek();
            if (token != null && token.getType() == Token.TokenType.Identifier) {
                tokens.pop();
                node = new SimpleASTNode(SimpleASTNode.ASTNodeType.IntDeclaration, token.getTokenText());
                token = tokens.peek();
                if (null != token && token.getType() == Token.TokenType.Assignment) {
                    tokens.pop();
                    SimpleASTNode childNode = additive2(tokens);
                    if (null != childNode) {
                        node.addChild(childNode);
                    }
                }
            }
        }
        return node;
    }

    /**
     * 存在左递归的算法，2+3+4的运算顺序错误
     * @param tokens
     * @return
     */
    public SimpleASTNode additive(TokenReader tokens) {
        //additive(tokens);//正常如果是add -> add + mul | mul就是如此，深度优先有一个规则是尽量多的匹配，所以会先匹配add + mul这一语法规则

        SimpleASTNode node = multiplicative(tokens);
        Token token = tokens.peek();
        if (null != node && token != null && (token.getType() == Token.TokenType.ADD || token.getType() == Token.TokenType.SUB)) {
            tokens.pop();
            SimpleASTNode child1 = additive(tokens);
            if (null != child1) {
                SimpleASTNode temp = new SimpleASTNode(SimpleASTNode.ASTNodeType.Add_Sub, token.getTokenText());
                temp.addChild(node);
                temp.addChild(child1);
                node = temp;
            }
        }
        return node;
    }
    public SimpleASTNode additive2(TokenReader tokens) {
        SimpleASTNode child1 = multiplicative(tokens);

        SimpleASTNode node = child1;
        while (true) {
            Token token = tokens.peek();
            if (null != child1 && null != token && (token.getType() == Token.TokenType.ADD || token.getType() == Token.TokenType.SUB)) {
                tokens.pop();
                SimpleASTNode child2 = multiplicative(tokens);
                if (null != child2) {
                    node = new SimpleASTNode(SimpleASTNode.ASTNodeType.Add_Sub, token.getTokenText());
                    node.addChild(child1);
                    node.addChild(child2);
                }
                child1 = node;
            } else {
                break;
            }
        }

        return node;
    }
    public SimpleASTNode multiplicative(TokenReader tokens) {

        SimpleASTNode node = primary(tokens);
        Token token = tokens.peek();
        if (token != null && (token.getType() == Token.TokenType.DIV || token.getType() == Token.TokenType.MULTIPLE)) {
            tokens.pop();
            SimpleASTNode temp = new SimpleASTNode(Multiple_Div, token.getTokenText());
            temp.addChild(node);
            node = temp;
            SimpleASTNode child = multiplicative(tokens);
            if (child != null) node.addChild(child);
        }
        return node;
    }
    public SimpleASTNode primary(TokenReader tokens) {
        SimpleASTNode node = null;
        Token token = tokens.peek();
        if (null == token) return null;
        if (token.getType() == Token.TokenType.IntLiteral) {
            tokens.pop();
            node = new SimpleASTNode(SimpleASTNode.ASTNodeType.IntLiteral, token.getTokenText());
        } else if (token.getType() == Token.TokenType.Identifier) {
            tokens.pop();
            node = new SimpleASTNode(SimpleASTNode.ASTNodeType.Identifier, token.getTokenText());
        }
        return node;
    }

}
