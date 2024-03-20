package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[i][j]를 i자리 일떄, j로 끝날 수 있는 경우의 수라고 생각해본다.
 * dp[1][j]는 모두 1로 초기화할 수 있다. 자기 자신밖에 없다.
 * dp[2][0]때는 dp[1][0] 그대로다. 왜냐하면 0 이전에는 0 이외의 수가 올 수 없다.
 * 최종 점화식
 * dp[i][j] = dp[i-1][0] ~ dp[i-1][j] 의 누적합 % 10_007
 * 예를 들어 j가 5면 앞 자리에 0~5 까지 가능하다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11057">백준 11057번 - DP : 오르막 수</a>
 * @since 2024-03-19
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[n + 1][10];
        for (int i = 0; i < 10; i++) { //0~9
            dp[1][i] = 1; //1자리일 때는 1로 초기화
        }

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][0]; //0 앞에는 0만 올 수 있음

            for (int j = 1; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 10007;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) { //n자리일 때 각 숫자로 끝나는 경우의 수의 합이 정답이다.
            sum = (sum + dp[n][i]) % 10007;
        }
        System.out.println(sum);
    }
}
