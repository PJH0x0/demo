import java.util.*;
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int[][] numTemp = new int[num2.length()][num1.length()+num2.length()];
        StringBuilder result = new StringBuilder();
        int c = 0;
        int last = numTemp[0].length - 1;
        int n1 = 0;
        int n2 = 0;
        for(int i = num2.length() - 1; i >= 0; i--) {
            n2 = num2.charAt(i) - '0';
            for (int j = num1.length() - 1; j >= 0; j--) {
                n1 = num1.charAt(j) - '0';
                numTemp[num2.length()-i-1][last - (num1.length() - 1 -j)] = (n1 * n2 + c) % 10 ;
                c = (n1 * n2 + c)/ 10;
            }
            numTemp[num2.length()-i-1][last - num1.length()] = c;
            c = 0;
            last--;
        }

        int resultTemp = 0;
        c = 0;
        for(int i = numTemp[0].length - 1; i >= 0; i--) {
            resultTemp = 0;
            for (int j = 0; j < numTemp.length; j++) {
                resultTemp += numTemp[j][i];
            }
            resultTemp += c;
            c = resultTemp / 10;
            if (resultTemp != 0 || i != 0)
                result.append(resultTemp % 10);
        }
        return result.reverse().toString();
    }
    public static void main(String[] args) {
        String[] num1 = new String[] {
            "2",
                "123",
                "789",
                "4290",
                "9",
                "12232131",
                "0",
                "1",

        };
        String[] num2 = new String[] {
            "3",
                "4567",
                "9",
                "1237821378913",
                "99",
                "0",
                "321312",
                "3213213"


        };

        for(int i = 0; i < num1.length; i++) {
            System.out.println("********");
            System.out.println("test[" + i + "] = " + new Solution().multiply(num1[i], num2[i]));
            System.out.println("*******\n");
        }
    }
}
