import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Leetcode98 {

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

    public static boolean isValidBST(TreeNode root) {
        List<int[]> list = new ArrayList<>();
        return dfs(root, list);
    }
    public static boolean dfs(TreeNode node, List<int[]> list) {
        if (node == null) {
            return true;
        }
        int val = node.val;
        for (int[] ints:list) {
            if (ints[1] == -1) {
                if (ints[0] <= val) {
                    return false;
                }
            } else {
                if (ints[0] >= val) {
                    return false;
                }
            }
        }
        int[] ints = new int[]{node.val, -1};
        list.add(ints);
        if (!dfs(node.left, list)) {
            return false;
        }
        list.remove(list.size() - 1);
        ints = new int[]{node.val, 1};
        list.add(ints);
        if (!dfs(node.right, list)) {
            return false;
        }
        list.remove(list.size() - 1);
        return true;
    }

    /**
     * 方法一递归
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (!helper(node.right, val, upper)) {
            return false;
        }
        if (!helper(node.left, lower, val)) {
            return false;
        }
        return true;
    }

    /**
     * 方法二：中序遍历
     * 二叉搜索树「中序遍历」得到的值构成的序列一定是升序的
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(8);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.left.left = new TreeNode(1);
        treeNode.left.left.right = new TreeNode(5);
        treeNode.left.right= new TreeNode(6);
        treeNode.left.right.right = new TreeNode(7);

        treeNode.right = new TreeNode(10);
        treeNode.right.right = new TreeNode(11);
        boolean ans = isValidBST(treeNode);
        System.out.println(ans);
    }
}
