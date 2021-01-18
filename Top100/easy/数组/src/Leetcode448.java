public class Leetcode448 {

    /**时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if (prices.length == 0 | prices.length == 1) {
            return 0;
        }
        int result = 0;
        int curr = 0;
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            curr = prices[i] - buy;
            if (curr > result) {
                result = curr;
            }
            if (prices[i] < buy) {
                buy = prices[i];
            }
        }

        return result;
    }
}
