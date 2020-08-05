import java.util.*;
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int ans = 0;
        int second = 0;
        int third = 0;
        int sum = 0;
        for(int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            third = nums.length - 1;
            second = i + 1;
            while (second != third) {
                sum = nums[second] + nums[third] + nums[i];
                if (sum < target) {
                    if ((target - sum) < min) {
                        min = target - sum;
                        ans = sum;
                    }
                    second++;
                } else if (sum > target) {
                    if ((sum - target) < min) {
                        min = sum - target;
                        ans = sum;
                    }
                    third--;
                } else {
                    return sum;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
