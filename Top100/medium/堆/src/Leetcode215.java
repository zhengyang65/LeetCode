public class Leetcode215 {
    static int N = 0;
    public static int findKthLargest(int[] nums, int k) {
        N = nums.length - 1;
        for (int i = (N - 1) / 2; i >= 0; i--) {
            sink(nums, i);
        }
        while (N > 0) {
            exch(nums,0, N--);
            sink(nums, 0);
        }
        return nums[k - 1];
    }
    public static void swim(int[] nums, int k) {
        while (k > 1 && nums[(k - 1) / 2] < nums[k]) {
            exch(nums, (k - 1) / 2, k);
            k = (k -1) / 2;
        }
    }
    public static void sink(int[] nums, int k) {
        while (2 * k + 1 <= N) {
            int j =2 * k + 1;
            if (j < N && nums[j] > nums[j+1]) {
                j++;
            }
            if (nums[k] <= nums[j]) {
                break;
            }
            exch(nums, k, j);
            k = j;
        }
    }
    public static void exch(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    public int findKthLargest2(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{3,2,3,1,2,4,5,5,6};
        int res = findKthLargest(ints, 4);
        System.out.println(res);
    }
}
