package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String value = br.readLine();
            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < value.length(); j++) {
                if (value.charAt(j) == '(') {
                    stack.push(value.charAt(j));
                } else {
                    if (stack.isEmpty()) {
                        stack.push(value.charAt(j));
                        break;
                    }else stack.pop();
                }
            }
            if (stack.isEmpty()) {
                sb.append("YES").append('\n');
            } else {
                sb.append("NO").append('\n');
            }
        }
        System.out.println(sb);
    }
}
