public class Leetcode38 {
    public static String countAndSay(int n) {
        String s = "1";
        if (n == 1) {
            return s;
        }
        s = "11";
        if (n == 2) {
            return s;
        }
        for (int i = 2; i < n; i++) {
            String s2 = "";
            char c1 = s.charAt(0);
            int count = 1;
            for(int j = 1; j < s.length(); j++) {
                char c2 = s.charAt(j);
                if (c1==c2) {
                    count += 1;
                } else {
                    s2 += count;
                    s2 += c1;
                    c1 = c2;
                    count = 1;
                }
            }
            s2 += count;
            s2 += c1;
            s = s2;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
