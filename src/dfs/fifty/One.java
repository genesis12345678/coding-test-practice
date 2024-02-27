package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * "인접하다" 라는게 상하좌우 인줄 알았는데 8방으로 해야한다.
 * 같은 높이의 산봉우리만 dfs 탐색하면서 주위에 더 높은 산봉우리가 있으면 현재 위치는 그 산봉우리 중 하나이므로 count를 더하지 않는다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1245">백준 1245번 : 깊이우선탐색 - 농장 관리</a>
 * @since 2024-02-27
 */
public class One {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    //시계 방향
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] > 0) {
                    flag = true;
                    dfs(i, j);
                    if (flag) {
                        count++;
                    }
/*
                    for (boolean[] booleans : visit) {
                        System.out.println("visit = " + Arrays.toString(booleans));
                    }
                    System.out.println("===============================================");
*/
                }
            }
        }
        System.out.println(count);
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;
        for (int i = 0; i < 8; i++) {//8방 탐색
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (map[nx][ny] > map[x][y]) {//꼭대기가 아니면 값을 더하지 않는다.
                    flag = false;
                }
                if (!visit[nx][ny] && map[nx][ny] == map[x][y]) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
