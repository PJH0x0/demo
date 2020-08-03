class Solution {
    public boolean isSubsequence(String s, String t) {
        if (t == null || "".equals(t)) return false;
        if (s == null || "".equals(s)) return true;

        int index = 0;
        for(int i = 0; i < s.length(); i++) {
            index = t.indexOf(s.charAt(i));
            if (index != -1) {
                t = t.substring(index + 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String t1 = "ahbgdc";

        System.out.println("test1 = " + new Solution().isSubsequence(s1, t1));

        String s2 = "axc";
        String t2 = "ahbgdc";
        System.out.println("test2 = " + new Solution().isSubsequence(s2, t2));
    }
}
