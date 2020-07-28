class Solution {
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        double m = (double) x;
        int k = x;
        while (k > (x / k)) {
            m = m / 2 + (x / m) / 2;
            k = (int) m;
        }
        return k;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 20; i++) {
            System.out.println("test[" + i + "] = " + new Solution().mySqrt(i));
        }
        System.out.println("2147395599 = " + new Solution().mySqrt(2147395599));
    }
}
