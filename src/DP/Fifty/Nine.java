package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[i][j]를 숫자 i에 대해 j(1,2,3)로 더하기를 시작했을 때 나오는 경우의 수라 생각해본다.
 * 1로 시작한다면? 뒤에 2 또는 3이 올 수 있다.
 * 2로 시작한다면? 뒤에 1 또는 3이 올 수 있다.
 * 3로 시작한다면? 뒤에 1 또는 2가 올 수 있다.
 * 최종적으로 dp[n][1] + dp[n][2] + dp[n][3]이 정답이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15990">백준 15990번 - DP : 1, 2, 3 더하기 5</a>
 * @since 2024-03-31
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long[][] dp = new long[100_001][4];

        dp[1][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

        int mod = 1_000_000_009;

        for (int i = 4; i <= 100_000; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % mod;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % mod;
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println((dp[n][1] + dp[n][2] + dp[n][3]) % mod);
        }
    }
}
