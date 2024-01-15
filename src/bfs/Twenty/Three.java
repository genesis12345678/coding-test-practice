package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 다른 dfs문제들과 비슷하지만 대각선이 포함됐다.
 * x와 y좌표에 대각선을 추가해서 풀었다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/4963">백준 4963번 : 너비우선탐색 - 섬의 개수</a>
 * @since 2024-01-15
 */
public class Three {

    static int[][] map;
    static boolean[][] visit;
    static StringBuilder sb = new StringBuilder();

    // 상, 하, 좌, 우, 왼쪽 위, 왼쪽 아래, 오른쪽 위, 오른쪽 아래
    static int[] moveX = {-1, 1, 0, 0, -1, 1, -1, 1}; // x 좌표
    static int[] moveY = {0, 0, -1, 1, -1, -1, 1, 1}; // y 좌표
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

           w = Integer.parseInt(st.nextToken());
           h = Integer.parseInt(st.nextToken());

           // 입력이 0 0 이면 실행 종료
            if (w == 0 && h == 0) {
                break;
            }

            map = new int[h][w];
            visit = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visit[i][j] && map[i][j] == 1) {
                        count++;
                        dfs(i, j);
                    }
                }
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb);

    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int nx = x + moveX[i];
            int ny = y + moveY[i];

            if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                if (!visit[nx][ny] && map[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
