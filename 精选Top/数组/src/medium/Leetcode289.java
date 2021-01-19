package medium;

public class Leetcode289 {
    public static void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int[][] temp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) {
                    //左上角
                    if (i > 0 && j > 0) {
                        temp[i - 1][j - 1] += 1;
                    }
                    //正上方
                    if (i > 0) {
                        temp[i - 1][j] += 1;
                    }
                    //右上角
                    if (i > 0 && j < col - 1) {
                        temp[i - 1][j + 1] += 1;
                    }
                    //左边
                    if (j > 0) {
                        temp[i][j - 1] += 1;
                    }
                    //右边
                    if (j < col - 1) {
                        temp[i][j + 1] += 1;
                    }
                    //左下角
                    if (i < row - 1 && j > 0) {
                        temp[i + 1][j - 1] += 1;
                    }
                    //下方
                    if (i < row - 1) {
                        temp[i + 1][j] += 1;
                    }
                    //右下角
                    if (i < row - 1 && j < col - 1) {
                        temp[i + 1][j + 1] += 1;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) {
                    if (temp[i][j] < 2 || temp[i][j] > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (temp[i][j] == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{1}};
        gameOfLife(test);
        System.out.println(1);
    }
}
