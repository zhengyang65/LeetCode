public class Leetcode104 {
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
     * 时间复杂度：O(n)，其中 nn 为二叉树节点的个数。每个节点在递归中只被遍历一次。
     * 空间复杂度：O(height)，其中 height表示二叉树的高度。取决于递归的深度，因此空间复杂度等价于二叉树的高度。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return count(root, 0);
     }

    public int count(TreeNode root, int d) {
        if (root == null) {
            return d;
        }
        int d1 = count(root.left, d + 1);
        int d2 = count(root.right, d + 1);
        return Math.max(d1, d2);
    }
}

