package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 선행 되어야 하는 작업이 있으므로 위상 정렬 문제라는 것을 알 수 있다.
 * 위상 정렬을 수행하면서 각 작업을 수행하는 데 필요한 최대 시간을 업데이트 한다.
 * 그 후 각각 자신에게 필요한 시간을 더해준다.
 * 모든 작업을 완료해야 하기 때문에 최소 가장 오래 걸리는 작업이 끝나야 모든 작업을 완료할 수 있다.
 * 즉, 정답은 결과 배열에서 최댓값
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2056">백준 2056번 - 위상 정렬 : 작업</a>
 * @since 2024-04-23
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] A = new ArrayList[n + 1];
        int[] inDegree = new int[n + 1];
        int[] time = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {

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
