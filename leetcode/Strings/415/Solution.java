public class Solution {
    public String addStrings(String num1, String num2) {
        char base = '0';
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int c = 0;
        int temp = 0;
        int max = num1.length() > num2.length() ? num1.length() : num2.length();
        char[] sum = new char[max + 1 ];
        while (i >= 0 || j >= 0) {
            temp = (i>=0 ? (num1.charAt(i--) - base) : 0) + (j >= 0 ? (num2.charAt(j--) - base) : 0) + c;
            c = temp / 10;
            sum[max--] = (char) (base + temp % 10) ;
        }
        if (c != 0) {
            sum[0] = (char) (base + c);
            return new String(sum);
        } else {
            return new String(sum, 1, sum.length - 1);
        }
    }

    public static void main(String[] args) {
        String[] test1 = new String[] {
            "1234",
                "5678",
                "99",
                "999",
                "873131241",
        };
        String[] test2 = new String[] {
            "123456",
                "5678910",
                "1",
                "999",
                "213182932819",
        };
        for(int i = 0; i < test1.length; i++) {
            System.out.println("test[" + i + "] = " + new Solution().addStrings(test1[i], test2[i]));
        }
    }
}
