public class Leetcode338 {

    public static int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        if (num == 0) {
            return res;
        }
        res[1] = 1;
        if (num == 1) {
            return res;
        }
        int times = (int)(Math.log(num) / Math.log(2));
        int counts = (int) Math.pow(2, times);
        //int bias = num - times;
        for (int i = 1; i < times; i++) {
            int first = (int) Math.pow(2, i);
            int end = (int) Math.pow(2, i + 1);
            for (int k = first; k < end;k++) {
                res[k] = res[k - first] + 1;
            }
        }
        for (int j = counts; j < num + 1; j++) {
            res[j] = res[j - counts] + 1;
        }
        return res;
    }

    /**
     * 更简洁
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        int i = 0, b = 1;
        // [0, b) is calculated
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            while(i < b && i + b <= num){
                ans[i + b] = ans[i] + 1;
                ++i;
            }
            i = 0;   // reset i
            b <<= 1; // b = 2b
        }
        return ans;
    }

    /**
     * P(x)=P(x / 2)+(x mod 2)
     * @param num
     * @return
     */
    public int[] countBits3(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
        return ans;
    }

    /**
     * 方法四：动态规划 + 最后设置位【通过】  (神奇)
     * 算法
     * 与上述方法思路相同，我们可以利用最后设置位。
     *
     * 最后设置位是从右到左第一个为 1的位。使用 x &= x - 1 将该位设置为0，就可以得到以下状态转移函数：
     * P(x)=P(x&(x−1))+1;
     * @param num
     * @return
     */
    public int[] countBits4(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }

    public static void main(String[] args) {
        int[] ans = countBits(1);
        for (int i:ans) {
            System.out.println(i);
        }
        //System.out.println((int)(Math.log(31) / Math.log(2)));
    }
}
