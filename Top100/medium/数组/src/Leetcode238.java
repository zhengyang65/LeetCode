public class Leetcode238 {
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int temp = 1;
        helper(res, nums, temp, 0);
        return res;
    }
    public static int helper(int[] res, int[] nums, int temp, int i) {
        if (i == nums.length - 1) {
            res[i] = temp;
            return nums[i];
        }
        int num = helper(res, nums, temp * nums[i], i + 1);
        res[i] = temp * num;
        return num * nums[i];
    }

    /**
     * 方法一：左右乘积列表
     * 时间复杂度：O(N)，其中 N指的是数组 nums 的大小。预处理 L 和 R 数组以及最后的遍历计算都是 O(N) 的时间复杂度。
     * 空间复杂度：O(N)，其中 N指的是数组 nums 的大小。使用了 L 和 R 数组去构造答案，L 和 R 数组的长度为数组 nums 的大小。
     */
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;

            // L 和 R 分别表示左右两侧的乘积列表
            int[] L = new int[length];
            int[] R = new int[length];

            int[] answer = new int[length];

            // L[i] 为索引 i 左侧所有元素的乘积
            // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
            L[0] = 1;
            for (int i = 1; i < length; i++) {
                L[i] = nums[i - 1] * L[i - 1];
            }

            // R[i] 为索引 i 右侧所有元素的乘积
            // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
            R[length - 1] = 1;
            for (int i = length - 2; i >= 0; i--) {
                R[i] = nums[i + 1] * R[i + 1];
            }

            // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
            for (int i = 0; i < length; i++) {
                answer[i] = L[i] * R[i];
            }

            return answer;
        }
    }

    /**
     * 方法二：空间复杂度 O(1)的方法
     * 时间复杂度：O(N)，其中 N指的是数组 nums 的大小。分析与方法一相同。
     * 空间复杂度：O(1)，输出数组不算进空间复杂度中，因此我们只需要常数的空间存放变量
     */
    class Solution2 {
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;
            int[] answer = new int[length];

            // answer[i] 表示索引 i 左侧所有元素的乘积
            // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
            answer[0] = 1;
            for (int i = 1; i < length; i++) {
                answer[i] = nums[i - 1] * answer[i - 1];
            }

            // R 为右侧所有元素的乘积
            // 刚开始右边没有元素，所以 R = 1
            int R = 1;
            for (int i = length - 1; i >= 0; i--) {
                // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
                answer[i] = answer[i] * R;
                // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
                R *= nums[i];
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4};
        int[] res = productExceptSelf(ints);
        System.out.println(1);
    }
}
