import java.util.HashMap;

public class Leetcode560 {
    public static int subarraySum(int[] nums, int k) {
        int ans = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                res[j] += nums[i + j];
                if (res[j] == k) {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：前缀和 + 哈希表优化
     * 时间复杂度：O(n)，其中 n为数组的长度。我们遍历数组的时间复杂度为 O(n)，
     * 中间利用哈希表查询删除的复杂度均为 O(1)，因此总时间复杂度为 O(n)。
     * 空间复杂度：O(n)，其中 n为数组的长度。哈希表在最坏情况下可能有 n个不同的键值，因此需要 O(n) 的空间复杂度。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap< Integer, Integer > mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{3, 2, 1, 4, 3};
        int ans = subarraySum(ints, 5);
        System.out.println(ans);
    }
}
