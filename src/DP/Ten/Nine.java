package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n]을 2 x n일때 가능한 경우의 수라고 생각해본다.
 * 2xn 타일링 문제에서 2 x 2 타일이 하나 늘어났다.
 * 즉 n - 2에서 만들 수 있는 경우의 수가 하나 늘어난 것이다.
 * n - 1에서는 2x1 타일 하나만 놓을 수 있으므로 dp[n] = dp[n - 1]
 * n - 2에서는 2x1 또는 1x2, 2x2로 놓을 수 있으므로 dp[n] = dp[n - 1] + 2 * dp[n - 2] 점화식이 나온다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11727">백준 11727번 - DP : 2xn 타일링 2</a>
 * @since 2024-03-17
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 1;  //2x1일 때는 2x1 타일 경우의 수 1개다.
        dp[2] = 3;  //2x2일 때는 1x2, 2x1, 2x2로 각각 채울 수 있다.

        for (int i = 3; i < 1001; i++) {
            dp[i] = (dp[i - 1] + (2 * dp[i - 2])) % 10_007;
        }

        System.out.println(dp[n]);
    }
}
