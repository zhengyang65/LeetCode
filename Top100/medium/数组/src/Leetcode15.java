import java.util.*;

public class Leetcode15 {
        /** 方法：排序 + 双指针
         * 时间复杂度：O(N^2)，其中 N是数组 nums 的长度。
         * 空间复杂度：O(logN)。我们忽略存储答案的空间，额外的排序的空间复杂度为 O(logN)。然而我们修改了输入的数组 nums，
         * 在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 nums 的副本并进行排序，空间复杂度为 O(N)。

         * @param nums
         * @return
         */
        public static List<List<Integer>> threeSum(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            // 枚举 a
            for (int first = 0; first < n; ++first) {
                // 需要和上一次枚举的数不相同
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                // c 对应的指针初始指向数组的最右端
                int third = n - 1;
                int target = -nums[first];
                // 枚举 b
                for (int second = first + 1; second < n; ++second) {
                    // 需要和上一次枚举的数不相同
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    // 需要保证 b 的指针在 c 的指针的左侧
                    while (second < third && nums[second] + nums[third] > target) {
                        --third;
                    }
                    // 如果指针重合，随着 b 后续的增加
                    // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                    if (second == third) {
                        break;
                    }
                    if (nums[second] + nums[third] == target) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        ans.add(list);
                    }
                }
            }
            return ans;
        }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 3) {
            return list;
        }
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        Map<Integer, Integer> set = new HashMap<>();
        //初始化
        List<List<Integer>> list11 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(nums[0]);
        list1.add(nums[1]);
        list11.add(list1);
        map.put(nums[0] + nums[1], list11);
        set.put(nums[0], 1);
        set.put(nums[1], set.getOrDefault(nums[1], 0) + 1);
        for (int i = 2; i < nums.length; i++) {
            int num = nums[i];
            if (set.containsKey(num) && set.get(num) >= 3) {
                continue;
            } else {
                set.put(num, set.getOrDefault(num, 0) + 1);
            }
            if (map.containsKey(-num)) {
                for (List<Integer> l:map.remove(-num)) {
                    l.add(num);
                    list.add(l);
                }
                for (int j = 0; j < i; j++) {
                    int sum = nums[j] + num;
                    if (set.containsKey(-sum)) {
                        continue;
                    }
                    if (!map.containsKey(sum)) {
                        List<List<Integer>> lists = new ArrayList<>();
                        List<Integer> curr = new ArrayList<>();
                        curr.add(nums[j]);
                        curr.add(num);
                        lists.add(curr);
                        map.put(sum, lists);
                        continue;
                    }
                    boolean flag = true;
                    for (List<Integer> l : map.get(sum)) {
                        if (l.contains(num)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        map.get(sum).add(new ArrayList<>() {{
                            add(sum - num);
                            add(num);
                        }});
                    }
                }
            } else {
                for (int j = 0; j < i; j++) {
                    int sum =  nums[j] + num;
                    if (!map.containsKey(sum)) {
                        List<List<Integer>> lists = new ArrayList<>();
                        List<Integer> curr = new ArrayList<>();
                        curr.add(nums[j]);
                        curr.add(num);
                        lists.add(curr);
                        map.put(sum, lists);
                        continue;
                    }
                    boolean flag = true;
                    for (List<Integer> l:map.get(sum)) {
                        if (l.contains(num)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        //List<Integer> curr = new ArrayList<>();
                        //curr.add(nums[j]);
                        //curr.add(num);
                        //List<List<Integer>> listList = map.get(sum);
                        //listList.add(curr);
                        //map.put(sum, listList);
                        map.get(sum).add(new ArrayList<>(){{add(sum - num); add(num);}});
                    }
                }
            }

        }
        return list;
    }


    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 3) {
            return list;
        }
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        Map<Integer, Integer> set = new HashMap<>();
        //初始化
        List<List<Integer>> list11 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(nums[0]);
        list1.add(nums[1]);
        list11.add(list1);
        map.put(nums[0] + nums[1], list11);
        set.put(nums[0], 1);
        set.put(nums[1], set.getOrDefault(nums[1], 0) + 1);
        for (int i = 2; i < nums.length; i++) {
            int num = nums[i];
            if (set.containsKey(num) && set.get(num) >= 3) {
                continue;
            } else {
                set.put(num, set.getOrDefault(num, 0) + 1);
            }
            if (map.containsKey(-num)) {
                for (List<Integer> l:map.remove(-num)) {
                    l.add(num);
                    list.add(l);
                }

            }
            for (int j = 0; j < i; j++) {
                int sum =  nums[j] + num;
                if (set.containsKey(-sum)) {
                    continue;
                }
                if (!map.containsKey(sum)) {
                    List<List<Integer>> lists = new ArrayList<>();
                    List<Integer> curr = new ArrayList<>();
                    curr.add(nums[j]);
                    curr.add(num);
                    lists.add(curr);
                    map.put(sum, lists);
                    continue;
                }
                boolean flag = true;
                for (List<Integer> l:map.get(sum)) {
                    if (l.contains(num)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    map.get(sum).add(new ArrayList<>(){{add(sum - num); add(num);}});
                }
            }
        }
        return list;
    }
    public static void main(String[] args) {
        int[] ints = new int[]{0, 0, 0, 0, 1, 1};
        List<List<Integer>> ans = threeSum(ints);
        System.out.println(1);
    }
}
