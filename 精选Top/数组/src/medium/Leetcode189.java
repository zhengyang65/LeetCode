package medium;

import java.util.LinkedList;
import java.util.List;

public class Leetcode189 {
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        if (k == 0) {
            return;
        }
        List<Integer> list = new LinkedList<>();
        if (k > (len / 2)) {
            k = len - k;
            for (int i = 0; i < k; i++) {
                list.add(nums[i]);
            }
            for (int i = 0; i < len - k; i++) {
                nums[i] = nums[i+k];
            }
            for (int i = len - k; i < len; i++) {
                nums[i] = list.remove(0);
            }
        } else {
            for (int i = len - k; i < len; i++) {
                list.add(nums[i]);
            }
            for (int i = len - k - 1; i >= 0; i--) {
                nums[i+k] = nums[i];
            }
            for (int i = 0; i < k; i++) {
                nums[i] = list.remove(0);
            }
        }

     }

    /**
     * 翻转数组，比如1，2，3，4，5，6，7
     * 1° . 7,6,5, 4,3,2,1
     * 2° . 5,6,7, 1,2,3,4
     * 时间复杂度：O(n)，其中 n为数组的长度。每个元素被翻转两次，一共 n个元素，因此总时间复杂度为 O(2n)=O(n)
     * 空间复杂度：O(1)。
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }


    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4,5};
        rotate(test, 10);
        System.out.println(1);
    }
}
