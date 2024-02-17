package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 처음(0, 0)부터 탐색하면서 '-'면 가로로, '|'면 세로로 dfs 탐색하면서 방문 체크하고 카운트를 센다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1388">백준 1388번 : 깊이우선탐색 - 바닥 장식</a>
 * @since 2024-02-17
 */
public class Two {

    static char[][] map;
    static int N, M;
    static boolean[][] visit;
    static int count = 0;
    static int[] d = {-1, 1};//상하좌우 탐색 필요없이 상하 또는 좌우만 탐색하면 된다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j]) {
                    dfs(i, j, map[i][j]);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void dfs(int x, int y, char ch) {
        visit[x][y] = true;

        if (ch == '-') {
            rowDfs(x, y, ch);
        } else {
            columnDfs(x, y, ch);
        }
    }

    private static void columnDfs(int x, int y, char ch) {
        for (int i = 0; i < 2; i++) {
            int nx = x + d[i];//열에 대해서만 탐색하면 되기 때문에 x좌표만 변경한다.

            if (nx >= 0 && nx < N) {
                if (!visit[nx][y] && map[nx][y] == ch) {
                    dfs(nx, y, ch);
                }
            }
        }
    }
    private static void rowDfs(int x, int y, char ch) {
        for (int i = 0; i < 2; i++) {
            int ny = y + d[i];//행에 대해서만 탐색하면 되기 때문에 y좌표만 변경한다.

            if (ny >= 0 && ny < M) {
                if (!visit[x][ny] && map[x][ny] == ch) {
                    dfs(x, ny, ch);
                }
            }
        }
    }
}
