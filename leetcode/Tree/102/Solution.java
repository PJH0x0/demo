import java.util.*;
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>();
        List<TreeNode> cur = new ArrayList<>();
        cur.add(root);
        List<Integer> elementList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();
        System.out.println("first cur = " + cur);
        while (!cur.isEmpty()) {
            for (TreeNode node : cur) {
                elementList.add(node.val);
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            cur = nextLevel;
            nextLevel = new ArrayList<>();
            result.add(elementList);
            elementList = new ArrayList<>();
        }
        return result;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {

    }
}
