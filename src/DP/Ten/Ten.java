package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n]을 n번째까지 마실 수 있는 최댓값이라 생각해본다.
 * 문제 조건에 3번을 연달아 마실 수 없는 것에 주의해야 한다.
 * n-1번째를 마신 경우 : dp[n-3] + A[n - 1] + A[n]
 * n-2번째를 마시지 않은 경우 : dp[n - 2] + A[n]
 * 둘 중 큰 값을 dp[n]에 저장한다.
 * 단! n번째 잔을 마시지 않는 경우도 고려해야 한다.
 * 그래서 max(dp[n], dp[n-1])을 해주어야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2156">백준 2156번 - DP : 포도주 시식</a>
 * @since 2024-03-18
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = A[1];
        if (n >= 2) {
            dp[2] = A[1] + A[2];
        }

        for (int i = 3; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + A[i - 1]) + A[i];
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        System.out.println(dp[n]);
    }
}
