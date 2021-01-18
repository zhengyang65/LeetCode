import java.util.ArrayList;
import java.util.List;

public class Leetcode78 {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        lists.add(list);
        if (nums.length == 0) {
            return lists;
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> p = new ArrayList<>();
            List<Integer> q = new ArrayList<>();
            p.add(nums[i]);
            q.add(nums[i]);
            lists.add(q);
            for (int j = i + 1; j < nums.length; j++) {
                p.add(nums[j]);
                List<Integer> r = new ArrayList<>(p);
                lists.add(r);
            }
        }
        return lists;
    }

    /**
     * 方法一：迭代法实现子集枚举
     * 时间复杂度：O(n×2^n)
     * 空间复杂度：O(n)。即构造子集使用的临时数组 t 的空间代价。
     */
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets2(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

    /**
     *方法二：回溯
     * 时间复杂度：O(n×2^n)
     * 空间复杂度：O(n)。即构造子集使用的临时数组 t 的空间代价。
     */
    static List<Integer> t2 = new ArrayList<Integer>();
    static List<List<Integer>> ans2 = new ArrayList<List<Integer>>();

    public static List<List<Integer>> subsets3(int[] nums) {
        dfs(0, nums);
        return ans2;
    }

    public static void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans2.add(new ArrayList<Integer>(t2));
            return;
        }
        t2.add(nums[cur]);
        dfs(cur + 1, nums);
        t2.remove(t2.size() - 1);
        dfs(cur + 1, nums);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,2,9};
        subsets3(nums);
    }
}
