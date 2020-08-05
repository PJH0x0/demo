class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p != null && q != null){
            if (p.val == q.val) {
                boolean right = 
            }
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            else 
                return false;
        } else {
            return false;
        }
    }

    public boolean dfs(TreeNode p, TreeNode q) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

    }
}
