package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n][i]를 다음과 같이 정의한다.
 * 피보나치(n)을 호출했을 떄 i(0 또는 1)가 출력되는 횟수
 * 규칙을 찾아보면
 * dp[n][i] = dp[n-2][i] + dp[n-1][i] 라는 점화식이 나온다.
 * 초깃값을 지정해 놓고 값을 채워나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1003">백준 1003번 : DP - 피보나치 함수</a>
 * @since 2024-03-16
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[][] dp = new int[41][2];

        dp[0][0] = 1;  //피보나치(0)을 호출했을 때 0이 나오는 횟수 = 1
        dp[0][1] = 0;  //피보나치(0)을 호출했을 때 1이 나오는 횟수 = 0

        dp[1][0] = 0;  //피보나치(1)을 호출했을 때 0이 나오는 횟수 = 0
        dp[1][1] = 1;  //피보나치(1)을 호출했을 때 1이 나오는 횟수 = 1

        for (int i = 2; i < 41; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = dp[i - 2][j] + dp[i - 1][j];
            }
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n][0] + " " + dp[n][1]);
        }
    }
}
