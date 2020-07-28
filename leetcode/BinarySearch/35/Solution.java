class Solution {
    public int searchInsert(int[] nums, int target) {
        if (null == nums || nums.length == 0) return 0;
        int lo = 0;
        int hi = nums.length - 1;
        int mid = lo;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else hi = mid - 1;
            } else if (nums[mid] < target) {
                if ((mid + 1) == nums.length || nums[mid + 1] > target) return mid + 1;
                else lo = mid + 1;
            }
        }
        return (lo + hi) / 2;
    }

    public static void main(String[] args) {
        int[][] test = new int[][] {
            {1,3,5,6},
            {1,3,5,6},
            {1,3,5,6},
            {1,3,5,6},
            {1,3,5,7,9,12,18,20},
            {1,3,5,7,9,12,18,20},
            {1,3,5,7,9,12,18,20},
            {1,3,5,7,9,12,18,20},
            {1,3,5,7,9,12,18,20},
        };
        for(int i = 0; i < test.length; i++) {
            System.out.println("test[" + i + "] = " + new Solution().searchInsert(test[i], 21));
        }

    }
}
