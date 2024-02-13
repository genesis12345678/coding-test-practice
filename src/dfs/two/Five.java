package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 0, 0(처음)부터 방문하지 않은 칸부터 dfs로 탐색하면서 depth가 얼마나 나오는지 계산한다.
 * 각 구역의 depth를 제곱한 다음, 병사에 맞게 누적합을 해준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1303">백준 1303번 : 깊이우선탐색 - 전쟁 전투</a>
 * @since 2024-02-13
 */
public class Five {

    static char[][] map;
    static boolean[][] visit;
    static int N, M;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        visit = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int white = 0;
        int black = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    int depth = dfs(i, j, map[i][j]);
                    if (map[i][j] == 'W') {
                        white += depth * depth;
                    } else {
                        black += depth * depth;
                    }
                }
            }
        }

        System.out.println(white + " " + black);
    }

    static int dfs(int x, int y, char ch) {
        visit[x][y] = true;
        int depth = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + mx[i];
            int ny = y + my[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (!visit[nx][ny] && map[nx][ny] == ch) {
                    depth += dfs(nx, ny, ch);
                }
            }
        }
        return depth;
    }
}
