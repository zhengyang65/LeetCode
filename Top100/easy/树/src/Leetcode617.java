public class Leetcode617 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return merge(t1, t2);
    }
    public static TreeNode merge(TreeNode p, TreeNode q) {
        if (p == null & q == null) {
            return null;
        }
        if (p == null & q != null) {
            return q;
        }
        if (p != null & q == null) {
            return p;
        }

        TreeNode node = new TreeNode(p.val + q.val);
        node.left = merge(p.left, q.left);
        node.right = merge(p.right, q.right);
        return node;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        //root.left.left = new TreeNode(3);
        t1.left.left = new TreeNode(5);
        //root.right.left = new TreeNode(4);
        //t1.right.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);
        //root.left.left = new TreeNode(3);
        t2.left.right = new TreeNode(4);
        //root.right.left = new TreeNode(4);
        t2.right.right = new TreeNode(7);
        mergeTrees(t1, t2);
    }
}
