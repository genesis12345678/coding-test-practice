package bfs.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dfs 방식으로 1의 범위를 찾아나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1012">백준 1012번 : 너비우선탐색 - 유기농 배추</a>
 * @since 2024-01-11
 */
public class Five {

    static boolean[][] visit;
    static int[][] map;
    static int[] moveX = {-1, 1, 0, 0}; // X좌표의 움직임 == 상,하,좌,우
    static int[] moveY = {0, 0, -1, 1}; // Y좌표의 움직임 == 상,하,좌,우
    static int M; // 가로
    static int N; // 세로
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        while (T-- > 0) {
            count = 0; // 카운트 초기화

            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            /**
             * M사이즈의 배열이 N개
             * M=가로, N=세로
             */
            visit = new boolean[N][M];
            map = new int[N][M];

            int K = Integer.parseInt(st.nextToken());
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 & !visit[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (rangeCheck(nextX, nextY) && !visit[nextX][nextY] && map[nextX][nextY] == 1) {
                visit[nextX][nextY] = true;
                dfs(nextX, nextY);
            }
        }
    }

    // 범위 체크
    private static boolean rangeCheck(int nextX, int nextY) {
        return nextX >= 0 && nextY >= 0 && nextX < N && nextY < M;
    }
}
