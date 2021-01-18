import java.util.*;

public class Leetcode39 {
    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(list,candidates,  0, target, 0);
        return res;
    }
    public static void combinationSum(List<Integer> list,int[] ints, int current, int target, int first) {
        if (current == target) {
            List<Integer> ans = new ArrayList<>(list);
            res.add(ans);
            return;
        }
        for (int i = first; i < ints.length; i++) {
            int sum = current + ints[i];
            if (sum <= target) {
                list.add(ints[i]);
                combinationSum(list, ints, sum, target, i);
                list.remove(list.size() - 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2,3,5};
        List<List<Integer>> res = combinationSum(ints, 8);
        for (List<Integer> list:res) {
            for (int i:list) {
                System.out.print(i+",");
            }
            System.out.println();
        }
    }
}
