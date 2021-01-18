import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Leetcode279 {
    /**
     * 和方法三差别在没有用一个set保存每一层的剩余的数
     * 例如 13 - 1、13 - 4、 13 - 9
     */
    public static int res = Integer.MAX_VALUE;
    public static int sqrmax;
    public static int target;
    public static int numSquares(int n) {
        target = n;
        sqrmax = (int)Math.sqrt(n);
        bfs(0, 0);
        return res;
    }
    public static void bfs(int sum, int times) {
        if (times >= res || sum > target) {
            return;
        }
        if (sum == target) {
            if (times < res) {
                res = times;
            }
            return;
        }
        for (int i = sqrmax; i > 0; i--) {
            bfs(sum + i * i, times + 1);
        }
    }

    /**
     *方法二：贪心枚举
     */
    class Solution2 {
        Set<Integer> square_nums = new HashSet<Integer>();

        protected boolean is_divided_by(int n, int count) {
            if (count == 1) {
                return square_nums.contains(n);
            }

            for (Integer square : square_nums) {
                if (is_divided_by(n - square, count - 1)) {
                    return true;
                }
            }
            return false;
        }

        public int numSquares(int n) {
            this.square_nums.clear();

            for (int i = 1; i * i <= n; ++i) {
                this.square_nums.add(i * i);
            }

            int count = 1;
            for (; count <= n; ++count) {
                if (is_divided_by(n, count))
                    return count;
            }
            return count;
        }
    }


    /**
     * 贪心+BFS
     * 注意：在典型的 BFS 算法中，queue 变量通常是数组或列表类型。
     * 但是，这里我们使用 set 类型，以消除同一级别中的剩余项的冗余。事实证明，这个小技巧甚至可以增加 5 倍的运行加速。
     *
     */
    class Solution3 {
        public int numSquares(int n) {

            ArrayList<Integer> square_nums = new ArrayList<Integer>();
            for (int i = 1; i * i <= n; ++i) {
                square_nums.add(i * i);
            }

            Set<Integer> queue = new HashSet<Integer>();
            queue.add(n);

            int level = 0;
            while (queue.size() > 0) {
                level += 1;
                Set<Integer> next_queue = new HashSet<Integer>();

                for (Integer remainder : queue) {
                    for (Integer square : square_nums) {
                        if (remainder.equals(square)) {
                            return level;
                        } else if (remainder < square) {
                            break;
                        } else {
                            next_queue.add(remainder - square);
                        }
                    }
                }
                queue = next_queue;
            }
            return level;
        }
    }

    public static void main(String[] args) {
        int input = 13;
        int ans =numSquares(input);
        System.out.println(ans);
    }
}
