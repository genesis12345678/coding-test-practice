package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 각 i행에서 j열까지 구간 합이라고 생각해본다.
 * 처음에는 각 행에 대해서만 구간 합을 구해놓는다.
 * 질의에 따라 점화식을 적용하면 된다. 예를 들어 (2, 2) ~ (3, 4)라면,
 * (dp[3][4] - dp[3][1]) + (dp[2][4] - dp[2][1])이 된다.
 * 즉 x1 ~ x2 각 행마다 y2 - (y1 - 1)의 구간 합의 총합이 정답이다.
 * (구간 합 공식을 사용한 것이 시간이 더 적게 걸린다.)
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11660">백준 11660번 - DP : 구간 합 구하기 5</a>
 * @since 2024-03-19
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int sum = 0;
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j <= x2; j++) {
                sum += dp[j][y2] - dp[j][y1 - 1];
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
