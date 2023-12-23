package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Deque<int[]> deque = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb.append("1 ");
        int move = Integer.parseInt(st.nextToken());

        for (int i = 2; i <= N; i++) {
            deque.add(new int[]{i, Integer.parseInt(st.nextToken())});
        }

        while (!deque.isEmpty()) {
            if (move > 0) {
                for (int i = 0; i < move - 1; i++) {
                    deque.addLast(deque.pollFirst());
                }
                int[] next = deque.removeFirst();
                move = next[1];
                sb.append(next[0]).append(" ");
            } else {
                for (int i = move + 1; i < 0; i++) {
                    deque.addFirst(deque.pollLast());
                }
                int[] next = deque.removeLast();
                move = next[1];
                sb.append(next[0]).append(" ");
            }
        }
        System.out.println(sb);
    }
}
