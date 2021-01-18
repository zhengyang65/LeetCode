public class Leetcode309 {
    public int maxProfit(int[] prices) {
        int res = 0;

        return res;
    }

    /** 方法：动态规划
     * 时间复杂度：O(n)，其中 n为数组 prices 的长度。
     * 空间复杂度：O(1)。
     * 注意到第 0天实际上是不存在处于冷冻期的情况的，但我们仍然可以将对应的状态 f2 置为零
     */

    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < n; ++i) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }

        return Math.max(f1, f2);
    }

}
