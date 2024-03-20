package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 기본적인 피보나치 수열 문제다.
 * dp[n] = dp[n-1] + dp[n-2]
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2748">백준 2748번 - DP : 피보나치 수 2</a>
 * @since 2024-03-18
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[91];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i < 91; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}
