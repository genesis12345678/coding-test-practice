package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    que.offer(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(!que.isEmpty() ? que.poll() : -1).append("\n");
                    break;
                case "size":
                    sb.append(que.size()).append("\n");
                    break;
                case "empty":
                    sb.append(que.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(!que.isEmpty() ? que.peek() : -1).append("\n");
                    break;
                case "back":
                    sb.append(!que.isEmpty() ? que.peekLast() : -1).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
