import java.util.HashSet;
import java.util.Set;

public class Leetcode217 {
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n:nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }

    /**
     * 直接add
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{1,2,3,4};
        System.out.println(containsDuplicate(ints));
    }

}
