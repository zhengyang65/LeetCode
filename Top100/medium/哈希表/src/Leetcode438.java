import java.util.*;

public class Leetcode438 {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int plen = p.length();
        if (s == null || s.length() < plen) {
            return list;
        }
        Set<Character> characterSet = new HashSet<>();
        Set<String> set = new HashSet<>();
        int[] counts = new int[26];

        for (int i = 0; i < plen; i++) {
            char c = p.charAt(i);
            counts[c - 'a']++;
            characterSet.add(c);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                sb.append((char) ('a' + i));
                sb.append(counts[i]);
            }
        }
        String key = sb.toString();
        set.add(key);

        List<int[]> intslist = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!characterSet.contains(c)) {
                intslist.clear();
            } else {
                intslist.add(new int[26]);
                for (int[] ints:intslist) {
                    ints[c - 'a']++;
                }
                if (intslist.size() == plen) {
                    StringBuffer str = new StringBuffer();
                    int[] curr = intslist.get(0);
                    for (int j = 0; j < 26; j++) {
                        if (curr[j] != 0) {
                            str.append((char) ('a' + j));
                            str.append(curr[j]);
                        }
                    }
                    String strkey = str.toString();
                    if(set.contains(strkey)) {
                        list.add(i - plen + 1);
                    }
                    intslist.remove(0);

                }
            }
        }
        return list;
    }

    /**
     * 滑动窗口 + 左右索引指针
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams2(String s, String p) {
        char[] arrS = s.toCharArray();
        char[] arrP = p.toCharArray();

        // 接收最后返回的结果
        List<Integer> ans = new ArrayList<>();

        // 定义一个 needs 数组来看 arrP 中包含元素的个数
        int[] needs = new int[26];
        // 定义一个 window 数组来看滑动窗口中是否有 arrP 中的元素，并记录出现的个数
        int[] window = new int[26];

        // 先将 arrP 中的元素保存到 needs 数组中
        for (int i = 0; i < arrP.length; i++) {
            needs[arrP[i] - 'a'] += 1;
        }

        // 定义滑动窗口的两端
        int left = 0;
        int right = 0;

        // 右窗口开始不断向右移动
        while (right < arrS.length) {
            int curR = arrS[right] - 'a';
            right++;
            // 将右窗口当前访问到的元素 curR 个数加 1
            window[curR] += 1;

            // 当 window 数组中 curR 比 needs 数组中对应元素的个数要多的时候就该移动左窗口指针
            while (window[curR] > needs[curR]) {  //循环到curR位置前的异常数据恢复到正常 abb和bab(第一个删除a不够，还要循环一次删除b，第二个删除b就够了)
                int curL = arrS[left] - 'a';
                left++;
                // 将左窗口当前访问到的元素 curL 个数减 1
                window[curL] -= 1;
            }

            // 这里将所有符合要求的左窗口索引放入到了接收结果的 List 中
            if (right - left == arrP.length) {
                ans.add(left);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> list = findAnagrams2(s, p);
        System.out.println(1);
    }
}
