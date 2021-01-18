public class Leetcode538 {
    public static class TreeNode {
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

    public static TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        int count = 0;
        dfs(root, count);
        return root;
    }
    public static int dfs(TreeNode node, int d) {
        if (node == null) {
            return d;
        }
        node.val += dfs(node.right, d);
        return dfs(node.left, node.val);
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

        TreeNode ans = convertBST(treeNode);
        System.out.println(1);
    }
}
