package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 처음에는 dp[n][0]과 dp[n][1]을 각각 n자리일 때 0으로 끝나는 경우의 수, 1로 끝나는 경우의 수로 생각했었다.
 * 그런데 규칙을 찾아보니 단순 피보나치 수열이랑 똑같았다.
 * 15746으로 MOD 연산하는 것을 주의해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1904">백준 1904번 - DP : 01타일</a>
 * @since 2024-03-18
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1_000_001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < 1_000_001; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
        }

        System.out.println(dp[n]);
    }
}
