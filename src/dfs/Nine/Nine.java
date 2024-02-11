package dfs.Nine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 각 노드가 모든 노드들과 연결되어 있는지 여부를 체크해야 된다.
 * 모든 정점에서 나머지 모든 정점들을 탐색하는 플로이드-와샬 알고리즘을 사용한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2458">백준 2458번 : 깊이우선탐색 - 키 순서</a>
 * @since 2024-02-11
 */
public class Nine {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] visit = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            visit[a][b] = true;
        }

        for (int k = 0; k < N; k++) { //경유지
            for (int i = 0; i < N; i++) { //출발
                for (int j = 0; j < N; j++) { //도착
                    if (visit[i][k] && visit[k][j]) {
                        visit[i][j] = true;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (visit[i][j] || visit[j][i]) {
                    count++;
                }
            }
            if (count == N - 1) {
                result++;
            }
        }
        System.out.println(result);
    }
}
