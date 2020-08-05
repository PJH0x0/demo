class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (null == candidates || candidates.length == 0) return ans;
        int remain = 0;
        int div = 0;
        int can = 0;
        for(int i = 0; i < candidates.length; i++) {
            if (candidates[i] > target) continue;
            else if (candidates[i] == target) {
                ans.add(new ArrayList<Integer>().add(target));
            } else {
                div = target / candidates[i];
                remain = target % candidates[i];
                if (remain == 0) {
                    List<Integer> list = new ArrayList<>();
                    for(int j = 0; j < div; i++) {
                        list.add(candidates[j]);
                    }
                }
            }
        }
    }
}
