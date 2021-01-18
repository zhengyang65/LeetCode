import java.util.Deque;
import java.util.LinkedList;

public class Leetcode105 {

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

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode buildTreeHelper(int[] preorder, int p, int q, int[] inorder, int r, int s) {
        if (p == q) {
            return new TreeNode(preorder[p]);
        }
        int val = preorder[p];
        TreeNode node = new TreeNode(val);
        int index = r;
        while (index <= s & inorder[index] != val) {
            index++;
        }
        int bias = index - r;
        if (bias > 0) {
            node.left = buildTreeHelper(preorder, p + 1, p + bias, inorder, r,index - 1);
        }
        if (index < s) {
            node.right = buildTreeHelper(preorder, p + bias + 1, q, inorder, index + 1,s);
        }
        return node;
    }

    /**
     * 迭代，有点难顶
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(8);

        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);
        treeNode.right.left.right = new TreeNode(9);

        int[] ints1 = new int[]{1,2,4,7,8,3,5,9,6};
        int[] ints2 = new int[]{7,4,8,2,1,5,9,3,6};
        TreeNode res = buildTree(ints1, ints2);
        System.out.println(1);
    }
}
