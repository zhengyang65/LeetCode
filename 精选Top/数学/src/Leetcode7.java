public class Leetcode7 {
    public static int reverse(int x) {
        int res = 0;
        int wei = 0;
        int num1 = x;
        while (num1 != 0) {
            num1 /= 10;
            wei += 1;
        }
        int mask = (int)Math.pow(10, wei - 1);
        int symbol = x >>> 31;
        int num2 = Math.abs(x);
        while (num2 > 0) {
            int c = num2 % 10;
            int d = res;
            res += c * mask;
            if (res - c * mask != d) {
                return 0;
            }
            num2 = (num2 - c) / 10;
            mask /= 10;
        }
        return res * (int)Math.pow(-1, symbol);
    }

    public int reverse2(int x) {
        int ans = 0;
        while (x != 0) {
            // 防止栈溢出
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int a =  1534236469;
        System.out.println(Integer.toBinaryString(a));
        int d = a >> 31;
        System.out.println(Integer.toBinaryString(d));
        System.out.println(d);
        System.out.println(reverse(a));
    }
}
