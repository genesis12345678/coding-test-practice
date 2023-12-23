package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        while (K-- > 0) {
            int c = Integer.parseInt(br.readLine());
            if (c == 0) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        int sum = 0;
        for (Integer num : stack) {
            sum += num;
        }
        System.out.println(sum);
    }
}
