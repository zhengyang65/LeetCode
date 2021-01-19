package medium;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class Leetcode54 {
    static List<Integer> res;
    public static List<Integer> spiralOrder(int[][] matrix) {
        res = new ArrayList<>();
        spiralOrderHelper(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
        return res;
    }
    public static void spiralOrderHelper(int[][] matrix, int up, int down, int left, int right) {
        if (up > down || left > right) {
            return;
        }
        if (up == down && left == right) {
            res.add(matrix[up][left]);
            return;
        }
        if (up == down) {
            for (int j = left; j <= right; j++) {
                res.add(matrix[up][j]);
            }
            return;
        }
        if (left == right) {
            for (int i = up; i <= down; i++) {
                res.add(matrix[i][left]);
            }
            return;
        }
        for (int j = left; j < right; j++) {
            res.add(matrix[up][j]);
        }
        for (int i = up; i < down; i++) {
            res.add(matrix[i][right]);
        }
        for (int j = right; j > left; j--) {
            res.add(matrix[down][j]);
        }
        for (int i = down; i > up; i--) {
            res.add(matrix[i][left]);
        }
        spiralOrderHelper(matrix, up + 1, down - 1, left + 1, right - 1);
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> ans = spiralOrder(test);
        System.out.println(1);
    }
}
