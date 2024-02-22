package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 맨 위쪽에서부터 0인 부분을 시작으로 dfs탐색하면서 맨 밑쪽까지 탐색하느냐를 판단한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/13565">백준 13565번 : 깊이우선탐색 - 침투</a>
 * @since 2024-02-21
 */
public class Nine {
    static int M, N;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        //맨 위 행만 탐색
        for (int i = 0; i < N; i++) {
            if (map[0][i] == '0' && dfs(0, i)) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    private static boolean dfs(int x, int y) {
        if (x == M - 1) {//맨 밑까지 닿으면 성공
            return true;
        }
        map[x][y] = 1;//방문했던 곳 표시
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (map[nx][ny] == '0') {
                    if (dfs(nx, ny)) {
                        return true;
                    }
                }
            }
        }
        map[x][y] = 0;//방문했던 곳 원복
        return false;
    }
}
