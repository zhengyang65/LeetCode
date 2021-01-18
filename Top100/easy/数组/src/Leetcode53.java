public class Leetcode53 {
    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int currsum = 0;
        for (int num:nums) {
            currsum += num;
            if (currsum < num){
                currsum = num;
            }
            if (currsum >= result) {
                result = currsum;
            }
        }
        return result;
    }

}
