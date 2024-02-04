package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 음식물을 1이라고 두고  bfs탐색으로 1의 범위를 구하면서 max값을 갱신한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1743">백준 1743번 : 너비우선탐색 - 음식물 피하기</a>
 * @since 2024-02-04
 */
public class Four {
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int N, M;
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(max);
    }

    static int bfs(int a, int b) {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{a, b});
        visit[a][b] = true;

        int count = 0;

        while (!qu.isEmpty()) {
            count++;

            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int j = 0; j < 4; j++) {
                int nx = x + mx[j];
                int ny = y + my[j];

                if (nx >= 0 && ny >= 0 && nx <= N && ny <= M) {
                    if (!visit[nx][ny] && map[nx][ny] == 1) {
                        qu.offer(new int[]{nx, ny});
                        visit[nx][ny] = true;
                    }
                }
            }
        }
        return count;
    }
}
