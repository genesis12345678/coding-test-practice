package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1인 부분을 상하좌우 + 대각선으로 dfs 탐색해서 방문처리 한다.
 * 탐색한 만큼 count를 더하고 count를 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14716">백준 14716번 : 깊이우선탐색 - 현수막</a>
 * @since 2024-02-21
 */
public class One {

    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
    //상, 하, 좌, 우, 왼위, 오위, 오아래, 왼아래
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visit = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    count++;
                    dfs(i, j);
                }
            }
        }
        System.out.println(count);
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (!visit[nx][ny] && map[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
