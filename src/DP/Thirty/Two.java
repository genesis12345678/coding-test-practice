package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 그리디 방식으로 무작정 큰 제곱수부터 계산해도 최소항이 아니다.
 * 제곱수들의 합으로 이루어져 있다는 것을 명심하고 문제를 해결한다.
 * dp[n]을 숫자 n일 때 제곱수 항의 최소 개수라고 생각해본다.
 * 7 = (7-1^2) + 1^2 = 7을 6의 최소 항의 개수에 마지막에 1^2 한개를 붙인다.
 * 7 = (7-2^2) + 2^2 = 7을 3의 최소 항의 개수에 마지막에 2^2 한개를 붙인다.
 * 이렇게 생각하면 다음과 같은 점화식을 도출할 수 있다.
 * dp[n] = min(dp[n], dp[n - 제곱수] + 1)
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1699">백준 1699번 - DP : 제곱수의 합</a>
 * @since 2024-03-21
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i] = i;    //초기에는 1^2로 표현할 수 있는 항의 개수로 초기화한다.
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                //dp[i] = min(dp[i], dp[i - 제곱수] + 1)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
