import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode169 {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int mid = nums.length / 2;
        for (int i = 0; i <= mid; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
            if (map.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }
        for (int i = mid + 1; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) + nums.length / 2 < mid + 1) {
                    map.remove(nums[i]);
                }
                if (map.get(nums[i]) > nums.length / 2) {
                    return nums[i];
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 方法一：哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public class Solution1 {
        private Map<Integer, Integer> countNums(int[] nums) {
            Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
            for (int num : nums) {
                if (!counts.containsKey(num)) {
                    counts.put(num, 1);
                } else {
                    counts.put(num, counts.get(num) + 1);
                }
            }
            return counts;
        }

        public int majorityElement(int[] nums) {
            Map<Integer, Integer> counts = countNums(nums);

            Map.Entry<Integer, Integer> majorityEntry = null;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                    majorityEntry = entry;
                }
            }

            return majorityEntry.getKey();
        }
    }

    /**
     * 方法二：排序
     * 思路：如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，
     * 那么下标为  n/2 的元素（下标从 0 开始）一定是众数。
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public class Solution2 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    /**
     * 方法三：分治
     * 思路:
     * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     * <p>
     * 算法:
     * 我们使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。
     * 长度为 1 的子数组中唯一的数显然是众数，直接返回即可。
     * 如果回溯后某区间的长度大于 1，我们必须将左右子区间的值合并。
     * 如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。
     * 否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
     * <p>
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     * 尽管分治算法没有直接分配额外的数组空间，但在递归的过程中使用了额外的栈空间。
     * 算法每次将数组从中间分成两部分，所以数组长度变为 1 之前需要进行O(logn) 次递归，即空间复杂度为O(logn)。
     */
    public class Solution3 {
        private int countInRange(int[] nums, int num, int lo, int hi) {
            int count = 0;
            for (int i = lo; i <= hi; i++) {
                if (nums[i] == num) {
                    count++;
                }
            }
            return count;
        }

        private int majorityElementRec(int[] nums, int lo, int hi) {
            // base case; the only element in an array of size 1 is the majority
            // element.
            if (lo == hi) {
                return nums[lo];
            }

            // recurse on left and right halves of this slice.
            int mid = (hi - lo) / 2 + lo;
            int left = majorityElementRec(nums, lo, mid);
            int right = majorityElementRec(nums, mid + 1, hi);

            // if the two halves agree on the majority element, return it.
            if (left == right) {
                return left;
            }

            // otherwise, count each element and return the "winner".
            int leftCount = countInRange(nums, left, lo, hi);
            int rightCount = countInRange(nums, right, lo, hi);

            return leftCount > rightCount ? left : right;
        }

        public int majorityElement(int[] nums) {
            return majorityElementRec(nums, 0, nums.length - 1);
        }
    }

    /**方法五：Boyer-Moore 投票算法
     * 思路:
     * 如果我们把众数记为 +1，把其他数记为 −1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
     *
     * 举例：
     * nums:      [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
     * candidate:  7  7  7  7  7  7   5  5   5  5  5  5   7  7  7  7
     * count:      1  2  1  2  1  0   1  0   1  2  1  0   1  2  3  4
     */

    public class Solution4 {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }
}

