import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[n + 1];
        ArrayList<Integer>[] A = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            inDegree[b]++;
        }

        Queue<Integer> qu = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        int[] result = new int[n + 1];
        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : A[now]) {
                inDegree[next]--;

                result[next] = Math.max(result[next], result[now] + 1);
                if (inDegree[next] == 0) {
                    qu.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(++result[i] + " ");
        }
    }
}
