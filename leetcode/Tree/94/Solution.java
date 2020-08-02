import java.util.*;
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //dfs(result, root);//recusive
         stack = new Stack<>();
        TreeNode cur = root;
        constructStack(stack, root);
        while(!stack.isEmpty()) {
            cur = stack.pop();
            result.add(cur.val);
            constructStack(stack, cur.right);
        }
        return result;
    }

    public void constructStack(Stack<TreeNode> stack, TreeNode cur) {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    public void dfs(List<Integer> result, TreeNode node) {
        if (null == node) return;
        else {
            dfs(result, node.left);
            result.add(node.val);
            dfs(result, node.right);
        }
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
