package medium;

import java.util.*;

public class Leetcode227 {
    /**
     * 1.0不正确解法
     * @param s
     * @return
     */
    public static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = new char[s.length()];
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                chars[len++] = c;
            }
        }

        Stack<Integer> stack = new Stack<>();
        Stack<Character> charStack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            switch (c){
                case '+', '-' :
                    charStack.push(c);
                    break;
                case '*' :
                    stack.push(stack.pop() * Character.getNumericValue(chars[++i]));
                    break;
                case '/' :
                    stack.push(stack.pop() / Character.getNumericValue(chars[++i]));
                    break;
                default :
                    StringBuilder sb = new StringBuilder();
                    while (i < len) {
                            c = chars[i];
                            if (c >= '0' && c<= '9') {
                                sb.append(c);
                                i++;
                            } else {
                                break;
                            }
                        }
                    i--;
                    stack.push(Integer.parseInt(sb.toString()));
                }
            }

        int k = 0;int j = 0;
        int num1 = stack.get(k++);
        while (j < charStack.size() && k < stack.size()) {
            int num2 = stack.get(k++);
            char c = charStack.get(j++);
            switch (c) {
                case '+' -> num1 += num2;
                case '-' -> num1 -= num2;
            }
        }
        return num1;
    }

    /**
     * 2.0解法，已通过，但性能不佳
     * @param s
     * @return
     */
    public static int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = new char[s.length()];
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                chars[len++] = c;
            }
        }

        List<Integer> stack = new ArrayList<>();
        List<Character> charStack = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            switch (c){
                case '+', '-', '*', '/' :
                    charStack.add(c);
                    break;
                default :
                    StringBuilder sb = new StringBuilder();
                    while (i < len) {
                        c = chars[i];
                        if (c >= '0' && c<= '9') {
                            sb.append(c);
                            i++;
                        } else {
                            break;
                        }
                    }
                    i--;
                    stack.add(Integer.parseInt(sb.toString()));
            }
        }

        int k = 0;int j = 0;
        int num;
        while (j < charStack.size() && k < stack.size()) {
            char c = charStack.get(j);
            switch (c) {
                case '*' :
                    num = stack.remove(k);
                    stack.set(k, num * stack.get(k));
                    charStack.remove(j);
                    break;
                case '/' :
                    num = stack.remove(k);
                    stack.set(k, num / stack.get(k));
                    charStack.remove(j);
                    break;
                default:
                    k++;
                    j++;
            }
        }

        int res = stack.remove(0);
        while (!charStack.isEmpty() && !stack.isEmpty()) {
            int curr = stack.remove(0);
            char c = charStack.remove(0);
            if (c == '+') {
                res += curr;
            } else {
                res -= curr;
            }

        }
        return res;
    }

    /**
     * 别人解法
     * @param s
     * @return
     */
    public static int calculate3(String s) {
        /*
            将 减法、乘法、除法 转换为 加法
            某个数 num, 如果前面的对应的运算符是 -，那么 将 -num 压入栈中
            这样，我们只需在最后将栈的元素全部弹出，完成加法操作，即可得到最终结果

            对于括号，它存在递归性质
            即
            3 * (2 + 4 * 3) + 2
          = 3 * calculate(2 + 4 * 3) + 2
          = 3 * 24 + 2
          即我们可以将括号内的字符串当作一个运算式，再递归调用本函数，最终返回一个数值
        */
        int[] i = new int[1];
        return dfs(s, i);
    }
    private static int dfs(String s, int[] i){
        Deque<Integer> stack = new LinkedList<>();

        //记录某个连续的数，比如 "42"，那么我们首先 num = 4，然后遇到 2 ,num = num * 10 + 2 = 42
        int num = 0;
        char op = '+';
        for(; i[0] < s.length(); i[0]++){
            char ch = s.charAt(i[0]);

            //遇到左括号，递归运算内部子式
            if(ch == '('){
                ++i[0];
                num = dfs(s, i);
            }

            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }
            //不是数字，不是空格（运算符 或 '(' 或 ')' ） 或者 到了最后一个字符，那么根据前面记录的 op 操作符 将数字压栈，然后将新的运算符 ch 赋值给 op
            if(!Character.isDigit(ch) && ch != ' ' || i[0] == s.length() - 1){
                switch(op){
                    case '+':
                        stack.push(num); break;
                    case '-':
                        stack.push(-num); break;
                    case '*':
                        int pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }
                num = 0;
                op = ch;
            }
            /*
            遇到右括号，退出循环，然后计算结果， 返回上一层 dfs
            这一步写在最后是因为，当 ch 为 右括号 时，那么我们需要先将前面已经得到的 num 压入栈中，再退出循环
            */
            if(ch == ')'){
                break;
            }
        }
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
    public static void main(String[] args) {
        long startTime =  System.currentTimeMillis();
        String test = "1*2-3/4+5*6-7*8+9/10";
        int ans = calculate2(test);
        long endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime);
        System.out.println(usedTime);
    }
}
