import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] A = new ArrayList[n + 1];
        int[] inDegree = new int[n + 1];
        int[] time = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            A[i] = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int t = Integer.parseInt(st.nextToken());

            for (int j = 0; j < t; j++) {
                int temp = Integer.parseInt(st.nextToken());
                A[temp].add(i);

                inDegree[i]++;
            }

        }

        int[] result = new int[n + 1];
        Queue<Integer> qu = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : A[now]) {
                inDegree[next]--;

                result[next] = Math.max(result[next], result[now] + time[now]);
                if (inDegree[next] == 0) {
                    qu.offer(next);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            result[i] += time[i];
        }

        int max = Arrays.stream(result).max().getAsInt();
        System.out.println(max);
    }
}
