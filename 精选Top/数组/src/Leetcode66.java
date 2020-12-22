public class Leetcode66 {
    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        int index = len - 1;
        int d = digits[len - 1];
        if (d + 1 == 10) {
            int count = 0;
            while (index >= 0 && digits[index] == 9) {
                count += 1;
                index -= 1;
            }
            if (count == len) {
                int[] res = new int[count + 1];
                res[0] = 1;
                return res;
            }
            digits[index] = digits[index] + 1;
            for (int i = index + 1; i < len; i++) {
                digits[i] = 0;
            }
        } else {
            digits[len - 1] = d + 1;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] d = new int[]{8,9,9};
        int[] res = plusOne(d);
        for (int i:res) {
            System.out.println(i);
        }
    }
}
