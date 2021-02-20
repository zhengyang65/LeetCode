package medium;

import java.util.Arrays;

public class Leetcode324 {
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int p = len - 1;
        int q = (len - 1) / 2;
        int middle = q;
        int[] res = new int[len];
        int i = 0;
        while (p > middle) {
            res[i++] = nums[q--];
            res[i++] = nums[p--];
        }
        if (q == 0) {
            res[len - 1] = nums[0];
        }
        System.arraycopy(res, 0, nums, 0, len);
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,3,2,1,1};
        wiggleSort(test);
        System.out.println(1);
    }
}
