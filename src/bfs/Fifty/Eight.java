package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 일반적인 bfs 문제다.
 * 처음 '2' 위치에서 시작해서 전 칸 +1 하면서 탐색하면 된다.
 * 출력할 때 원래 1인 칸인데 탐색 못한 땅만 체크해주면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14940">백준 14940번 : 너비우선탐색 - 쉬운 최단거리</a>
 * @since 2024-02-05
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<int[]> qu = new LinkedList<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        boolean[][] visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { //시작 위치 저장
                    map[i][j] = 0;
                    qu.offer(new int[]{i, j});
                    visit[i][j] = true;
                }
            }
        }

        int[] mx = {-1, 1, 0, 0};
        int[] my = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            int size = qu.size();
            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();
                int x = now[0];
                int y = now[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                        if (!visit[nx][ny] && map[nx][ny] == 1) {
                            visit[nx][ny] = true;
                            map[nx][ny] = map[x][y] + 1;
                            qu.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        /**
         * 최종 출력할 때 원래 갈 수 있는 땅인 부분 중에서
         * 도달할 수 없는 위치에 -1을 출력해 주어야 한다.
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    map[i][j] = -1;
                }
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
