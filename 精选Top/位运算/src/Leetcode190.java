public class Leetcode190 {
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int res = 0;
        int mask = 1;
        int k = 31;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                res += (1 << k);
                String r2 = Integer.toBinaryString(1<<k);
                System.out.println(r2);
                String r = Integer.toBinaryString(res);
                System.out.println(r);
            }
            mask = mask << 1;
            k -= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        String res = Integer.toBinaryString(-3);
        System.out.println(res);
        System.out.println(reverseBits(-3));
        String res2 = Integer.toBinaryString(-2147483648);
        System.out.println(res2);
    }
}
