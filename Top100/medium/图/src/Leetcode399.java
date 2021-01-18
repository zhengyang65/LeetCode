import javax.management.ListenerNotFoundException;
import java.util.*;

public class Leetcode399 {
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        int len = equations.size();
        Map<String, Double> map = new HashMap<>();
        //Map<List<>, >
        for (int i = 0; i < len; i++) {
            List<String> list = equations.get(i);
            String s1 = list.get(0);
            String s2 = list.get(1);
            if (map.containsKey(s1) && map.containsKey(s2)) {
                continue;
            } else if (!map.containsKey(s1) && !map.containsKey(s2)) {
                map.put(s1, values[i]);
                map.put(s2, 1.0);
            } else if (map.containsKey(s2)) {
                //int num = (int) (map.get(s2) * values[i]);
                //map.put(s1, (double) num);
                map.put(s1, map.get(s2) * values[i]);
            } else {
                //int num = (int) (map.get(s1) / values[i]);
                //map.put(s2, (double) num);
                map.put(s2, map.get(s1) / values[i]);
            }
        }
        int index = 0;
        for (List<String> list:queries) {
            String s1 = list.get(0);
            String s2 = list.get(1);
            if (map.containsKey(s1) && map.containsKey(s2)) {
                res[index] = map.get(s1) / map.get(s2);
            } else {
                res[index] = -1.0;
            }
            index++;
        }
        return res;
    }

    public static double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        int len = equations.size();
        Map<String[], Double> map = new HashMap<>();
        Map<String, String[]> stringMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            List<String> list = equations.get(i);
            String s1 = list.get(0);
            String s2 = list.get(1);
            String[] str = new String[]{s1, s2};
            //String[] str2 = new String[]{s2, s1};
            map.put(str, values[i]);
            //map.put(str2, 1 / values[i]);
            stringMap.put(s1, str);
            stringMap.put(s2, str);

            int mult = 1;
            while (stringMap.containsKey(s1)) {
                String[] strings = stringMap.get(s1);
                mult *= map.get(strings);
                s1 = strings[1];
            }
        }
        return res;
    }

    /**
     * 方法一：广度优先搜索
     * 时间复杂度：O(ML+Q⋅(L+M))，其中 M为边的数量，Q为询问的数量，L为字符串的平均长度。
     * 构建图时，需要处理 M条边，每条边都涉及到 O(L)的字符串比较；
     * 处理查询时，每次查询首先要进行一次 O(L)的比较，然后至多遍历 O(M)条边。
     *
     * 空间复杂度：O(NL+M)，其中 N为点的数量，M为边的数量，L为字符串的平均长度。
     * 为了将每个字符串映射到整数，需要开辟空间为 O(NL)的哈希表；
     * 随后，需要花费 O(M)的空间存储每条边的权重；处理查询时，还需要 O(N)的空间维护访问队列。
     * 最终，总的复杂度为 O(NL+M+N) =O(NL+M)
     */
    class Solution1 {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            int nvars = 0;
            Map<String, Integer> variables = new HashMap<String, Integer>();

            int n = equations.size();
            for (int i = 0; i < n; i++) {
                if (!variables.containsKey(equations.get(i).get(0))) {
                    variables.put(equations.get(i).get(0), nvars++);
                }
                if (!variables.containsKey(equations.get(i).get(1))) {
                    variables.put(equations.get(i).get(1), nvars++);
                }
            }

            // 对于每个点，存储其直接连接到的所有点及对应的权值
            List<Pair>[] edges = new List[nvars];
            for (int i = 0; i < nvars; i++) {
                edges[i] = new ArrayList<Pair>();
            }
            for (int i = 0; i < n; i++) {
                int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
                edges[va].add(new Pair(vb, values[i]));
                edges[vb].add(new Pair(va, 1.0 / values[i]));
            }

            int queriesCount = queries.size();
            double[] ret = new double[queriesCount];
            for (int i = 0; i < queriesCount; i++) {
                List<String> query = queries.get(i);
                double result = -1.0;
                if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                    int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                    if (ia == ib) {
                        result = 1.0;
                    } else {
                        Queue<Integer> points = new LinkedList<Integer>();
                        points.offer(ia);
                        double[] ratios = new double[nvars];
                        Arrays.fill(ratios, -1.0);
                        ratios[ia] = 1.0;

                        while (!points.isEmpty() && ratios[ib] < 0) {
                            int x = points.poll();
                            for (Pair pair : edges[x]) {
                                int y = pair.index;
                                double val = pair.value;
                                if (ratios[y] < 0) {
                                    ratios[y] = ratios[x] * val;
                                    points.offer(y);
                                }
                            }
                        }
                        result = ratios[ib];
                    }
                }
                ret[i] = result;
            }
            return ret;
        }
    }

    class Pair {
        int index;
        double value;

        Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }


    /**
     * 方法二：带权并查集
     * 时间复杂度：O(ML+N+MlogN+Q⋅(L+logN))。构建图需要 O(ML) 的时间；
     * 初始化并查集需要 O(N) 的初始化时间；构建并查集的单次操作复杂度为 O(logN)，共需 O(MlogN) 的时间；
     * 每个查询需要 O(L)的字符串比较以及 O(logN) 的查询。
     *
     * 空间复杂度：O(NL)。哈希表需要O(NL) 的空间，并查集需要 O(N) 的空间。
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public static double[] calcEquation3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }

        //初始化 索引 和 权值
        int[] f = new int[nvars];
        double[] w = new double[nvars];
        Arrays.fill(w, 1.0);
        for (int i = 0; i < nvars; i++) {
            f[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            merge(f, w, va, vb, values[i]);
        }
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                int fa = findf(f, w, ia), fb = findf(f, w, ib);
                if (fa == fb) {
                    result = w[ia] / w[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    public static void merge(int[] f, double[] w, int x, int y, double val) {
        int fx = findf(f, w, x);
        int fy = findf(f, w, y);
        f[fx] = fy;
        w[fx] = val * w[y] / w[x];
    }

    public static int findf(int[] f, double[] w, int x) {
        if (f[x] != x) {
            int father = findf(f, w, f[x]);
            w[x] = w[x] * w[f[x]];
            f[x] = father;
        }
        return f[x];
    }


    public static void main(String[] args) {
        List<List<String>> test = List.of(List.of("a", "b"), List.of("e", "f"), List.of("b", "e"));
        double[] val = new double[]{3.4, 1.4, 2.3};
        List<List<String>> questions = List.of(List.of("b","a"),List.of("a","f"),List.of("f","f"),
                List.of("e","e"),List.of("c","c"), List.of("a", "c"), List.of("f", "e"));
        double[] ans = calcEquation3(test, val, questions);
        System.out.println(1);

    }
}
