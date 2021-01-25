package medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode378 {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] ints = new int[row * col];
        int i = 0;
        for (int[] ints1:matrix) {
            for (int n:ints1) {
                ints[i++] = n;
            }
        }
        Arrays.sort(ints);
        return ints[k-1];
    }

    /**
     * 方法二：归并排序
     * 时间复杂度：O(klogn)，归并 k 次，每次堆中插入和弹出的操作时间复杂度均为 logn。
     * 空间复杂度：O(n)，堆的大小始终为 n。
     * @param matrix
     * @param k
     * @return
     */
    public static int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }


    /**
     * 方法三：二分查找
     * 时间复杂度：O(nlog(r−l))，二分查找进行次数为 O(log(r−l))，每次操作时间复杂度为 O(n)。
     * 空间复杂度：O(1)。
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest3(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }


    public static void main(String[] args) {
        int[][] test = new int[][]{{1,5,9},{10,11,13},{12,13,15}};
        int ans = kthSmallest2(test, 8);
        System.out.println(ans);
    }

}
