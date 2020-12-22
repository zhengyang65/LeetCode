public class Leetcode125 {
    public static boolean isPalindrome(String s) {
        if (s == null | s.length() == 1) {
            return true;
        }
        int len = s.length();
        int p = 0;
        int q = len - 1;
        while (p < q) {
            char c1 = s.charAt(p);
            if (c1 < '0' | (c1 > '9' & c1 < 'A') | (c1 > 'Z' & c1 < 'a') | c1 > 'z') {
                p++;
                continue;
            }
            char c2 = s.charAt(q);
            if (c2 < '0' | (c2 > '9' & c2 < 'A') | (c2 > 'Z' & c2 < 'a') | c2 > 'z') {
                q--;
                continue;
            }
            if (c1 >= '0' & c1 <= '9') {
                if (c1 != c2) {
                    return false;
                }
            } else if(c1 >= 'a' & c1 <= 'z'){
                if (c1 != c2 & (c1 - 32) != c2) {
                    return false;
                }

            } else {
                if (c1 != c2 & (c1 + 32) != c2) {
                    return false;
                }
            }
            p++;
            q--;
        }
        return true;
    }

    /**
     * 时间复杂度：O(|s|)，其中 |s|是字符串s的长度。
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    public static boolean isPalindrome2(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s ="A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
}
