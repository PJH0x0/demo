import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int k = 0;
        Set<List<Integer>> ans = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                k = binarySearch(-(nums[i] + nums[j]), nums);
                if (k != -1 && k != i && k != j) {
                    Integer[] tmp = new Integer[]{nums[i], nums[j], nums[k]};
                    Arrays.sort(tmp);
                    ans.add(Arrays.asList(tmp));
                }
            }
        }
        return new ArrayList<List<Integer>>(ans);
    }

    public int binarySearch(int target, int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid = 0;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (nums[mid] > target) hi = mid - 1;
            else if(nums[mid] < target) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = new int[] {
            -1, 0, 1, 2, -1, -4
        };
        System.out.println("ans = " + new Solution().threeSum(nums));
    }
}
