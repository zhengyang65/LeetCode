public class Leetcode287 {
    /**
     * 方法一：二分查找 （不懂）
     * 时间复杂度：O(nlogn)，其中 n为 nums[] 数组的长度。二分查找最多需要二分 O(logn) 次，
     * 每次判断的时候需要O(n) 遍历 nums[] 数组求解小于等于 mid 的数的个数，因此总时间复杂度为 O(nlogn)。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        // l和r不断缩小范围
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }

            if (cnt <= mid) {    //   表明mid左边正确，去右边查找
                l = mid + 1;
            } else {             //   表明应去mid左边查找
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 方法二；二进制（妙）
     * 时间复杂度：O(nlogn)，其中 n为 nums[] 数组的长度。O(logn) 代表了我们枚举二进制数的位数个数，
     * 枚举第 i位的时候需要遍历数组统计 x和 y的答案，因此总时间复杂度为 O(nlogn)。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        //确定最高有几位
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    /**
     * 方法三：快慢指针
     * 思路：我们先设置慢指针slow 和快指针 fast ，慢指针每次走一步，快指针每次走两步，
     * 根据「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，
     * 此时我们再将slow 放置起点 0，两个指针每次同时移动一步，相遇的点就是答案。
     * 时间复杂度：O(n)。「Floyd 判圈算法」时间复杂度为线性的时间复杂度。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, 2, 4, 1};
        int ans = findDuplicate(ints);
        System.out.println(ans);
    }
}
