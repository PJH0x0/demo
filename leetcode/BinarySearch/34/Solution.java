import java.util.Arrays;
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (null == nums || nums.length == 0) return new int[]{-1, -1};
        int lo = 0;
        int hi = nums.length - 1;
        int mid = lo;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (nums[mid] < target) lo = mid + 1;
            else if (nums[mid] > target) hi = mid - 1;
            else {
                int i = mid - 1;
                int j = mid + 1;
                while (i >= 0 && nums[i] == nums[mid]) i--;
                while (j < nums.length && nums[j] == nums[mid]) j++;
                return new int[] {i + 1, j - 1};
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] test1 = new int[] {5,7,7,8,8,8,8,8,10};
        System.out.println("test[1] = " + Arrays.toString(new Solution().searchRange(test1, 8)));
        System.out.println("test[2] = " + Arrays.toString(new Solution().searchRange(test1, 6)));
        int[] test2 = new int[] {2, 2};
        System.out.println("test[3] = " + Arrays.toString(new Solution().searchRange(test2, 3)));
    }
}
