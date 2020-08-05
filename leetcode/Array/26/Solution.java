class Solution {
    public int removeDuplicates(int[] nums) {
        if (null == nums || 0 == nums.length) return 0;
        int newIndex = 1;
        int cur = nums[0];
        for(int i = 1; i < nums.length; i++) {
            while (nums[i] != cur) {
                nums[newIndex++] = nums[i];
                cur = nums[i];
            }
        }
        return newIndex;
    }
}
