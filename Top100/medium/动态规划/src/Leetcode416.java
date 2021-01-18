import java.util.Arrays;
import java.util.Collections;

public class Leetcode416 {
    /**
     * 没解出来
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int p = 0;
        int q = nums.length - 1;
        int sum = 0;
        while (p < q) {
            if (sum <= 0) {
                sum += nums[p];
                p++;
            } else {
                sum -= nums[q];
                q--;
            }
        }
        if (sum == 0) {
            return true;
        }
        return sum == 0;
    }

    /** 方法：动态规划
     * 时间复杂度：O(n×target)，其中 n是数组的长度，arget 是整个数组的元素和的一半。
     * 需要计算出所有的状态，每个状态在进行转移时的时间复杂度为 O(1)。
     *
     * 空间复杂度：O(target)，其中 target 是整个数组的元素和的一半。
     * 空间复杂度取决于 dp 数组，在不进行空间优化的情况下，空间复杂度是O(n×target)，
     * 在进行空间优化的情况下，空间复杂度可以降到 O(target)。
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4};
        boolean ans = canPartition(test);
        System.out.println(ans);
    }
}
