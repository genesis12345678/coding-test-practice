package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 최대 범위가 8x8로 매우 작다. bfs, dfs, 브루트포스 알고리즘을 사용할 수 있을 것 같다.
 * 벽 3개를 세울 수 있는 모든 경우의 수에 안전 영역을 구할 수 있다.
 * dfs로 벽을 세우면서 3개가 되는 순간 bfs로 바이러스를 퍼뜨리고 난 뒤 안전영역의 수를 구해나간다.
 * 중요한 것은 return으로 계속해서 다음 경우의 수로 진행해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14502">백준 14502번 : 너비우선탐색 - 연구소</a>
 * @since 2024-01-13
 */
public class Nine {

    static int[][] lab; // 기존 연구소
    static int N, M;
    static int[] moveX = {-1, 1, 0, 0}; // x 좌표
    static int[] moveY = {0, 0, -1, 1}; // y 좌표
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];

        // 입력 부분
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        // 처음부터 시작
        dfs(0);

        System.out.println(result);
    }

    static void dfs(int x) {
        /**
         * 벽이 3개 세워지는 순간 바이러스가 퍼진 뒤 안전영역에 개수를 구한다.
         * return으로 기존 벽을 허물고 다음 위치에 벽을 세워 다음 경우의 수를 구한다.
         * 범위가 작아서 시간초과 없이 가능하다.
         */
        if (x == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    dfs(x + 1);
                    /**
                     * 중요!
                     * bfs() 함수를 마치고 return으로 다시 돌아오면 마지막 3개째에 벽을 허물고
                     * 다음 위치에 벽을 세워 브루트포스 알고리즘으로 모든 경우의 수를 구할 수 있다.
                     */
                    lab[i][j] = 0;
                }
            }
        }
    }


    static void bfs() {

        /**
         * 주의!
         * int[][] copyLab = lab; 을 하면 얕은 복사가 된다.
         * (참조값, 즉 주소값을 복사하기 때문에 기존 객체도 변경이 된다.)
         * 그래서 아예 new로 새로운 객체를 만들어 독립적으로 될 수 있게 해야한다.
         */
        int[][] copyLab = new int[N][M];

        Queue<int[]> qu = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyLab[i][j] = lab[i][j];
                if (copyLab[i][j] == 2) {
                    qu.offer(new int[]{i, j});
                }
            }
        }

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + moveX[i];
                int nextY = nowY + moveY[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
                    if (copyLab[nextX][nextY] == 0) {
                        copyLab[nextX][nextY] = 2;
                        qu.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }

        // 3개의 벽이 세워진 경우의 수가 끝날 때마다 안전 영역의 크기를 구한다.
        countSafetyZone(copyLab);
    }

    static void countSafetyZone(int[][] copyMap) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    count++;
                }
            }
        }
        result = Math.max(result, count);
    }
}
