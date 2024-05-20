import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[n + 1];
        ArrayList<Integer>[] A = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            inDegree[b]++;
        }

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!qu.isEmpty()) {
            int now = qu.poll();
            sb.append(now).append(" ");

            for (int next : A[now]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    qu.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}
