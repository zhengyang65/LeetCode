import java.util.*;

public class Leetcode49 {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character, Integer>, List<String>> map = new HashMap<>();
        for (String s:strs) {
            Map<Character, Integer> map1 = new HashMap<>();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0) + 1);
            }

            if (!map.containsKey(map1)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(map1, list);
            } else {
                map.get(map1).add(s);
            }
        }
        //res = (List<List<String>>) map.values();
        return new ArrayList<>(map.values());
    }

    /**
     * 方法二：排序
     * 思路：由于互为字母异位词的两个字符串包含的字母相同，
     * 因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键。
     *
     * 时间复杂度：O(nklogk)，其中 n是 strs 中的字符串的数量，k是 strs 中的字符串的的最大长度。
     * 需要遍历 n个字符串，对于每个字符串，需要 O(klogk) 的时间进行排序以及 O(1)的时间更新哈希表，
     * 因此总时间复杂度是 O(nklogk)。
     *
     * 空间复杂度：O(nk)，其中 n是 strs 中的字符串的数量，k是 strs 中的字符串的的最大长度。需要用哈希表存储全部字符串。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /** 方法三：计数
     *  思路：由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26的数组记录每个字母出现的次数。
     *
     *  时间复杂度：O(n(k+∣Σ∣))，其中 n是 strs 中的字符串的数量，k是 strs 中的字符串的的最大长度，
     *  Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。需要遍历 n个字符串，
     *  对于每个字符串，需要 O(k)时间计算每个字母出现的次数，O(∣Σ∣) 的时间生成哈希表的键，
     *  以及 O(1)的时间更新哈希表，因此总时间复杂度是 O(n(k+∣Σ∣))。
     *
     * 空间复杂度：O(n(k+∣Σ∣))，需要用哈希表存储全部字符串，而记录每个字符串中每个字母出现次数的数组需要的空间为 O(∣Σ∣)，
     * 在渐进意义下小于 O(n(k+∣Σ∣))，可以忽略不计。

     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);    //次数也加上
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
        String[] test = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> ans= groupAnagrams(test);
        for (List<String> list:ans) {
            for (String s:list) {
                System.out.print(s + "  ");
            }
            System.out.println();
        }
    }
}
