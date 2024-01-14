package bfs.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dfs로 적록색약이 아닌 사람이 본 구역의 수를 구하고 적록색약인 사람이 본 구역의 수를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10026">백준 10026번 : 너비우선탐색 - 적록색약</a>
 * @since 2024-01-14
 */
public class Ten {
    static int N;
    static char[][] map;
    static boolean[][] visit;
    static int[] moveX = {-1, 1, 0, 0}; // x 좌표
    static int[] moveY = {0, 0, -1, 1}; // y 좌표
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visit = new boolean[N][N];

        // 입력
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // 적록색약이 아닌 사람이 본 구역의 수
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }
        sb.append(count).append(" ");

        // 적록색약인 사람이 본 구역의 수를 구하기 위해 R과 G를 같게 한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'R') {
                    map[i][j] = 'G';
                }
            }
        }

        // 방문기록을 초기화 해준다.
        visit = new boolean[N][N];
        // count를 초기화해준다.
        count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }
        sb.append(count).append(" ");

        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;
        char nowChar = map[x][y];

        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
                if (!visit[nextX][nextY] && nowChar == map[nextX][nextY]) {
                    dfs(nextX, nextY);
                }
            }
        }
    }
}
