class Solution {
    public int findMagicIndex(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) return i;
            if (nums[i] > i) i = nums[i];
            else i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] test = new int[][] {
            {0, 2, 3, 4, 5},
                {1, 1, 1},
                {1, 1, 2, 3, 5},
                {2, 2, 2, 2, 2},
                {5, 5, 5, 5, 5, 5}
        };
        for(int i = 0; i < test.length; i++)
            System.out.println("test [" + i + "] = " + new Solution().findMagicIndex(test[i]));
    }
}
