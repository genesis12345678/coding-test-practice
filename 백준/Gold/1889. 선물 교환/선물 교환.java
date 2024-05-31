import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] inDegree = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        ArrayList<Integer>[] arr = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());

            inDegree[s1]++;
            inDegree[s2]++;
            arr[i].add(s1);
            arr[i].add(s2);
        }

        PriorityQueue<Integer> qu = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] < 2) {
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int now = qu.poll();

            if (visit[now]) {
                continue;
            }
            visit[now] = true;

            for (int next : arr[now]) {
                inDegree[next]--;
                if (inDegree[next] < 2) {
                    qu.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 2) {
                sb.append(i).append(" ");
                count++;
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }
}
