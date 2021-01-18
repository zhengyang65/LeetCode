import java.util.*;

public class Leetcode621 {

    public static int leastInterval(char[] tasks, int n) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (char c:tasks) {
            map.put((int) c, map.getOrDefault((int) c, 0) + 1);
        }
        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry:map.entrySet()) {
            list.add(new int[]{entry.getKey(), entry.getValue()});

        }

        while (!list.isEmpty()) {
            list.sort((o1, o2) -> o2[1] - o1[1]);
            int i = 0;
            int size = list.size();
            while (!list.isEmpty() && i <= n) {
                res += 1;
                i++;
                if (i > size) {
                    continue;
                }
                int[] maxtask = list.remove(0);
                if (maxtask[1] > 1) {
                    maxtask[1] -= 1;
                    list.add(maxtask);
                }
            }
        }
        return res;
    }

    /**
     * 方法二：构造
     * 时间复杂度：O(∣task∣+∣Σ∣)，其中 ∣Σ∣是数组 task中出现任务的种类，在本题中任务用大写字母表示，因此 ∣Σ∣ 不会超过 26。
     * 空间复杂度：O(|Σ∣)。
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

    public static void main(String[] args) {
        char[] chars = new char[]{'A','A','A','A','A','B','B','C','D','E','F','G'};
        int ans = leastInterval(chars, 2);
        System.out.println(ans);
    }
}
