public class Leetcode69 {
    public static int mySqrt(int x) {
        double g=x;
        while(Math.abs(g*g-x)>0.000001)
        {
            g=(g+x/g)/2;
        }
        return (int)g;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }

}
