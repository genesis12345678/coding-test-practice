package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1이 나올 때마다 bfs탐색으로 그림의 크기를 구하면서 최대 크기를 갱신한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1926">백준 1926번 : 너비우선탐색 - 그림</a>
 * @since 2024-01-27
 */
public class Ten {
    static int n, m;
    static int[][] map;
    static boolean[][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        int oneCount = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    oneCount++;
                }
            }
        }
        if (oneCount == 0) {
            System.out.println(0);
            System.out.println(0);
            return;
        }



        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j] && map[i][j] == 1) {
                    count++;
                    bfs(i, j);
                }
            }
        }
        System.out.println(count);
        System.out.println(max);

    }

    static void bfs(int i, int j) {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{i, j});
        visit[i][j] = true;

        int size = 0;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            size++;

            for (int k = 0; k < 4; k++) {
                int nx = x + mx[k];
                int ny = y + my[k];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visit[nx][ny] && map[nx][ny] == 1) {
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        max = Math.max(max, size);
    }
}

