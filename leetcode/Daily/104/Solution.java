class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int maxDepth(TreeNode root) {
        if (null == root) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int max = left > right ? left + 1 : right + 1;
        return max;
    }

}
