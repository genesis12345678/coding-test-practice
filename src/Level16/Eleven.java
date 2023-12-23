package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Eleven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 4
        int[] arr = new int[N];
        Deque<Integer> deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 0 1 1 0 - [0, 1, 1, 0]
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken()); // 1, 2, 3, 4
            if (arr[i] == 0) {
                deque.addLast(num); // [1, 4]
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine()); // 3
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            deque.addFirst(Integer.parseInt(st.nextToken())); // 2, 4, 7
            sb.append(deque.pollLast()).append(" ");
        }
        System.out.println(sb);
    }
}
