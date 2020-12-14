public class Leetcode461 {
    public int hammingDistance(int x, int y) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (x == 0 & y == 0) {
                break;
            }
            if (x % 2 != y % 2){
                count += 1;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return count;
    }

    /**
     * 异或
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance2(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1){
                distance += 1;
            xor = xor >> 1;
            }
        }
        return distance;
    }

}
