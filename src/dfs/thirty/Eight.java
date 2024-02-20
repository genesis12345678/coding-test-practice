package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 왼쪽 아래부터 dfs탐색을 하며 depth가 K이면서 도착지점에 도착했을 경우 count를 늘린다.
 */

/**
 * <a>백준 1189번 : 깊이우선탐색 - 컴백홈</a>
 */
public class Eight {
    static char[][] map;
    static int R, C, K;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count = 0;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        visit = new boolean[R][C];

        //왼쪽 아래부터 시작
        dfs(R - 1, 0, 1);
        System.out.println(count);
    }

    static void dfs(int x, int y, int depth) {
        //도착지점에 도착했을 때 거리가 K였을 때만 count + 1
        if (x == 0 && y == C - 1 && depth == K) {
            count++;
            return;
        }
        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                if (map[nx][ny] == '.' && !visit[nx][ny]) {
                    dfs(nx, ny, depth + 1);
                }
            }
        }
        visit[x][y] = false;
    }
}
