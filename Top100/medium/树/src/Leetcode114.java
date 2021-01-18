public class Leetcode114 {
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
    public static void flatten(TreeNode root) {
        //TreeNode right = root.right;
        //root.right = flattenHelper(root.left);
        flattenHelper(root);
    }
    public static TreeNode flattenHelper(TreeNode node) {
        if (node == null) {
            return node;
        }
        if (node.left == null) {
            node.right = flattenHelper(node.right);
            return node;
        }
        TreeNode right = node.right;
        node.right = flattenHelper(node.left);
        node.left = null;
        TreeNode left = node ;
        while (left.right != null) {
            left = left.right;
        }
        left.right = flattenHelper(right);
        left.left = null;
        return node;
    }

    /** 与我方法类似
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)。
     * @param root
     */
    public void flatten2(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}
