package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 i줄에서 시작했을 때 j까지 탐색했을 때 최댓값
 * i는 0또는 1이다.(2행이므로)
 * 변을 공유하면 안 되므로 대각선으로 밖에 움직이지 못한다.
 * 대각선 한 칸 이전과 대각선 두 칸 이전 값을 비교해 큰 값에 현재 값을 dp[i][j]에 저장한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9465">백준 9465번 - DP : 스티커</a>
 * @since 2024-03-19
 */

public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[2][100_001];
            int[][] A = new int[2][100_001];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = A[0][1];
            dp[1][1] = A[1][1];

            for (int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + A[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + A[1][i];
            }

            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }
        System.out.print(sb);
    }
}
