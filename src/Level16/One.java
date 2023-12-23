package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "1":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case "2":
                    if(!stack.isEmpty()) {
                        sb.append(stack.pop()).append('\n');
                    }else
                        sb.append(-1).append('\n');
                    break;
                case "3":
                    sb.append(stack.size()).append('\n');
                    break;
                case "4":
                    sb.append(stack.isEmpty() ? 1 : 0).append('\n');
                    break;
                case "5":
                    if(!stack.isEmpty()) {
                        sb.append(stack.peek()).append('\n');
                    }else
                        sb.append(-1).append('\n');
                    break;
            }

        }
        System.out.println(sb);
    }
}
