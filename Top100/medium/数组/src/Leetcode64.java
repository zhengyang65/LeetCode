public class Leetcode64 {
    public static int col;
    public static int row;
    public static int min = Integer.MAX_VALUE;
    public static int minPathSum(int[][] grid) {
        row = grid.length;;
        col = grid[0].length;
        return dfs(grid, 0, 0, 0);
    }
    public static int dfs (int[][] grid, int sum, int i, int j) {
        sum += grid[i][j];
        if (i == row - 1 && j == col - 1) {
            if (sum < min) {
                min = sum;
            }
            return sum;
        }
        int num1 = Integer.MAX_VALUE;
        int num2 = Integer.MAX_VALUE;
        if (i < row - 1 && sum < min) {
            num1 = dfs(grid, sum, i + 1, j);
        }
        if (j < col - 1 && sum < min) {
            num2 = dfs(grid, sum, i, j + 1);
        }
        return Math.min(num1, num2);
    }

    /**
     * 时间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。需要对整个网格遍历一次，计算 dp 的每个元素的值。
     * 空间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。创建一个二维数组 dp，和网格大小相同。
     * 空间复杂度可以优化，例如每次只存储上一行的 dp 值，则可以将空间复杂度优化到 O(n)。
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        int[][] ints = new int[][]{
                {3,8,6,0,5,9,9,6,3,4,0,5,7,3,9,3},
                {0,9,2,5,5,4,9,1,4,6,9,5,6,7,3,2},
                {8,2,2,3,3,3,1,6,9,1,1,6,6,2,1,9},
                {1,3,6,9,9,5,0,3,4,9,1,0,9,6,2,7},
                {8,6,2,2,1,3,0,0,7,2,7,5,4,8,4,8},
                {4,1,9,5,8,9,9,2,0,2,5,1,8,7,0,9},
                {6,2,1,7,8,1,8,5,5,7,0,2,5,7,2,1},
                {8,1,7,6,2,8,1,2,2,6,4,0,5,4,1,3},
                {9,2,1,7,6,1,4,3,8,6,5,5,3,9,7,3},
                {0,6,0,2,4,3,7,6,1,3,8,6,9,0,0,8},
                {4,3,7,2,4,3,6,4,0,3,9,5,3,6,9,3},
                {2,1,8,8,4,5,6,5,8,7,3,7,7,5,8,3},
                {0,7,6,6,1,2,0,3,5,0,8,0,8,7,4,3},
                {0,4,3,4,9,0,1,9,7,7,8,6,4,6,9,5},
                {6,5,1,9,9,2,2,7,4,2,7,2,2,3,7,2},
                {7,1,9,6,1,2,7,0,9,6,6,4,4,5,1,0},
                {3,4,9,2,8,3,1,2,6,9,7,0,2,4,2,0},
                {5,1,8,8,4,6,8,5,2,4,1,6,2,2,9,7}};
        int ans = minPathSum(ints);
        System.out.println(ans);
    }
}
