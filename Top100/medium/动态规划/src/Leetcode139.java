import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode139 {

    /**
     * 时间复杂度：O(n^2)，其中 n为字符串 s的长度。我们一共有 O(n)个状态需要计算，每次计算需要枚举 O(n)个分割点，
     * 哈希表判断一个字符串是否出现在给定的字符串列表需要 O(1)的时间，因此总时间复杂度为 O(n^2)
     * 空间复杂度：O(n) ，我们需要 O(n)的空间存放 res[] 值以及哈希表亦需要 O(n)的空间复杂度，因此总空间复杂度为O(n)。
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] res = new boolean[len];
        //可省略。。。//
        String[] strings = new String[len];
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            sb.append(c);
            strings[i] = sb.toString();
        }
        //可省略。。。//
        for (int j = 0; j < len; j++) {
            String currtarget = strings[j];
            if (set.contains(currtarget)) {
                res[j] = true;
                continue;
            }
            for (int k = 0; k < j; k++) {
                if (res[k] && set.contains(currtarget.substring(k + 1))){
                    res[j] = true;
                    break;
                }
            }
        }
        return res[len - 1];
    }

    /**
     * 改进：
     * 1.不需要String[], 直接用substring
     * 2.
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) { // 子字符串范围为【j. i) 或【j, i -1】
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> list = List.of("cats", "dog", "sand", "and", "cat");
        boolean ans = wordBreak(s, list);
        System.out.println(ans);
    }
}
