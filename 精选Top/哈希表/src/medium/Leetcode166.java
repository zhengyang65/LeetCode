package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode166 {
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        double num = (double) numerator / denominator;
        String str = String.valueOf(num);

        if (Math.abs(num - (int) num) < 0.00001) {
            return String.valueOf((int) num);
        }

        int len = str.length();
        int i = 0;
        char c = str.charAt(i);
        while (c != '.') {
            c = str.charAt(i++);
            sb.append(c);
        }
        if (len - i < 16) {
            return str;
        }

        Map<Character, List<Integer>> map = new HashMap<>();
        int index = i;
        int start = 0;
        int finish = 0;
        boolean flag = false;
        for (; i < len - 1; i++) {
            c = str.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, List.of(i));
            } else {
                List<Integer> list = map.get(c);
                for (int n : list) {
                    int n2 = i;
                    int n3 = 2 * i - n;
                    if (n3 > len) {
                        if (str.substring(n, n + len - i).equals(str.substring(i, len))) {
                            flag = true;
                        }
                    }
                    while (n3 < len) {
                        if (str.substring(n, i).equals(str.substring(n2, n3))) {
                            flag = true;
                            n2 += i - n;
                            n3 += i - n;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        start = n;
                        finish = i;
                        break;
                    }
                }
                if (flag) {
                    break;
                } else {
                    List<Integer> list1 = new ArrayList<>(list);
                    list1.add(i);
                    map.put(c, list1);
                }
            }
        }
        if (flag) {
            sb.append(str, index, start);
            sb.append('(');
            sb.append(str, start, finish);
            sb.append(')');
            return sb.toString();
        }
        sb.append('(');
        sb.append(str, index, len);
        sb.append(')');
        return sb.toString();
    }

    /**
     * 方法：长除法
     * @param numerator
     * @param denominator
     * @return
     */
    public static String fractionToDecimal2(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        // 负数
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        // 防止溢出
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

    public static void main(String[] args) {
        String string = fractionToDecimal2(1, 19);
        System.out.println(string);
    }
}
