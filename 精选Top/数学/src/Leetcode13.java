import java.util.HashMap;
import java.util.Map;

public class Leetcode13 {
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int p = 1001;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
             Character c = s.charAt(i);
             int d = map.get(c);
            if (d <= p) {
                res += d;
                p = d;
            } else {
                res = res - 2 * p + d;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
