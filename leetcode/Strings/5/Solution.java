class Solution {
    public String longestPalindrome(String s) {
        if (null == s || "".equals(s)) return "";
        String result = "";
        String r1 = "";
        String r2 = "";
        for(int i = 0; i < s.length(); i++) {
            r1 = palindrame(i, i, s);
            r2 = palindrame(i, i+1, s);
            if (result.length() < r1.length()) result = r1;
            if (result.length() < r2.length()) result = r2;
        }
        return result;
    }

    public String palindrame(int i, int j, String s) {
        String p = new String();
        if (i == j) {
            p = String.valueOf(s.charAt(i));
            i--;
            j++;
        }
        while(i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                p = s.charAt(i--) + p + s.charAt(j++);
            } else {
                return p;
            }
        }
        return p;
    }

    public static void main(String[] args) {

    }
}
