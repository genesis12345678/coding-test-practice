package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j][k]를 (i, j) 위치에 k 방향으로 오는 경우의 수라고 생각해본다.
 * k는 0=가로, 1=세로, 2=대각선
 * 만약 (n, n)위치에 가로 방향으로 도착했다면 (n, n-1) 위치에 가로로 도착한 경우의 수 + 대각선으로 도착한 경우의 수와 같다.
 * dp[n][n][0] = dp[n][n-1][0] + dp[n][n-1][2]
 *
 * 만약 (n, n)위치에 세로 방향으로 도착했다면 (n-1, n) 위치에 세로로 도착한 경우의 수 + 대각선으로 도착한 경우의 수와 같다.
 * dp[n][n][1] = dp[n-1][n][1] + dp[n-1][n][2]
 *
 * 만약 (n, n)위치에 대각선 방향으로 도착했다면 (n-1, n-1) 위치에 가로 + 세로 + 대각선으로 도착한 경우의 수와 같다.
 * dp[n][n][2] = dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2]
 * 주의할 점은 대각선 이동은 인접한 모든 칸이 빈 칸 이어야한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17070">백준 17070번 - DP : 파이프 옮기기 1</a>
 * @since 2024-03-24
 */
public class Three {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[17][17];
        int[][][] dp = new int[17][17][3]; //0=가로, 1=세로, 2=대각선

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][2][0] = 1; //초기 (1, 2) 위치에서는 가로로 올 수 있다.

        for (int i = 3; i <= n; i++) { //1행 3열부터 n열까지 가로로 올 수 있는 경우를 센다.
            if (map[1][i] == 0) {
                dp[1][i][0] = dp[1][i - 1][0];
            }
        }

        for (int i = 2; i <= n; i++) { //1행은 위에서 체크했으니 2행부터
            for (int j = 1; j <= n; j++) { //1열부터
                if (map[i][j] == 0) { //셀 수 있는 곳이면

                    //(i, j)위치에 가로(0)로 올 수 있는 경우의 수는
                    //파이프 왼쪽에 가로로 오는 경우의 수 + 대각선으로 오는 경우의 수와 같다.
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

                    //(i, j)위치에 세로(1)로 올 수 있는 경우의 수는
                    //파이프 위쪽에 세로로 오는 경우의 수 + 대각선으로 오는 경우의 수와 같다.
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

                    //대각선 이동은 도착하는 곳 뿐만 아니라 총 3곳이 빈 칸 이어야 한다.
                    if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                        //(i, j)위치에 대각선(2)으로 올 수 있는 경우의 수는
                        //파이프 대각선 위 왼쪽에 가로 + 세로 + 대각선으로 오는 경우의 수와 같다.
                        dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    }
                }
            }
        }

        //최종적으로 (n, n)위치에 가로 + 세로 + 대각선으로 오는 경우의 수가 정답이다.
        System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
    }
}