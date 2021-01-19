package medium;

public class Leetcode162 {
    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        int num = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > num) {
                num = nums[i];
            } else {
                return i - 1;
            }
        }
        return len - 1;
    }

    /**
     * 方法二：迭代二分查找
     * 时间复杂度 :O(log2(n))
     * 空间复杂度 : O(1)
     * @param nums
     * @return
     */
    public int findPeakElement2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }


    public static void main(String[] args) {
        int[] test = new int[]{-2147483648};
    }
}
