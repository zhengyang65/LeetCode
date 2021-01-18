import java.util.Stack;

public class Leetcode394 {
    static int i = 0;
    public static String decodeString(String s) {
        int len = s.length();
        Stack<String> stringStack = new Stack<>();
        Stack<Integer> integerStack = new Stack<>();
        while(i < len) {
            char c = s.charAt(i);
            while (c != ']') {
                if (Character.isDigit(c)) {
                    String num = getDigits(s);
                    //integerStack.push((Character.getNumericValue(c)));
                    integerStack.push(Integer.valueOf(num));
                } else {
                    stringStack.push(String.valueOf(c));
                }
                i++;
                if (i >= len) {
                    break;
                }
                c = s.charAt(i);
            }
            i++;

            String curr = "";
            while (!stringStack.isEmpty() && stringStack.peek().charAt(0) != '[') {
                curr = stringStack.pop() + curr;
            }
            if (!stringStack.isEmpty()) {
                stringStack.pop();
            }
            int num = 1;
            if (!integerStack.isEmpty()) {
                num = integerStack.pop();
            }
            String sum = "";
            for (int j = 0; j < num; j++) {
                sum += curr;
            }
            stringStack.push(sum);
        }
        String res = "";
        while (!stringStack.isEmpty()) {
            res = stringStack.pop() + res;
        }
        return res;
    }

    public static String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(i))) {
            ret.append(s.charAt(i++));
        }
        return ret.toString();
    }

    public static String decodeString2(String s) {
        StringBuffer ans = new StringBuffer();
        Stack<Integer> multiStack=new Stack<>();
        Stack<StringBuffer> ansStack=new Stack<>();
        int multi=0;
        for(char c:s.toCharArray()){
            if(Character.isDigit(c))multi=multi*10+c-'0';
            else if(c=='['){
                ansStack.add(ans);
                multiStack.add(multi);
                ans=new StringBuffer();
                multi=0;
            }else if(Character.isAlphabetic(c)){
                ans.append(c);
            }else{
                StringBuffer ansTmp=ansStack.pop();
                int tmp=multiStack.pop();
                for(int i=0;i<tmp;i++)ansTmp.append(ans);
                ans=ansTmp;
            }
        }
        return ans.toString();
    }
    public static void main(String[] args) {
        String s = "3[a2[bc]]ef]";
        String string = decodeString2(s);
        System.out.println(string);
    }
}
