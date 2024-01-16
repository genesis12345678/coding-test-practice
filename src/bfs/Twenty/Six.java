package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 처음에는 flag 하나 세워서 벽을 한번 부수면 true로 바꿔서 진행하려고 했다.
 * 하지만 너무 복잡했고 또한 벽을 부순 경로와 부수지 않은 경로를 나눠야 한다.
 * 그래서 방문 배열을 일반적으로 2차원을 쓰지만 나누기 위해 3차원을 사용한다.
 * 기본적으로는 bfs알고리즘을 사용한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2206">백준 2206번 : 너비우선탐색 - 벽 부수고 이동하기</a>
 * @since 2024-01-16
 */
public class Six {
    static int N, M;
    static int[][] map;
    static boolean[][][] visit;
    static int[] moveX = {-1, 1, 0, 0}; // x 좌표
    static int[] moveY = {0, 0, -1, 1}; // y 좌표
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        map = new int[N][M];
        /**
         * [0][N][M] - 부수지 않은 경로
         * [1][N][M] - 부순 경로
         */
        visit = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> qu = new LinkedList<>();
        // 시작점
        // {벽 부순 여부, x, y}
        qu.offer(new int[]{0, 0, 0});
        visit[0][0][0] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int w = now[0];
            int x = now[1];
            int y = now[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + moveX[i];
                int ny = y + moveY[i];

                // 기본적으로 범위 내여야 한다.
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    /**
                     * 다음 위치가 벽이라면
                     * 벽을 부순적이 한 번 있나 확인해야 한다.
                     */
                    if (map[nx][ny] == 1) {
                        // w == 0 -> 벽을 부수지 않음
                        // !visit[1][nx][ny] -> 벽을 부순 경로가 방문을 안 했다.
                        if (w == 0 && !visit[1][nx][ny]) {
                            visit[w][nx][ny] = true;
                            map[nx][ny] = map[x][y] + 1;
                            qu.offer(new int[]{1, nx, ny});
                        }
                    // 벽이 아니라면 일반적으로 진행한다.
                    } else {
                        if (!visit[w][nx][ny]) {
                            visit[w][nx][ny] = true;
                            map[nx][ny] = map[x][y] + 1;
                            qu.offer(new int[]{w, nx, ny});
                        }
                    }
                }

                // 마지막 위치까지 도달했다면 종료
                if (nx == N - 1 && ny == M - 1) {
                    return map[nx][ny] + 1;
                }
            }
        }
        // 여기까지 온다면 도착하지 못한 것이다.
        return -1;
    }
}
