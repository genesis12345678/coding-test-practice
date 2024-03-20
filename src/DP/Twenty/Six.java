package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i]을 i장 샀을 때 최대 비용이라 생각해본다.
 * i장을 이루는 경우의 수가 다양하므로 모든 경우의 수를 고려해야 한다.
 * dp[i] = max(dp[i], dp[i-j] + P[j])
 * 각 장을 샀을 때 최대 비용과 현재 비용을 계산하면서
 * 다양한 조합 중에서 최대 비용을 선정해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11052">백준 11052번 - DP : 카드 구매하기</a>
 * @since 2024-03-19
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        int[] P = new int[1001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) { //카드는 N개를 넘을 수 없으므로 i까지
                dp[i] = Math.max(dp[i], dp[i - j] + P[j]);
            }
        }
        System.out.println(dp[n]);
    }
}
