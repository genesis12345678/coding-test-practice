package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 직사각형 부분은 방문 처리를 하고 이후 map에서 0의 범위를 찾아서 계산한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2583">백준 2583번 : 너비우선탐색 - 영역 구하기</a>
 * @since 2024-01-17
 */
public class Eight {
    static int M, N, K;
    static int[][] map;
    static boolean[][] visit;
    static int[] moveX = {-1, 1, 0, 0};
    static int[] moveY = {0, 0, -1, 1};
    static Queue<Integer> q = new PriorityQueue<>();// 영역의 범위를 저장할 우선순위 큐(자동 정렬)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visit = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            // -1을 해줘야 범위가 맞는다.
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            for (int x = x2; x >= x1; x--) {
                for (int y = y2; y >= y1; y--) {
                    visit[y][x] = true;
                    map[y][x] = 1;
                }
            }
        }

        int areaCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && map[i][j] == 0) {
                    areaCount++; // 0의 영역을 찾을 때마다 +1
                    q.offer(bfs(i, j)); // bfs를 통해 나온 영역의 범위를 우선순위 큐에 넣어준다.
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(areaCount).append("\n");
        while (!q.isEmpty()) {
            sb.append(q.poll()).append(" ");
        }
        System.out.println(sb);
    }

    static int bfs(int x, int y) {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{x, y});

        int count = 1; // 크기가 1인 영역을 생각해서 초기값 1

        while (!qu.isEmpty()) {
            int[] now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + moveX[i];
                int ny = now[1] + moveY[i];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    if (!visit[nx][ny] && map[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny});
                        count++;
                    }
                }
            }
        }
        // count가 1보다 크다면 초기값 1로 설정한 것은 빼줘야 정확한 값이다.
        return count > 1 ? count - 1 : count;
    }
}
