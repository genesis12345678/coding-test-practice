package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2623">백준 2623번 - 위상 정렬 : 음악프로그램</a>
 * @since 2024-05-18
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] A = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());

            for (int j = 0; j < t - 1; j++) {
                int p = Integer.parseInt(st.nextToken());
                A[first].add(p);
                inDegree[p]++;
                first = p;
            }
        }

        Queue<Integer> qu = new LinkedList<>();
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!qu.isEmpty()) {
            int now = qu.poll();
            sb.append(now).append("\n");

            for (int next : A[now]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    qu.offer(next);
                }
            }
        }

        for (int i : inDegree) {
            if (i > 0) {
                System.out.println(0);
                return;
            }
        }

        System.out.print(sb);
    }
}
