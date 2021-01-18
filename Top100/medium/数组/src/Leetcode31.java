public class Leetcode31 {
    /**
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int len = nums.length - 1;
        boolean flag = false;
        int index = -1;
        int num = 0;
        for (int i = len; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                index = i - 1;
                flag = true;
                num = nums[index];
                break;
            }
        }
        if (flag) {
            for (int i = len; i > index; i--) {
                if (nums[i] > num) {
                    int temp = nums[i];
                    nums[i] = num;
                    nums[index] = temp;
                    break;
                }
            }
        }
        for (int i = index + 1,  j = len; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 4, 2, 1};
        nextPermutation(ints);
        System.out.println(1);
    }
}
