import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode17 {
    static List<String> res = new ArrayList<>();
    static String string;

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        string = digits;
        Map<Character, Character[]> map = new HashMap<>();
        map.put('2', new Character[]{'a', 'b', 'c'});
        map.put('3', new Character[]{'d', 'e', 'f'});
        map.put('4', new Character[]{'g', 'h', 'i'});
        map.put('5', new Character[]{'j', 'k', 'l'});
        map.put('6', new Character[]{'m', 'n', 'o'});
        map.put('7', new Character[]{'p', 'q', 'r', 's'});
        map.put('8', new Character[]{'t', 'u', 'v'});
        map.put('9', new Character[]{'w', 'x', 'y', 'z'});
        letterCombinations(new StringBuffer(), map, 0);
        return res;
    }
    public static void letterCombinations(StringBuffer stringBuffer, Map<Character, Character[]> map, int i) {
        if (stringBuffer.length() == string.length()) {
            res.add(stringBuffer.toString());
            return;
        }
        Character[] chars = map.get(string.charAt(i));
        int len = chars.length;
        for (int k = 0; k < len; k++) {
            stringBuffer.append(chars[k]);
            letterCombinations(stringBuffer, map, i + 1);
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> stringList = letterCombinations("23");
        System.out.println(1);
    }
}
