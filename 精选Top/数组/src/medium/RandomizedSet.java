package medium;

import java.util.*;

public class RandomizedSet {
    Map<Integer, List<Integer>> map;
    List<List<Integer>> list;
    Random r;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new LinkedHashMap<>();
        list = new LinkedList<>();
        r = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        List<Integer> list1 = List.of(val);
        map.put(val, list1);
        list.add(list1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            List<Integer> relist = map.remove(val);
            list.remove(relist);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = r.nextInt(list.size());
        return list.get(index).get(0);
    }

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(1);
        boolean param_2 = obj.remove(2);
        boolean param_3 = obj.insert(2);
        int param_4 = obj.getRandom();
        boolean param_5 = obj.remove(1);
        boolean param_6 = obj.insert(2);
        int param_7 = obj.getRandom();
        System.out.println(1);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

