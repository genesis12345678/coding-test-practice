package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n]을 3 x n일 때 가능한 경우의 수라 생각해본다.
 * 3 x 2 간격은 항상 3가지 경우의 수로 고정되어 있다.
 * 그래서 일단 dp[n] = dp[n - 2] * 3이 가능하다.
 * 그리고 생각해야 할 것이 n >= 4 부터는 2칸 간격으로 2가지 경우의 수가 추가된다.
 * 최종 점화식은 이렇다.
 * dp[n] = dp[n - 2] * 3 + dp[4칸부터 2칸씩] * 2
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2133">백준 2133번 - DP : 타일 채우기</a>
 * @since 2024-03-22
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[31];

        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 2] * 3;
            for (int j = 4; j <= i; j += 2) {
                dp[i] += dp[i - j] * 2;
            }
        }

        System.out.println(dp[n]);
    }
}
