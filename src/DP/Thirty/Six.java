package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 0~i(N)까지의 정수를 사용해 j개를 더해서 합이 i가 되는 경우의 수라 생각해본다.
 * 규칙을 찾아보니 왼쪽 값 + 위쪽 값이 해당 자리의 수가 된다.
 * dp[i][j] = dp[i-1][j] + dp[i][j-1]
 * MOD 연산도 잊지 말아야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2225">백준 2225번 - DP : 합분해</a>
 * @since 2024-03-22
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1; //1개를 사용해 i가 되려면 자기 자신밖에 없으므로 1개로 초기화 가능

            for (int j = 2; j <= k; j++) {

                dp[0][j] = 1; //합이 0이 되려면 0만 쓸 수 있으므로 1개로 초기화 가능
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1_000_000_000;
            }
        }

        System.out.println(dp[n][k]);
    }
}
