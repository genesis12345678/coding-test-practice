package bfs.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 최소를 알기 위해 bfs를 사용한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/7576">백준 7576번 : 너비우선탐색 - 토마토</a>
 * @since 2024-01-12
 */
public class Six {

    static int N , M;
    static int[][] map;
    static boolean[][] visit;
    static int[] moveX = {-1, 1, 0, 0}; // X좌표 - 상하
    static int[] moveY = {0, 0, -1, 1}; // Y좌표 - 좌우
    static Queue<int[]> qu = new LinkedList<>();
    static int count = 0; // 0인 토마토 개수
    static int days = 0; // 모두 익을 때까지 일수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) { // 처음부터 심어져 있는 토마토 위치 저장
                    qu.offer(new int[]{i, j});
                    visit[i][j] = true;
                } else if(map[i][j] == 0){ // 익지 않아 있는 토마토 개수
                    count++;
                }
            }
        }

        // 저장될 때부터 모든 토마토가 익어있는 상태
        if (count == 0) {
            System.out.println(0);
            return;
        }

        bfs(); // 너비우선탐색 실행

        if (count == 0) {
            System.out.println(days - 1);
        } else {
            System.out.println(-1);
        }

    }

    static void bfs() {
        while (!qu.isEmpty()) {
            /**
             * 큐의 size만큼 반복하면서 days++을 해야
             * 1의 범위를 늘려가면서 정상적으로 일수를 셀 수 있다.
             */
            int size = qu.size();

            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();
                int nowX = now[0];
                int nowY = now[1];

                for (int j = 0; j < 4; j++) {
                    int nextX = nowX + moveX[j];
                    int nextY = nowY + moveY[j];

                    if (rangeCheck(nextY, nextX) && map[nextX][nextY] == 0 && !visit[nextX][nextY]) {
                        map[nextX][nextY] = 1;
                        visit[nextX][nextY] = true;
                        qu.offer(new int[]{nextX, nextY});
                        count--;
                    }
                }
            }
            days++;
        }
    }

    private static boolean rangeCheck(int nextY, int nextX) {
        return nextY >= 0 && nextX >= 0 && nextX < N && nextY < M;
    }
}
