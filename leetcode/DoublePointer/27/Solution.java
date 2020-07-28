
public class Solution {
    public int removeElement(int[] nums, int val) {
        if (null == nums || nums.length == 0) return 0;
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[]{2, 0, 1, 1, 2, 2, 3, 0, 4, 2};
        int length = solution.removeElement(array, 2);
        System.out.println("new Array length = " + length);
        for(int i = 0; i < length; i++) {
            System.out.println("nums[" + i + "] = " + array[i]);
        }
    }
}
