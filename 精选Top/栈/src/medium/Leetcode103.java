package medium;

import java.util.*;

public class Leetcode103 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Stack<TreeNode> treeNodes = new Stack<>();
        treeNodes.push(root);
        boolean flag = true;
        while (!treeNodes.isEmpty()) {
            List<Integer> curr = new ArrayList<>();
            Stack<TreeNode> currStack = new Stack<>();
            while (flag && !treeNodes.isEmpty()) {
                TreeNode father = treeNodes.pop();
                curr.add(father.val);
                if (father.left != null) {
                    currStack.push(father.left);
                }
                if (father.right != null) {
                    currStack.push(father.right);
                }
            }
            while (!flag && !treeNodes.isEmpty()) {
                TreeNode father = treeNodes.pop();
                curr.add(father.val);
                if (father.right != null) {
                    currStack.push(father.right);
                }
                if (father.left != null) {
                    currStack.push(father.left);
                }
            }
            res.add(curr);
            treeNodes.addAll(currStack);
            currStack.clear();
            flag = !flag;
        }
        return res;
    }

    /**
     * 双端队列，广度优先遍历
     * 时间复杂度：O(N)，其中 N 为二叉树的节点数。每个节点会且仅会被遍历一次。
     * 空间复杂度：O(N)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }



    public static void main(String[] args) {
        TreeNode test = new TreeNode(1);
        test.left = new TreeNode(2);
        test.right = new TreeNode(3);
        test.left.left = new TreeNode(4);
        test.left.right = new TreeNode(5);
        test.right.left = new TreeNode(6);
        test.right.right = new TreeNode(7);

        List<List<Integer>> ans = zigzagLevelOrder(test);
        System.out.println(1);
    }
}
