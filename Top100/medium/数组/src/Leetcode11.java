public class Leetcode11 {
    public static int maxArea(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return res;
    }

    /**
     * 方法二：双指针
     * 时间复杂度：O(N)，双指针总计最多遍历整个数组一次。
     * 空间复杂度：O(1)，只需要额外的常数级别的空间。
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,8,6,2,5,4,8,3,7};
        int ans = maxArea(ints);
        System.out.println(ans);
    }
}
