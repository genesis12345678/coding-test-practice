package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * bfs를 사용한다.
 * 상하좌우를 탐색하면서 햇수를 증가시키고 덩어리 수를 return 받는다.
 * 덩어리 수가 2 이상이면 main 메서드를 종료한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2573">백준 2573번 : 너비우선탐색 - 빙산</a>
 * @since 2024-01-24
 */
public class Six {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] mx = new int[]{-1, 1, 0, 0};
    static int[] my = new int[]{0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 입력 부분
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0; // 햇수

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    count++; // 녹을 빙산이 있으므로 햇수를 증가한다.
                    int result = bfs(i, j); // bfs는 녹을 빙산이 다 녹고 나서 덩어리 수를 반환한다.
                    if (result >= 2) { // 덩어리 수가 2 이상이면 햇수를 출력하고 종료한다.
                        System.out.println(count);
                        return;
                    }
                }
            }
        }
        System.out.println(0); // 반복을 마칠 때까지 두 덩어리 이상으로 분리되지 않았다.
    }

    static int bfs(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a, b});
        visit = new boolean[N][M];

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            visit[x][y] = true;

            int count = 0; // 인접한 바다 수
            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                // 반복을 진행하면서 빙산이 자신의 크기보다 많은 인접한 바다 수를 가질 수 있다.
                // 그럼 빙산이 녹았을 때 음수가 될 수도 있으므로 0보다 작거나 같아야 한다.
                if (map[nx][ny] <= 0 && !visit[nx][ny]) {
                    count++; // 인접한 바다 수 증가
                } else if(!visit[nx][ny]){
                    visit[nx][ny] = true;
                    q.offer(new int[]{nx, ny}); // 다음 탐색 위치 추가
                }
            }
            map[x][y] -= count; // 빙산을 녹인다.
        }

        visit = new boolean[N][M]; // 덩어리 수를 세기위한 방문배열 초기화
        int count = 0; // 덩어리 수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visit[i][j]) {
                    count++; // 새로운 덩어리가 나올 때마다 +1
                    countLump(i, j); // 덩어리를 표시(방문처리)한다.
                }
            }
        }
        return count;
    }

    static void countLump(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a, b});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            visit[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                if (map[nx][ny] > 0 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
