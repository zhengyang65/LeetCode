package medium;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode179 {
    public static String largestNumber(int[] nums) {
        Integer[] temp = new Integer[nums.length];
        for (int i = 0; i<nums.length; i++) {
            temp[i] = nums[i];
        }
        Arrays.sort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer a,Integer b) {
                String s1 = String.valueOf(a);
                String s2 = String.valueOf(b);
                int len1 = s1.length();
                int len2 = s2.length();
                int i = 0;
                while (i <= len1 - 1 && i <= len2 - 1) {
                    int num1 = s1.charAt(i);
                    int num2 = s2.charAt(i);
                    if (num1 != num2) {
                        return num2 - num1;
                    } else {
                        i++;
                    }
                }
                if (len1 == len2) {
                    return a - b;
                }
                int com = 0;
                if (len1 < len2) {
                    com = s2.charAt(i);
                } else {
                    com = s1.charAt(i);
                }
                int j = 0;
                while (j < i) {
                    int num = s1.charAt(j);
                    if (num != com) {
                        return (num - com) * (a - b);
                    } else {
                        j++;
                    }
                }
                return a - b;
            }
        });
        StringBuilder res = new StringBuilder();
        if (temp[0] == 0) {
            return "0";
        }
        for (int i:temp) {
            res.append(i);
        }
        return res.toString();
    }

    /**
     * 没想到可以直接字符串比较，无需转化为数字
     * 时间复杂度：O(nlgn)，排序
     * 空间复杂度：O(n)
     * 这里，我们使用了 O(n)的额外空间去保存 nums 的副本。
     */
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber2(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        StringBuilder largestNumberStr = new StringBuilder(new String());
        for (String numAsStr : asStrs) {
            largestNumberStr.append(numAsStr);
        }
        return largestNumberStr.toString();
    }

    public static void main(String[] args) {
        int[] test = new int[]{0,0};
        String ans = largestNumber(test);
        System.out.println(ans);
    }

}
