public class Leetcode581 {
    public static int findUnsortedSubarray(int[] nums) {
        int res = 0;
        int len = nums.length;
        if (len == 1) {
            return res;
        }
        int p = 0;
        int q = len - 1;
        boolean flag1 = false;
        for (int i = p; i < q; i++) {
            if (nums[i] > nums[i + 1]) {
                p = i;
                flag1 = true;
                break;
            }
        }
        boolean flag2 = false;
        for (int i = q; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                q = i;
                flag2 = true;
                break;
            }
        }

        int min;
        int max;
        if (flag1 || flag2) {
            res += q - p + 1;
            min = nums[p];
            max = nums[p];
            for (int i = p + 1; i <= q; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
        } else {
            return res;
        }

        for (int i = p - 1; i >= 0; i--) {
            if (nums[i] > min) {
                res += 1;
            }
        }
        for (int i = q + 1; i < len; i++) {
            if (nums[i] < max) {
                res += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, 2, 3, 3};
        int ans = findUnsortedSubarray(ints);
        System.out.println(ans);
    }
}
