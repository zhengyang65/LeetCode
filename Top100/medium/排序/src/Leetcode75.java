import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Leetcode75 {

    public static void sortColors(int[] nums) {
        int zero = 0;
        int one = 0;
        //int two = 0;
        for (int i:nums) {
            if (i == 0) {
                zero += 1;
            } else if (i == 1) {
                one += 1;
            }
        }
        for (int j = 0; j < zero; j++) {
            nums[j] = 0;
        }
        for (int k = zero; k < zero + one; k++) {
            nums[k] = 1;
        }
        for (int p = zero + one; p < nums.length; p++) {
            nums[p] = 2;
        }

    }

    /**
     * 方法二：单指针
     * 我们可以考虑对数组进行两次遍历。在第一次遍历中，我们将数组中所有的 0交换到数组的头部。
     * 在第二次遍历中，我们将数组中所有的 1交换到头部的 0之后。此时，所有的 2都出现在数组的尾部，这样我们就完成了排序。
     * 时间复杂度：O(n)，其中 n是数组 nums 的长度。
     * 空间复杂度：O(1)。
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    /**
     * 方法二：双指针
     * 时间复杂度：O(n)，其中 n是数组 nums 的长度。
     * 空间复杂度：O(1)。
     * @param nums
     */
    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }

    /**
     * 也是双指针，指向0、2
     * @param nums
     */
    public void sortColors4(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors(nums);
        for (int i:nums) {
            System.out.println(i);
        }
    }
}
