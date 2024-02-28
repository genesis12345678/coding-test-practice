package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 일반적인 dfs로 해결하려고 하면 시간초과가 발생한다. 메모리제이션을 사용해야 한다.
 * dp 배열에 depth별로 각 위치에서 나올 수 있는 경로의 수이다.
 * 3차원으로 하는 이유는 단어가 같은 알파벳이 여러 번 나올 수 있기 때문이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2186">백준 2186번 : 깊이우선탐색 - 문자판</a>
 * @since 2024-02-28
 */
public class Two {
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m, k;
    static int[][][] dp;
    static char[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        ans = br.readLine().toCharArray();
        dp = new int[n][m][ans.length];

        //dp배열 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == ans[0]) {//시작 부분을 찾으면 탐색 시작
                    result += dfs(i, j, 1);//dfs를 통해 시작 위치에서 이동 가능한 경우의 수를 반환받는다.
                }
            }
        }
        System.out.println(result);

        for (int[][] ints : dp) {
            System.out.println("ints = " + Arrays.deepToString(ints));
            System.out.println("===============================");
        }
    }

    static int dfs(int x, int y, int depth) {
        if (depth == ans.length) {//마지막까지 도착했다면 종료
            return 1;
        }

        if (dp[x][y][depth] != -1) {
            return dp[x][y][depth];
        }

        int count = 0;

        for (int i = 1; i <= k; i++) {//상하좌우로 K개의 칸까지 이동할 수 있다.
            for (int j = 0; j < 4; j++) {
                int nx = x + i * dx[j];
                int ny = y + i * dy[j];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == ans[depth]) {
                        count += dfs(nx, ny, depth + 1);
                    }
                }
            }
        }
        return dp[x][y][depth] =  count;
    }
}
