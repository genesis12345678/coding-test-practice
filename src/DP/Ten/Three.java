package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[N][i] 일때, N번 집을 i의 색깔로 칠했을 때 최솟값
 * dp[N][i] = dp[N-1][i!=i] + cost[N][i]
 * 이전 최소 비용에서 현재 색깔의 비용을 더했을 때 현재 N에서 최소 비용이 된다.
 * 주의할 건 같은 색을 연속으로 칠하면 안 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1149">백준 1149번 - DP : RGB 거리</a>
 * @since 2024-03-16
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N + 1][3]; //색깔은 3가지 경우 밖에 없으니 3으로 지정
        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }

        System.out.println(
                Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2])
        );

    }
}
