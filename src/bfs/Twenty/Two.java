package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dfs + 브루트포스 알고리즘으로 각 물의 높이마다 안전 영역의 개수를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2468">백준 2468번 : 너비우선탐색 - 안전 영역</a>
 * @since 2024-01-15
 */
public class Two {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] moveX = {-1, 1, 0, 0}; // x 좌표
    static int[] moveY = {0, 0, -1, 1}; // y 좌표
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        int max = Integer.MIN_VALUE;
        // 입력 부분
        // 동시에 최대 높이를 구해준다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > max) {
                    max = map[i][j];
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i <= max; i++) {
            // i = 비의 높이
            // 높이마다 구역을 구해야 하기 때문에 초기화 해준다.
            visit = new boolean[N][N];
            count = 0;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    // 잠기지 않는 범위를 찾을 때마다 count증가와 잠기지 않는 범위를 체크해준다.
                    if (!visit[j][k] && map[j][k] > i) {
                        count++;
                        dfs(j, k, i);
                    }
                }
            }
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    static void dfs(int x, int y, int h) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
                if (!visit[nextX][nextY] && map[nextX][nextY] > h) {
                    dfs(nextX, nextY, h);
                }
            }
        }
    }
}
