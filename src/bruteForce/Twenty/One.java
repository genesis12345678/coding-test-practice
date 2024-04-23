package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 도형을 회전하는 걸 어떻게 구현하나 고민했는데, 알고보니 회전이나 대칭이 가능하므로 DFS로 완전탐색 하면서 depth가 4면 합을 구하는 방식이 가능하다.
 * 문제는 5번째 'ㅜ'자 도형인데, 2번째 위치에서 두 방향으로 갈 수가 있다.
 * 즉 ㅓ, ㅏ, ㅗ, ㅓ 이렇게 가운데 고정인 채로 3방향만 각각 다른 경우가 생긴다.
 * 이 부분은 재귀호출을 할 때 x, y를 고정하고 시작점을 다르게 해 4가지 조합을 본다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14500">백준 14500번 - 브루트포스 : 테트로미노</a>
 * @since 2024-04-23
 */
public class One {

    static int n, m;
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    dfs(i, j, 1, map[i][j]); //오버로딩
                    visit[i][j] = false;

                    dfs(0, i, j, 0, map[i][j]); //오버로딩
                }
            }
        }

        System.out.print(max);
    }

    static void dfs(int start, int x, int y, int depth, int sum) {
        if (depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                dfs(i + 1, x, y, depth + 1, sum + map[nx][ny]);
            }
        }
    }

    static void dfs(int x, int y, int depth, int sum) {

        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visit[nx][ny]) {
                visit[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }
}
