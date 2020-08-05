class Solution {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = (j - i) * Math.min(height[i], height[j]);
        int minIndex = height[i] < height[j] ? i : j;
        while (true) {
            System.out.println("height[ " + i + "] = " + height[i] + " height[" + j + "] = " + height[j]);
            while (height[i] <= height[minIndex] && i < j) i++;
            while (height[j] <= height[minIndex] && i < j) j--;
            if (i >= j) return max;
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            minIndex = height[i] < height[j] ? i : j;
        }
        //return max;
    }

    public static void main(String[] args) {
        int[] height = new int[] {
            1,8,6,2,5,4,8,3,7
        };
        System.out.println("max = " + new Solution().maxArea(height));
    }
}
