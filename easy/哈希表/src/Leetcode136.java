import java.util.HashMap;
import java.util.Map;

public class Leetcode136 {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int num:nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, true);
            }
        }
        for (Map.Entry<Integer, Boolean> entry: map.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("No result");
    }

    /**
     * 方法二：使用位运算。
     *
     * 对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
     * 任何数和 00 做异或运算，结果仍然是原来的数，即 a⊕0=a。
     * 任何数和其自身做异或运算，结果是 0，即a⊕a=0。
     * 异或运算满足交换律和结合律，即a⊕b⊕a = b⊕a⊕a = b⊕(a⊕a) = b⊕0=b。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
