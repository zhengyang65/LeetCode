public class Leetcode226 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(node);
        return root;
    }

    /**
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)。使用的空间由递归栈的深度决定，它等于当前节点在二叉树中的高度。
     * 在平均情况下，二叉树的高度与节点个数为对数关系，即 O(logN)。
     * 而在最坏情况下，树形成链状，空间复杂度为 O(N)。
     * @param args
     */
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(4);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(7);
        t1.left.left = new TreeNode(1);
        t1.left.right = new TreeNode(3);
        t1.right.left = new TreeNode(6);
        t1.right.right = new TreeNode(9);
        invertTree(t1);
    }

}
