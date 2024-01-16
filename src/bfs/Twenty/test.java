package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class test {
    static int N, M;
    static int[][] map;
    static boolean[][][] visit; // 벽을 부순 여부도 추가로 저장
    static int[] moveX = {-1, 1, 0, 0}; // x 좌표
    static int[] moveY = {0, 0, -1, 1}; // y 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M][2]; // 2는 벽을 부순 여부를 나타냄

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(map[N - 1][M - 1] == 0 ? -1 : map[N - 1][M - 1]);
    }

    static void bfs() {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{0, 0, 0}); // 시작점과 벽을 부순 횟수를 함께 저장
        visit[0][0][0] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int destroyCount = now[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + moveX[i];
                int ny = y + moveY[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (map[nx][ny] == 0 && !visit[nx][ny][destroyCount]) {
                        qu.offer(new int[]{nx, ny, destroyCount});
                        visit[nx][ny][destroyCount] = true;
                        map[nx][ny] = map[x][y] + 1;
                    } else if (destroyCount == 0 && map[nx][ny] == 1 && !visit[nx][ny][1]) {
                        qu.offer(new int[]{nx, ny, 1});
                        visit[nx][ny][1] = true;
                    }
                }
            }
        }
    }
}

