
public class Solution {
    public boolean isValid(String s) {
        int length = s.length();
        char[] stack = new char[length];
        int sIndex = 0;
        char symbol = '0';
        for (int i = 0; i < length; i++) {
            symbol = s.charAt(i);
            if (symbol == '(' || symbol == '{' || symbol == '[') {
                stack[sIndex++] = symbol;
            } else {
                switch (symbol) {
                    case ')':
                        if (stack[--sIndex] != '(') return false;
                        break;
                    case '}':
                        if (stack[--sIndex] != '{') return false;
                        break;
                    case ']':
                        if (stack[-sIndex] != '[') return false;
                        break;
                    default:
                        return false;
                }
            }
        }
        System.out.println("sIndex = " + sIndex);
        return sIndex == 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("isValid() : " + solution.isValid("()"));
    }
}
