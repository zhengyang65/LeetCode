import java.util.Collections;

public class Leetcode647 {
    public int countSubstrings(String s) {
        int res = s.length();

        return res;
    }

    /** 思路：枚举每一个可能的回文中心，然后用两个指针分别向左右两边拓展，当两个指针指向的元素相同的时候就拓展，否则停止拓展。
     *
     * 在实现的时候，我们需要处理一个问题，即如何有序地枚举所有可能的回文中心，
     * 我们需要考虑回文长度是奇数和回文长度是偶数的两种情况。如果回文长度是奇数，那么回文中心是一个字符；
     * 如果回文长度是偶数，那么中心是两个字符。
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    /** 方法二：Manacher 算法（有点难）
     * 时间复杂度：O(n)。即 Manacher 算法的时间复杂度，由于最大回文右端点 rm只会增加而不会减少，
     * 故中心拓展进行的次数最多为 O(n)，此外我们只会遍历字符串一次，故总复杂度为 O(n)。
     * 空间复杂度：O(n)。
     * @param s
     * @return
     */
    public int countSubstrings3(String s) {
        int n = s.length();

        // 还要加上一个 "$"
        StringBuffer t = new StringBuffer("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        // 更新 s 的长度
        n = t.length();

        // 注意的是不能让下标越界，有一个很简单的办法，就是在开头加一个 "$" ，并在结尾加一个 "!"，
        // 这样开头和结尾的两个字符一定不相等，循环就可以在这里终止。
        t.append('!');
        int[] f = new int[n];
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 初始化 f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }

        return ans;
    }

}
