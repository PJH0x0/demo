public class Solution {
    public boolean isMatch(String s, String p) {
        if ("".equals(s)) return true;
        if ("".equals(p)) return false;
        int i = 0;
        int j = 0;
        char chs = '0';
        char chp = '0';
        while (j < p.length()) {
            chp = p.charAt(j);
            if (i == s.length()) return false;
            if (chp >= 'a' && chp <= 'z') {
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    while (s.charAt(i) == chp) {
                        i++;
                        if (i == s.length()) {
                            break;
                        }
                    }
                    j += 2;
                } else if (chp == s.charAt(i)) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            } else if (chp == '.') {
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    j += 2;
                    if (j >= p.length()) return true;
                    chp = p.charAt(j);
                    System.out.println(".  chp = " + chp);
                    while (s.charAt(i) != chp) {
                        i++;
                        if (i == s.length()) {
                            break;
                        }
                    }
                    System.out.println("i = " + i);
                } else {
                    i++;
                    j++;
                }
            }
        }
        return i == s.length();
    }

    public static void main(String[] args) {
        String[] test = new String[] {
            "aa",
                "aa",
                "ab",
                "aab",
                "mississippi",
                "abababbbbb",
                "ab",
                "ac",

        };
        String[] pattern = new String[] {
            "a",
                "a*",
                ".*",
                "c*a*b",
                "mis*is*p*.",
                "ab*ab*ab*",
                ".*c",
                ".*c",
        };
        for(int i = 0; i < test.length; i++)
            System.out.println("test[" + i + "] = " + (new Solution().isMatch(test[i], pattern[i])));
    }
}
