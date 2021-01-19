package medium;

import java.util.*;

public class Leetcode73 {
    public static void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        Set<Integer> rowset = new HashSet<>();
        Set<Integer> colset = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rowset.add(i);
                    colset.add(j);
                }
            }
        }

        for (int index : rowset) {
            for (int j = 0; j < col; j++) {
                matrix[index][j] = 0;
            }
        }
        for (int index : colset) {
            for (int i = 0; i < row; i++) {
                matrix[i][index] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(test);
        System.out.println(1);
    }
}
