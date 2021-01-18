import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leetcode22 {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuffer s = new StringBuffer("((()))");
        res.add(s.toString());
        generateParenthesisHelper(n, s, res, 1, 0);
        return res;
    }
    public static void generateParenthesisHelper(int n, StringBuffer s,List<String> list, int first, int count) {
        if (count == n) {
            list.add(s.toString());
        }
        for (int i = first; i < n; i++) {
            generateParenthesisHelper(n, s, list, first, n);
            s.setCharAt(i, ')');
            //s.setCharAt();
            //s.replace(i, i+count,")");
            //s.insert(i,')');
            s.deleteCharAt(2 * n);
            generateParenthesisHelper(n, s, list, first + 2, 1);
            s.deleteCharAt(i);
            s.insert(n - 1, ')');
        }
    }

    /**
     * 回溯
     * 时间复杂度：O(4的n次方 / 根号n)在回溯过程中，每个答案需要 O(n)的时间复制到答案数组中。
     * 空间复杂度：O(n)，除了答案数组之外，我们所需要的空间取决于递归栈的深度，每一层递归函数需要 O(1)的空间，最多递归2n层
     * @param n
     * @return
     */
    public static List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public static void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 妙！
     */
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis3(int n) {
        if(n <= 0){
            return res;
        }
        getParenthesis("",n,n);
        return res;
    }

    private void getParenthesis(String str,int left, int right) {
        if(left == 0 && right == 0 ){
            res.add(str);
            return;
        }
        if(left == right){
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(str+"(",left-1,right);
        }else if(left < right){
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if(left > 0){
                getParenthesis(str+"(",left-1,right);
            }
            getParenthesis(str+")",left,right-1);
        }
    }

    public static void main(String[] args) {
        StringBuffer s2 = new StringBuffer("((()))");
        s2.replace(0,2,")))");

        List<String> res = generateParenthesis2(3);
        for (String s:res) {
            System.out.println(s);
        }
    }
}
