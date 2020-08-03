import java.util.*;
class Solution {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        if (n % 3 == 0)  return (int) Math.pow(3, n / 3);
        if (n % 3 == 1) {
            int div = n / 3;
            return (int) Math.pow(3, div - 1) * 4;
        }
        if (n % 3 == 2) return (int) Math.pow(3, n / 3) * 2;
        return 0;
    }

    public static void main(String[] args) {
        for(int i = 2; i <= 58; i++) {
            System.out.println("test[" + i + "] = " + new Solution().integerBreak(i));
        }
    }
}
