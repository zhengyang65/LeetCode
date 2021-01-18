import java.util.*;

public class Leetcode55 {
    static int len;
    static ArrayList<Integer> list;

    public static boolean canJump(int[] nums) {
        list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        len = nums.length - 1;
        return canJump(0);
    }

    public static boolean canJump(int d) {
        int val = list.get(d);
        List<Integer> sub = new ArrayList<>();
        sub = list.subList(d, Math.min(len, d + val + 1));
        ArrayList<Integer> currlist = new ArrayList<>(Arrays.asList(new Integer[sub.size()]));
        Collections.copy(currlist, sub);
        currlist.sort(Comparator.comparingInt(o -> o));
        if (d + val >= len) {
            return true;
        }
        if (val == 0) {
            return false;
        }
        int target = 0;
        for (int j = val; j > 0; j--) {
            if (currlist.get(j) > target) {
                break;
            }
            target += 1;
        }
        if (target == val) {
            return false;
        }
        for (int i = val; i > 0; i--) {
            if (canJump(d + i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 贪心算法
     * 时间复杂度：O(n)，其中 n为数组的大小。只需要访问 nums数组一遍，共 n个位置。
     * 空间复杂度：O(1)，不需要额外的空间开销。
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,1,1,0};
        boolean ans = canJump(test);
        System.out.println(ans);
    }
}
