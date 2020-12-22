public class Leetcode371 {
    public static int getSum(int a, int b) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            int d = mask << i;
            if ((a & d) != 0) {
                res += d;
            }
            if ((b & d) != 0) {
                res += d;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getSum(1,2));
    }
}
