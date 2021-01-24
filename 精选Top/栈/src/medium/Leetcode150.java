package medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.Stack;

public class Leetcode150 {
    public static int evalRPN(String[] tokens) {
        //Deque<Integer> stack = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        for (String s:tokens) {
            char c = s.charAt(0);
            if (s.length() == 1 && (c =='+' || c =='-' || c =='*' || c =='/') ) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                if (c =='+') {
                    stack.push(num1 + num2);
                } else if (c =='-') {
                    stack.push(num1 - num2);
                } else if ( c =='*') {
                    stack.push(num1 * num2);
                } else {
                    stack.push(num1 / num2);
                }

            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    /**
     * 方法二：纯数组模拟栈实现(推荐)   3 ms	38.3 MB
     * 1. 数组最大长度为tokens.length / 2 + 1
     * 2. switch代替if-else，效率优化
     * 3. Integer.parseInt代替Integer.valueOf,减少自动拆箱装箱操作
     *
     * @param tokens
     * @return
     */
    public static int evalRPN2(String[] tokens) {
        int[] numStack = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String s : tokens) {
            // numStack[index++] = Integer.valueOf(s);
            //valueOf改为parseInt，减少自动拆箱装箱操作
            switch (s) {
                case "+" -> numStack[index - 2] += numStack[--index];
                case "-" -> numStack[index - 2] -= numStack[--index];
                case "*" -> numStack[index - 2] *= numStack[--index];
                case "/" -> numStack[index - 2] /= numStack[--index];
                default -> numStack[index++] = Integer.parseInt(s);
            }
        }
        return numStack[0];
    }

    public static void main(String[] args) {
        String[] test = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int ans = evalRPN(test);
        System.out.println(ans);
    }
}
