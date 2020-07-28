class Solution {
    public double myPow(double x, int n) {
        boolean neg = n < 0 ? true : false;
        n = n < 0 ? -n : n;
        for(int i = 1; i < n / 2; i *= 2) {
            System.out.println("x = " + x);
            x = x * x;

        }
        if (neg) x = 1 / x;
        return x;
    }
    public static void main(String[] args) {
        double x1 = 2.0;
        int n1 = 10;
        System.out.println("n1 = " + n1);
        System.out.println("test 1 = " + new Solution().myPow(x1, n1));
    }
}
