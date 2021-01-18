import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode3 {

    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        int temp;
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        //boolean flag = false;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                //flag = true;
                res = Math.max(res, right - left);
                temp = left;
                left = map.get(c) + 1;
                for (int i = temp; i < left; i++) {
                    map.remove(s.charAt(i));
                }
            }
            map.put(c, right);
            right++;
        }
        res = Math.max(res, right - left);
        return res;
    }

    /**
     * 方法二：滑动窗口
     * 时间复杂度：O(N)，其中 N是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
     *
     * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。
     * 在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在[0,128) 内的字符，即∣Σ∣=128。我
     * 们需要用到哈希集合来存储出现过的字符，而字符最多有∣Σ∣ 个，因此空间复杂度为 O(∣Σ∣)。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            // i 是左指针
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断 地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        //String s = "abcdebafg";
        String s = " ";
        int ans = lengthOfLongestSubstring(s);
        System.out.println(ans);
    }
}
