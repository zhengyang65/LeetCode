import java.util.HashSet;
import java.util.Set;

public class Leetcode200 {

    static int[] visited;
    static int col;
    static int size;
    public static int numIslands2(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        col = grid[0].length;
        size = row * col;
        visited = new int[size];
        int res = 0;
        for (int i = 0; i < size; i++) {
            if (visited[i] == 0) {
                if (grid[i / col][i % col] == '1') {
                    res += 1;
                    dfs(i, grid);
                } else {
                    visited[i] = 1;
                }
            }
        }
        return res;
    }

    public static void dfs(int n, char[][] grid) {
        if (n < 0 || n >= size) {
            return;
        }
        if (visited[n] == 1) {
            return;
        }
        visited[n] = 1;
        if (grid[n / col][n % col] == '0') {
            return;
        }
        dfs(n - col, grid);
        dfs(n - 1, grid);
        dfs(n + 1, grid);
        dfs(n + col, grid);
    }

    /**
     * 方法一：深度优先搜索
     * 时间复杂度：O(MN)，其中 M和 N分别为行数和列数。
     * 空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到MN
     * @param grid
     * @param r
     * @param c
     */
    public void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }

    /**
     * 方法二：并查集
     * 时间复杂度：O(MN∗α(MN))，其中 M和 N分别为行数和列数。
     * 注意当使用路径压缩（见 find 函数）和按秩合并（见数组 rank）实现并查集时，
     * 单次操作的时间复杂度为 α(MN)，其中α(x) 为反阿克曼函数，
     * 当自变量 xx 的值在人类可观测的范围内（宇宙中粒子的数量）时，
     * 函数α(x) 的值不会超过 5，因此也可以看成是常数时间复杂度。
     *
     * 空间复杂度：O(MN)，这是并查集需要使用的空间。
     */
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands4(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }


    public static void main(String[] args) {
        char[][] chars = new char[][]{
                {'1','0','0','1','1','1','0','1','1','0','0','0','0','0','0','0','0','0','0','0'},
                {'1','0','0','1','1','0','0','1','0','0','0','1','0','1','0','1','0','0','1','0'},
                {'0','0','0','1','1','1','1','0','1','0','1','1','0','0','0','0','1','0','1','0'},
                {'1','0','0','1','1','0','0','1','0','0','0','1','0','1','0','1','0','0','1','0'}};
        int ans = numIslands2(chars);
        System.out.println(ans);
    }
}
