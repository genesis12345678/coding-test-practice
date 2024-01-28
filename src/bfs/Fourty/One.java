package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 모서리 부분인 1을 찾아내는 것이 포인트다. 루프를 돌면서 1인 부분부터 시작하면서 어떻게 해야할 줄 알았다.
 * (0, 0)부터 bfs탐색을 시작하여 0인 부분만 계속 탐색해 나간다.
 * 치즈(1)를 만나면 그 부분은 큐에 넣지 않고 0으로만 변경한다. 만나는 부분이 모서리 부분인 것이다.
 * 치즈가 다 녹을때 까지 반복한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2636">백준 2636번 : 너비우선탐색 - 치즈</a>
 * @since 2024-01-28
 */
public class One {
    static int n, m;
    static int[][] map;
    static boolean[][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int cheese;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int count = 0;
        int time = 0;
        while (cheese > 0) {
            count = cheese;
            time++;
            visit = new boolean[n][m];
            bfs();
        }
        System.out.println(time);
        System.out.println(count);
    }

    static void bfs() {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{0, 0});
        visit[0][0] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    if (map[nx][ny] == 0) {
                        qu.offer(new int[]{nx, ny});
                    } else {
                        cheese--;
                        map[nx][ny] = 0;
                    }
                }
            }
        }
    }
}
