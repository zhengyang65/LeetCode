public class Leetcode101 {
     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

     public static boolean isSymmetric(TreeNode root) {
         if (root == null) {
             return true;
         }
         return isSymmetricHelper(root.left, root.right);
     }

    public static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null & right == null) {
            return true;
        }
        if (left == null | right == null) {
            return false;
        }
        // 可以再简化
        // return left.val == right.val && isSymmerticHelper(left.left, right.right)
        // && isSymmerticHelper(left.right, right.left);
        if (left.val == right.val) {
            if (isSymmetricHelper(left.left, right.right)
                    & isSymmetricHelper(left.right, right.left)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        //root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        //root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(isSymmetric(root));

    }
}
