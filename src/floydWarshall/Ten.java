package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 일단 N의 범위가 매우 작으므로 플로이드워셜로 최단 거리를 구해준다.
 * 이후에는 DFS를 모든 경로들에 대해 최단 거리를 갱신해주면서 진행한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17182">백준 17182번 - 플로이드워셜 : 우주 탐사선</a>
 * @since 2024-04-13
 */
public class Ten {

    static boolean[] visit;
    static int[][] dist;
    static int min = Integer.MAX_VALUE;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floydWarshall();
        dfs(0, K, 0);

        System.out.println(min);
    }

    private static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                for (int e = 0; e < N; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }
    }

    private static void dfs(int depth, int start, int sum) {
        if (depth == N - 1) { //모든 경로를 탐색했다면 최단 경로 업데이트
            min = Math.min(min, sum);
            return;
        }

        visit[start] = true;

        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                dfs(depth + 1, i, sum + dist[start][i]);
                visit[i] = false;
            }
        }
    }
}
