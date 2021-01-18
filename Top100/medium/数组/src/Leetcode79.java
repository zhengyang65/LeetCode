import edu.princeton.cs.algs4.SET;

import java.util.*;

public class Leetcode79 {
    public boolean exist(char[][] board, String word) {
        Map<Character, Set<int[]>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (map.containsKey(board[i][j])) {
                    Set<int[]> set = map.get(board[i][j]);
                    set.add(new int[]{i, j});
                    map.put(board[i][j], set);
                } else {
                    Set<int[]> set = new HashSet<>();
                    set.add(new int[]{i, j});
                    map.put(board[i][j], set);
                }
            }
        }

        for (int i = 0;i < word.length();i++) {
            //Todo
        }
        return false;
    }

    /**
     * 时间复杂度：一个非常宽松的上界为 O(MN * 3^L), L 为字符串word 的长度。
     * 在每次调用函数check 时，除了第一次可以进入4个分支以外，其余时间我们最多会进入3个分支
     *（因为每个位置只能使用一次，所以走过来的分支没法走回去）。
     * 由于单词长为 L，故 check(i, j, 0)的时间复杂度为 O(3^L).
     * 而我们要执行 O(MN)次检查。然而，由于剪枝的存在，我们在遇到不匹配或已访问的字符时会提前退出，终止递归流程。
     * 因此，实际的时间复杂度会远远小于 Theta(MN * 3^L)
     *
     * 空间复杂度：O(MN)。我们额外开辟了 O(MN)的 visited数组，同时栈的深度最大为O(min(L,MN))。
     * @param board
     * @param word
     * @return
     */
    public boolean exist2(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

}
