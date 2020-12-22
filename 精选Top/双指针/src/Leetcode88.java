import java.util.Arrays;

public class Leetcode88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = 0;
        int q = 0;
        int count = -1;
        while (q < n & p < m + n) {
            if (p >= m & nums1[p] <= nums2[q]) {
                for (int i = p + 1; i < m + n; i++) {
                    nums1[i] = nums2[q];
                    q++;
                }
            } else if (nums1[p] <= nums2[q] & p < m) {
                p++;
            } else {
                count += 1;
                for (int i = m + count; i > p; i--) {
                    nums1[i] = nums1[i-1];
                }
                nums1[p] = nums2[q];
                q++;
                p++;
            }
        }
    }

    /**
     * 方法一 : 合并后排序
     * 时间复杂度 : O((n+m)log(n+m))
     * 空间复杂度 : O(1)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 方法二 : 双指针 / 从前往后
     * 时间复杂度 : O(n + m)
     * 空间复杂度 : O(m)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n)) {
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }
        // if there are still elements to add
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    /**
     * 方法三 : 双指针 / 从后往前
     * 时间复杂度 : O(n + m)
     * 空间复杂度 : O(1)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge4(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0)) {
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        int[] n = new int[]{2,5,6,0,0,0};
        int[] m = new int[]{1,2,3};
        merge4(n,3,m,3);
        for (int i = 0;i < 6; i++ ) {
            System.out.println(n[i]);
        }
    }
}
