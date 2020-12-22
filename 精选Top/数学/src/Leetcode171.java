public class Leetcode171 {
    public static int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - 'A' + 1;
            res += Math.pow(26, s.length() - i - 1) * d;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "ZY";
        System.out.println(titleToNumber(s));
    }
}
