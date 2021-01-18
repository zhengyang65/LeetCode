import java.util.*;

public class Leetcode207 {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> studyied = new HashSet<>();
        for (int[] ints:prerequisites) {
            int numcourse = ints[0];
            int prepcourse = ints[1];
            if (map.containsKey(numcourse)) {
                Set<Integer> set = map.get(numcourse);
                set.add(prepcourse);
                map.put(numcourse, set);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(prepcourse);
                map.put(numcourse, set);
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                studyied.add(i);
            }else {
                Set<Integer> set =map.get(i);
                boolean flag = false;
                for (int n:set) {
                    if (studyied.contains(n) || !map.containsKey(n)) {
                        studyied.add(i);
                        studyied.add(n);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 方法一：深度优先搜索
     * 时间复杂度: O(n+m)，其中 n为课程数，m为先修课程的要求数。这其实就是对图进行深度优先搜索的时间复杂度。
     * 空间复杂度: O(n+m)。题目中是以列表形式给出的先修课程关系，为了对图进行深度优先搜索，
     * 我们需要存储成邻接表的形式，空间复杂度为 O(n+m)。
     * 在深度优先搜索的过程中，我们需要最多 O(n)的栈空间（递归）进行深度优先搜索，因此总空间复杂度为 O(n+m)。
     */
    static List<List<Integer>> edges;
    static int[] visited;
    static boolean valid = true;

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public static void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }


    /**
     * 方法二：广度优先搜索
     * 时间复杂度: O(n+m)，其中 n为课程数，m为先修课程的要求数。这其实就是对图进行深度优先搜索的时间复杂度。
     * 空间复杂度: O(n+m)。
     */
    List<List<Integer>> edges2;
    int[] indeg;

    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        edges2 = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges2.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges2.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges2.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }


    public static void main(String[] args) {
        boolean ans = canFinish2(3, new int[][]{{0,1}, {0,2}, {1,0}});
        System.out.println(ans);
    }
}
