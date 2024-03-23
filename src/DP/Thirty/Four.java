package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * DFS 문제에 DP를 병합해서 해결해야 한다. 그렇지 않으면 시간 초과가 발생한다.
 * dp[i][j]를 (i, j)위치에서 끝까지 갈 수 있는 경로의 경우의 수라 생각해본다.
 * DFS를 통해 탐색하면서 갈 수 있던 경로를 다시 탐색하면 더 이상 탐색하지 않고 해당 값을 리턴한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1520">백준 1520번 - DP : 내리막 길</a>
 * @since 2024-03-21
 */
public class Four {

    static int m, n;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; //초기에는 모두 갈 수 없다고 판단하고 -1로 초기화
            }
        }

        System.out.println(dfs(0, 0)); //처음부터 시작
    }

    private static int dfs(int x, int y) {
        if (x == m - 1 && y == n - 1) { //마지막 위치에 도착하면 탐색 경로에 경우의 수 1 증가
            return 1;
        }

        if (dp[x][y] != -1) { //메모이제이션
            return dp[x][y];
        }

        dp[x][y] = 0; //가능한 경로 중 하나라고 보고 0으로 변경

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                if (map[x][y] > map[nx][ny]) {
                    dp[x][y] += dfs(nx, ny); //결과적으로 (0, 0)에서 갈 수 있는 경우의 수가 저장된다.
                }
            }
        }

        return dp[x][y];
    }
}
