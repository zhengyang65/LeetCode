
import java.util.ArrayList;
import java.util.Arrays;

public class Leetcode350 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 | nums2.length == 0) {
            return new int[]{};
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> arrayList = new ArrayList<>();
        int index = 0;
        if (nums1.length < nums2.length) {
            int len = nums1.length;
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[index] == nums2[j]) {
                    arrayList.add(nums2[j]);
                    index++;
                } else if (nums1[index] < nums2[j]) {
                    index++;
                    j--;
                }
                if (index >= len) {
                    break;
                }
            }
        } else {
            int len = nums2.length;
            for (int j = 0; j < nums1.length; j++) {
                if (nums2[index] == nums1[j]) {
                    arrayList.add(nums1[j]);
                    index++;
                } else if (nums2[index] < nums1[j]) {
                    index++;
                    j--;
                }
                if (index >= len) {
                    break;
                }
            }
        }
        int[] res = new int[arrayList.size()];
        int r = 0;
        for (int i:arrayList) {
            res[r] = i;
            r++;
        }
        return res;
    }

    /**
     * 时间复杂度：O(mlogm+nlogn)，其中 m 和 n 分别是两个数组的长度。
     *   对两个数组进行排序的时间复杂度是O(mlogm+nlogn)，遍历两个数组的时间复杂度是 O(m+n)
     *   因此总时间复杂度是O(mlogm+nlogn)。
     * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个数组的长度。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{-2147483648,1,2,3};
        int[] nums2 = new int[]{1,-2147483648,-2147483648};
        int[] res = intersect(nums1, nums2);
        for (int i:res) {
            System.out.println(i);
        }
    }
}
