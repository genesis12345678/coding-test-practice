package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i]를 i원을 구성할 수 있는 경우의 수라 생각해본다.
 * 규칙을 찾아 보면 다음과 같은 점화식이 나온다.
 * dp[i] = dp[i] + dp[i - 현재 동전 가치]
 * 현재 동전 가치가 하나의 구성원이 되려면 최소한 현재 i가 현재 동전의 가치보다 크거나 같아야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2293">백준 2293번 - DP : 동전 1</a>
 * @since 2024-03-19
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] v = new int[n]; //각 동전 가치
        for (int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = v[i]; j <= k; j++) { //각 동전의 가치부터 최대까지
                dp[j] = dp[j] + dp[j - v[i]];
            }
        }
        System.out.println(dp[k]);

    }
}
