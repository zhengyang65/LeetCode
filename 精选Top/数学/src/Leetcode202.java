import java.util.HashMap;

public class Leetcode202 {
    public static boolean isHappy(int n) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        while (!map.containsKey(n)) {
            if (n == 1) {
                return true;
            }
            map.put(n,true);
            int i = 0;
            while (n >= 10) {
                int d = n % 10;
                i += d * d;
                n /= 10;
            }
            i += n * n;
            n = i;
        }
        return false;
    }

    /**
     * 方法二:快慢指针
     * @param n
     * @return
     */
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }
}
