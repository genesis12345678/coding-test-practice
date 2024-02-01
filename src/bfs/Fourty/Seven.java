package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 일단 각 구역을 구분할 수 있어야 하는데 구역마다 다른 숫자를 부여하도록 했다.
 * 그리고 각 육지마다 bfs탐색으로 다른 구역으로 이동하는 최소 거리를 구하면서 min을 갱신시킨다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2146">백준 2146번 : 너비우선탐색 - 다리 만들기</a>
 * @since 02-01
 */
public class Seven {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 구역을 어떻게 구분해야할까 고민하다가 한 구역의 시작점을 찾을 때마다 area를 증가하면서
        // bfs탐색으로 area를 부여하기로 했다.
        int area = 1;
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && map[i][j] != 0) {
                    areaBfs(i, j, area++);
                }
            }
        }

        // 이제 구역이 분리가 되었으므로 육지를 하나씩 옮겨가면서 다른 구역으로 이동하는
        // 최소 이동거리를 구한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(min);
    }

    static void bfs(int i, int j) {
        boolean[][] temp = new boolean[N][N];
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{i, j, 0});
        temp[i][j] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int cnt = now[2];

            for (int k = 0; k < 4; k++) {
                int nx = x + mx[k];
                int ny = y + my[k];

                // 기본적으로는 범위 안이어야 한다.
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    // 다음 탐색이 바다가 아니면서, 내 구역이 아닌 다른 구역이다.
                    // 최소이동거리를 갱신
                    if (map[nx][ny] != 0 && map[nx][ny] != map[i][j]) {
                        min = Math.min(min, cnt);
                        return;
                    }
                    if (!temp[nx][ny] && map[nx][ny] == 0) {
                        temp[nx][ny] = true;
                        qu.offer(new int[]{nx, ny, cnt + 1});
                    }
                }
            }
        }
    }

    static void areaBfs(int a, int b, int num) {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{a, b});
        visit[a][b] = true;
        map[a][b] = num;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!visit[nx][ny] && map[nx][ny] != 0) {
                        qu.offer(new int[]{nx, ny});
                        visit[nx][ny] = true;
                        map[nx][ny] = num;
                    }
                }
            }
        }
    }
}
