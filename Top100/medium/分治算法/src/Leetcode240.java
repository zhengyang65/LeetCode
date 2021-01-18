public class Leetcode240 {
    /**
     * 思路是对的，但未解出
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        if (row == 0) {
            int i = 0;
            while (i <= col) {
                int mid = (i + col) >> 1;
                int curr = matrix[0][mid];
                if (curr == target) {
                    return true;
                } else if (curr > target) {
                    col = mid - 1;
                } else {
                    i = mid + 1;
                }
            }
        } else if (col == 0) {
            int j = 0;
            while (j <= row) {
                int mid = (j + row) >> 1;
                int curr = matrix[mid][0];
                if (curr == target) {
                    return true;
                } else if (curr > target) {
                    row = mid - 1;
                } else {
                    j = mid + 1;
                }
            }
        }
        return binarySearch(matrix, 0, row, 0, col, target);
    }


    public static boolean binarySearch(int[][] matrix, int r1, int r2, int c1, int c2, int target) {
        if (r1 > r2 || c1 > c2) {
            return false;
        }
        if (target < matrix[r1][c1] || target > matrix[r2][c2]) {
            return false;
        }
        int copyr1 = r1;
        int copyr2 = r2;
        int copyc1 = c1;
        int copyc2 = c2;
        if (r1 == r2 && c1 == c2) {
            if (matrix[r1][c1] == target){
            }
            return true;
        }
        while (r1 < r2 && c1 < c2) {
            int midrow = (r1 + r2) >> 1;
            int midcol = (c1 + c2) >> 1;
            int curr = matrix[midrow][midcol];
            if (curr == target) {
                return true;
            } else if (curr > target) {
                r2 = midrow - 1;
                c2 = midcol - 1;
            } else {
                r1 = midrow + 1;
                c1 = midcol + 1;
            }
        }
        return binarySearch(matrix, r1, copyr2, copyc1, c1 - 1, target)
        || binarySearch(matrix, copyr1, r1 - 1, c1, copyc2, target);
    }

    /**
     * 思路：沿着索引行的矩阵中间列寻找 ，
     * matrix[row-1][mid] < target < matrix[row][mid]
     *
     * 时间复杂度：O(nlgn)(分析比较复杂)
     * 空间复杂度：O(lgn)，虽然这种方法从根本上不需要大于常量的附加内存，
     * 但是它使用递归意味着它将使用与其递归树高度成比例的内存。
     * 因为这种方法丢弃了每一级递归一半的矩阵（并进行了两次递归调用），所以树的高度由 lgn限定。
     */
    class Solution {
        private int[][] matrix;
        private int target;

        private boolean searchRec(int left, int up, int right, int down) {
            // this submatrix has no height or no width.
            if (left > right || up > down) {
                return false;
                // `target` is already larger than the largest element or smaller
                // than the smallest element in this submatrix.
            } else if (target < matrix[up][left] || target > matrix[down][right]) {
                return false;
            }

            int mid = left + (right-left)/2;

            // Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
            int row = up;
            while (row <= down && matrix[row][mid] <= target) {
                if (matrix[row][mid] == target) {
                    return true;
                }
                row++;
            }

            return searchRec(left, row, mid-1, down) || searchRec(mid+1, up, right, row-1);
        }

        public boolean searchMatrix(int[][] mat, int targ) {
            // cache input values in object to avoid passing them unnecessarily
            // to `searchRec`
            matrix = mat;
            target = targ;

            // an empty matrix obviously does not contain `target`
            if (matrix == null || matrix.length == 0) {
                return false;
            }

            return searchRec(0, 0, matrix[0].length-1, matrix.length-1);
        }
    }



    /**
     * 用一个左下角的指针，把左小角当作根节点（妙！！！）
     * 时间复杂度：O(n+m)。行 m，列 n
     * 空间复杂度：O(1)，因为这种方法只处理几个指针，所以它的内存占用是恒定的。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9},
                {10,11,12}};
        boolean ans = searchMatrix(matrix, 3);
        System.out.println(ans);
    }
}
