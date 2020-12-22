public class Leetcode172 {
    public static int trailingZeroes(int n) {
        int res = 0;
        int d = n % 5;
        n = n - d;
        while (n >= 5) {
            int c = n;
            boolean r = true;
            while (r) {
                if (c != 0 & c % 5 ==0) {
                    res += 1;
                    c = c / 5;
                } else {
                    r = false;
                }
            }
            n -= 5;
        }
        return res;
    }

    /**
     * 时间复杂度:O(logn)。在这种方法中，我们将 n除以 5的每个幂。根据定义，5的log5_n小于或等于 n。
     * 由于乘法和除法在 32 位整数范围内，我们将这些计算视为 O(1)。
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        int zeroCount = 0;
        // We need to use long because currentMultiple can potentially become
        // larger than an int.
        long currentMultiple = 5;
        while (n >= currentMultiple) {
            zeroCount += (n / currentMultiple);
            currentMultiple *= 5;
        }
        return zeroCount;
    }

    /**
     *每次将 n本身除以 5。
     * @param n
     * @return
     */
    public int trailingZeroes3(int n) {
        int zeroCount = 0;
        long currentMultiple = 5;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(4));
    }
}
