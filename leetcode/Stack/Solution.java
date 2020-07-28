public class Solution {
    public boolean isValid(String s) {
        if (null == s || "".equals(s)) return true;
        char[] stack = new char[s.length()];
        int stackPoint = -1;
        char symbol = ' ';
        for(int i = 0; i < s.length(); i++) {
            symbol = s.charAt(i);
            if (symbol == '(' || symbol == '[' || symbol == '{') {
                stack[++stackPoint] = symbol;
            } else if (symbol == ')') {
                if (stackPoint == -1 || stack[stackPoint--] != '(')
                    return false;
            } else if (symbol == ']') {
                if (stackPoint == -1 || stack[stackPoint--] != '[')
                    return false;
            } else if (symbol == '}') {
                if (stackPoint == -1 || stack[stackPoint--] != '{')
                    return false;
            }
        }
        return stackPoint == -1;
    }

    public static void main(String[] args) {
        String[] test = new String[] {
            "()",
                "()[]{}",
                "(]",
                "([)]",
                "{[]}",
                "]",
                ""
        };
        for(int i = 0; i < test.length; i++) {
            System.out.println("test[" + i + "] = " + new Solution().isValid(test[i]));
        }
    }
}
