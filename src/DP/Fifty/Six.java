package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 N개 동전 중 i번째까지 탐색했을 때 0~M원 중 j원을 만들 수 있는 경우의 수
 * j=0원일 때 고르지 않는 것도 경우의 수에 포함하여 1로 초기화한다.
 * j원이 동전[i]보다 크다면 이전 경우의 수를 그대로 이어간다.
 * 동전[i]이 j원보다 크거나 같다면 경우의 수를 증가시킬 수 있다.
 * dp[i][j] = dp[i-1][j] + dp[i][j-현재 동전 금액]
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9084">백준 9084번 - DP : 동전</a>
 * @since 2024-03-30
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] coins = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());
            int[][] dp = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                dp[i][0] = 1;
                for (int j = 1; j <= m; j++) {

                    if (j >= coins[i]) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            System.out.println(dp[n][m]);

        }
    }
}
