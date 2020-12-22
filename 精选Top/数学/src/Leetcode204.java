import java.util.Arrays;

public class Leetcode204 {
    /**
     * 我们考虑这样一个事实：如果 x是质数，那么大于 x的 x的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}
