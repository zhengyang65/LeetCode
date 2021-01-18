public class Leetcode494 {
    public static int res = 0;
    public static int len = 0;
    public static int findTargetSumWays(int[] nums, int S) {
         if (nums == null || nums.length == 0) {
             return res;
         }
         len = nums.length;
         dfs(nums, 0, S, 0);
         return res;

    }
    public static void dfs(int[] nums, int sum, int target, int index) {
        if (index >= len) {
            if (sum == target) {
                res += 1;
            }
            return;
        }
        dfs(nums, sum + nums[index], target, index + 1);
        dfs(nums, sum - nums[index], target, index + 1);
    }

    /**
     * 方法二：动态规划
     * 时间复杂度：O(N∗sum)，其中 N是数组 nums 的长度。
     *
     * 空间复杂度：O(N∗sum)。
     * @param nums
     * @param S
     * @return
     */
    public static int findTargetSumWays2(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }
    public static int findTargetSumWays3(int[] nums, int S) {
        int[][] dp = new int[nums.length][13];
        dp[0][nums[0] + 6] = 1;
        dp[0][-nums[0] + 6] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -6; sum <= 6; sum++) {
                if (dp[i - 1][sum + 6] > 0) {
                    dp[i][sum + nums[i] + 6] += dp[i - 1][sum + 6];
                    dp[i][sum - nums[i] + 6] += dp[i - 1][sum + 6];
                }
            }
        }
        return S > 6 ? 0 : dp[nums.length - 1][S + 6];
    }

    /**
     * 方法三：动态规划+空间优化
     * 方法二中动态规划的状态转移方程中，dp[i][...] 只和 dp[i - 1][...] 有关，
     * 因此我们可以优化动态规划的空间复杂度，只需要使用两个一维数组即可。
     * 时间复杂度：O(N∗sum)，其中 N是数组 nums 的长度。
     * 空间复杂度：O(sum)。
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays4(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 1, 1, 1, 1};
        int ans = findTargetSumWays3(ints, 3);
        System.out.println(ans);
    }
}
