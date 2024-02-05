package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * M가지를 뽑는 모든 경우의 수를 계산한다.
 * TODO: 캐슬 디펜스와 똑같은 난관에 부딪혔다. 아직 완벽히 풀지 못했다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17142">백준 17142번 : 너비우선탐색 - 연구소 3</a>
 * @since 2024-02-05
 */
public class Five {
    static int N, M;
    static int[][] map;
    static List<int[]> virus = new ArrayList<>();
    static int[] temp;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    static int empty = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        temp = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
                else if (map[i][j] == 0) {
                    empty++;
                }
            }
        }
        if (empty == 0) {
            System.out.println(0);
            return;
        }
        ready(0,0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void ready(int idx, int start) {
        if (idx == M) {
            start(empty);
            return;
        }
        for (int i = start; i < virus.size(); i++) {
            temp[idx] = i;
            ready(idx + 1, i + 1);
        }
    }

    static void start(int empty) {

        boolean[][] visit = new boolean[N][N];
        Queue<int[]> qu = new LinkedList<>();

        for (int num : temp) {
            int[] now = virus.get(num);
            int x = now[0];
            int y = now[1];
            qu.offer(new int[]{x, y, 0});
            visit[x][y] = true;
        }

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int cnt = now[2];

            for (int j = 0; j < 4; j++) {
                int nx = x + mx[j];
                int ny = y + my[j];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!visit[nx][ny] && map[nx][ny] == 0) {
                        empty--;
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny, cnt + 1});
                    }
                    if (empty == 0) {
                        min = Math.min(min, cnt + 1);
                        return;
                    }
                }
            }
        }
    }
}
