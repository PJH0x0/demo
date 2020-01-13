package pers.algs._1._3;

import edu.princeton.cs.algs4.StdOut;

public class Practice {
    
    public static String nine(String expression) {
        if (null == expression || expression.isEmpty()) return "";
        Stack<String> numStack = new Stack<String>();
        Stack<String> symbolStack = new Stack<String>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch >= '0' && ch <= '9') numStack.push(String.valueOf(ch));
            else if (ch != ')') symbolStack.push(String.valueOf(ch));
            else {
                String num1 = numStack.pop();
                String num2 = numStack.pop();
                String symbol = symbolStack.pop();
                numStack.push("(" + num2 + symbol + num1 + ")");
            }
        }
        return numStack.pop();
    }
    public static void main(String[] args) {
        String expr = nine("1+2)*3-4)*5-6)))");
        StdOut.println("opt expr >> " + expr);
    }
}
