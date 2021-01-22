package medium;

import java.util.*;

public class Leetcode36 {
    public static boolean isValidSudoku(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        List<Set<Character>> rowmap = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            rowmap.add(new HashSet<>());
        }
        List<Set<Character>> colmap = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            colmap.add(new HashSet<>());
        }
        List<Set<Character>> square = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            square.add(new HashSet<>());
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col ;j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                if (rowmap.get(i).contains(c)) {
                    return false;
                }
                if (colmap.get(j).contains(c)) {
                    return false;
                }
                int index = (i / 3) * 3 + j / 3;
                if (square.get(index).contains(c)) {
                    return false;
                }
                rowmap.get(i).add(c);
                colmap.get(j).add(c);
                square.get(index).add(c);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] test = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        boolean ans = isValidSudoku(test);
        System.out.println(ans);
    }
}
