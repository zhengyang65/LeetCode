import java.util.Arrays;

public class Leetcode322 {
    /**
     * 超出时间限制
     */
    static int res = -1;
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {return 0;}
        Arrays.sort(coins);
        if (amount < coins[0]) {return res;}
        for (int i = coins.length - 1; i >= 0; i--) {
            if (res == -1 || (res != - 1 && coins[i] * res > amount)) {
                dfs(coins, amount, 0,0, i);
            }
        }

        return res;
    }
    public static boolean dfs(int[] coins, int amount ,int sum, int count, int d) {
        if (sum > amount || (res != -1 && count >= res)) {
            return false;
        }
        if (sum == amount) {
            if (res == -1) {
                res = count;
            } else {
                res = Math.min(res, count);
            }
            return true;
        }
        if (res == -1 || (res != - 1 && count < res)) {
            int i = d;
            boolean flag = false;
            while (i >= 0 && !flag) {
                flag = dfs(coins, amount, sum + coins[i], count + 1, i);
                i--;
            }
        }
        return false;
    }

    /**
     * 方法二：动态规划-自上而下
     * 假设我们知道 F(S)，即组成金额 S最少的硬币数，最后一枚硬币的面值是 C。
     * 那么由于问题的最优子结构，转移方程应为：
     * F(S) = F(S - C) + 1
     *
     * 时间复杂度：O(Sn)，其中 S是金额，n是面额数。我们一共需要计算 S个状态的答案，
     * 且每个状态 F(S)由于上面的记忆化的措施只计算了一次，而计算一个状态的答案需要枚举 n个面额值，所以一共需要 O(Sn)的时间复杂度。
     * 空间复杂度：O(S)，我们需要额外开一个长为 S的数组来存储计算出来的答案 F(S)。
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange2(coins, amount, new int[amount]);
    }

    private static int coinChange2(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange2(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res; //
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /**
     * 方法三：动态规划：自下而上
     * F(i)= min (F(i - cj)) + 1
     * 其中：j=0…n−1
     * 时间复杂度：O(Sn)，其中 S是金额，n是面额数。
     * 空间复杂度：O(S)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] coins = new int[]{1,2,3};
        int amount = 7;
        int ans = coinChange2(coins, amount);
        System.out.println(ans);
        long end = System.currentTimeMillis();
        System.out.println("共耗时"+(end-start)+"毫秒");
    }
}
