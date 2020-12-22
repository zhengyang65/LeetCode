public class Leetcode191 {
    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int count = 0;
        if (n < 0) {
            if ((n & 1) == 1) {
                count += 1;
            }
            n = n >>> 1;
        }
        while (n != 0) {
            if ((n & 1) == 1) {
                count += 1;
            }
            n = n >> 1;
        }
        return count;
    }

    public static void main(String[] args) {

        String res = Integer.toBinaryString(-1);
        System.out.println(res);
        System.out.println(hammingWeight(-1));
    }
}
