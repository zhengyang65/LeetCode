public class Leetcode26 {
    public static int removeDuplicates(int[] nums) {
        if (nums == null | nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return 1;
        }
        int res = 1;
        int p = 0;
        int q = 1;
        while (q <= len - 1 & p <= q) {
            if (nums[p] == nums[q]) {
                q++;
            } else {
                p++;
                nums[p] = nums[q];
                q++;
                res += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{}));
    }
}
