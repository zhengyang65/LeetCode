import java.util.Stack;

public class Leetcode20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char s1 = s.charAt(i);
            if ( s1 == '(' | s1 == '[' | s1 == '{') {
                stack.push(s1);
            } else if (stack.isEmpty()) {
                return false;
            } else if ( s1 == ')') {
                if (stack.pop() != '(') {
                    return false;
                }
            } else if ( s1 == ']') {
                if (stack.pop() != '[') {
                    return false;
                }
            } else if ( s1 == '}') {
                if (stack.pop() != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
