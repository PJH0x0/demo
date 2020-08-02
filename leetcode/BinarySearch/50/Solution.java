class Solution {
    public double myPow(double x, int n) {
        if (n == 0 || x == 1) return 1;
        boolean neg = n < 0 ? true : false;
        double ans = 1.0;
        while (n != 0) {
            if (n % 2 != 0) ans *= x;
            //System.out.println(" x = " + x + " n = " + n + " ans = " + ans + " n % 2" + (n % 2));
            x *= x;
            n = n / 2;
        }

        if (neg) ans = 1 / ans;
        return ans;
    }
    public static void main(String[] args) {
        double x1 = 2.0;
        int n1 = -2;
        System.out.println("test 1 = " + new Solution().myPow(x1, n1));
    }
}
