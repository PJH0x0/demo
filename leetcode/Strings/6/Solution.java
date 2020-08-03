public class Solution {
    public String convert(String s, int numRows) {
        if (1 == numRows) return s;
        if (null == s || "".equals(s)) return s;
        int interval = 2 * (numRows - 1);
        int num = s.length() / interval;
        int remain = s.length() % interval;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < (remain > i ? num + 1 : num); j++) {
                builder.append(s.charAt(i + interval * j));
                int next = interval * (j + 1) - i;
                if (i != 0 && i != numRows -1 && next < s.length()) {
                    builder.append(s.charAt(next));
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] test = new String[] {
            "PAYPALISHIRING",
        }; 
        System.out.println("test 3 = \n" + (new Solution().convert(test[0], 3)));
        System.out.println("test 4 = \n" + (new Solution().convert(test[0], 4)));
    }
}
