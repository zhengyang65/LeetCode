import java.util.ArrayList;
import java.util.List;

public class Leetcode118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        res.add(list1);
        if (numRows == 1) {
            return res;
        }
        //  res.add(new ArrayList<>(){{add(1); add(1);}});
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        res.add(list2);
        if (numRows == 2) {
            return res;
        }
        for (int i = 2; i < numRows; i++) {
            ArrayList<Integer> currlist = new ArrayList<>();
            currlist.add(1);
            List<Integer> list = res.get(i - 1);
            for (int j = 1; j < i; j++) {
                int num1 = list.get(j - 1);
                int num2 = list.get(j);
                currlist.add(num1 + num2);
            }
            currlist.add(1);
            res.add(currlist);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = generate(5);
        for (List<Integer> list:res) {
            for (int i:list) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
