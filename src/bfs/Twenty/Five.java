package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * bfs 방식으로 시작점에서부터 이동 가능한 칸에 이동할 때마다 전칸+1을 하면서 움직인다.
 * 답은 이동하려고 하는 칸의 값이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/7562">백준 7562번 : 너비우선탐색 - 나이트의 이동</a>
 * @since 2024-01-16
 */
public class Five {
    static int[][] map;
    static boolean[][] visit;
    static int[] moveX = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};
    static int startX, startY;
    static int endX, endY;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visit = new boolean[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            bfs();
            sb.append(map[endX][endY]).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs() {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{startX, startY});
        visit[startX][startY] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i = 0; i < 8; i++) {
                int nx = nowX + moveX[i];
                int ny = nowY + moveY[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visit[nx][ny]) {
                        qu.offer(new int[]{nx, ny});
                        map[nx][ny] = map[nowX][nowY] + 1;
                        visit[nx][ny] = true;
                    }
                }
            }
        }
    }
}
