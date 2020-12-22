import javax.swing.tree.TreeNode;

public class Leetcode108 {

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

     public static TreeNode sortedArrayToBST(int[] nums) {
         return sortedHelper(0, nums.length - 1, nums);
     }
     public static TreeNode sortedHelper(int lo, int hi, int[] nums) {
         if (lo == hi) {
             return new TreeNode(nums[lo]);
         }
         if (lo > hi) {
             return null;
         }
         int mid = lo + (hi + 1 - lo) / 2;
         TreeNode res = new TreeNode(nums[mid]);
         res.left = sortedHelper(lo, mid - 1, nums);
         res.right = sortedHelper(mid + 1, hi, nums);
         return res;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{-10,-3,0,5,9};
        sortedArrayToBST(ints);
    }
}
