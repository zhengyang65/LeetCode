public class Leetcode283 {
    public static void moveZeroes(int[] nums) {
        int sort = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sort += 1;
                continue;
            }
            if(sort > 0) {
                nums[i -sort] = nums[i];
                nums[i] = 0;
            }
        }
    }

    /**
     * 方法：双指针
     * 时间复杂度：O(n)，其中 n为序列长度。每个位置至多被遍历两次。
     * 空间复杂度：O(1)。只需要常数的空间存放若干变量
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
