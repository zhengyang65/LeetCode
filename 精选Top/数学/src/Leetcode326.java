public class Leetcode326 {
    public static boolean isPowerOfThree(int n) {
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 如果我们把我们的数转换成基3，并且表示形式是 100…0，那么这个数就是3的幂
     * 我们将使用上面的正则表达式来检查字符串是否以1 ^1 开头，后跟 0 或 多个 0* 并且不包含任何其他值 $。
     */
    public class Solution {
        public boolean isPowerOfThree(int n) {
            return Integer.toString(n, 3).matches("^10*$");
        }
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27));
    }
}
