public class Leetcode62 {
    /**
     * 方法一：dfs
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        return dfs(m, n, 1, 1);
    }
    public static int dfs (int m, int n, int i, int j) {
        if (i == m && j == n) {
            return 1;
        }
        int down = 0;
        int right = 0;
        if (i < m) {
            down = dfs(m, n, i + 1, j);
        }
        if (j < n) {
            right = dfs(m, n, i, j + 1);
        }
        return down + right;
    }

    /**
     * 方法二：动态规划
     * 时间复杂度：O(mn)。
     * 空间复杂度：O(mn)，即为存储所有状态需要的空间。注意到 f(i, j)仅与第 i行和第 i-1行的状态有关，
     * 因此我们可以使用滚动数组代替代码中的二维数组，使空间复杂度降低为 O(n)。
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths2(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[] ints = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            ints[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            ints[0] += 1;
            for (int j = 1; j < n - 1; j++) {
                ints[j] += ints[j - 1];
            }
        }
        return ints[n - 2];
    }

    /**
     * 方法三：组合数学
     * 思路：从左上角到右下角的过程中，我们需要移动 m+n−2 次，
     * 其中有 m-1次向下移动，n-1次向右移动。因此路径的总数，就等于从 m+n−2 次移动中选择 m−1 次向下移动的方案数
     *
     * 时间复杂度：O(m)。由于我们交换行列的值并不会对答案产生影响，
     * 因此我们总可以通过交换 m和 n使得m≤n，这样空间复杂度降低至O(min(m,n))。
     * 空间复杂度：O(1)。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths3(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int ans = uniquePaths2(19, 13);
        System.out.println(ans);
    }
}
