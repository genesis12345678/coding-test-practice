package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 첫 위치에서 시작하고 다시 첫 위치로 돌아올 때 깊이가 4 이상을 확인하는 dfs 탐색을 한다.
 * 첫 위치를 기억하고 있어야 하므로 static으로 보관한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16929">백준 16929번 : 깊이우선탐색 - Two Dots</a>
 * @since 2024-02-20
 */
public class Seven {
    static int N, M;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int startX, startY;

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
                startX = i;
                startY = j;
                if (dfs(i, j, 1)) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    private static boolean dfs(int x, int y, int depth) {
       visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[x][y] == map[nx][ny]) {
                if (!visit[nx][ny]) {
                    if (dfs(nx, ny, depth + 1)) {
                        return true;
                    }
                } else {
                    if (nx == startX && ny == startY && depth >= 4) {
                        return true;
                    }
                }
            }
        }

        visit[x][y] = false;
        return false;
    }
}

