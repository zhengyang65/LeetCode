import java.util.ArrayList;
import java.util.List;

public class Leetcode5 {
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c:chars) {
            sb.append(c);
            sb.append('#');
        }
        String modstring = sb.toString();
        int len = modstring.length();
        int[] radius = new int[len];
        int c = -1;
        int r = -1;
        String res = "";
        for (int i = 0; i < len; i++) {
            radius[i] = r > i? Math.min(r - i + 1, radius[2*c - i]):1;
            while (i + radius[i] < len && i - radius[i] >= 0) {
                if (modstring.charAt(i + radius[i]) == modstring.charAt(i - radius[i])) {
                    radius[i] += 1;
                } else {
                    break;
                }
            }
            if (i + radius[i] > r) {
                r = i + radius[i] - 1;
                c = i;
            }
            if (res.length() < radius[i]) {
                res = s.substring((2 * c - r + 1)/2, (r - 1)/2 + 1);
            }
        }
        return res;
    }


    public String longestPalindrome2(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        //只要一次就可，不用每次计算res
        //过滤 # 号
        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }


    public static void main(String[] args) {
        String test = "cabad";
        String ans = longestPalindrome(test);
        System.out.println(ans);
    }
}
