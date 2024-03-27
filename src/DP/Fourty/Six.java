package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * DSF와 DP를 이용해 해결한다.
 * dp[i][j]를 (i, j)위치에서 오른쪽 아래로 갈 수 있는 경우의 수라 생각해본다.
 * 일단 처음에는 경우의 수가 없다고 생각하고 dp를 모두 -1로 초기화한다.
 * 그리고 해당 위치 칸의 수만큼 아래나 오른쪽으로 이동하면서 오른쪽 아래를 찍고 오면 dp에 저장한다.
 * 이후 재귀를 빠져나오면서 다른 경로를 탐색할 때는 dp 배열에 저장되어 있는 값을 사용할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1890">백준 1890번 - DP : 점프</a>
 * @since 2024-03-25
 */
public class Six {

    static int n;
    static int[][] map;
    static long[][] dp;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static long dfs(int x, int y) {
        if (x == n - 1 && y == n - 1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i] * map[x][y];
            int ny = y + dy[i] * map[x][y];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }
}
