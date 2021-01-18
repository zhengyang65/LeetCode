import java.util.*;

public class Leetcode347 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[k];
        int[] kcount = new int[k];
        Arrays.sort(nums);
        int i = 0;
        int num = 0;
        while (num < k ) {
            int count = 0;
            int curr=nums[i];
            res[num] = curr;
            while (nums[i] == curr) {
                count += 1;
                i++;
            }
            kcount[num] = count;
            num += 1;
        }
        sort(res, kcount);
        while (i < nums.length) {

        }
        return res;
    }
    public void sort(int[] ints1, int[] ints2) {
        int [] temp = ints2.clone();
        Arrays.sort(temp);
    }

    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i:nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            map.put(i,1);
        }
        return new int[0];
    }

    /**
     * 1° 首先遍历整个数组，并使用哈希表记录每个数字出现的次数。
     * 2° 建立一个小顶堆，然后遍历「出现次数数组」：
     *    1） 如果堆的元素个数小于 k，就可以直接插入堆中。
     *    2） 如果堆的元素个数等于 k，则检查堆顶与当前出现次数的大小。如果堆顶更大，
     *    说明至少有 k 个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }


    public int[] topKFrequent4(int[] nums, int k) {
        int[] res = new int[k];    // 结果数组
        Map<Integer, Integer> map = new HashMap();
        // 统计数组中各元素出现的次数
        for(int num : nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else{
                map.put(num, 1);
            }
        }

        int maxTimes = 0;    // 出现最多的元素的出现次数
        // 找出出现次数最多的元素出现的次数
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > maxTimes){
                maxTimes = entry.getValue();
            }
        }

        // 按出现次数从大到小添加到结果数组
        while(k > 0){
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                if(entry.getValue() == maxTimes){
                    res[k - 1] = entry.getKey();
                    k--;
                }
            }
            maxTimes--; //妙
        }

        return res;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{1,1,3,3,3,2};
        topKFrequent3(ints,2);
    }
}
