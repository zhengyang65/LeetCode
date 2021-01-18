import java.util.HashMap;
import java.util.Map;

public class Leetcode337 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static int first = -1;
    static int second = -1;
    public static int rob(TreeNode root) {
        midorder(root.left);
        robHelper(root);
        robHelper(root.right);
        midorder(root.right.left);
        midorder(root.right.right);
        return second;
    }
    public static void midorder(TreeNode node) {
        if (node == null) {
            return;
        }
        midorder(node.left);
        robHelper(node);
        midorder(node.right);
    }
    public static void robHelper(TreeNode node) {
        if (first == -1) {
            first = node.val;
            return;
        }
        if (first != - 1 && second == -1) {
            second = Math.max(first, node.val);
            return;
        }
        int temp = second;
        second = Math.max(first + node.val, second);
        first = temp;
    }

    /**
     * 方法一：动态规划
     * 时间复杂度是 O(n)
     * 空间复杂度是 O(n)
     */
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob2(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /**
     * 再优化
     * @param root
     * @return
     */
    public int rob3(TreeNode root) {
        int[] rootStatus = dfs2(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs2(node.left);
        int[] r = dfs2(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right= new TreeNode(3);

        treeNode.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(1);
        int ans = rob(treeNode);
        System.out.println(ans);
    }
}
