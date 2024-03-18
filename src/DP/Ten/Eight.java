package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n]을 n번에 있는 정삼각형 한 변의 길이라고 생각해본다.
 * 예제를 보고 규칙을 찾았는데,
 * dp[n] = dp[n - 3] + dp[n - 2]라는 규칙이 나온다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9461">백준 9461번 - DP : 파도반 수열</a>
 * @since 2024-03-17
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;

        for (int i = 4; i < 101; i++) {
            dp[i] = dp[i - 3] + dp[i - 2];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
