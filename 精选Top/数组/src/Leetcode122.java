public class Leetcode122 {
    public static int maxProfit(int[] prices) {
        int res = 0;
        if (prices.length == 0 | prices.length == 1) {
            return res;
        }
        int buy = prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            int curr = prices[i] - buy;
            if (curr < 0) {
                res += sell;
                sell = 0;
            } else {
                sell += curr;
            }
            buy = prices[i];
        }
        if (sell > 0) {
            return res + sell;
        }
        return res;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(ints));
    }
}
