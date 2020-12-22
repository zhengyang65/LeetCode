import java.util.*;

public class Leetcode387 {
    public static int firstUniqChar(String s) {
        Queue<Integer> queue = new ArrayDeque<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
                queue.add(i);
            } else if (map.get(c) == -1) {
                continue;
            } else {
                queue.remove(map.get(c));
                map.put(c, -1);
            }
        }
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }
}
