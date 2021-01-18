import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Leetcode43 {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        if (intervals == null || intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        int[] prev = intervals[0];
        list.add(prev);
        for (int[] ints:intervals) {
            int lo = ints[0];
            int hi = ints[1];
            if (lo > prev[1]) {
                list.add(ints);
                prev = ints;
            } else {
                //Math.min(prev[0], lo
                prev = new int[]{prev[0], Math.max(prev[1], hi)};
                list.set(list.size() - 1, prev);
            }
        }
        //return list.toArray(new int[list.size()][]);
        int[][] res = new int[list.size()][];
        for (int i = 0; i<list.size();i++) {
            //res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 也是排序
     * 时间复杂度：O(nlogn)，其中 n为区间的数量。除去排序的开销，
     * 我们只需要一次线性扫描，所以主要的时间开销是排序的 O(nlogn)。
     *
     * 空间复杂度：O(logn)，其中 n为区间的数量。这里计算的是存储答案之外，使用的额外空间。
     * O(logn) 即为排序所需要的空间复杂度
     * @param intervals
     * @return
     */
    public static int[][] merge2(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                //只修改 int[][1]
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


    public static void main(String[] args) {
        int[][] ints = new int[][]{{1,4},{0,0},{8,10},{15,18}};
        int[][] ans = merge2(ints);
        System.out.println(1);
    }
}
