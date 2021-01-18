import java.util.*;

public class Leetcode236 {

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

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> route1 = new Stack<>();
        Stack<TreeNode> route2 = new Stack<>();
        route(route1, root, p);
        route(route2, root, q);
        int size1 = route1.size();
        int size2 = route2.size();
        int bias = Math.abs(size1 - size2);
        if (size1 < size2) {
            return CommonAncestor(route1, route2, bias);
        }
        return CommonAncestor(route2, route1, bias);
    }

    public static TreeNode CommonAncestor(Stack<TreeNode> r1, Stack<TreeNode> r2, int d) {
        TreeNode n1 = r1.peek();
        TreeNode n2 = r2.peek();
        while (n1 != n2 && d > 0) {
            n2 = r2.pop();
            d--;
        }
        if (d == 0) {
            while (n1 != n2) {
                n1 = r1.pop();
                n2 = r2.pop();
            }
        }
        return n1;
    }
    public static boolean route(Stack<TreeNode> stack, TreeNode root, TreeNode node) {
        if (root == null) {
            return false;
        }
        if (root == node) {
            stack.push(root);
            return true;
        }
        stack.push(root);
        if (route(stack, root.left, node)) {
            return true;
        }
        if (route(stack, root.right, node)) {
            return true;
        }
        stack.pop();
        return false;
    }

    /**
     * 方法一：递归
     * (lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))
     * 其中 lson 和 rson 分别代表 x节点的左孩子和右孩子。
     * flson && frson说明左子树和右子树均包含 p节点或 q节点，如果左子树包含的是 p节点，那么右子树只能包含 q节点，反之亦然，
     * 因为 p节点和 q节点都是不同且唯一的节点，因此如果满足这个判断条件即可说明 xx 就是我们要找的最近公共祖先。
     * 再来看第二条判断条件，这个判断条件即是考虑了 x恰好是 p节点或 q节点且它的左子树或右子树有一个包含了另一个节点的情况，
     * 因此如果满足这个判断条件亦可说明 xx 就是我们要找的最近公共祖先。
     */

    private TreeNode ans;

    public Leetcode236() {
        this.ans = null;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }


    /**
     * 方法三：储存父节点
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
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

        //Stack<TreeNode> stack1 = new Stack<>(){};
        //stack1.push(treeNode);
        //stack1.push(treeNode.right);
        //stack1.push(treeNode.right.left);
        //stack1.push(treeNode.right.left.right);
        //Stack<TreeNode> stack2 = new Stack<>();
        //stack2.push(treeNode);
        //stack2.push(treeNode.right);
        //stack2.push(treeNode.right.right);
        //TreeNode ans = CommonAncestor(stack2, stack1, 1);

        //route(stack, treeNode, treeNode.right.left.right);
        TreeNode ans =lowestCommonAncestor(treeNode, treeNode.right.right,treeNode.left.left.right);
        System.out.println(ans.val);
    }
}
