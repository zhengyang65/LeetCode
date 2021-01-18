public class Leetcode70 {
    /**
     * 递归超时
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 1;
        }
        return climbStairs(n-1) + climbStairs(n-2);
    }

    /**
     * 迭代
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int i = 3;
        int p = 1;
        int q = 2;
        while (i <= n) {
            int temp = q;
            q = p + q;
            p = temp;
            i++;
        }
        return q;
    }
}
