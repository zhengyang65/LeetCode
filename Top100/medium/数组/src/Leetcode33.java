public class Leetcode33 {
    static public int index = -1;
    static public int res = -1;
    static public int tar;
    public static int search(int[] nums, int target) {
        tar = target;
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] > nums[right]) {
            binarySearch(nums, left, right);
        } else {
            binarySearch2(nums, left, right, target);
        }
        return res;
    }
    public static void binarySearch(int[] nums, int p, int q) {
        if (p > q || p < 0 || q > nums.length - 1) {
            return;
        }
        int mid = (p + q) / 2;
        if (mid > 0 && nums[mid] < nums[mid - 1]) {
            index = mid;
            if (nums[0] > tar) {
                binarySearch2(nums, mid, nums.length - 1, tar);
            } else {
                binarySearch2(nums, 0, mid - 1, tar);
            }
            return;
        }
        binarySearch(nums, p, mid - 1);
        binarySearch(nums, mid + 1, q);
    }

    public static void binarySearch2(int[] nums, int p, int q, int target) {
        if (p > q || p < 0 || q > nums.length - 1) {
            return;
        }
        int mid = (p + q) / 2;
        if (nums[mid] == target) {
            res = mid;
            return;
        }
        binarySearch2(nums, p, mid - 1, target);
        binarySearch2(nums, mid + 1, q, target);
    }

    /**
     * 更简便的二分
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,3};
        int ans = search(ints, 3);
        System.out.println(ans);
    }
}
