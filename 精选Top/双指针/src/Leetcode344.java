public class Leetcode344 {
    /**
     * 时间复杂度：O(N)，其中 N为字符数组的长度。一共执行了N/2 次的交换。
     * 空间复杂度：O(1)。只使用了常数空间来存放若干变量。
     * @param s
     */
    public static void reverseString(char[] s) {
        int len = s.length;
        int p = 0;
        int q = len - 1;
        while (p < q) {
            char temp = s[p];
            s[p] = s[q];
            s[q] = temp;
            p += 1;
            q -= 1;
        }
    }

    public void reverseString2(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }


    public static void main(String[] args) {
        char[] chars = new char[]{'H','a','l','l','o','h'};
        reverseString(chars);
        for (char c:chars) {
            System.out.println(c);
        }
    }
}
