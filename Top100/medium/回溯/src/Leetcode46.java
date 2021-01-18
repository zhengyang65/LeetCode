import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leetcode46 {
    static int[] ints;

    public static List<List<Integer>> permute(int[] nums) {
        int sum = 0;
        int len = nums.length;
        ints = nums;
        List<List<Integer>> res = new ArrayList<>();
        boolean[] bools = new boolean[len];
        res = permutehelper(res, bools, sum, len - 1);
        return res;
    }
    public static List<List<Integer>> permutehelper(List<List<Integer>> list, boolean[] bools, int n, int d) {
        boolean[] booleans = bools.clone();
        for (int i = 0; i < ints.length; i++) {
            if (n == d && !booleans[i]) {
                List<Integer> ln = new ArrayList<>();
                ln.add(ints[i]);
                list.add(ln);
                return list;
            }
            if (booleans[i]) {
                continue;
            }
            List<List<Integer>> copylist = list.subList(0, list.size());

            if (n < d) {
                n++;
                boolean[] currbool = booleans.clone();
                currbool[i] = true;
                copylist = permutehelper(copylist, currbool, n, d);
            }
            for (List<Integer> l:copylist) {
                if (!l.isEmpty()) {
                    l.add(ints[i]);
                }
            }
            n--;
            list.addAll(copylist);
        }
        return list;
    }

    /**
     * 回溯
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    public static void main(String[] args) {
        int[] ints1 = new int[]{1, 2, 3};
        List<List<Integer>> listList = permute2(ints1);
        System.out.println(1);
    }
}
