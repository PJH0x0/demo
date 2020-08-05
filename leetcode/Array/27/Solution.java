class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int j = nums.length - 1;
        int i = 0;
        while(i <= j) {
            if (nums[i] == val) {
                while (i < j && nums[j] == val) {
                    j--;
                }
                System.out.println("i = " + i + " j = " + j);
                if (i < j) {
                    nums[i] = nums[j];
                    j--;
                } else {
                    break;
                }
            }
            i++;
        }
        return nums[0] == val ? i : i+1;
    }

    public static void main(String[] args) {

    }
}
