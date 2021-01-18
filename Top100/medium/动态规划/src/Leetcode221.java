public class Leetcode221 {
    /**
     * 1.0不完善版本
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一列有无1
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                res = 1;
                break;
            }
        }

        //第一行有无1
        if (res == 0) {
            for (int i = 0; i < n; i++) {
                if (matrix[0][i] == '1') {
                    res = 1;
                    break;
                }
            }
        }

        //剩余
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i-1][j-1] != '0' && matrix[i][j] == '1') {
                    // char 转 int 需-48
                    int num = matrix[i-1][j-1] - 48;
                    boolean flag = true;
                    //第j列
                    for (int k = i - num; k < i; k++) {
                        if (matrix[k][j] == '0') {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        //第i行
                        for (int l = j - num; l < j; l++) {
                            if (matrix[i][l] == '0') {
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag) {
                        //int 转 char 需 + 48
                        res = Math.max(res, (num + 1) * (num + 1));
                        matrix[i][j] = (char) (num + 49);
                    }
                } else {
                    if (res == 0 && matrix[i][j] == '1') {
                        res = 1;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 2.0通过版本
     * @param matrix
     * @return
     */
    public static int maximalSquare2(char[][] matrix) {
        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一列有无1
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                res = 1;
                break;
            }
        }

        //第一行有无1
        if (res == 0) {
            for (int i = 0; i < n; i++) {
                if (matrix[0][i] == '1') {
                    res = 1;
                    break;
                }
            }
        }

        //剩余
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i-1][j-1] != '0' && matrix[i][j] == '1') {
                    // char 转 int 需-48
                    int num = matrix[i-1][j-1] - 48;
                    int count = 1;
                    for (int k = 1; k <= num; k++) {
                        if (matrix[i][j-k] != '0' && matrix[i-k][j] != '0') {
                            count += 1;
                        } else {
                            break;
                        }
                    }
                    //int 转 char 需 + 48
                    res = Math.max(res, count * count);
                    matrix[i][j] = (char) (count + 48);
                } else {
                    if (res == 0 && matrix[i][j] == '1') {
                        res = 1;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 官方解答
     * 状态转移方程：dp(i,j) = min( dp(i−1,j),dp(i−1,j−1),dp(i,j−1) )+1
     * @param matrix
     * @return
     */
    public int maximalSquare3(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] test = new char[][]{
                {'0','0','0','1'},
                {'1','1','0','1'},
                {'1','1','1','1'},
                {'0','1','1','1'},
                {'0','1','1','1'}};
        int ans = maximalSquare2(test);
        System.out.println(ans);
    }
}
