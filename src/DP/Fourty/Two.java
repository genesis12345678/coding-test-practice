package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n][i][j]를 n행일 때, 놓을 수 있는 경우의 수라 생각해본다.
 * (i, j)를 세 가지로 볼 수 있다. (0, 0), (0, 1), (1, 0)
 * 0은 배치 안했을 때, 1은 배치 했을 때
 * (0, 0)의 경우 n-1 행에는 세 가지 모두 올 수 있다.
 * (1, 0)의 경우 n-1 행에는 (0, 0), (0, 1)이 올 수 있다. (1, 0)은 세로로 붙어있다.
 * (0, 1)의 경우 n-1 행에는 (0, 0), (1, 0)이 올 수 있다. (0, 1)은 세로로 붙어있다.
 * 9901로 % 연산 주의해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1309">백준 1309번 - DP : 동물원</a>
 * @since 2024-03-24
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][][] dp = new int[100_001][3][3];
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0][0] = (dp[i - 1][1][0] + dp[i - 1][0][0] + dp[i - 1][0][1]) % 9901;
            dp[i][0][1] = (dp[i - 1][0][0] + dp[i - 1][1][0]) % 9901;
            dp[i][1][0] = (dp[i - 1][0][1] + dp[i - 1][0][0]) % 9901;
        }

        System.out.println((dp[n][0][0] + dp[n][0][1] + dp[n][1][0]) % 9901);
    }
}
