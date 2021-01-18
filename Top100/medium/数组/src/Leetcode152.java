import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Leetcode152 {
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int i = 0;
        boolean zero = false;
        while (i < nums.length) {
            int[] ints = new int[]{1,1};

            int num = nums[i];
            if (num == 0) {
                i++;
                zero = true;
                continue;
            }
            boolean sign = true;
            boolean pos = false;   //是否操作过ints[0]
            boolean flag = true;   //前一个符号
            while (i < nums.length && nums[i] != 0) {
                if (nums[i] > 0 && sign) {
                    ints[0] *= nums[i];
                    //不需要修改int[1];
                    pos = true;
                } else if (nums[i] < 0 && !sign) {
                    ints[0] = nums[i] * ints[1];
                    ints[1] = nums[i];
                    sign = true;
                    pos = true;
                    flag = false;
                } else if (nums[i] > 0 && !sign) {
                    ints[1] *= nums[i];
                    ints[0] = Math.max(ints[0], nums[i]);
                } else if (nums[i] < 0 && sign) {
                    if (!flag) {
                        int temp = ints[0];
                        ints[0] = Math.max(ints[0], ints[1] * nums[i]);
                        ints[1] = nums[i] * temp;
                    } else {
                        ints[1] = nums[i] * ints[0];
                    }
                    sign = false;
                    flag = false;
                }
                i++;
            }
            if (pos) {
                res = Math.max(res, ints[0]);
            } else {
                res = Math.max(res, ints[1]);
            }
        }
        if (zero) {
            return Math.max(res, 0);
        }
        return res;
    }


    /** 方法：动态规划
     * 时间复杂度：程序一次循环遍历了 nums，故渐进时间复杂度为 O(n)。
     * 空间复杂度：优化后只使用常数个临时变量作为辅助空间，与 n无关，故渐进空间复杂度为 O(1)。
     * @param nums
     * @return
     */
    public static int maxProduct2(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2, -5, -2, -4, 3};
        int ans = maxProduct2(ints);
        System.out.println(ans);
    }
}
