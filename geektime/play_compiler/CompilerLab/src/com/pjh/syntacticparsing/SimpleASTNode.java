package com.pjh.syntacticparsing;

import java.util.ArrayList;
import java.util.List;

public class SimpleASTNode {
    enum ASTNodeType {
        IntDeclaration,
        IntLiteral,
        Identifier,
        Add_Sub,
        Multiple_Div,

    }
    ASTNodeType type;
    List<SimpleASTNode> childs = new ArrayList<>();
    String nodeText;
    public SimpleASTNode(ASTNodeType type, String nodeText) {
        this.nodeText = nodeText;
        this.type = type;
    }

    public ASTNodeType getType() {
        return type;
    }

    public String getNodeText() {
        return nodeText;
    }

    public List<SimpleASTNode> getChilds() {
        return childs;
    }

    public void addChild(SimpleASTNode node) {
        childs.add(node);
    }

    public void dumpAST(String indent) {
        System.out.println(indent + getType() + " " + getNodeText());
        for (SimpleASTNode child : getChilds()) {
            child.dumpAST(indent + "\t");
        }
    }

    @Override
    public String toString() {
        return type + " : " + nodeText + " childs -> \n"+ childs;
    }
}
