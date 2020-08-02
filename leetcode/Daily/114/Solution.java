class Solution {
    public void flatten(TreeNode root) {
        if (null == root) return;
        TreeNode cur = root;
        TreeNode pre = null;
        while (null != cur) {
            if (null == cur.left) {
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                System.out.println("pre.value" + pre.val);
                pre.right = cur.right;
                cur.right = pre;
                cur.left = null;
                cur = cur.right;
            }
        }
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
}
