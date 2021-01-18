public class Leetcode300 {

    public int q = 0;
    public int lengthOfLIS(int[] nums) {
        int res = 1;
        int p = 0;
        q = nums.length - 1;
        while (p < q - 1&& nums[p] > nums[p + 1]) {
            p++;
        }
        int num = nums[p];
        while (p < q && nums[p] < nums[p + 1]) {

        }
        return res;

    }

    /** 方法一：动态规划
     * 时间复杂度：O(n^2)，其中 n为数组 nums 的长度。动态规划的状态数为 n，
     * 计算状态 dp[i]时，需要 O(n)的时间遍历 dp[0…i−1] 的所有状态，所以总时间复杂度为 O(n^2)
     *
     * 空间复杂度：O(n)，需要额外使用长度为 n的 dp数组。
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 方法：贪心 + 二分查找
     * 时间复杂度：O(nlogn)。数组 nums 的长度为 n，我们依次用数组中的元素去更新 d数组，
     * 而更新 d数组时需要进行 O(logn) 的二分搜索，所以总时间复杂度为O(nlogn)。
     *
     * 空间复杂度：O(n)，需要额外使用长度为 n的 d数组
     * @return
     */
    public int lengthOfLIS3(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {   //在d[1] - d[lem] 里面二分查找
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];    //找到第一个比 nums[i] 小的数 d[k]
            }
        }
        return len;
    }



}
