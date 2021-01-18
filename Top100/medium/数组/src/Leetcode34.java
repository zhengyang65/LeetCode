public class Leetcode34 {
    public static int[] res = new int[]{-1, -1};
    public static int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return res;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                res[0] = mid;
                res[1] = mid;
                dfs(nums, target, left, mid - 1);
                dfs(nums, target, mid + 1, right);
                break;
            } else if (nums[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static void dfs(int[] nums, int target, int left, int right) {
        if (left > right || left < 0 || right > nums.length - 1) {
            return;
        }
        if (nums[left] > target || nums[right] < target) {
            return;
        }
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            res[0] = Math.min(res[0], mid);
            res[1] = Math.max(res[1], mid);
            dfs(nums, target, left, mid - 1);
            dfs(nums, target, mid + 1, right);
        } else if (nums[mid] > target){
            dfs(nums, target, left, mid - 1);
        } else {
            dfs(nums, target, mid + 1, right);
        }
    }

    /**
     * 同样二分查找
     * 时间复杂度： O(logn) ，其中 n为数组的长度。二分查找的时间复杂度为 O(logn)，一共会执行两次，因此总时间复杂度为O(logn)。
     * 空间复杂度：O(1) 。只需要常数空间存放若干变量。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {    // lower区分大于还是大于等于，妙！
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{5, 7, 7, 8, 8, 10};
        int[] ans = searchRange(ints, 8);
        System.out.println(1);
    }
}
