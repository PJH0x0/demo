class Solution {
    //Not compelete
    int sumOdd = 0;
    int sumEven = 0;
    public int rob(TreeNode root) {
        dfs(root, 0);
        return Math.max(sumOdd, sumEven);
    }
    public void dfs(TreeNode node, int depth) {
        if (null == node) return;
        if (depth % 2 == 0) {
            sumEven += node.val;
        } else {
            sumOdd += node.val;
        }

        dfs(node.left, depth+1);
        dfs(node.right, depth+1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

