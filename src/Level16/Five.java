package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> que = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            que.offer(Integer.parseInt(st.nextToken()));
        }
        int start = 1;

        while (!que.isEmpty()) {
            if (que.peek() == start) {
                start++;
                que.poll();
            } else if (!stack.isEmpty() && stack.peek() == start) {
                start++;
                stack.pop();
            } else stack.push(que.poll());
        }

        while (!stack.isEmpty()) {
            if (stack.peek() != start) {
                System.out.println("Sad");
                return;
            } else {
                stack.pop();
                start++;
            }
        }
        System.out.println("Nice");
    }
}
