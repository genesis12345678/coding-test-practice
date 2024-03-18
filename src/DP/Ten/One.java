package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n] : n을 1,2,3의 합으로 나타낼 수 있는 경우의 수로 생각한다.
 * 규칙을 찾아보면 dp[n] = dp[n-3] + dp[n-2] + dp[n-1] 이라는 점화식이 나온다.
 * dp[1]은 1 밖에 안되므로 1로 초기화 가능하다.
 * dp[2]는 1+1 과 2 밖에 안되므로 2로 초기화 가능하다.
 * dp[3]은 1+1+1, 1+2, 2+1, 3으로 4로 초기화 가능하다.
 * 이렇게 초기화된 값을 바탕으로 나머지 값들을 채워 넣는다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9095>백준 9095번 : DP - 1, 2, 3 더하기</a>
 * @since 2024-03-16
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
