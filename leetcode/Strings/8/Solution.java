public class Solution {
    public int myAtio(String str) {
        String str2 = str.trim();
        if ("".equals(str2)) {
            return 0;
        }
        char num = str2.charAt(0);
        boolean isPlus = true;
        long result = 0;
        if (num == '-') {
            isPlus = false;
        } else if (num == '+') {
        } else if (num >= '0' && num <= '9') {
            result = num - '0';
        } else {
            return (int) result;
        }
        for (int i = 1; i < str2.length(); i++) {
            num = str2.charAt(i);
            if (num >= '0' && num <= '9') {
                result = result * 10 + (num - '0');
                if (isPlus && result >= Integer.MAX_VALUE) { 
                    return Integer.MAX_VALUE;
                } else if (!isPlus && -result <= Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                if (!isPlus) {
                    result = -result;
                }
                return (int) result;
            }
        }
        if (!isPlus) {
            result = -result;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        String[] test = new String[]{
        "42",
        "   -42",
        "4193 with words",
        "words and 987",
        "-91283472332",
        "-2147483648",
        "-2147483647",
        "-2147483649",
        "2147483648",
        "2147483647",
        "2147483646",
        "    "
        };

        for(int i = 0; i < test.length; i++) {
            System.out.println("result[" + i + "] = " + new Solution().myAtio(test[i]));
        }
    }
}
