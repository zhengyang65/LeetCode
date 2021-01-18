import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode102 {

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    static List<List<Integer>> res = new ArrayList<>();
    static List<TreeNode> tree = new ArrayList<>();
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return res;
        }
        List<Integer> rootlist = new ArrayList<>();
        rootlist.add(root.val);
        res.add(rootlist);
        tree.add(root);
        while (!tree.isEmpty()) {
            List<Integer> curr = new ArrayList<>();
            int size = tree.size();
            for (int i = 0; i < size; i++) {
                TreeNode prev = tree.remove(0);
                if (prev.left != null) {
                    tree.add(prev.left);
                    curr.add(prev.left.val);
                }
                if (prev.right != null) {
                    tree.add(prev.right);
                    curr.add(prev.right.val);
                }
            }
            if (!curr.isEmpty()) {
                res.add(curr);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(1);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right= new TreeNode(2);
        treeNode.left.right.right = new TreeNode(3);

        treeNode.right = new TreeNode(6);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(7);
        treeNode.right.right.right = new TreeNode(8);

        List<List<Integer>> ans = levelOrder(treeNode);
        for (List<Integer> entry:ans) {
            for (int i:entry) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
