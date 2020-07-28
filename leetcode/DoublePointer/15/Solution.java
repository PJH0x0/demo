/*
 *Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

 */
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> sum = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length - 1; j++) {
                if (i > 0 && nums[i] == nums[i - 1]) break;
                if (j > (i + 1) && nums[j] == nums[j - 1]) continue;
                int k = binarySearch(nums, - (nums[i] + nums[j]), j + 1);
                if(k > 0) {
                    List<Integer> tmp = Arrays.asList(nums[i], nums[j], nums[k]);
                    sum.add(tmp);
                }
            }
        }
        return sum;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> sum = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1; 
            while(i < j) {
                if (i > (k + 1) && nums[i] == nums[i - 1])  {
                    i++;
                    continue;
                }
                int value = nums[i] + nums[j] + nums[k];
                if (value == 0) {
                    List<Integer> tmp = Arrays.asList(nums[i], nums[j], nums[k]);
                    sum.add(tmp);
                    i++;
                } else if (value < 0){
                    i++;
                } else if (value > 0) {
                    j--;
                }
            }
        }
        return sum;
    }

    public int binarySearch(int[] nums, int key, int start) {
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > key) end = mid - 1;
            else if(nums[mid] < key) start = mid + 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        //List<List<Integer>> sum = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        //List<List<Integer>> sum = solution.threeSum(new int[]{ 0, 0, 0, 0, 0, 0, 0});
        //List<List<Integer>> sum = solution.threeSum2(new int[]{ 0, 0, 0, 0, 0, 0, 0});
        List<List<Integer>> sum = solution.threeSum2(new int[]{-1, 0, 1, 2, -1, -4});
        for(int i = 0; i < sum.size(); i++) {
            System.out.println("Item [" + i + "] = " + sum.get(i));
        }
    }
}
