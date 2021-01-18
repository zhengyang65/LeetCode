import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Leetcode94 {

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

    /**
     * 方法一：递归
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。
     * 空间复杂度：O(n)。空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。

     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversalHelper(res,root);
        return res;
    }
    public static void inorderTraversalHelper(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversalHelper(list, node.left);
        list.add(node.val);
        inorderTraversalHelper(list,node.right);
    }

}
