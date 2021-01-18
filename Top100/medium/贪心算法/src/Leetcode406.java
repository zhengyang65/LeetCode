import java.util.*;

public class Leetcode406 {
    public static int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, (o1, o2) -> {
        if (o1[1] != o2[1]) {
            return o1[1] - o2[1];
        }
        return o1[0] - o2[0];
    });
    for (int i = 0; i < people.length; i++) {
        int count = 0;
        while (i - people[i][1] > 0) {
            count++;
        }
    }
    return people;
    }

    /**
     * 方法一：从低到高考虑
     * 思路：按照 hi 为第一关键字升序，ki为第二关键字降序进行排序
     * 第 i个人的位置，就是队列中从左往右数第 ki+1个「空」位置。
     *
     * 时间复杂度：O(n^2)，其中 n是数组people 的长度。我们需要O(nlogn) 的时间进行排序，
     * 随后需要 O(n^2)的时间遍历每一个人并将他们放入队列中。由于前者在渐近意义下小于后者，因此总时间复杂度为 O(n^2)
     * 空间复杂度：O(logn)，即为排序需要使用的栈空间。
     * @param people
     * @return
     */
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：从高到低考虑
     * 我们可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。
     * 也就是说，当我们放入第 i个人时，只需要将其插入队列中，使得他的前面恰好有 ki个人即可。
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(logn)
     * @param people
     * @return
     */
    public static int[][] reconstructQueue3(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        System.out.println();
        a = reconstructQueue3(a);
        System.out.println();
    }
}
