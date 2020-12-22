import java.util.ArrayDeque;
import java.util.Queue;

public class Leetcode28 {
    public static int strStr(String haystack, String needle) {
        int p = 0;
        int q = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        while (p < haystack.length()) {
            if (q == needle.length()) {
                return p - needle.length();
            }
            char c1 = haystack.charAt(p);
            char c2 = needle.charAt(q);
            if (c1 == c2) {
                q++;
                queue.add(p);
                p++;
            } else {
                if (!queue.isEmpty()) {
                    queue.remove();
                }
                q = 0;
                if (queue.isEmpty()) {
                    p++;
                } else {
                    p = queue.peek();
                }
            }
        }
        if (q == needle.length()) {
            return p - needle.length();
        }
        return -1;
    }
    public static int strStr2(String haystack, String needle) {
        int p = 0;
        int q = 0;
        int curr = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        while (p < haystack.length()) {
            if (q == needle.length()) {
                return p - needle.length();
            }
            char c1 = haystack.charAt(p);
            char c2 = needle.charAt(q);
            if (c1 == c2) {
                q++;
                queue.add(p);
                p++;
                curr++;
            } else {
                q = 0;
                p = p + 1 - curr;
                curr = 0;
            }
        }
        if (q == needle.length()) {
            return p - needle.length();
        }
        return -1;
    }
    /**
     * 时间复杂度：最坏时间复杂度为O((N−L)L)，最优时间复杂度为 O(N)
     * 空间复杂度：O(1)
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr3(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L == 0) return 0;

        int pn = 0;
        while (pn < n - L + 1) {
            // find the position of the first needle character
            // in the haystack string
            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) ++pn;

            // compute the max match string
            int currLen = 0, pL = 0;
            while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }

            // if the whole needle string is found,
            // return its start position
            if (currLen == L) return pn - L;

            // otherwise, backtrack
            pn = pn - currLen + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s1 = "mississippi";
        String s2 = "issip";
        System.out.println(strStr2(s1,s2));
        System.out.println(s1.indexOf(s2));
    }
}
