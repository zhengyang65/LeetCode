public class Leetcode48 {
    /**
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < len;i++) {
            for (int j = 0; j < len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] ints = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate(ints);
        System.out.println(1);
    }
}
