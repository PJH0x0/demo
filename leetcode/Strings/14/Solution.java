class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) return "";

        char ch = '0';
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < strs[0].length(); i++) {
            ch = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++) {
                if (strs[j] == "") return "";
                if (i == strs[j].length()) return builder.toString();
                if (ch != strs[j].charAt(i)) return builder.toString();
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[][] test = new String[][] {
            {"flower","flow","flight"},
                {"dog","racecar","car"},
                {"1111", "1122", "1"},
                {"aaaaaaa", "", "aaaaa"}
        };
        for(int i = 0; i < test.length; i++) {
            System.out.println("test[" + i + "] = " + new Solution().longestCommonPrefix(test[i]));
        }
    }
}
