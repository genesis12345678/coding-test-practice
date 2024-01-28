package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 브루트포스 + bfs
 * 육지(L)를 돌면서 각 육지마다 가장 먼 육지의 거리를 구하면서 최댓값을 갱신시킨다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2589">백준 2589번 : 너비우선탐색 - 보물섬</a>
 * @since 2024-01-28
 */
public class Two {
    static int n, m;
    static char[][] map;
    static boolean[][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }
        System.out.println(max);
    }

    static void bfs(int a, int b) {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{a, b});
        visit = new boolean[n][m];
        visit[a][b] = true;

        int count = 0;

        while (!qu.isEmpty()) {
            int size = qu.size();
            count++;

            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();
                int x = now[0];
                int y = now[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                        if (!visit[nx][ny] && map[nx][ny] == 'L') {
                            visit[nx][ny] = true;
                            qu.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

        }
        max = Math.max(max, count);
    }
}
