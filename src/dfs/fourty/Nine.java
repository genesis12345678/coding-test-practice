package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 양 무리를 찾을 때마다 dfs로 탐색하면서 방문 처리를 하고 몇 개의 무리를 찾을 수 있는지 계산한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11123">백준 11123번 : 깊이우선탐색 - 양 한마리...양 두마리...</a>
 * @since 2024-02-27
 */
public class Nine {
    static boolean[][] visit;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int H, W, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            visit = new boolean[H][W];
            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
            }

            count = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (!visit[i][j] && map[i][j] == '#') {
                        count++;
                        dfs(i, j);
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < H && ny < W) {
                if (!visit[nx][ny] && map[nx][ny] == '#') {
                    dfs(nx, ny);
                }
            }
        }
    }
}
