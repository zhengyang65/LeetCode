import java.util.*;

public class Leetcode739 {
    public static int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return null;
        }
        int len = T.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        stack.push(T[0]);
        //Map<Integer,Integer> map = new HashMap<>();
        //map.put(0,1);
        Stack<Integer> index = new Stack<>();
        index.push(0);
        Stack<Integer> date = new Stack<>();
        date.push(0);
        for (int i = 1; i < len; i++) {
            int capacity = stack.size();
            for (int j = 0; j < capacity; j++) {
                int temp = stack.peek();
                if (T[i] > temp) {
                    res[index.pop()] = date.pop() + 1;
                    stack.pop();
                } else {
                    int cap = date.size();
                    int [] ints = new int[cap];
                    for (int k = 0; k < cap; k++) {
                        ints[k] = date.pop() + 1;
                    }
                    for (int k = cap - 1; k >= 0; k--) {
                        date.push(ints[k]);
                    }
                    break;
                }

            }
            stack.push(T[i]);
            index.push(i);
            date.push(0);
        }
        return res;
    }
    public static int[] dailyTemperatures2(int[] T) {
        if (T == null || T.length == 0) {
            return null;
        }
        int len = T.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < len; i++) {
            int capacity = stack.size();
            for (int j = 0; j < capacity; j++) {
                int index = stack.peek();
                if (T[i] > T[index]) {
                    res[stack.peek()] = i - stack.pop();
                }
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param T
     * @return
     */
    public int[] dailyTemperatures3(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{75,60,59,55,70,68,69,76};
        int[] res = dailyTemperatures2(ints);
        for (int i:res) {}

    }
}
