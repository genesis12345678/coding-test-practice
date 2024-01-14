package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 2차원 토마토 문제의 높이까지 더해져서 3차원으로 생각해야 한다.
 * 3차원 배열로 [높이][세로][가로]를 입력받는다.
 * 상,하,좌,우 에다가 위와 아래까지 계산해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/7569">백준 7569번 : 너비우선탐색 - 토마토</a>
 * @since 2024-01-14
 */
public class One {
    static int M, N, H;
    static int[][][] map;
    static boolean[][][] visit;
    static Queue<int[]> qu = new LinkedList<>();

                        //상, 하, 좌, 우, 위, 아래
    static int[] moveX = {-1, 1, 0, 0, 0, 0}; //x좌표
    static int[] moveY = {0, 0, -1, 1, 0, 0}; //y좌표
    static int[] moveZ = {0, 0, 0, 0, -1, 1}; //z좌표
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        visit = new boolean[H][N][M];

        int count = 0;
        // 입력
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 0) {
                        count++;
                    } else if (map[i][j][k] == 1) {
                        visit[i][j][k] = true;
                        qu.offer(new int[]{i, j, k});
                    }
                }
            }
        }
        // 처음부터 모든 토마토가 익어있는 상태
        if (count == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int days = 0;

        while (!qu.isEmpty()) {
            days++;

            int size = qu.size();

            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();
                int nowZ = now[0];
                int nowX = now[1];
                int nowY = now[2];
                // 상, 하, 좌, 우, 위, 아래 총 6번 반복
                for (int j = 0; j < 6; j++) {
                    int nextZ = nowZ + moveZ[j];
                    int nextX = nowX + moveX[j];
                    int nextY = nowY + moveY[j];

                    if (rangeCheck(nextZ, nextX, nextY)) {
                        if (!visit[nextZ][nextX][nextY] && map[nextZ][nextX][nextY] == 0) {
                            map[nextZ][nextX][nextY] = 1;
                            visit[nextZ][nextX][nextY] = true;
                            qu.offer(new int[]{nextZ, nextX, nextY});
                        }
                    }
                }
            }
        }

        // 인접한 토마토가 다 익고 나서 익지 못한 토마토가 남아있다면 -1 반환
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[i][j][k] == 0) {
                        return -1;
                    }
                }
            }
        }
        // 첫날은 빼야 한다.
        return days - 1;
    }

    private static boolean rangeCheck(int nextZ, int nextX, int nextY) {
        return nextZ >= 0 && nextX >= 0 && nextY >= 0 && nextZ < H && nextX < N && nextY < M;
    }
}


