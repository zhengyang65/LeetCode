package medium;

public class Leetcode134 {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int res = -1;
        int max = -1;
        int len = gas.length;
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += gas[i] - cost[i];
            if (sum > max) {
                max = sum;
                res = i;
            }
        }
        if (sum >= 0) {
            return res;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] gas = new int[]{1,2,3,4,5};
        int[] cost = new int[]{3,4,5,1,2};
        int ans = canCompleteCircuit(gas, cost);
        System.out.println(ans);
    }
}
