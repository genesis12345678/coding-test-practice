package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디이
 * dp[n]을 n번 수열에서 가능한 최댓값이라 생각해본다.
 * 처음에는 각자 자기 수 한 개를 고른 상태로 초기화할 수 있다.
 * 점화식 : dp[n] = max(dp[n], dp[n - 1] + A[n])
 * n자리에서 가능한 최댓값은 그냥 현재값 그대로 쓰는 것과 이전 최댓값에 현재 수열 값을 더한 것 중 큰 값이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11053">백준 11053번 - DP : 가장 긴 증가하는 부분 수열</a>
 * @since 2024-03-17
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n + 1];
        int[] dp = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = dp[i] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + A[i]);
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}
