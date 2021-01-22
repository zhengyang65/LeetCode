package medium;

import java.util.HashMap;
import java.util.Map;

public class Leetcode454 {
    /**
     * 自做，超出时间限制
     */
    static Map<int[], Map<Integer, Integer>> four;
    static int[][] fourints;
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        four = new HashMap<>();
        fourints = new int[][]{D, C, B, A};
        // 初始化A
        Map<Integer, Integer> mapa = new HashMap<>();
        for (int i:A) {
            mapa.put(i, mapa.getOrDefault(i,0) + 1);
        }
        Map<Integer, Integer> mapb = new HashMap<>();
        Map<Integer, Integer> mapc = new HashMap<>();
        Map<Integer, Integer> mapd = new HashMap<>();
        four.put(A, mapa);
        four.put(B, mapb);
        four.put(C, mapc);
        four.put(D, mapd);

        return fourSumCountHelper(0, 0);
    }
    public static int fourSumCountHelper(int target, int d) {
        int[] ints = fourints[d];
        Map<Integer, Integer> curr = four.get(ints);
        if (d == 3 || curr.containsKey(target)) {
            return curr.getOrDefault(target, 0);
        }
        int count = 0;
        for (int i:ints) {
            count += fourSumCountHelper(target - i, d + 1);
        }
        curr.put(target, count);
        return count;
    }

    /**
     * 时间复杂度：O(n^2)。我们使用了两次二重循环，时间复杂度均为 O(n^2)
     * 在循环中对哈希映射进行的修改以及查询操作的期望时间复杂度均为 O(1)，因此总时间复杂度为 O(n^2)。
     *
     * 空间复杂度：O(n^2)，即为哈希映射需要使用的空间。
     * 在最坏的情况下，A[i]+B[j]的值均不相同，因此值的个数为 n^2，也就需要 O(n^2)的空间。
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,2};
        int[] b = new int[]{-2,-1};
        int[] c = new int[]{-1,2};
        int[] d = new int[]{0,2};

        int ans = fourSumCount(a,b,c,d);
        System.out.println(ans);
    }
}
