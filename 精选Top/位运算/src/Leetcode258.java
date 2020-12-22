public class Leetcode258 {
    public static int missingNumber(int[] nums) {
        int len = nums.length;
        int sum = (len + 1) * len / 2;
        int res = 0;
        for (int i:nums) {
            res += i;
        }
        return sum - res;
    }

    /**
     * 方法二：异或
     * 由于异或运算（XOR）满足结合律，并且对一个数进行两次完全相同的异或运算会得到原来的数，
     * 因此我们可以通过异或运算找到缺失的数字。
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{0,1,3,4,5};
        System.out.println(missingNumber(ints));
    }
}
